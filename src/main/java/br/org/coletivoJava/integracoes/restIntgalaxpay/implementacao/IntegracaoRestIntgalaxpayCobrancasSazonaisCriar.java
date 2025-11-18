package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpaySazonal;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCobrancaSazonal;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ComoPessoaFisicoJuridico;

@InfoIntegracaoRestIntgalaxpaySazonal(tipo = FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CRIAR)
public class IntegracaoRestIntgalaxpayCobrancasSazonaisCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayCobrancasSazonaisCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayCobrancaSazonal.COBRANCAS_SAZONAIS_CRIAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        try {
            String codigoInterno = parametros.get(0).toString();
            ItfPrevisaoValorMoeda previsaoRecebimento = (ItfPrevisaoValorMoeda) parametros.get(1);
            previsaoRecebimento.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.NOME).getValor();
            boolean temNotaFiscal = false;
            ComoPessoaFisicoJuridico cliente = (ComoPessoaFisicoJuridico) parametros.get(2);
            if (parametros.size() > 3) {
                temNotaFiscal = (boolean) parametros.get(3);
            }
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            JsonObjectBuilder jsonCobrancaSazonal;
            try {
                jsonCobrancaSazonal = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("myId", codigoInterno);
                int valorPrimeiraParcelaEmCentavos = UtilSBCoreNumeros.converterNumeroDoubleToMoedaPadraoBancoEmCentavos(previsaoRecebimento.getValor());
                jsonCobrancaSazonal.add("value", valorPrimeiraParcelaEmCentavos);
                System.out.println("A DESCRICAO CADASTRADAS é:" + previsaoRecebimento.getNome());
                jsonCobrancaSazonal.add("aditionalInfo", previsaoRecebimento.getNome());
                jsonCobrancaSazonal.add("payday", formatoData.format(previsaoRecebimento.getDataPrevista()));
                jsonCobrancaSazonal.add("mainPaymentMethodId", "boleto");
                JsonObjectBuilder clienteJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("myId", String.valueOf(cliente.getId()));
                //clienteJson.add("name", cliente.getNome());
                clienteJson.add("document", cliente.getCpfCnpj());

                jsonCobrancaSazonal.add("Customer", clienteJson.build());
                if (temNotaFiscal) {
                    JsonObjectBuilder detalhesNotaFiscal = Json.createObjectBuilder();
                    detalhesNotaFiscal.add("description", previsaoRecebimento.getNome());
                    detalhesNotaFiscal.add("type", "onePerTransaction");
                    detalhesNotaFiscal.add("createOn", "daysBeforePayDay");
                    detalhesNotaFiscal.add("qtdDaysBeforePayDay", 2);
                    jsonCobrancaSazonal.add("InvoiceConfig", detalhesNotaFiscal.build());
                }
                JsonObjectBuilder jsonDetalhesBoleto = Json.createObjectBuilder();
                jsonDetalhesBoleto.add("instructions", previsaoRecebimento.getNome());
                jsonCobrancaSazonal.add("PaymentMethodBoleto", jsonDetalhesBoleto.build());
                String corpoRequisicao = UtilSBCoreJson.getTextoByJsonObjeect(jsonCobrancaSazonal.build());

                return corpoRequisicao;
            } catch (ErroProcessandoJson ex) {
                Logger.getLogger(IntegracaoRestIntgalaxpayCobrancasSazonaisCriar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Throwable t) {
            System.out.println("Erro gerando corpo de requisição sazonal");
        }
        return null;

    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        RespostaWebServiceSimples resp = super.gerarRespostaTratamentoFino(pRespostaWSSemTratamento);
        UtilApiGalaxPayRest.aplicarMensagemDeErroPadraoGalaxPay(resp);
        return resp;
    }
}
