/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public abstract class DTO_SB_JSON_PROCESSADOR_GENERICO<T> extends StdDeserializer<T> {

    private JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    private Map<String, DTO_SBGENERICO> objetosArmazenados;
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

}
