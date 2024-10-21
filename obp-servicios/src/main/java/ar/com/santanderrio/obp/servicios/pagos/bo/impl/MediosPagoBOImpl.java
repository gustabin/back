/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoComprasBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class MediosPagoBOImpl.
 *
 * @author B039636
 */
@Component
public class MediosPagoBOImpl implements MediosPagoBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoBOImpl.class);

    /** The medios pago DAO. */
    @Autowired
    private BuscadorEmpresaDAO buscadorMediosPagoDAO;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The tipos medio de pago BO. */
    @Autowired
    private List<TipoMedioPagoBO> tiposMedioDePagoBO;

    /** The tipos medio de pago compras BO. */
    @Autowired
    private List<TipoMedioPagoComprasBO> tiposMedioDePagoComprasBO;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#getByCodigo(java.
     * lang.String)
     */
    @Override
    public Respuesta<MedioPago> getByCodigo(String codigo) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por codigo: " + codigo);
        MedioPago medioPago = obtenerMedioPagoPorCodigo(codigo);
        LOGGER.debug("MEDIO DE PAGO - encontrado: " + medioPago);
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();

        if (medioPago != null) {
            respuesta.setRespuesta(medioPago);
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuestaVacia(false);
        } else {
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
            respuesta.setRespuestaVacia(true);
            respuesta.add(new ItemMensajeRespuesta(mensajeDAO.obtenerMensajePorCodigo("1060").getMensaje()));
        }

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#
     * obtenerMedioPagoPorCodigo(java.lang.String)
     */
    @Override
    public MedioPago obtenerMedioPagoPorCodigo(String codigo) {
        return buscadorMediosPagoDAO.getByCodigo(codigo.trim());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#getByCuitServicio
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<MedioPago> getByCuitServicio(String cuit, String servicio) {
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        try {
            respuesta.setRespuesta(buscadorMediosPagoDAO.getByCuitServicio(cuit, servicio));
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuestaVacia(false);
        } catch (DAOException e) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuesta.setRespuestaVacia(true);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#obtenerPorCodigo(
     * java.lang.String)
     */
    @Override
    public MedioPago obtenerPorCodigo(String codigo) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por codigo: " + codigo);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.searchByCode(codigo.trim());
        if (mediosPago.iterator().hasNext()) {
            return mediosPago.iterator().next();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#
     * obtenerPorNombreFantasia(java.lang.String)
     */
    @Override
    public MedioPago obtenerPorNombreFantasia(String termino) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por codigo: " + termino);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.searchEmpresaByNombreFantasia(termino.trim());
        if (mediosPago.iterator().hasNext()) {
            return mediosPago.iterator().next();
        }
        return null;
    }

    /**
     * Obtener por cod establecimiento.
     *
     * @param codigo
     *            the codigo
     * @return the medio pago
     */
    @Override
    public MedioPago obtenerPorCodEstablecimiento(String codigo) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por codigo establecimiento: " + codigo);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.searchByVisaCodEstablecimiento(codigo);
        if (mediosPago.iterator().hasNext()) {
            return mediosPago.iterator().next();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#
     * obtenerPorNombreFantasiaExacto(java.lang.String)
     */
    @Override
    public MedioPago obtenerPorNombreFantasiaExactoTarjeta(String termino) {
        LOGGER.info("MEDIO DE PAGO Tarjeta - Se buscara por codigo: " + termino);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.porNombreFantasiaTarjeta(termino.trim());
        if (mediosPago.iterator().hasNext()) {
            return mediosPago.iterator().next();
        }
        return null;
    }

    @Override
    public MedioPago obtenerPorNombreFantasiaPagoAutomatico(String termino) {
        LOGGER.info("MEDIO DE PAGO Debito Automatico - Se buscara por codigo: " + termino);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.porNombreFantasiaPagoAutomatico(termino.trim());
        for (MedioPago medioPago : mediosPago) {
            if (StringUtils.equalsIgnoreCase(medioPago.getNombreFantasia(), termino)) {
                return medioPago;
            }
        }
        return null;
    }

    /**
     * Obtener.
     *
     * @param medioPago
     *            the medio pago
     * @return the tipo nuevo pago enum
     */
    @Override
    public TipoMedioPagoBO obtenerTipoMedioPago(final MedioPago medioPago) {
        for (TipoMedioPagoBO tipoNuevoPagoBO : tiposMedioDePagoBO) {
            if (tipoNuevoPagoBO.esTipoNuevoPago(medioPago)) {
                return tipoNuevoPagoBO;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#
     * obtenerTipoNuevoPagoEnum(ar.com.santanderrio.obp.servicios.pagos.entities.
     * MedioPago)
     */
    @Override
    public TipoNuevoPagoEnum obtenerTipoNuevoPagoEnum(final MedioPago medioPago) {
        if ("S".equals(medioPago.getPpdctaHabilitado())) {
            for (TipoMedioPagoComprasBO tipoNuevoPagoComprasBO : tiposMedioDePagoComprasBO) {
                if (tipoNuevoPagoComprasBO.esTipoNuevoPago(medioPago)) {
                    return tipoNuevoPagoComprasBO.getTipoNuevoPagoEnum();
                }
            }
        }
        if ("S".equals(medioPago.getPesHabilitado())) {
            for (TipoMedioPagoBO tipoNuevoPagoBO : tiposMedioDePagoBO) {
                if (tipoNuevoPagoBO.esTipoNuevoPago(medioPago)) {
                    return tipoNuevoPagoBO.getTipoNuevoPagoEnum();
                }
            }
        }
        return null;
    }

    /**
     * Sets the tipos medio de pago BO.
     *
     * @param tiposMedioDePagoBO
     *            the new tipos medio de pago BO
     */
    public void setTiposMedioDePagoBO(List<TipoMedioPagoBO> tiposMedioDePagoBO) {
        this.tiposMedioDePagoBO = tiposMedioDePagoBO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO#
     * obtenerEmpresaPorIdAcuerdoCompras(java.lang.String)
     */
    @Override
    public MedioPago obtenerEmpresaPorIdAcuerdoCompras(String idAcuerdo) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por id acuerdo: " + idAcuerdo);
        Set<MedioPago> mediosPago = buscadorMediosPagoDAO.searchEmpresaByIdAcuerdoCompras(idAcuerdo);
        if (mediosPago.iterator().hasNext()) {
            return mediosPago.iterator().next();
        }
        return null;
    }
}
