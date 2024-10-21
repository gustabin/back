package ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexLiquidacionOrdenPagoBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.common.TiposDocumentoComexEnum;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexOrdenPagoDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ConceptosLiquidacionOPDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.CargaDocumentoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoConfiguracionOrdenPagoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConfiguracionLiquidacionOrdenPagoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarOrdenPagoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConceptoLiquidacionOPEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarOrPagoOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.CuentaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

@Component
public class ComexLiquidacionOrdenPagoBOImpl implements ComexLiquidacionOrdenPagoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexLiquidacionOrdenPagoBOImpl.class);

	@Autowired
	private ComexCanalesDAO comexCanalesDAO;

	@Autowired
	private RespuestaFactory respFactory;

	@Autowired
	private ConceptosLiquidacionOPDAO conceptosLiquidacionOPDAO;

	@Autowired
	private ComexOrdenPagoDAO comexOrdenPagoDAO;

	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

	/** The Constant S. */
	private static final String S = "S";

	/** The Constant GUION_ESPACIO. */
	private static final String GUION_ESPACIO = " - ";

	/** The Constant GUION. */
	private static final String GUION = "-";

	private static final String CERO = "0";

	/** The paginado desktop. */
	@Value("${COMEX.CONCEPTOS.SIN.VINCULANTE}")
	private String conceptosSinVinculantes;

	@Override
	public Respuesta<ConfiguracionLiquidacionOrdenPagoDTO> inicioLiquidacionOrdenPago(Cliente cliente)
			throws BusinessException {
		ConfiguracionLiquidacionOrdenPagoDTO configuracionDTO = new ConfiguracionLiquidacionOrdenPagoDTO();
		try {
			configuracionDTO.setConceptos(obtenerConceptos());
		} catch (DAOException e) {
			throw new BusinessException();
		}
		configuracionDTO.setCuentasPesos(obtenerCuentasPorMoneda(cliente.getCuentas(), false));
		configuracionDTO.setCuentasPesosDolares(obtenerCuentasPorMoneda(cliente.getCuentas(), true));
		if (cliente.getClienteBancaPrivada()) {
			List<Cuenta> cuentasBP = cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente);
			List<Cuenta> cuentasBPFiltradas = new ArrayList<Cuenta>();
			for (Cuenta cuentaBP : cuentasBP) {
				if (CuentasBancaPrivadaUtil.isCuentaPrivada(cuentaBP) && CuentasBancaPrivadaUtil.validaCuentaBPComex(cuentaBP)) {
					cuentasBPFiltradas.add(cuentaBP);
				}
			}
			configuracionDTO.getCuentasPesos().addAll(obtenerCuentasPorMoneda(cuentasBPFiltradas, false));
			configuracionDTO.getCuentasPesosDolares().addAll(obtenerCuentasPorMoneda(cuentasBPFiltradas, true));
		}
		return respFactory.crearRespuestaOk(configuracionDTO);
	}

	private List<Cuenta> obtenerCuentasPorMoneda(List<Cuenta> cuentas, Boolean incluyeCuentasDolar) {
		List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
		Cuenta cuentaFavorita = null;
		for (Cuenta cuenta : cuentas) {
			if (cuenta.isCuentaPesos() || cuenta.esCuentaUnica() || (incluyeCuentasDolar && cuenta.isCuentaDolares())) {
				if (cuenta.getIsFavorita()) {
					cuentaFavorita = cuenta;
				} else {
					cuentasFiltradas.add(cuenta);
				}
			}
		}
		if (cuentaFavorita != null) {
			cuentasFiltradas.add(0, cuentaFavorita);
		}
		return cuentasFiltradas;
	}

	private List<ConceptoConfiguracionOrdenPagoDTO> obtenerConceptos() throws DAOException {
		List<ConceptoLiquidacionOPEntity> conceptosTabla = conceptosLiquidacionOPDAO.obtenerConceptosLiquidacionOP();
		List<ConceptoConfiguracionOrdenPagoDTO> conceptosDTO = new ArrayList<ConceptoConfiguracionOrdenPagoDTO>();
		for (ConceptoLiquidacionOPEntity concepto : conceptosTabla) {
			ConceptoConfiguracionOrdenPagoDTO conceptoDTO = new ConceptoConfiguracionOrdenPagoDTO();
			conceptoDTO.setNombre(concepto.getCodigoConcepto() + GUION_ESPACIO + concepto.getConcepto());
			conceptoDTO.setAdmiteCanje(StringUtils.equals(S, concepto.getAdmiteCanje()));
			conceptoDTO.setDocumentacionObligatoria(StringUtils.equals(S, concepto.getDocumentacionObligatoria()));
			conceptoDTO.setIdConcepto(concepto.getIdConcepto());
			conceptoDTO.setMuestraVinculante(!conceptosSinVinculantes.contains(concepto.getCodigoConcepto()));
			conceptosDTO.add(conceptoDTO);
		}
		return conceptosDTO;
	}

	@Override
	public Respuesta<Boolean> limpiarConceptos() {
		conceptosLiquidacionOPDAO.limpiarConceptos();
		return respFactory.crearRespuestaOk(true);
	}

	@Override
	public Respuesta<CargaDocumentoDTO> adjuntarArchivos(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		try {
			AdjuntarArchivosInEntity adjuntarArchivosInEntity = createAdjuntarArchivosInEntity(
					adjuntarArchivosInDto.getNroTransferencia(), adjuntarArchivosInDto.getArchivo());
			CargaDocResponse response = this.comexCanalesDAO.adjuntarArchivos(adjuntarArchivosInEntity);
			if (ISBANStringUtils.isEmptyOrNull(response.getErrorSistema().getValue())
					&& ISBANStringUtils.isEmptyOrNull(response.getErrorInterface().getValue())) {
				CargaDocumentoDTO documentoCarga = new CargaDocumentoDTO();
				documentoCarga.setHoja(response.getHoja().getValue());
				documentoCarga.setNroTransferencia(response.getNroTransferencia().getValue());
				return respFactory.crearRespuestaOk(documentoCarga);
			}
		} catch (Exception e) {
			// TODO: consultar casuística.
			return respFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
		return respFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
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

	@Override
	public Respuesta<ProcesarOrdenPagoOutDTO> procesarOrdenPago(Cliente cliente, ProcesarOrdenPagoInView view)
			throws BusinessException {
		try {
			ProcesarOrPagoOBPInEntity request = obtenerProcesarOrPagoOBPInEntity(cliente, view);
			ProcesarOrPagoOBPResponse response = comexOrdenPagoDAO.procesarOrdenPago(request);
			if (response.getErrorInterface().getValue() != null) {
				throw new BusinessException(response.getErrorInterface().getValue());
			}
			ProcesarOrdenPagoOutDTO dto = new ProcesarOrdenPagoOutDTO();
			dto.setNroForm(response.getNroForm().getValue());
			return respFactory.crearRespuestaOk(dto);
		} catch (DAOException e) {
			LOGGER.info("Error al consultar servicio procesar orden pago");
			throw new BusinessException();
		} catch (ImporteConvertException e) {
			LOGGER.info("Error al convertir importe");
			throw new BusinessException();
		}
	}

	private ProcesarOrPagoOBPInEntity obtenerProcesarOrPagoOBPInEntity(Cliente cliente, ProcesarOrdenPagoInView view)
			throws ImporteConvertException {
		ProcesarOrPagoOBPInEntity request = new ProcesarOrPagoOBPInEntity();
		request.setAceptaDdjj((short) 1);
		request.setConcepto(view.getConcepto().getIdConcepto());
		request.setCuentaCredito(obtenerNumeroCuentaFormateado(view.getCuenta()));
		request.setImportePago(ISBANStringUtils.convertirImporte(view.getImporte()));
		request.setNroDocCliente(StringUtils.stripStart(cliente.getDni(), CERO));
		request.setNroForm(view.getIdAdjunto());
		request.setNroOperacion(view.getNumeroReferencia());
		request.setNupCliente(StringUtils.stripStart(cliente.getNup(), CERO));
		request.setRazonSocial(cliente.getNombre() + ISBANStringUtils.ESPACIO_STRING + cliente.getApellido1());
		request.setTipoDocCliente(TiposDocumentoComexEnum.obtenerDescripcionPorCodigo(cliente.getTipoDocumento()));
		request.setEmpresaVinculada(view.getConcepto().getMuestraVinculante() && view.getEmpresaVinculada() ? (short) 1 : (short) 0);
		return request;
	}

	private String obtenerNumeroCuentaFormateado(CuentaView cuentaView) {
		IdentificacionCuenta idCuenta = new IdentificacionCuenta(cuentaView.getNumero());
		String tipoCuenta = TipoCuenta.fromDescripcionConMoneda(cuentaView.getDescripcionTipoCuenta()).getCodigo()
				.toString();
		return idCuenta.getNroSucursal() + GUION + StringUtils.leftPad(tipoCuenta, 2, CERO) + GUION
				+ idCuenta.getNroCuentaProducto();
	}

	@Override
	public Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		try {
			AdjuntarArchivosInEntity borrarDocumentacionInEntity = obtenerBorrarDocumentacionInEntity(
					adjuntarArchivosInDto);
			borrarDocumentacionInEntity.setNroTransferencia(adjuntarArchivosInDto.getNroTransferencia());
			BorrarDocResponse response = this.comexCanalesDAO.borrarDoc(borrarDocumentacionInEntity);
			if (ISBANStringUtils.isEmptyOrNull(response.getErrorSistema().getValue())
					&& ISBANStringUtils.isEmptyOrNull(response.getErrorInterface().getValue())) {
				return respFactory.crearRespuestaOk(true);
			}
		} catch (DAOException e) {
			return respFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
		return respFactory.crearRespuestaOk(false);
	}

	private AdjuntarArchivosInEntity obtenerBorrarDocumentacionInEntity(AdjuntarArchivosDTO dto) {
		AdjuntarArchivosInEntity request = new AdjuntarArchivosInEntity();
		ReporteView archivo = new ReporteView();
		archivo.setId(dto.getHoja());
		request.setNroTransferencia(dto.getNroTransferencia());
		request.setArchivo(archivo);
		return request;
	}
}
