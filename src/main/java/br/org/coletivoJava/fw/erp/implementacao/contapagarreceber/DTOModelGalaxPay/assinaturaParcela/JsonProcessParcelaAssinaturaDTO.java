/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SB_JSON_PROCESSADOR_GENERICO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.DTOCtPagarReceberJsonAssinatura;
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
public class JsonProcessParcelaAssinaturaDTO extends DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberJsonParcelaAssinatura> {

    public JsonProcessParcelaAssinaturaDTO() {
        super(DTOCtPagarReceberJsonParcelaAssinatura.class);
    }

    @Override
    public DTOCtPagarReceberJsonParcelaAssinatura deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOCtPagarReceberJsonParcelaAssinatura dto = new DTOCtPagarReceberJsonParcelaAssinatura();

        getObjectBuilder().add("valor", node.get("value").asDouble());
        selarProcesamento(dto);

        return dto;
    }

}
