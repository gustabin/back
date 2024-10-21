/*
 * 
 */
package ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * Clase StopDebitTarjetasBOImpl.
 */
@Component
public class StopDebitTarjetasBOImpl implements StopDebitTarjetasBO {

    /** Variable stopDebitTarjetasDao. */
    @Autowired
    StopDebitTarjetasDAO stopDebitTarjetasDao;

    /** The mensaje dao. */
    @Autowired
    MensajeBO mensajeBO;

    /** The hora limite cancelacion. */
    @Value("${CANCELAR_STOP_DEBIT.HORA_HASTA}")
    private String horaLimiteCancelacion;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StopDebitTarjetasBOImpl.class);

    /**
     * Realizar stop debit tarjeta.
     *
     * @param cliente
     *            the cliente
     * @param datos
     *            the datos
     * @return the stop debit out
     * @throws DAOException
     *             the DAO exception
     * @see ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO#realizarStopDebitTarjeta(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     *      ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit)
     */
    @Override
    public StopDebitOut realizarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos) throws DAOException {
        return stopDebitTarjetasDao.realizarStopDebitTarjeta(cliente, datos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.
     * StopDebitTarjetasBO#cancelarStopDebitTarjeta(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.
     * DatosStopDebit, java.lang.String)
     */
    @Override
    public ComprobanteFeedbackView cancelarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos, String nroTarjeta)
            throws BusinessException {
        try {
            return stopDebitTarjetasDao.cancelarStopDebitTarjeta(cliente, datos, nroTarjeta);
        } catch (DAOException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.
     * StopDebitTarjetasBO#revisarHorarioSolicitud(java.lang.String)
     */
    @Override
    public Boolean revisarHorarioSolicitud(String fechaVencimiento) throws DAOException {
        String dia = fechaVencimiento.substring(0, 2);
        String mes = fechaVencimiento.substring(3, 5);
        String anio = fechaVencimiento.substring(6, fechaVencimiento.length());

        String horaLimite = horaLimiteCancelacion.substring(0, 2);
        String minutoLimite = horaLimiteCancelacion.substring(3, 5);

        if (anio.length() == 2) {
            anio = "20" + anio;
        }

        DateTime fechaActual = new DateTime();
        DateTime fechaSolicitud = new DateTime(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia),
                fechaActual.getHourOfDay(), fechaActual.getMinuteOfHour(), fechaActual.getSecondOfMinute());
        DateTime fechaComparacion = new DateTime(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia),
                Integer.parseInt(horaLimite), Integer.parseInt(minutoLimite), 0, 0);

        if (fechaSolicitud.isAfter(fechaComparacion)) {
            LOGGER.info("No se puede realizar el stop debit ni su cancelación, esta fuera de horario");
            String mensajeError = mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.STOP_DEBIT_TARJETA_FUERA_HORARIO).getMensaje();
            throw new DAOException(mensajeError, "NO_PERMITE_REINTENTOS");
        } else {
            return true;
        }
    }

}
