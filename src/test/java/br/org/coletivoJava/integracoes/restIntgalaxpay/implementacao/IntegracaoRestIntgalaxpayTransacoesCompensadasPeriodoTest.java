/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayTransacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayTransacoesCompensadasPeriodoTest {

    public IntegracaoRestIntgalaxpayTransacoesCompensadasPeriodoTest() {
    }

    @Test
    public void testSomeMethod() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao token = FabApiRestIntGalaxPayPlanos.PLANO_CRIAR.getGestaoToken();
        if (!token.isTemTokemAtivo()) {
            token.gerarNovoToken();
        }

        double total = 0;
        boolean coletouTodos = false;
        int start = 20000;

        while (!coletouTodos) {

            ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayTransacao.TRANSACOES_COMPENSADAS_PERIODO.getAcao("01-07-23", "31-07-23", start).getResposta();
            System.out.println(resposta.getRespostaTexto());
            JsonObject json = resposta.getRespostaComoObjetoJson();
            JsonArray jsonTransacoes = json.getJsonArray("Transactions");
            if (jsonTransacoes.isEmpty()) {
                coletouTodos = true;
                continue;
            }
            for (JsonObject jsonTranscao : jsonTransacoes.getValuesAs(JsonObject.class)) {
                try {
                    double valor = UtilSBCoreNumeros.converterMoedaPadraoBancoParaDouble(String.valueOf(jsonTranscao.getInt("value")));
                    total = total + valor;
                } catch (Throwable t) {
                    System.out.println("Erro");
                    System.out.println(t.getMessage());
                }

            }
            start = start + 100;

        }

        System.out.println(total);

    }

}
