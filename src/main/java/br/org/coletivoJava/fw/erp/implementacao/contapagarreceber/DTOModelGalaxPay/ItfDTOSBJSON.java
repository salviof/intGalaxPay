/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay;

import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.ItfAssinaturaDTO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import jakarta.json.JsonObject;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sfurbino
 */
public interface ItfDTOSBJSON {

    public JsonObject getJsonModoPojo();

    public List getLista(String pNomeAtributop);

    public default Object getValorPorReflexao() {

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();

        String nomeOriginalMetodo = stackTraceTodosMetodos[2].getMethodName();//"getParcelas";
        String nomeAtributo = nomeOriginalMetodo.replace("get", "");
        nomeAtributo = nomeAtributo.toLowerCase().charAt(0) + nomeAtributo.substring(1);
        try {
            Method metodo = this.getClass().getMethod(nomeOriginalMetodo);
            Class tipoRetorno = metodo.getReturnType();
            if (tipoRetorno.isPrimitive()) {
                if (tipoRetorno.getSimpleName().equals("int")) {
                    return getJsonModoPojo().getInt(nomeAtributo);
                }

            } else {
                if (tipoRetorno.isAssignableFrom(ItfBeanSimples.class)) {
                    System.out.println("Objeto");
                }
                if (tipoRetorno.isAssignableFrom(List.class)) {

                    return getLista(nomeAtributo);
                }

            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ItfAssinaturaDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ItfAssinaturaDTO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getJsonModoPojo().getInt(nomeAtributo);
    }
}
