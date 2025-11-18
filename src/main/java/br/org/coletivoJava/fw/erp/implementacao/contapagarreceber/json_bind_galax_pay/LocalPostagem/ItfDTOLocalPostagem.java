package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.LocalPostagem;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.LocalPostagem.JsonBindDTOLocalPostagem;
import java.lang.String;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocalPostagem;

@JsonDeserialize(using = JsonBindDTOLocalPostagem.class)
public interface ItfDTOLocalPostagem extends ItfDTOSBJSON, ComoLocalPostagem {

    @Override
    public default String getCep() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getLogradouro() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getComplemento() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default double getLatitude() {
        return (double) getValorPorReflexao();
    }

    @Override
    public default ComoBairro getBairro() {
        return (ComoBairro) getValorPorReflexao();
    }

    @Override
    public default ComoLocalPostagem getComoLocalPostavel() {
        return (ComoLocalPostagem) getValorPorReflexao();
    }

    @Override
    public default boolean isLocaPostavel() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default double getLongitude() {
        return (double) getValorPorReflexao();
    }

    @Override
    public default String getNomeUnicoSlug() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default boolean isTemImagemPequenaAdicionada() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default String getSlugIdentificador() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getNome() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getIconeDaClasse() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getNomeCurto() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default Long getId() {
        return (Long) getValorPorReflexao();
    }
}
