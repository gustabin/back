/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasDetalleBO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DatosParticipantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DetalleCuotaPrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DetalleImpuestoMonedaExtranjeraDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DetalleMensualImpuestosDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DetallePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.DetalleTarjetaCreditoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ParticipantesPLDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenCuentaInversionDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDetalleDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDetalleInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.exception.CopiarListaException;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.InversionesHelper;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;
import ar.com.santanderrio.obp.servicios.tenencias.view.DatosParticipantesPLView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleCuotasPrestamosView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleMensualImpuestosView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetallePlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleimpuestoMonedaExtranjeraView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ParticipantesPLView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ResumenCuentaInversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;

/**
 * The Class TenenciasDetalleManagerImpl.
 *
 * @author desa
 */
@Component("tenenciasDetalleManager")
public class TenenciasDetalleManagerImpl implements TenenciasDetalleManager {

	/** The Constant DET_TARJ_CREDITO *. */
	private static final int DET_TARJ_CREDITO = 1;

	/** The Constant DET_PRESTAMO *. */
	private static final int DET_PRESTAMO = 3;

	/** The Constant DET_INVERSIONES *. */
	private static final int DET_INVERSIONES = 5;

	/** The Constant DET_IMPUESTOS *. */
	private static final int DET_IMPUESTOS = 6;
	
	/** The Constant ANIO_PL *. */
	private static final int ANIO_PL = 2019;


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasDetalleManagerImpl.class);

	/** The Tenencias BO *. */
	@Autowired
	private TenenciasDetalleBO tenenciasDetalleBO;

	/** The response factory *. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The Constant ERROR_PARSEO_TENENCIAVIEW. */
	private static final String ERROR_PARSEO_TENENCIAVIEW = "Error al parsear TenenciasDetalleView";
	
	private int anioConsulta;

	/**
	 * Crea la consulta de todas los detalles de las tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TenenciasDetalleView> consultarAllDetalleTenencias(TenenciasDetalleInView viewRequest) {
		Respuesta<TenenciasDetalleView> respuesta = null;
		this.anioConsulta=Integer.parseInt(viewRequest.getAnioHasta());
		Respuesta<TenenciasDetalleDTO> tenenciasDetalleDTO = tenenciasDetalleBO
				.consultarDetalleTenenciasCompleto(createInDto(viewRequest));

		try {
			TenenciasDetalleView detalleView = new TenenciasDetalleView();

			if (tenenciasDetalleDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				this.setDetalleTarjetaCredito(detalleView, tenenciasDetalleDTO.getRespuesta().getDetalleTarjetaDTO());
				this.setDetalleCuotasPrestamos(detalleView,
						tenenciasDetalleDTO.getRespuesta().getDetalleCuotaPrestamoDTO());
				this.setDetallePlazoFijo(detalleView, tenenciasDetalleDTO.getRespuesta().getDetallePlazoFijoDTO());
				this.setResumenCuentaInversiones(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionDTO());
				this.setDetalleImpuestoMonedaExtranjera(detalleView,
						tenenciasDetalleDTO.getRespuesta().getDetalleImpuestoMonedaExtranjeraDTO());
				this.setDetalleMensualImpuestos(detalleView,
						tenenciasDetalleDTO.getRespuesta().getDetalleMensualImpuestosDTO());
				this.setResumenCuentaInversionesViewsMON(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionesMONDTO());
				this.setResumenCuentaInversionesViewsCEF(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionesCEFDTO());
				this.setResumenCuentaInversionesViewsBON(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionesBONDTO());
				this.setResumenCuentaInversionesViewsSHS(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionesSHSDTO());
				this.setResumenCuentaInversionesViewsFON(detalleView,
						tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionesFONDTO());
				this.setParticipantesViews(detalleView,tenenciasDetalleDTO.getRespuesta().getParticipantesDTO());
				respuesta = respuestaFactory.crearRespuestaOk(TenenciasDetalleView.class, detalleView);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleView.class, null);
			}
		} catch (CopiarListaException e) {
			LOGGER.error(ERROR_PARSEO_TENENCIAVIEW, e);
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleView.class, null);
		}
		return respuesta;
	}

	/**
	 * Sets the resumen cuenta inversiones views FON.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param resumenCuentaInversionesViewsFON
	 *            the resumen cuenta inversiones views FON
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversionesViewsFON(TenenciasDetalleView detalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionesViewsFON) throws CopiarListaException {
		List<ResumenCuentaInversionesView> detalleFondos = new ArrayList<ResumenCuentaInversionesView>();
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (resumenCuentaInversionesViewsFON != null) {
			for (ResumenCuentaInversionDTO fon : resumenCuentaInversionesViewsFON) {
				if (fon.getSucCtaOri() != null && fon.getNroCtaOri() != null) {
					fon.setNroCtaOri(TenenciasHelper.nroCuentaCanonico(fon.getSucCtaOri(), fon.getNroCtaOri()));
				} else {
					fon.setNroCtaOri("");
				}
				if(anioConsulta<ANIO_PL) {
					
					fon.setCotizacion(InversionesHelper.formatearSaldo6Decimales(new BigDecimal(fon.getCotizacion())));
					fon.setCantidad(InversionesHelper.formatearSaldo4Decimales(new BigDecimal(fon.getCantidad())));
					fon.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(fon.getImporte())));
					
				} 
				
				fon.setCuenta(TenenciasHelper.nroCuentaCanonico(fon.getCuenta()));
				String fcParseada = fon.getFecha();
				String flParseada = fon.getFecha_liq();
				try {
					Date fechaConstitucion = fechaFormat.parse(fon.getFecha());
					Date fechaLiquidacion =  anioConsulta < ANIO_PL ? fechaFormat.parse(fon.getFecha()) : fechaFormat.parse(fon.getFecha_liq());
					flParseada = ISBANStringUtils.formatearFecha(fechaLiquidacion);
					fcParseada = ISBANStringUtils.formatearFecha(fechaConstitucion);
				} catch (ParseException e) {
					fcParseada = fon.getFecha();
					flParseada = fon.getFecha_liq();
				}
				fon.setFecha(fcParseada);
				fon.setFecha_liq(flParseada);
			}
			TenenciasHelper.copiarLista(resumenCuentaInversionesViewsFON, detalleFondos,
					ResumenCuentaInversionesView.class);
			detalleView.setResumenCuentaInversionesViewsFON(detalleFondos);
		}
	}

	/**
	 * Sets the resumen cuenta inversiones views SHS.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param resumenCuentaInversionesViewsSHS
	 *            the resumen cuenta inversiones views SHS
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversionesViewsSHS(TenenciasDetalleView detalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionesViewsSHS) throws CopiarListaException {
		List<ResumenCuentaInversionesView> detalleAcciones = new ArrayList<ResumenCuentaInversionesView>();
		if (resumenCuentaInversionesViewsSHS != null) {
			TenenciasHelper.copiarLista(resumenCuentaInversionesViewsSHS, detalleAcciones,
					ResumenCuentaInversionesView.class);
			detalleView.setResumenCuentaInversionesViewsSHS(detalleAcciones);
		}
	}
	
	/**
	 * Sets the participantes.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param participantesView
	 *            the resumen cuenta inversiones views SHS
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setParticipantesViews(TenenciasDetalleView detalleView,
			List<DatosParticipantesDTO> datoCuentaParticipante) throws CopiarListaException {
		List<DatosParticipantesPLView> cuentPart = new ArrayList<DatosParticipantesPLView>();
		if (datoCuentaParticipante != null) {
			List<ParticipantesPLDTO> lPartiDTO = new ArrayList<ParticipantesPLDTO>();
			for (DatosParticipantesDTO itemCtaDTO : datoCuentaParticipante) {
				for (ParticipantesPLDTO itemPartDTO : itemCtaDTO.getParticipantesDTO()) {
					lPartiDTO.add(itemPartDTO);
				}
			}
			List<DatosParticipantesPLView> lDatosCtaPartView = new ArrayList<DatosParticipantesPLView>();
			TenenciasHelper.copiarLista(datoCuentaParticipante, cuentPart, DatosParticipantesPLView.class);
			List<ParticipantesPLView> lPartiView = new ArrayList<ParticipantesPLView>();
			TenenciasHelper.copiarLista(lPartiDTO, lPartiView, ParticipantesPLView.class);
			for (DatosParticipantesPLView datosCtaView : cuentPart) {
				List<ParticipantesPLView> lRowPartiVie = new ArrayList<ParticipantesPLView>();
				for (ParticipantesPLView itemPart : lPartiView) {
					if (datosCtaView.getNumeroCuenta().equals(itemPart.getNumeroContrato())) {
						lRowPartiVie.add(itemPart); 
					}
				}
				datosCtaView.setParticipantesView(lRowPartiVie);
				lDatosCtaPartView.add(datosCtaView);
			}
			detalleView.setParticipantesViews(lDatosCtaPartView);
		}

	}

	/**
	 * Sets the resumen cuenta inversiones views BON.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param resumenCuentaInversionesViewsBON
	 *            the resumen cuenta inversiones views BON
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversionesViewsBON(TenenciasDetalleView detalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionesViewsBON) throws CopiarListaException {
		List<ResumenCuentaInversionesView> detalleBonos = new ArrayList<ResumenCuentaInversionesView>();
		if (resumenCuentaInversionesViewsBON != null) {
			TenenciasHelper.copiarLista(resumenCuentaInversionesViewsBON, detalleBonos,
					ResumenCuentaInversionesView.class);
			detalleView.setResumenCuentaInversionesViewsBON(detalleBonos);
		}
	}

	/**
	 * Sets the resumen cuenta inversiones views CEF.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param resumenCuentaInversionesViewsCEF
	 *            the resumen cuenta inversiones views CEF
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversionesViewsCEF(TenenciasDetalleView detalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionesViewsCEF) throws CopiarListaException {
		List<ResumenCuentaInversionesView> detalleCEF = new ArrayList<ResumenCuentaInversionesView>();
		if (resumenCuentaInversionesViewsCEF != null) {
			TenenciasHelper.copiarLista(resumenCuentaInversionesViewsCEF, detalleCEF,
					ResumenCuentaInversionesView.class);
			detalleView.setResumenCuentaInversionesViewsCEF(detalleCEF);
		}
	}

	/**
	 * Sets the resumen cuenta inversiones views MON.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param resumenCuentaInversionesViewsMON
	 *            the resumen cuenta inversiones views MON
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversionesViewsMON(TenenciasDetalleView detalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionesViewsMON) throws CopiarListaException {
		List<ResumenCuentaInversionesView> detalleMonedaExtranjera = new ArrayList<ResumenCuentaInversionesView>();
		if (resumenCuentaInversionesViewsMON != null) {
			TenenciasHelper.copiarLista(resumenCuentaInversionesViewsMON, detalleMonedaExtranjera,
					ResumenCuentaInversionesView.class);
			detalleView.setResumenCuentaInversionesViewsMON(detalleMonedaExtranjera);
		}
	}

	/**
	 * consultar Tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta tenenciasView.
	 */
	@Override
	public Respuesta<TenenciasDetalleView> consultarDetalleTenencias(TenenciasDetalleInView viewRequest) {

		Respuesta<TenenciasDetalleView> respuesta = null;
		this.anioConsulta=Integer.parseInt(viewRequest.getAnioHasta());
		Respuesta<TenenciasDetalleDTO> tenenciasDetalleDTO = tenenciasDetalleBO
				.consultarDetalleTenencias(createInDto(viewRequest), viewRequest.getTipoDetalle());

		try {
			if (tenenciasDetalleDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				TenenciasDetalleView detalleView = new TenenciasDetalleView();
				switch (viewRequest.getTipoDetalle()) {
				case DET_TARJ_CREDITO:
					break;
				case DET_PRESTAMO:
					setDetalleCuotasPrestamos(detalleView,
							tenenciasDetalleDTO.getRespuesta().getDetalleCuotaPrestamoDTO());
					break;
				case DET_INVERSIONES:
					if (viewRequest.getEspeTipo().isEmpty()) {
						setDetallePlazoFijo(detalleView, tenenciasDetalleDTO.getRespuesta().getDetallePlazoFijoDTO());
					}
					setResumenCuentaInversiones(detalleView,
							tenenciasDetalleDTO.getRespuesta().getResumenCuentaInversionDTO());
					break;
				case DET_IMPUESTOS:
					setDetalleTarjetaCredito(detalleView, tenenciasDetalleDTO.getRespuesta().getDetalleTarjetaDTO());
					setDetalleImpuestoMonedaExtranjera(detalleView,
							tenenciasDetalleDTO.getRespuesta().getDetalleImpuestoMonedaExtranjeraDTO());
					setDetalleMensualImpuestos(detalleView,
							tenenciasDetalleDTO.getRespuesta().getDetalleMensualImpuestosDTO());
					break;
				}

				respuesta = respuestaFactory.crearRespuestaOk(TenenciasDetalleView.class, detalleView);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleView.class, null);
			}
		} catch (CopiarListaException e) {
			LOGGER.error(ERROR_PARSEO_TENENCIAVIEW, e);
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleView.class, null);
		}

		return respuesta;
	}

	/**
	 * crear un DTO para llamar al BO.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return dto.
	 */
	private TenenciasDetalleInDTO createInDto(TenenciasDetalleInView viewRequest) {
		TenenciasDetalleInDTO inDTO = new TenenciasDetalleInDTO();
		inDTO.setAnioDesde(viewRequest.getAnioDesde());
		inDTO.setAnioHasta(viewRequest.getAnioHasta());
		inDTO.setNup(sesionCliente.getCliente().getNup());
		inDTO.setpEspeTipo(viewRequest.getEspeTipo());
		return inDTO;
	}

	/**
	 * Setea la lista de DetalleMensualImpuestosDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param detalleMensualImpuestosDTO
	 *            the detalle mensual impuestos DTO
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setDetalleMensualImpuestos(TenenciasDetalleView tenenciasDetalleView,
			List<DetalleMensualImpuestosDTO> detalleMensualImpuestosDTO) throws CopiarListaException {
		List<DetalleMensualImpuestosView> detalleMensualImpuestosViews = new ArrayList<DetalleMensualImpuestosView>();
		if (detalleMensualImpuestosDTO != null) {
			TenenciasHelper.copiarLista(detalleMensualImpuestosDTO, detalleMensualImpuestosViews,
					DetalleMensualImpuestosView.class);
			for (DetalleMensualImpuestosView dmiv : detalleMensualImpuestosViews) {
				dmiv.setCuenta(TenenciasHelper.nroCuentaCanonico(dmiv.getPecodofi(), dmiv.getCuenta()));
				TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(dmiv.getTipoCuenta());
				dmiv.setTipoCuenta(tipoCuenta.getDescripcionConMoneda());
				dmiv.setImpComputable01(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable01().replace(",", ".")));
				dmiv.setImpComputable02(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable02().replace(",", ".")));
				dmiv.setImpComputable03(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable03().replace(",", ".")));
				dmiv.setImpComputable04(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable04().replace(",", ".")));
				dmiv.setImpComputable05(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable05().replace(",", ".")));
				dmiv.setImpComputable06(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable06().replace(",", ".")));
				dmiv.setImpComputable07(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable07().replace(",", ".")));
				dmiv.setImpComputable08(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable08().replace(",", ".")));
				dmiv.setImpComputable09(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable09().replace(",", ".")));
				dmiv.setImpComputable10(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable10().replace(",", ".")));
				dmiv.setImpComputable11(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable11().replace(",", ".")));
				dmiv.setImpComputable12(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpComputable12().replace(",", ".")));
				dmiv.setImpCredito01(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito01().replace(",", ".")));
				dmiv.setImpCredito02(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito02().replace(",", ".")));
				dmiv.setImpCredito03(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito03().replace(",", ".")));
				dmiv.setImpCredito04(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito04().replace(",", ".")));
				dmiv.setImpCredito05(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito05().replace(",", ".")));
				dmiv.setImpCredito06(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito06().replace(",", ".")));
				dmiv.setImpCredito07(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito07().replace(",", ".")));
				dmiv.setImpCredito08(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito08().replace(",", ".")));
				dmiv.setImpCredito09(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito09().replace(",", ".")));
				dmiv.setImpCredito10(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpCredito10().replace(",", ".")));
				dmiv.setImpDebito01(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito01().replace(",", ".")));
				dmiv.setImpDebito02(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito02().replace(",", ".")));
				dmiv.setImpDebito03(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito03().replace(",", ".")));
				dmiv.setImpDebito04(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito04().replace(",", ".")));
				dmiv.setImpDebito05(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito05().replace(",", ".")));
				dmiv.setImpDebito06(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito06().replace(",", ".")));
				dmiv.setImpDebito07(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito07().replace(",", ".")));
				dmiv.setImpDebito08(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito08().replace(",", ".")));
				dmiv.setImpDebito09(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito09().replace(",", ".")));
				dmiv.setImpDebito10(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito10().replace(",", ".")));
				dmiv.setImpDebito11(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito11().replace(",", ".")));
				dmiv.setImpDebito12(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getImpDebito12().replace(",", ".")));
				dmiv.setTotalImpComputable(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getTotalImpComputable().replace(",", ".")));
				dmiv.setTotalImpCredito(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getTotalImpCredito().replace(",", ".")));
				dmiv.setTotalImpDebito(ISBANStringUtils.formatearConComaYDosDecimales(dmiv.getTotalImpDebito().replace(",", ".")));
			}
			tenenciasDetalleView.setDetalleMensualImpuestosViews(detalleMensualImpuestosViews);
		}
	}

	/**
	 * Setea la lista de ResumenCuentaInversionDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param resumenCuentaInversionDTO
	 *            the resumen cuenta inversion DTO
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setResumenCuentaInversiones(TenenciasDetalleView tenenciasDetalleView,
			List<ResumenCuentaInversionDTO> resumenCuentaInversionDTO) throws CopiarListaException {
		List<ResumenCuentaInversionesView> resumenCuentaInversionesViews = new ArrayList<ResumenCuentaInversionesView>();
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (resumenCuentaInversionDTO != null) {
			for (ResumenCuentaInversionDTO resumen : resumenCuentaInversionDTO) {
				String fcParseada = resumen.getFecha();
				try {
					Date fechaConstitucion = fechaFormat.parse(resumen.getFecha());
					fcParseada = ISBANStringUtils.formatearFecha(fechaConstitucion);
				} catch (ParseException e) {
					fcParseada = resumen.getFecha();
				}
				resumen.setCuenta(TenenciasHelper.nroCuentaCanonico(resumen.getCuenta()));
				resumen.setFecha(fcParseada);
				if (resumen.getSucCtaOri() != null && resumen.getNroCtaOri() != null) {
					resumen.setNroCtaOri(
							TenenciasHelper.nroCuentaCanonico(resumen.getSucCtaOri(), resumen.getNroCtaOri()));
				} else {
					resumen.setNroCtaOri("");
				}
				
				if(anioConsulta>=ANIO_PL) {
					
					resumen.setCotizacion(
							ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getCotizacion(), 4));
					resumen.setCantidad(ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getCantidad(), 2));
					resumen.setImporte(ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getImporte(),2));
					resumen.setGastoEntrada(ISBANStringUtils.formatearConComaYDosDecimales2(resumen.getGastoEntrada()));
					if (resumen.getEspeTipo().equals("FCR")) {
					resumen.setGastoSalida(ISBANStringUtils.formatearConComaYDosDecimales2(resumen.getGastoSalida()));
					} else {
						resumen.setGastoSalida("0");
					}
				} else {
					resumen.setCotizacion(
							ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getCotizacion(), 4));
					resumen.setCantidad(ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getCantidad(), 2));
					resumen.setImporte(ISBANStringUtils.formatearConComaYVariosDecimales2(resumen.getImporte(),2));
					resumen.setGastoEntrada("0");
					resumen.setGastoSalida("0");
					resumen.setAmortizacion("0");
					resumen.setImporteBruto("0");
					resumen.setImpuestos("0");
					resumen.setMonedaTransaccion("");
					resumen.setNroBoleto("");
					resumen.setNroOrden("");
					resumen.setRenta("");
					
				}
			}
			TenenciasHelper.copiarLista(resumenCuentaInversionDTO, resumenCuentaInversionesViews,
					ResumenCuentaInversionesView.class);
			tenenciasDetalleView.setResumenCuentaInversionesViews(resumenCuentaInversionesViews);
		}
	}

	/**
	 * Setea la lista de DetallePlazoFijoDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param detallePlazoFijoDTO
	 *            the detalle plazo fijo DTO
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setDetallePlazoFijo(TenenciasDetalleView tenenciasDetalleView,
			List<DetallePlazoFijoDTO> detallePlazoFijoDTO) throws CopiarListaException {
		List<DetallePlazoFijoView> detallePlazoFijoViews = new ArrayList<DetallePlazoFijoView>();
		if (detallePlazoFijoDTO != null) {
			TenenciasHelper.copiarLista(detallePlazoFijoDTO, detallePlazoFijoViews, DetallePlazoFijoView.class);

			SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (DetallePlazoFijoView pf : detallePlazoFijoViews) {
				String fcParseada = pf.getFechaConstitucion();
				String fvParseada = pf.getFechaVencimiento();
				String fscParseada= pf.getFechaSolicitudCancelacion();
				String fmcParseada= pf.getFechaMinimaCancelacion();
				try {
					if (anioConsulta< ANIO_PL) {
						pf.setFechaSolicitudCancelacion(pf.getFechaConstitucion());
						pf.setFechaMinimaCancelacion(pf.getFechaVencimiento());
						pf.setInteresesTotales("0");
						pf.setFechaLiquidacion(pf.getFechaVencimiento());
						pf.setFrecuenciaCobroInteres("0");
						pf.setFrecuenciaPrecancelacion("0");
						pf.setImpuestos("0");
						pf.setModalidad("No aplica");
						pf.setTasaEfectivaAnual("0");
						pf.setTasaNominalAnual("0");
						pf.setCapitalInvertido("0");
					}
					Date fechaConstitucion = fechaFormat.parse(pf.getFechaConstitucion());
					Date fechaVencimiento = fechaFormat.parse(pf.getFechaVencimiento());
					Date fecSoCancelacion = fechaFormat.parse(pf.getFechaSolicitudCancelacion());
					Date fecMiCancelacion = fechaFormat.parse(pf.getFechaMinimaCancelacion());
					fcParseada = ISBANStringUtils.formatearFecha(fechaConstitucion);
					fvParseada = ISBANStringUtils.formatearFecha(fechaVencimiento);
					fscParseada = ISBANStringUtils.formatearFecha(fecSoCancelacion);
					fmcParseada= ISBANStringUtils.formatearFecha(fecMiCancelacion);
				} catch (ParseException e) {
					fcParseada = pf.getFechaConstitucion();
					fvParseada = pf.getFechaVencimiento();
					fscParseada= pf.getFechaSolicitudCancelacion();
					fmcParseada= pf.getFechaMinimaCancelacion();
				}

				pf.setCuentaCreditoNro(
						TenenciasHelper.nroCuentaCanonico(pf.getCuentaCreditoSuc(), pf.getCuentaCreditoNro()));
				pf.setCuentaDebitoNro(
						TenenciasHelper.nroCuentaCanonico(pf.getCuentaDebitoSuc(), pf.getCuentaDebitoNro()));
				pf.setFechaConstitucion(fcParseada);
				pf.setFechaVencimiento(fvParseada);
				pf.setFechaSolicitudCancelacion(fscParseada);
				pf.setFechaMinimaCancelacion(fmcParseada);
				pf.setInteresesTotales(ISBANStringUtils.formatearSaldo(new BigDecimal(pf.getInteresesTotales())));
				pf.setIntCobrado(ISBANStringUtils.formatearSaldo(new BigDecimal(pf.getIntCobrado())));
				pf.setIntDevengado(ISBANStringUtils.formatearSaldo(new BigDecimal(pf.getIntDevengado())));
				pf.setAjuCobrado(Double.valueOf(pf.getAjuCobrado()) > 0 ? ISBANStringUtils.formatearSaldo(new BigDecimal(pf.getAjuCobrado())) : null);
				pf.setCapitalInvertido(ISBANStringUtils.formatearSaldo(new BigDecimal(pf.getCapitalInvertido())));
			}

			tenenciasDetalleView.setDetallePlazoFijoViews(detallePlazoFijoViews);

		}
	}

	/**
	 * Setea la lista de DetalleCuotaPrestamoDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param detalleCuotaPrestamoDTO
	 *            the detalle cuota prestamo DTO
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setDetalleCuotasPrestamos(TenenciasDetalleView tenenciasDetalleView,
			List<DetalleCuotaPrestamoDTO> detalleCuotaPrestamoDTO) throws CopiarListaException {
		List<DetalleCuotasPrestamosView> detalleCuotasPrestamosViews = new ArrayList<DetalleCuotasPrestamosView>();
		if (detalleCuotaPrestamoDTO != null) {
			TenenciasHelper.copiarLista(detalleCuotaPrestamoDTO, detalleCuotasPrestamosViews,
					DetalleCuotasPrestamosView.class);
			tenenciasDetalleView.setDetalleCuotasPrestamosViews(detalleCuotasPrestamosViews);
		}

		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (DetalleCuotasPrestamosView dcpv : detalleCuotasPrestamosViews) {
			dcpv.setCapital(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getCapital())));
			dcpv.setComisiones(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getComisiones())));
			dcpv.setGastos(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getGastos())));
			dcpv.setImporteCuota(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getImporteCuota())));
			dcpv.setImpuestos(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getImpuestos())));
			dcpv.setIntComp(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getIntComp())));
			dcpv.setIntMora(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getIntMora())));
			dcpv.setSdoAntSinAjustar(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getSdoAntSinAjustar())));
			dcpv.setSeguros(ISBANStringUtils.formatearSaldo(new BigDecimal(dcpv.getSeguros())));
			String fcParseada = dcpv.getFechaVto();
			try {
				Date fechaConstitucion = fechaFormat.parse(dcpv.getFechaVto());
				fcParseada = ISBANStringUtils.formatearFecha(fechaConstitucion);
			} catch (ParseException e) {
				fcParseada = dcpv.getFechaVto();
			}
			dcpv.setFechaVto(fcParseada);
		}
	}

	/**
	 * Setea la lista de DetalleImpuestoMonedaExtranjeraDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param detalleImpuestoMonedaExtranjeraDTO
	 *            the detalle impuesto moneda extranjera DTO
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setDetalleImpuestoMonedaExtranjera(TenenciasDetalleView tenenciasDetalleView,
			List<DetalleImpuestoMonedaExtranjeraDTO> detalleImpuestoMonedaExtranjeraDTO) throws CopiarListaException {
		List<DetalleimpuestoMonedaExtranjeraView> detalleimpuestoMonedaExtranjeraViews = new ArrayList<DetalleimpuestoMonedaExtranjeraView>();
		if (detalleImpuestoMonedaExtranjeraDTO != null) {
			TenenciasHelper.copiarLista(detalleImpuestoMonedaExtranjeraDTO, detalleimpuestoMonedaExtranjeraViews,
					DetalleimpuestoMonedaExtranjeraView.class);
			tenenciasDetalleView.setDetalleimpuestoMonedaExtranjeraViews(detalleimpuestoMonedaExtranjeraViews);
		}
	}

	/**
	 * Setea la lista de DetalleTarjetaDTO.
	 *
	 * @param tenenciasDetalleView
	 *            the tenencias detalle view
	 * @param detalleTarjetaDTOs
	 *            the detalle tarjeta DT os
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private void setDetalleTarjetaCredito(TenenciasDetalleView tenenciasDetalleView,
			List<DetalleTarjetaCreditoDTO> detalleTarjetaDTOs) throws CopiarListaException {
		List<DetalleTarjetaCreditoView> detalleTarjetaCreditoViews = new ArrayList<DetalleTarjetaCreditoView>();
		if (detalleTarjetaDTOs != null) {
			TenenciasHelper.copiarLista(detalleTarjetaDTOs, detalleTarjetaCreditoViews,
					DetalleTarjetaCreditoView.class);
			tenenciasDetalleView.setDetalleTarjetaCreditoViews(detalleTarjetaCreditoViews);
		}
	}

}
