/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restIntgalaxpay.implementacao;

import br.org.coletivoJava.integracoes.intGalaxPay.api.ConfiguradorCoreApiGalaxPay;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayAssinatura;
import br.org.coletivoJava.integracoes.intGalaxPay.api.FabApiRestIntGalaxPayCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import jakarta.json.JsonValue;
import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestIntgalaxpayListarClientesTest {

    public IntegracaoRestIntgalaxpayListarClientesTest() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfiguradorCoreApiGalaxPay(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        testePoke(new String[]{"api.sandbox.cloud.galaxpay.com.br", "443"});
        FabApiRestIntGalaxPayAssinatura.ASSINATURAS_DE_CLIENTES.getGestaoToken().getComoTokenDinamico();
        System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
        FabApiRestIntGalaxPayCliente.CLIENTE_LISTAR.getGestaoToken().gerarNovoToken();

        ItfAcaoApiRest acao = FabApiRestIntGalaxPayCliente.CLIENTE_LISTAR.getAcao(0, 100);
        ItfRespostaWebServiceSimples resposta = acao.getResposta();
        assertTrue("Falha de comuniucação com a api", resposta.isSucesso());
        if (resposta.isSucesso()) {
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta.getRespostaComoObjetoJson()));
        }
    }

    public static void testePoke(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: " + IntegracaoRestIntgalaxpayListarClientesTest.class.getName() + " <host> <port>");
            System.exit(1);
        }
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(args[0], Integer.parseInt(args[1]));

            InputStream in = sslsocket.getInputStream();
            OutputStream out = sslsocket.getOutputStream();

            // Write a test byte to get a reaction :)
            out.write(1);

            while (in.available() > 0) {
                System.out.print(in.read());
            }
            System.out.println("Successfully connected");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
