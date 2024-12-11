/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayClienteCriarNovoTest {

    public IntegracaoRestIntgalaxpayClienteCriarNovoTest() {
    }

    @Test
    public void testSomeMethod() {

        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO.getGestaoToken();
        IntegracaoRestIntgalaxpayClienteCriarNovo acao = (IntegracaoRestIntgalaxpayClienteCriarNovo) FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO
                .getAcao(666, "Joao da Silva", "82847467009", "joaoteste@cs.com.br", "3184178555", "30190030", "Rua goias 171", "Ap 41", "centro", "Belo horizonte", "MG");
        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        if (!resposta.isSucesso()) {
            resposta.dispararMensagens();
        }
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        }

    }
}
