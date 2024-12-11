package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PrevisaoValorMoeda;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import java.io.IOException;

public class JsonBindDTOPrevisaoValorMoeda
        extends
        DTO_SB_JSON_PROCESSADOR_GENERICO<DTOPrevisaoValorMoeda> {

    public JsonBindDTOPrevisaoValorMoeda() {
        super(DTOPrevisaoValorMoeda.class);
    }

    @Override
    public DTOPrevisaoValorMoeda deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOPrevisaoValorMoeda dtoCobrancasazonal = new DTOPrevisaoValorMoeda();
        adicionarPropriedadeInteiro("id", node, "galaxPayId");
        adicionarPropriedadeDouble("valor", node, "value");
        adicionarPropriedadeData("dataPrevista", node, "payday");
        adicionarPropriedadeString("nome", node, "additionalInfo");

        if (node.has("Pix")) {
            JsonNode pix = node.get("Pix");
            adicionarPropriedadeString("pix", pix, "qrCode");
        }
        if (node.has("Boleto")) {
            JsonNode boleto = node.get("Boleto");
            adicionarPropriedadeString("PDFCobranca", boleto, "pdf");
        }
        adicionarPropriedadeBoolean("pagamentoEfetuado", Lists.newArrayList("payedBoleto", "captured"), node, "status");

        selarProcesamento(dtoCobrancasazonal);
        return dtoCobrancasazonal;
    }
}
