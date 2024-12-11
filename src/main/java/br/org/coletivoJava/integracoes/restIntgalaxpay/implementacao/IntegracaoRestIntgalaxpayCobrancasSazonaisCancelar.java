package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpaySazonal;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntgalaxpaySazonal(tipo = FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CANCELAR)
public class IntegracaoRestIntgalaxpayCobrancasSazonaisCancelar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayCobrancasSazonaisCancelar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CANCELAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "";
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao();
        return url;
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        RespostaWebServiceSimples resp = super.gerarRespostaTratamentoFino(pRespostaWSSemTratamento);
        UtilApiGalaxPayRest.aplicarMensagemDeErroPadraoGalaxPay(resp);
        return resp;
    }
}
