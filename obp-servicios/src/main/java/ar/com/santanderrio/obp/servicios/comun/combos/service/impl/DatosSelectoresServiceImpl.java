/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;

/**
 * The Class ConsultarTagsMaximoVezServiceImpl.
 *
 * @author B042191
 */
@Component
public class DatosSelectoresServiceImpl implements DatosSelectoresService {

	/** The datos selectores BO. */
	@Autowired
	private DatosSelectoresBO datosSelectoresBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerTiposDeDocumento()
	 */
	@Override
	public List<Opcion> obtenerTiposDeDocumento() {
		return datosSelectoresBO.obtenerTiposDeDocumento();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerImportesARecargar()
	 */
	@Override
	public List<Opcion> obtenerImportesARecargar() {
		return datosSelectoresBO.obtenerImportesARecargar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerLimitesDeRecargaMensual()
	 */
	@Override
	public List<Opcion> obtenerLimitesDeRecargaMensual() {
		return datosSelectoresBO.obtenerLimitesDeRecargaMensual();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerPaisesDeNacimiento()
	 */
	@Override
	public List<Opcion> obtenerPaisesDeNacimiento() {
		return datosSelectoresBO.obtenerPaisesDeNacimiento();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerProvincias()
	 */
	@Override
	public List<Opcion> obtenerProvincias() {
		return datosSelectoresBO.obtenerProvincias();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerSexo() /* (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerSexo()
	 */
	@Override
	public List<Opcion> obtenerSexo() {
		return datosSelectoresBO.obtenerSexo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerEstadoCivil()
	 */
	@Override
	public List<Opcion> obtenerEstadoCivil() {
		return datosSelectoresBO.obtenerEstadoCivil();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerNacionalidad()
	 */
	@Override
	public List<Opcion> obtenerNacionalidad() {
		return datosSelectoresBO.obtenerNacionalidad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerTipoDeMovimientoDescripcion(java.lang.
	 * String)
	 */
	@Override
	public String obtenerTipoDeMovimientoDescripcion(String tipoDeMovimiento) {
		return datosSelectoresBO.obtenerTipoDeMovimientoDescripcion(tipoDeMovimiento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerPreguntasSeguridad()
	 */
	@Override
	public List<Opcion> obtenerPreguntasSeguridad() {
		return datosSelectoresBO.obtenerPreguntasSeguridad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerCantidadChequesComun()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequesComun() {
		return datosSelectoresBO.obtenerCantidadChequesComun();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerCantidadChequesPagoDiferido()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequesPagoDiferido() {
		return datosSelectoresBO.obtenerCantidadChequesPagoDiferido();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerCantidadChequeraComun()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequeraComun() {
		return datosSelectoresBO.obtenerCantidadChequeraComun();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.service.
	 * DatosSelectoresService#obtenerCantidadChequeraPagoDiferido()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequeraPagoDiferido() {
		return datosSelectoresBO.obtenerCantidadChequeraPagoDiferido();
	}

}
