package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabConfigApiGalaxyPay;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoDinamico;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenDinamico;
import jakarta.json.JsonObject;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpHeaders;
import org.json.simple.JSONObject;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntgalaxpayCliente(tipo = FabApiRestIntGalaxPayCliente.CLIENTE_LISTAR)
public class GestaoTokenRestIntgalaxpay extends GestaoTokenDinamico {

    public GestaoTokenRestIntgalaxpay(
            final FabTipoAgenteClienteApi pTipoAgente, final ComoUsuario pUsuario) {
        super(FabApiRestIntGalaxPayCliente.class, pTipoAgente, pUsuario);
    }

    /**
     * https://docs.galaxpay.com.br/autenticacao
     *
     * @return
     */
    @Override
    public ItfTokenDeAcessoExterno gerarNovoToken() {
        if (validarToken()) {
            return getTokenCompleto();
        }
        //Segundo a documentação, o token é válido por 600 segundos
        String url = getConfig().getPropriedade(FabConfigApiGalaxyPay.GALAX_URL) + "/token";
        String galaxID = getConfig().getPropriedade(FabConfigApiGalaxyPay.GALAXID);
        String galaxHash = getConfig().getPropriedade(FabConfigApiGalaxyPay.GALAXHASH);

        //
        //
        HashMap<String, String> cabecalhos = new HashMap<>();

        String stringAuthContatenada = galaxID + ":" + galaxHash;

        String tokenAutenticador = java.util.Base64.getEncoder().encodeToString(StringUtils.getBytesUtf8(stringAuthContatenada));

        cabecalhos.put(HttpHeaders.AUTHORIZATION, "Basic " + tokenAutenticador);
        cabecalhos.put("Content-Type", "application/json");
        cabecalhos.put("user-agent", "casanovaDigitalServer");
        String corpo = "{\"grant_type\": \"" + "authorization_code" + "\",\"scope\": \"customers.read customers.write plans.read plans.write transactions.read transactions.write webhooks.write cards.read cards.write card-brands.read subscriptions.read subscriptions.write charges.read charges.write boletos.read payment-methods.read\"}";
        //   cabecalhos.put("Content-Length", "" + corpo.getBytes().length);

        RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                cabecalhos, corpo, false);

        if (resposta.isSucesso()) {
            JsonObject jsonArquivado = resposta.getRespostaComoObjetoJson();
            jsonArquivado = UtilSBCoreJson.getJsonObjectIncrementandoCampo(jsonArquivado, "dataHora", new Date().getTime());
            //String textoResposta = UtilSBCoreJson.getTextoByJsonObjeect(jsonArquivado);
            String textoJson = UtilSBCoreJson.getTextoByJsonObjeect(jsonArquivado);
            armazenarRespostaToken(textoJson);
            setToken(extrairToken(textoJson));

        } else {
            SBCore.enviarAvisoAoUsuario("Falha obtendo token de acesso" + resposta.getMensagens().get(0).getMenssagem());
            return null;
        }
        return getTokenCompleto();
    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JsonObject pJson) {

        String tokenDeAcesso = pJson.getString("access_token");
        Long segundosExpira = (long) pJson.getJsonNumber("expires_in").longValue();
        Date dataHoraExipira = UtilSBCoreDataHora.decrementaMinutos(new Date(), 30);
        if (pJson.containsKey("dataHora")) {
            Date dataHoraGeracaoToken = new Date((long) pJson.getJsonNumber("dataHora").longValue());
            dataHoraExipira = UtilSBCoreDataHora.incrementaSegundos(dataHoraGeracaoToken, segundosExpira.intValue());
        }

        UtilSBCoreDataHora.incrementaSegundos(dataHoraExipira, segundosExpira.intValue());
        TokenDeAcessoExternoDinamico token = new TokenDeAcessoExternoDinamico(tokenDeAcesso, dataHoraExipira);
        return token;

    }

    @Override
    public boolean validarToken() {
        if (getTokenCompleto() == null) {
            return false;
        }
        return getTokenCompleto().isTokenValido();
    }

    @Override
    public boolean isTemTokemAtivo() {
        return super.isTemTokemAtivo(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public ItfTokenDeAcessoExterno loadTokenArmazenado() {
        return super.loadTokenArmazenado(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public JsonObject loadTokenArmazenadoComoJsonObject() {
        return super.loadTokenArmazenadoComoJsonObject(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
