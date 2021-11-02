/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfValorMoedaFuturo;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessAssinaturaDTO.class)
public interface ItfAssinaturaDTO extends ItfAssinatura, ItfDTOSBJSON {

    @Override
    public default List<ItfValorMoedaFuturo> getParcelas() {
        return (List<ItfValorMoedaFuturo>) getValorPorReflexao();
    }

    @Override
    public default int getQtdParcelas() {
        return (int) getValorPorReflexao();
    }

    @Override
    public default void setQtdParcelas(int pParcela) {

    }

}
