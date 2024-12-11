/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.FaturaAssinatura.DTOFaturaAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreInputOutputConversoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRecTest {

    public IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRecTest() {
    }

    @Test
    public void testGerarCorpoRequisicao() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        String jsonCobrancaSazonal = UtilSBCoreInputOutputConversoes.getStringUTF8(this.getClass().getResourceAsStream("/exemplos/galaxPay/envioAssinaturaRecorrenteBoleto.json"));

        DTOFaturaAssinatura assinaturaTeste = new DTOFaturaAssinatura(jsonCobrancaSazonal);
        assinaturaTeste.getNome();
        assinaturaTeste.getParcelas();

        IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRec cobrancaRece = (IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRec) FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE_CRIAR_COBRANCA_REC
                .getAcao("testecnd123", assinaturaTeste);

        ItfRespostaWebServiceSimples resposta = cobrancaRece.getResposta();
        assertNotNull("Resposta returnou nula", resposta);
//        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        } else {
            System.out.println(resposta.getRespostaTexto());
            System.out.println(resposta.getMensagens().get(0).getMenssagem());
            resposta.dispararMensagens();
        }
    }

}
