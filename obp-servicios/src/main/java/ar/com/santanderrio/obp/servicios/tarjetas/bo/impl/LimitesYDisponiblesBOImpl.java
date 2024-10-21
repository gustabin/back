/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.LimitesYDisponiblesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimitesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSResumenCuentaImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class LimitesYDisponiblesBOImpl.
 */
@Component
public class LimitesYDisponiblesBOImpl extends TarjetasBOImpl implements LimitesYDisponiblesBO {

	private static final String TIPO_CUENTA_TARJETA_RECARGABLE = "77";

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LimitesYDisponiblesBOImpl.class);

	/** The Constant LOGGER_ERROR. */
	private static final String LOGGER_ERROR = "Error en calcular Limites y Disponibles";

	/** The parseador. */
	@Autowired
	private ParseadorWSResumenCuentaImpl parseador;

	/** The legal DAO. */
	@Autowired
	private LegalDAO legalDAO;

	/**
	 * Obtener limite Y disponible DTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param retornoWS
	 *            the retorno WS
	 * @return List<TarjetaActiva>
	 */
	@Override
	public LimitesYDisponiblesDTO obtenerLimiteYDisponibleDTO(Cuenta cuenta, RetornoTarjetasEntity retornoWS) {
		try {
			if (cuenta != null && !tieneErrorDeCredenciales(retornoWS)) {
				TarjetaEntity tarjetaEntity = parseador.obtenerTarjetaPorNroTarjetaActiva(retornoWS,
						cortarNroTarjetaDesdeCuenta(cuenta));
                if (!parseador.tarjetaTieneError(tarjetaEntity)
                        && (parseador.esCategoriaTitular(tarjetaEntity) || TIPO_CUENTA_TARJETA_RECARGABLE.equals(cuenta.getTipoCuenta()))
                        && tarjetaEntity.getTarjetaDocument().getDatosCuenta().getLimites() != null) {
					return crearLimitesYDisponiblesDTO(cuenta, tarjetaEntity);
				}
			}
		} catch (ParseadorVisaException e) {
			LOGGER.error(LOGGER_ERROR, e);
		} catch (Exception e) {
			LOGGER.error(LOGGER_ERROR, e);
		}
		return null;
	}
	
	
	/**
	 * ver control entity.getErrorTarjetas()
	 *
	 * @param entity
	 *            the entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private Boolean tieneErrorDeCredenciales(RetornoTarjetasEntity entity) throws ParseadorVisaException {
        if (entity == null) {
            throw new ParseadorVisaException();
        }
        if (entity.getError() == null && entity.getTarjetas() == null) {
            throw new ParseadorVisaException();
        }
        return entity.getError() != null && entity.getError();
    }

	
	/**
	 * Buscar limite Y disponible de cuenta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param listaLimitesYDisponibles
	 *            the lista limites Y disponibles
	 * @return the limites Y disponibles DTO
	 */
	@Override
	public LimitesYDisponiblesDTO buscarLimiteYDisponibleDeCuenta(IdentificacionCuenta identificacionCuenta,
			Cliente cliente, List<LimitesYDisponiblesDTO> listaLimitesYDisponibles) {
		Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
		if (listaLimitesYDisponibles != null) {
			for (LimitesYDisponiblesDTO limitesYDisponible : listaLimitesYDisponibles) {
				if (limitesYDisponible.getNroTarjeta().trim().equals(cuenta.getNroTarjetaCredito().trim())) {
					limitesYDisponible.setLegales(obtenerLegales());
					return limitesYDisponible;
				}
			}
		}
		return null;
	}

	/**
	 * Obtener legales para Limites y Disponibles.
	 *
	 * @return the string
	 */
	private String obtenerLegales() {
		try {
			return legalDAO.obtenerLegal("1010");
		} catch (DAOException e) {
			LOGGER.error("No se encontro mensaje... ", e);
			return null;
		}
	}

	/**
	 * Crear limites Y disponibles DTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the list
	 */
	private LimitesYDisponiblesDTO crearLimitesYDisponiblesDTO(Cuenta cuenta, TarjetaEntity tarjetaEntity) {
		try {
			DatosEntity datos = parseador.obtenerDatos(tarjetaEntity);
			String marca = this.obtenerMarcaDeTarjeta(cuenta);

			LimitesYDisponiblesDTO limitesYDisponiblesDTO = new LimitesYDisponiblesDTO();

			limitesYDisponiblesDTO.setMarca(marca);
			limitesYDisponiblesDTO.setNroTarjeta(cuenta.getNroTarjetaCredito());
			limitesYDisponiblesDTO.setNroCuentaProducto(cuenta.getNroCuentaProducto());
			limitesYDisponiblesDTO.setNroTarjetaConFormato(TarjetaBOUtils
					.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), obtenerMarcaDeTarjeta(cuenta)));
			limitesYDisponiblesDTO.setSaldoDisponibleCompras(ISBANStringUtils
					.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.DISPONIBLE_COMPRAS)));
			limitesYDisponiblesDTO.setMostrarSaldoEnCompras(true);
			limitesYDisponiblesDTO.setLimiteCompraPesos(ISBANStringUtils
					.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.LIMITE_COMPRAS)));

			boolean isBuyLimitPesos = isLimitPesos(tarjetaEntity, TarjetaUtils.LIMITE_COMPRAS, cuenta);

			limitesYDisponiblesDTO.setIsLimiteCompraPesos(isBuyLimitPesos);
			limitesYDisponiblesDTO.setIsLimiteCompraDolares(!isBuyLimitPesos);
			limitesYDisponiblesDTO.setHasLimiteUnificado(tieneLimiteUnificado(tarjetaEntity));

			if (Boolean.TRUE.equals(limitesYDisponiblesDTO.getHasLimiteUnificado())) {
				limitesYDisponiblesDTO.setMostrarSaldoEnCuotas(false);
			} else {
				limitesYDisponiblesDTO.setSaldoDisponibleCuotas(TarjetaBOUtils
						.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.DISPONIBLE_CUOTAS)));
				limitesYDisponiblesDTO.setLimiteCuotasDolares(TarjetaBOUtils
						.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.LIMITE_CUOTAS)));

				boolean isInstallmentLimitPesos = isLimitPesos(tarjetaEntity, TarjetaUtils.LIMITE_CUOTAS, cuenta);

				limitesYDisponiblesDTO.setIsLimiteCuotasPesos(isInstallmentLimitPesos);
				limitesYDisponiblesDTO.setIsLimiteCuotasDolares(!isInstallmentLimitPesos);

				limitesYDisponiblesDTO.setMostrarSaldoEnCuotas(true);
			}

			limitesYDisponiblesDTO.setAdelantoEfectivoPesos(TarjetaBOUtils
					.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.ADELANTO_EFECTIVO)));

			limitesYDisponiblesDTO.setLimiteAdelantoEfectivoPesos(TarjetaBOUtils
					.formatearSaldo(obtenerSaldoTipo(tarjetaEntity, TarjetaUtils.LIMITE_ADELANTO)));
			limitesYDisponiblesDTO.setIsLimiteAdelantoPesos(true);
			limitesYDisponiblesDTO.setIsLimiteAdelantoDolares(false);

			return limitesYDisponiblesDTO;

		} catch (ImporteConvertException e) {
			LOGGER.error(LOGGER_ERROR, e);
		} catch (ParseadorVisaException e) {
		    LOGGER.error(LOGGER_ERROR, e);
		}
		return null;
	}

	/**
	 * Tiene limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public Boolean tieneLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException {
        return parseador.esCategoriaTitular(tarjeta)
                && TarjetaUtils.LIMITE_UNIFICADO.equals(obtenerLimitesUnificados(tarjeta).trim());
    }

	/**
	 * Obtener limites unificados.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private String obtenerLimitesUnificados(TarjetaEntity tarjeta) throws ParseadorVisaException {

        try {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getLimitesUnificados();
        } catch (Exception e) {
            throw new ParseadorVisaException();
        }
    }


    /**
	 * Obtener saldo tipo.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param tipoLimite
	 *            the tipo limite
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
    private BigDecimal obtenerSaldoTipo(TarjetaEntity tarjetaEntity, String tipoLimite) throws ImporteConvertException {
	    for(LimitesEntity limite:  tarjetaEntity.getTarjetaDocument().getDatosCuenta().getLimites().getLimites()){
	        if(tipoLimite.equals(limite.getDescripcion())) {
	            return ISBANStringUtils.convertirImporte(limite.getPesos());
	        }
	    }
        return null;
    }

	/**
	 * Comparar limite.
	 *
	 * @param l
	 *            the l
	 * @return the boolean
	 */
	public Boolean compararLimite(String l) {
		String n2 = l.replace(".", "");
		String n3 = n2.replace(",", ".");

		BigDecimal n = new BigDecimal(n3);
		return n.compareTo(TarjetaUtils.LIMITE_ADELANTO_VALOR) < 0;
	}

	/**
	 * Verifica si el limite es en pesos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 * @Modificacion 180119 - se saca TarjetaUtils.CLASE_CUENTA_L como limites en dolares
	 */
	private Boolean isAccountLimitInPesos(Cuenta cuenta) {
		if (cuenta != null) {
			if (StringUtils.equals(cuenta.getClaseCuenta(), TarjetaUtils.CLASE_CUENTA_S)
					        || StringUtils.equals(cuenta.getClaseCuenta(), TarjetaUtils.CLASE_CUENTA_H)) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
		return Boolean.TRUE;
	}


	/**
	 * Verifica si el limite es en pesos.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param tipoLimite
	 *            the tipo limite
	 */
	private Boolean isLimitPesos(TarjetaEntity tarjetaEntity, String tipoLimite, Cuenta cuenta) {
		for(LimitesEntity limite:  tarjetaEntity.getTarjetaDocument().getDatosCuenta().getLimites().getLimites()){
			if(tipoLimite.equals(limite.getDescripcion())) {
				if(limite.getMoneda() == null) {
					LOGGER.info("Currency not found for limit {}", tipoLimite);
					return isAccountLimitInPesos(cuenta);
				}
				return Currency.ARS.equals(limite.getMoneda());
			}
		}
		return false;
	}

}