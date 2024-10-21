/*
 *
 */
package ar.com.santanderrio.obp.servicios.seguros.bo;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.seguros.dao.SeguroTenenciasDAO;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;
import ar.com.santanderrio.obp.servicios.seguros.entities.TipoSeguroImagenEnum;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

/**
 * The Class SeguroTenenciasBOImpl.
 */
@Component
public class SeguroTenenciasBOImpl implements SeguroTenenciasBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SeguroTenenciasBOImpl.class);

    /** The seguro tenencias DAO. */
    @Autowired
    private SeguroTenenciasDAO seguroTenenciasDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The seguro token. */
    @Autowired
    private SeguroToken seguroToken;

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO#
     * consultarSeguros(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Respuesta<PolizaDTO> consultarPoliza(String tokenJwt, Cliente cliente) {
        PolizaDTO polizasDTO = new PolizaDTO();
        Respuesta<PolizaDTO> respuesta = respuestaFactory.crearRespuestaOk(PolizaDTO.class);
        try {
            List<Poliza> polizas = seguroTenenciasDAO.consultarSegurosJwt(tokenJwt, cliente);
            polizasDTO.setPoliza(polizas);
            respuesta.setRespuesta(polizasDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

    @Override
    public Respuesta<PolizasDTO> consultarSeguros(Cliente cliente) {
        final List<TenenciasPolizaDTO> tenencias = new ArrayList<TenenciasPolizaDTO>();
        PolizasDTO polizasDTO = new PolizasDTO();
        Respuesta<PolizasDTO> respuesta = respuestaFactory.crearRespuestaOk(PolizasDTO.class);
        try {
            TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
            Respuesta<TokenDTO> token = seguroToken.armarToken(null, tokenExternoDTO);
            if (EstadoRespuesta.ERROR.equals(token.getEstadoRespuesta())) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY);
            }
            List<Poliza> polizas = seguroTenenciasDAO.consultarSeguros(token.getRespuesta().getTokenFirmado(), cliente);
            CollectionUtils.filter(polizas, new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    TenenciasPolizaDTO tenencia = new TenenciasPolizaDTO();
                    Poliza poliza = (Poliza) object;
                    tenencia.setTitulo(poliza.getTitulo());
                    tenencia.setNombreImagen(
                            TipoSeguroImagenEnum.getTipoImagenFromCodigo(poliza.getCodigoRamo().toString()));
                    tenencias.add(tenencia);
                    return false;
                }
            });
            polizasDTO.setPolizas(tenencias);
            respuesta.setRespuesta(polizasDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }


    @Override
    public Respuesta<GastoProtegidoDTO> consultarGastoProtegido(GastoProtegido gastoProtegido, Cliente cliente, String tokenJwt) {
        Respuesta<GastoProtegidoDTO> respuesta = respuestaFactory.crearRespuestaOk(GastoProtegidoDTO.class);
        try {
            GastoProtegidoDTO gastoProtegidoDTO = seguroTenenciasDAO.consultarGastosProtegidos(cliente, gastoProtegido, tokenJwt);
            respuesta.setRespuesta(gastoProtegidoDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

    @Override
    public Respuesta<CompraProtegidaDTO> consultarCompraProtegida(Cliente cliente, String numeroTarjeta, String numeroCuenta, String tipoTarjeta, String tokenJwt) {
        Respuesta<CompraProtegidaDTO> respuesta = respuestaFactory.crearRespuestaOk(CompraProtegidaDTO.class);
        try {
            CompraProtegidaDTO compraProtegidaDTO = seguroTenenciasDAO.consultarCompraProtegida(cliente, numeroTarjeta, numeroCuenta, tipoTarjeta, tokenJwt);
            respuesta.setRespuesta(compraProtegidaDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

    @Override
    public Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegrada(EmisionOfertaIntegrada nuevaEmisioView, Cliente cliente, String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida) {
        Respuesta<EmisionOfertaIntegradaDTO> respuesta = respuestaFactory.crearRespuestaOk(EmisionOfertaIntegradaDTO.class);
        try {
            TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
            Respuesta<TokenDTO> token = seguroToken.armarToken(null, tokenExternoDTO);
            if (EstadoRespuesta.ERROR.equals(token.getEstadoRespuesta())) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY);
            }
            EmisionOfertaIntegradaDTO emisionOfertaIntegradaDTO = seguroTenenciasDAO.emitirOfertaIntegrada(token.getRespuesta().getTokenFirmado(), cliente, nuevaEmisioView, numeroTarjeta, numeroCuenta, tarjetaElegida);
            respuesta.setRespuesta(emisionOfertaIntegradaDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

    @Override
    public Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegradaGastoProtegido(EmisionOfertaIntegrada nuevaEmisioView, Cliente cliente, String sucursal, String tipoCuenta, String nroCuenta, String descripcionServicioPago) {
        Respuesta<EmisionOfertaIntegradaDTO> respuesta = respuestaFactory.crearRespuestaOk(EmisionOfertaIntegradaDTO.class);
        try {
            TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
            Respuesta<TokenDTO> token = seguroToken.armarToken(null, tokenExternoDTO);
            if (EstadoRespuesta.ERROR.equals(token.getEstadoRespuesta())) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY);
            }
            EmisionOfertaIntegradaDTO emisionOfertaIntegradaDTO = seguroTenenciasDAO.emitirOfertaIntegradaGastoProtegido(token.getRespuesta().getTokenFirmado(), cliente, nuevaEmisioView, sucursal, tipoCuenta, nroCuenta, descripcionServicioPago);
            respuesta.setRespuesta(emisionOfertaIntegradaDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

    @Override
    public Respuesta<FlagCompraProtegidaDTO> flagCompraProtegida(Cliente cliente) {
        Respuesta<FlagCompraProtegidaDTO> respuesta = respuestaFactory.crearRespuestaOk(FlagCompraProtegidaDTO.class);
        try {

            TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
            Respuesta<TokenDTO> token = seguroToken.armarToken(null, tokenExternoDTO);
            if (EstadoRespuesta.ERROR.equals(token.getEstadoRespuesta())) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY);
            }
            FlagCompraProtegidaDTO flagCompraProtegidaDTO = seguroTenenciasDAO.obtenerFlagCompraProtegida(token.getRespuesta().getTokenFirmado());
            respuesta.setRespuesta(flagCompraProtegidaDTO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        }
        return respuesta;
    }

}