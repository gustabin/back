/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeguroHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.seguros.dto.PolizasDTO;
import ar.com.santanderrio.obp.servicios.seguros.dto.TenenciasPolizaDTO;
import ar.com.santanderrio.obp.servicios.seguros.entities.TipoSeguroImagenEnum;

/**
 * The Class SeguroHomeManagerImpl.
 *
 * @author B043069
 */
@Component
public class SeguroHomeManagerImpl extends AbstractHomeManager implements SeguroHomeManager {

    /** The Constant TEXTO_SIN_SEGUROS. */
    private static final String TEXTO_SIN_SEGUROS = "Cotizá online ahora tu seguro";

    /** The Constant SEGUROS. */
    private static final String SEGUROS = "Seguros";
    
    /** The Constant TEXTO_LINK_CAJA_SEGURO. */
    private static final String TEXTO_LINK_CAJA_SEGURO= "Ver seguros";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The seguro tenencias BO. */
    @Autowired
    private SeguroTenenciasBO seguroTenenciasBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.
     * AbstractHomeManager#obtenerCajas()
     */
    @Override
    protected GrupoCajaHomeView obtenerCajas() {
        List<Caja> cajas = new ArrayList<Caja>();
        Caja caja = getCaja();
        cajas.add(caja);
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        return grupoCajaHomeView;
    }

    /**
     * Gets the caja.
     *
     * @return the caja
     */
    private Caja getCaja() {
        CajaHomeSegurosView cajaHomeSegurosView = new CajaHomeSegurosView();
        cajaHomeSegurosView.setIsSeguro(true);
        cajaHomeSegurosView.setEncabezado(SEGUROS);
        cajaHomeSegurosView.setIcono(TipoSeguroImagenEnum.SEGUROS_HOME_IMAGEN.getNombreImagen());
        cajaHomeSegurosView.setRecargar(Boolean.TRUE);
        cajaHomeSegurosView.setTitulo(TEXTO_SIN_SEGUROS);
        cajaHomeSegurosView.setTextoLink(TEXTO_LINK_CAJA_SEGURO);
        return cajaHomeSegurosView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * aplicaGrupo()
     */
    @Override
    public Respuesta<Boolean> aplicaGrupo() {
        return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * obtenerAccion()
     */
    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_HOME_SEGURO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.SeguroHomeManager#
     * obtenerSeguro()
     */
    @Override
    public Respuesta<CajaHomeSegurosView> obtenerSeguro() {
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<PolizasDTO> respuesta = seguroTenenciasBO.consultarSeguros(cliente);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            
        	CajaHomeSegurosView cajaHomeSeguroView = new CajaHomeSegurosView();
            cajaHomeSeguroView.setEncabezado(SEGUROS);
            cajaHomeSeguroView.setIcono(TipoSeguroImagenEnum.SEGUROS_HOME_IMAGEN.getNombreImagen());
            cajaHomeSeguroView.setTextoLink(TEXTO_LINK_CAJA_SEGURO);
            
            
            PolizasDTO poliza = respuesta.getRespuesta();
            List<TenenciasPolizaDTO> polizas = poliza.getPolizas();
            if (!polizas.isEmpty()) {
                Respuesta<CajaHomeSegurosView> respuestaSeguro = respuestaFactory
                        .crearRespuestaOk(CajaHomeSegurosView.class);
                int cantidad = polizas.size();
                if (cantidad == 1) {
                    TenenciasPolizaDTO tenenciaDTO = polizas.get(0);
                    cajaHomeSeguroView.setIdSeguro(tenenciaDTO.getNombreImagen().getCodigoRamo());
                    cajaHomeSeguroView.setTitulo(tenenciaDTO.getTitulo());
                } else {
                    cajaHomeSeguroView.setIdSeguro(TipoSeguroImagenEnum.SEGUROS_HOME_IMAGEN.getCodigoRamo());
                    cajaHomeSeguroView.setTitulo(SEGUROS);
                }
                cajaHomeSeguroView.setCantidad(cantidad);
                respuestaSeguro.setRespuesta(cajaHomeSeguroView);
                return respuestaSeguro;
            } else {
            	cajaHomeSeguroView.setIdSeguro(TipoSeguroImagenEnum.SEGUROS_HOME_IMAGEN.getCodigoRamo());
                cajaHomeSeguroView.setTitulo(TEXTO_SIN_SEGUROS);
                cajaHomeSeguroView.setCantidad(0);
                return respuestaFactory.crearRespuestaOk(CajaHomeSegurosView.class, cajaHomeSeguroView);
            }
        }
        return respuestaFactory.crearRespuestaError(CajaHomeSegurosView.class, StringUtils.EMPTY, StringUtils.EMPTY);
    }

}
