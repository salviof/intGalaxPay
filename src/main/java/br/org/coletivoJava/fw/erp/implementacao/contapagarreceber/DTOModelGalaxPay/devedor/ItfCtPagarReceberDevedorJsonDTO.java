/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.devedor;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;
import java.util.List;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = JsonProcessDevedor.class)
public interface ItfCtPagarReceberDevedorJsonDTO extends ItfPessoaFisicoJuridico, ItfDTOSBJSON {

    @Override
    public default ItfLocalPostagem getLocalizacao() {
        return (ItfLocalPostagem) getValorPorReflexao();
    }

    @Override
    public default int getId() {
        return (int) getValorPorReflexao();
    }

    @Override
    public default String getCpfCnpj() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getTelefone() {

        return (String) getValorPorReflexao();
    }

    @Override
    public default String getApelido() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default boolean isAtivo() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default List<String> getGaleria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public default String getDescritivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default void setLocalizacao(ItfLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default void setAtivo(boolean pAtivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default void setDescritivo(String pDescritivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
