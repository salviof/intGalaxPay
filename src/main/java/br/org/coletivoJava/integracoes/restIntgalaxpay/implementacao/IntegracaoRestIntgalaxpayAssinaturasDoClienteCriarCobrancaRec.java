package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfFaturaAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@InfoIntegracaoRestIntgalaxpayAssinatura(tipo = FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE_CRIAR_COBRANCA_REC)
public class IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRec
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayAssinaturasDoClienteCriarCobrancaRec(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(
                FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DO_CLIENTE_CRIAR_COBRANCA_REC,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String codigoInterno = parametros.get(0).toString();
        ItfFaturaAssinatura registroDeCobranca = (ItfFaturaAssinatura) parametros.get(1);
        boolean temNotaFiscal = false;
        if (parametros.size() > 2) {
            temNotaFiscal = (boolean) parametros.get(2);
            System.out.println("Tem nota fiscal?" + temNotaFiscal);
        }
        try {
            ComparadorDataPrevisaParcelas comparador = new ComparadorDataPrevisaParcelas();
            registroDeCobranca.getParcelas().sort(comparador);

            List<ItfPrevisaoValorMoeda> parcelasFuturasSemPagamento = new ArrayList<>();
            registroDeCobranca.getParcelas().stream().filter(prc -> !prc.isPagamentoEfetuado()
                    && UtilSBCoreDataHora.isDiaIgualOuSuperior(new Date(), prc.getDataPrevista())
            ).forEach(parcelasFuturasSemPagamento::add);
            parcelasFuturasSemPagamento.sort(comparador);
            Date primeiroPagamento = parcelasFuturasSemPagamento.get(0).getDataPrevista();
            int quantidadeParcelas = parcelasFuturasSemPagamento.size();
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

            String primeiroPgtoStr = formatoData.format(primeiroPagamento);

            JsonObjectBuilder jsonCobrancaAssinatura = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("myId", codigoInterno);
            jsonCobrancaAssinatura.add("aditionalInfo", registroDeCobranca.getNome());
            jsonCobrancaAssinatura.add("mainPaymentMethodId", "boleto");
            jsonCobrancaAssinatura.add("firstPayDayDate", primeiroPgtoStr);
            jsonCobrancaAssinatura.add("quantity", quantidadeParcelas);
            jsonCobrancaAssinatura.add("periodicity", "monthly");
            int valorPrimeiraParcelaEmCentavos = UtilSBCoreNumeros.converterNumeroDoubleToMoedaPadraoBancoEmCentavos(parcelasFuturasSemPagamento.get(0).getValor());
            jsonCobrancaAssinatura.add("value", valorPrimeiraParcelaEmCentavos);

            JsonObjectBuilder clienteJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("myId", String.valueOf(registroDeCobranca.getDevedor().getId()));
            //clienteJson.add("name", registroDeCobranca.getDevedor().getNome());
            clienteJson.add("document", registroDeCobranca.getDevedor().getCpfCnpj());
            //  JsonArrayBuilder arrayDeEmails = Json.createArrayBuilder();
            //  arrayDeEmails.add(registroDeCobranca.getDevedor().getEmail());
            //   clienteJson.add("emails", arrayDeEmails.build());
            //   JsonArrayBuilder arrayTelefone = Json.createArrayBuilder();
            //    arrayTelefone.add(registroDeCobranca.getDevedor().getTelefonePrincipal());
            //   clienteJson.add("phones", arrayTelefone);

            jsonCobrancaAssinatura.add("Customer", clienteJson.build());

            JsonArrayBuilder arrayTransacoes = Json.createArrayBuilder();
            int numeroParcela = 1;
            for (ItfPrevisaoValorMoeda paracela : parcelasFuturasSemPagamento) {
                if (!paracela.isPagamentoEfetuado() && UtilSBCoreDataHora.isDiaIgualOuSuperior(new Date(), paracela.getDataPrevista())) {
                    JsonObjectBuilder paracelaBuilder = Json.createObjectBuilder();
                    int idLocal = paracela.getId();
                    paracelaBuilder.add("myId", String.valueOf(idLocal));
                    paracelaBuilder.add("installment", numeroParcela);
                    int valorEmCentavos = UtilSBCoreNumeros.converterNumeroDoubleToMoedaPadraoBancoEmCentavos(paracela.getValor());
                    paracelaBuilder.add("value", valorEmCentavos);

                    paracelaBuilder.add("payday", formatoData.format(paracela.getDataPrevista()));
                    paracelaBuilder.add("payedOutsideGalaxPay", false);
                    paracelaBuilder.add("additionalInfo", paracela.getNome());
                    numeroParcela++;
                    arrayTransacoes.add(paracelaBuilder.build());
                }
            }
            jsonCobrancaAssinatura.add("Transactions", arrayTransacoes.build());
            if (temNotaFiscal) {
                JsonObjectBuilder detalhesNotaFiscal = Json.createObjectBuilder();
                detalhesNotaFiscal.add("description", "Prestação de serviços de publicidade online.");
                detalhesNotaFiscal.add("type", "onePerTransaction");
                detalhesNotaFiscal.add("createOn", "daysBeforePayDay");
                detalhesNotaFiscal.add("qtdDaysBeforePayDay", 2);
                jsonCobrancaAssinatura.add("InvoiceConfig", detalhesNotaFiscal.build());
            }
            JsonObjectBuilder dadosDeCobrancaNoBoleto = Json.createObjectBuilder();
            dadosDeCobrancaNoBoleto.add("fine", 200);
            dadosDeCobrancaNoBoleto.add("interest", 100);
            dadosDeCobrancaNoBoleto.add("instructions", "Este boleto pode ser pago em qualquer banco, mesmo que vencido, desde que não ultrapasse 10 dia(s) da data do vencimento (sujeito a multa e juros).");
            dadosDeCobrancaNoBoleto.add("deadlineDays", 10);
            jsonCobrancaAssinatura.add("PaymentMethodBoleto", dadosDeCobrancaNoBoleto.build());
            String reposta = UtilSBCoreJson.getTextoByJsonObjeect(jsonCobrancaAssinatura.build());
            return reposta;

        } catch (ErroProcessandoJson ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando json envio criação registro de cobranca" + ex.getMessage(), ex);
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
