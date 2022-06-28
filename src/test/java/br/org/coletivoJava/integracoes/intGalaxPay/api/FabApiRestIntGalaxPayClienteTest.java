/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.intGalaxPay.api;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author salvio
 */
public class FabApiRestIntGalaxPayClienteTest extends TestesApiRest {

    public FabApiRestIntGalaxPayClienteTest() {
    }

    /**
     * Test of valueOf method, of class FabApiRestIntGalaxPayCliente.
     */
    @Test
    public void testValueOf() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gerarCodigosChamadasEndpoint(FabApiRestIntGalaxPayCliente.class);
    }

}
