/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConceptosTipo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ConceptosTipo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.DetalleTrfImagen;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.Operacion;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.Rechazo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Banco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.common.EstadoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoPorTipoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ComexCanalesBOImpl.
 *
 * @author IT Resources
 */
@Component
public class ComexCanalesBOImpl implements ComexCanalesBO {

	/** The comex canales dao. */
	@Autowired
	private ComexCanalesDAO comexCanalesDAO;

	/** The comex consultas dao. */
	@Autowired
	private ComexConsultasDAO comexConsultasDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The Constant INICIO_DESCRIPCION_CONCEPTO. */
	private static final int INICIO_DESCRIPCION_CONCEPTO = 3;

	/** The Constant SWIFT. */
	private static final String SWIFT = "SWIFT";

	/** The Constant ABA. */
	private static final String ABA = "ABA";

	/** The Constant CUENTAS_UNICAS. */
	private static final String CUENTAS_UNICAS = "(-10-|-09-)";
	
	/** The Constant CUENTA_UNICA_PESOS. */
	private static final String CUENTA_UNICA_PESOS = "-09-";
	
	/** The Constant CUENTA_UNICA_DOLARES. */
	private static final String CUENTA_UNICA_DOLARES = "-10-";
	
	/** The Constant CUENTA_UNICA. */
	private static final String CUENTA_UNICA = "-02-";

	/** The Constant ERROR_I07. */
	private static final String ERROR_I07 = "I07_0001";

	/** The Constant ERROR_A07. */
	private static final String ERROR_A07 = "A07_0001";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfExt.bo.ComexCanalesBO#
	 * consultaDetalleTrf(ar.com.santanderrio.obp.servicios.comex.transfExt.dto.
	 * ConsultaDetalleTrfOBPInDTO)
	 */
	@Override
	public Respuesta<ConsultaDetalleTrfOBPOutDTO> consultaDetalleTrf(ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO, ConsultaOperacionesView operacion) {
		ConsultaDetalleTrfOBPResponse response;
		ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity = createConsultaDetalleTrfOBPInEntity(consultaDetalleTrfOBPInDTO);
		ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO = null;
		try {
			response = comexCanalesDAO.consultaDetalleTrf(consultaDetalleTrfOBPInEntity);
			consultaDetalleTrfOBPOutDTO = createConsultaDetalleTrfOBPOutDTO(response, operacion);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(ConsultaDetalleTrfOBPOutDTO.class, consultaDetalleTrfOBPOutDTO);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#consultaConceptoPorTipo()
	 */
	@Override
	public Respuesta<List<ConceptoPorTipoOutDTO>> consultaConceptoPorTipo(ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity) {
		ConceptosPorTipoResponse response;
		try {
			response = comexCanalesDAO.consultaConceptosPorTipo(consultaConceptosPorTipoInEntity);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		List<ConceptoPorTipoOutDTO> conceptosPorTipoOutDTO = createConceptosPorTipoOutDTO(response);
		return respuestaFactory.crearRespuestaOk(conceptosPorTipoOutDTO);
	}

	/**
	 * Creates the consulta detalle trf OBP in entity.
	 *
	 * @param consultaDetalleTrfOBPInDTO
	 *            the consulta detalle trf OBP in DTO
	 * @return the consulta detalle trf OBP in entity
	 */
	private ConsultaDetalleTrfOBPInEntity createConsultaDetalleTrfOBPInEntity(ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO) {
		ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity = new ConsultaDetalleTrfOBPInEntity();
		consultaDetalleTrfOBPInEntity.setNroTransferencia(consultaDetalleTrfOBPInDTO.getNroTransferencia().intValue());
		return consultaDetalleTrfOBPInEntity;
	}

	/**
	 * Creates the consulta detalle trf OBP out DTO.
	 *
	 * @param response
	 *            the response
	 * @param operacion
	 *            the operacion
	 * @return the consulta detalle trf OBP out DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaDetalleTrfOBPOutDTO createConsultaDetalleTrfOBPOutDTO(ConsultaDetalleTrfOBPResponse response, ConsultaOperacionesView operacion) throws DAOException {
		ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO = new ConsultaDetalleTrfOBPOutDTO();
		consultaDetalleTrfOBPOutDTO.setImporteTransferencia(ISBANStringUtils.formatearSaldo(response.getImporteTransferencia().getValue()));
		consultaDetalleTrfOBPOutDTO.setCodMoneda(response.getMoneda().getValue());
		consultaDetalleTrfOBPOutDTO.setEstadoTransferencia(response.getEstadoTransferencia().getValue());
		consultaDetalleTrfOBPOutDTO.setFechaOperacion(operacion.getFechaOperacion());

		setDatosBeneficiario(consultaDetalleTrfOBPOutDTO, response);
		setDatosTransferencia(consultaDetalleTrfOBPOutDTO, response, operacion);
		setDocumentacionAdjunta(consultaDetalleTrfOBPOutDTO, response);
		List<Rechazo> listRechazo = response.getRechazos().getValue().getRechazo();
		ListIterator<Rechazo> itRechazo = listRechazo.listIterator();
		//Solo viene un rechazo en la lista.
		while (itRechazo.hasNext()) {
			Rechazo rechazo = itRechazo.next();
			consultaDetalleTrfOBPOutDTO.setMotivoRechazo(rechazo.getMotivoRechazo().getValue());
			consultaDetalleTrfOBPOutDTO.setDescripcionMotivo(rechazo.getObservaciones().getValue());
		}
		return consultaDetalleTrfOBPOutDTO;
	}

	/**
	 * Sets the documentacion adjunta.
	 *
	 * @param consultaDetalleTrfOBPOutDTO
	 *            the consulta detalle trf OBP out DTO
	 * @param response
	 *            the response
	 */
	private void setDocumentacionAdjunta(ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO, ConsultaDetalleTrfOBPResponse response) {
		for (DetalleTrfImagen index : response.getImagenes().getValue().getDetalleTrfImagen()) {
			ReporteView reporteView = new ReporteView();
			reporteView.setNombre(index.getNombreArchivo().getValue());
			reporteView.setTipoArchivo(index.getTipoArchivo().getValue());
			reporteView.setId(Integer.toString(index.getIdImagen().getValue()));
			consultaDetalleTrfOBPOutDTO.getArchivos().add(reporteView);
		}
	}

	/**
	 * Sets the datos transferencia.
	 *
	 * @param consultaDetalleTrfOBPOutDTO
	 *            the consulta detalle trf OBP out DTO
	 * @param response
	 *            the response
	 * @param operacion
	 *            the operacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void setDatosTransferencia(ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO, ConsultaDetalleTrfOBPResponse response, ConsultaOperacionesView operacion) throws DAOException {
		if (response.getConcepto().getValue().length() > INICIO_DESCRIPCION_CONCEPTO) {
			consultaDetalleTrfOBPOutDTO.setConceptoDescripcion(response.getConcepto().getValue().substring(INICIO_DESCRIPCION_CONCEPTO, response.getConcepto().getValue().length()).trim());
		}
		consultaDetalleTrfOBPOutDTO.setConceptoCodigo(response.getCodigoConcepto().getValue());
		consultaDetalleTrfOBPOutDTO.setOrigen(response.getCtaDebito().getValue());
		consultaDetalleTrfOBPOutDTO.setDestino(response.getCuentaBeneficiario().getValue());
		consultaDetalleTrfOBPOutDTO.setDestinoBancoIntermediario(response.getCuentaBcoIntermediario().getValue());
		if (response.getGastos().getValue() != null) {
			consultaDetalleTrfOBPOutDTO.setGastoACargo(response.getGastos().getValue().toString());

		}

		setBancoBeneficiario(consultaDetalleTrfOBPOutDTO, response.getBancoBeneficiario().getValue());
		setBancoBeneficiarioIntermediario(consultaDetalleTrfOBPOutDTO, response.getBancoIntermediario().getValue(), response.getCuentaBcoIntermediario().getValue());
		//Obtener cuenta origen
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			String nroCuentaMatch = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-" + cuenta.getTipoCuenta() + "-" + ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto());
			if (nroCuentaMatch.equals(operacion.getCuentaCliente().replaceAll(CUENTAS_UNICAS, CUENTA_UNICA))) {
				//Se utiliza el cod cuenta para especificar la moneda de la cuenta
				if (operacion.getCuentaCliente().contains(CUENTA_UNICA_PESOS)) {
					consultaDetalleTrfOBPOutDTO.setCodTipoCuenta(StringUtils.leftPad(String.valueOf(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo()), 2, '0'));
					consultaDetalleTrfOBPOutDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
				} else if (operacion.getCuentaCliente().contains(CUENTA_UNICA_DOLARES)) {
					consultaDetalleTrfOBPOutDTO.setCodTipoCuenta(StringUtils.leftPad(String.valueOf(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo()), 2, '0'));
					consultaDetalleTrfOBPOutDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda());
				} else {
					consultaDetalleTrfOBPOutDTO.setCodTipoCuenta(cuenta.getTipoCuenta());
					consultaDetalleTrfOBPOutDTO.setTipoCuentaOrigen(cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
				}
				
				consultaDetalleTrfOBPOutDTO.setOrigen(new IdentificacionCuenta(cuenta.getSucursalPaquete(), cuenta.getNroCuentaProducto()).toString());
				break;
			}
		}
	}

	/**
	 * Sets the banco beneficiario intermediario.
	 *
	 * @param consultaDetalleTrfOBPOutDTO
	 *            the consulta detalle trf OBP out DTO
	 * @param codBcoBenIntermediario
	 *            the cod bco ben intermediario
	 * @param destinoCuentaBcoBenIntermediario
	 *            the destino cuenta bco ben intermediario
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void setBancoBeneficiarioIntermediario(ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO, String codBcoBenIntermediario, String destinoCuentaBcoBenIntermediario)
			throws DAOException {
		if (!StringUtils.isBlank(codBcoBenIntermediario)) {
			ConsultaBancosInEntity consultaBancosInEntity = new ConsultaBancosInEntity();
			consultaBancosInEntity.setCodigoBancario(StringUtils.removeStart(codBcoBenIntermediario, "FW"));
			ConsultaBancosResponse responseConsultaBancos = this.comexConsultasDAO.consultaBancos(consultaBancosInEntity);
			if (StringUtils.isBlank(responseConsultaBancos.getErrorInterface().getValue()) && StringUtils.isBlank(responseConsultaBancos.getErrorSistema().getValue())) {
				if (responseConsultaBancos.getRegistros().getValue().getBanco().size() > 0) {
					Banco bcoBen = responseConsultaBancos.getRegistros().getValue().getBanco().get(0);
					consultaDetalleTrfOBPOutDTO.setDescripcionBancoIntermediario(destinoCuentaBcoBenIntermediario);
					if (!StringUtils.isBlank(bcoBen.getSwiftBanco().getValue())) {
						consultaDetalleTrfOBPOutDTO.setCodigoBancoIntermediario(SWIFT);
						consultaDetalleTrfOBPOutDTO.setDescripcionBancoIntermediario(bcoBen.getSwiftBanco().getValue());
					} else {
						consultaDetalleTrfOBPOutDTO.setCodigoBancoIntermediario(ABA);
						consultaDetalleTrfOBPOutDTO.setDescripcionBancoIntermediario(bcoBen.getAbaBanco().getValue());
					}
				}
			}
		}
	}

	/**
	 * Sets the banco beneficiario.
	 *
	 * @param consultaDetalleTrfOBPOutDTO
	 *            the consulta detalle trf OBP out DTO
	 * @param codBcoBen
	 *            the cod bco ben
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void setBancoBeneficiario(ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO, String codBcoBen) throws DAOException {
		if (!StringUtils.isBlank(codBcoBen)) {
			ConsultaBancosInEntity consultaBancosInEntity = new ConsultaBancosInEntity();
			consultaBancosInEntity.setCodigoBancario(codBcoBen);
			ConsultaBancosResponse responseConsultaBancos = this.comexConsultasDAO.consultaBancos(consultaBancosInEntity);
			if (StringUtils.isBlank(responseConsultaBancos.getErrorInterface().getValue()) && StringUtils.isBlank(responseConsultaBancos.getErrorSistema().getValue())) {
				if (responseConsultaBancos.getRegistros().getValue().getBanco().size() > 0) {
					Banco bcoBen = responseConsultaBancos.getRegistros().getValue().getBanco().get(0);
					if (!StringUtils.isBlank(bcoBen.getSwiftBanco().getValue())) {
						consultaDetalleTrfOBPOutDTO.setCodigoBanco(SWIFT);
						consultaDetalleTrfOBPOutDTO.setDescripcionBanco(bcoBen.getSwiftBanco().getValue());
					} else {
						consultaDetalleTrfOBPOutDTO.setCodigoBanco(ABA);
						consultaDetalleTrfOBPOutDTO.setDescripcionBanco(bcoBen.getAbaBanco().getValue());
					}

				}
			}
		}
	}

	/**
	 * Sets the datos beneficiario.
	 *
	 * @param consultaDetalleTrfOBPOutDTO
	 *            the consulta detalle trf OBP out DTO
	 * @param response
	 *            the response
	 */
	private void setDatosBeneficiario(ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO, ConsultaDetalleTrfOBPResponse response) {
		consultaDetalleTrfOBPOutDTO.setNombreBeneficiario(response.getBeneficiario().getValue());
		consultaDetalleTrfOBPOutDTO.setVinculo(response.getVinculo().getValue());
		consultaDetalleTrfOBPOutDTO.setCodPais(response.getBeneficiarioPais().getValue());
		String[] parts = response.getBeneficiarioDomicilio().getValue().split(Pattern.quote(","));
		for (int i = 0; i < parts.length; i++) {
			if (i == 0) {
				String ultimoString = parts[i].substring(parts[i].lastIndexOf(" ") + 1);
				if (NumberUtils.isNumber(ultimoString)) {
					consultaDetalleTrfOBPOutDTO.setDomicilioNumero(ultimoString);
					consultaDetalleTrfOBPOutDTO.setDomicilioCalle(parts[i].substring(0, parts[i].lastIndexOf(" ")));
				} else {
					consultaDetalleTrfOBPOutDTO.setDomicilioCalle(parts[i]);
				}
			} else if (i == 1) {
				consultaDetalleTrfOBPOutDTO.setDomicilioLocalidad(parts[i].trim());
			} else if (i == 2) {
				consultaDetalleTrfOBPOutDTO.setDomicilioPais(parts[i].trim());
			}
		}

	}

	/**
	 * Creates the conceptos por tipo out DTO.
	 *
	 * @param response
	 *            the response
	 * @return the list
	 */
	private List<ConceptoPorTipoOutDTO> createConceptosPorTipoOutDTO(ConceptosPorTipoResponse response) {
		List<ConceptoPorTipoOutDTO> conceptosPorTipoOutDTO = new ArrayList<ConceptoPorTipoOutDTO>();
		ArrayOfConceptosTipo listConceptosPorTipo = response.getRegistros().getValue();
		Iterator<ConceptosTipo> itConceptoTipo = listConceptosPorTipo.getConceptosTipo().iterator();
		while (itConceptoTipo.hasNext()) {
			ConceptosTipo conceptoTipo = itConceptoTipo.next();
			ConceptoPorTipoOutDTO conceptoTipoOutDTO = new ConceptoPorTipoOutDTO();
			conceptoTipoOutDTO.setIdConcepto(conceptoTipo.getIdConcepto().getValue());
			conceptoTipoOutDTO.setConcepto(conceptoTipo.getConcepto().getValue());
			conceptosPorTipoOutDTO.add(conceptoTipoOutDTO);
		}
		return conceptosPorTipoOutDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#consultaOperaciones(ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO)
	 */
	@Override
	public Respuesta<ConsultaOperacionesOutDTO> consultaOperaciones(ConsultaOperacionesInDTO consultaOperacionesInDTO) {
		ConsultaOperacionesResponse response;
		ConsultaOperacionesInEntity consultaOperacionesInEntity = createConsultaOperacionesInEntity(consultaOperacionesInDTO);
		try {
			response = comexCanalesDAO.consultaOperaciones(consultaOperacionesInEntity);
			if (response.getCantReg().equals(new BigDecimal(-1))) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
			}
			if (!StringUtils.isBlank(response.getErrorInterface().getValue()) || !StringUtils.isBlank(response.getErrorSistema().getValue())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
			}
			ConsultaOperacionesOutDTO consultaOperacionesOutDTO = createConsultaOperacionesOutDTO(response, consultaOperacionesInDTO);
			return respuestaFactory.crearRespuestaOk(ConsultaOperacionesOutDTO.class, consultaOperacionesOutDTO);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
	}

	/**
	 * Creates the consulta operaciones out DTO.
	 *
	 * @param response
	 *            the response
	 * @param consultaOperacionesInDTO
	 *            the consulta operaciones in DTO
	 * @return the consulta operaciones out DTO
	 */
	private ConsultaOperacionesOutDTO createConsultaOperacionesOutDTO(ConsultaOperacionesResponse response, ConsultaOperacionesInDTO consultaOperacionesInDTO) {
		ConsultaOperacionesOutDTO consultaOperacionesOutDTO = new ConsultaOperacionesOutDTO();
		List<ConsultaOperacionesDTO> consultaOperaciones = new ArrayList<ConsultaOperacionesDTO>();
		Integer cantidadTransferenciasGuardadas = 0;
		Integer cantidadTransferenciasPendientes = 0;
		Integer cantidadTransferenciasRechazadas = 0;
		boolean esBusquedaPendiente = EstadoTransferenciaEnum.ESTADO_ENTRAMITE.equals(consultaOperacionesInDTO.getEstado()) && consultaOperacionesInDTO.getEsBuscador();
		List<Operacion> listOperacion = response.getRegistros().getValue().getOperacion();
		ListIterator<Operacion> itOperacion = listOperacion.listIterator();
		while (itOperacion.hasNext()) {
			Operacion value = itOperacion.next();
			ConsultaOperacionesDTO consultaOperacionesDTO = new ConsultaOperacionesDTO();
			if(esGuardadaSinDestinatario(value) || (esBusquedaPendiente && !EstadoTransferenciaEnum.PENDIENTE_TRAMITE.getCodigo().equals(value.getCodEstado().getValue())
					&& !EstadoTransferenciaEnum.PENDIENTE_PROCOTIZACION.getCodigo().equals(value.getCodEstado().getValue()))) {
				itOperacion.remove();
				continue;
			}
			if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.GUARDADA.getCodigo())) {
				consultaOperacionesDTO.setEstadoDescripcion(EstadoTransferenciaEnum.GUARDADA.getDescripcion());
				cantidadTransferenciasGuardadas++;
			} else if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_TRAMITE.getCodigo()) ||
					   value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.EN_ANALISIS_TECNICO_NORMATIVO.getCodigo()) ||
					   value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.CUSTOMER_COMEX.getCodigo()) ||
					   value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_AUTORIZACION_BCRA.getCodigo()) ||
					   value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.A_PROCESAR_CON_FECHA_PROYECTADA.getCodigo()) ||
					   value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.INGRESADO_COMO_TRF.getCodigo())) {
				consultaOperacionesDTO.setEstadoDescripcion(EstadoTransferenciaEnum.PENDIENTE_TRAMITE.getDescripcion());
				cantidadTransferenciasPendientes++;
			} else if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.RECHAZADA.getCodigo())) {
				consultaOperacionesDTO.setEstadoDescripcion(EstadoTransferenciaEnum.RECHAZADA.getDescripcion());
				cantidadTransferenciasRechazadas++;
			} else if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.LIQUIDADA.getCodigo())) {
				consultaOperacionesDTO.setEstadoDescripcion(EstadoTransferenciaEnum.LIQUIDADA.getDescripcion());
			} else if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_PROCOTIZACION.getCodigo())) {
				consultaOperacionesDTO.setEstadoDescripcion(EstadoTransferenciaEnum.PENDIENTE_PROCOTIZACION.getDescripcion());
				cantidadTransferenciasPendientes++;
			}

			agregarOperacion(value, consultaOperacionesDTO, consultaOperaciones);
			
		}
		Collections.sort(consultaOperaciones);
		consultaOperacionesOutDTO.setOperaciones(consultaOperaciones);
		consultaOperacionesOutDTO.setCantidadTransferenciasGuardadas(cantidadTransferenciasGuardadas);
		consultaOperacionesOutDTO.setCantidadTransferenciasPendientes(cantidadTransferenciasPendientes);
		consultaOperacionesOutDTO.setCantidadTransferenciasRechazadas(cantidadTransferenciasRechazadas);
		return consultaOperacionesOutDTO;
	}
	
	private void agregarOperacion(Operacion value, ConsultaOperacionesDTO consultaOperacionesDTO, List<ConsultaOperacionesDTO> consultaOperaciones) {
		
		if (value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.GUARDADA.getCodigo()) 
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.LIQUIDADA.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_TRAMITE.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.RECHAZADA.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_PROCOTIZACION.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.EN_ANALISIS_TECNICO_NORMATIVO.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.CUSTOMER_COMEX.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.PENDIENTE_AUTORIZACION_BCRA.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.A_PROCESAR_CON_FECHA_PROYECTADA.getCodigo())
				|| value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.INGRESADO_COMO_TRF.getCodigo())) {
			consultaOperacionesDTO.setCodEstado(value.getCodEstado().getValue());
			consultaOperacionesDTO.setFechaOperacion(value.getFechaOp().getValue());
			consultaOperacionesDTO.setDestinatario(value.getBeneficiario().getValue());
			consultaOperacionesDTO.setMonto(ISBANStringUtils.formatearSaldo(value.getMonto().getValue()));
			consultaOperacionesDTO.setCodMoneda(value.getCodMoneda().getValue());
			consultaOperacionesDTO.setNroForm(value.getNroForm().getValue());
			consultaOperacionesDTO.setCuentaCliente(value.getCuentaCliente().getValue());
			consultaOperaciones.add(consultaOperacionesDTO);
		}
		
	}
	
	private Boolean esGuardadaSinDestinatario(Operacion value) {
		return value.getCodEstado().getValue().equals(EstadoTransferenciaEnum.GUARDADA.getCodigo()) && StringUtils.isBlank(value.getBeneficiario().getValue());
	}

	/**
	 * Creates the consulta operaciones in entity.
	 *
	 * @param consultaOperacionesInDTO
	 *            the consulta operaciones in DTO
	 * @return the consulta operaciones in entity
	 */
	private ConsultaOperacionesInEntity createConsultaOperacionesInEntity(ConsultaOperacionesInDTO consultaOperacionesInDTO) {
		ConsultaOperacionesInEntity consultaOperacionesInEntity = new ConsultaOperacionesInEntity();
		if (EstadoTransferenciaEnum.ESTADO_ENTRAMITE.equals(consultaOperacionesInDTO.getEstado())) {
			consultaOperacionesInEntity.setEstadoTransferencia(null);
		} else {
			consultaOperacionesInEntity.setEstadoTransferencia(consultaOperacionesInDTO.getEstado());
		}
		consultaOperacionesInEntity.setFechaDesde(consultaOperacionesInDTO.getFechaDesde());
		consultaOperacionesInEntity.setFechaHasta(consultaOperacionesInDTO.getFechaHasta());
		consultaOperacionesInEntity.setMoneda(consultaOperacionesInDTO.getMoneda());
		consultaOperacionesInEntity.setMontoDesde(consultaOperacionesInDTO.getMontoDesde());
		consultaOperacionesInEntity.setMontoHasta(consultaOperacionesInDTO.getMontoHasta());
		return consultaOperacionesInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#adjuntarArchivos(ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO)
	 */
	@Override
	public Respuesta<Integer> adjuntarArchivos(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		try {
			AdjuntarArchivosInEntity adjuntarArchivosInEntity = createAdjuntarArchivosInEntity(adjuntarArchivosInDto.getNroTransferencia(), adjuntarArchivosInDto.getArchivo());
			CargaDocResponse response = this.comexCanalesDAO.adjuntarArchivos(adjuntarArchivosInEntity);
			if (ISBANStringUtils.isEmptyOrNull(response.getErrorSistema().getValue()) && ISBANStringUtils.isEmptyOrNull(response.getErrorInterface().getValue())) {
				return respuestaFactory.crearRespuestaOk(response.getHoja().getValue());
			}
		} catch (Exception e) {
			Integer numeroHojaRecuperado = obtenerNumeroDeHojaEnCasoDefalla(adjuntarArchivosInDto.getArchivo().getNombre(), adjuntarArchivosInDto.getNroTransferencia());
			if (numeroHojaRecuperado != null) {
				return respuestaFactory.crearRespuestaOk(numeroHojaRecuperado);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");

	}
	
	/**
	 * Obtener numero de hoja en caso defalla.
	 *
	 * @param nombreArchivo
	 *            the nombre archivo
	 * @param nroTransferencia
	 *            the nro transferencia
	 * @return the integer
	 */
	private Integer obtenerNumeroDeHojaEnCasoDefalla(String nombreArchivo, Integer nroTransferencia) {		
		ConsultaDetalleTrfOBPResponse response;
		ConsultaDetalleTrfOBPOutDTO consultaDetalleTrfOBPOutDTO=null;		
		try {
			ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO=new ConsultaDetalleTrfOBPInDTO();
			consultaDetalleTrfOBPInDTO.setNroTransferencia(nroTransferencia.longValue());
			ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity = createConsultaDetalleTrfOBPInEntity(consultaDetalleTrfOBPInDTO);
			response = comexCanalesDAO.consultaDetalleTrf(consultaDetalleTrfOBPInEntity);
			consultaDetalleTrfOBPOutDTO = new ConsultaDetalleTrfOBPOutDTO();
			setDocumentacionAdjunta( consultaDetalleTrfOBPOutDTO,  response);		
			for(ReporteView reporteView : consultaDetalleTrfOBPOutDTO.getArchivos()) {
				if(reporteView.getNombre().equalsIgnoreCase(nombreArchivo)) {
					return Integer.parseInt(reporteView.getId());
				}
			}
		}catch(Exception e) {
			return null;
		}
		return null;	
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#borrarDoc(ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO)
	 */
	@Override
	public Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		try {
			AdjuntarArchivosInEntity adjuntarArchivosInEntity = createAdjuntarArchivosInEntity(adjuntarArchivosInDto.getNroTransferencia(), adjuntarArchivosInDto.getArchivo());

			BorrarDocResponse response = this.comexCanalesDAO.borrarDoc(adjuntarArchivosInEntity);
			if (ISBANStringUtils.isEmptyOrNull(response.getErrorSistema().getValue()) && ISBANStringUtils.isEmptyOrNull(response.getErrorInterface().getValue())) {
				return respuestaFactory.crearRespuestaOk(true);
			}

		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(false);

	}

	/**
	 * Creates the adjuntar archivos in entity.
	 *
	 * @param nroTransferencia
	 *            the nro transferencia
	 * @param reporteView
	 *            the reporte view
	 * @return the adjuntar archivos in entity
	 */
	private AdjuntarArchivosInEntity createAdjuntarArchivosInEntity(Integer nroTransferencia, ReporteView reporteView) {
		AdjuntarArchivosInEntity adjuntarArchivosInEntity = new AdjuntarArchivosInEntity();
		adjuntarArchivosInEntity.setNroTransferencia(nroTransferencia);
		adjuntarArchivosInEntity.setArchivo(reporteView);
		return adjuntarArchivosInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#procesarTransferenciaComex(ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO)
	 */
	@Override
	public Respuesta<ProcesarTransferenciaComexOutDTO> procesarTransferenciaComex(ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO) {
		ProcesarTransferenciaOBPResponse response;
		ProcesarTransferenciaComexInEntity procesarTransferenciaComexInEntity = createProcesarTransferenciaComexInEntity(procesarTransferenciaComexInDTO);
		try {
			response = comexCanalesDAO.procesarTransferenciaComex(procesarTransferenciaComexInEntity);
			if (!StringUtils.isBlank(response.getErrorInterface().getValue()) || !StringUtils.isBlank(response.getErrorSistema().getValue())) {
				String errorSistema = response.getErrorSistema().getValue();
				if (!StringUtils.isBlank(errorSistema) && (errorSistema.startsWith(ERROR_I07) || errorSistema.startsWith(ERROR_A07))) {
					String codigoMensaje = errorSistema.startsWith(ERROR_A07) ? 
	        				CodigoMensajeConstantes.ERROR_TRANSFERENCIA_COMEX_LIMITE_A07 : CodigoMensajeConstantes.ERROR_TRANSFERENCIA_COMEX_LIMITE_I07;
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_TRANSFERENCIA_COMEX_LIMITE, codigoMensaje);
				}
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO, "");
			}
			ProcesarTransferenciaComexOutDTO procesarTransferenciaComexOutDTO = createProcesarTransferenciaComexOutDTO(response);
			return respuestaFactory.crearRespuestaOk(ProcesarTransferenciaComexOutDTO.class, procesarTransferenciaComexOutDTO);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
	}

	/**
	 * Creates the procesar transferencia comex out DTO.
	 *
	 * @param response
	 *            the response
	 * @return the procesar transferencia comex out DTO
	 */
	private ProcesarTransferenciaComexOutDTO createProcesarTransferenciaComexOutDTO(ProcesarTransferenciaOBPResponse response) {
		ProcesarTransferenciaComexOutDTO procesarTransferenciaComexOutDTO = new ProcesarTransferenciaComexOutDTO();
		procesarTransferenciaComexOutDTO.setNroTransferencia(response.getNroTransf().getValue());
		return procesarTransferenciaComexOutDTO;
	}
	
	/**
	 * Creates the procesar transferencia comex in entity.
	 *
	 * @param procesarTransferenciaComexInDTO
	 *            the procesar transferencia comex in DTO
	 * @return the procesar transferencia comex in entity
	 */
	private ProcesarTransferenciaComexInEntity createProcesarTransferenciaComexInEntity(ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO) {
		ProcesarTransferenciaComexInEntity procesarTransferenciaComexInEntity = new ProcesarTransferenciaComexInEntity();
		procesarTransferenciaComexInEntity.setNroTransferencia(procesarTransferenciaComexInDTO.getNroTransferencia());
		procesarTransferenciaComexInEntity.setEstadoTransferencia(procesarTransferenciaComexInDTO.getEstadoTransferencia());
		procesarTransferenciaComexInEntity.setTipoOperacion(procesarTransferenciaComexInDTO.getTipoOperacion());

		procesarTransferenciaComexInEntity.setMoneda(procesarTransferenciaComexInDTO.getCodMoneda());
		procesarTransferenciaComexInEntity.setImporteTransferencia(procesarTransferenciaComexInDTO.getImporteTransferencia());

		procesarTransferenciaComexInEntity.setBeneficiario(procesarTransferenciaComexInDTO.getNombreBeneficiario());
		if (procesarTransferenciaComexInDTO.getDomicilio() != null) {
			procesarTransferenciaComexInEntity.setBeneficiarioDomicilio(procesarTransferenciaComexInDTO.getDomicilio());
		}else {
			procesarTransferenciaComexInEntity.setBeneficiarioDomicilio("");
		}
		procesarTransferenciaComexInEntity.setVinculo(procesarTransferenciaComexInDTO.getVinculo());
		procesarTransferenciaComexInEntity.setBeneficiarioPais(procesarTransferenciaComexInDTO.getCodPais());

		procesarTransferenciaComexInEntity.setConcepto(procesarTransferenciaComexInDTO.getIdConcepto());

		procesarTransferenciaComexInEntity.setAceptaDDJJ(procesarTransferenciaComexInDTO.getAceptaDDJJ());
		procesarTransferenciaComexInEntity.setBancoBeneficiario(procesarTransferenciaComexInDTO.getBancoBeneficiario());
		procesarTransferenciaComexInEntity.setBancoIntermediario(procesarTransferenciaComexInDTO.getBancoIntermediario());

		procesarTransferenciaComexInEntity.setCtaAltair(procesarTransferenciaComexInDTO.getCuentaAltair());
		procesarTransferenciaComexInEntity.setCuentaBcoIntermediario(procesarTransferenciaComexInDTO.getCuentaBcoIntermediario());
		procesarTransferenciaComexInEntity.setCuentaBeneficiario(procesarTransferenciaComexInDTO.getCuentaBeneficiario());
		procesarTransferenciaComexInEntity.setCuentaDebito(procesarTransferenciaComexInDTO.getCuentaDebito());
		procesarTransferenciaComexInEntity.setGastoACargo(procesarTransferenciaComexInDTO.getGastoACargo());
		procesarTransferenciaComexInEntity.setRazonSocial(procesarTransferenciaComexInDTO.getRazonSocial());
		procesarTransferenciaComexInEntity.setNroFormRel(procesarTransferenciaComexInDTO.getNroFormRel());
		procesarTransferenciaComexInEntity.setVinculante((short) BooleanUtils.toInteger(procesarTransferenciaComexInDTO.getVinculante(), 1, 0, 0));
		return procesarTransferenciaComexInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO#consultaImagenTrf(ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO)
	 */
	@Override
	public Respuesta<AdjuntarArchivosDTO> consultaImagenTrf(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		AdjuntarArchivosDTO adjuntarArchivosOutDto = new AdjuntarArchivosDTO();
		try {
			AdjuntarArchivosInEntity adjuntarArchivosInEntity = createAdjuntarArchivosInEntity(adjuntarArchivosInDto.getNroTransferencia(), adjuntarArchivosInDto.getArchivo());
			ConsultaImagenTrfResponse consultaImagenTrfResponse = this.comexCanalesDAO.consultaImagenTrf(adjuntarArchivosInEntity);
			if (ISBANStringUtils.isEmptyOrNull(consultaImagenTrfResponse.getErrorSistema().getValue()) && ISBANStringUtils.isEmptyOrNull(consultaImagenTrfResponse.getErrorInterface().getValue())) {
				adjuntarArchivosOutDto.getArchivo().setByteArray(consultaImagenTrfResponse.getDataImagen().getValue());
				adjuntarArchivosOutDto.getArchivo().setId(adjuntarArchivosInDto.getArchivo().getId());
				adjuntarArchivosOutDto.getArchivo().setTipoArchivo(adjuntarArchivosInDto.getArchivo().getTipoArchivo());
				adjuntarArchivosOutDto.getArchivo().setNombre(adjuntarArchivosInDto.getArchivo().getNombre());
				adjuntarArchivosOutDto.setNroTransferencia(adjuntarArchivosInDto.getNroTransferencia());
				return respuestaFactory.crearRespuestaOk(adjuntarArchivosOutDto);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");

	}
	
	/* 
	 * Consulta concepto.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConceptoOutDTO> consultaConcepto(int idConcepto) {
		ConsultaConceptoResponse response;
		ConsultaConceptoInEntity consultaConceptoInEntity = new ConsultaConceptoInEntity();
		consultaConceptoInEntity.setIdConcepto(idConcepto);
		try {
			response = comexCanalesDAO.consultaConceptos(consultaConceptoInEntity);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ConceptoOutDTO conceptoOutDTO = createConsultaConceptoOutDTO(response);
		return respuestaFactory.crearRespuestaOk(conceptoOutDTO);
	}
	
	/**
	 * Creates the conceptos por tipo out DTO.
	 *
	 * @param response
	 *            the response
	 * @return the list
	 */
	private ConceptoOutDTO createConsultaConceptoOutDTO(ConsultaConceptoResponse response) {		
		ConceptoOutDTO conceptoOutDTO = new ConceptoOutDTO();
		conceptoOutDTO.setAyudaDocumentacion(response.getAyudaDocumentacion().getValue());
		conceptoOutDTO.setConFechaEmbargue(response.getConFechaEmbarque().getValue());
		conceptoOutDTO.setConFechaEmbEst(response.getConFechaEmbEst().getValue());
		conceptoOutDTO.setDeclaracionJurada(response.getDeclaracionJurada().getValue());
		conceptoOutDTO.setDeclaraImpuestos(response.getDeclaraImpuestos().getValue());
		conceptoOutDTO.setDescripcionConcepto(response.getDescripcionConcepto().getValue());
		conceptoOutDTO.setErrorInterface(response.getErrorInterface().getValue());
		conceptoOutDTO.setErrorSistema(response.getErrorSistema().getValue());
		conceptoOutDTO.setIdConcepto(BigDecimal.valueOf(response.getIdConcepto().getValue()));
		conceptoOutDTO.setIngresaDespachos(response.getIngresaDespachos().getValue());
		conceptoOutDTO.setIngresaNif(response.getIngresaNif().getValue());
		conceptoOutDTO.setMontoCom4834(response.getMontoCom4834().getValue());
		conceptoOutDTO.setMontoEmpVinculada(response.getMontoEmpVinculada().getValue());
		conceptoOutDTO.setMostrarCom4834(response.getMostrarCom4834().getValue());
		conceptoOutDTO.setMostrarEmpVinculada(response.getMostarEmpVinculada().getValue());
		conceptoOutDTO.setTipoConcepto(response.getTipoConcepto().getValue());
		conceptoOutDTO.setCon4237(response.getCon4237().getValue());
		conceptoOutDTO.setConCuitBenef(response.getConCuitBenef().getValue());
		conceptoOutDTO.setConNifGan(response.getConNIFGan().getValue());
		conceptoOutDTO.setConPosAran(response.getConPosAran().getValue());
		conceptoOutDTO.setDeclaraciones(response.getDeclaraciones().getValue());
		conceptoOutDTO.setDocumentos(response.getDocumentos().getValue());
		conceptoOutDTO.setEditaImpo(response.getEditaImpo().getValue());
		conceptoOutDTO.setInvAcre(response.getInvAcre().getValue());
		return conceptoOutDTO;
	}

}