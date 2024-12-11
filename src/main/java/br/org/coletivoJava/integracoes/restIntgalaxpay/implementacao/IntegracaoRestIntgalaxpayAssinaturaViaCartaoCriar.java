package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Date;

@InfoIntegracaoRestIntgalaxpayAssinatura(tipo = FabApiRestIntGalaxPayAssinatura.ASSINATURA_VIA_CARTAO_CRIAR)
public class IntegracaoRestIntgalaxpayAssinaturaViaCartaoCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayAssinaturaViaCartaoCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayAssinatura.ASSINATURA_VIA_CARTAO_CRIAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        RespostaWebServiceSimples resp = super.gerarRespostaTratamentoFino(pRespostaWSSemTratamento);
        UtilApiGalaxPayRest.aplicarMensagemDeErroPadraoGalaxPay(resp);
        return resp;
    }
}
