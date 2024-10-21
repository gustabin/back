/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.impl.DatosSelectoresDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;

/**
 * The Class DatosSelectoresBOImpl.
 */
@Component
public class DatosSelectoresBOImpl implements DatosSelectoresBO {

	/** The datos selectores DAO. */
	@Autowired
	private DatosSelectoresDAO datosSelectoresDAO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DatosSelectoresBOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerTiposDeDocumento()
	 */
	@Override
	public List<Opcion> obtenerTiposDeDocumento() {

		List<Opcion> tiposDeDocumento = new ArrayList<Opcion>();

		try {
			tiposDeDocumento = datosSelectoresDAO.obtenerTiposDeDocumento();
		} catch (DAOException e) {
			LOGGER.error("obtenerTiposDeDocumento", e);
		}
		return tiposDeDocumento;
	}

	/**
	 * Obtiene opciones formateadas.
	 *
	 * @param simbolo
	 *            the simbolo
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<Opcion> obtieneOpcionesFormateadas(String simbolo, List<Opcion> lista) {
		List<Opcion> opciones = new ArrayList<Opcion>();
		for (Iterator<Opcion> iterator = lista.iterator(); iterator.hasNext();) {
			Opcion opcion = (Opcion) iterator.next();

			// String opcionStr = simbolo + opcion.getOpcion();
			BigDecimal bd = new BigDecimal(opcion.getOpcion());
			String opcionStr = simbolo + ISBANStringUtils.formatearSaldo(bd);
			opcion.setOpcion(opcionStr);
			opciones.add(opcion);
		}
		return opciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerImportesARecargar()
	 */
	@Override
	public List<Opcion> obtenerImportesARecargar() {

		List<Opcion> importesARecargar = new ArrayList<Opcion>();

		try {
			importesARecargar = datosSelectoresDAO.obtenerImportesMaximosMensuales();
			return obtieneOpcionesFormateadas("$ ", importesARecargar);
		} catch (DAOException e) {
			LOGGER.error("obtenerImportesARecargar", e);
		}
		return importesARecargar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerLimitesDeRecargaMensual()
	 */
	@Override
	public List<Opcion> obtenerLimitesDeRecargaMensual() {

		List<Opcion> limitesDeRecargaMensual = new ArrayList<Opcion>();

		try {
			limitesDeRecargaMensual = datosSelectoresDAO.obtenerLimitesDeRecargaMensual();
			return obtieneOpcionesFormateadas("$ ", limitesDeRecargaMensual);
		} catch (DAOException e) {
			LOGGER.error("obtenerLimitesDeRecargaMensual", e);
		}
		return limitesDeRecargaMensual;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerPaisesDeNacimiento()
	 */
	@Override
	public List<Opcion> obtenerPaisesDeNacimiento() {

		List<Opcion> paisesDeNacimiento = new ArrayList<Opcion>();

		try {
			paisesDeNacimiento = datosSelectoresDAO.obtenerPaisesDeNacimiento();
		} catch (DAOException e) {
			LOGGER.error("obtenerPaisesDeNacimiento", e);
		}
		return paisesDeNacimiento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerProvincias()
	 */
	@Override
	public List<Opcion> obtenerProvincias() {

		List<Opcion> provincias = new ArrayList<Opcion>();

		try {
			provincias = datosSelectoresDAO.obtenerProvincias();
		} catch (DAOException e) {
			LOGGER.error("obtenerProvincias", e);
		}
		return provincias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerSexo()
	 */
	@Override
	public List<Opcion> obtenerSexo() {

		List<Opcion> sexo = new ArrayList<Opcion>();

		try {
			sexo = datosSelectoresDAO.obtenerSexo();
		} catch (DAOException e) {
			LOGGER.error("obtenerSexo", e);
		}
		return sexo;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
     * obtenerSexoCompleto()
     */
    @Override
    public List<Opcion> obtenerSexoCompleto() {

        List<Opcion> sexoCompleto = new ArrayList<Opcion>();

        try {
            sexoCompleto = datosSelectoresDAO.obtenerSexoCompleto();
        } catch (DAOException e) {
            LOGGER.error("obtenerSexoCompleto", e);
        }
        return sexoCompleto;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerEstadoCivil()
	 */
	@Override
	public List<Opcion> obtenerEstadoCivil() {

		List<Opcion> estadoCivil = new ArrayList<Opcion>();

		try {
			estadoCivil = datosSelectoresDAO.obtenerEstadoCivil();
		} catch (DAOException e) {
			LOGGER.error("obtenerEstadoCivil", e);
		}
		return estadoCivil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerNacionalidad()
	 */
	@Override
	public List<Opcion> obtenerNacionalidad() {

		List<Opcion> nacionalidad = new ArrayList<Opcion>();

		try {
			nacionalidad = datosSelectoresDAO.obtenerNacionalidad();
		} catch (DAOException e) {
			LOGGER.error("obtenerNacionalidad", e);
		}
		return nacionalidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerTipoDeMovimientoDescripcion(java.lang.String)
	 */
	@Override
	public String obtenerTipoDeMovimientoDescripcion(String tipoDeMovimiento) {
		try {
			return datosSelectoresDAO.obtenerOpcionDescripcion("tagTipoMov", tipoDeMovimiento);
		} catch (Exception e) {
			LOGGER.error("obtenerTipoDeMovimientoDescripcion", e);
		}
		return tipoDeMovimiento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerDescripcionPorOptionId(java.lang.String, java.lang.String)
	 */
	@Override
	public String obtenerDescripcionPorOptionId(String comboId, String optionId) {
		try {
			return datosSelectoresDAO.obtenerOpcionDescripcion(comboId, optionId);
		} catch (Exception e) {
			LOGGER.error("obtenerDescripcionDeCombo", e);
		}
		return optionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerPreguntasSeguridad()
	 */
	@Override
	public List<Opcion> obtenerPreguntasSeguridad() {

		List<Opcion> preguntasSeguridad = new ArrayList<Opcion>();

		try {
			preguntasSeguridad = datosSelectoresDAO.obtenerPreguntasSeguridad();
		} catch (DAOException e) {
			LOGGER.error("obtenerPrgSeguridad", e);
		}
		return preguntasSeguridad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerCantidadChequesComun()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequesComun() {

		List<Opcion> cantidadChequesComun = new ArrayList<Opcion>();

		try {
			cantidadChequesComun = datosSelectoresDAO.obtenerCantidadChequesComun();
		} catch (DAOException e) {
			LOGGER.error("obtenerCantidadChequesComun", e);
		}
		return cantidadChequesComun;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerCantidadChequesPagoDiferido()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequesPagoDiferido() {

		List<Opcion> cantidadChequesPagoDiferido = new ArrayList<Opcion>();

		try {
			cantidadChequesPagoDiferido = datosSelectoresDAO.obtenerCantidadChequesPagoDiferido();
		} catch (DAOException e) {
			LOGGER.error("obtenerCantidadChequesPagoDiferido", e);
		}
		return cantidadChequesPagoDiferido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerCantidadChequeraComun()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequeraComun() {

		List<Opcion> cantidadChequeraComun = new ArrayList<Opcion>();

		try {
			cantidadChequeraComun = datosSelectoresDAO.obtenerCantidadChequeraComun();
		} catch (DAOException e) {
			LOGGER.error("obtenerCantidadChequeraComun", e);
		}
		return cantidadChequeraComun;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerCantidadChequeraPagoDiferido()
	 */
	@Override
	public List<Opcion> obtenerCantidadChequeraPagoDiferido() {

		List<Opcion> cantidadChequeraPagoDiferido = new ArrayList<Opcion>();

		try {
			cantidadChequeraPagoDiferido = datosSelectoresDAO.obtenerCantidadChequeraPagoDiferido();
		} catch (DAOException e) {
			LOGGER.error("obtenerCantidadChequeraPagoDiferido", e);
		}
		return cantidadChequeraPagoDiferido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerVinculos()
	 */
	@Override
	public List<Opcion> obtenerVinculos() {
		List<Opcion> vinculosComex = new ArrayList<Opcion>();

		try {
			vinculosComex = datosSelectoresDAO.obtenerVinculos();
		} catch (DAOException e) {
			LOGGER.error("obtenerVinculos", e);
		}
		return vinculosComex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerGastoACargo()
	 */
	@Override
	public List<Opcion> obtenerGastoACargo() {
		List<Opcion> gastosACargo = new ArrayList<Opcion>();

		try {
			gastosACargo = datosSelectoresDAO.obtenerGastoACargo();
		} catch (DAOException e) {
			LOGGER.error("obtenerGastosACargo", e);
		}
		return gastosACargo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerCodigoBancario()
	 */
	@Override
	public List<Opcion> obtenerCodigoBancario() {
		List<Opcion> codigoBancario = new ArrayList<Opcion>();

		try {
			codigoBancario = datosSelectoresDAO.obtenerCodigoBancario();
		} catch (DAOException e) {
			LOGGER.error("obtenerCodigoBancario", e);
		}
		return codigoBancario;
	}

    /* 
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
     * obtenerCondicionIVA()
     */
    @Override
    public List<Opcion> obtenerCondicionIVA() {
        List<Opcion> condicionIVA = new ArrayList<Opcion>();

        try {
            condicionIVA = datosSelectoresDAO.obtenerCondicionIVA();
        } catch (DAOException e) {
            LOGGER.error("obtenerCondicionIVA", e);
        }
        return condicionIVA;
    }

    /* 
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
     * obtenerCondicionIIBB()
     */
    @Override
    public List<Opcion> obtenerCondicionIIBB() {
        List<Opcion> condicionIIBB = new ArrayList<Opcion>();

        try {
            condicionIIBB = datosSelectoresDAO.obtenerCondicionIIBB();
        } catch (DAOException e) {
            LOGGER.error("obtenerCondicionIIBB", e);
        }
        return condicionIIBB;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerModalidadesECheq()
	 */
	@Override
	public List<Opcion> obtenerModalidadesECheq() {
		List<Opcion> modalidadesECheq = new ArrayList<Opcion>();

		try {
			modalidadesECheq = datosSelectoresDAO.obtenerModalidadesECheq();
		} catch (DAOException e) {
			LOGGER.error("obtenerModalidadesECheq", e);
		}
		return modalidadesECheq;
	}

	/*
	 * Obtiene tipos de endoso segun modalidad del Echeq
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#
	 * obtenerTiposEndosoECheq()
	 */
	@Override
	public List<Opcion> obtenerTiposEndosoECheq(String modalidadEcheq) {
		List<Opcion> tiposEndosoECheq = new ArrayList<Opcion>();

		try {
			tiposEndosoECheq = datosSelectoresDAO.obtenerTiposEndosoECheq(
					datosSelectoresDAO.obtenerIdOpcionPorDescripcion(DatosSelectoresDAOImpl.TAG_MODALIDADES_ECHEQ, modalidadEcheq));
		} catch (DAOException e) {
			LOGGER.error("obtenerTiposEndosoECheq", e);
		}
		return tiposEndosoECheq;
	}
	
	/**
	 * Obtiene id a traves de la descripcion
	 * de una de opcion del combo
	 * @param combo
	 * @param descripcion
	 * @return
	 */
	@Override
	public String obtenerIdOpcionPorDescripcion(String combo, String descripcion) {
		return datosSelectoresDAO.obtenerIdOpcionPorDescripcion(combo, descripcion);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO#obtenerMotivosEmail()
	 */
	@Override
	public List<Opcion> obtenerMotivosEmail() {
		List<Opcion> motivosEmail = new ArrayList<Opcion>();

		try {
			motivosEmail = datosSelectoresDAO.obtenerMotivosEmail();
		} catch (DAOException e) {
			LOGGER.error("motivosEmail", e);
		}
		return motivosEmail;
	}

	/**
     * Obtener actividades getnet.
     *
     * @return the list
     */
	@Override
    public List<Opcion> obtenerActividadesGetnet() {
		List<Opcion> actividadesGetnet = new ArrayList<Opcion>();

		try {
			actividadesGetnet = datosSelectoresDAO.obtenerActividadesGetnet();
		} catch (DAOException e) {
			LOGGER.error("obtenerActividadesGetnet", e);
		}
		return actividadesGetnet;
    }
    
    /**
     * Obtener ingresos getnet.
     *
     * @return the list
     */
	@Override
    public List<Opcion> obtenerIngresosGetnet() {
		List<Opcion> ingresosGetnet = new ArrayList<Opcion>();

		try {
			ingresosGetnet = datosSelectoresDAO.obtenerIngresosGetnet();
		} catch (DAOException e) {
			LOGGER.error("obtenerIngresosGetnet", e);
		}
		return ingresosGetnet;
    }
    
}
