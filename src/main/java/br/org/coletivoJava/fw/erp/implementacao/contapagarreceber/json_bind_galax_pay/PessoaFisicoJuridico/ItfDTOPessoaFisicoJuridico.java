package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PessoaFisicoJuridico;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.ItfDTOSBJSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.json_bind_galax_pay.PessoaFisicoJuridico.JsonBindDTOPessoaFisicoJuridico;
import java.lang.String;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ComoPessoaFisicoJuridico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocalPostagem;

@JsonDeserialize(using = JsonBindDTOPessoaFisicoJuridico.class)
public interface ItfDTOPessoaFisicoJuridico
        extends
        ItfDTOSBJSON,
        ComoPessoaFisicoJuridico {

    @Override
    public default ComoLocalPostagem getLocalizacao() {
        return (ComoLocalPostagem) getValorPorReflexao();
    }

    @Override
    public default String getCpfCnpj() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getApelido() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getTelefone() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default boolean isAtivo() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default String getNomeLongo() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default List getGaleria() {
        return (List) getValorPorReflexao();
    }

    @Override
    public default String getDescritivo() {
        return (String) getValorPorReflexao();
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
