/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PessoaFisicoJuridico.DTOPessoaFisicoJuridico;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
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
public class IntegracaoRestIntgalaxpayClienteListarByDocumentoTest {

    public IntegracaoRestIntgalaxpayClienteListarByDocumentoTest() {
    }

    /**
     * Test of gerarRespostaTratamentoFino method, of class
     * IntegracaoRestIntgalaxpayClienteListarByDocumento.
     */
    @Test
    public void testGerarRespostaTratamentoFino() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO.getGestaoToken();
        IntegracaoRestIntgalaxpayClienteListarByDocumento acao = (IntegracaoRestIntgalaxpayClienteListarByDocumento) FabApiRestIntGalaxPayCliente.CLIENTE_LISTAR_BY_DOCUMENTO
                .getAcao("90128701510");
        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        if (!resposta.isSucesso()) {
            resposta.dispararMensagens();
        }
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        DTOPessoaFisicoJuridico pessoa = new DTOPessoaFisicoJuridico(resposta.getRetorno().toString());
        ItfLocalPostagem localizacao = pessoa.getLocalizacao();
        if (resposta.isSucesso()) {
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        }
    }

}
