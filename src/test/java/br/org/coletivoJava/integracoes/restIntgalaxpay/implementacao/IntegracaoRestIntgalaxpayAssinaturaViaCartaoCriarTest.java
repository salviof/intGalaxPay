/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayAssinaturaViaCartaoCriarTest {

    public IntegracaoRestIntgalaxpayAssinaturaViaCartaoCriarTest() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestIntgalaxpayAssinaturaViaCartaoCriar.
     */
    @Test
    public void testGerarCorpoRequisicao() {

        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        FabApiRestIntGalaxPayAssinatura.ASSINATURA_VIA_CARTAO_CRIAR.getGestaoToken().getComoTokenDinamico();
        FabApiRestIntGalaxPayAssinatura.ASSINATURA_VIA_CARTAO_CRIAR.getGestaoToken();
        ItfAcaoApiRest acao = FabApiRestIntGalaxPayAssinatura.ASSINATURA_VIA_CARTAO_CRIAR.
                getAcao(1, 1, new Date(), 1,
                        1, "Joao da Silva", "nome cartao", "mesano", "cvv");
        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(resposta.toString());
        }

        /**
         * int idInternoAssinatura = (int) parametros[0]; int idPlanoEscolhido =
         * * (int) parametros[1];
         *
         * Date dataPrimeiroPagamento = (Date) parametros[2];
         *
         * int codigInternoCliente = (int) parametros[3]; int
         * codigoInternoCartao = (int) parametros[4];  <br>
         * int numeroCartao = (int); parametros[5]; <br>
         * String pNomeCartao = (String) parametros[6]; <br>
         * String mesAnoExpira = (String) parametros[7]; <br>
         * String cvv = (String) parametros[8];
         *
         */
    }

}
