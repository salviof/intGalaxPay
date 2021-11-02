package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobranca;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfValorMoedaFuturo;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobrancaAssinatura;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;
import br.org.coletivoJava.fw.api.erp.contapagarreceber.CtPagarReceberGalaxPay;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.DTOCtPagarReceberJsonAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@CtPagarReceberGalaxPay
public class CtPagarReceberGalaxPayimpl
        implements
        br.org.coletivoJava.fw.api.erp.contaPagarReceber.apiCore.ItfERPContaPagarReceber {

    @Override
    public ItfRegistroCobranca getCobrancaSazonal(ItfValorMoedaFuturo pValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfRegistroCobranca getCobrancaAssinatura(ItfRegistroCobrancaAssinatura pValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAssinatura getAssinatura(ItfAssinatura pFaturaRecorrente) {
        // Considerar Valor CPF do cliente, e estado ativo.
        RespostaWebServiceSimples resposta = FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE.getAcao("5").getResposta();
        JSONObject json = resposta.getRespostaComoObjetoJson();
        System.out.println(json.toJSONString());
        JSONArray array = (JSONArray) json.get("Subscriptions");
        DTOCtPagarReceberJsonAssinatura assinatura = new DTOCtPagarReceberJsonAssinatura(array.get(1).toString());
        System.out.println(assinatura.getQtdParcelas());

        DTOCtPagarReceberJsonAssinatura assinatura2 = new DTOCtPagarReceberJsonAssinatura(array.get(1).toString());
        try {
            List<ItfValorMoedaFuturo> parcelas = (List) assinatura2.getParcelas();
            for (ItfValorMoedaFuturo parcela : parcelas) {
                System.out.println(parcela.getValor());
            }
        } catch (Throwable t) {
            System.out.println("PAU" + t.getMessage());
        }
        return assinatura;
    }

    @Override
    public List<ItfRegistroCobranca> getCobrancasSazonaisEmAberto(ItfPessoaFisicoJuridico pPessoas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfRegistroCobrancaAssinatura> getAssinaturasAtivas(ItfPessoaFisicoJuridico pPessoas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfPessoaFisicoJuridico> getDevedoresRegistrados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfPessoaFisicoJuridico getDevedorByCNPJ(String pCNPJ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
