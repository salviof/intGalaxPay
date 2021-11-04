/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.fw.erp.implementacao.contapagarreceber;

import br.org.coletivoJava.fw.api.erp.contaPagarReceber.apiCore.ERPContaPagarReceber;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.faturamento.ItfFatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobranca;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.regsitroCobranca.ItfRegistroCobrancaAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.financeiro.ItfPessoaFisicoJuridico;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.assinatura.ItfFaturaAssinatura;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.model.valormoedaFuturo.ItfPrevisaoValorMoeda;
import br.org.coletivoJava.fw.erp.implementacao.contapagarreceber.DTOModelGalaxPay.assinatura.DTOCtPagarReceberJsonAssinatura;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreInputOutputConversoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringListas;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.utils.IOUtils;

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

    }

    /**
     * Test of getRegistroAssinatura method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetRegistroAssinatura() {
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        String assinaturaTeste;
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            ///home/superBits/projetos/coletivoJava/source/integracao/intGalaxPay/src/test/resources/bola.jpg
            InputStream is = classLoader.getResourceAsStream("exemplos/galaxPay/assinatura.json");
            assinaturaTeste = IOUtils.toString(is);
            DTOCtPagarReceberJsonAssinatura assinatura = new DTOCtPagarReceberJsonAssinatura(assinaturaTeste);
            CtPagarReceberGalaxPayimpl instanciaGP = (CtPagarReceberGalaxPayimpl) ERPContaPagarReceber.GALAX_PAY.getImplementacaoDoContexto();
            ItfFaturaAssinatura result = instanciaGP.getAssinatura(assinatura);
        } catch (IOException ex) {
            Logger.getLogger(CtPagarReceberGalaxPayimplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getDevedorByCNPJ method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByCNPJ() {

    }

    /**
     * Test of getRegistrosEmAberto method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetRegistrosEmAberto() {

    }

    /**
     * Test of getAssinaturasAtivas method, of class CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetAssinaturasAtivas() {

    }

    /**
     * Test of getClientesRegistrados method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetClientesRegistrados() {

    }

    /**
     * Test of getDevedorByIdExterno method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByIdExterno() {

    }

    /**
     * Test of getDevedorByIdAplicacao method, of class
     * CtPagarReceberGalaxPayimpl.
     */
    @Test
    public void testGetDevedorByIdAplicacao() {

    }

}
