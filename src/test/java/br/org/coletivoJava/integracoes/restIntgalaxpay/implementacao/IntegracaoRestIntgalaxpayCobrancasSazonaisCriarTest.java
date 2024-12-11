/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PessoaFisicoJuridico.DTOPessoaFisicoJuridico;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PrevisaoValorMoeda.DTOPrevisaoValorMoeda;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreInputOutputConversoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import jakarta.json.JsonObject;
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
public class IntegracaoRestIntgalaxpayCobrancasSazonaisCriarTest {

    public IntegracaoRestIntgalaxpayCobrancasSazonaisCriarTest() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestIntgalaxpayCobrancasSazonaisCriar.
     */
    @Test
    public void testGerarCorpoRequisicao() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        String json = UtilSBCoreInputOutputConversoes.getStringUTF8(this.getClass().getResourceAsStream("/exemplos/galaxPay/envioAssinaturaSazonalBoleto.json"));
        String jsonCliente = UtilSBCoreInputOutputConversoes.getStringUTF8(this.getClass().getResourceAsStream("/exemplos/galaxPay/envioAssinaturaSazonalCliente.json"));

        DTOPessoaFisicoJuridico pessoa = new DTOPessoaFisicoJuridico(jsonCliente);
        DTOPrevisaoValorMoeda assinaturaTeste = new DTOPrevisaoValorMoeda(json);
        IntegracaoRestIntgalaxpayCobrancasSazonaisCriar cobrancaRece
                = (IntegracaoRestIntgalaxpayCobrancasSazonaisCriar) FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CRIAR
                        .getAcao("951358", assinaturaTeste, pessoa);

        ItfRespostaWebServiceSimples resposta = cobrancaRece.getResposta();
        assertNotNull("Resposta returnou nula", resposta);

//        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            String retornop = (String) resposta.getRetorno();
            JsonObject jsonFaturaSazonal = UtilSBCoreJson.getJsonObjectByTexto(retornop);

            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        } else {
            System.out.println(resposta.getRespostaTexto());
            System.out.println(resposta.getMensagens().get(0).getMenssagem());
            resposta.dispararMensagens();
        }
    }

}
