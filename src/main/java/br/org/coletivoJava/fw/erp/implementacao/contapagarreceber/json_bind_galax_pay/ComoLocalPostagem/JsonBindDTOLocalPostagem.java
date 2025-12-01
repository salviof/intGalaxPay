package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.ComoLocalPostagem;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class JsonBindDTOLocalPostagem
        extends
        DTO_SB_JSON_PROCESSADOR_GENERICO<DTOComoLocalPostagem> {

    public JsonBindDTOLocalPostagem() {
        super(DTOComoLocalPostagem.class);
    }

    @Override
    public DTOComoLocalPostagem deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        adicionarPropriedadeString("cep", node, "zipCode");

        String logadouro = node.get("street").asText();

        String numero = node.get("number").asText();
        logadouro = logadouro + " " + numero;
        getObjectBuilder().add("logradouro", logadouro);
        adicionarPropriedadeString("complemento", node, "complement");

        DTOComoLocalPostagem dto = new DTOComoLocalPostagem();
        selarProcesamento(dto);
        return dto;
    }
}
