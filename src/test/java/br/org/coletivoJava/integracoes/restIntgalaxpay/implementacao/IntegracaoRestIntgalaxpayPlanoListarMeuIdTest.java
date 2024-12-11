/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayPlanos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.JsonObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntgalaxpayPlanoListarMeuIdTest {

    public IntegracaoRestIntgalaxpayPlanoListarMeuIdTest() {
    }

    @Test
    public void testExecutarAcao() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        //
        ItfRespostaWebServiceSimples resposta = FabApiRestIntGalaxPayPlanos.PLANO_LISTAR_MEU_ID.getAcao("1385700190").getResposta();

        assertTrue("Falhou ao obter a lista de planos", resposta.isSucesso());
        System.out.println(resposta.getRetorno());
        JsonObject respJson = resposta.getRespostaComoObjetoJson();
        System.out.println(respJson);
        int encontrados = respJson.getInt("totalQtdFoundInPage");
        assertTrue("Plano não encontrado", encontrados > 0);

    }

}
