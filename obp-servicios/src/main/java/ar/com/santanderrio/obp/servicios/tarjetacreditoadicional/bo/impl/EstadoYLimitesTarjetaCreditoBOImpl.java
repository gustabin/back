/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.EstadoYLimitesTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;

/**
 * The Class EstadoYLimitesTarjetaCreditoBOImpl.
 */
@Component
public class EstadoYLimitesTarjetaCreditoBOImpl implements EstadoYLimitesTarjetaCreditoBO {

    /** The consulta tarjeta titular DAO. */
    @Autowired
    private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatosSolicitanteTarjetaAdicionalBOImpl.class);

    /**
     * Gets the respuesta factory.
     *
     * @return the respuesta factory
     */
    public RespuestaFactory getRespuestaFactory() {
        return respuestaFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.
     * EstadoYLimitesTarjetaCreditoBO#obtenerDetalleDatosTarjeta(ar.com.
     * santanderrio.obp.servicios.tarjetacreditoadicional.dto.
     * ConsultaDetalleDatosTarjetaInDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public ConsultaDetalleDatosTarjetaOutDTO obtenerDetalleDatosTarjeta(
            ConsultaDetalleDatosTarjetaInDTO consultaDetalleDatosTarjetaInDTO, Cliente cliente) throws DAOException {
        ConsultaDetalleDatosTarjetaInEntity consultaDetalleDatosTarjetaInEntity = new ConsultaDetalleDatosTarjetaInEntity();
        consultaDetalleDatosTarjetaInEntity.setCliente(cliente);
        consultaDetalleDatosTarjetaInEntity.setNroCuentaTarjeta(consultaDetalleDatosTarjetaInDTO.getNroCuentaTarjeta());
        consultaDetalleDatosTarjetaInEntity.setTipoTarjeta(consultaDetalleDatosTarjetaInDTO.getTipoTarjeta());
        LOGGER.info("Consuta de detalle de tarjeta.");
        ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity = consultaTarjetaTitularDAO
                .obtenerDetalleDatosTarjeta(consultaDetalleDatosTarjetaInEntity);
        ConsultaDetalleDatosTarjetaOutDTO consultaDetalleDatosTarjetaOutDTO = new ConsultaDetalleDatosTarjetaOutDTO(
                consultaDetalleDatosTarjetaOutEntity);
        return consultaDetalleDatosTarjetaOutDTO;
    }

}
