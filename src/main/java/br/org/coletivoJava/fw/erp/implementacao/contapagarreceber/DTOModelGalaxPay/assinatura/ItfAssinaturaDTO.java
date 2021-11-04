/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;

import java.util.List;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfFaturaAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessAssinaturaDTO.class)
public interface ItfAssinaturaDTO extends ItfFaturaAssinatura, ItfDTOSBJSON {

    @Override
    public default List<ItfPrevisaoValorMoeda> getParcelas() {
        return (List<ItfPrevisaoValorMoeda>) getValorPorReflexao();
    }

    @Override
    public default double getValorAtualMensal() {
        return (double) getValorPorReflexao();
    }

    @Override
    public default int getDiaDoMesVencimento() {
        return (int) getValorPorReflexao();
    }

    @Override
    public default ItfPessoaFisicoJuridico getDevedor() {
        return (ItfPessoaFisicoJuridico) getValorPorReflexao();
    }

    @Override
    public default int getQtdParcelas() {
        return (int) getValorPorReflexao();
    }

    @Override
    public default boolean isAtivo() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default void setDiaDoMesVencimento(int diaDoMesPagamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default void setQtdParcelas(int qtdParcelas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
