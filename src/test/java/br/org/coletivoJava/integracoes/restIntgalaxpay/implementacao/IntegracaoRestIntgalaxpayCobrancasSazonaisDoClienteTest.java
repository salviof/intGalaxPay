/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
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
public class IntegracaoRestIntgalaxpayCobrancasSazonaisDoClienteTest {

    public IntegracaoRestIntgalaxpayCobrancasSazonaisDoClienteTest() {
    }

    /**
     * Test of gerarRespostaTratamentoFino method, of class
     * IntegracaoRestIntgalaxpayCobrancasSazonaisDoCliente.
     */
    @Test
    public void testGerarRespostaTratamentoFino() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        ItfTokenGestao gestap = FabApiRestIntGalaxPayCliente.CLIENTE_LISTAR_BY_DOCUMENTO.getGestaoToken();
        if (gestap.isTemTokemAtivo()) {
            gestap.gerarNovoToken();
        }
        IntegracaoRestIntgalaxpayCobrancasSazonaisDoCliente acao = (IntegracaoRestIntgalaxpayCobrancasSazonaisDoCliente) FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_DO_CLIENTE.getAcao(10);

        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        System.out.println(resposta.getRespostaTexto());
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        }
    }

}
