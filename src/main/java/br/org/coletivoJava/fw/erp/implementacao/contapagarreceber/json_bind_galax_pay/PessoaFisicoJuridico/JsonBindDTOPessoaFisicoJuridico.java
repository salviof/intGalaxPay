package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PessoaFisicoJuridico;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class JsonBindDTOPessoaFisicoJuridico
        extends
        DTO_SB_JSON_PROCESSADOR_GENERICO<DTOPessoaFisicoJuridico> {

    public JsonBindDTOPessoaFisicoJuridico() {
        super(DTOPessoaFisicoJuridico.class);
    }

    @Override
    public DTOPessoaFisicoJuridico deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOPessoaFisicoJuridico dto = new DTOPessoaFisicoJuridico();
        adicionarPropriedadeString("cpfCnpj", node, "document");
        adicionarPropriedadeInteiro("id", node, "galaxPayId");

        selarProcesamento(dto);

        return dto;
    }
}
