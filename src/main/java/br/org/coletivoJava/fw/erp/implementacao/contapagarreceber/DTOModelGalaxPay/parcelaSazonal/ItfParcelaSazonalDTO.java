/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.parcelaSazonal;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
import java.util.List;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessParcelaSazonal.class)
public interface ItfParcelaSazonalDTO extends ItfPrevisaoValorMoeda, ItfDTOSBJSON {

    @Override
    public default double getValor() {
        return (double) getValorPorReflexao();
    }
}
