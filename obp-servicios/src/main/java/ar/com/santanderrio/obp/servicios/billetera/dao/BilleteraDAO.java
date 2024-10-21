/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao;

import javax.xml.ws.Holder;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPago;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPagoResponse;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.AltaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultaCuentaResult;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultarCuenta;

/**
 * The Interface BilleteraDAO.
 *
 */
public interface BilleteraDAO {

	/**
	 * The Enum TipoReporte.
	 */
	public enum TipoReporte {
		/** The adhesion. */
		ADHESION,
		/** The modificacion. */
		MODIFICACION
	}

	/**
	 * Administrar CBU bancos.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param banco
	 *            the banco
	 * @param canal
	 *            the canal
	 * @param tipoAcreditacion
	 *            the tipo acreditacion
	 * @param cbu
	 *            the cbu
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param monedaCuenta
	 *            the moneda cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param tipoNovedad
	 *            the tipo novedad
	 * @param codBanco
	 *            the cod banco
	 * @param cuit
	 *            the cuit
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("all")
	String administrarCBUBancos(int idCuenta, String banco, int canal, String tipoAcreditacion, String cbu,
			String tipoCuenta, String monedaCuenta, String nroCuenta, String tipoNovedad, String codBanco, String cuit)
			throws DAOException;

	/**
	 * Administrar clave bancos.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param banco
	 *            the banco
	 * @param contrasenia
	 *            the contrasenia
	 * @param respPregSeguridad
	 *            the resp preg seguridad
	 * @param tipoNovedad
	 *            the tipo novedad
	 * @param canal
	 *            the canal
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String administrarClaveBancos(int idCuenta, String banco, String contrasenia, String respPregSeguridad,
			String tipoNovedad, int canal) throws DAOException;

	/**
	 * Administrar medio pago.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param banco
	 *            the banco
	 * @param canal
	 *            the canal
	 * @param mediosPago
	 *            the medios pago
	 * @return the medios pago response
	 * @throws DAOException
	 *             the DAO exception
	 */
	MediosPagoResponse administrarMedioPago(int idCuenta, String banco, String canal, MediosPago mediosPago)
			throws DAOException;

	/**
	 * Alta cuenta.
	 *
	 * @param parameters
	 *            the parameters
	 * @param status
	 *            the status
	 * @param idCuenta
	 *            the id cuenta
	 * @throws DAOException
	 *             the DAO exception
	 */
	void altaCuenta(AltaCuentaRequest parameters, Holder<String> status, Holder<String> idCuenta) throws DAOException;

	/**
	 * consultarCuenta.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the consulta cuenta result
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaCuentaResult consultarCuenta(ConsultarCuenta parameters) throws DAOException;

	/**
	 * Generar comprobante.
	 *
	 * @param tipoReporte
	 *            the tipo reporte
	 * @return the reporte
	 */
	Reporte generarComprobante(TipoReporte tipoReporte);
}