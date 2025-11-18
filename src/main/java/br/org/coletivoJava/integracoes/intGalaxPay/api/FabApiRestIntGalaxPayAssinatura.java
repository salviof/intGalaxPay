/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.intGalaxPay.api;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ComoFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

/**
 *
 * @author desenvolvedorninja01
 * @since 30/01/2020
 * @version 1.0
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://docs.galaxpay.com.br/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigApiGalaxyPay.NOME_INTEGRACAO,
        configuracao = FabConfigApiGalaxyPay.class
)
public enum FabApiRestIntGalaxPayAssinatura implements ComoFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/subscriptions?startAt=0&limit=100&customerGalaxPayIds={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/list",
            adicionarAutenticacaoBearer = true)
    ASSINATURAS_DO_CLIENTE,
    @InfoConsumoRestService(getPachServico = "/subscriptions?startAt={0}&limit={1}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            aceitarCertificadoDeHostNaoConfiavel = false,
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/list",
            adicionarAutenticacaoBearer = true)
    ASSINATURAS_DE_CLIENTES,
    @InfoConsumoRestService(getPachServico = "/subscriptions?myIds={0}&startAt=0&limit=1",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"myId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions",
            adicionarAutenticacaoBearer = true)
    ASSINATURAS_DO_CLIENTE_GET_BY_MY_ID,
    @InfoConsumoRestService(getPachServico = "/subscriptions/{0}/myId",
            tipoConexao = FabTipoConexaoRest.DELETE,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"myId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/cancel",
            adicionarAutenticacaoBearer = true)
    ASSINATURAS_DO_CLIENTE_CANCELAR_COBRANCA_REC,
    @InfoConsumoRestService(getPachServico = "/subscriptions",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"myId", "ItfFaturaAssinatura"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    ASSINATURAS_DO_CLIENTE_CRIAR_COBRANCA_REC,
    @InfoConsumoRestService(getPachServico = "/charges",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_PLANO_VIA_PIX_CRIAR,
    @InfoConsumoRestService(getPachServico = "/subscriptions",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            parametrosPost = {"id", "idplano", "dataPrimeiroPagamento", "códigoInternoCliente", "codigoInternoCartao", "numerocartao", "nome usuário Cartao", "mesAno vencimento cartao 1990-12", "cvv"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_PLANO_VIA_CARTAO_CRIAR,
    @InfoConsumoRestService(getPachServico = "/subscriptions",
            tipoConexao = FabTipoConexaoRest.POST,
            parametrosPost = {"Código interno", "Codigo interno plano", "codigoInternoCliente", "dataPrimeiroPagamento",
                "codigoInternoCartao", "numeroCartao",
                "NomeUsuárioCartão", "Mes Ano Expira", "CVV"},
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_VIA_CARTAO_CRIAR,
    @InfoConsumoRestService(getPachServico = "/subscriptions",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_VIA_PIX_CRIAR,
    @InfoConsumoRestService(getPachServico = "/subscriptions/{0}/myId",
            tipoConexao = FabTipoConexaoRest.DELETE,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/cancel",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_CANCELAR,
    @InfoConsumoRestService(getPachServico = "/subscriptions?myIds={0}&startAt=0&limit=1",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"myId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions",
            adicionarAutenticacaoBearer = true)
    ASSINATURA_BY_MY_ID,

}
