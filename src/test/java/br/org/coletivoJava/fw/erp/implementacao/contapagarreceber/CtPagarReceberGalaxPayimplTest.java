/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.apiCore.ERPContaPagarReceber;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.faturamento.ItfFatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobranca;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobrancaAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfValorMoedaFuturo;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class CtPagarReceberGalaxPayimplTest {

    /**
     * Test of getRegistroCobranca method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetRegistroCobranca() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        System.out.println("getRegistroCobranca");
        ItfValorMoedaFuturo itfValorMoedaFuturo = null;
        CtPagarReceberGalaxPayimpl instanciaGP = (CtPagarReceberGalaxPayimpl) ERPContaPagarReceber.GALAX_PAY.getImplementacaoDoContexto();

        ItfAssinatura result = instanciaGP.getAssinatura(null);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistroAssinatura method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetRegistroAssinatura() {
        System.out.println("getRegistroAssinatura");
        ItfFatura itfFatura = null;
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        ItfRegistroCobrancaAssinatura expResult = null;
        //ItfRegistroCobrancaAssinatura result = instance.getRegistroAssinatura();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDevedorByCNPJ method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByCNPJ() {
        System.out.println("getDevedorByCNPJ");
        String s = "";
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        // List result = instance.getDevedorByCNPJ(s);
        //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistrosEmAberto method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetRegistrosEmAberto() {
        System.out.println("getRegistrosEmAberto");
        ItfPessoaFisicoJuridico itfPessoaFisicoJuridico = null;
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        //    List result = instance.getRegistrosEmAberto(itfPessoaFisicoJuridico);
        //     assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssinaturasAtivas method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetAssinaturasAtivas() {
        System.out.println("getAssinaturasAtivas");
        ItfPessoaFisicoJuridico itfPessoaFisicoJuridico = null;
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        List result = instance.getAssinaturasAtivas(itfPessoaFisicoJuridico);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientesRegistrados method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetClientesRegistrados() {
        System.out.println("getClientesRegistrados");
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        //     List result = instance.getClientesRegistrados();
        //     assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDevedorByIdExterno method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByIdExterno() {
        System.out.println("getDevedorByIdExterno");
        String s = "";
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        List result = instance.getDevedorByIdExterno(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDevedorByIdAplicacao method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByIdAplicacao() {
        System.out.println("getDevedorByIdAplicacao");
        int i = 0;
        CtPagarReceberGalaxPayimpl instance = new CtPagarReceberGalaxPayimpl();
        List expResult = null;
        List result = instance.getDevedorByIdAplicacao(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
