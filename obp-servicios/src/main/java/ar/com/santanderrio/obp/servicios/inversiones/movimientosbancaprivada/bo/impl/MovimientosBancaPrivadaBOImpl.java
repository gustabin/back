package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.CoordinadorComprobantesBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientosPDF;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcel;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcelCabecera;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ItemMovimiento;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaMovimientosCuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPinEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO;

/**
 * The Class MovimientosBancaPrivadaBOImpl.
 */
@Component
public class MovimientosBancaPrivadaBOImpl implements MovimientosBancaPrivadaBO {

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The cuenta saldo DAO. */
    @Autowired
    private CuentaSaldoDAO cuentaSaldoDAO;

    /** The cuentas banca privada BO. */
    @Autowired
    private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

    /** The reporte DAO. */
    @Autowired
    private ReporteDAO reporteDAO;

    /** The canal bca priv. */
    @Value("${INVERSIONES.CANAL.BANCAPRIVADA}")
    private String canalBcaPriv;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinadorComprobantesBOImpl.class);

    /** The Constant LIMPIANDO_CACHE. */
    private static final String LIMPIANDO_CACHE = "Limpiando cache {}.";

    /** The Constant AYER. */
    private static final int AYER = 1;

    /** The Constant NUM_TRES_DIAS. */
    private static final int NUM_TRES_DIAS = 3;

    /** The Constant NUM_SIETE_DIAS. */
    private static final int NUM_SIETE_DIAS = 7;
    
    /** The Constant NUM_OCHO_DIAS. */
    private static final int NUM_OCHO_DIAS = 8;
    
    /** The Constant NUM_QUINCE_DIAS. */
    private static final int NUM_QUINCE_DIAS = 15;

    /** The Constant NUM_TREINTA_DIAS. */
    private static final int NUM_TREINTA_DIAS = 30;

    /** The Constant NUM_SESENTA_DIAS. */
    private static final int NUM_SESENTA_DIAS = 60;
    
    /** The Constant NUM_CINCUENTA_Y_TRES_DIAS. */
    private static final int NUM_CINCUENTA_Y_TRES_DIAS = 53;

    /** The Constant IMPORTE_VACIO. */
    private static final String IMPORTE_VACIO = "";

    /** The Constant BIG_DECIMAL_INFINITO. */
    private static final BigDecimal BIG_DECIMAL_INFINITO = new BigDecimal("999999999.99");

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#limpiarCache(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @CacheEvict(value = CacheConstants.Names.CACHE_MOVIMIENTOS_BANCA_PRIVADA, key = "#cliente.nup")
    @Override
    public void limpiarCache(Cliente cliente) {
        LOGGER.info(LIMPIANDO_CACHE, CacheConstants.CACHE_MOVIMIENTOS_BANCA_PRIVADA);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#obtenerMovimientos(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Cacheable(value = CacheConstants.Names.CACHE_MOVIMIENTOS_BANCA_PRIVADA, key = "#cliente.nup")
    @Override
    public MovimientosCuentaBPOutEntity obtenerMovimientos(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView, Cliente cliente, Cuenta cuenta) throws BusinessException {

        Credential credencial;
        try {
            credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
        } catch (SQLException e) {
            throw new BusinessException();
        }

        MovimientosCuentaBPinEntity movimientosCuentaBPinEntity = new MovimientosCuentaBPinEntity();
        
        if (cuenta != null) {
            movimientosCuentaBPinEntity.setCuenta(ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getProductoAltair())  + StringUtils.right(cuenta.getNroCuentaProducto(), 9));
        } else {
            throw new BusinessException();
        }

        movimientosCuentaBPinEntity.setUsuario(credencial.getUsuario());
        movimientosCuentaBPinEntity.setPass(credencial.getPassword());
        movimientosCuentaBPinEntity.setDivisa(consultaUltimosMovimientosView.getMoneda().getCodigo());
        movimientosCuentaBPinEntity.setCanal(canalBcaPriv);
        setearFechas(consultaUltimosMovimientosView, movimientosCuentaBPinEntity);
        MovimientosCuentaBPOutEntity movimientos;
        try {
            movimientos = cuentaSaldoDAO.verMovimientosCuentaBancaPrivada(movimientosCuentaBPinEntity);
        } catch (DAOException e) {
            throw new BusinessException();
        }
        setearDate(movimientos);
        ordenarPorFecha(movimientos);
        return movimientos;
    }

    /**
	 * Sets the ear date.
	 *
	 * @param movimientos
	 *            the new ear date
	 */
    private void setearDate(MovimientosCuentaBPOutEntity movimientos) {
        if (movimientos.getErrorEnConsulta().equals(Boolean.FALSE)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (RtaMovimientosCuentaBP mov : movimientos.getRespuesta()) {
                try {
                    mov.setFechaOperacionDate(sdf.parse(mov.getFechaOperacion()));
                } catch (ParseException e) {
                    movimientos.setErrorEnConsulta(Boolean.TRUE);
                    break;
                }
            }
        }
    }

    /**
	 * Ordenar por fecha.
	 *
	 * @param movimientos
	 *            the movimientos
	 */
    private void ordenarPorFecha(MovimientosCuentaBPOutEntity movimientos) {
        if (movimientos.getErrorEnConsulta().equals(Boolean.FALSE)) {
            Collections.sort(movimientos.getRespuesta(), new Comparator<RtaMovimientosCuentaBP>() {
                @Override
                public int compare(RtaMovimientosCuentaBP mov1, RtaMovimientosCuentaBP mov2) {
                    return mov2.getFechaOperacionDate().compareTo(mov1.getFechaOperacionDate());
                }
            });
        }
    }

    /**
	 * Obtener cuenta cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the cuenta
	 */
    public Cuenta obtenerCuentaCliente(Cliente cliente,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        String numeroCuenta = StringUtils.right(consultaUltimosMovimientosView.getNumero().replaceAll("\\/", ""), 7);
        List<Cuenta> cuentasBP = cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente);
        for (Cuenta cuentaBancaPrivada : cuentasBP) {
            if (cuentaBancaPrivada.getNroCuentaProducto().contains(numeroCuenta)) {
                return cuentaBancaPrivada;
            }
        }

        return null;
    }

    /**
	 * Setear fechas.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @param movimientosCuentaBPinEntity
	 *            the movimientos cuenta B pin entity
	 */
    private void setearFechas(ConsultaUltimosMovimientosView consultaUltimosMovimientosView,
            MovimientosCuentaBPinEntity movimientosCuentaBPinEntity) {
        Date fechaHoyCeroHoras = obtenerFechaHoy(false);
        Date fechaHoyUltimaHora = obtenerFechaHoy(true);
        Date fechaActual = new Date();
        switch (consultaUltimosMovimientosView.getRango()) {
        case AYER:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaHoyCeroHoras, AYER));
            movimientosCuentaBPinEntity.setFechaHasta(fechaHoyUltimaHora);
            break;
        case TRES_DIAS:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaHoyCeroHoras, NUM_TRES_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaHoyUltimaHora);
            break;
        case SIETE_DIAS:
        case DEFAULT:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaHoyCeroHoras, NUM_SIETE_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaHoyUltimaHora);
            break;
        case QUINCE_DIAS:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaHoyCeroHoras, NUM_QUINCE_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaHoyUltimaHora);
            break;
        case TREINTA_DIAS:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaHoyCeroHoras, NUM_TREINTA_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaHoyUltimaHora);
            break;
        case SESENTA_DIAS:
            movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaActual, NUM_SESENTA_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaActual);
            break;
        case CINCUENTA_Y_TRES_DIAS:
        	Date fechaEspecial = restarDias(fechaHoyCeroHoras, NUM_SIETE_DIAS);
        	Date fechaEspecialMenosUnDia = restarDias(fechaHoyCeroHoras, NUM_OCHO_DIAS);
        	movimientosCuentaBPinEntity.setFechaDesde(restarDias(fechaEspecial, NUM_CINCUENTA_Y_TRES_DIAS));
            movimientosCuentaBPinEntity.setFechaHasta(fechaEspecialMenosUnDia);
            break;        	
        case PERSONALIZADO:
            // Se debe validar que si el rango de días es el máximo (60 días)
            // se debe usar la hora actual tanto en el desde como en el hasta,
            // porque el SP rompe al mandarle mas de 60 días, aunque sean 60
            // días y 1 minuto. Si no es el rango máximo, se usará el rango de
            // días completo, desde las 00:00 hasta las 23:59
            Date fechaDesde = convertirFecha(consultaUltimosMovimientosView.getFechaDesde(), true);
            Date fechaHasta = convertirFecha(consultaUltimosMovimientosView.getFechaHasta(), false);
            if (validarRangoMaximoDias(fechaDesde, fechaHasta)) {
                Date hoy = new Date();
                movimientosCuentaBPinEntity.setFechaDesde(restarDias(hoy, NUM_SESENTA_DIAS));
                movimientosCuentaBPinEntity.setFechaHasta(hoy);
            } else {
                movimientosCuentaBPinEntity.setFechaDesde(fechaDesde);
                movimientosCuentaBPinEntity.setFechaHasta(fechaHasta);
            }
            break;
        default:
            break;
        }
    }

    /**
	 * Validar rango maximo dias.
	 *
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @return true, if successful
	 */
    private boolean validarRangoMaximoDias(Date fechaDesde, Date fechaHasta) {
        return DateUtils.isSameDay(fechaDesde, restarDias(new Date(), NUM_SESENTA_DIAS))
                && DateUtils.isSameDay(fechaHasta, new Date());
    }

    /**
	 * Convertir fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @param primeraHora
	 *            the primera hora
	 * @return the date
	 */
    private Date convertirFecha(String fecha, Boolean primeraHora) {

        DateTime fechaDateTime;

        if (primeraHora) {
            fechaDateTime = new DateTime(Integer.parseInt(fecha.substring(6, fecha.length())),
                    Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2)), 0, 0, 0, 0);
        } else {
            fechaDateTime = new DateTime(Integer.parseInt(fecha.substring(6, fecha.length())),
                    Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2)), 23, 59, 59, 0);
        }

        return fechaDateTime.toDate();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#obtenerFechaHoy(java.lang.Boolean)
     */
    @Override
    public Date obtenerFechaHoy(Boolean isUltimaHora) {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        DateTime fechaHoy;

        if (isUltimaHora) {
            fechaHoy = new DateTime(year, month + 1, date, 23, 59, 59, 0);
        } else {
            fechaHoy = new DateTime(year, month + 1, date, 0, 0, 0, 0);
        }

        return fechaHoy.toDate();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#restarDias(java.util.Date, int)
     */
    @Override
    public Date restarDias(Date fecha, int cantidadDias) {

        DateTime fechaDateTime = new DateTime(fecha);
        fechaDateTime = fechaDateTime.plusDays(-cantidadDias);
        return fechaDateTime.toDate();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#aplicarFiltros(ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity, ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView)
     */
    @Override
    public MovimientosCuentaBPOutEntity aplicarFiltros(MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

        MovimientosCuentaBPOutEntity listaFiltrada = new MovimientosCuentaBPOutEntity();
        List<RtaMovimientosCuentaBP> listaMovimiento = new ArrayList<RtaMovimientosCuentaBP>();
        listaFiltrada.setRespuesta(listaMovimiento);

        if (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteDesde())
                || StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteHasta())) {
            listaFiltrada = filtroImporte(movimientosCuentaBPOutEntity, consultaUltimosMovimientosView, listaFiltrada);
        }

        if (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getPalabraClave())) {
            if (CollectionUtils.isNotEmpty(listaFiltrada.getRespuesta())) {
                listaFiltrada = filtroPalabraClave(listaFiltrada, consultaUltimosMovimientosView);
            } else {
                listaFiltrada = filtroPalabraClave(movimientosCuentaBPOutEntity, consultaUltimosMovimientosView);
            }
        }

        return listaFiltrada;

    }

    /**
	 * Filtro importe.
	 *
	 * @param movimientosCuentaBPOutEntity
	 *            the movimientos cuenta BP out entity
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @param listaFiltrada
	 *            the lista filtrada
	 * @return the movimientos cuenta BP out entity
	 */
    private MovimientosCuentaBPOutEntity filtroImporte(MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView, MovimientosCuentaBPOutEntity listaFiltrada) {

        for (RtaMovimientosCuentaBP movimientoSinFiltrar : movimientosCuentaBPOutEntity.getRespuesta()) {
            BigDecimal ingreso = new BigDecimal(movimientoSinFiltrar.getIngresos());
            BigDecimal egreso = new BigDecimal(movimientoSinFiltrar.getEgresos());

            String importeDesde = StringUtils.isEmpty(consultaUltimosMovimientosView.getImporteDesde()) ? "0"
                    : consultaUltimosMovimientosView.getImporteDesde();
            String importeHasta = StringUtils.isEmpty(consultaUltimosMovimientosView.getImporteHasta()) ? "999999999.99"
                    : consultaUltimosMovimientosView.getImporteHasta();

            BigDecimal importeDesdePositivo = new BigDecimal(importeDesde);
            BigDecimal importeHastaPositivo = new BigDecimal(importeHasta);

            BigDecimal importeDesdeNegativo = new BigDecimal("-" + importeDesde);
            BigDecimal importeHastaNegativo = new BigDecimal("-" + importeHasta);

            if ((ingreso.compareTo(BigDecimal.ZERO) > 0 && ingreso.compareTo(importeDesdePositivo) >= 0
                    && ingreso.compareTo(importeHastaPositivo) <= 0)
                    || (egreso.compareTo(BigDecimal.ZERO) < 0 && egreso.compareTo(importeHastaNegativo) >= 0
                            && egreso.compareTo(importeDesdeNegativo) <= 0)) {
                listaFiltrada.getRespuesta().add(movimientoSinFiltrar);
            }
        }

        return listaFiltrada;

    }

    /**
	 * Filtro palabra clave.
	 *
	 * @param movimientosCuentaBPOutEntity
	 *            the movimientos cuenta BP out entity
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos cuenta BP out entity
	 */
    private MovimientosCuentaBPOutEntity filtroPalabraClave(MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

        MovimientosCuentaBPOutEntity listaFiltrada = new MovimientosCuentaBPOutEntity();
        List<RtaMovimientosCuentaBP> movimientos = new ArrayList<RtaMovimientosCuentaBP>();

        for (RtaMovimientosCuentaBP movimientoSinFiltrar : movimientosCuentaBPOutEntity.getRespuesta()) {
            String descripcion = movimientoSinFiltrar.getConcepto() + " " + movimientoSinFiltrar.getTextoDelApunte();
            descripcion = StringUtils.lowerCase(descripcion);
            String palabraMinuscula = StringUtils.lowerCase(consultaUltimosMovimientosView.getPalabraClave());
            palabraMinuscula = StringUtils.stripAccents(palabraMinuscula);

            if (descripcion.contains(palabraMinuscula)) {
                movimientos.add(movimientoSinFiltrar);
            }
        }

        listaFiltrada.setRespuesta(movimientos);

        return listaFiltrada;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO#exportarUltimosMovimientos(java.util.List, java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos)
     */
    @Override
    public Respuesta<Reporte> exportarUltimosMovimientos(List<ItemMovimiento> listaMovimientos, String dispositivo,
            Cliente cliente, ConsultaUltimosMovimientos consultaUltimosMovimientos) {

        MovimientosExcel movimientosExcel = obtenerDatosExcel(consultaUltimosMovimientos, dispositivo, listaMovimientos);
		formatearMontos(movimientosExcel);

        return reporteDAO.obtenerReporte(movimientosExcel, ExcelBuilderHelper.ULTIMOS_MOVIMIENTOS, cliente, true);
    }
    
    @Override
    public DetalleComprobanteView exportarUltimosMovimientosPDF(List<ItemMovimiento> listaMovimientos, String dispositivo, ConsultaUltimosMovimientos filtro) {
        MovimientosExcel movimientosExcel = obtenerDatosExcel(filtro, dispositivo, listaMovimientos);
        return new DetalleUltimosMovimientosPDF(movimientosExcel);
    }

    private MovimientosExcel obtenerDatosExcel(ConsultaUltimosMovimientos filtro, String dispositivo, List<ItemMovimiento> listaMovimientos) {
        MovimientosExcel movimientosExcel = new MovimientosExcel();
        Boolean tieneCuentaCorriente = false;
        for (ItemMovimiento itemMovimiento : listaMovimientos) {
            if (itemMovimiento.getIsCuentaCorrienteEnPesos()) {
                tieneCuentaCorriente = true;
                break;
            }
        }
        MovimientosExcelCabecera cabecera = crearCabeceraExcel(filtro, dispositivo,
                tieneCuentaCorriente);

        List<MovimientoExcelItem> items = new ArrayList<MovimientoExcelItem>();
        for (ItemMovimiento itemMovimiento : listaMovimientos) {
            MovimientoExcelItem movimientoExcel = crearItemExcel(itemMovimiento, filtro);
            items.add(movimientoExcel);
        }

        movimientosExcel.setCabecera(cabecera);
        movimientosExcel.setMovimientos(items);
        return movimientosExcel;
    
    }
    
    
	private void formatearMontos (MovimientosExcel movimientosExcel) {
		
		for (MovimientoExcelItem movimiento : movimientosExcel.getMovimientos()) {
			movimiento.setImporteString(ISBANStringUtils.formatearSaldo(new BigDecimal(movimiento.getImporte().toString())));
			movimiento.setSaldoString(ISBANStringUtils.formatearSaldo(new BigDecimal(movimiento.getSaldo().toString())));
		}
		
	}
	
    /**
	 * Crear cabecera excel.
	 *
	 * @param filtro
	 *            the filtro
	 * @param dispositivo
	 *            the dispositivo
	 * @param tieneCuentaCorriente
	 *            the tiene cuenta corriente
	 * @return the movimientos excel cabecera
	 */
    private MovimientosExcelCabecera crearCabeceraExcel(ConsultaUltimosMovimientos filtro, String dispositivo,
            Boolean tieneCuentaCorriente) {
        MovimientosExcelCabecera cabecera = new MovimientosExcelCabecera();

        // siempre filtro por fecha
        Boolean mostrarFiltroFecha = true;

        String fechaDesde = ISBANStringUtils.formatearFecha(filtro.getFechaDesde());
        String fechaHasta = ISBANStringUtils.formatearFecha(filtro.getFechaHasta());

        cabecera.setFechaDesde(fechaDesde);
        cabecera.setFechaHasta(fechaHasta);

        if (filtro.getImporteDesde() == null || filtro.getImporteDesde().compareTo(BigDecimal.ZERO) == 0) {
			if (!importeHastaDistintoDeNullOCero(filtro.getImporteHasta())) {
				cabecera.setImporteDesde(ISBANStringUtils.formatearSaldo(new BigDecimal("0.00")));
				filtro.setImporteDesde(null);				
			} else {
	            cabecera.setImporteDesde(IMPORTE_VACIO);
	            filtro.setImporteDesde(null);
			}
        } else {
            cabecera.setImporteDesde(ISBANStringUtils.formatearSaldo(filtro.getImporteDesde()));
        }
        if (filtro.getImporteHasta() == null || filtro.getImporteHasta().compareTo(BIG_DECIMAL_INFINITO) == 0) {
            cabecera.setImporteHasta(IMPORTE_VACIO);
            filtro.setImporteHasta(null);
        } else {
            cabecera.setImporteHasta(ISBANStringUtils.formatearSaldo(filtro.getImporteHasta()));
        }
        Boolean mostrarFiltroImportes = filtro.getImporteDesde() != null || filtro.getImporteHasta() != null;

        Boolean mostrarFiltroPalabraClave = StringUtils.isNotBlank(filtro.getPalabraClave());

        String tipoCuenta = filtro.getTipoCuenta().getDescripcion();

        cabecera.setPalabraClave(
                StringUtils.isEmpty(filtro.getPalabraClave()) ? StringUtils.EMPTY : filtro.getPalabraClave());
        cabecera.setNumeroCuenta(filtro.getNumeroCuenta());
        cabecera.setTipoCuenta(tipoCuenta.toUpperCase().substring(0, 1).concat(tipoCuenta.toLowerCase().substring(1)));

        cabecera.setMostrarFiltroFecha(mostrarFiltroFecha);
        cabecera.setMostrarFiltroImportes(mostrarFiltroImportes);
        cabecera.setMostrarFiltroPalabraClave(mostrarFiltroPalabraClave);
        cabecera.setMostrarSaldoAperturado(filtro.getTipoCuenta().equals(TipoCuenta.CUENTA_UNICA) ? true : false);
        cabecera.setHasCtaCte(tieneCuentaCorriente);

        if (filtro.getMoneda() != null) {
            cabecera.setMoneda(filtro.getMoneda().equals(DivisaEnum.PESO) ? "Pesos" : "D\u00F3lares");
        } else {
            cabecera.setMoneda("");
        }
        cabecera.setDispositivo(dispositivo);

        cabecera.setIsConvenioPAS(false);

        return cabecera;
    }

	private Boolean importeHastaDistintoDeNullOCero (BigDecimal importeHasta) {
		
		return importeHasta == null || importeHasta.compareTo(BIG_DECIMAL_INFINITO) == 0;
		
	}
    
    /**
	 * Crear item excel.
	 *
	 * @param itemMovimiento
	 *            the item movimiento
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return the movimiento excel item
	 */
    private MovimientoExcelItem crearItemExcel(ItemMovimiento itemMovimiento,
            ConsultaUltimosMovimientos consultaUltimosMovimientos) {
        MovimientoExcelItem movimiento = new MovimientoExcelItem();
        movimiento.setDescripcion(itemMovimiento.getDetalle());
        movimiento.setFechaHora(itemMovimiento.getFecha());

        movimiento.setIsCajaDeAhoroEnPesos(itemMovimiento.getIsCajaDeAhoroEnPesos());
        movimiento.setIsCuentaCorrienteEnPesos(itemMovimiento.getIsCuentaCorrienteEnPesos());
        movimiento.setIsMovimientoEnDolares(consultaUltimosMovimientos.getMoneda().equals(DivisaEnum.DOLAR));

        movimiento.setImporte(Double.parseDouble(formatearValores(itemMovimiento.getImporte())));
        movimiento.setSaldo(Double.parseDouble(formatearValores(itemMovimiento.getSaldo())));

        movimiento.setSucursalOrigen(itemMovimiento.getNumeroSucursal());

        movimiento.setReferencia(itemMovimiento.getNumeroReferencia());
        movimiento.setIsDelDia(itemMovimiento.getIsDelDia());

        return movimiento;
    }

    /**
	 * Formatear valores.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
    private String formatearValores(String valor) {
        String valorFormateado = StringUtils.replace(valor, ".", "");
        return StringUtils.replace(valorFormateado, ",", ".");
    }
    
}
