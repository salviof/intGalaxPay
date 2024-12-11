/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayPlanoCriarTest {

    public IntegracaoRestIntgalaxpayPlanoCriarTest() {
    }

    @Test
    public void testSomeMethod() {

        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        FabApiRestIntGalaxPayPlanos.PLANO_CRIAR.getGestaoToken().getComoTokenDinamico();
        ItfTokenGestao gestao = FabApiRestIntGalaxPayPlanos.PLANO_CRIAR.getGestaoToken();
        if (!gestao.isTemTokemAtivo()) {
            gestao.gerarNovoToken();
        }
        ItfAcaoApiRest acao = FabApiRestIntGalaxPayPlanos.PLANO_CRIAR.getAcao(898972465, "plano testes", 12, 130.5);

        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        System.out.println(resposta.getRespostaTexto());
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(resposta.toString());
        }

    }

}
