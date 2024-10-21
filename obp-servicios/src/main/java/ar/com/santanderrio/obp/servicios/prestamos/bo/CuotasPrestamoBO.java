package ar.com.santanderrio.obp.servicios.prestamos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;

/**
 * The Interface CuotasPrestamoBO.
 */
public interface CuotasPrestamoBO {

    /**
     * Consultar proximas cuotas.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @return the deuda prestamo entity
     * @throws BusinessException
     *             the business exception
     */
    DeudaPrestamoEntity consultarProximasCuotas(Cliente cliente, Cuenta cuenta) throws BusinessException;

    /**
     * Consultar cuotas pagas.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @param fechaInicio
     *            the fecha inicio
     * @return the consulta cuota paga out entity
     * @throws BusinessException
     *             the business exception
	 * @throws SinCuotasPagasException
	 *             the sin cuotas pagas exception
     */
    ConsultaCuotaPagaOutEntity consultarCuotasPagas(Cliente cliente, Cuenta cuenta, String fechaInicio)
            throws BusinessException, SinCuotasPagasException;

    /**
     * Consultar interviniente prestamo.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @return the interviniente
     * @throws BusinessException
     *             the business exception
     */
    Interviniente consultarIntervinientePrestamo(Cliente cliente, Cuenta cuenta) throws BusinessException;

    Respuesta<Reporte> exportarCuotasPagas(Cliente cliente, ProximasCuotasView cuotasPagas);

    Respuesta<Reporte> exportarProximasCuotas(Cliente cliente, ProximasCuotasView proximasCuotas);
}
