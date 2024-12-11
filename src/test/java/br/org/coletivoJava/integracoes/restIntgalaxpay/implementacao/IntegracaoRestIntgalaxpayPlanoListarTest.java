package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayPlanoListarTest {

    public IntegracaoRestIntgalaxpayPlanoListarTest() {
    }

    /**
     * Test of executarAcao method, of class
     * IntegracaoRestIntgalaxpayPlanoListar.
     */
    @Test
    public void testExecutarAcao() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayPlanos.PLANO_LISTAR.getAcao(0, 100).getResposta();

        assertTrue("Falhou ao obter a lista de planos", resposta.isSucesso());

    }

}
