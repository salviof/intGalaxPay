/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayAssinaturasDoClienteCancelarCobrancaRecTest {

    public IntegracaoRestIntgalaxpayAssinaturasDoClienteCancelarCobrancaRecTest() {
    }

    /**
     * Test of gerarRespostaTratamentoFino method, of class
     * IntegracaoRestIntgalaxpayAssinaturasDoClienteCancelarCobrancaRec.
     */
    @Test
    public void testGerarRespostaTratamentoFino() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        IntegracaoRestIntgalaxpayAssinaturasDoClienteCancelarCobrancaRec cobrancaRece = (IntegracaoRestIntgalaxpayAssinaturasDoClienteCancelarCobrancaRec) FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE_CANCELAR_COBRANCA_REC.getAcao("cdn6662");
        ItfRespostaWebServiceSimples resposta = cobrancaRece.getResposta();
        if (resposta.isSucesso()) {
            System.out.println("ok");
            System.out.println(resposta.getRetorno());
        } else {
            resposta.dispararMensagens();
            System.out.println(resposta.getRetorno());
            assertTrue("Falha excluindo assinatura", resposta.isSucesso());
        }

    }

}
