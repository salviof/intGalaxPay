package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Date;

@InfoIntegracaoRestIntgalaxpayAssinatura(tipo = FabApiRestIntGalaxPayAssinatura.ASSINATURA_PLANO_VIA_CARTAO_CRIAR)
public class IntegracaoRestIntgalaxpayAssinaturaPlanoViaCartaoCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayAssinaturaPlanoViaCartaoCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(
                FabApiRestIntGalaxPayAssinatura.ASSINATURA_PLANO_VIA_CARTAO_CRIAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {

        int idInternoAssinatura = (int) parametros.get(0);
        int idPlanoEscolhido = (int) parametros.get(1);

        Date dataPrimeiroPagamento = (Date) parametros.get(2);
        String diaPrimeiroPagamentoFormatado = UtilSBCoreDataHora.
                getDataHoraString(dataPrimeiroPagamento, UtilSBCoreDataHora.FORMATO_TEMPO.ANO_MES_DIA_POR_TRACO);
        UtilSBCoreDataHora.getDataHoraString(dataPrimeiroPagamento, UtilSBCoreDataHora.FORMATO_TEMPO.ANO_MES_DIA_POR_TRACO);

        int codigInternoCliente = (int) parametros.get(3);

        String nome = (String) parametros.get(4);
        String documento = UtilSBCoreStringFiltros.filtrarApenasNumeros((String) parametros.get(5));
        String email = (String) parametros.get(6);

        int codigoInternoCartao = (int) parametros.get(7);

        String numeroCartao = (String) parametros.get(8);
        String pNomeCartao = (String) parametros.get(9);
        String mesAnoExpira = (String) parametros.get(10);
        String cvv = (String) parametros.get(11);
        System.out.println("assinando plano" + idPlanoEscolhido);
        String corpoRequisicao
                = "{\n"
                + "    \"myId\": \"" + idInternoAssinatura + "\",\n"
                + "    \"planMyId\": \"" + idPlanoEscolhido + "\",\n"
                + "    \"firstPayDayDate\": \"" + diaPrimeiroPagamentoFormatado + "\",\n"
                + "    \"mainPaymentMethodId\": \"creditcard\",\n"
                + "    \"Customer\": {\n"
                + "        \"myId\": \"" + codigInternoCliente + "\",\n"
                + "       \"name\": \"" + nome + "\",\n"
                + "        \"document\": \"" + documento + "\",\n"
                + "        \"emails\": [\n"
                + "            \"" + email + "\"\n"
                + "        ]                                             "
                + "    },\n"
                + "    \"PaymentMethodCreditCard\": {\n"
                + "        \"Card\": {\n"
                + "            \"myId\": \"" + codigoInternoCartao + "\",\n"
                + "            \"number\": \"" + numeroCartao + "\",\n"
                + "            \"holder\": \"" + pNomeCartao + "\",\n"
                + "            \"expiresAt\": \"" + mesAnoExpira + "\",\n"
                + "            \"cvv\": \"" + cvv + "\"\n"
                + "        }\n"
                + "    }\n"
                + "}";

        return corpoRequisicao;
    }

}
