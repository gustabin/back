/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPermitidoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosPorCuenta;

/**
 * The Class PrestamoPermitidoBOImpl.
 */
@Component
public class PrestamoPermitidoBOImpl implements PrestamoPermitidoBO {


	/** The prestamo permitido DAO. */
	@Autowired
	private PrestamoPermitidoDAO prestamoPermitidoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoPermitidoBOImpl.class);
	
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
	/** The Constant CODIGO_ERROR_OPERACION_INHABILITADA. */
	private static final String CODIGO_ERROR_OPERACION_INHABILITADA = "10099906";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPermitidoBO#
	 * obtenerPrestamoConSaldoMayor(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Respuesta<PrestamoPermitidoDTO> obtenerPrestamoConSaldoMayor(Cliente cliente) {
		List<PrestamosPorCuenta> listaPrestamosPorCuenta = new ArrayList<PrestamosPorCuenta>();
		List<Cuenta> cuentasUnicasPesos = cliente.getCuentasUnicasPesos();
		PrestamoPermitidoDTO prestamoPermitidoDTO = new PrestamoPermitidoDTO();
		BigDecimal monto = BigDecimal.valueOf(-1);
		for (Cuenta cuenta:cuentasUnicasPesos) {
			PrestamosPorCuenta prestamos = obtenerPrestamoConSaldoMayor(cliente,cuenta);
			if (CODIGO_ERROR_OPERACION_INHABILITADA.equals(prestamos.getCodigoError())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, 
						CodigoMensajeConstantes.CONSULTA_PRESTAMO_PERMITIDO_INHABILITADA);
			}
			listaPrestamosPorCuenta.add(prestamos);
			BigDecimal importe = prestamos.getPrestamoMayor();
			if (importe!= null) {
				if (importe.compareTo(monto) == 1) {
					monto = importe;
				}
			}
		}
		sesionParametros.setPrestamosPorCuenta(listaPrestamosPorCuenta);
		if (monto != null) {
			prestamoPermitidoDTO.setMontoPrestamo(monto);
			return respuestaFactory.crearRespuestaOk(prestamoPermitidoDTO);
		}
		return respuestaFactory.crearRespuestaError(PrestamoPermitidoDTO.class, "", "");
	}

	private PrestamosPorCuenta obtenerPrestamoConSaldoMayor(Cliente cliente, Cuenta cuenta) {
		PrestamoPermitidoInEntity request = new PrestamoPermitidoInEntity();
		request.setCliente(cliente);
		request.setCuenta(cuenta);
		PrestamosPorCuenta rta = new PrestamosPorCuenta();
		rta.setCuenta(cuenta);
		BigDecimal importe1 = null;
		BigDecimal importe2 = null;
		PrestamoPermitidoEntity prestamoMayor = null;

		try {
			PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = prestamoPermitidoDAO
					.consultarPrestamosPermitidosPreacordados(request);
			List<PrestamoPermitidoEntity> listaResult = prestamoPermitidoOutEntity.getListaResult();
			rta.setPrestamos(prestamoPermitidoOutEntity);
			if (CollectionUtils.isNotEmpty(listaResult)) {
				for (PrestamoPermitidoEntity prestamoPermitidoEntity : listaResult) {
					if (prestamoMayor == null) {
						prestamoMayor = prestamoPermitidoEntity;
					} else {
						importe1 = ISBANStringUtils.stringToBigDecimal(prestamoMayor.getMaxImpPrest(), 13, 4, false);
						importe2 = ISBANStringUtils.stringToBigDecimal(prestamoPermitidoEntity.getMaxImpPrest(), 13, 4,
								false);
						if (importe1.compareTo(importe2) < 0) {
							prestamoMayor = prestamoPermitidoEntity;
						}
					}
				}
			}
		} catch (DAOException e) {
			LOGGER.error("Error consultando prestamos permitidos para la cuenta ", cuenta.getNroCuentaProducto());
			rta.setCodigoError(e.getErrorCode());
			return rta;
		}
		BigDecimal importeMayor = new BigDecimal(0);
		if(prestamoMayor != null){
			importeMayor = ISBANStringUtils.stringToBigDecimal(prestamoMayor.getMaxImpPrest(), 13, 4, false);
		}
		rta.setPrestamoMayor(importeMayor);
		return rta;
	}

}
