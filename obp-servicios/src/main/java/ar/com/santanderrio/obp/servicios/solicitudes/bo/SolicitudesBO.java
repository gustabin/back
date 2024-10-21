/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoResponseDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;


/**
 * The Interface SolicitudesBO.
 */
public interface SolicitudesBO {

	/**
	 * Obtener cuentas Y paquetes.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the SolicitudesDTO
	 */
	SolicitudesDTO obtenerCuentasYPaquetes(List<Cuenta> cuentas);

	/**
	 * Obtener tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentas
	 *            the cuentas
	 * @return the SolicitudesDTO
	 */
	SolicitudesDTO obtenerTarjetas(Cliente cliente, List<Cuenta> cuentas);


	/**
	 * Si el cliente es solo titular de una tarjeta de credito se envia
	 * <b>true</b></br>
	 * Si el cliente tiene una cuenta se envía <b>false</b></br>
	 * Si el cliente tiene una cuenta y tarjetas se envia <b>false</b></br>
	 * .
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return true, if successful
	 */
	boolean soloUnaTarjetaDeCredito(List<Cuenta> cuentas);

    /**
     * Obtener otros medios pago.
     *
     * @param cuentas the cuentas
     * @param monederosInactivos  the monederosInactivos
     * @return the solicitudes DTO
     */
    SolicitudesDTO obtenerOtrosMediosPago(List<Cuenta> cuentas, List<MonederoDTO> monederosInactivos);
    
    ValidaAltaView solicitudCtaTit();

    Boolean transferiSueldo(TransferiSueldoRequestDTO transferiSueldoRequestDTO);
}
