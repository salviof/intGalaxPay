package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayTransacao;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayTransacao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntgalaxpayTransacao(tipo = FabApiRestIntGalaxPayTransacao.TRANSACOES_COMPENSADAS_PERIODO)
public class IntegracaoRestIntgalaxpayTransacoesCompensadasPeriodo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayTransacoesCompensadasPeriodo(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayTransacao.TRANSACOES_COMPENSADAS_PERIODO,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao();
        return url;
    }

}
