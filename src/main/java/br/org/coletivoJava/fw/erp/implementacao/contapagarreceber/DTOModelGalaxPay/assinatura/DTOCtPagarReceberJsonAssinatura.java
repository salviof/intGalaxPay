/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.formaPagamento.ItfFormaPagamento;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfValorMoedaFuturo;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.DTO_SBGENERICO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.json.JsonObject;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class DTOCtPagarReceberJsonAssinatura extends DTO_SBGENERICO<ItfAssinaturaDTO> implements ItfAssinaturaDTO {

    public DTOCtPagarReceberJsonAssinatura(String pJosonGalaxPay) {
        super(JsonProcessAssinaturaDTO.class, pJosonGalaxPay);
    }

    public DTOCtPagarReceberJsonAssinatura() {
        super(null, null);
    }

    @Override
    public double getValorTotal() {
        return getObjetoDeserializado().getValorTotal();
    }

    @Override
    public ItfFormaPagamento getFormaPagamento() {
        return getObjetoDeserializado().getFormaPagamento();
    }

    @Override
    public int getDiaDoMesPagamento() {
        return getObjetoDeserializado().getDiaDoMesPagamento();
    }

    @Override
    public Date getDataPrimeiroPagamento() {
        return getObjetoDeserializado().getDataPrimeiroPagamento();
    }

    @Override
    public void setDataPrimeiroPagamento(Date dataPrimeiroPagamento) {

    }

    @Override
    public void setDiaDoMesPagamento(int diaDoMesPagamento) {

    }

    @Override
    public void setFormaPagamento(ItfFormaPagamento formaPagamento) {

    }

    @Override
    public void setParcelas(List<ItfValorMoedaFuturo> parcelas) {

    }

    @Override
    public void setTipoFatura(String tipoFatura) {

    }

    @Override
    public void setValorTotal(double valorTotal) {

    }

}