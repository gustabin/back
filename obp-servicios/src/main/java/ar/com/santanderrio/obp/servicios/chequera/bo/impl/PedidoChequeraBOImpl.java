/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.bo.PedidoChequeraBO;
import ar.com.santanderrio.obp.servicios.chequera.dao.PedidoChequeraDAO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraInDTO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraOutDTO;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraInEntity;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraOutEntity;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class PedidoChequeraBOImpl.
 */
@Component("pedidoChequeraBO")
public class PedidoChequeraBOImpl implements PedidoChequeraBO {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoChequeraBOImpl.class);

    /** The adhesion chequera DAO. */
    @Autowired
    private PedidoChequeraDAO pedidoChequeraDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBo;

    /** The Constant NO_HAY_CTA_PRINCIPAL_ERROR. */
    private static final String ERROR_CONFIRMACION_CHEQUERA = "ERROR_CONFIRMACION_CHEQUERA_PAGO";

    /** The Constant TIPO_CHEQUERA_COMUN. */
    private static final String TIPO_CHEQUERA_COMUN = "00";

    /** The Constant TIPO_CHEQUERA_PAGO_DIFERIDO. */
    private static final String TIPO_CHEQUERA_PAGO_DIFERIDO = "01";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.chequera.bo.PedidoChequeraBO#
     * confirmarPedidoChequera(ar.com.santanderrio.obp.servicios.chequera.dto.
     * ChequeraInDTO)
     */
    @Override
    public Respuesta<List<Respuesta<ChequeraOutDTO>>> confirmarPedidoChequera(ChequeraInDTO reqChequera) {
        List<Respuesta<ChequeraOutDTO>> respuestasSerivicioIATX = new ArrayList<Respuesta<ChequeraOutDTO>>();

        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(reqChequera.getCuenta());
        AbstractCuenta cuenta = obtenerCuenta(identificacionCuenta, reqChequera.getCliente());
        cuenta.setNroCuentaProducto(identificacionCuenta.getNroCuentaProducto());
        cuenta.setNroSucursal(identificacionCuenta.getNroSucursal());

        boolean esChequeraComun = true;
        generarChequera(reqChequera, respuestasSerivicioIATX, cuenta, esChequeraComun);
        generarChequera(reqChequera, respuestasSerivicioIATX, cuenta, !esChequeraComun);
        return convertirRespuestaDTO(respuestasSerivicioIATX);
    }

    /**
     * Obtener cuenta.
     *
     * @param identificacionCuenta
     *            the identificacion cuenta
     * @param cliente
     *            the cliente
     * @return the abstract cuenta
     */
    private AbstractCuenta obtenerCuenta(IdentificacionCuenta identificacionCuenta, Cliente cliente) {
        return getCuentaById(identificacionCuenta, cliente);
    }

    /**
     * Generar chequera.
     *
     * @param reqChequera
     *            the req chequera
     * @param respuestasSerivicioIATX
     *            the respuestas serivicio IATX
     * @param cuenta
     *            the cuenta
     * @param esChequeraComun
     *            the es chequera comun
     */
    private void generarChequera(ChequeraInDTO reqChequera, List<Respuesta<ChequeraOutDTO>> respuestasSerivicioIATX,
            AbstractCuenta cuenta, boolean esChequeraComun) {
        Respuesta<ChequeraOutDTO> respuetaChequeraOutDTO = new Respuesta<ChequeraOutDTO>();
        try {
            if (esChequeraComun) {
                if (reqChequera.isPedidoChequeraComun()) {
                    respuetaChequeraOutDTO = confirmarPedidoChequeras(reqChequera.getCantidadChequeComun(),
                            reqChequera.getCantidadChequeraComun(), TIPO_CHEQUERA_COMUN, cuenta,
                            reqChequera.getCliente());

                    respuestasSerivicioIATX.add(respuetaChequeraOutDTO);
                    grabarEstadisticaPedidoDeChequera(esChequeraComun, 
                    		EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                }
            } else {
                if (reqChequera.isPedidoChequeraPagoDiferido()) {
                    respuetaChequeraOutDTO = confirmarPedidoChequeras(reqChequera.getCantidadChequePagoDiferido(),
                            reqChequera.getCantidadChequeraPagoDiferido(), TIPO_CHEQUERA_PAGO_DIFERIDO, cuenta,
                            reqChequera.getCliente());
                    respuestasSerivicioIATX.add(respuetaChequeraOutDTO);
                    grabarEstadisticaPedidoDeChequera(esChequeraComun, 
                    		EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                }
            }
        } catch (Exception e) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuetaChequeraOutDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuetaChequeraOutDTO.add(itemMensajeRespuesta);
            respuestasSerivicioIATX.add(respuetaChequeraOutDTO);
            grabarEstadisticaPedidoDeChequera(esChequeraComun, 
            		EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            LOGGER.error("generarChequera", e);
        }
    }

    /**
     * Graba estadistica pedido de chequera comun o pago diferido
     * 
     * @param esChequeraComun
     * @param codigo 1 = OK, 2 = ERROR
     */
    private void grabarEstadisticaPedidoDeChequera(boolean esChequeraComun, String codigo) {
		if(esChequeraComun) {
            estadisticaManager.add(EstadisticasConstants.PEDIDO_DE_CHEQUERA_COMUN,
            		codigo);
		}else {
            estadisticaManager.add(EstadisticasConstants.PEDIDO_DE_CHEQUERA_PAGO_DIFERIDO,
            		codigo);			
		}
	}

	/**
     * Convertir respuesta DTO.
     *
     * @param respuestasSerivicioIATX
     *            the respuestas serivicio IATX
     * @return the respuesta
     */
    private Respuesta<List<Respuesta<ChequeraOutDTO>>> convertirRespuestaDTO(
            List<Respuesta<ChequeraOutDTO>> respuestasSerivicioIATX) {
        boolean existeError = false;
        boolean existeOk = false;
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaPedidoChequera = new Respuesta<List<Respuesta<ChequeraOutDTO>>>();

        for (Respuesta<ChequeraOutDTO> e : respuestasSerivicioIATX) {
            if (e.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                existeOk = true;
            } else {
                existeError = true;
                respuestaPedidoChequera.add(e.getItemsMensajeRespuesta().get(0));
            }
        }

        /* Tratamiento Respuesta Warning */
        if (existeOk && existeError) {
            respuestaPedidoChequera.setEstadoRespuesta(EstadoRespuesta.WARNING);
        } else {
            /* Tratamiento Respuesta Ok */
            if (existeOk) {
                respuestaPedidoChequera.setEstadoRespuesta(EstadoRespuesta.OK);
            }
            /* Tratamiento Respuesta Error */
            else {
                respuestaPedidoChequera.setEstadoRespuesta(EstadoRespuesta.ERROR);
            }
        }
        respuestaPedidoChequera.setRespuesta(respuestasSerivicioIATX);
        return respuestaPedidoChequera;
    }

    /**
     * Confirmar pedido chequeras.
     *
     * @param cantidadCheque
     *            the cantidad cheque
     * @param cantidadChequera
     *            the cantidad chequera
     * @param tipoChequera
     *            the tipo chequera
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    private Respuesta<ChequeraOutDTO> confirmarPedidoChequeras(String cantidadCheque, String cantidadChequera,
            String tipoChequera, AbstractCuenta cuenta, Cliente cliente) {
        Respuesta<ChequeraOutDTO> respuestaChequeraDTO = new Respuesta<ChequeraOutDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

        try {
            Respuesta<PedidoChequeraOutEntity> pedidoChequeraOutEntity;
            pedidoChequeraOutEntity = pedidoChequeraDAO.confirmarPedidoChequera(
                    dtoToEntity(cantidadCheque, cantidadChequera, tipoChequera, cuenta, cliente));

            if (pedidoChequeraOutEntity.getEstadoRespuesta().equals(EstadoRespuesta.OK)
                    && pedidoChequeraOutEntity.getRespuesta() != null) {
                respuestaChequeraDTO = convertirEntityDTO(pedidoChequeraOutEntity.getRespuesta(), cuenta);
            } else {
                if (TipoError.ERROR_GENERICO.getDescripcion()
                        .equals(pedidoChequeraOutEntity.getItemsMensajeRespuesta().get(0).getTipoError())) {
                    ItemMensajeRespuesta itemMensajeRespuesta = crearItemMensajeErrorGenerico();
                    itemsMensajeRespuesta.add(itemMensajeRespuesta);
                } else {
                    ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
                    Mensaje msg = mensajeBo.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PEDIDO_CHEQUERA);
                    itemMensajeRespuesta.setMensaje(msg.getMensaje());
                    itemMensajeRespuesta.setTipoError(ERROR_CONFIRMACION_CHEQUERA);
                    itemsMensajeRespuesta.add(itemMensajeRespuesta);
                }
                for (ItemMensajeRespuesta item : pedidoChequeraOutEntity.getItemsMensajeRespuesta()) {

                    if(item.getMensaje().contains("BQE0091")){
                        LOGGER.info("***** INGRESA POR VALIDACION CHEQUERA.");
                        itemsMensajeRespuesta.clear();
                        item.setTipoError(ERROR_CONFIRMACION_CHEQUERA);
                        item.setMensaje("La cantidad de cheques solicitados supera el limite permitido");
                        itemsMensajeRespuesta.add(item);
                    }
                }
                respuestaChequeraDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
                respuestaChequeraDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
            }
        } catch (Exception e) {
            ItemMensajeRespuesta itemMensajeRespuesta = crearItemMensajeErrorGenerico();
            respuestaChequeraDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaChequeraDTO.add(itemMensajeRespuesta);
            LOGGER.error("confirmarPedidoChequera", e);
        }
        return respuestaChequeraDTO;
    }

    /**
     * Convertir entity DTO.
     *
     * @param pedidoChequeraEntity
     *            the pedido chequera entity
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    private Respuesta<ChequeraOutDTO> convertirEntityDTO(PedidoChequeraOutEntity pedidoChequeraEntity,
            AbstractCuenta cuenta) {
        ChequeraOutDTO chequeraDTO = new ChequeraOutDTO();
        chequeraDTO.setCuenta(cuenta);
        chequeraDTO.setNumeroComprobante(pedidoChequeraEntity.getNumeroComprobante());
        chequeraDTO.setSucursalEntrega(pedidoChequeraEntity.getSucursalEntrega().trim());
        chequeraDTO.setDomicilioSucursal(pedidoChequeraEntity.getDomicilioSucursal().trim());
        chequeraDTO.setLocalidadSucursal(pedidoChequeraEntity.getLocalidadSucursal().trim());
        chequeraDTO.setFechaEntrega(pedidoChequeraEntity.getFechaEntrega());
        chequeraDTO.setFechaTransaccion(pedidoChequeraEntity.getFechaTransaccion());
        chequeraDTO.setTipoChequera(pedidoChequeraEntity.getTipoChequera());
        chequeraDTO.setCantidadCheque(pedidoChequeraEntity.getCantidadCheques());
        chequeraDTO.setCantidadChequera(pedidoChequeraEntity.getCantidadChequera());
        return respuestaFactory.crearRespuestaOk(ChequeraOutDTO.class, chequeraDTO);
    }

    /**
     * Dto to entity.
     *
     * @param cantidadCheque
     *            the cantidad cheque
     * @param cantidadChequera
     *            the cantidad chequera
     * @param tipoChequera
     *            the tipo chequera
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @return the pedido chequera in entity
     */
    private PedidoChequeraInEntity dtoToEntity(String cantidadCheque, String cantidadChequera, String tipoChequera,
            AbstractCuenta cuenta, Cliente cliente) {
        PedidoChequeraInEntity pedidoChequeraInEntity = new PedidoChequeraInEntity();
        pedidoChequeraInEntity.setCliente(cliente);
        pedidoChequeraInEntity.setCuenta(cuenta);
        pedidoChequeraInEntity.setCantidadCheque(cantidadCheque);
        pedidoChequeraInEntity.setCantidadChequera(cantidadChequera);
        pedidoChequeraInEntity.setTipoChequera(tipoChequera);
        return pedidoChequeraInEntity;
    }

    /**
     * Gets the cuenta by id.
     *
     * @param id
     *            the id
     * @param cliente
     *            the cliente
     * @return the cuenta by id
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.bo.CuentaBO#getCuentaById(ar.com.
     * santanderrio.obp.cuentas.entities.IdentificacionCuenta,
     * ar.com.santanderrio.obp.clientes.entities.Cliente)
     */
    public AbstractCuenta getCuentaById(IdentificacionCuenta id, Cliente cliente) {
        return this.buscarCuentaPorIdentificador(cliente, id);
    }

    /**
     * Buscar cuenta por id.
     *
     * @param cliente
     *            the cliente
     * @param id
     *            the id
     * @return the abstract cuenta
     */
    public AbstractCuenta buscarCuentaPorIdentificador(Cliente cliente, IdentificacionCuenta id) {

        AbstractCuenta cuentaEncontrada = null;

        List<Cuenta> cuentas = cliente.getCuentas();
        if (cuentas != null) {
            cuentaEncontrada = encontrarCuenta(id, cuentas);
        }

        if (cuentaEncontrada == null) {
            List<CuentaCerrada> cuentasCerradas = cliente.getCuentasCerradas();
            if (cuentasCerradas != null) {
                cuentaEncontrada = encontrarCuentaCerrada(id, cuentasCerradas);
            }
        }
        return cuentaEncontrada;
    }

    /**
     * Encontrar cuenta.
     *
     * @param id
     *            the id
     * @param cuentas
     *            the cuentas
     * @return the abstract cuenta
     */
    private AbstractCuenta encontrarCuenta(IdentificacionCuenta id, List<Cuenta> cuentas) {
        AbstractCuenta cuentaEncontrada = null;
        for (Cuenta cuenta : cuentas) {
            if (coincideCuentaId(cuenta, id)) {
                cuentaEncontrada = cuenta;
            }
        }
        return cuentaEncontrada;
    }

    /**
     * Encontrar cuenta cerrada.
     *
     * @param id
     *            the id
     * @param cuentas
     *            the cuentas
     * @return the abstract cuenta
     */
    private AbstractCuenta encontrarCuentaCerrada(IdentificacionCuenta id, List<CuentaCerrada> cuentas) {
        AbstractCuenta cuentaEncontrada = null;
        for (CuentaCerrada cuenta : cuentas) {
            if (coincideCuentaId(cuenta, id)) {
                cuentaEncontrada = cuenta;
            }
        }
        return cuentaEncontrada;
    }

    /**
     * Coincide cuenta id.
     *
     * @param cuenta
     *            the cuenta
     * @param id
     *            the id
     * @return true, if successful
     */
    private boolean coincideCuentaId(AbstractCuenta cuenta, IdentificacionCuenta id) {
        boolean eqNroCuentaProducto = Long.parseLong(cuenta.getNroCuentaProducto()) == Long
                .parseLong(id.getNroCuentaProducto());
        boolean eqNroSucursal = Long.parseLong(cuenta.getNroSucursal()) == Long.parseLong(id.getNroSucursal());
        return eqNroCuentaProducto && eqNroSucursal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.chequera.bo.PedidoChequeraBO#
     * generarComprobanteChequera(ar.com.santanderrio.obp.servicios.chequera.
     * view.ChequeraConfirmacionViewResponse)
     */
    @Override
    public Respuesta<Reporte> generarComprobanteChequera(ChequeraConfirmacionViewResponse chequeraConfirmacionView) {
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        Reporte reporte = pedidoChequeraDAO.generarComprobanteChequera(chequeraConfirmacionView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
    }

    /**
	 * Crear item mensaje error generico.
	 *
	 * @return the item mensaje respuesta
	 */
    private ItemMensajeRespuesta crearItemMensajeErrorGenerico() {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        String codigoMensajeGenerico = "1435";
        Mensaje msg = mensajeBo.obtenerMensajePorCodigo(codigoMensajeGenerico);
        itemMensajeRespuesta.setMensaje(msg.getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        return itemMensajeRespuesta;
    }
}
