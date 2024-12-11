/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayCobrancasSazonaisCancelarTest {

    public IntegracaoRestIntgalaxpayCobrancasSazonaisCancelarTest() {
    }

    @Test
    public void testGerarCorpoRequisicao() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        IntegracaoRestIntgalaxpayCobrancasSazonaisCancelar cobrancaRece
                = (IntegracaoRestIntgalaxpayCobrancasSazonaisCancelar) FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CANCELAR
                        .getAcao("951357");
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
