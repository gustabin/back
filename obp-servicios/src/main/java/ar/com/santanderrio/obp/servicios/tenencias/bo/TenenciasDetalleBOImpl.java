/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CuentasParticipanesDTO;
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
import ar.com.santanderrio.obp.servicios.tenencias.entity.CuentasParticipantesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosParticipantesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ParticipantesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasDetalleInEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasDetalleOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.exception.CopiarListaException;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;

/**
 * The Class TenenciasDetalleBOImpl.
 *
 * @author
 */
@Component("tenenciasDetalleBO")
public class TenenciasDetalleBOImpl implements TenenciasDetalleBO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasDetalleBOImpl.class);

	/** dao. */
	@Autowired
	private TenenciasDetalleDAO tenenciasDetalleDAO;
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	@Autowired
	private ConsultaPlazoFijoDAO consultaPlazoFijoDAO;

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasDetalleBO#
	 * consultarDetalleTenenciasCompleto(ar.com.santanderrio.obp.servicios.
	 * tenencias.dto.TenenciasDetalleInDTO)
	 */
	public Respuesta<TenenciasDetalleDTO> consultarDetalleTenenciasCompleto(TenenciasDetalleInDTO dtoRequest) {
		TenenciasDetalleInEntity inEntity = new TenenciasDetalleInEntity();
		TenenciasDetalleOutEntity outEntity = new TenenciasDetalleOutEntity();
		Respuesta<TenenciasDetalleDTO> respuesta = null;
		try {
			inEntity = createTenenciasDetalleInEntity(dtoRequest);
			outEntity.setDetalleCuotasPrestamosEntities(
					tenenciasDetalleDAO.obtenerDetalleCuotasPrestamos(inEntity.getNup(), inEntity.getAnioDesde()));
			outEntity.setDetallePlazoFijoEntities(tenenciasDetalleDAO.obtenerDetallePlazoFijo(inEntity.getNup(),
					inEntity.getAnioDesde(), inEntity.getAnioHasta()));
			outEntity.setResumenCuentaInversionesEntitiesMON(tenenciasDetalleDAO
					.obtenerResumenCuentaInversiones(inEntity.getNup(), inEntity.getAnioDesde(), "MON"));
			outEntity.setResumenCuentaInversionesEntitiesCEF(tenenciasDetalleDAO
					.obtenerResumenCuentaInversiones(inEntity.getNup(), inEntity.getAnioDesde(), "CEF"));
			outEntity.setResumenCuentaInversionesEntitiesBON(tenenciasDetalleDAO
					.obtenerResumenCuentaInversiones(inEntity.getNup(), inEntity.getAnioDesde(), "BON"));
			outEntity.setResumenCuentaInversionesEntitiesSHS(tenenciasDetalleDAO
					.obtenerResumenCuentaInversiones(inEntity.getNup(), inEntity.getAnioDesde(), "SHS"));
			outEntity.setResumenCuentaInversionesEntitiesFON(tenenciasDetalleDAO
					.obtenerResumenCuentaInversiones(inEntity.getNup(), inEntity.getAnioDesde(), "FON"));
			outEntity.setDetalleImpuestoMonedaExtranjeraEntities(tenenciasDetalleDAO
					.obtenerDetalleImpuestoMonedaExtranjera(inEntity.getNup(), inEntity.getAnioDesde()));
			outEntity.setDetalleTarjetaCreditoEntities(
					tenenciasDetalleDAO.obtenerDetalleTarjetaCredito(inEntity.getNup(), inEntity.getAnioDesde()));
			outEntity.setDetalleMensualImpuestosEntity(
					tenenciasDetalleDAO.obtenerDetalleMensualImpuestos(inEntity.getNup(), inEntity.getAnioDesde()));
			outEntity.setParticipantesPL(tenenciasDetalleDAO.consultarFirmantesPL(inEntity.getAnioDesde(), inEntity.getNup() ));
			respuesta = respuestaFactory.crearRespuestaOk(TenenciasDetalleDTO.class,
					createTenenciasDetalleDTO(outEntity));
		} catch (DAOException daoe) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al consultar DAO con", inEntity);
		} catch (IllegalAccessException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inEntity);
		} catch (InvocationTargetException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inEntity);
		} catch (CopiarListaException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inEntity);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasDetalleBO#
	 * consultarDetalleTenencias(ar.com.santanderrio.obp.servicios.tenencias.dto
	 * .TenenciasDetalleInDTO, int)
	 */
	public Respuesta<TenenciasDetalleDTO> consultarDetalleTenencias(TenenciasDetalleInDTO dtoRequest, int tipoDetalle) {
		TenenciasDetalleInEntity inDAO = new TenenciasDetalleInEntity();
		TenenciasDetalleOutEntity outDAO = new TenenciasDetalleOutEntity();

		Respuesta<TenenciasDetalleDTO> respuesta = null;
		try {
			inDAO = createTenenciasDetalleInEntity(dtoRequest);
			switch (tipoDetalle) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				outDAO.setDetalleCuotasPrestamosEntities(
						tenenciasDetalleDAO.obtenerDetalleCuotasPrestamos(inDAO.getNup(), inDAO.getAnioDesde()));
				break;
			case 5:
				if (dtoRequest.getpEspeTipo().isEmpty()) {
				outDAO.setDetallePlazoFijoEntities(tenenciasDetalleDAO.obtenerDetallePlazoFijo(inDAO.getNup(),
						inDAO.getAnioDesde(), inDAO.getAnioHasta()));
				}
				outDAO.setResumenCuentaInversionesEntities(tenenciasDetalleDAO
						.obtenerResumenCuentaInversiones(inDAO.getNup(), inDAO.getAnioDesde(), inDAO.getEspeTipo()));
				break;
			case 6:
				outDAO.setDetalleImpuestoMonedaExtranjeraEntities(tenenciasDetalleDAO
						.obtenerDetalleImpuestoMonedaExtranjera(inDAO.getNup(), inDAO.getAnioDesde()));
				outDAO.setDetalleTarjetaCreditoEntities(
						tenenciasDetalleDAO.obtenerDetalleTarjetaCredito(inDAO.getNup(), inDAO.getAnioDesde()));
				outDAO.setDetalleMensualImpuestosEntity(
						tenenciasDetalleDAO.obtenerDetalleMensualImpuestos(inDAO.getNup(), inDAO.getAnioDesde()));
				break;
			default:
				break;
			}
			respuesta = respuestaFactory.crearRespuestaOk(TenenciasDetalleDTO.class, createTenenciasDetalleDTO(outDAO));
		} catch (DAOException daoe) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al consultar DAO con", inDAO);
		} catch (IllegalAccessException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inDAO);
		} catch (InvocationTargetException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inDAO);
		} catch (CopiarListaException e) {
			respuesta = respuestaFactory.crearRespuestaError(TenenciasDetalleDTO.class, null);
			LOGGER.error("Error al parsear DAO con", inDAO);
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
	private TenenciasDetalleInEntity createTenenciasDetalleInEntity(TenenciasDetalleInDTO dtoRequest)
			throws IllegalAccessException, InvocationTargetException {
		TenenciasDetalleInEntity entityRequest = new TenenciasDetalleInEntity();
		BeanUtils.copyProperties(entityRequest, dtoRequest);
		entityRequest.setEspeTipo(dtoRequest.getpEspeTipo());
		return entityRequest;
	}

	/**
	 * crear una entidad TenenciasDetalleDTO para retornar al
	 * TenenciasDetalleBO.
	 *
	 * @param outDAO
	 *            the out DAO
	 * @return entidad DTO.
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	private TenenciasDetalleDTO createTenenciasDetalleDTO(TenenciasDetalleOutEntity outDAO)
			throws CopiarListaException {

		TenenciasDetalleDTO outDTO = new TenenciasDetalleDTO();

		List<DetalleCuotaPrestamoDTO> detalleCuotaPrestamoDTO = new ArrayList<DetalleCuotaPrestamoDTO>();
		if (outDAO.getDetalleCuotasPrestamosEntities() != null) {
			TenenciasHelper.copiarLista(outDAO.getDetalleCuotasPrestamosEntities(), detalleCuotaPrestamoDTO,
					DetalleCuotaPrestamoDTO.class);
			outDTO.setDetalleCuotaPrestamoDTO(detalleCuotaPrestamoDTO);
		}

		List<DetalleTarjetaCreditoDTO> detalleTarjetaDTO = new ArrayList<DetalleTarjetaCreditoDTO>();
		if (outDAO.getDetalleTarjetaCreditoEntities() != null) {
			TenenciasHelper.copiarLista(outDAO.getDetalleTarjetaCreditoEntities(), detalleTarjetaDTO,
					DetalleTarjetaCreditoDTO.class);
			outDTO.setDetalleTarjetaDTO(detalleTarjetaDTO);
		}

		List<DetalleImpuestoMonedaExtranjeraDTO> detalleImpuestoMonedaExtranjeraDTO = new ArrayList<DetalleImpuestoMonedaExtranjeraDTO>();
		if (outDAO.getDetalleImpuestoMonedaExtranjeraEntities() != null) {
			TenenciasHelper.copiarLista(outDAO.getDetalleImpuestoMonedaExtranjeraEntities(),
					detalleImpuestoMonedaExtranjeraDTO, DetalleImpuestoMonedaExtranjeraDTO.class);
			outDTO.setDetalleImpuestoMonedaExtranjeraDTO(detalleImpuestoMonedaExtranjeraDTO);
		}

		List<DetalleMensualImpuestosDTO> detalleMensualImpuestosDTO = new ArrayList<DetalleMensualImpuestosDTO>();
		if (outDAO.getDetalleMensualImpuestosEntity() != null) {
			TenenciasHelper.copiarLista(outDAO.getDetalleMensualImpuestosEntity(), detalleMensualImpuestosDTO,
					DetalleMensualImpuestosDTO.class);
			outDTO.setDetalleMensualImpuestosDTO(detalleMensualImpuestosDTO);
		}

		List<DetallePlazoFijoDTO> detallePlazoFijoDTO = new ArrayList<DetallePlazoFijoDTO>();
		if (outDAO.getDetallePlazoFijoEntities() != null) {
			TenenciasHelper.copiarLista(outDAO.getDetallePlazoFijoEntities(), detallePlazoFijoDTO,
					DetallePlazoFijoDTO.class);
			for (DetallePlazoFijoDTO detallePFDto : detallePlazoFijoDTO) {
			    try { 
					if (detallePFDto.getNombrePlazoFijo() == null || detallePFDto.getNombrePlazoFijo().isEmpty()) {
						PlazoFijoEntity plazoFijoEntity = consultaPlazoFijoDAO.obtenerPorCodigo(detallePFDto.getTipo());
						if (plazoFijoEntity != null) {
							LOGGER.info("Levanto de la base el plazo fijo con codigo {} y utilizo la descripcion {}",
									detallePFDto.getTipo(), plazoFijoEntity.getDescripcion());
							detallePFDto.setTipo(plazoFijoEntity.getDescripcion());
						} else {
							LOGGER.info(
									"No se encontro el plazo fijo con codigo {} en la cache y se deja la descripcion vacia",
									detallePFDto.getTipo());
							detallePFDto.setTipo(StringUtils.EMPTY);
						}
					} else {
						LOGGER.info("Se obtuvo el nombre de plazo fijo PL",
								detallePFDto.getTipo());
						detallePFDto.setTipo(detallePFDto.getNombrePlazoFijo());
					}
                } catch (DAOException e) {
                    LOGGER.error("Error al consultar plazo fijo con el codigo {}, se deja la descripcion vacia.", detallePFDto.getTipo(), e);
                    detallePFDto.setTipo(StringUtils.EMPTY);
                }
            }
			outDTO.setDetallePlazoFijoDTO(detallePlazoFijoDTO);
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntities() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntities(), resumenCuentaInversionDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionDTO(resumenCuentaInversionDTO);
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionMONDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntitiesMON() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntitiesMON(), resumenCuentaInversionMONDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionesMONDTO(resumenCuentaInversionMONDTO);
		} else {
			outDTO.setResumenCuentaInversionesMONDTO(new ArrayList<ResumenCuentaInversionDTO>());
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionCEFDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntitiesCEF() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntitiesCEF(), resumenCuentaInversionCEFDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionesCEFDTO(resumenCuentaInversionCEFDTO);
		} else {
			outDTO.setResumenCuentaInversionesCEFDTO(new ArrayList<ResumenCuentaInversionDTO>());
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionSHSDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntitiesSHS() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntitiesSHS(), resumenCuentaInversionSHSDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionesSHSDTO(resumenCuentaInversionSHSDTO);
		} else {
			outDTO.setResumenCuentaInversionesSHSDTO(new ArrayList<ResumenCuentaInversionDTO>());
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionFONDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntitiesFON() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntitiesFON(), resumenCuentaInversionFONDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionesFONDTO(resumenCuentaInversionFONDTO);
		} else {
			outDTO.setResumenCuentaInversionesFONDTO(new ArrayList<ResumenCuentaInversionDTO>());
		}

		List<ResumenCuentaInversionDTO> resumenCuentaInversionBONDTO = new ArrayList<ResumenCuentaInversionDTO>();
		if (outDAO.getResumenCuentaInversionesEntitiesBON() != null) {
			TenenciasHelper.copiarLista(outDAO.getResumenCuentaInversionesEntitiesBON(), resumenCuentaInversionBONDTO,
					ResumenCuentaInversionDTO.class);
			outDTO.setResumenCuentaInversionesBONDTO(resumenCuentaInversionBONDTO);
		} else {
			outDTO.setResumenCuentaInversionesBONDTO(new ArrayList<ResumenCuentaInversionDTO>());
		}
		
		
		List<CuentasParticipanesDTO> cuentasDTO = new ArrayList<CuentasParticipanesDTO>();
		if(outDAO.getParticipantesPL()!= null) {
			List<CuentasParticipantesEntity> dParticipantes = new ArrayList<CuentasParticipantesEntity>();
			for(DatosParticipantesEntity partEntity : outDAO.getParticipantesPL()) {
				CuentasParticipantesEntity cParti = new CuentasParticipantesEntity();
				cParti.setNumeroCuenta(partEntity.getNroCuenta());
				dParticipantes.add(cParti);
			}
			
			TenenciasHelper.copiarLista(dParticipantes, cuentasDTO, CuentasParticipanesDTO.class);
			List<ParticipantesPLDTO> detalleParticipantes = new ArrayList<ParticipantesPLDTO>();
			List<ParticipantesEntity> detPartic = new ArrayList<ParticipantesEntity>();
			for (DatosParticipantesEntity item : outDAO.getParticipantesPL()) {
				for (ParticipantesEntity participantes: item.getParticipantes()) {
					ParticipantesEntity partEnt = new ParticipantesEntity();
					partEnt = participantes;
					detPartic.add(partEnt);
				}
			}
			TenenciasHelper.copiarLista(detPartic, detalleParticipantes, ParticipantesPLDTO.class);
			List<DatosParticipantesDTO> cuentasParticipantes = new ArrayList<DatosParticipantesDTO>();
			for(CuentasParticipanesDTO dCuenta:cuentasDTO) {
				DatosParticipantesDTO datosCuentaDto = new DatosParticipantesDTO();
				datosCuentaDto.setNumeroCuenta(dCuenta.getNumeroCuenta());
				List<ParticipantesPLDTO> partDTO = new ArrayList<ParticipantesPLDTO>();
				for(ParticipantesPLDTO partic: detalleParticipantes) {
					if(dCuenta.getNumeroCuenta().equals(partic.getNumeroContrato())) {
					partDTO.add(partic);
					}
				}
				datosCuentaDto.setParticipantesDTO(partDTO);
				cuentasParticipantes.add(datosCuentaDto);
			}
			outDTO.setParticipantesDTO(cuentasParticipantes);
		}
		return outDTO;
	}

	
	@Override
	public Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> cnsHabilitaCompraVentaUSD(String nup) {
		try {
			return respuestaFactory.crearRespuestaOk(tenenciasDetalleDAO.consultarHabilitacionCompraVtaUSD(nup));
		} catch (DAOException e) {
			LOGGER.error("Error al consultar habilitación de operaciones: Compra y Venta de USD.", e);
		}
		return respuestaFactory.crearRespuestaError(null);
	}
}