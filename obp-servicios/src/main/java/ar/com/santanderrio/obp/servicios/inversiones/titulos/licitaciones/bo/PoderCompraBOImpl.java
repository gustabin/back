/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.PoderCompraDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DisclaimersAdhesionPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FormCamposAdhesionPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FormCamposAdhesionRequest;

/**
 * The Class PoderCompraBOImpl.
 */
@Component("poderCompraBO")
public class PoderCompraBOImpl extends InversionesAbstractBO implements PoderCompraBO {

	/** The poder compra DAO. */
	@Autowired
	private PoderCompraDAO poderCompraDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PoderCompraBOImpl.class);

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The constant CUENTA_CUSTODIA. */
	public static final String TIPO_CUENTA_OPERATIVA = "O";

	/** The Constant VACIO. */
	public static final String VACIO = "";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.PoderCompraBO#simularAdhesion(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> simularAdhesion(AdhesionPDCRequestDTO request, Cliente cliente,
			TipoBancaEnum tipoBancaEnum) {
		CrearAdhesionResponse response = new CrearAdhesionResponse();
		try {
			response = poderCompraDAO.adherirPoderCompra(crearRequestAdhesion(request, cliente, tipoBancaEnum));
		} catch (DAOException e) {
			LOGGER.error("Error al simular adhesion PDC ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return crearRespuestaOKAdhesionPDC(response);
	}

	/**
	 * Crear respuesta OK adhesion PDC.
	 *
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
	Respuesta<AdhesionPDCOutDTO> crearRespuestaOKAdhesionPDC(CrearAdhesionResponse response) {
		AdhesionPDCOutDTO rta = new AdhesionPDCOutDTO();
		if (response.getDatos().getDisclaimers() != null) {
			for (DisclaimersAdhesionPDCResponse disclaimers : response.getDatos().getDisclaimers()) {
				if (rta.getLegal() == null && disclaimers.getTipo().equals("I")) {
					if (disclaimers.getContenido() != null && !VACIO.equals(disclaimers.getContenido())) {
						rta.setLegal(disclaimers.getContenido());
					} 
//					else {
//						return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
//								CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
//					}
				}

				if (rta.getLink() == null && disclaimers.getTipo().equals("T")) {
					if (!VACIO.equals(disclaimers.getContenido()) && disclaimers.getListaCheckboxs() != null
							&& disclaimers.getContenido() != null
							&& disclaimers.getListaCheckboxs().get(0).getDesc() != null
							&& !VACIO.equals(disclaimers.getListaCheckboxs().get(0).getDesc())) {
						rta.setLink(disclaimers.getListaCheckboxs().get(0).getDesc());
						rta.setCheck(disclaimers.getListaCheckboxs().get(0).isChecked());
						rta.setLegalLink(disclaimers.getContenido());
						rta.setTitulo(disclaimers.getTitulo());
					} 
//						else {
//						return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
//								CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
//					}
				}
			}
		}

		for (ArrayList<FormCamposAdhesionPDCResponse> formResponse : response.getDatos().getFormCampos()) {
			for (FormCamposAdhesionPDCResponse form : formResponse) {
				if (form.getId().equals("IdSimCuentaPdc")) {
					rta.setIdSimCuentaPdc(form.getValue());
				}
			}
		}

		return respuestaFactory.crearRespuestaOk(AdhesionPDCOutDTO.class, rta);
	}

	/**
	 * Crear request adhesion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the crear adhesion request entity
	 */
	CrearAdhesionRequestEntity crearRequestAdhesion(AdhesionPDCRequestDTO request, Cliente cliente,
			TipoBancaEnum tipoBancaEnum) {
		CrearAdhesionRequestEntity entity = new CrearAdhesionRequestEntity();
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			entity.setCanal("04");
			entity.setSubCanal("0099");
			entity.getDatos().setSegmento("RTL");
		} else {
			entity.setCanal("79");
			entity.setSubCanal("0000");
			entity.getDatos().setSegmento("BP");
		}
		entity.getDatos().setNup(cliente.getNup());
		entity.getDatos().setOperacion("S");
		entity.getDatos().setIdServicio("PDC");
		entity.getDatos().setFormCompleted(1);
		entity.getDatos().setUsuario(System.getProperty("user.name"));
		entity.getDatos().setIp(NetworkUtil.getHostAddress());
		request = completarRequestAdhesionPDC(request, cliente);
		entity.getDatos().setFormCampos(crearFormAdhesionPDC(request));
		return entity;
	}

	/**
	 * Crear form adhesion PDC.
	 *
	 * @param request
	 *            the request
	 * @return the list
	 */
	List<FormCamposAdhesionRequest> crearFormAdhesionPDC(AdhesionPDCRequestDTO request) {
		List<FormCamposAdhesionRequest> listForm = new ArrayList<FormCamposAdhesionRequest>();
		FormCamposAdhesionRequest form = new FormCamposAdhesionRequest();
		form.setId("CuentaTitulos");
		form.setName("CuentaTitulos");
		form.setType("input-text");
		form.setValue(request.getNroCuentaTitulos());
		FormCamposAdhesionRequest form2 = new FormCamposAdhesionRequest();
		form2.setId("NroCtaOperativa");
		form2.setName("NroCtaOperativa");
		form2.setType("input-text");
		form2.setValue(request.getNroCuentaOperativa() + "-" + request.getTipoCuenta() + "-" + request.getNroSucursal()
				+ "-" + request.getProducto() + "-" + request.getSubProducto() + "-" + request.getMoneda());
		FormCamposAdhesionRequest form3 = new FormCamposAdhesionRequest();
		form3.setId("CodigoMoneda");
		form3.setName("CodigoMoneda");
		form3.setType("input-text");
		form3.setValue(request.getMoneda());
		listForm.add(form);
		listForm.add(form2);
		listForm.add(form3);
		return listForm;
	}

	/**
	 * Completar request adhesion PDC.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @return the adhesion PDC request DTO
	 */
	AdhesionPDCRequestDTO completarRequestAdhesionPDC(AdhesionPDCRequestDTO request, Cliente cliente) {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()) 
					.equals(ISBANStringUtils.eliminarCeros(request.getNroCuentaOperativa()))) {
				request.setProducto(cuenta.getProductoAltair());
				request.setSubProducto(cuenta.getSubproductoAltair());
				request.setTipoCuenta(obtenerTipoCuentaDesdeAbreviatura(request.getTipoCuenta(), request.getMoneda()));
			}
		}
		return request;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.PoderCompraBO#finalizarAdhesion(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<FinalizarAdhesionDTO> finalizarAdhesion(FinalizarAdhesionPDC request, Cliente cliente,
			TipoBancaEnum tipoBancaEnum) {
		CrearAdhesionResponse response = new CrearAdhesionResponse();
		try {
			response = poderCompraDAO
					.adherirPoderCompra(crearRequestFinalizarAdhesion(request, cliente, tipoBancaEnum));
		} catch (DAOException e) {
			LOGGER.error("Error al simular adhesion PDC ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		FinalizarAdhesionDTO respuestaBO = crearRespuestaOKAdhesionPDCFinalizar(response);
		if (response.getCodigo() == 0 && "1".equals(response.getDatos().getResultado())) {
			for (FormCamposAdhesionPDCResponse form : response.getDatos().getFormCampos().get(0)) {
				respuestaBO.getMapFormCampos().put(form.getId(), form.getValue());
			}
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(FinalizarAdhesionDTO.class, respuestaBO);
	}

	/**
	 * Crear request finalizar adhesion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the crear adhesion request entity
	 */
	CrearAdhesionRequestEntity crearRequestFinalizarAdhesion(FinalizarAdhesionPDC request, Cliente cliente,
			TipoBancaEnum tipoBancaEnum) {
		CrearAdhesionRequestEntity entity = new CrearAdhesionRequestEntity();
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			entity.setCanal("04");
			entity.setSubCanal("0099");
			entity.getDatos().setSegmento("RTL");
		} else {
			entity.setCanal("79");
			entity.setSubCanal("0000");
			entity.getDatos().setSegmento("BP");
		}
		entity.getDatos().setNup(cliente.getNup());
		entity.getDatos().setOperacion("P");
		entity.getDatos().setIdServicio("PDC");
		entity.getDatos().setFormCompleted(1);
		entity.getDatos().setUsuario(System.getProperty("user.name"));
		entity.getDatos().setIp(NetworkUtil.getHostAddress());
		entity.getDatos().setFormCampos(crearFormFinalizarAdhesionPDC(request));
		return entity;
	}

	/**
	 * Crear form finalizar adhesion PDC.
	 *
	 * @param request
	 *            the request
	 * @return the list
	 */
	List<FormCamposAdhesionRequest> crearFormFinalizarAdhesionPDC(FinalizarAdhesionPDC request) {
		List<FormCamposAdhesionRequest> listForm = new ArrayList<FormCamposAdhesionRequest>();
		FormCamposAdhesionRequest form = new FormCamposAdhesionRequest();
		form.setId("IdSimCuentaPdc");
		form.setName("IdSimCuentaPdc");
		form.setType("input-text");
		form.setValue(request.getIdSimCuentaPdc());
		listForm.add(form);
		return listForm;
	}

	/**
	 * Obtener tipo cuenta desde abreviatura.
	 *
	 * @param tipoCuentaDebito
	 *            the tipo cuenta debito
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaDesdeAbreviatura(String tipoCuentaDebito, String moneda) {
		if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(tipoCuentaDebito)) {
			if (TipoMonedaInversionEnum.USD.getCodigo2().equals(moneda)) {
				return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
			} else {
				return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
			}
		}
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString();
		}
		if (TIPO_CUENTA_OPERATIVA.equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_DOLARES.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
		return null;
	}

	/**
	 * Crear respuesta OK adhesion PDC finalizar.
	 *
	 * @param response
	 *            the response
	 * @return the finalizar adhesion DTO
	 */
	FinalizarAdhesionDTO crearRespuestaOKAdhesionPDCFinalizar(CrearAdhesionResponse response) {
		FinalizarAdhesionDTO rta = new FinalizarAdhesionDTO();
		for (DisclaimersAdhesionPDCResponse disclaimers : response.getDatos().getDisclaimers()) {
			if (rta.getLegal() == null && disclaimers.getTipo().equals("I") && disclaimers.getContenido() != null) {
				rta.setLegal(disclaimers.getContenido());
			}
		}
		return rta;
	}

}
