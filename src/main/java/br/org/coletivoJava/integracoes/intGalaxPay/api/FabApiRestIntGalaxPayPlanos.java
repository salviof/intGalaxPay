/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.coletivoJava.integracoes.intGalaxPay.api;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://docs.galaxpay.com.br/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigApiGalaxyPay.NOME_INTEGRACAO,
        configuracao = FabConfigApiGalaxyPay.class
)
public enum FabApiRestIntGalaxPayPlanos implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/plans?startAt={0}&limit={1}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/list",
            adicionarAutenticacaoBearer = true)
    PLANO_LISTAR,
    @InfoConsumoRestService(getPachServico = "/plans?myIds={0}&startAt=0&limit=10",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/list",
            adicionarAutenticacaoBearer = true)
    PLANO_LISTAR_MEU_ID,
    @InfoConsumoRestService(getPachServico = "/plans?startAt=1&limit=10&createdAtTo=2022-11-14",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/list",
            adicionarAutenticacaoBearer = true)
    PLANO_LISTAR_CRIADO_HOJE,
    @InfoConsumoRestService(getPachServico = "/plans",
            tipoConexao = FabTipoConexaoRest.POST,
            parametrosPost = {"Código Interno", "Nome", "Meses", "valor"},
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/create",
            adicionarAutenticacaoBearer = true)
    PLANO_CRIAR,
    @InfoConsumoRestService(getPachServico = "/plans/{0}/myId",
            tipoConexao = FabTipoConexaoRest.PUT,
            parametrosPost = {"Código Interno", "Nome", "Meses", "valor"},
            parametrosGet = {"código interno"},
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/create",
            adicionarAutenticacaoBearer = true)
    PLANO_EDITAR,
    @InfoConsumoRestService(getPachServico = "/plans/{0}/myId",
            parametrosGet = {"plaId"},
            tipoConexao = FabTipoConexaoRest.DELETE,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://docs.galaxpay.com.br/plans/delete",
            adicionarAutenticacaoBearer = true)
    PLANO_EXCLUIR,

}
