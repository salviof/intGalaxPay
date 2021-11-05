/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.parcelaSazonal;

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
public class JsonProcessParcelaSazonal extends DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarRecebeJsonCobrancaSazonal> {

    public JsonProcessParcelaSazonal() {
        super(DTOCtPagarRecebeJsonCobrancaSazonal.class);
    }

    @Override
    public DTOCtPagarRecebeJsonCobrancaSazonal deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOCtPagarRecebeJsonCobrancaSazonal dtoCobrancasazonal = new DTOCtPagarRecebeJsonCobrancaSazonal();

        getObjectBuilder().add("valor", node.get("value").asDouble());
        selarProcesamento(dtoCobrancasazonal);
        return dtoCobrancasazonal;

    }

}
