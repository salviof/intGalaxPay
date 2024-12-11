/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class FluxoCartaoCompleto {

    private final int CODIGO_ASSINATURA = 693;
    private final int CODIGO_PLANO = 669;
    private final int CODIGO_CLIENTE = 666;

    @Test
    public void inicio() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        _1criarCliente();
        _2editarCliente();
        _3criarPlano();
        _4CriarAssinatura();
        _5CancelarAssinatura();
        _6ExluirPlano();

    }

    public void _1criarCliente() {
        //{"codigoInterno","documento","email","telefone","cep","logradouro","complemento","bairro","cidade","UF"}
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayCliente.CLIENTE_CRIAR_NOVO
                .getAcao(CODIGO_CLIENTE, "Salvio Furbino", "06321298670", "salviof@gmail.com", "+5531984178550",
                        "30190030",
                        "Rua goias 171", "171 ap 64", "Centro", "Belo Horizonte", "MG").getResposta();
        if (!resposta.isSucesso()) {
            System.out.println(resposta.getRespostaTexto());

            System.out.println(resposta.getRetorno());
        }
        Assert.assertTrue("Falha criando cliente", resposta.isSucesso());

    }

    public void _2editarCliente() {
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayCliente.CLIENTE_ATUALIZAR
                .getAcao(CODIGO_CLIENTE, "Salvio Furbino", "06321298670", "salviof@gmail.com", "+5531984178550",
                        "30190030",
                        "Rua goias 171", "171 ap 64", "Centro", "Belo Horizonte", "MG").getResposta();
        Assert.assertTrue("Falha editando cliente", resposta.isSucesso());
    }

    public void _3criarPlano() {
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayPlanos.PLANO_CRIAR
                .getAcao(CODIGO_PLANO, "Plano teste 1", 12, 150.30).getResposta();
        Assert.assertTrue("Falha criando plano", resposta.isSucesso());
    }

    public void _4CriarAssinatura() {
// parametrosPost = {"id", "idplano", "dataPrimeiroPagamento", "códigoInternoCliente", "codigoInternoCartao", "numerocartao", "nome usuário Cartao", "mesAno vencimento cartao 1990-12", "cvv"},
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURA_PLANO_VIA_CARTAO_CRIAR
                .getAcao(CODIGO_ASSINATURA, CODIGO_PLANO, new Date(), CODIGO_CLIENTE,
                        "Salvio Furbino Elias",
                        "06321298670",
                        "salviof@gmail.com",
                        669, "4111 1111 1111 1111", "SALVIO FURBINO", "2022-06", "363")
                .getResposta();
        Assert.assertTrue("Falha editando cliente", resposta.isSucesso());

    }

    public void _5CancelarAssinatura() {
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURA_CANCELAR
                .getAcao(CODIGO_ASSINATURA).getResposta();
        Assert.assertTrue("Falha editando cliente", resposta.isSucesso());
    }

    public void _6ExluirPlano() {
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayPlanos.PLANO_EXCLUIR
                .getAcao(CODIGO_PLANO).getResposta();
        Assert.assertTrue("Falha editando cliente", resposta.isSucesso());
    }

    public void _7ExcluirCliente() {
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayCliente.CLIENTE_EXCLUIR
                .getAcao(CODIGO_CLIENTE).getResposta();
    }

}
