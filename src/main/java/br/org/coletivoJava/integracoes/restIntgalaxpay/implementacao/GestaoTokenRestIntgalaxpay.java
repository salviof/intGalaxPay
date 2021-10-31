package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabConfigApiGalaxyPay;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCriptrografia;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpHeaders;
import org.json.simple.JSONObject;

@InfoIntegracaoRestIntgalaxpayCliente(tipo = FabApiRestIntGalaxPayCliente.LISTAR_CLIENTES)
public class GestaoTokenRestIntgalaxpay extends GestaoTokenDinamico {

    @Override
    public boolean validarToken() {
        return false;
    }

    @Override
    public ItfTokenDeAcessoExterno loadTokenArmazenado() {
        return null;
    }

    public GestaoTokenRestIntgalaxpay(
            final FabTipoAgenteClienteApi pTipoAgente, final ItfUsuario pUsuario) {
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
        cabecalhos.put("Content-Type", "application/x-www-form-urlencoded");

        String corpo = "{\"grant_type\": \"" + "authorization_code" + "\",\"scope\": \"customers.read customers.write plans.read plans.write transactions.read transactions.write webhooks.write cards.read cards.write card-brands.read subscriptions.read subscriptions.write charges.read charges.write boletos.read payment-methods.read\"}";
        cabecalhos.put("Content-Length", "" + corpo.getBytes().length);
        RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                cabecalhos, corpo);

        String stringAuthConcatenadaValidacao = "20:XuXlLhYdU4LgBbLnLk9y35CrPjTmCtL4C5WlP50l";
        String validacaoBase64 = java.util.Base64.getEncoder().encodeToString(StringUtils.getBytesUtf8(stringAuthConcatenadaValidacao));
        if (!"MjA6WHVYbExoWWRVNExnQmJMbkxrOXkzNUNyUGpUbUN0TDRDNVdsUDUwbA==".equals(validacaoBase64)) {
            System.out.println("opa opa, Validação falhou");
            System.out.println(validacaoBase64);
            throw new UnsupportedOperationException("Não sabemos criptografar com base 64");
        }

        if (resposta.isSucesso()) {
            armazenarRespostaToken(resposta.getResposta());
            setToken(extrairToken(resposta.getResposta()));

        } else {
            SBCore.enviarAvisoAoUsuario("Falha obtendo token de acesso");
            return null;
        }
        return getTokenCompleto();
    }

    @Override
    public ItfTokenDeAcessoExterno getTokenCompleto() {
        return super.getTokenCompleto(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemTokemAtivo() {
        return super.isTemTokemAtivo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JSONObject pJson) {
        System.out.println("Opa!");
        System.out.println(pJson.toJSONString());

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
