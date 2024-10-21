package ar.com.santanderrio.obp.servicios.transferencias;

import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import org.junit.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TransferenciaUtilTest {


    private final String ip = "192.168.10.1";
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String CREATED_AT_MAIL = "2023-04-01";
    private final String CURRENT_DATE_MAIL = "2023-04-19";

    @Test
    public void getIpSinPuntosYFormatoFijoTestOk(){
        String ipProcesada= TransferenciaUtil.getIpSinPuntosYFormatoFijo(ip);
        assertEquals("192168010001", ipProcesada);
    }

    @Test
    public void getIpSinPuntosYFormatoFijoTestFail(){
        String ipProcesada= TransferenciaUtil.getIpSinPuntosYFormatoFijo(ip);
        assertNotEquals("192168101", ipProcesada);
    }

    @Test
    public void getIpSinPuntosYFormatoFijoOchoDigitosTestOk(){
        final String ip = "192.168.1.1";
        String ipProcesada= TransferenciaUtil.getIpSinPuntosYFormatoFijo(ip);
        assertEquals("192168001001", ipProcesada);
    }

    @Test
    public void getIpSinPuntosYFormatoFijoDoceDigitosTestOk(){
        final String ip = "192.168.102.200";
        String ipProcesada= TransferenciaUtil.getIpSinPuntosYFormatoFijo(ip);
        assertEquals("192168102200", ipProcesada);
    }

    @Test
    public void obtenerCantDiasUltimoCambioMailConUpdateAtOk() throws ParseException {

        DateFormat formateador = new SimpleDateFormat(DATE_FORMAT);

        Date createdAt = formateador.parse(CREATED_AT_MAIL);
        Date updateAt = formateador.parse("2023-04-10");
        Date currentDate = formateador.parse(CURRENT_DATE_MAIL);

       long result = TransferenciaUtil.obtenerCantDiasUltimoCambioMail(createdAt, updateAt, currentDate);

       assertEquals(9, result);

    }

    @Test
    public void obtenerCantDiasUltimoCambioMailConCreatedAtAOk() throws ParseException {

        DateFormat formateador = new SimpleDateFormat(DATE_FORMAT);

        Date createdAt = formateador.parse(CREATED_AT_MAIL);
        Date updateAt = formateador.parse("2023-04-01");
        Date currentDate = formateador.parse(CURRENT_DATE_MAIL);

        long result = TransferenciaUtil.obtenerCantDiasUltimoCambioMail(createdAt, updateAt, currentDate);

        assertEquals(18, result);

    }

    @Test
    public void obtenerCantDiasUltimoCambioMailConUpdateAtNullAOk() throws ParseException {

        DateFormat formateador = new SimpleDateFormat(DATE_FORMAT);

        Date createdAt = formateador.parse(CREATED_AT_MAIL);
        Date currentDate = formateador.parse(CURRENT_DATE_MAIL);

        long result = TransferenciaUtil.obtenerCantDiasUltimoCambioMail(createdAt, null, currentDate);

        assertEquals(18, result);

    }

    @Test
    public void convertirFormatoFechaOk(){
        String fechaFormateada= TransferenciaUtil.convertirFormatoFecha("08/05/2024");
        assertEquals("2024-05-08", fechaFormateada);
    }

    @Test
    public void whenObtenerContratoAltair(){
        String nroCuenta = "175-407350/0";
        String tipoCuenta = "CUP";

        String contratoAltair = TransferenciaUtil.obtenerContratoAltair(nroCuenta,tipoCuenta);
        assertEquals("0175007004073500", contratoAltair);
    }

}
