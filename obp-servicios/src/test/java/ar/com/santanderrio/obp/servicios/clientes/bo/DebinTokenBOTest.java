/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debin.bo.DebinTokenBOImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DebinTokenBOTest {
    @InjectMocks
    private DebinTokenBOImpl debinTokenBOImpl;

    @Mock
    private NUPDAO nupDAO;
    @Mock
    private Sign sign;
    @Mock
    private MessageContext mc;
    @Mock
    private HttpServletRequest request;
    @Spy
    private RespuestaFactory respuestaFactory;
    @Mock
    private SesionCliente sesionCliente;

    @Test
    public void obtenerDatosAFirmar() throws DAOException, IllegalAccessException {
        String firma = "-----BEGIN PKCS7-----\nMIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCA\nJIAEggEbeyJ0aXBvRG9jdW1lbnRvIjoiTiIsIm5yb0RvY3VtZW50byI6IjAwMDEz\nMjM4ODYxIiwiY3VpdCI6IjIwMTMyMzg4NjEwIiwibnJvVGFyamV0YSI6IjAwMDA0\nNTE3NjYwMDIxNzc4ODIzIiwiY2FuYWwiOiJJIiwiaXAiOiIxMC4xMS4xMi4xMyIs\nInRlcm1pbmFsIjoiTW96aWxsYS81LjAgKFdpbmRvd3MgTlQgNi4xKSBBcHBsZVdl\nYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvNTguMC4zMDI5\nLjExMCBTYWZhcmkvNTM3LjM2IiwiZG9ibGVGYWN0b3JWZXJpZmljYWRvIjp0cnVl\nfQAAAAAAAKCAMIIEoDCCBAmgAwIBAgICAPowDQYJKoZIhvcNAQEFBQAwgbAxCzAJ\nBgNVBAYTAkFSMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxGDAWBgNVBAcTD0NhcGl0\nYWwgRmVkZXJhbDEVMBMGA1UEChMMQmFuZWxjbyBTLkEuMR4wHAYDVQQLExVTZWd1\ncmlkYWQgSW5mb3JtYXRpY2ExOTA3BgNVBAMTMENBIGRlIENlcnRpZmljYWNpb24g\nLSBCYW5lbGNvIFMuQS4gKGMpIDIwMDQtMjAwOTAeFw0xMTA3MjUyMDI0MjVaFw0y\nMTA3MjIyMDI0MjVaMIGbMQswCQYDVQQGEwJBUjEVMBMGA1UECBMMQnVlbm9zIEFp\ncmVzMRgwFgYDVQQHEw9DYXBpdGFsIEZlZGVyYWwxFjAUBgNVBAoTDUNlcnRpZmlj\nYWNpb24xHjAcBgNVBAsTFVNlZ3VyaWRhZCBJbmZvcm1hdGljYTEjMCEGA1UEAxMa\nQmFuY28gUmlvIGRlIGxhIFBsYXRhIFMuQS4wggEiMA0GCSqGSIb3DQEBAQUAA4IB\nDwAwggEKAoIBAQDFmJmPFuppf3sly13ulbh86GMPxZUUPln1U5JIJlv1JC/pwkUK\nzd1Z29+/kGkSqHhJKyldbm0XkaW2swqXVGFOzBcnoedahgRbx6OEmtsV/OEx1sJd\nqmEjGTdf4EFWBuCVTsBN83mjXIHQqyvVmm61nkkk47X91MYn2Y2dp4PXHPLgBQU/\neKy1kFaSLNa82Gpr8rHO+JWOhCWw06gfV6ASfKix80aM+ZZpXJXRmgxhSjzPK060\n7Gp4id6AgHfmE9lnUCCVBLXpBszNdq78v3Ngo73vQIWJnVt1wxutuYdn/42YYQdZ\nDEBELFAdZ3eea1CvdYMLIMtZVo3KI1UGDOljAgMBAAGjggFWMIIBUjAJBgNVHRME\nAjAAMBEGCWCGSAGG+EIBAQQEAwIEsDAzBglghkgBhvhCAQ0EJhYkQ2VydGlmaWNh\nZG8gZW1pdGlkbyBwb3IgQmFuZWxjbyBTLkEuMB0GA1UdDgQWBBRve6W0oHXsybFf\niSo75cWi2+q10jCB3QYDVR0jBIHVMIHSgBRho6r9ffcnlmmRZJZ71eN5ks8KK6GB\ntqSBszCBsDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYG\nA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRUwEwYDVQQKEwxCYW5lbGNvIFMuQS4xHjAc\nBgNVBAsTFVNlZ3VyaWRhZCBJbmZvcm1hdGljYTE5MDcGA1UEAxMwQ0EgZGUgQ2Vy\ndGlmaWNhY2lvbiAtIEJhbmVsY28gUy5BLiAoYykgMjAwNC0yMDA5ggEAMA0GCSqG\nSIb3DQEBBQUAA4GBAC+JoJcnAq4f+maaQrOAUVG5NSiyJ4jGc0NfgdtfX7uIq3EN\nGo4xlpkchxk+OKg0tEmMcEnELZ1qxEFShX2lH9kgKVDcs+/iMJHqkqB/Oi//3k6Q\nPq6Tlut0F+YvYwrG9iEe1d8a/5dFGyIpxkmy6tCJbqkdPpRXR4lle6ZnO1vqAAAx\nggJqMIICZgIBATCBtzCBsDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBB\naXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRUwEwYDVQQKEwxCYW5lbGNv\nIFMuQS4xHjAcBgNVBAsTFVNlZ3VyaWRhZCBJbmZvcm1hdGljYTE5MDcGA1UEAxMw\nQ0EgZGUgQ2VydGlmaWNhY2lvbiAtIEJhbmVsY28gUy5BLiAoYykgMjAwNC0yMDA5\nAgIA+jAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJ\nKoZIhvcNAQkFMQ8XDTE3MTIyODIwMjAyOFowIwYJKoZIhvcNAQkEMRYEFKiCPxwV\nemdDuv1C6wWCkUsP7ghcMCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkq\nhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQBy8Pvfmb7mxkloRmERaStdlD+J\nqWtFxsbdOHYucgDdYszcNlMgR21LR8aetPiLonO+tBLneNOyH1H3NT25e761grSz\nErHQhr/HBIVysEuhUl9KURIUSpcRhY1WgcLRLP7Vu7h19IZFeCieEXz//y73g5QQ\nYiMff7vlVHxrLZg4NGhUvYQ3/U7F0QwmQvvxJv8Q6HbdZWD+J882S6G8zp13XBrf\nrS/CIcr/0oOd9gvcGtJSkfA3sS2AJNIeMal4I5x4iDRgpxI2OaH1s7s9MOKXV9us\nknxwmFJxHZKSYThcKcgmehEZHF6QnYBD0EJjfTmnOeYqpfBgE7EnNo+xnvviAAAA\nAAAA\n-----END PKCS7-----\n";
        Cliente cliente = new Cliente();
        cliente.setDni("00013238861");
        cliente.setTipoDocumento("N");

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNroTarjetaCredito("00004517660021778823");
        cuenta1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuentas.add(cuenta1);
        cliente.setCuentas(cuentas);

    
        cliente.setNup("123456");
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        
        NupDTO nupDTO = new NupDTO();
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento(NupDTO.TIPO_DOC_CUIL);
        detalleDocumentoDTO.setNroDocumento("20132388610");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(sign.buildPemSignature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean()))
                .thenReturn(firma);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("10.11.12.13");
        Mockito.when(request.getHeader("User-Agent")).thenReturn(
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        FieldUtils.writeDeclaredField(debinTokenBOImpl, "debinUrl", "https://debinweb.prismamp.com/debin-web/login.do",
                true);
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
        Respuesta<TokenDTO> respuestaTokenDebin = debinTokenBOImpl.armarToken(mc, tokenExternoDTO);
        Assert.assertNotNull(respuestaTokenDebin);
        Assert.assertEquals("No coincide la firma esperada con la recibida.", firma,
                respuestaTokenDebin.getRespuesta().getTokenFirmado());
    }
}
