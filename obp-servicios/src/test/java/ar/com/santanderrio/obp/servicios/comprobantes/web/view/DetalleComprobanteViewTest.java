package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteViewTest {


    
//    @Test
//    public void construirDetallePagoConDeuda(){
//        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
//        detalle.setTipoPMC("PagoConDeuda");
//        String fechaString = "20/07/2017";
//        CuitDTO cuit = new CuitDTO();
//        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
//        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS);
//        detalle.setCuit(cuit);
//        Date fecha = ISBANStringUtils.formatearFecha(fechaString);
//        detalle.setFechaDePago(fecha);
//        detalle.setMoneda("$ ");
//        detalle.setImporte(new BigDecimal(2000.00));
//        List<String> leyendaFactura = new ArrayList<String>();
//        String linea1 = "test";
//        String linea2 = "test";
//        String linea3 = "test";
//        leyendaFactura.addAll(Arrays.asList(linea1, linea2, linea3));
//        detalle.setNroCuentaOrigen("100-098098/7");
//        detalle.setleyendaFactura(leyendaFactura);
//        DetalleComprobanteView detalleView = new DetalleComprobanteView(detalle);
//        Assert.assertNotNull(detalleView);
//        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
//        }
//    
//    @Test
//    public void construirDetallePagoSinDeuda(){
//        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
//        detalle.setTipoPMC("PagoSinDeuda");
//        String fechaString = "20/07/2017";
//        CuitDTO cuit = new CuitDTO();
//        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
//        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS);
//        detalle.setCuit(cuit);
//        Date fecha = ISBANStringUtils.formatearFecha(fechaString);
//        detalle.setFechaDePago(fecha);
//        detalle.setMoneda("$ ");
//        detalle.setImporte(new BigDecimal(2000.00));
//        List<String> leyendaFactura = new ArrayList<String>();
//        String linea1 = "test";
//        String linea2 = "test";
//        String linea3 = "test";
//        leyendaFactura.addAll(Arrays.asList(linea1, linea2, linea3));
//        detalle.setNroCuentaOrigen("100-098098/7");
//        detalle.setleyendaFactura(leyendaFactura);
//        DetalleComprobanteView detalleView = new DetalleComprobanteView(detalle);
//        Assert.assertNotNull(detalleView);
//        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
//        }
//
//    @Test
//    public void construirDetallePagoAfip(){
//        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
//        detalle.setTipoPMC("PagoAFIP");
//        String fechaString = "20/07/2017";
//        CuitDTO cuit = new CuitDTO();
//        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
//        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS);
//        detalle.setCuit(cuit);
//        Date fecha = ISBANStringUtils.formatearFecha(fechaString);
//        detalle.setFechaDePago(fecha);
//        detalle.setMoneda("$ ");
//        detalle.setImporte(new BigDecimal(2000.00));
//        List<String> leyendaFactura = new ArrayList<String>();
//        String linea1 = "test";
//        String linea2 = "test";
//        String linea3 = "test";
//        leyendaFactura.addAll(Arrays.asList(linea1, linea2, linea3));
//        detalle.setNroCuentaOrigen("100-098098/7");
//        detalle.setleyendaFactura(leyendaFactura);
//        DetalleComprobanteView detalleView = new DetalleComprobanteView(detalle);
//        Assert.assertNotNull(detalleView);
//        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
//        }
//    
//    @Test
//    public void construirDetallePagoVEP(){
//        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
//        detalle.setTipoPMC("PagoVEP");
//        String fechaString = "20/07/2017";
//        CuitDTO cuit = new CuitDTO();
//        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
//        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS);
//        detalle.setCuit(cuit);
//        Date fecha = ISBANStringUtils.formatearFecha(fechaString);
//        detalle.setFechaDePago(fecha);
//        detalle.setMoneda("$ ");
//        detalle.setImporte(new BigDecimal(2000.00));
//        List<String> leyendaFactura = new ArrayList<String>();
//        String linea1 = "test";
//        String linea2 = "test";
//        String linea3 = "test";
//        leyendaFactura.addAll(Arrays.asList(linea1, linea2, linea3));
//        detalle.setNroCuentaOrigen("100-098098/7");
//        detalle.setleyendaFactura(leyendaFactura);
//        DetalleComprobanteView detalleView = new DetalleComprobanteView(detalle);
//        Assert.assertNotNull(detalleView);
//        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
//        }
//    
//    @Test
//    public void construirDetallePagoAutonomo(){
//        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
//        detalle.setTipoPMC("autonomo");
//        String fechaString = "20/07/2017";
//        CuitDTO cuit = new CuitDTO();
//        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
//        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS);
//        detalle.setCuit(cuit);
//        Date fecha = ISBANStringUtils.formatearFecha(fechaString);
//        detalle.setFechaDePago(fecha);
//        detalle.setMoneda("$ ");
//        detalle.setImporte(new BigDecimal(2000.00));
//        List<String> leyendaFactura = new ArrayList<String>();
//        String linea1 = "test";
//        String linea2 = "test";
//        String linea3 = "test";
//        leyendaFactura.addAll(Arrays.asList(linea1, linea2, linea3));
//        detalle.setNroCuentaOrigen("100-098098/7");
//        detalle.setleyendaFactura(leyendaFactura);
//        DetalleComprobanteView detalleView = new DetalleComprobanteView(detalle);
//        Assert.assertNotNull(detalleView);
//        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
//        }
        
}
