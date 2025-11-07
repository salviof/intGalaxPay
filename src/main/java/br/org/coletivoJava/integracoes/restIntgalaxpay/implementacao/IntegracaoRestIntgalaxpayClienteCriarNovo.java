package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntgalaxpayCliente(tipo = FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO)
public class IntegracaoRestIntgalaxpayClienteCriarNovo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayClienteCriarNovo(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {

        long codigo = (long) parametros.get(0);
        String nome = (String) parametros.get(1);
        String documento = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros.get(2));
        String email = (String) parametros.get(3);
        String telefonr = UtilSBCoreStringFiltros.filtrarApenasNumeros(((String) parametros.get(4)).replace("+55", ""));
        String cep = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros.get(5));
        String logradouro = UtilSBCoreStringFiltros.filtrarApenasLetra((String) parametros.get(6));
        String numero = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros.get(6));
        String bairro = (String) parametros.get(8);
        String cidade = (String) parametros.get(9);
        String estado = (String) parametros.get(10);

        String corpo = "{\n"
                + "    \"myId\": \"" + codigo + "\",\n"
                + "    \"name\": \"" + nome + "\",\n"
                + "    \"document\": \"" + documento + "\",\n"
                + "    \"emails\": [\n"
                + "        \"" + email + "\"\n"
                + "    ],\n"
                + "    \"phones\": [\n"
                + "        " + telefonr + "\n"
                + "    ],\n"
                + "    \"Address\": {\n"
                + "        \"zipCode\": \"" + cep + "\",\n"
                + "        \"street\": \"" + logradouro + "\",\n"
                + "        \"number\": \"" + numero + "\",\n"
                + "        \"neighborhood\": \"" + bairro + "\",\n"
                + "        \"city\": \"" + cidade + "\",\n"
                + "        \"state\": \"" + estado + "\"\n"
                + "    }\n"
                + "}";
        return corpo;
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        RespostaWebServiceSimples resp = super.gerarRespostaTratamentoFino(pRespostaWSSemTratamento);
        UtilApiGalaxPayRest.aplicarMensagemDeErroPadraoGalaxPay(resp);
        return resp;
    }
}
