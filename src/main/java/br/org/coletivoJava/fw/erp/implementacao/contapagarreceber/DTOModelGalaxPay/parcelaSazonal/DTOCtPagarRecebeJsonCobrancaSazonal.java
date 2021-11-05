/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.parcelaSazonal;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SBGENERICO;
import java.util.Date;

/**
 *
 * @author sfurbino
 */
public class DTOCtPagarRecebeJsonCobrancaSazonal extends DTO_SBGENERICO<ItfParcelaSazonalDTO> implements ItfParcelaSazonalDTO {

    public DTOCtPagarRecebeJsonCobrancaSazonal(String pJson) {
        super(JsonProcessParcelaSazonal.class, pJson);
    }

    public DTOCtPagarRecebeJsonCobrancaSazonal() {
        super(null, null);
    }

    @Override
    public double getValorRealizado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValor(double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDataPrevista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDataPrevista(Date pDataPrevista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPagamentoEfetuado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmValorPersonalizado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getParcelaNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParcelaNumero(double parcelaNumero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
