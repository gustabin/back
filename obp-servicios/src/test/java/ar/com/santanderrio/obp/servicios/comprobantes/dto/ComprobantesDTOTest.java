package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta7x24;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.RespuestaScomp;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ScompBOImpl;

@RunWith(MockitoJUnitRunner.class)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
public class ComprobantesDTOTest {

    @Test
    public void equalsTest() {
        List<ComprobanteDTO> comprobante1 = new ArrayList<ComprobanteDTO>();
        List<ComprobanteDTO> comprobante2 = new ArrayList<ComprobanteDTO>();
        List<String> listaLimites = new ArrayList<String>();
        listaLimites.add("limite");
        listaLimites.add("limite2");
        listaLimites.add("limite3");
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante1.add(comprobante);
        ComprobantesDTO comprobantes = new ComprobantesDTO(comprobante1, "", "", listaLimites);
        ComprobantesDTO comprobantes2 = new ComprobantesDTO(comprobante2, "", "", listaLimites);

        Assert.assertTrue(!comprobantes.equals(comprobantes2));
        Assert.assertTrue(comprobantes.equals(comprobantes));
    }

    @Test
    public void hashTest() {
        List<ComprobanteDTO> comprobante1 = new ArrayList<ComprobanteDTO>();
        List<ComprobanteDTO> comprobante2 = new ArrayList<ComprobanteDTO>();
        List<String> listaLimites = new ArrayList<String>();
        listaLimites.add("limite");
        listaLimites.add("limite2");
        listaLimites.add("limite3");
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante1.add(comprobante);
        ComprobantesDTO comprobantes = new ComprobantesDTO(comprobante1, "", "", listaLimites);
        ComprobantesDTO comprobantes2 = new ComprobantesDTO(comprobante2, "", "", listaLimites);

        Assert.assertTrue(comprobantes.hashCode() != comprobantes2.hashCode());
        Assert.assertTrue(comprobantes.hashCode() == comprobantes.hashCode());
    }

    @Test
    public void toStringTest() {
        List<ComprobanteDTO> comprobante1 = new ArrayList<ComprobanteDTO>();
        List<ComprobanteDTO> comprobante2 = new ArrayList<ComprobanteDTO>();
        List<String> listaLimites = new ArrayList<String>();
        listaLimites.add("limite");
        listaLimites.add("limite2");
        listaLimites.add("limite3");
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante1.add(comprobante);
        ComprobantesDTO comprobantes = new ComprobantesDTO(comprobante1, "", "", listaLimites);
        ComprobantesDTO comprobantes2 = new ComprobantesDTO(comprobante2, "", "", listaLimites);
        String string1 = comprobantes.toString();
        String string2 = comprobantes2.toString();
        Assert.assertTrue(!string1.equals(string2));
        Assert.assertTrue(string1.equals(string1));
    }

    /**
     * Pendiente de ajustar el hashcode y equals de los dto para que no se dupliquen
     * y ahi se puede reemplazar el criterio del test.
     * 
     * @throws JAXBException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void evitarComprobantesDuplicadosTest()
            throws JAXBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String xml = "<respuesta><comprobantes><comprobante><id>1631227</id><tipo>18</tipo><categorias/><detalle><TRFCTA><Canal>04</Canal><SubCanal>99</SubCanal><TpoComprobante>18</TpoComprobante><SubTpoComprobante>0</SubTpoComprobante><EstadoOper>A</EstadoOper><DescEstado>Aceptada</DescEstado><FechaOper>2018-04-12T00:00:00Z</FechaOper><HoraOper>10:16:53</HoraOper><Cliente><Nup>00276937</Nup><TpoDoc>N</TpoDoc><NroDoc>21587183</NroDoc><FechaNac>1970-04-20T00:00:00Z</FechaNac></Cliente><Origen><TipoCuentaOrigen>09</TipoCuentaOrigen><SucursalCuentaOrigen>033</SucursalCuentaOrigen><NumeroCuentaOrigen>361253/2</NumeroCuentaOrigen></Origen><Destino><TipoCuentaDestino>09</TipoCuentaDestino><SucursalCuentaDestino>000</SucursalCuentaDestino><NumeroCuentaDestino>060136/4</NumeroCuentaDestino></Destino><TransfDiferida>N</TransfDiferida><NroComprobante>10111685</NroComprobante><ImporteDebito>8,00</ImporteDebito><Concepto>VAR</Concepto><DescConcepto>Varios</DescConcepto><TitularCtaCredito>Amatt Taiel Luz</TitularCtaCredito><Recordatorio>Varios</Recordatorio></TRFCTA></detalle></comprobante></comprobantes></respuesta>";
        RespuestaScomp comprobateScompTrfcta = JaxbUtils.transformarXmlAObject(xml, RespuestaScomp.class);
        xml = "<respuesta><comprobantes><comprobante><id>1631175</id><tipo>2</tipo><categorias/><detalle><TRANSF-INM-RIO-RIO><Canal>04</Canal><SubCanal>99</SubCanal><TpoComprobante>2</TpoComprobante><SubTpoComprobante>9</SubTpoComprobante><EstadoOper>A</EstadoOper><DescEstado>Aceptada</DescEstado><IdMedioPago/><DescMedioPago/><FechaOper>2018/04/12T00:00:00Z</FechaOper><HoraOper>10:16:00</HoraOper><FechaGen>2018-04-12T00:00:00Z</FechaGen><Cliente><Nup>00276937</Nup><TpoDoc>N</TpoDoc><NroDoc>21587183</NroDoc></Cliente><Transferencia><Alias>Amattt</Alias><AliasCBU/><NombreDestinatario>Amatt Taiel Luz</NombreDestinatario><Importe>8,00</Importe><TipoCuentaOrigen>09</TipoCuentaOrigen><SucursalCuentaOrigen>033</SucursalCuentaOrigen><NumeroCuentaOrigen>3612532</NumeroCuentaOrigen><Banco>Santander</Banco><TipoCuentaDestino>09</TipoCuentaDestino><SucursalCuentaDestino>000</SucursalCuentaDestino><NumeroCuentaDestino>0601364</NumeroCuentaDestino><Concepto>Varios</Concepto><DescripcionConcepto>Varios</DescripcionConcepto><PlazoAcreditacion>Inmediata</PlazoAcreditacion><NroComprobante>10111685</NroComprobante></Transferencia></TRANSF-INM-RIO-RIO></detalle></comprobante></comprobantes></respuesta>";
        RespuestaScomp comprobateScompRioRio = JaxbUtils.transformarXmlAObject(xml, RespuestaScomp.class);
        ComprobanteTrfcta trfcta = (ComprobanteTrfcta) comprobateScompTrfcta.getComprobantes().get(0)
                .getComprobanteList().get(0);
        ComprobanteTransfInmRioRio rioRio = (ComprobanteTransfInmRioRio) comprobateScompRioRio.getComprobantes().get(0)
                .getComprobanteList().get(0);
        ScompBOImpl bo = new ScompBOImpl();

        ComprobanteDTO trfctaDTO = getComprobanteDTO("obtenerComprobanteDTOParaTransferenciaInmediataRioRio",
                ComprobanteTrfcta.class, bo, trfcta);
        ComprobanteDTO rioRioDTO = getComprobanteDTO("obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco",
                ComprobanteTransfInmRioRio.class, bo, rioRio);

        Set<ComprobanteDTO> comprobantes = new HashSet<ComprobanteDTO>();
        comprobantes.add(trfctaDTO);
        comprobantes.add(rioRioDTO);
        // Assert.assertEquals("Se duplican los comprobantes", 1, comprobantes.size());
        Assert.assertEquals(1, comprobantes.size());
    }
    
    @Test
    public void evitarComprobantesDuplicadosEntreRioRioInme()
            throws JAXBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String xml = "<respuesta><comprobantes><comprobante><id>130928162</id><tipo>18</tipo><categorias/><detalle><TRFCTA7X24><Canal>04</Canal><SubCanal>99</SubCanal><TpoComprobante>18</TpoComprobante><SubTpoComprobante>2</SubTpoComprobante><EstadoOper>A</EstadoOper><DescEstado>Aceptada</DescEstado><FechaOper>2018-06-19T00:00:00Z</FechaOper><HoraOper>09:34:03</HoraOper><Cliente><Nup>02810993</Nup><TpoDoc>N</TpoDoc><NroDoc>26328189</NroDoc><FechaNac>1977-11-08T00:00:00Z</FechaNac></Cliente><Origen><TipoCuentaOrigen>09</TipoCuentaOrigen><SucursalCuentaOrigen>000</SucursalCuentaOrigen><NumeroCuentaOrigen>360407/0</NumeroCuentaOrigen></Origen><Destino><TipoCuentaDestino>00</TipoCuentaDestino><SucursalCuentaDestino>476</SucursalCuentaDestino><NumeroCuentaDestino>000044/4</NumeroCuentaDestino></Destino><TransfDiferida>N</TransfDiferida><NroComprobante>9324784</NroComprobante><ImporteDebito>5.592,38</ImporteDebito><Concepto>VAR</Concepto><DescConcepto>11 B TR</DescConcepto><TitularCtaCredito/><Recordatorio>11 B TR</Recordatorio></TRFCTA7X24></detalle></comprobante></comprobantes></respuesta>";
        RespuestaScomp comprobateScompTrfcta = JaxbUtils.transformarXmlAObject(xml, RespuestaScomp.class);
        xml = "<respuesta><comprobantes><comprobante><id>130925189</id><tipo>2</tipo><categorias/><detalle><TRANSF-INM-RIO-RIO><Canal>04</Canal><SubCanal>99</SubCanal><TpoComprobante>2</TpoComprobante><SubTpoComprobante>9</SubTpoComprobante><EstadoOper>A</EstadoOper><DescEstado>Aceptada</DescEstado><IdMedioPago/><DescMedioPago/><FechaOper>2018/06/19T00:00:00Z</FechaOper><HoraOper>09:34:00</HoraOper><FechaGen>2018-06-19T00:00:00Z</FechaGen><Cliente><Nup>02810993</Nup><TpoDoc>N</TpoDoc><NroDoc>26328189</NroDoc></Cliente><Transferencia><Alias>Cons Prop Av Libertador 2861</Alias><AliasCBU/><NombreDestinatario/><Importe>5592,38</Importe><TipoCuentaOrigen>09</TipoCuentaOrigen><SucursalCuentaOrigen>000</SucursalCuentaOrigen><NumeroCuentaOrigen>3604070</NumeroCuentaOrigen><Banco>Santander</Banco><TipoCuentaDestino>00</TipoCuentaDestino><SucursalCuentaDestino>476</SucursalCuentaDestino><NumeroCuentaDestino>0000444</NumeroCuentaDestino><Concepto>Varios</Concepto><DescripcionConcepto>11 B TR</DescripcionConcepto><PlazoAcreditacion>Inmediata</PlazoAcreditacion><EmailDestinatario>burattipropiedades@fibertel.com.ar</EmailDestinatario><Mensaje>enviamos el pago correspondiente al 11 B Torre RÃ­o. Atte, Maura y Pablo</Mensaje><NroComprobante>09324784</NroComprobante></Transferencia></TRANSF-INM-RIO-RIO></detalle></comprobante></comprobantes></respuesta>";
        RespuestaScomp comprobateScompRioRio = JaxbUtils.transformarXmlAObject(xml, RespuestaScomp.class);
        ComprobanteTrfcta7x24 trfcta = (ComprobanteTrfcta7x24) comprobateScompTrfcta.getComprobantes().get(0)
                .getComprobanteList().get(0);
        ComprobanteTransfInmRioRio rioRio = (ComprobanteTransfInmRioRio) comprobateScompRioRio.getComprobantes().get(0)
                .getComprobanteList().get(0);
        ScompBOImpl bo = new ScompBOImpl();

        ComprobanteDTO trfctaDTO = getComprobanteDTO("obtenerComprobanteDTOParaTransferenciaInmediataRioRio7x24",
        		ComprobanteTrfcta7x24.class, bo, trfcta);
        ComprobanteDTO rioRioDTO = getComprobanteDTO("obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco",
                ComprobanteTransfInmRioRio.class, bo, rioRio);

        HashSet<ComprobanteDTO> comprobantes = new HashSet<ComprobanteDTO>();
        comprobantes.add(trfctaDTO);
        comprobantes.add(rioRioDTO);
        Assert.assertEquals(1, comprobantes.size());
    }

    private <T extends Comprobante> ComprobanteDTO getComprobanteDTO(String metodo, Class<T> class1, ScompBOImpl bo,
            T trfcta) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Method m = ScompBOImpl.class.getDeclaredMethod(metodo, class1);
        m.setAccessible(true);
        return (ComprobanteDTO) m.invoke(bo, trfcta);
    }

}
