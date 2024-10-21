/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasCitiDAO;
import ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDAO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CuentaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CustodiaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmanteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantePrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoPendienteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoMonedaExtranjeraDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.RendimientoFondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TarjetasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TipoClienteCitiDTO;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesInEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasInEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TipClienteCitiTenenciasOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.exception.CopiarListaException;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;

/**
 * The Class TenenciasBOImpl.
 *
 * @author
 */
@Component("tenenciasBO")
public class TenenciasBOImpl implements TenenciasBO {

	/** The Constant ERROR_CONSULTAR_DAO. */
	private static final String ERROR_CONSULTAR_DAO = "Error al parsear DAO con";
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasBOImpl.class);

	/** dao. */
	@Autowired
	private TenenciasDAO tenenciasDAO;
	/** daoTipoCLiente. */
	@Autowired
	private TenenciasCitiDAO tenenciasCitiDAO;
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasBO#
	 * consultarTenencias(ar.com.santanderrio.obp.servicios.tenencias.dto.
	 * TenenciasInDTO)
	 */
	public Respuesta<TenenciasDTO> consultarTenencias(TenenciasInDTO dtoRequest) {
		TenenciasInEntity inDAO = null;
		TenenciasOutEntity outDAO = null;
		Respuesta<TenenciasDTO> respuesta = null;
		try {
			inDAO = createTenenciasInEntity(dtoRequest);
			outDAO = tenenciasDAO.obtenerTenecias(inDAO.getNup(), inDAO.getAnioDesde(), inDAO.getAnioHasta());
			respuesta = respuestaFactory.crearRespuestaOk(TenenciasDTO.class, createTenenciasDTO(outDAO));
		} catch (DAOException daoe) {
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, daoe);
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDTO.class, null);
		} catch (IllegalAccessException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		} catch (InvocationTargetException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		} catch (CopiarListaException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		}
		return respuesta;
	}

	/**
	 * crear una entidad para llegar al dao.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @return entidad de entrada.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	private TenenciasInEntity createTenenciasInEntity(TenenciasInDTO dtoRequest)
			throws IllegalAccessException, InvocationTargetException {
		TenenciasInEntity entityRequest = new TenenciasInEntity();
		BeanUtils.copyProperties(entityRequest, dtoRequest);
		return entityRequest;
	}

	/**
	 * crear una entidad TenenciasDTO para retornar al TenenciasBO.
	 *
	 * @param outDAO
	 *            the out DAO
	 * @return entidad DTO.
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private TenenciasDTO createTenenciasDTO(TenenciasOutEntity outDAO) throws CopiarListaException {

		TenenciasDTO outDTO = new TenenciasDTO();

		List<CuentaDTO> cuentas = new ArrayList<CuentaDTO>();
		TenenciasHelper.copiarLista(outDAO.getCuentasEntity(), cuentas, CuentaDTO.class);
		outDTO.setCuentasDTO(cuentas);

		List<CustodiaDTO> custodias = new ArrayList<CustodiaDTO>();
		TenenciasHelper.copiarLista(outDAO.getCustodiaEntity(), custodias, CustodiaDTO.class);
		outDTO.setCustodiaDTO(custodias);

		List<FondoDTO> fondos = new ArrayList<FondoDTO>();
		TenenciasHelper.copiarLista(outDAO.getFondosEntity(), fondos, FondoDTO.class);
		outDTO.setFondosDTO(fondos);

		List<FondoPendienteDTO> fondosPendientes = new ArrayList<FondoPendienteDTO>();
		TenenciasHelper.copiarLista(outDAO.getFondosPendientesEntity(), fondosPendientes, FondoPendienteDTO.class);
		outDTO.setFondosPendientesDTO(fondosPendientes);

		List<ImpuestoDTO> impuestos = new ArrayList<ImpuestoDTO>();
		TenenciasHelper.copiarLista(outDAO.getImpuestosEntity(), impuestos, ImpuestoDTO.class);
		outDTO.setImpuestosDTO(impuestos);

		List<PlazoFijoDTO> plazoFijos = new ArrayList<PlazoFijoDTO>();
		TenenciasHelper.copiarLista(outDAO.getPlazoFijoEntity(), plazoFijos, PlazoFijoDTO.class);
		outDTO.setPlazoFijoDTO(plazoFijos);

		List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
		TenenciasHelper.copiarLista(outDAO.getPrestamosEntity(), prestamos, PrestamoDTO.class);
		outDTO.setPrestamosDTO(prestamos);

		List<TarjetasDTO> tarjeta = new ArrayList<TarjetasDTO>();
		TenenciasHelper.copiarLista(outDAO.getTarjetasEntity(), tarjeta, TarjetasDTO.class);
		outDTO.setTarjetasDTO(tarjeta);

		List<RendimientoFondoDTO> rendimientoFondo = new ArrayList<RendimientoFondoDTO>();
		TenenciasHelper.copiarLista(outDAO.getRendimientoFondosEntity(), rendimientoFondo, RendimientoFondoDTO.class);
		outDTO.setRendimientoFondosDTO(rendimientoFondo);

		List<ImpuestoMonedaExtranjeraDTO> impuestoMonedaExt = new ArrayList<ImpuestoMonedaExtranjeraDTO>();
		TenenciasHelper.copiarLista(outDAO.getImpuestoMonedaExtranjeraEntity(), impuestoMonedaExt,
				ImpuestoMonedaExtranjeraDTO.class);
		outDTO.setImpuestoMonedaExtranjeraDTO(impuestoMonedaExt);
		outDTO.setCotiDolar(outDAO.getCotiDolar());
		return outDTO;
	}
	
	
	/**
	 * crear una entidad TenenciasDTO para retornar al TenenciasBO.
	 *
	 * @param outDAO
	 *            the out DAO
	 * @return entidad DTO.
	 */
	private TipoClienteCitiDTO createTipClienteTenenciasDTO(TipClienteCitiTenenciasOutEntity outDAO)  {

		TipoClienteCitiDTO outDTO = new TipoClienteCitiDTO();
		outDTO.setTipCliente(outDAO.getTipCliente());
		outDTO.setCodResultado(outDAO.getCodResultado());
		outDTO.setDescErrorTecnico(outDAO.getDescErrorTec());
		outDTO.setDescErrorAmigable(outDAO.getDescErrorAmig());
		
		return outDTO;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasBO#
	 * consultarFirmantes(ar.com.santanderrio.obp.servicios.tenencias.dto.
	 * TenenciasInDTO)
	 */
	@Override
	public Respuesta<FirmantesDTO> consultarFirmantes(TenenciasInDTO reqFirmantes) {
		FirmantesInEntity inDAO = null;
		FirmantesOutEntity outDAO = null;
		Respuesta<FirmantesDTO> respuesta = null;
		try {
			inDAO = createFirmantesInEntity(reqFirmantes);
			outDAO = tenenciasDAO.obtenerFirmantes(inDAO.getNup(), inDAO.getAnioDesde(), inDAO.getAnioHasta());
			respuesta = respuestaFactory.crearRespuestaOk(FirmantesDTO.class, createFirmantesDTO(outDAO));
		} catch (DAOException daoe) {
			respuesta = respuestaFactory.crearRespuestaError(FirmantesDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, daoe);
		} catch (IllegalAccessException e) {
			respuesta = respuestaFactory.crearRespuestaError(FirmantesDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		} catch (InvocationTargetException e) {
			respuesta = respuestaFactory.crearRespuestaError(FirmantesDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		} catch (CopiarListaException e) {
			respuesta = respuestaFactory.crearRespuestaError(FirmantesDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		}
		return respuesta;
	}

	/**
	 * crear una entidad para llegar al dao.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @return entidad de entrada.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	private FirmantesInEntity createFirmantesInEntity(TenenciasInDTO dtoRequest)
			throws IllegalAccessException, InvocationTargetException {
		FirmantesInEntity entityRequest = new FirmantesInEntity();
		BeanUtils.copyProperties(entityRequest, dtoRequest);
		return entityRequest;
	}

	/**
	 * crear una entidad FirmantesDTO para retornar al FirmantesBO.
	 *
	 * @param outDAO
	 *            the out DAO
	 * @return entidad DTO.
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private FirmantesDTO createFirmantesDTO(FirmantesOutEntity outDAO) throws CopiarListaException {

		FirmantesDTO outDTO = new FirmantesDTO();

		List<FirmanteDTO> firmantesCuentas = new ArrayList<FirmanteDTO>();
		TenenciasHelper.copiarLista(outDAO.getCuentas(), firmantesCuentas, FirmanteDTO.class);
		outDTO.setCuentas(firmantesCuentas);

		List<FirmanteDTO> firmantesCustodia = new ArrayList<FirmanteDTO>();
		TenenciasHelper.copiarLista(outDAO.getCustodias(), firmantesCustodia, FirmanteDTO.class);
		outDTO.setCustodias(firmantesCuentas);

		List<FirmanteDTO> firmantesFondos = new ArrayList<FirmanteDTO>();
		TenenciasHelper.copiarLista(outDAO.getFondos(), firmantesFondos, FirmanteDTO.class);
		outDTO.setFondos(firmantesCuentas);

		List<FirmanteDTO> firmantesPlazoFijos = new ArrayList<FirmanteDTO>();
		TenenciasHelper.copiarLista(outDAO.getPlazoFijo(), firmantesPlazoFijos, FirmanteDTO.class);
		outDTO.setPlazoFijo(firmantesPlazoFijos);

		List<FirmantePrestamoDTO> firmantesPrestamo = new ArrayList<FirmantePrestamoDTO>();
		TenenciasHelper.copiarLista(outDAO.getPrestamos(), firmantesPrestamo, FirmantePrestamoDTO.class);
		outDTO.setPrestamos(firmantesPrestamo);

		return outDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasBO#consultarTipClienteTenencias(ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO)
	 */
	@Override
	public Respuesta<TipoClienteCitiDTO> consultarTipClienteTenencias(TenenciasInDTO reqCliTenencias) {
		TenenciasInEntity inDAO = null;
		TipClienteCitiTenenciasOutEntity outDAO = null;
		Respuesta<TipoClienteCitiDTO> respuesta = null;
		try {
			inDAO = createTenenciasInEntity(reqCliTenencias);
			outDAO = tenenciasCitiDAO.obtenerTipoCLienteTenencias(inDAO.getNup(), inDAO.getAnioDesde(), inDAO.getAnioHasta());
			//outDAO =  new TipClienteCitiTenenciasOutEntity();
			//outDAO.setCodResultado("0");
			//outDAO.setDescErrorAmig("LALA");
			//outDAO.setDescErrorTec("LALA");
			//outDAO.setTipoCliente("CITI");
			respuesta = respuestaFactory.crearRespuestaOk(TipoClienteCitiDTO.class, createTipClienteTenenciasDTO(outDAO));
		} catch (DAOException daoe) {
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, daoe);
			respuesta = respuestaFactory.crearRespuestaError(TipoClienteCitiDTO.class, null);
		} catch (IllegalAccessException e) {
			respuesta = respuestaFactory.crearRespuestaError(TipoClienteCitiDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		} catch (InvocationTargetException e) {
			respuesta = respuestaFactory.crearRespuestaError(TipoClienteCitiDTO.class, null);
			LOGGER.error(ERROR_CONSULTAR_DAO, inDAO, e);
		}
		return respuesta;
	}

}