/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.intGalaxPay.api;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
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
public enum FabApiRestIntGalaxPayCliente implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/customers?startAt={0}&limit={1}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"startAt", "limit"},
            urlDocumentacao = "https://docs.galaxpay.com.br/customers/",
            adicionarAutenticacaoBearer = true)
    CLIENTE_LISTAR,
    @InfoConsumoRestService(getPachServico = "/customers?startAt=0&limit=1&documents={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            urlDocumentacao = "https://docs.galaxpay.com.br/customers/list",
            adicionarAutenticacaoBearer = true)
    CLIENTE_LISTAR_BY_DOCUMENTO,
    @InfoConsumoRestService(getPachServico = "/customers",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"codigoInterno", "nome", "documento", "email", "telefone", "cep", "logradouro", "complemento", "bairro",
                "cidade", "UF"},
            parametrosGet = {"documents"},
            urlDocumentacao = "https://docs.galaxpay.com.br/customers/list",
            adicionarAutenticacaoBearer = true)
    CLIENTE_CRIAR_NOVO,
    @InfoConsumoRestService(getPachServico = "/customers/{0}/myId",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"codigoInterno"},
            parametrosPost = {"codigoInterno", "nome", "documento", "email", "telefone", "cep", "logradouro", "complemento", "bairro",
                "cidade", "UF"},
            urlDocumentacao = "https://docs.galaxpay.com.br/customers/list",
            adicionarAutenticacaoBearer = true)
    CLIENTE_ATUALIZAR,
    @InfoConsumoRestService(getPachServico = "/customers/{0}/myId",
            tipoConexao = FabTipoConexaoRest.DELETE,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"codigoInterno"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/cancel",
            adicionarAutenticacaoBearer = true)
    CLIENTE_EXCLUIR;

}
