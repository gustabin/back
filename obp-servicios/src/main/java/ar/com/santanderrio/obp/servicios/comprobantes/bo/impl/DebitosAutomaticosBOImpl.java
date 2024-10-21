/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DebitosAutomaticosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesVisaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitosTarjetaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.InformeDebitosAutomaticosEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class DebitosAutomaticosBOImpl.
 */
@Component
public class DebitosAutomaticosBOImpl extends ComprobantesBOImpl implements DebitosAutomaticosBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebitosAutomaticosBOImpl.class);

    /** The parseador. */
    @Autowired
    private ParseadorWSInformeDebitosAutomaticosImpl parseador;

    /** The comprobantes visa DAO. */
    @Autowired
    private ComprobantesVisaDAO comprobantesVisaDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
     * TransferenciaInmediataBO#obtenerComprobantesAsync(ar.com.santanderrio.obp
     * .servicios.clientes.entities.Cliente)
     */
    @Async
    @Override
    public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesAsync(Cliente cliente) {
        LOGGER.info(LLAMANDO_METODO_ASINCRONICO);
        return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantes(cliente));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.DebitosAutomaticosBO
     * #obtenerComprobantes
     * (ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
     */
    @Override
    public Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente, TransaccionDTO dto) {
        LOGGER.info(INICIO_COMPROBANTES);
        try {
            List<Cuenta> productos = obtenerProductosVisa(cliente);
            List<Future<ComprobantesDTO>> listaFuture = obtenerListaDeHilosPorProductos(cliente, productos, dto);
            List<ComprobantesDTO> listaComprobantesDTO = procesarHilosYObtenerListaDeComprobantes(listaFuture);

            Respuesta<ComprobantesDTO> respuesta = generarRespuesta(listaComprobantesDTO, dto);
            LOGGER.info(FIN_COMPROBANTES, respuesta);
            return respuesta;
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error(ComprobantesBOEnum.ERROR_CALCULO_INFORMES.getId(), e);
            return crearRespuestaErrorGenerico();
        } catch (NullPointerException e) {
            LOGGER.error(CREANDO_RESPUESTA_ERROR, e);
            return crearRespuestaErrorGenerico();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
     * TransferenciaInmediataBO#obtenerComprobantesAsync(ar.com.santanderrio.obp
     * .servicios.clientes.entities.Cliente)
     */
    @Async
    @Override
    public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesAsync(Cliente cliente, TransaccionDTO dto) {
        LOGGER.info(LLAMANDO_METODO_ASINCRONICO);
        return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantes(cliente, dto));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.DebitosAutomaticosBO#
     * obtenerDebitosAutomaticos(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente) {
        LOGGER.info(INICIO_COMPROBANTES);
        try {
            TransaccionDTO dto = new TransaccionDTO();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 6);
            dto.setFechaDesde(cal.getTime());
            dto.setFechaHasta(new Date());
            List<Cuenta> productos = obtenerProductosVisa(cliente);
            List<Future<ComprobantesDTO>> listaFuture = obtenerListaDeHilosPorProductos(cliente, productos, dto);
            List<ComprobantesDTO> listaComprobantesDTO = procesarHilosYObtenerListaDeComprobantes(listaFuture);

            Respuesta<ComprobantesDTO> respuesta = generarRespuesta(listaComprobantesDTO, dto);
            LOGGER.info(FIN_COMPROBANTES, respuesta);
            return respuesta;
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error(ComprobantesBOEnum.ERROR_CALCULO_INFORMES.getId(), e);
            return crearRespuestaErrorGenerico();
        }
    }

    /**
     * Obtener productos visa del cliente.
     * 
     * @param cliente
     *            the cliente
     * @return the list
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    public List<Cuenta> obtenerProductosVisa(Cliente cliente) throws TarjetaBOUtilsException {
        List<Cuenta> productosTarjetas = new ArrayList<Cuenta>();
        List<Cuenta> productos = TarjetaBOUtils.filtrarCuentasDeTipoCuentaTarjeta(cliente.getCuentas());
        List<Cuenta> productosVisa = filtrarVisasYRecargables(productos);
        List<Cuenta> productosAmex = filtrarAmexs(productos);
        if (!productosVisa.isEmpty()) {
            productosTarjetas.addAll(productosVisa);
        }
        if (!productosAmex.isEmpty()) {
            productosTarjetas.addAll(productosAmex);
        }
        return productosTarjetas;
    }

    /**
     * Procesar hilos Y obtener lista de comprobantes.
     * 
     * @param listaFuture
     *            the lista future
     * @return the list
     */
    public List<ComprobantesDTO> procesarHilosYObtenerListaDeComprobantes(List<Future<ComprobantesDTO>> listaFuture) {
        List<ComprobantesDTO> listaRespuestas = new ArrayList<ComprobantesDTO>();
        if (!listaFuture.isEmpty()) {
            try {
                esperarHastaFinDeListaDeHilos(listaFuture);

                for (Future<ComprobantesDTO> thread : listaFuture) {
                    listaRespuestas.add(thread.get());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error(ComprobantesBOEnum.ERROR_CALCULO_INFORMES.getId(), e);
                listaRespuestas.add(new ComprobantesDTO(true));
            } catch (ExecutionException e) {
                LOGGER.error(ComprobantesBOEnum.ERROR_CALCULO_INFORMES.getId(), e);
                listaRespuestas.add(new ComprobantesDTO(true));
            }
        } else {
            listaRespuestas.add(new ComprobantesDTO(false));
        }
        return listaRespuestas;
    }

    /**
     * Obtener list future de comprobantes DTO.
     *
     * @param cliente
     *            the cliente
     * @param productos
     *            the productos
     * @param dto
     *            the dto
     * @return the list
     */
    public List<Future<ComprobantesDTO>> obtenerListaDeHilosPorProductos(Cliente cliente, List<Cuenta> productos,
            TransaccionDTO dto) {
        List<Future<ComprobantesDTO>> listaFuture = new ArrayList<Future<ComprobantesDTO>>();
        for (Cuenta producto : productos) {
            listaFuture.add(obtenerComprobantesDTOAsync(cliente, producto, dto));
        }
        return listaFuture;
    }

    /**
     * Obtener informe debitos automaticos async.
     *
     * @param cliente
     *            the cliente
     * @param producto
     *            the producto
     * @param dto
     *            the dto
     * @return the future
     */
    @Async
    public Future<ComprobantesDTO> obtenerComprobantesDTOAsync(Cliente cliente, Cuenta producto, TransaccionDTO dto) {
        LOGGER.info(LLAMANDO_METODO_ASINCRONICO);
        return new AsyncResult<ComprobantesDTO>(obtenerComprobantesDTOPorProducto(producto, dto));
    }

    /**
     * Generar comprobantes DTO desde producto.
     *
     * @param producto
     *            the producto
     * @param dto
     *            the dto
     * @return the comprobantes DTO
     */
    private ComprobantesDTO obtenerComprobantesDTOPorProducto(Cuenta producto, TransaccionDTO dto) {
        ComprobantesDTO comprobantesDTO = new ComprobantesDTO();

        RetornoTarjetasEntity retornoWS = obtenerInformesDebitosAutomaticosWS(producto, dto);

        LinkedList<ComprobanteDTO> informes = parcearRetornoWS(producto, retornoWS);

        if (!informes.isEmpty()) {
            if (tieneErrorDeComprobantes(informes)) {
                comprobantesDTO = new ComprobantesDTO(informes, true);
            } else {
                comprobantesDTO = new ComprobantesDTO(informes);
            }
        }
        return comprobantesDTO;
    }

    /**
     * Obtener informe debitos automaticos.
     *
     * @param producto
     *            the producto
     * @param dto
     *            the dto
     * @return the retorno tarjetas entity
     */
    private RetornoTarjetasEntity obtenerInformesDebitosAutomaticosWS(Cuenta producto, TransaccionDTO dto) {
        try {
            return comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(producto,
                    OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS, dto, producto.getCliente());
        } catch (DAOException e) {
            LOGGER.error(ComprobantesBOEnum.LOGGER_ERROR_VISA.getId(), e);
            return null;
        }
    }

    /**
     * Parcear retorno W sy listar. Se muestran los debitos automaticos del grupo
     * tarjeta solo si el valor del tag <tarjetaActiva> es igual al
     * Nro_tarjeta_credito (descartando de este campo los ceros a izquierda) de
     * alguna de las tarjetas: VISA retornadas en el servicio IDECLTSDO
     * 180.Nro_tarjeta_credito para el cliente, de lo contrario se ignoran. AMEX
     * retornadas en el servicio IDECLTSDO 180. Nro_tarjeta_credito para el cliente,
     * de lo contrario se ignoran.
     * 
     * 
     * @param cuenta
     *            the cuenta
     * @param entity
     *            the entity
     * @return the linked list
     * 
     */
    private LinkedList<ComprobanteDTO> parcearRetornoWS(Cuenta cuenta, RetornoTarjetasEntity entity) {
        try {
            if (entity != null && !parseador.tieneErrorDeCredenciales(entity)) {
                if (parseador.esTarjetaSinInformeDebitosAutomaticos(entity)) {
                    return new LinkedList<ComprobanteDTO>();
                }
                TarjetaEntity tarjetaEntity = parseador.obtenerPorNumeroDeTarjetaActiva(entity,
                        TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(cuenta.getNroTarjetaCredito(),
                                TarjetaUtils.getMarca(cuenta)));
                if (tarjetaEntity != null) {
                    return parsearInformeDebitosAutomaticosEntity(cuenta, tarjetaEntity);
                }
            }
        } catch (ParseadorVisaException e) {
            LOGGER.error(ComprobantesBOEnum.LOGGER_ERROR_PARCEADOR.getId(), e);
        }
        return new LinkedList<ComprobanteDTO>();
    }

    /**
     * Parcear informe debitos automaticos entity.
     * 
     * @param cuenta
     *            the cuenta
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the linked list
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private LinkedList<ComprobanteDTO> parsearInformeDebitosAutomaticosEntity(Cuenta cuenta,
            TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
        LinkedList<ComprobanteDTO> informes = new LinkedList<ComprobanteDTO>();

        InformeDebitosAutomaticosEntity informeDebitosAutomaticosEntity = parseador
                .obtenerInformeDebitosAutomaticos(tarjetaEntity);
        for (DebitosTarjetaEntity tarjeta : informeDebitosAutomaticosEntity.getDebitosTarjeta()){
        	for (DebitoEntity debitoEntity : parseador.obtenerListaDebitosTarjeta(tarjeta)) {
        		informes.add(crearComprobanteDTO(cuenta, debitoEntity, parseador.obtenerNombreTarjetaEnDebitos(tarjeta)));
        	}
        	
        }
        return informes;
    }

    /**
     * Crear comprobante DTO. Desde el tag <tarjetaActiva> es igual al
     * Nro_tarjeta_credito
     * 
     * @param cuenta
     *            the cuenta
     * @param debitoEntity
     *            the debito entity
     * @param nombreTarjeta
     *            the nombre tarjeta
     * @return the comprobante DTO
     */
    private ComprobanteDTO crearComprobanteDTO(Cuenta cuenta, DebitoEntity debitoEntity, String nombreTarjeta) {

        try {
            ComprobanteDTO cdto = new ComprobanteDTO();
            cdto.setFecha(formatearFechaAnio(parseador.obtenerFechaDeDebitoEntity(debitoEntity)));
            cdto.setDestinatario(ISBANStringUtils
                    .convertirStringToCamelcase(obtenerMedioDePago(parseador.obtenerCodEstablecimiento(debitoEntity))));
            cdto.setTipoOperacion(TipoOperacionComprobanteEnum.INFORME_DEBITOS_AUTOMATICOS);
            cdto.setHistorial(HistorialComprobanteEnum.DEBITO_AUTOMATICO_TARJETA);
            BigDecimal importePesos = parseador.obtenerConsumoPesos(debitoEntity);
            BigDecimal importeDolares = parseador.obtenerConsumoDolares(debitoEntity);

            String formatearNumeroTarjeta = TarjetaUtils.cortarYFormatearNumeroTarjeta(cuenta.getNroTarjetaCredito(),
                    TarjetaUtils.getMarca(cuenta));
            TipoCuenta tipoCuenta = TipoCuenta.AMEX;
            String medioDePago;
            if (TarjetaBOUtils.esTipoCuentaVisa(cuenta)) {
                tipoCuenta = TipoCuenta.VISA;
                medioDePago = TipoCuenta.VISA.getDescripcion().toUpperCase() + " " + formatearNumeroTarjeta;
            } else {
                medioDePago = "American Express" + " " + formatearNumeroTarjeta;
            }

            if (!importePesos.equals(new BigDecimal(0))) {
                cdto.setImportePesos(importePesos);
                cdto.setTipoCtaMedioDePagoPesos(tipoCuenta);
                cdto.setCtaMedioDePagoPesos(medioDePago);
            } else {
                cdto.setImporteDolares(importeDolares);
                cdto.setTipoCtaMedioDePagoDolares(tipoCuenta);
                cdto.setCtaMedioDePagoDolares(medioDePago);
            }

            cdto.setDetalle(obtenerDetalle(debitoEntity, nombreTarjeta));
            cdto.setTieneError(false);
            return cdto;
        } catch (ParseException e) {
            LOGGER.error(ComprobantesBOEnum.LOGGER_ERROR_PARCEADOR.getId(), e);
            return new ComprobanteDTO(true);
        } catch (ParseadorVisaException e) {
            LOGGER.error(ComprobantesBOEnum.LOGGER_ERROR_PARCEADOR.getId(), e);
            return new ComprobanteDTO(true);
        }
    }

    /**
     * Obtener detalle.
     * 
     * @param debitoEntity
     *            the debito entity
     * @param nombreTarjeta
     *            the nombre tarjeta
     * @return the detalle comprobante DTO
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private DetalleComprobanteDTO obtenerDetalle(DebitoEntity debitoEntity, String nombreTarjeta)
            throws ParseadorVisaException {
        DetalleComprobanteDebitoAutomaticoTarjetaDTO detalle = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        detalle.setDescripcionDebito(parseador.obtenerDescripcionDebito(debitoEntity).replaceAll("\\s{2,}", " "));
        detalle.setNombreTarjeta(nombreTarjeta.replace("/", " "));
        detalle.setTituloComprobante(CabeceraComprobantesEnum.DEBITO_AUTOMATICO_EN_TARJETA.getDetalle());
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.VISA_DEBITO_EN_TARJETA);
        return detalle;
    }

    /**
     * Tiene error de comprobantes.
     * 
     * @param informes
     *            the informes
     * @return true, if successful
     */
    private boolean tieneErrorDeComprobantes(LinkedList<ComprobanteDTO> informes) {
        boolean error = false;
        for (ComprobanteDTO comprobanteDTO : informes) {
            if (comprobanteDTO.getTieneError()) {
                error = true;
                break;
            }
        }
        return error;
    }

    /**
     * Generar respuesta comprobantes DTO.
     *
     * @param listaComprobantesDTO
     *            the lista comprobantes DTO
     * @param dto
     *            the dto
     * @return the respuesta
     */
    private Respuesta<ComprobantesDTO> generarRespuesta(List<ComprobantesDTO> listaComprobantesDTO,
            TransaccionDTO dto) {
        if (listaComprobantesDTO.isEmpty()) {
            return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, new ComprobantesDTO());
        } else {
            LinkedList<ComprobanteDTO> informes = new LinkedList<ComprobanteDTO>();
            boolean warning = false;
            for (ComprobantesDTO comprobantesDTO : listaComprobantesDTO) {
                if (comprobantesDTO != null && !comprobantesDTO.getTieneError()
                        && !comprobantesDTO.getComprobantes().isEmpty()) {
                    informes.addAll(comprobantesDTO.getComprobantes());
                }
                if (comprobantesDTO != null && comprobantesDTO.getTieneError()) {
                    warning = true;
                }
            }
            return crearRespuestaFinal(warning, informes, dto);
        }
    }

    /**
     * Crear respuesta final.
     *
     * @param warning
     *            the warning
     * @param comprobantes
     *            the comprobantes
     * @param dto
     *            the dto
     * @return the respuesta
     */
    private Respuesta<ComprobantesDTO> crearRespuestaFinal(boolean warning, List<ComprobanteDTO> comprobantes,
            TransaccionDTO dto) {
        List<ComprobanteDTO> informes = null;
        if (dto.getImporteDesde() != null && dto.getImporteHasta() != null) {
            informes = filtrarComprobantesPorImporte(comprobantes, dto.getImporteDesde(), dto.getImporteHasta());
        } else {
            informes = comprobantes;
        }
        if (!warning && CollectionUtils.isEmpty(informes)) {
            return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, new ComprobantesDTO(informes));
        }
        if (warning && !CollectionUtils.isEmpty(informes)) {
            return respuestaFactory.crearRespuestaWarning(ComprobantesDTO.class, new ComprobantesDTO(informes), null);
        }
        if (!warning && !CollectionUtils.isEmpty(informes)) {
            return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, new ComprobantesDTO(informes));
        } else
            return crearRespuestaErrorGenerico();

    }

    /**
     * Filtrar visas Y recargables.
     * 
     * @param productos
     *            the productos
     * @return the list
     */
    public List<Cuenta> filtrarVisasYRecargables(List<Cuenta> productos) {
        List<Cuenta> productosVisa = new ArrayList<Cuenta>();
        try {
            productosVisa = TarjetaBOUtils.filtrarVisasYRecargables(productos);
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error(ComprobantesBOEnum.LOGGER_ERROR_VISA.getId(), e);
        }
        return productosVisa;
    }

    /**
     * Filtrar amexs.
     * 
     * @param productos
     *            the productos
     * @return the list
     */
    public List<Cuenta> filtrarAmexs(List<Cuenta> productos) {
        List<Cuenta> productosAmex = new ArrayList<Cuenta>();
        try {
            productosAmex = TarjetaBOUtils.filtrarAmexs(productos);
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error(ComprobantesBOEnum.ERROR_CALCULO_INFORMES.getId(), e);
        }
        return productosAmex;
    }

}
