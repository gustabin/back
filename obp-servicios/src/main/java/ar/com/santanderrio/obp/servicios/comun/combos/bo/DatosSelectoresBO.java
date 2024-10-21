/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.bo;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;

/**
 * The Interface DatosSelectoresBO.
 */
public interface DatosSelectoresBO {

	/**
	 * Obtener tipos de documento.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerTiposDeDocumento();

	/**
	 * Obtener importes A recargar.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerImportesARecargar();

	/**
	 * Obtener limites de recarga mensual.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerLimitesDeRecargaMensual();

	/**
	 * Obtener paises de nacimiento.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerPaisesDeNacimiento();

	/**
	 * Obtener sexo.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerSexo();

	/**
     * Obtener sexo completo.
     *
     * @return the list
     */
	List<Opcion> obtenerSexoCompleto();

	/**
	 * Obtener estado civil.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerEstadoCivil();

	/**
	 * Obtener nacionalidad.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerNacionalidad();

	/**
	 * Obtener tipo de movimiento descripcion.
	 *
	 * @param tipoDeMovimiento
	 *            the tipo de movimiento
	 * @return the string
	 */
	String obtenerTipoDeMovimientoDescripcion(String tipoDeMovimiento);

	/**
	 * Obtener descripcion por option id.
	 *
	 * @param comboId
	 *            the combo id
	 * @param optionId
	 *            the option id
	 * @return the string
	 */
	String obtenerDescripcionPorOptionId(String comboId, String optionId);

	/**
	 * Obtener Preguntas de Seguridad.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerPreguntasSeguridad();

	/**
	 * Obtener Cantidad Cheques Comun.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequesComun();

	/**
	 * Obtener Cantidad Cheques Pago Diferido.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequesPagoDiferido();

	/**
	 * Obtener Cantidad Chequera Comun.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequeraComun();

	/**
	 * Obtener Cantidad Chequera Pago Diferido.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequeraPagoDiferido();

	/**
	 * Obtener opciones de Provincias.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerProvincias();
	
	/**
	 * Obtener vinculos.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerVinculos();
	
	/**
	 * Obtener gasto a cargo.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerGastoACargo();
	
	/**
	 * Obtener codigos bancarios.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerCodigoBancario();
	
	/**
	 * Obtener condicion IVA.
	 *
	 * @return the list
	 */
    List<Opcion> obtenerCondicionIVA();
    
    
    /**
     * Obtener condicion IIBB.
     *
     * @return the list
     */
    List<Opcion> obtenerCondicionIIBB();

    /**
     * Obtener modalidades ECheq.
     *
     * @return the list
     */
    List<Opcion> obtenerModalidadesECheq();

    /**
     * Obtener tipos endoso ECheq.
     *
     * @return the list
     */
    List<Opcion> obtenerTiposEndosoECheq(String modalidadEcheq);
    
    /**
     * Obtener motivos email.
     *
     * @return the list
     */
    List<Opcion> obtenerMotivosEmail();


    /**
     * Obtener actividades getnet.
     *
     * @return the list
     */
    List<Opcion> obtenerActividadesGetnet();
    
    /**
     * Obtener ingresos getnet.
     *
     * @return the list
     */
    List<Opcion> obtenerIngresosGetnet();

    /**
     * Obtiene id a traves de la descripcion
	 * de una de opcion del combo
     * @param combo
     * @param descripcion
     * @return
     */
	String obtenerIdOpcionPorDescripcion(String combo, String descripcion);
}
