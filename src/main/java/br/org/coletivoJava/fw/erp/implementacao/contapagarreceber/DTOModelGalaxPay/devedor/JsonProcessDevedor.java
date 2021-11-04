/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.devedor;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SB_JSON_PROCESSADOR_GENERICO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela.DTOCtPagarReceberJsonParcelaAssinatura;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 *
 * @author sfurbino
 */
public class JsonProcessDevedor extends DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberGalaxPayDevedor> {

    public JsonProcessDevedor() {
        super(DTOCtPagarReceberGalaxPayDevedor.class);
    }

    @Override
    public DTOCtPagarReceberGalaxPayDevedor deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOCtPagarReceberGalaxPayDevedor dto = new DTOCtPagarReceberGalaxPayDevedor();

        getObjectBuilder().add("cpfCnpj", node.get("document").asText());

        getObjectBuilder().add("id", node.get("galaxPayId").asInt());

        selarProcesamento(dto);

        return dto;
    }

}
