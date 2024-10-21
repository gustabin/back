/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.recuperaciones.dao.impl.RefinancingDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;

/**
 * The Class ConsultasControllerHomeBOImpl.
 */
@Component
public class ConsultasControllerHomeBOImpl implements ConsultasControllerHomeBO {

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;
	
	@Autowired
	private SesionParametros sesionParametros;
	
	@Autowired
	private RefinancingDAOImpl refinancingDao;	
	
	@Autowired
	private SesionCliente sesionCliente;
	
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;
	
	/** The turnos web caja habilitado general. */
	@Value("${TURNOSWEB.CAJA.HABILITADO.GENERAL:0}")
	private String turnosWebCajaHabilitadoGeneral;

	/** The turnos web ejecutivo habilitado general. */
	@Value("${TURNOSWEB.EJECUTIVO.HABILITADO.GENERAL:0}")
	private String turnosWebEjecutivoHabilitadoGeneral;
	
	/**
	 * Aplica cuentas.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaCuentas(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaCuentas(Cliente cliente) {
		return cuentaBO.hasCuentasMonetariasActivas(cliente) || cuentaBO.hasCuentasMonetariasCerradas(cliente);
	}

	/**
	 * Aplica cuentas banca privada.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaCuentasBancaPrivada(ar.com.santanderrio.obp.servicios.clientes.
	 * entities. Cliente)
	 */
	@Override
	public Boolean aplicaCuentasBancaPrivada(Cliente cliente) {
		return CollectionUtils.isNotEmpty(cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente));
	}
	
	/**
	 * Aplica tarjetas.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaTarjetas(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaTarjetas(Cliente cliente) {
		try {
			if (CollectionUtils.isNotEmpty(cliente.getCuentas())) {
				List<Cuenta> tarjetas = TarjetaBOUtils.filtrarCuentasDeTipoCuentaTarjeta(cliente.getCuentas());
				if (CollectionUtils.isNotEmpty(tarjetas)) {
					return true;
				}
			}
		} catch (TarjetaBOUtilsException e) {
			return false;
		}
		return false;
	}

	/**
	 * Aplica prestamos.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaPrestamos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaPrestamos(Cliente cliente) {
		return true;
	}

	/**
	 * Aplica comprobantes.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaComprobantes(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaComprobantes(Cliente cliente) {
		return true;
	}

	/**
	 * Aplica ahorros beneficios.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaAhorrosBeneficios(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Boolean aplicaAhorrosBeneficios(Cliente cliente) {
		return true;
	}

	/**
	 * Aplica seguros.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaSeguros(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaSeguros(Cliente cliente) {
		return true;
	}

	/**
	 * Aplica tarjeta monedero.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaTarjetaMonedero(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente)
	 */
	@Override
	public Boolean aplicaTarjetaMonedero(Cliente cliente) {

		List<Cuenta> productos = cliente.getCuentas();

		for (Cuenta cuenta : productos) {
			if (cuenta.getTipoCuenta().equals(TipoCuenta.TARJETA_MONEDERO.getCodigo().toString())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Aplica resumen impositivo.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#
	 * aplicaResumenImpositivo(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Boolean aplicaResumenImpositivo(Cliente cliente) {
		return true;
	}

	/**
	 * Tiene una sola cuenta.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#tieneUnaSolaCuenta(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean tieneUnaSolaCuenta(Cliente cliente) {
		return cuentaBO.tieneUnaSolaCuenta(cliente);		
	}

	/**
	 * Tiene una sola cuenta banca privada.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#tieneUnaSolaCuentaBancaPrivada(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean tieneUnaSolaCuentaBancaPrivada(Cliente cliente) {
		return cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente).size() == 1;
	}

	
	/**
	 * Aplica monedero.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#aplicaMonedero(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaMonedero(Cliente cliente) {
		Cuenta cuentaMonederoTitular = MonederoUtils.obtenerCuentaMonedero(cliente);
		Cuenta cuentaMonederoAdicional = MonederoUtils.obtenerCuentaMonederoAdicional(cliente);
		if(cuentaMonederoTitular != null || cuentaMonederoAdicional != null){
			return true;
		}
		return false;
	}

	/**
	 * Aplica E cheq.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO#aplicaECheq(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaECheq(Cliente cliente) {
		if(!cliente.getCuentas().isEmpty()) {
			return true;
		}
	    return false;
	}
	
	/**
	 * Aplica reclamos.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	@Override
	public Boolean aplicaReclamos(Cliente cliente) {
		return true;
	}

	/**
	 * Verificar si corresponde mostrar en menu
	 * la accion de gestor de deudas
	 */
	@Override
	public Boolean aplicaRecuperaciones() {		
		if(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH).tienePermisoDeVisibilidad()){
			synchronized (sesionCliente.getCliente()) {
				if(sesionParametros.isAplicaRefi() == null) {
					boolean aplicaRefi = refinancingDao.aplicaRefinanciacion();
					sesionParametros.setAplicaRefi(aplicaRefi);
					return aplicaRefi;
				}
				else return sesionParametros.isAplicaRefi();
			}
		}
		return false;
	}
	
	@Override
	public Boolean aplicaTurnosOnline(Cliente cliente) {
		if ("1".equals(turnosWebCajaHabilitadoGeneral) || "1".equals(turnosWebEjecutivoHabilitadoGeneral)) {
			return true; 
		} else {
			Segmento segmento = cliente.getSegmento();
			if (segmento != null && segmento.isSelect()) {
				return true;
			}
		}
		return false;
	}

}
