/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.Map;

/**
 *
 * @author salvio
 */
public class UtilApiGalaxPayRest {

    public static void aplicarMensagemDeErroPadraoGalaxPay(RespostaWebServiceSimples pResposta) {
        if (!pResposta.isSucesso()) {
            String jsonResp = (String) pResposta.getRetorno();

            if (jsonResp != null && !jsonResp.isEmpty() && jsonResp.startsWith("{")) {
                JsonObject json = UtilSBCoreJson.getJsonObjectByTexto(jsonResp);
                if (json.containsKey("error")) {
                    pResposta.getMensagens().clear();
                    String tituloMensagem = json.getJsonObject("error").getString("message");
                    pResposta.addErro(tituloMensagem);
                    if (json.getJsonObject("error").containsKey("details")) {
                        for (Map.Entry entrada : json.getJsonObject("error").getJsonObject("details").entrySet()) {
                            pResposta.addErro(entrada.getKey().toString() + ": " + entrada.getValue().toString());
                        }
                    }

                }
            }
        }
    }

}
