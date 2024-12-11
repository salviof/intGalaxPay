/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.intGalaxPay.api;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author salvio
 */
public class FabApiRestIntGalaxPayTransacaoTest extends TestesApiRest {

    public FabApiRestIntGalaxPayTransacaoTest() {
    }

    /**
     * Test of values method, of class FabApiRestIntGalaxPayTransacao.
     */
    @Test
    public void testValues() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gerarCodigosChamadasEndpoint(FabApiRestIntGalaxPayTransacao.class);
    }

}
