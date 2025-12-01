package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.ComoPessoaFisicoJuridico;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.ComoLocalPostagem.DTOComoLocalPostagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.util.Iterator;

public class JsonBindDTOPessoaFisicoJuridico
        extends
        DTO_SB_JSON_PROCESSADOR_GENERICO<DTOComoPessoaFisicoJuridico> {

    public JsonBindDTOPessoaFisicoJuridico() {
        super(DTOComoPessoaFisicoJuridico.class);
    }

    @Override
    public DTOComoPessoaFisicoJuridico deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOComoPessoaFisicoJuridico dto = new DTOComoPessoaFisicoJuridico();
        adicionarPropriedadeString("cpfCnpj", node, "document");
        adicionarPropriedadeInteiro("id", node, "galaxPayId");
        adicionarPropriedadeString("nome", node, "name");
        adicionarPropriedadeString("nomeLongo", node, "name");

        ArrayNode emails = (ArrayNode) node.get("emails");
        if (emails != null) {
            for (Iterator<JsonNode> it = emails.iterator(); it.hasNext();) {
                TextNode email = (TextNode) it.next();
                if (!email.asText().contains("casanovadigital")) {
                    getObjectBuilder().add("email", email.asText());
                }
            }
        }

        ArrayNode telefones = (ArrayNode) node.get("phones");
        if (telefones != null) {
            for (Iterator<JsonNode> it = telefones.iterator(); it.hasNext();) {
                JsonNode telefone = (JsonNode) it.next();

                getObjectBuilder().add("telefonePrincipal".toLowerCase(), telefone.asText());

            }
        }

        JsonNode endereco = node.get("Address");
        if (endereco != null) {
            adicionarObjeto("localizacao", new DTOComoLocalPostagem(endereco.toString()));
        }
        selarProcesamento(dto);

        return dto;
    }
}
