/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosInicioCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InicioCancelarStopDebitDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CancelarStopDebitTarjetaManager;

/**
 * The Class CancelarStopDebitTarjetaManagerImpl.
 *
 * @author mariano.g.pulera
 */
@Component
public class CancelarStopDebitTarjetaManagerImpl implements CancelarStopDebitTarjetaManager {

    /** The respuesta factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /** The stop debit tarjetas BO. */
    @Autowired
    StopDebitTarjetasBO stopDebitTarjetasBO;

    /** The pago tarjeta credito BO. */
    @Autowired
    PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

    /** The mensaje bo. */
    @Autowired
    MensajeBO mensajeBo;

    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;

    /** The estadistica manager. */
    @Autowired
    EstadisticaManager estadisticaManager;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagosTarjetaManagerImpl.class);

    /** The Constant CODIGO_MENSAJE_INICIO_FLUJO. */
    private static final String CODIGO_MENSAJE_INICIO_FLUJO = "1285";

    /** The Constant ERROR_REINTENTAR_CANCELAR_STOP_DEBIT_TARJETA. */
    private static final String ERROR_REINTENTAR_CANCELAR_STOP_DEBIT_TARJETA = "ERROR_REINTENTAR_CANCELAR_STOP_DEBIT_TARJETA";

    /** The Constant ERROR_LIMITE_HORARIO. */
    private static final String ERROR_NO_HAY_SOLICITUDES_VIGENTES = "ERROR_NO_HAY_SOLICITUDES_VIGENTES";

    /** The Constant MENSAJE_INFORMATIVO_INICIO_CANCELAR_STOP_DEBIT. */
    private static final String MENSAJE_INFORMATIVO_INICIO_CANCELAR_STOP_DEBIT = "1656";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * CancelarStopDebitTarjetaManager#inicioCancelarStopDebit(ar.com.
     * santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.tarjetas.view.
     * DatosInicioCancelarStopDebit)
     */
    @Override
    public Respuesta<InicioCancelarStopDebitDTO> inicioCancelarStopDebit(Cliente cliente,
            DatosInicioCancelarStopDebit datosInicioCancelarStopDebit) {

        InicioCancelarStopDebitDTO cancelarStopDebitDTO = new InicioCancelarStopDebitDTO();
        String nroTarjeta = datosInicioCancelarStopDebit.getNroTarjeta();
        Cuenta tarjetaElegida = cliente.getTarjeta(nroTarjeta);
        String abreviaturaTarjeta = StringUtils.upperCase(tarjetaElegida.getTipoCuentaEnum().getAbreviatura());
        String comillaDoble = "\"";
        String alias = tarjetaElegida.getAlias();
        String anexoMensaje;
        if (StringUtils.isNotBlank(alias)) {
            alias = abreviaturaTarjeta + " " + comillaDoble + alias + comillaDoble;
            anexoMensaje = alias;
        } else {
            anexoMensaje = nroTarjeta;
        }

        String mensajeInicio = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_INICIO_FLUJO).getMensaje();
        mensajeInicio = MessageFormat.format(mensajeInicio, anexoMensaje);

        cancelarStopDebitDTO.setMensajeDeInicio(mensajeInicio);
        cancelarStopDebitDTO.setAlias(alias);
        cancelarStopDebitDTO.setNroTarjeta(nroTarjeta);

        Date fecha = new Date();
        String stringFecha = ISBANStringUtils.formatearFecha(fecha);
        cancelarStopDebitDTO.setFechaDeSolicitud(stringFecha);
        cancelarStopDebitDTO.setMensajeInformativo(
                mensajeBo.obtenerMensajePorCodigo(MENSAJE_INFORMATIVO_INICIO_CANCELAR_STOP_DEBIT).getMensaje());

        estadisticaManager.add(EstadisticasConstants.ESTADISTICA_ACCESO_FLUJO_CANCELAR_STOP_DEBIT,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(InicioCancelarStopDebitDTO.class, cancelarStopDebitDTO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * CancelarStopDebitTarjetaManager#cancelarStopDebit(ar.com.santanderrio.obp
     * .servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.tarjetas.view.DatosCancelarStopDebit)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> cancelarStopDebit(Cliente cliente,
            DatosCancelarStopDebit datosCancelarStopDebit) {
        LOGGER.info("Entro al manager para cancelar el stop debit.");
        if (!"true".equals(datosCancelarStopDebit.getReintentar())) {
            sessionParametros.setContador(new ContadorIntentos(2));
        }

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        try {
            Cuenta tarjetaElegida = cliente.getTarjeta(datosCancelarStopDebit.getNroTarjeta());
            for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
                List<DebitoAutomatico> listaDebitos = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente,
                        cuenta);
                comprobante = revisarDeudasYCancelarStopDebit(listaDebitos, tarjetaElegida, cliente, cuenta,
                        datosCancelarStopDebit.getNroTarjeta());
                if (comprobante.getAccionRealizada()) {
                    break;
                }
            }
        } catch (BusinessException e) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_ACCION_CANCELAR_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return identificarRespuestasErroneas(e);
        }
        estadisticaManager.add(EstadisticasConstants.CODIGO_ACCION_CANCELAR_STOP_DEBIT,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);
    }

    /**
	 * Revisar deudas Y cancelar stop debit.
	 *
	 * @param listaDebitos
	 *            the lista debitos
	 * @param tarjetaElegida
	 *            the tarjeta elegida
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the comprobante feedback
	 * @throws BusinessException
	 *             the business exception
	 */
    private ComprobanteFeedbackView revisarDeudasYCancelarStopDebit(List<DebitoAutomatico> listaDebitos,
            Cuenta tarjetaElegida, Cliente cliente, Cuenta cuenta, String nroTarjeta) throws BusinessException {

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();

        if (listaDebitos.isEmpty()) {
            comprobante.setAccionRealizada(false);
            return comprobante;
        }
        for (DebitoAutomatico debitoAutom : listaDebitos) {
            String numeroPartidaDebitoFormateado = ISBANStringUtils.eliminarCeros(debitoAutom.getNumeroPartida());
            String numeroCuentaTarjetaFormateado = ISBANStringUtils
                    .eliminarCeros(tarjetaElegida.getNroCuentaProducto());
            if (numeroPartidaDebitoFormateado.equals(numeroCuentaTarjetaFormateado)) {
                DatosStopDebit datos = new DatosStopDebit();
                datos.setCodigoServicio(debitoAutom.getCodigoServicio());
                datos.setNroPartida(debitoAutom.getNumeroPartida());
                datos.setNroCuenta(cuenta.getNroCuentaProducto());
                datos.setSucursalCuenta(cuenta.getNroSucursal());
                datos.setTipoCuenta(cuenta.esCuentaUnica() ? debitoAutom.getTipoSubcuentaCU() : cuenta.getTipoCuenta());
                return stopDebitTarjetasBO.cancelarStopDebitTarjeta(cliente, datos, nroTarjeta);
            }
        }
        comprobante.setAccionRealizada(false);
        return comprobante;
    }

    /**
     * Identificar respuestas erroneas.
     *
     * @param e
     *            the e
     * @return the respuesta
     */
    private Respuesta<ComprobanteFeedbackView> identificarRespuestasErroneas(BusinessException e) {
        Respuesta<ComprobanteFeedbackView> respuesta;
        if (CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_NO_HAY_STOP_DEBIT.equals(e.getMessage())) {
            respuesta = respuestaFactory.crearRespuestaError("", ERROR_NO_HAY_SOLICITUDES_VIGENTES,
                    CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_NO_HAY_STOP_DEBIT);
        } else {
            respuesta = respuestaFactory.crearRespuestaError("", ERROR_REINTENTAR_CANCELAR_STOP_DEBIT_TARJETA,
                    CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO);
            if (sessionParametros.getContador().permiteReintentar()) {
                respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
            }
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * CancelarStopDebitTarjetaManager#
     * estadisticaVisualizacionComprobanteCancelacionStopDebit()
     */
    @Override
    public void estadisticaVisualizacionComprobanteCancelacionStopDebit() {
        estadisticaManager.add(EstadisticasConstants.VISUALIZACION_COMPROBANTE_CANCELAR_STOP_DEBIT_TARJETAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
