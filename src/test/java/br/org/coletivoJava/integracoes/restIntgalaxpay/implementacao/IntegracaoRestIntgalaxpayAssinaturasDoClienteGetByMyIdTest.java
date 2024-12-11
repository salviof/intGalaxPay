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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayAssinaturasDoClienteGetByMyIdTest {

    public IntegracaoRestIntgalaxpayAssinaturasDoClienteGetByMyIdTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of gerarUrlRequisicao method, of class
     * IntegracaoRestIntgalaxpayAssinaturasDoClienteGetByMyId.
     */
    @Test
    public void testGerarUrlRequisicao() {

        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        IntegracaoRestIntgalaxpayAssinaturasDoClienteGetByMyId cobrancaRece = (IntegracaoRestIntgalaxpayAssinaturasDoClienteGetByMyId) FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE_GET_BY_MY_ID.getAcao("testecnd123");
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
