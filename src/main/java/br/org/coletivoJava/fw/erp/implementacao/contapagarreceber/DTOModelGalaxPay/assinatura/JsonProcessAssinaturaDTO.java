/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SB_JSON_PROCESSADOR_GENERICO;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela.DTOCtPagarReceberJsonParcelaAssinatura;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.devedor.DTOCtPagarReceberGalaxPayDevedor;
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
public class JsonProcessAssinaturaDTO extends DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberJsonAssinatura> {

    public JsonProcessAssinaturaDTO() {
        super(DTOCtPagarReceberJsonAssinatura.class);
    }

    @Override
    public DTOCtPagarReceberJsonAssinatura deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        DTOCtPagarReceberJsonAssinatura dto = new DTOCtPagarReceberJsonAssinatura();

        adicionarPropriedadeInteiro("qtdParcelas", node, "quantity");
        adicionarPropriedadeBoolean("ativo", "active", node, "status");
        adicionarPropriedadeDouble("valorAtualMensal", node, "value");
        adicionarPropriedadeData("dataPrimeiroPagamento", node, "firstPayDayDate");
        adicionarPropriedadeListaObjetos(DTOCtPagarReceberJsonParcelaAssinatura.class, node, "Transactions");
        adicionarPropriedadObjeto(DTOCtPagarReceberGalaxPayDevedor.class, "devedor", node, "Customer");

        selarProcesamento(dto);
        return dto;
    }

}
