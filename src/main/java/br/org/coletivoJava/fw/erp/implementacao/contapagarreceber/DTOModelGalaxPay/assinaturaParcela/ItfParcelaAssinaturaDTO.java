/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfValorMoedaFuturo;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.json.JsonObject;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessParcelaAssinaturaDTO.class)
public interface ItfParcelaAssinaturaDTO extends ItfValorMoedaFuturo, ItfDTOSBJSON {

    @Override
    public default double getValor() {
        return getJsonModoPojo().getJsonNumber("valor").doubleValue();
    }

    public JsonObject getJsonModoPojo();

}
