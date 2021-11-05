package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobranca;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;
import br.org.coletivoJava.fw.api.erp.contapagarreceber.CtPagarReceberGalaxPay;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.DTOCtPagarReceberJsonAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfFaturaAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.devedor.DTOCtPagarReceberGalaxPayDevedor;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.parcelaSazonal.DTOCtPagarRecebeJsonCobrancaSazonal;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import java.util.ArrayList;
import java.util.Optional;

@CtPagarReceberGalaxPay
public class CtPagarReceberGalaxPayimpl
        implements
        br.org.coletivoJava.fw.api.erp.contaPagarReceber.apiCore.ItfERPContaPagarReceber {

    @Override
    public ItfPrevisaoValorMoeda getCobrancaSazonal(ItfPrevisaoValorMoeda pValor, ItfPessoaFisicoJuridico pDevedor) {

        ItfPessoaFisicoJuridico devedor = getDevedorByCNPJ(pDevedor.getCpfCnpj());
        RespostaWebServiceSimples resposta = FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_DO_CLIENTE.getAcao(devedor.getId()).getResposta();
        JSONObject json = resposta.getRespostaComoObjetoJson();
        List<ItfPrevisaoValorMoeda> cobrancasSazonais = new ArrayList<>();
        JSONArray array = (JSONArray) json.get("Charges");
        for (Object jsonAssinatura : array) {
            DTOCtPagarRecebeJsonCobrancaSazonal cobrancaSazonal = new DTOCtPagarRecebeJsonCobrancaSazonal(jsonAssinatura.toString());
            cobrancasSazonais.add(cobrancaSazonal);
        }
        Optional<ItfPrevisaoValorMoeda> cobrancaCompativel = cobrancasSazonais.stream().filter(asnt
                -> asnt.getValor() == pValor.getValor() && asnt.getDataPrevista().getTime() == pValor.getDataPrevista().getTime())
                .findFirst();
        if (cobrancaCompativel.isPresent()) {
            return cobrancaCompativel.get();
        } else {
            return null;
        }

    }

    @Override
    public ItfPrevisaoValorMoeda getCobrancaAssinatura(ItfPrevisaoValorMoeda pValor, ItfPessoaFisicoJuridico pDevedor) {
        RespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE.getAcao().getResposta();
        List<ItfFaturaAssinatura> assinaturas = getAssinaturasAtivas(pDevedor);
        if (assinaturas.isEmpty()) {
            return null;
        }
        if (assinaturas.size() > 1) {
            //throw new UnsupportedOperationException("Foram encontradas mais de uma assinatura para o cliente");
        }
        Optional<ItfFaturaAssinatura> assinaturaEquivalente
                = assinaturas.stream().filter(ass
                        -> ass.getValorAtualMensal() == pValor.getValor()
                ).findFirst();
        if (!assinaturaEquivalente.isPresent()) {
            return null;
        }
        Optional<ItfPrevisaoValorMoeda> previssao = assinaturaEquivalente.get().getParcelas().stream()
                .filter(parcela -> UtilSBCoreDataHora.isMesIgual(parcela.getDataPrevista(), pValor.getDataPrevista()))
                .findFirst();
        if (!previssao.isPresent()) {
            return null;
        } else {
            return previssao.get();
        }

    }

    @Override
    public ItfFaturaAssinatura getAssinatura(ItfFaturaAssinatura pFaturaRecorrente) {
        // Considerar Valor CPF do cliente, e estado ativo.
        UtilSBCOREErp teste = new UtilSBCOREErp();
        pFaturaRecorrente.getValorAtualMensal();
        pFaturaRecorrente.isAtivo();
        String cnpj = pFaturaRecorrente.getDevedor().getCpfCnpj();
        ItfPessoaFisicoJuridico devedor = getDevedorByCNPJ(cnpj);

        RespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE.getAcao(String.valueOf(devedor.getId())).getResposta();
        JSONObject json = resposta.getRespostaComoObjetoJson();
        System.out.println(json.toJSONString());
        JSONArray array = (JSONArray) json.get("Subscriptions");
        List<ItfFaturaAssinatura> assinaturas = new ArrayList<>();

        for (Object jsonAssinatura : array) {
            DTOCtPagarReceberJsonAssinatura assinatura = new DTOCtPagarReceberJsonAssinatura(jsonAssinatura.toString());
            assinaturas.add(assinatura);
        }
        Optional<ItfFaturaAssinatura> faturaCompativel = assinaturas.stream().filter(asnt
                -> asnt.getValorAtualMensal() == pFaturaRecorrente.getValorAtualMensal() && asnt.isAtivo())
                .findFirst();
        if (faturaCompativel.isPresent()) {
            return faturaCompativel.get();
        } else {
            return null;
        }

    }

    @Override
    public List<ItfRegistroCobranca> getCobrancasSazonaisEmAberto(ItfPessoaFisicoJuridico pPessoas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfFaturaAssinatura> getAssinaturasAtivas(ItfPessoaFisicoJuridico pPessoas) {

        String cnpj = pPessoas.getCpfCnpj();
        ItfPessoaFisicoJuridico devedor = getDevedorByCNPJ(cnpj);

        RespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE.getAcao(String.valueOf(devedor.getId())).getResposta();
        JSONObject json = resposta.getRespostaComoObjetoJson();
        System.out.println(json.toJSONString());
        JSONArray array = (JSONArray) json.get("Subscriptions");
        List<ItfFaturaAssinatura> assinaturas = new ArrayList<>();

        for (Object jsonAssinatura : array) {
            DTOCtPagarReceberJsonAssinatura assinatura = new DTOCtPagarReceberJsonAssinatura(jsonAssinatura.toString());
            if (assinatura.isAtivo()) {
                assinaturas.add(assinatura);
            }
        }
        return assinaturas;
    }

    @Override
    public List<ItfPessoaFisicoJuridico> getDevedoresRegistrados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfPessoaFisicoJuridico getDevedorByCNPJ(String pCNPJ) {

        RespostaWebServiceSimples resp = FabApiRestIntGalaxPayCliente.LISTAR_CLIENTE_BY_DOCUMENTO.getAcao(pCNPJ).getResposta();
        if (resp.isSucesso()) {
            long objetoQuantidade = (long) resp.getRespostaComoObjetoJson().get("totalQtdFoundInPage");
            if (objetoQuantidade == 0) {
                return null;
            }
            JSONArray objetoClientes = (JSONArray) resp.getRespostaComoObjetoJson().get("Customers");
            return new DTOCtPagarReceberGalaxPayDevedor(objetoClientes.get(0).toString());
        }
        return null;
    }

    @Override
    public List<ItfPessoaFisicoJuridico> getDevedorByIdExterno(String pCNPJ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfPessoaFisicoJuridico> getDevedorByIdAplicacao(int pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
