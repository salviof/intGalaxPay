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
public enum FabApiRestIntGalaxPayCobrancaSazonal implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/charges?startAt=0&limit=100&customerGalaxPayIds={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"documents"},
            urlDocumentacao = "https://docs.galaxpay.com.br/customers/list",
            adicionarAutenticacaoBearer = true
    )
    COBRANCAS_SAZONAIS_DO_CLIENTE,
    @InfoConsumoRestService(getPachServico = "/charges/{0}/myId",
            tipoConexao = FabTipoConexaoRest.DELETE,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"myId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    COBRANCAS_SAZONAIS_CANCELAR,
    @InfoConsumoRestService(getPachServico = "/charges",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"myId", "ItfPrevisaoValorMoeda", "ItfPessoaFisicoJuridico"},
            urlDocumentacao = "https://docs.galaxpay.com.br/subscriptions/create-with-plan",
            adicionarAutenticacaoBearer = true)
    COBRANCAS_SAZONAIS_CRIAR,
    @InfoConsumoRestService(getPachServico = "/charges?galaxPayIds={0}&startAt=0&limit=1",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"galaxPayId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/individual-charges/list",
            adicionarAutenticacaoBearer = true)
    COBRANCA_SAZONAL_BY_ID_GALAX_PAY,
    @InfoConsumoRestService(getPachServico = "/charges?myIds={0}&startAt=0&limit=1",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"myId"},
            urlDocumentacao = "https://docs.galaxpay.com.br/individual-charges/list",
            adicionarAutenticacaoBearer = true)
    COBRANCA_SAZONAL_BY_MYID;

}
