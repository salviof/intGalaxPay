/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.erpContaPagarReceber;

import br.org.coletivoJava.fw.api.erp.codigoPostal.br.ERPCodigoPostalBR;
import br.org.coletivoJava.fw.api.erp.contaPagarReceber.apiCore.ERPContaPagarReceber;
import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.apache.logging.log4j.LogManager;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;
import org.junit.Test;
import testesFW.geradorDeCodigo.erp.GeradorERPImplementacaoContexto;

/**
 *
 * @author sfurbino
 */
public class CodigoGeradorERPPagarReceberGalaxPay {

    @Test
    public void inicio() {
        try {
            SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
            new GeradorERPImplementacaoContexto(ERPContaPagarReceber.GALAX_PAY).salvarEmDiretorioPadraCASO_NAO_EXISTA();
        } catch (Throwable t) {
            LogManager.getLogger(LogPadraoSB.class).error("Erro Criando anotações", t);
        }
    }
}
