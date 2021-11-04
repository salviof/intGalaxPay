/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinaturaParcela;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessParcelaAssinaturaDTO.class)
public interface ItfParcelaAssinaturaDTO extends ItfPrevisaoValorMoeda, ItfDTOSBJSON {

    @Override
    public default double getValor() {
        return (double) getValorPorReflexao();
    }

}
