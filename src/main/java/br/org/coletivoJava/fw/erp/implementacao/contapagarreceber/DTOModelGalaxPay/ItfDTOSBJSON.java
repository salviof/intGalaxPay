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

    public ItfBeanSimples getObjeto(String pNomeAtributop);

    public default Object getValorPorReflexao() {

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();

        String nomeOriginalMetodo = stackTraceTodosMetodos[2].getMethodName();//"getParcelas";
        String nomeAtributo = nomeOriginalMetodo;
        if (nomeOriginalMetodo.startsWith("get")) {
            nomeAtributo = nomeOriginalMetodo.replaceFirst("get", "");
        }
        if (nomeOriginalMetodo.startsWith("is")) {
            nomeAtributo = nomeOriginalMetodo.replaceFirst("is", "");
        }

        nomeAtributo = nomeAtributo.toLowerCase().charAt(0) + nomeAtributo.substring(1);
        try {
            Method metodo = this.getClass().getMethod(nomeOriginalMetodo);
            Class tipoRetorno = metodo.getReturnType();
            if (tipoRetorno.isPrimitive() || tipoRetorno.getSimpleName().equals(String.class.getSimpleName())) {
                if (tipoRetorno.getSimpleName().equals("int")) {
                    return getJsonModoPojo().getInt(nomeAtributo);
                }

                if (tipoRetorno.getSimpleName().equals("boolean")) {
                    return getJsonModoPojo().getBoolean(nomeAtributo);
                }

                if (tipoRetorno.getSimpleName().equals("double")) {
                    return getJsonModoPojo().getJsonNumber(nomeAtributo).doubleValue();
                }

                if (tipoRetorno.getSimpleName().equals("String")) {
                    return getJsonModoPojo().getString(nomeAtributo);
                }

            } else {

                try {
                    tipoRetorno.getMethod("getId");
                    tipoRetorno.getMethod("getNome");
                    return getObjeto(nomeAtributo);
                } catch (NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(ItfDTOSBJSON.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (tipoRetorno.isAssignableFrom(List.class)) {

                return getLista(nomeAtributo);
            }

        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(ItfAssinaturaDTO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getJsonModoPojo()
                .getInt(nomeAtributo);
    }
}
