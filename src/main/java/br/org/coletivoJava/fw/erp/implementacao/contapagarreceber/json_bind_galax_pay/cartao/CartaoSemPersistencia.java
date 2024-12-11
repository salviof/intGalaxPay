/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.cartao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Cartões", plural = "Cartões de crédito", icone = "fa fa-credit-card")
public class CartaoSemPersistencia {

    private String nomeCartao;
    private String numeroCartao;
    private String vencimento;

    private String CVV;

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVC) {
        this.CVV = CVC;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

}
