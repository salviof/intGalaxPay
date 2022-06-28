package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
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

        int codigo = (int) parametros[0];
        String nome = (String) parametros[1];
        String documento = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros[2]);
        String email = (String) parametros[3];
        String telefonr = UtilSBCoreStringFiltros.filtrarApenasNumeros(((String) parametros[4]).replace("+55", ""));
        String cep = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros[5]);
        String logradouro = UtilSBCoreStringFiltros.filtrarApenasLetra((String) parametros[6]);
        String numero = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros[6]);
//        String complemnento = (String) parametros[6];
        String bairro = (String) parametros[8];
        String cidade = (String) parametros[9];
        String estado = (String) parametros[10];

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

}
