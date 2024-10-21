package ar.com.santanderrio.obp.servicios.prestamos.mock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.PrestamoCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagosFechaOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;

public final class PrestamoOpenCreditObjectsMock {
    
    private PrestamoOpenCreditObjectsMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    
    /**
     * Obtiene la entidad Prestamo
     */
    public static Prestamo obtenerResultadoDaoDeudaPrestamo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("0082");
        cuenta.setTipoCuentaEnum(TipoCuenta.PRESTAMO);
        cuenta.setNroCuentaProducto("231237123");
        cuenta.setClaseCuenta("O");
        Prestamo resultadoDaoDeudaPrestamo = new Prestamo();
        resultadoDaoDeudaPrestamo.setDivisa(DivisaEnum.PESO);
        resultadoDaoDeudaPrestamo.setOtrosImpuestos(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setIngresosBrutos(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setOtrosImpuestos(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setImporteIVA(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setImportesPunitorios(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setImportesPunitorios(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setInteresCompensatorioPendiente(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setImporteIntereses(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setTasaPrestamo(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setImporteTotalCuota(new BigDecimal("0"));
        resultadoDaoDeudaPrestamo.setVencimientoCuota(new Date());
        resultadoDaoDeudaPrestamo.setCuenta(cuenta);
        resultadoDaoDeudaPrestamo.setNumeroRecibo("1");
        return resultadoDaoDeudaPrestamo;
    }

    /**
     * Obtiene la entidad Prestamo
     */
    public static ConsultaPrestamoOpenCreditOutEntity obtenerConsultaPrestamoOpenCreditOutEntity() {
        ConsultaPrestamoOpenCreditOutEntity resultadoDaoOpenCredit = new ConsultaPrestamoOpenCreditOutEntity();
        resultadoDaoOpenCredit.setCapitalDispuesto(new BigDecimal("0"));
        resultadoDaoOpenCredit.setCapitalSolicitado(new BigDecimal("0"));
        resultadoDaoOpenCredit.setFechasInicio("2017-05-092017-05-092017-05-092017-05-302017-05-302017-05-30");
        return resultadoDaoOpenCredit;
    }
    
    /**
     * Obtiene la entidad ConsultaPrestamoOpenCreditInEntity
     */
    public static ConsultaPrestamoOpenCreditInEntity obtenerConsultaPrestamoOpenCreditInEntity() {
        ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity = new ConsultaPrestamoOpenCreditInEntity();
        consultaPrestamoOpenCreditInEntity.setCliente(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        consultaPrestamoOpenCreditInEntity.setFechaDelDia(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        consultaPrestamoOpenCreditInEntity.setNumeroPrestamo("0000035102898978");
        consultaPrestamoOpenCreditInEntity.setSucursal("0000");
        consultaPrestamoOpenCreditInEntity.setTipoPrestamo("00");
        return consultaPrestamoOpenCreditInEntity;
    }

    /**
     * Obtiene la entidad ConsultaCuotaPagaInEntity
     */
    public static ConsultaCuotaPagaInEntity obtenerConsultaCuotaPagaInEntity() {
        ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = new ConsultaCuotaPagaInEntity();
        consultaCuotaPagaEntity.setNumRegistros("000");
        consultaCuotaPagaEntity.setOficina("0000");
        consultaCuotaPagaEntity.setCuenta("035102898978");
        consultaCuotaPagaEntity.setCodEvento("COBR");
        consultaCuotaPagaEntity.setFecInicio("2017-05-09");
        consultaCuotaPagaEntity.setFecFin("2017-10-24");
        consultaCuotaPagaEntity.setTipomov(" ");
        consultaCuotaPagaEntity.setTimestamp(ISBANStringUtils.fillStr("", 26));
        consultaCuotaPagaEntity.setCodconli(ISBANStringUtils.fillStr("", 3));
        consultaCuotaPagaEntity.setNumSecuencia(ISBANStringUtils.fillStr("", 3));
        return consultaCuotaPagaEntity;
    }
    /**
     * Obtiene la entidad ConsultaPagosMinimosOpenCreditInDTO
     */
    public static ConsultaPagosMinimosOpenCreditInDTO obtenerConsultaPagosMinimosOpenCreditInDTO() {
        ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosOpenCreditInDTO = new ConsultaPagosMinimosOpenCreditInDTO();
        consultaPagosMinimosOpenCreditInDTO.setCuenta(obtenerCuentaOpenCredit());
        consultaPagosMinimosOpenCreditInDTO.setFechaInicio("2017-05-09");
        return consultaPagosMinimosOpenCreditInDTO;
    }
    
    /**
     * Obtiene la entidad ConsultaCuotaPagaOutEntity
     */
    public static ConsultaCuotaPagaOutEntity obtenerConsultaCuotaPagaOutEntity() {
        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        PrestamoCuotaPagaOutEntity pagoEntity = obtenerPrestamoCuotaPagaOutEntity();
        List<PrestamoCuotaPagaOutEntity> listCuotaPaga = new ArrayList<PrestamoCuotaPagaOutEntity>();
        listCuotaPaga.add(pagoEntity);
        consultaCuotaPagaOutEntity.setCuotasPagas(listCuotaPaga);
        consultaCuotaPagaOutEntity.setMarcaRellamada("N");
        consultaCuotaPagaOutEntity.setTimestamp("              ");
        consultaCuotaPagaOutEntity.setCodconli("000111");
        consultaCuotaPagaOutEntity.setNumSecuencia("1");
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(1L);
        return consultaCuotaPagaOutEntity;
    }
    
    
    /**
     * Obtiene la entidad PrestamoCuotaPagaOutEntity
     */
    private static PrestamoCuotaPagaOutEntity obtenerPrestamoCuotaPagaOutEntity() {
        PrestamoCuotaPagaOutEntity prestamoCoutaPagaOutEntity = new PrestamoCuotaPagaOutEntity();
        prestamoCoutaPagaOutEntity.setNumrec("001");
        prestamoCoutaPagaOutEntity.setIntinire("00100");
        prestamoCoutaPagaOutEntity.setDivisaPago("1");
        prestamoCoutaPagaOutEntity.setImpinire("00001");
        prestamoCoutaPagaOutEntity.setTna("0000100");
        prestamoCoutaPagaOutEntity.setFeliq("0001");
        prestamoCoutaPagaOutEntity.setImporteCargo("00001");
        return prestamoCoutaPagaOutEntity;
    }


    private static  Cuenta obtenerCuentaOpenCredit() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("30");
        cuenta.setNroSucursal("0082");
        cuenta.setTipoCuentaEnum(TipoCuenta.PRESTAMO);
        cuenta.setNroCuentaProducto("231237123");
        cuenta.setClaseCuenta("O");
        cuenta.setIndex(1);
        return cuenta;
    }
    

    /**
     * Obtiene la entidad Cliente
     */   
    public static Cliente obtenerClienteConCuentaOpenCredit() {
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(obtenerCuentaOpenCredit());
        cliente.setCuentas(listaCuentas);
        return cliente;
    }

    /**
     * Obtiene la entidad ReportePagosMinimosOpenCreditInView
     */
    public static ReportePagosMinimosOpenCreditInView obtenerReportePagosMinimosOpenCreditInView() {
        ReportePagosMinimosOpenCreditInView reportePagosMinimosOpenCreditInView = new ReportePagosMinimosOpenCreditInView();
        reportePagosMinimosOpenCreditInView.setDetallePagosMinimos(obtenerDetallePagosMinimos());
        reportePagosMinimosOpenCreditInView.setDisponibleParaUso("$ 10.00");
        reportePagosMinimosOpenCreditInView.setLineaCreditoTotal("$ 10000.00");
        reportePagosMinimosOpenCreditInView.setNumeroPrestamo("0/34234235");
        return reportePagosMinimosOpenCreditInView;
    }

    /**
     * Obtiene la entidad DetallePagosMinimosOpenCreditView
     */
    public static DetallePagosMinimosOpenCreditView obtenerDetallePagosMinimos() {
        DetallePagosMinimosOpenCreditView detallePagosMinimosOpenCreditView = new DetallePagosMinimosOpenCreditView();
        detallePagosMinimosOpenCreditView.setPagosFecha(obtenerPagosFecha());
        return detallePagosMinimosOpenCreditView;
    }

    /**
     * Obtiene la entidad List<PagosFechaOpenCreditView>
     */
    private static List<PagosFechaOpenCreditView> obtenerPagosFecha() {
        List<PagosFechaOpenCreditView> listaPagos = new ArrayList<PagosFechaOpenCreditView>();
        PagosFechaOpenCreditView pagosFechaOpenCreditView = new PagosFechaOpenCreditView();
        pagosFechaOpenCreditView.setFecha("2017");
        pagosFechaOpenCreditView.setPagos(obtenerPagosMinimos());
        listaPagos.add(pagosFechaOpenCreditView);
        return listaPagos;
    }

    /**
     * Obtiene la entidad List<PagoOpenCreditView>
     */
    private static List<PagoOpenCreditView> obtenerPagosMinimos() {
        List<PagoOpenCreditView> pagosOpenCredit = new ArrayList<PagoOpenCreditView>();
        PagoOpenCreditView pago = new PagoOpenCreditView();
        pago.setFechaVencimiento("2017/05/03");
        pago.setImpuestos("$ 31,02");
        pago.setIntCompensatoriosPeriodo("$ 15,01");
        pago.setNumeroPagoMinimo("1");
        pago.setTasaTNA("% 0,300");
        pagosOpenCredit.add(pago);
        PagoOpenCreditView pago2 = new PagoOpenCreditView();
        pago2.setFechaVencimiento("2017/05/03");
        pago2.setImpuestos("$ 31,99");
        pago2.setIntCompensatoriosPeriodo("$ 99,01");
        pago2.setNumeroPagoMinimo("2");
        pago2.setTasaTNA("% 0,300");
        pagosOpenCredit.add(pago2);
        return pagosOpenCredit;
    }


    public static List<PrestamoOpenCreditDTO> obtenerPrestamosOpenCreditDTO() {
        List<PrestamoOpenCreditDTO> prestamosOpenCreditDTO = new ArrayList<PrestamoOpenCreditDTO>() ;
        PrestamoOpenCreditDTO prestamoDTO = new PrestamoOpenCreditDTO();
        prestamoDTO.setCuenta(obtenerCuentaOpenCredit());
        prestamoDTO.setDescripcion("Open Credit");
        prestamoDTO.setDisponibleParaUso(new BigDecimal("0011100"));
        prestamoDTO.setDivisa(DivisaEnum.PESO);
        prestamoDTO.setFechaInicio("2017-05-09");
        prestamoDTO.setFechaVencimiento("21/03/05");
        prestamoDTO.setIngresosBrutos(new BigDecimal("000111000"));
        prestamoDTO.setIntCompensatoriosPeriodo(new BigDecimal("000111000"));
        prestamoDTO.setIntCompensatoriosPostVencimiento(new BigDecimal("000111000"));
        prestamoDTO.setInteresesPunitorios(new BigDecimal("000111000"));
        prestamoDTO.setIva(new BigDecimal("000111000"));
        prestamoDTO.setLineaCreditoTotal(new BigDecimal("000111000"));
        prestamoDTO.setNumeroRecibo(1);
        prestamoDTO.setOtrosImpuestos(new BigDecimal("000111000"));
        prestamoDTO.setPagoMinimo(new BigDecimal("000111000"));
        prestamoDTO.setTasaTNA(new BigDecimal("000111000"));
        prestamoDTO.setVencimientoCuota(new Date());
        prestamoDTO.setCapital(new BigDecimal("000111000"));
        prestamoDTO.setTasaTEA(new BigDecimal("000111000"));
        prestamoDTO.setCftConImp(new BigDecimal("000111000"));
        prestamoDTO.setCftSinImp(new BigDecimal("000111000"));
        prestamosOpenCreditDTO.add(prestamoDTO);
        return prestamosOpenCreditDTO;
    }

    /**
     * Obtiene la entidad CnsDetallePagosMinimosOpenCreditView
     */
    public static CnsDetallePagosMinimosOpenCreditView obtenerConsultaDetallePagosMMinimos() {
        CnsDetallePagosMinimosOpenCreditView consultaDetallePagos = new CnsDetallePagosMinimosOpenCreditView();
        consultaDetallePagos.setFechaInicio("2017-05-09");
        consultaDetallePagos.setId("6");
        return consultaDetallePagos;
    }

    /**
     * Obtiene la entidad DetallePagosMinimosOpenCreditDTO
     */
    public static DetallePagosMinimosOpenCreditDTO obtenerDetallePagosMinimosOpenCreditDTO() {
        DetallePagosMinimosOpenCreditDTO detallePagosMinimosDTO = new DetallePagosMinimosOpenCreditDTO();
        detallePagosMinimosDTO.setLineaCreditoTotal("$ 1300");
        detallePagosMinimosDTO.setDisponibleParaUso("$ 1300");
        detallePagosMinimosDTO.setNumeroPrestamo("0/32432432432");
        detallePagosMinimosDTO.setPagos(obtenerPagosOpenCreditDTO());
        return detallePagosMinimosDTO;
    }

    /**
     * Obtiene la entidad lista PagoOpenCreditDTO
     */
    private static List<PagoOpenCreditDTO> obtenerPagosOpenCreditDTO() {
        List<PagoOpenCreditDTO> listaPagosMinimos = new ArrayList<PagoOpenCreditDTO>();
        PagoOpenCreditDTO pago = new PagoOpenCreditDTO();
        pago.setDivisa(DivisaEnum.PESO);
        pago.setFechaVencimiento("20170801");
        pago.setImpuestos("10000000000000000");
        pago.setIntCompensatoriosPeriodo("10000000000000000");
        pago.setLineaCreditoTotal("");
        pago.setNumeroPagoMinimo("1");
        pago.setPagoMinimo("10000000000000000");
        pago.setTasaTNA("000111000");
        pago.setCapital("10000000000000000");
        pago.setTasaTEA("000111000");
        pago.setCftConImp("000111000");
        pago.setCftSinImp("000111000");
        listaPagosMinimos.add(pago);
        PagoOpenCreditDTO pago2 = new PagoOpenCreditDTO();
        pago2.setDivisa(DivisaEnum.PESO);
        pago2.setFechaVencimiento("27/05/2017");
        pago2.setImpuestos("10000000000000000");
        pago2.setIntCompensatoriosPeriodo("10000000000000000");
        pago2.setLineaCreditoTotal("");
        pago2.setNumeroPagoMinimo("2");
        pago2.setPagoMinimo("10000000000000000");
        pago2.setTasaTNA("000111000");
        pago2.setCapital("10000000000000000");
        pago2.setTasaTEA("000111000");
        pago2.setCftConImp("000111000");
        pago2.setCftSinImp("000111000");
        listaPagosMinimos.add(pago2);
        return listaPagosMinimos;
    }


    public static Reporte obtenerRespuestaReportePagos() {
        Reporte reporte = new Reporte();
        reporte.setBytes(new byte[8]);
        reporte.setNombre("HistorialPagosMinimos.xls");
        reporte.setTipoArchivo(TipoArchivoEnum.EXCEL);
        return reporte;
    }
    
}
