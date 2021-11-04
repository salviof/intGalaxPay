/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public class UtilSBCOREErp {

    private static Map<String, Map<String, String>> mapaLigacaoEstatico = new HashMap<>();

    public String getCodigoLingacaoDaApi(Class pEntidade, int pCodigoInterno) {
        if (mapaLigacaoEstatico.containsKey(pEntidade.getSimpleName())) {
            return mapaLigacaoEstatico.get(pEntidade.getSimpleName()).get(Integer.toString(pCodigoInterno));
        }
        return null;
    }

    public boolean registrarCodigoLigacaoApi(Class pEntidade, int codigoInterno, String codigoAPIExterna) {
        if (!mapaLigacaoEstatico.containsKey(pEntidade.getSimpleName())) {
            mapaLigacaoEstatico.put(pEntidade.getSimpleName(), new HashMap<>());
        }
        mapaLigacaoEstatico.get(pEntidade.getSimpleName()).put(String.valueOf(codigoInterno), codigoAPIExterna);
        return true;
    }

}
