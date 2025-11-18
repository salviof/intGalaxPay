/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author salvio
 */
public class ComparadorDataPrevisaParcelas implements Comparator<ItfPrevisaoValorMoeda> {

    @Override
    public int compare(ItfPrevisaoValorMoeda o1, ItfPrevisaoValorMoeda o2) {
        Date data1 = (Date) o1.getDataPrevista();
        Date data2 = (Date) o2.getDataPrevista();
        if (data1.getTime() > data2.getTime()) {
            //Atenção em caso de ordem reversa os valores das variaveis valorPrimeioroMaior e Menor já foram invertidos
            return 1;

        }
        if (data1.getTime() < data2.getTime()) {
            //Atenção em caso de ordem reversa os valores das variaveis valorPrimeioroMaior e Menor já foram invertidos
            return -1;

        }
        return 0;
    }

}
