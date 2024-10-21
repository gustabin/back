/**
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.PrestamoHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomePrestamosView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPreaprobadoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.InfoPrestamosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PrestamoHomeManagerImpl.
 *
 * @author B039543
 */
@Component
public class PrestamoHomeManagerImpl extends AbstractHomeManager implements PrestamoHomeManager {

	/** The Constant SUPER_PRESTAMO_DETALLE. */
	private static final String SUPER_PRESTAMO_DETALLE = "Pr\u00E9stamo";

	/** The Constant PRESTAMOS_HIPOTECARIOS_TITULO. */
	private static final String PRESTAMOS_HIPOTECARIOS_TITULO = "S\u00DAPER PR\u00C9STAMO HIPOTECARIO";

	/** The Constant PRESTAMOS_PERSONALES_TITULO. */
	private static final String PRESTAMOS_PERSONALES_TITULO = "S\u00DAPER PR\u00C9STAMO PERSONAL";

	/** The Constant PRESTAMOS_PRENDARIOS_TITULO. */
	private static final String PRESTAMOS_PRENDARIOS_TITULO = "S\u00DAPER PR\u00C9STAMO PRENDARIO";

	/** The Constant TEXTO_LINK_CAJA_PRESTAMO. */
	private static final String TEXTO_LINK_CAJA_PRESTAMO = "Ver más";

	/** The Constant TEXTO_LINK_CAJA_PRESTAMO. */
	private static final String TEXTO_LINK_CAJA_PRESTAMO_LINEA_CREDITICIA = "Solicitar";

	/** The Constant PRESTAMOS_PERSONALES_TITULO. */
	private static final String PRESTAMOS_PERSONALES_ENCABEZADO_LINEA_CREDITICIA = "Préstamos";

	/** The Constant PRESTAMOS_PERSONALES_TITULO. */
	private static final String PRESTAMOS_PERSONALES_TITULO_LINEA_CREDITICIA = "Préstamo preacordado";

	/** Constante PRESTAMOS_DISPONIBLES*/
	private static final String PRESTAMOS_DISPONIBLES = "Disponible";

	/** Constante PRESTAMOS_DISPONIBLES*/
	private static final String PRESTAMOS_PREAPROBADO = "Préstamo preaprobado";

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The prestamo BO. */
	@Autowired
	private PrestamoBO prestamoBO;

	/** The prestamo preaprobado BO. */
	@Autowired
	private PrestamoPreaprobadoBO prestamoPreaprobadoBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	@Autowired
	private ModuloPermisoBOImpl moduloPermisoBOImpl;

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.
	 * AbstractHomeManager#obtenerCajas()
	 */
	@Override
	protected GrupoCajaHomeView obtenerCajas() {

		Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		List<Caja> cajas = new ArrayList<Caja>();
		String prestamo = SUPER_PRESTAMO_DETALLE;
		Cliente cliente = sesionCliente.getCliente();
		InfoPrestamosDTO info = prestamoBO.obtenerCantidadPrestamosPorClase(cliente);
		if (info.getPrestamosHipotecarios() > 0) {
			CajaHomePrestamosView caja = getCajaHipotecarios();
			String plural = "";
			if (info.getPrestamosHipotecarios() > 1) {
				plural = "s";
			}
			caja.setCantidad(info.getPrestamosHipotecarios());
			caja.setTitulo(prestamo + plural);
			cajas.add(caja);
		}
		if (info.getPrestamosPrendarios() > 0) {
			CajaHomePrestamosView caja = getCajaPrendarios();
			String plural = "";
			if (info.getPrestamosPrendarios() > 1) {
				plural = "s";
			}
			caja.setCantidad(info.getPrestamosPrendarios());
			caja.setTitulo(prestamo + plural);
			cajas.add(caja);
		}
		// buscamos si hay ofertas de prestamo preaprobados para el nup, tomando el de mayor importe
		BigDecimal importeMaximo = validarDisponiblePrestamoPreaprobado();

		if (info.getPrestamosPersonales() > 0
				|| !StringUtils.isEmpty(sesionParametros.getOfertasComerciales().getLineaCrediticia())
				|| importeMaximo !=null) {

			if (info.getPrestamosPersonales() > 0 && (StringUtils.isEmpty(sesionParametros.getOfertasComerciales().getLineaCrediticia())
					|| !StringUtils.isEmpty(sesionParametros.getOfertasComerciales().getLineaCrediticia()) && !validarSiLineaCrediticiaMayorAMil(sesionParametros.getOfertasComerciales().getLineaCrediticia()))
					 && importeMaximo == null) {
				CajaHomePrestamosView caja = getCajaPersonales();
				String plural = "";
				if (info.getPrestamosPersonales() > 1) {
					plural = "s";
				}
				caja.setCantidad(info.getPrestamosPersonales());
				caja.setTitulo(prestamo + plural);
				cajas.add(caja);
			} else if (!StringUtils.isEmpty(sesionParametros.getOfertasComerciales().getLineaCrediticia()) &&
				validarSiLineaCrediticiaMayorAMil(sesionParametros.getOfertasComerciales().getLineaCrediticia())) {
				CajaHomePrestamosView caja = getCajaPersonales();
				if(importeMaximo != null) {
					BigDecimal lineaCred = ISBANStringUtils.convertirABigDecimal(sesionParametros.getOfertasComerciales().getLineaCrediticia().replace("$", "").trim());
					caja.setLineaCrediticia(ISBANStringUtils.formatearSaldoSinAbsConDivisa(importeMaximo.add(lineaCred), DivisaEnum.PESO));
					caja.setEncabezado(PRESTAMOS_PERSONALES_ENCABEZADO_LINEA_CREDITICIA);
					caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO_LINEA_CREDITICIA);
					caja.setTitulo(PRESTAMOS_DISPONIBLES);
					sesionParametros.setMaxOfertaPrestamoPreaprobado(importeMaximo);
				}else {
					caja.setLineaCrediticia(sesionParametros.getOfertasComerciales().getLineaCrediticia());
					caja.setEncabezado(PRESTAMOS_PERSONALES_ENCABEZADO_LINEA_CREDITICIA);
					caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO_LINEA_CREDITICIA);
					caja.setTitulo(PRESTAMOS_PERSONALES_TITULO_LINEA_CREDITICIA);
				}
				cajas.add(caja);
			} else if(importeMaximo != null) {
				CajaHomePrestamosView caja = getCajaPersonales();
				caja.setLineaCrediticia(ISBANStringUtils.formatearSaldoSinAbsConDivisa(importeMaximo, DivisaEnum.PESO));
				caja.setEncabezado(SUPER_PRESTAMO_DETALLE);
				caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO_LINEA_CREDITICIA);
				caja.setTitulo(PRESTAMOS_PREAPROBADO);
				cajas.add(caja);
				sesionParametros.setMaxOfertaPrestamoPreaprobado(importeMaximo);
			}

		}

		GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
		grupoCajaHomeView.setCajas(cajas);
		grupoCajaHomeView.setSinError(Boolean.TRUE);
		return grupoCajaHomeView;
	}

	private BigDecimal validarDisponiblePrestamoPreaprobado() {

		if(moduloPermisoBOImpl.tienePermisoMostrar(AccionController.SOLICITUD_PRESTAMO_PREAPROBADO_MONOPRODUCTO) && sesionParametros.getMaxOfertaPrestamoPreaprobado() == null) {
			PrestamoPermitidoEntity resultado = prestamoPreaprobadoBO.getMaxImporteOfertaPrestamoPreaprobado(sesionCliente.getCliente()).getRespuesta();
			if (resultado != null) {
				return resultado.getMaxImpPrest() != null ? ISBANStringUtils.stringToBigDecimal(resultado.getMaxImpPrest(), 13, 4, false) : null;
			}
		} else if(moduloPermisoBOImpl.tienePermisoMostrar(AccionController.SOLICITUD_PRESTAMO_PREAPROBADO_MONOPRODUCTO) && sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
			return sesionParametros.getMaxOfertaPrestamoPreaprobado();
		}
		return null;
	}

	private Boolean validarSiLineaCrediticiaMayorAMil(String lineaCrediticia) {

		Boolean esMayorAMil = Boolean.FALSE;
		String lineaCrediticiaFormateada = lineaCrediticia.replaceAll("\\$ ", "").replaceAll("\\.", "").replaceAll("\\,", "\\.");
		BigDecimal bigLinea = new BigDecimal(lineaCrediticiaFormateada);
		BigDecimal bigMil = new BigDecimal("1000");

		if (bigLinea.compareTo(bigMil) == (1)) {
			esMayorAMil = Boolean.TRUE;
		}

		return esMayorAMil;
	}


	/**
	 * Obtener la caja de calendario de pagos.
	 *
	 * @return the caja
	 */
	private CajaHomePrestamosView getCajaHipotecarios() {
		CajaHomePrestamosView caja = new CajaHomePrestamosView();
		caja.setEncabezado(WordUtils.capitalizeFully(PRESTAMOS_HIPOTECARIOS_TITULO));
		caja.setIsHipotecario(Boolean.TRUE);
		caja.setIsPrestamos(Boolean.TRUE);
		caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO);
		return caja;
	}

	/**
	 * Obtener la caja de calendario de pagos.
	 *
	 * @return the caja
	 */
	private CajaHomePrestamosView getCajaPersonales() {
		CajaHomePrestamosView caja = new CajaHomePrestamosView();
		caja.setEncabezado(WordUtils.capitalizeFully(PRESTAMOS_PERSONALES_TITULO));
		caja.setIsPersonal(Boolean.TRUE);
		caja.setIsPrestamos(Boolean.TRUE);
		caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO);
		return caja;
	}

	/**
	 * Obtener la caja de calendario de pagos.
	 *
	 * @return the caja
	 */
	private CajaHomePrestamosView getCajaPrendarios() {
		CajaHomePrestamosView caja = new CajaHomePrestamosView();
		caja.setEncabezado(WordUtils.capitalizeFully(PRESTAMOS_PRENDARIOS_TITULO));
		caja.setIsPrendario(Boolean.TRUE);
		caja.setIsPrestamos(Boolean.TRUE);
		caja.setTextoLink(TEXTO_LINK_CAJA_PRESTAMO);
		return caja;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
	 * aplicaGrupo()
	 */
	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		if (sesionCliente.getItemsRespuesta() != null) {
			for (ItemMensajeRespuesta item : sesionCliente.getItemsRespuesta()) {
				if (item.getTipoError().equals(TipoError.ERROR_ALTAIR_PRESTAMOS.getDescripcion())) {
					return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TABLERO_HOME,
							CodigoMensajeConstantes.ERROR_TABLERO_HOME);
				}
			}
		}
		return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
	 * obtenerAccion()
	 */
	@Override
	public AccionController obtenerAccion() {
		return AccionController.IR_HOME_PRESTAMO;
	}
}
