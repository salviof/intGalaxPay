/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.JsonProcessAssinaturaDTO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela.DTOCtPagarReceberJsonParcelaAssinatura;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sfurbino
 */
public abstract class DTO_SB_JSON_PROCESSADOR_GENERICO<T> extends StdDeserializer<T> {

    private final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    private final Map<String, DTO_SBGENERICO> objetosArmazenados = new HashMap<>();
    private Map<String, List<DTO_SBGENERICO>> listasArmazenadas;

    protected void adicionarListas(String pAtributo, List pLista) {
        if (listasArmazenadas == null) {
            listasArmazenadas = new HashMap<>();
        }
        listasArmazenadas.put(pAtributo, pLista);
    }

    protected void adicionarObjeto(String pAtributo, DTO_SBGENERICO pObjeto) {
        objetosArmazenados.put(pAtributo, pObjeto);
    }

    protected void setValor(int pValor) {
        objectBuilder.add("qtdBoletos", (int) pValor);
    }

    protected void setValor(String pValor) {
        objectBuilder.add("qtdBoletos", (String) pValor);
    }

    public void getValorComoInteiro() {
        objectBuilder.build();
    }

    protected JsonObjectBuilder getObjectBuilder() {
        return objectBuilder;
    }

    public DTO_SB_JSON_PROCESSADOR_GENERICO(Class<? extends T> pClasse) {
        super(pClasse);
    }

    protected void selarProcesamento(DTO_SBGENERICO dto) {

        dto.setDadosDoObjeto(objectBuilder.build(), objetosArmazenados, listasArmazenadas);
    }

    protected boolean adicionarPropriedadeInteiro(String pnome, JsonNode node, String pCaminho) {
        try {
            getObjectBuilder().add(pnome, node.get(pCaminho).asInt());
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    protected boolean adicionarPropriedadeBoolean(String pnome, String valorVerdadeiro, JsonNode node, String pCaminho) {
        try {

            getObjectBuilder().add(pnome, node.get(pCaminho).asText().equals(valorVerdadeiro));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    protected boolean adicionarPropriedadeDouble(String pnome, JsonNode node, String caminho) {
        try {
            String valor = node.get(caminho).asText();
            int tamanhoTotal = valor.length();
            valor = valor.substring(0, tamanhoTotal - 2) + "." + valor.substring(tamanhoTotal - 2, tamanhoTotal);
            //DecimalFormat df = new DecimalFormat("#.00");
            //df.setRoundingMode(RoundingMode.HALF_DOWN);
            //df.format(valor);
            double valorDouble = Double.parseDouble(valor);
            getObjectBuilder().add(pnome, valorDouble);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    protected boolean adicionarPropriedadeData(String pnome, JsonNode node, String caminho) {
        try {
            getObjectBuilder().add(pnome, node.get(caminho).asInt());
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    protected boolean adicionarPropriedadeString(String pnome, JsonNode node, String caminho) {
        try {
            getObjectBuilder().add(pnome, node.get(caminho).asText());
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    protected boolean adicionarPropriedadeListaObjetos(Class classeObjeto, JsonNode node, String caminho) {

        JsonNode parcelasJson = node.get(caminho);
        List<DTOCtPagarReceberJsonParcelaAssinatura> parcelas = new ArrayList();

        for (Iterator<JsonNode> iterator = parcelasJson.elements(); iterator.hasNext();) {
            JsonNode next = iterator.next();
            Constructor consTructorDTO;
            try {
                consTructorDTO = classeObjeto.getConstructor(String.class);
                parcelas.add((DTOCtPagarReceberJsonParcelaAssinatura) consTructorDTO.newInstance(next.toString()));
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(JsonProcessAssinaturaDTO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        adicionarListas("parcelas", parcelas);
        return true;
    }

    protected boolean adicionarPropriedadObjeto(Class classeObjeto, String atributo, JsonNode node, String caminho) {
        Constructor consTructorDTO;
        try {
            consTructorDTO = classeObjeto.getConstructor(String.class);
            DTO_SBGENERICO objeto = (DTO_SBGENERICO) consTructorDTO.newInstance(node.get(caminho).toString());
            adicionarObjeto(atributo, objeto);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(JsonProcessAssinaturaDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
