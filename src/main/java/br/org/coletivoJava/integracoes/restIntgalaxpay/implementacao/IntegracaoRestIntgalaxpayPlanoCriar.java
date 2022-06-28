package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.restIntgalaxpay.api.InfoIntegracaoRestIntgalaxpayPlanos;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntgalaxpayPlanos(tipo = FabApiRestIntGalaxPayPlanos.PLANO_CRIAR)
public class IntegracaoRestIntgalaxpayPlanoCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntgalaxpayPlanoCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntGalaxPayPlanos.PLANO_CRIAR, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        int codigoInterno = (int) parametros[0];
        String nome = (String) parametros[1];
        int quantidadeMeses = (int) parametros[2];
        double valor = (double) parametros[3];
        String valorFormatado = UtilSBCoreNumeros.converterNumeroToStrMoedaPadraoBanco(valor);
        String periodicidade = "monthly";

        String requisicao = "{\n"
                + "    \"myId\": \"" + codigoInterno + "\",\n"
                + "    \"name\": \"" + nome + "\",\n"
                + "    \"periodicity\": \"" + periodicidade + "\",\n"
                + "    \"quantity\": " + quantidadeMeses + ",\n"
                + "    \"PlanPrices\": [\n"
                + "        {\n"
                + "            \"payment\": \"creditcard\",\n"
                + "            \"value\": " + valorFormatado + "\n"
                + "        }\n"
                + "    ]\n"
                + "}";

        return requisicao;
    }

}
