package ar.com.santanderrio.obp.servicios.comun.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormatCrUtilTest {

    
    @InjectMocks
    private FormatCrUtil formatCrUtil = new FormatCrUtil();
    
    @Test
    public void criptoEncDniTest(){
        String tcstring = "12345678";
        String formateo = formatCrUtil.formatDNI(tcstring);
        String respuesta = formatCrUtil.criptoEnc(formateo);
        Assert.assertEquals(respuesta , "330A330A330A349A271A102A239A297A282A200A57A");
    }
    
    @Test
    public void criptoEncPinTest(){
        String tcstring = "1122";
        String formateo = formatCrUtil.formatPIN(tcstring);
        String respuesta = formatCrUtil.criptoEnc(formateo);
        Assert.assertEquals(respuesta , "330A330A330A330A349A349A271A271A");
        
    }
    
    @Test
    public void criptoEncUsrTest(){
        String tcstring = "santanderuser";
        String formateo = formatCrUtil.formatUSR(tcstring);
        String respuesta = formatCrUtil.criptoEnc(formateo);
        Assert.assertEquals(respuesta , "276A79A36A24A79A36A213A16A45A77A276A16A45A315A315A315A315A315A315A315A");
        
    }
    
    @Test
    public void formatearPesosDosDecimalesTest() {
    	double monto = 1234.68d;
    	String resultado = FormatCrUtil.formatearPesosDosDecimales(monto);
    	System.out.println(resultado);
    	Assert.assertEquals(resultado,"1.234,68$");
    }
    
    
}
