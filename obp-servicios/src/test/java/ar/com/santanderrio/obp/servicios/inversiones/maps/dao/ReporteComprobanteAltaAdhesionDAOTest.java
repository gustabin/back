package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.ConsultarSucursalesBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.CuentaOperativaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.CuentaTitulosControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaCompuestaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ImporteCompuestoControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.InputNumberControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.InputTextControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.LegalControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ListaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemCuentaOperativa;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemCuentaTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGenericoMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.LegalMaps;

/**
 * The Class ReporteCBUCuentaDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteComprobanteAltaAdhesionDAOTest {

    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private ReportesMapsDAO reportesMapsDAO = new ReportesMapsDAOImpl();

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    @Mock
	private ConsultarSucursalesBO consultarSucursalesBO = new ConsultarSucursalesBOImpl();
    
    

    /**
     * Inits the.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
    }

    
    /**
     * Test reporte.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException 
     */
    @Test
    public void testReporte() throws IOException, IllegalAccessException, DAOException {
        
        FormularioControl formulario = new FormularioControl();
        formulario.setFechaComprobante("31/12/2018 : 17:33");
        formulario.setComprobante("1733");
        formulario.setTitulo("Suscripcion Fondos de inversiones");
        List<ControlMaps> controles = new ArrayList<ControlMaps>();
        InputNumberControl control2 = new InputNumberControl();
        control2.setEtiqueta("Input Number");
        control2.setValor(new BigDecimal(33));
        control2.setIncremento(new BigDecimal(1));
        controles.add(control2);
        InputTextControl control3 = new InputTextControl();
        control3.setEtiqueta("Operacion de la activacion");
        control3.setValor("Suscripcion activacion de Fondo inversiones SAF");
        controles.add(control3);
        InputTextControl control4 = new InputTextControl();
        control4.setEtiqueta("Input Text");
        control4.setValor("Valor Input Text");
        controles.add(control4);
        InputTextControl control5 = new InputTextControl();
        control5.setEtiqueta("Input Text");
        control5.setValor("Valor Input Text");
        controles.add(control5);
        InputTextControl control6 = new InputTextControl();
        control6.setEtiqueta("Input Text");
        control6.setValor("Valor Input Text");
        controles.add(control6);
        InputTextControl control7 = new InputTextControl();
        control7.setEtiqueta("Input Text");
        control7.setValor("Valor Input Text");
        controles.add(control7);
        InputTextControl control8 = new InputTextControl();
        control8.setEtiqueta("Input Text");
        control8.setValor("Valor Input Text");
        controles.add(control8);
        InputNumberControl control9 = new InputNumberControl();
        control9.setEtiqueta("Input Number");
        control9.setIncremento(new BigDecimal(1));
        control9.setValor(new BigDecimal(19));
        controles.add(control9);
        controles.add(control9);
        controles.add(control9);
        controles.add(control9);
        controles.add(control9);
        controles.add(control9);
        List<InputNumberControl> items = new ArrayList<InputNumberControl>();
        items.add(control9);
        items.add(control9);
        ImporteCompuestoControl control10 = new ImporteCompuestoControl();
        control10.setEtiqueta("Importe compuesto");
        control10.setItems(items);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        controles.add(control10);
        LegalControl legal= new LegalControl();
        LegalMaps legalMaps = new LegalMaps();
        String textoLegal = "Banco Santander S.A. le informa que esta operación será cursada a través de BYMA, bajo la modalidad de compra venta en la rueda de concurrencia de negociación, quedando sujeta a confirmación por parte del mercado que se opere. Importe provisorio sujeto a la ejecución de su orden. Este importe corresponde al precio total de los títulos sumado la s comisiones y cargos de la operación. El importe se ajustará a los efectivamente ejecutado en el mercado. Los precios son orientativos, pudiendo diferir de los vigentes en el mercado. El Banco no garantiza estos precios al momento de operar. Monto mínimo para operaciones con CEDEARS $50,000,- Las operaciones bursátiles son cursadas a través del Banco Santander S.A. en su carácter de Agente de Liquidación y Compensación N°72 inscripto ante la Comisión Nacional de Valores. Banco Santander S.A.no será responsable de cambios de las condiciones de mercado, suspensiones que imponga la autoridad que corresponda, caso fortuito y/ o fuerza mayor que impida la formalización de la operación que por la presente ordena. Banco Santander S.A.le informa que se debitará una comisión del 1 % + IVA (con un mínimo de $30) y 0,08 % +IVA por impuestos y derechos de Mercado, calculados sobre el monto de transacción.Se continuarán cobrando estas comisiones, en forma transitoria, hasta que la CNV autorice comisiones máximas. Si desea modificar el precio o cantidad de la operación, por favor solicite la reversa de la misma, siempre y cuando no se haya ejecutado en el Mercado. Para las especies que hayan ingresado bajo la modalidad de subasta, la aplicación de la solicitud de reversa dependerá del Mercado operado. Desde el momento de confirmación de la solicitud de la orden, la tenencia se modificará dentro del plazo de negociación seleccionado. Dentro de dicho plazo se ajustarán los valores definitivos de tenencia y crédito. Para atención personalizada, podes contactarte al Centro de Atención Telefónica al 4345-2400 o desde el interior sin cargo al 0800";// - 999 - 2400.";
        legalMaps.setValor(textoLegal);
        List<LegalMaps> listaLegales =  new ArrayList<LegalMaps>();
        listaLegales.add(legalMaps);
        legal.setItems(listaLegales);
        controles.add(legal);
        controles.add(legal);
        formulario.setItems(controles);
        
        
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoTop", appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
        		true);
        FieldUtils.writeField(reportesMapsDAO, "fileJasperAlta",
                appContext.getResource("classpath:/report/maps/alta-adhesion-comprobante.jasper"), true);
        Reporte reporte = reportesMapsDAO.descargaComprobanteAltaAdhesion(formulario);
        Assert.assertNotNull(reporte.getBytes());
//         FileUtils.writeByteArrayToFile(new File("c:/file110.pdf"),
//         reporte.getBytes());
    }
    @Ignore
    @Test
    public void testInputNumberControl() {
        
        
        InputNumberControl input = new InputNumberControl();
        input.setValor(new BigDecimal("32132.1222"));
        input.setIncremento(new BigDecimal("2"));
        input.setSimbolo("POR");
        System.out.println("input 32132.1222 --.-- incremento:" + input.getIncremento() + " :" + input.valorComprobante());
        InputNumberControl input1 = new InputNumberControl();
        input1.setValor(new BigDecimal("32132.1222"));
        input1.setIncremento(new BigDecimal("2.1"));
        input1.setSimbolo("ARS");
        System.out.println("input 32132.1222 --.-- incremento:" + input1.getIncremento() + " :" + input1.valorComprobante());
        InputNumberControl input2 = new InputNumberControl();
        input2.setValor(new BigDecimal("32132.1222"));
        input2.setIncremento(new BigDecimal("2.11"));
        input2.setSimbolo("GBP");
        System.out.println("input 32132.1222 -.- incremento:" + input2.getIncremento() + " :" + input2.valorComprobante());
        InputNumberControl input3 = new InputNumberControl();
        input3.setValor(new BigDecimal("32132.1222")); 
        input3.setIncremento(new BigDecimal("2.122"));
        input3.setSimbolo("USD");
        System.out.println("input 32132.1222 -.- incremento:" + input3.getIncremento() + " :" + input3.valorComprobante());
        InputNumberControl input4 = new InputNumberControl();
        input4.setValor(new BigDecimal("32132.12")); 
        input4.setIncremento(new BigDecimal("2.122"));
        System.out.println("input:32132.12  incremento:" + input4.getIncremento() + " :" + input4.valorComprobante());
        InputNumberControl input5 = new InputNumberControl();
        input5.setValor(new BigDecimal("32132.1234")); 
        input5.setIncremento(new BigDecimal("2.12345"));
        System.out.println("input:32132.1234  incremento:" + input5.getIncremento() + " :" + input5.valorComprobante());
    }
  
    @Ignore
    @Test
    public void testReporteControlesAdicionales() throws IOException, IllegalAccessException, DAOException {
        
        FormularioControl formulario = new FormularioControl();
        formulario.setFechaComprobante("31/12/2018 : 17:33");
        formulario.setComprobante("1733");
        formulario.setTitulo("SAF");
        
        List<ControlMaps> controles = new ArrayList<ControlMaps>();
        
        FechaCompuestaControl fechaC = new FechaCompuestaControl();
        fechaC.setEtiqueta("Fecha Compuesta");
        FechaControl fechaDesde = new FechaControl();
        fechaDesde.setValor("2019-02-22");
        fechaDesde.setNombre("fecha-desde");
        
        FechaControl fechaHasta = new FechaControl();
        fechaHasta.setValor("2019-02-22");
        fechaHasta.setNombre("fecha-hasta");
        
        ListaControl<ItemGenericoMaps> vigencias = new ListaControl<ItemGenericoMaps>();
        List<ItemGenericoMaps> items = new ArrayList<ItemGenericoMaps>();
        ItemGenericoMaps vig1 = new ItemGenericoMaps();
        vig1.setDesc("Ultimos 30");
        vig1.setValor(30);
        vig1.setSeleccionado(Boolean.FALSE);
        ItemGenericoMaps vig2 = new ItemGenericoMaps();
        vig2.setDesc("Ultimos 60");
        vig2.setValor(60);
        vig2.setSeleccionado(Boolean.FALSE);
        ItemGenericoMaps vig3 = new ItemGenericoMaps();
        vig3.setDesc("Otro intervalo");
        vig3.setValor(0);
        vig3.setSeleccionado(Boolean.TRUE);
        items.add(vig1);
        items.add(vig2);
        items.add(vig3);
        vigencias.setItems(items);
        
        fechaC.setItems(new ArrayList<ControlMaps>());
        fechaC.getItems().add(vigencias);
        fechaC.getItems().add(fechaDesde);
        fechaC.getItems().add(fechaHasta);
        
        controles.add(fechaC);
        
        FechaCompuestaControl fechaC2 = new FechaCompuestaControl();
        fechaC2.setEtiqueta("Fecha Compuesta");
        FechaControl fechaDesde1 = new FechaControl();
        fechaDesde1.setValor("2019-02-22");
        fechaDesde1.setNombre("fecha-desde");
        
        FechaControl fechaHasta1 = new FechaControl();
        fechaHasta1.setValor("2019-02-22");
        fechaDesde1.setNombre("fecha-hasta");
        
        ListaControl<ItemGenericoMaps> vigencias2 = new ListaControl<ItemGenericoMaps>();
        List<ItemGenericoMaps> items2 = new ArrayList<ItemGenericoMaps>();
        ItemGenericoMaps vig4 = new ItemGenericoMaps();
        vig4.setDesc("Ultimos 30");
        vig4.setValor(30);
        vig4.setSeleccionado(Boolean.FALSE);
        ItemGenericoMaps vig5 = new ItemGenericoMaps();
        vig5.setDesc("Ultimos 60");
        vig5.setValor(60);
        vig5.setSeleccionado(Boolean.TRUE);
        ItemGenericoMaps vig6 = new ItemGenericoMaps();
        vig6.setDesc("Otro intervalo");
        vig6.setValor(0);
        vig6.setSeleccionado(Boolean.FALSE);
        items2.add(vig4);
        items2.add(vig5);
        items2.add(vig6);
        vigencias2.setItems(items2);
        
        fechaC2.setItems(new ArrayList<ControlMaps>());
        fechaC2.getItems().add(fechaDesde1);
        fechaC2.getItems().add(fechaHasta1);
        fechaC2.getItems().add(vigencias2);
        
        controles.add(fechaC2);
        formulario.setItems(controles);
        
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoTop", appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "fileJasperAlta",
                appContext.getResource("classpath:/report/maps/alta-adhesion-comprobante.jasper"), true);
        Reporte reporte = reportesMapsDAO.descargaComprobanteAltaAdhesion(formulario);
        Assert.assertNotNull(reporte.getBytes());
//        FileUtils.writeByteArrayToFile(new File("c:/file10.pdf"),
//                reporte.getBytes());
    }
    

    
    
    @Test
    public void testReporteControlCuentaTitulos() throws IOException, IllegalAccessException, DAOException {
        
        FormularioControl formulario = new FormularioControl();
        formulario.setFechaComprobante("31/12/2018 : 17:33");
        formulario.setComprobante("1733");
        formulario.setTitulo("SAF");
        
        List<ControlMaps> controles = new ArrayList<ControlMaps>();
        
        CuentaTitulosControl cuentaTitulosC = new CuentaTitulosControl();
        CuentaTitulosControl cuentaTitulosC2 = new CuentaTitulosControl();
        
        
        List<ItemCuentaTitulos> itemCuentaTitulos = new ArrayList<ItemCuentaTitulos>();
        List<ItemCuentaTitulos> itemCuentaTitulos2 = new ArrayList<ItemCuentaTitulos>();
        
        List<String> titulares  = new ArrayList<String>();
        titulares.add("Díaz, Pedro");
        titulares.add("Fuentes, Jesús");
        titulares.add("Echeverria, María");
        
        ItemCuentaTitulos item1CuentaTitulos = new ItemCuentaTitulos();
        item1CuentaTitulos.setNumeroCtaTitulo("246878");
        item1CuentaTitulos.setSeleccionado(Boolean.TRUE);
        item1CuentaTitulos.setTitulares(titulares);
        item1CuentaTitulos.setValor("24687/7");
    
        
        
        ItemCuentaTitulos item2CuentaTitulos = new ItemCuentaTitulos();
        item2CuentaTitulos.setNumeroCtaTitulo("246888");
        item2CuentaTitulos.setSeleccionado(Boolean.TRUE);
        item2CuentaTitulos.setTitulares(titulares);
        item2CuentaTitulos.setValor("24688/8");
        
        itemCuentaTitulos.add(item1CuentaTitulos);
        itemCuentaTitulos2.add(item2CuentaTitulos);
        cuentaTitulosC.setItems(itemCuentaTitulos);
        
        cuentaTitulosC.setEtiqueta("Nombre de la etiqueta");
        cuentaTitulosC.setNombre("SAF-Home");
        cuentaTitulosC.setId("cuenta-titulos1");
        
        cuentaTitulosC2.setItems(itemCuentaTitulos2);
        cuentaTitulosC2.setEtiqueta("Nombre de la etiqueta");
        cuentaTitulosC2.setNombre("SAF-Home");
        cuentaTitulosC2.setId("cuenta-titulos2");
            
        controles.add(cuentaTitulosC);
        controles.add(cuentaTitulosC2);
        formulario.setItems(controles);
        
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoTop", appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "fileJasperAlta",
                appContext.getResource("classpath:/report/maps/alta-adhesion-comprobante.jasper"), true);
        Reporte reporte = reportesMapsDAO.descargaComprobanteAltaAdhesion(formulario);
        Assert.assertNotNull(reporte.getBytes());
//      FileUtils.writeByteArrayToFile(new File("c:/file10.pdf"),
//      reporte.getBytes());
    }
  

    @Test
    public void testReporteControlCuentaOperativa() throws IOException, IllegalAccessException, DAOException {
        
        FormularioControl formulario = new FormularioControl();
        formulario.setFechaComprobante("31/12/2018 : 17:33");
        formulario.setComprobante("1733");
        formulario.setTitulo("SAF");
        
        List<ControlMaps> controles = new ArrayList<ControlMaps>();
        
        CuentaOperativaControl cuentaOperativaC = new CuentaOperativaControl();
        CuentaOperativaControl cuentaOperativaC2 = new CuentaOperativaControl();
        
        
        List<ItemCuentaOperativa> itemCuentaOperativa = new ArrayList<ItemCuentaOperativa>();
        List<ItemCuentaOperativa> itemCuentaOperativa2 = new ArrayList<ItemCuentaOperativa>();
        
        List<String> titulares  = new ArrayList<String>();
        titulares.add("Díaz, Pedro");
        titulares.add("Fuentes, Jesús");
        titulares.add("Echeverria, María");
        
        ItemCuentaOperativa item1CuentaOperativa = new ItemCuentaOperativa();
        item1CuentaOperativa.setTitulares(titulares);
        item1CuentaOperativa.setTipoCtaOperativa("03");
        item1CuentaOperativa.setProducto("003");
        item1CuentaOperativa.setSubproducto("0004");
        item1CuentaOperativa.setSucursalCtaOperativa("0001");
        item1CuentaOperativa.setNumeroCtaOperativa("0077886210");
        item1CuentaOperativa.setCodigoMoneda("USD");
        item1CuentaOperativa.setValor("0077-886210");
        item1CuentaOperativa.setSeleccionado(Boolean.TRUE);
        
        ItemCuentaOperativa item2CuentaOperativa = new ItemCuentaOperativa();
        item2CuentaOperativa.setTitulares(titulares);
        item2CuentaOperativa.setTipoCtaOperativa("00");
        item2CuentaOperativa.setProducto("003");
        item2CuentaOperativa.setSubproducto("0004");
        item2CuentaOperativa.setSucursalCtaOperativa("0001");
        item2CuentaOperativa.setNumeroCtaOperativa("0077240010");
        item2CuentaOperativa.setCodigoMoneda("ARS");
        item2CuentaOperativa.setValor("0077-240010");
        item2CuentaOperativa.setSeleccionado(Boolean.TRUE);

        itemCuentaOperativa.add(item1CuentaOperativa);
        itemCuentaOperativa2.add(item2CuentaOperativa);
        
        cuentaOperativaC.setItems(itemCuentaOperativa);        
        cuentaOperativaC.setId("lista-cuentas-operativas");
        cuentaOperativaC.setNombre("lista-cuentas-operativas");
        cuentaOperativaC.setEtiqueta("Seleccione la cuenta operativa");
        
        cuentaOperativaC2.setItems(itemCuentaOperativa2);        
        cuentaOperativaC2.setId("lista-cuentas-operativas");
        cuentaOperativaC2.setNombre("lista-cuentas-operativas");
        cuentaOperativaC2.setEtiqueta("Seleccione la cuenta operativa");
        
        controles.add(cuentaOperativaC);
        controles.add(cuentaOperativaC2);
        
        formulario.setItems(controles);
        
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoTop", appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "imagenLogoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        FieldUtils.writeField(reportesMapsDAO, "fileJasperAlta",
                appContext.getResource("classpath:/report/maps/alta-adhesion-comprobante.jasper"), true);
        Reporte reporte = reportesMapsDAO.descargaComprobanteAltaAdhesion(formulario);
        Assert.assertNotNull(reporte.getBytes());
//      FileUtils.writeByteArrayToFile(new File("c:/file10.pdf"),
//      reporte.getBytes());
    }
    
    
    
}
