/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import jakarta.json.JsonObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public class DTO_SBGENERICO<T extends ItfDTOSBJSON> extends ItemSimples implements ItfDTOSBJSON {

    private Map<String, DTO_SBGENERICO> objetosArmazenados;
    private Map<String, List<DTO_SBGENERICO>> listasArmazenadas;
    private JsonObject atributosObjetoJson;
    private boolean modoPojo;
    private final T dtoDecoratorGettersInstanciado;

    public DTO_SBGENERICO(Class<? extends DTO_SB_JSON_PROCESSADOR_GENERICO> pclasseProcessador, String pJson) {
        if (pclasseProcessador == null) {
            modoPojo = true;
            dtoDecoratorGettersInstanciado = null;
        } else {
            modoPojo = false;

            try {
                dtoDecoratorGettersInstanciado = new ObjectMapper().readValue(pJson, (Class<T>) this.getClass());

                ObjectMapper mapper = new ObjectMapper();

                SimpleModule mod = new SimpleModule(pclasseProcessador.getSimpleName());

                try {
                    //   mod.addDeserializer(this.getClass(), pclasseProcessador.newInstance());
                    //    mapper.registerModule(mod);
                    //    dtoDecoratorGettersInstanciado = mapper.readValue(pJson, (Class<T>) this.getClass());
                    return;

                } catch (Throwable t) {
                    System.out.println("UP");
                }
            } catch (Throwable t) {
                System.out.println("UP");
            }
            throw new UnsupportedOperationException("Falha iniciando DTO");
        }

    }

    protected void setDadosDoObjeto() {

    }

    @Override
    public JsonObject getJsonModoPojo() {
        if (!modoPojo) {
            return dtoDecoratorGettersInstanciado.getJsonModoPojo();
        }
        return atributosObjetoJson;
    }

    public T getObjetoDeserializado() {
        return dtoDecoratorGettersInstanciado;
    }

    @Override
    public List getLista(String pNomeAtributop) {
        if (!modoPojo) {
            return dtoDecoratorGettersInstanciado.getLista(pNomeAtributop);
        }
        return listasArmazenadas.get(pNomeAtributop);
    }

    @Override
    public ItfBeanSimples getObjeto(String pNomeAtributop) {
        if (!modoPojo) {
            return dtoDecoratorGettersInstanciado.getObjeto(pNomeAtributop);
        }
        return objetosArmazenados.get(pNomeAtributop);
    }

    protected void setDadosDoObjeto(JsonObject pJson, Map<String, DTO_SBGENERICO> pObjetosArmazenados, Map<String, List<DTO_SBGENERICO>> pListasArmazenadas) {
        objetosArmazenados = pObjetosArmazenados;
        atributosObjetoJson = pJson;
        listasArmazenadas = pListasArmazenadas;
    }
}
