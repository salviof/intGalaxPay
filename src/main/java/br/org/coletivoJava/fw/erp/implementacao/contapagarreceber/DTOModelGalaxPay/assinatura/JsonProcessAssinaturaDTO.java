/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SBGENERICO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SB_JSON_PROCESSADOR_GENERICO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela.DTOCtPagarReceberJsonParcelaAssinatura;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import groovy.json.JsonBuilder;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class JsonProcessAssinaturaDTO extends DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberJsonAssinatura> {

    public JsonProcessAssinaturaDTO() {
        super(DTOCtPagarReceberJsonAssinatura.class);
    }

    @Override
    public DTOCtPagarReceberJsonAssinatura deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOCtPagarReceberJsonAssinatura dto = new DTOCtPagarReceberJsonAssinatura();
        // try catch block

        getObjectBuilder().add("qtdParcelas", node.get("quantity").asInt());

        JsonNode parcelasJson = node.get("Transactions");
        List<DTOCtPagarReceberJsonParcelaAssinatura> parcelas = new ArrayList();

        for (Iterator<JsonNode> iterator = parcelasJson.elements(); iterator.hasNext();) {
            JsonNode next = iterator.next();
            parcelas.add(new DTOCtPagarReceberJsonParcelaAssinatura(next.toString()));
        }
        adicionarListas("parcelas", parcelas);

        selarProcesamento(dto);
        return dto;
    }

}
