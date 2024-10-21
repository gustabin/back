/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.bo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithCategoryDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RecargaCelularView;

/**
 * 
 * Esta clase toma el archivo de medios de pago para luego indexar en lucene y
 * realizar las busquedas sobre los indices creados.
 * 
 * @author pablo.martin.gore
 *
 */

@Service
public class BuscadorEmpresaBOImpl implements BuscadorEmpresaBO {

    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaBOImpl.class);

    /** The Constant SIN_COINCIDENCIAS. */
    private static final String SIN_COINCIDENCIAS = "1184";

    /** The Constant SIN_COINCIDENCIAS. */
    private static final String SIN_COINCIDENCIAS_PAGO_AUTOMATICO = "1279";

    /** Mensaje ayuda importe limite*. */
    private static final String IMPORTE_LIMITE = "1265";

    /** Mensaje ayuda empresa no permite pago automatico*. */
    private static final String NO_PERMITE_PAGO_AUTOMATICO = "1266";

    /** The Constant ERROR_BUSCADOR. */
    private static final String ERROR_BUSCADOR = "1277";

    /** The Constant ERROR_GENERICO_BUSCADOR. */
    private static final String ERROR_GENERICO_BUSCADOR = "1418";

    /** The Constant CAMPANIA_DA_INDICADOR. */
    private static final String CAMPANIA_DA_INDICADOR = "x";

    /** The debin recurrente DAO. */
    private DebinRecurrenteDAO debinRecurrenteDAO;
    
    /** The medios pago DAO. */
    private BuscadorEmpresaDAO buscadorEmpresaDAO;

    /** The medios pago BO. */
    private MediosPagoBO mediosPagoBO;

    /** The mensaje DAO. */
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory;

    /** The campania adhesion DA activo. */
    private String campaniaAdhesionDAActivo;

    /** The campania adhesion DA FIID. */
    private String campaniaAdhesionDAFIID;

    @Autowired
    public BuscadorEmpresaBOImpl(
            DebinRecurrenteDAO debinRecurrenteDAO,
            BuscadorEmpresaDAO buscadorEmpresaDAO,
            MediosPagoBO mediosPagoBO,
            MensajeBO mensajeBO,
            RespuestaFactory respuestaFactory,
            @Value("${CAMPANIA.ADHESION.DA.ACTIVO}") String campaniaAdhesionDAActivo,
            @Value("${CAMPANIA.ADHESION.DA.FIID}") String campaniaAdhesionDAFIID
    ) {
        this.debinRecurrenteDAO = debinRecurrenteDAO;
        this.buscadorEmpresaDAO = buscadorEmpresaDAO;
        this.mediosPagoBO = mediosPagoBO;
        this.mensajeBO = mensajeBO;
        this.respuestaFactory = respuestaFactory;
        this.campaniaAdhesionDAActivo = campaniaAdhesionDAActivo;
        this.campaniaAdhesionDAFIID = campaniaAdhesionDAFIID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> search(String term) {
        LOGGER.info("MEDIO DE PAGO - busqueda - termino de busqueda: " + term);
        List<MedioPagoView> pagoSelectionViews = new ArrayList<MedioPagoView>();
        try {
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.search(term);

            for (MedioPago medioPago : medioPagos) {
                MedioPagoView medioPagoView = new MedioPagoView(medioPago);
                TipoMedioPagoBO tipoMedioPagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);
                if (tipoMedioPagoBO != null) {
                    medioPagoView.setTipoNuevoPago(tipoMedioPagoBO.getTipoNuevoPagoEnum().getId());
                }
                pagoSelectionViews.add(medioPagoView);
            }
            return crearRespuestaOK(pagoSelectionViews, SIN_COINCIDENCIAS, term);
        } catch (Exception e) {
            LOGGER.error("Error al realizar la busqueda", e);
            return crearRespuestaError(pagoSelectionViews);
        }

    }

    /**
     * Search empresa nuevo pago.
     *
     * @param term the term
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO#searchEmpresaNuevoPago(java.lang.String)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchEmpresaNuevoPago(String term) {
        LOGGER.info("MEDIO DE PAGO - busqueda - termino de busqueda: " + term);
        List<MedioPagoView> pagoSelectionViews = new ArrayList<MedioPagoView>();
        try {
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.searchEmpresaNuevoPago(term);

            for (MedioPago medioPago : medioPagos) {
                MedioPagoView medioPagoView = new MedioPagoView(medioPago);

                TipoNuevoPagoEnum tipoNuevoPagoEnum = mediosPagoBO.obtenerTipoNuevoPagoEnum(medioPago);
                if (tipoNuevoPagoEnum != null) {
                    medioPagoView.setTipoNuevoPago(tipoNuevoPagoEnum.getId());
                }
                pagoSelectionViews.add(medioPagoView);
            }
            return crearRespuestaOK(pagoSelectionViews, SIN_COINCIDENCIAS, term);
        } catch (Exception e) {
            LOGGER.error("Error al realizar la busqueda", e);
            return crearRespuestaError(pagoSelectionViews);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchPagoAutomatico(String term) {
        LOGGER.info("MEDIO DE PAGO - busqueda automatica - termino de busqueda: " + term);
        List<MedioPagoView> pagoSelectionViews = new ArrayList<MedioPagoView>();
        try {
            List<String> campaniaAdhesionDAFIIDs = Arrays.asList(campaniaAdhesionDAFIID.split("\\|"));
            boolean isCampaniaAdhesionDAActivo = "1".equals(campaniaAdhesionDAActivo);
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.searchPagoAutomatico(term);

            for (MedioPago medioPago : medioPagos) {
                MedioPagoView pago = new MedioPagoView(medioPago);
                pago.setAddiGifFactura(StringUtils.replace(pago.getAddiGifFactura(), ";", ""));
                pago.setPesGifFactura(StringUtils.replace(pago.getPesGifFactura(), ";", ""));
                // Campania adhesion DA
                if (isCampaniaAdhesionDAActivo && campaniaAdhesionDAFIIDs.contains(pago.getFiid())) {
                    pago.setPesPAHabilitado(CAMPANIA_DA_INDICADOR);
                }
                pagoSelectionViews.add(pago);
            }
            return crearRespuestaOK(pagoSelectionViews, SIN_COINCIDENCIAS_PAGO_AUTOMATICO, term);
        } catch (Exception e) {
            LOGGER.error("Error al realizar la busqueda automatica", e);
            return crearRespuestaError(pagoSelectionViews);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<MedioPagoView> getByCodigo(String codigo) {
        LOGGER.info("MEDIO DE PAGO - Se buscara por codigo: " + codigo);
        Respuesta<MedioPagoView> respuesta = new Respuesta<MedioPagoView>();

        Respuesta<MedioPago> medioPagoRsta = mediosPagoBO.getByCodigo(codigo);
        respuesta.setEstadoRespuesta(medioPagoRsta.getEstadoRespuesta());

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            MedioPagoView medioPagoView = new MedioPagoView(medioPagoRsta.getRespuesta());
            respuesta.setRespuesta(medioPagoView);
            respuesta.setRespuestaVacia(false);
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        } else {
            respuesta.setEstadoRespuesta(medioPagoRsta.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(medioPagoRsta.getItemsMensajeRespuesta());
            respuesta.setRespuestaVacia(true);
        }

        return respuesta;
    }

    /**
     * Search empresa recarga celulares.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO#
     * searchEmpresaRecargaCelulares(java.lang.String)
     */
    @Override
    public Respuesta<EmpresaRecargaCelularView> searchEmpresaRecargaCelulares() {

        EmpresaRecargaCelularView empresaRecargaCelularView = new EmpresaRecargaCelularView();
        List<RecargaCelularView> listaEmpresasView = new ArrayList<RecargaCelularView>();

        try {
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.searchRecargaCelulares();

            for (MedioPago medioPago : medioPagos) {
                RecargaCelularView recargaCelularView = new RecargaCelularView();
                BeanUtils.copyProperties(medioPago, recargaCelularView);
                recargaCelularView
                        .setPesIdentificacion(WordUtils.capitalizeFully(recargaCelularView.getPesIdentificacion()));
                recargaCelularView.setPesGifFactura(recargaCelularView.getPesGifFactura().replaceAll(";", ""));
                listaEmpresasView.add(recargaCelularView);
            }

            empresaRecargaCelularView.setEmpresas(listaEmpresasView);
            empresaRecargaCelularView
                    .setMensajeError(mensajeBO.obtenerMensajePorCodigo(SIN_COINCIDENCIAS).getMensaje());

            return respuestaFactory.crearRespuestaOk(empresaRecargaCelularView);

        } catch (DAOException e) {
            LOGGER.error("Error al realizar la busqueda", e);
            String mensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_GENERICO_BUSCADOR).getMensaje();
            return respuestaFactory.crearRespuestaError(EmpresaRecargaCelularView.class, mensaje, StringUtils.EMPTY);
        }
    }

    /**
     * Search empresa debito automatico en tarjeta.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO#
     * searchEmpresaDebitoAutomaticoEnTarjeta(java.lang.String)
     */
    @Override
    public Respuesta<EmpresaDebitoAutomaticoTarjetaView> searchEmpresaDebitoAutomaticoEnTarjeta(
            String terminoBusqueda) {

        LOGGER.info("Termino de busqueda en recarga de celulares: " + terminoBusqueda);

        EmpresaDebitoAutomaticoTarjetaView empresaDebitoAutomaticoTarjetaView = new EmpresaDebitoAutomaticoTarjetaView();
        List<DebitoAutomaticoEnTarjetaView> listaEmpresasView = new ArrayList<DebitoAutomaticoEnTarjetaView>();

        try {
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.searchEmpresaDebitoAutomaticoEnTarjeta(terminoBusqueda);

            for (MedioPago medioPago : medioPagos) {

                DebitoAutomaticoEnTarjetaView debitoAutomaticoEnTarjetaView = new DebitoAutomaticoEnTarjetaView();
                BeanUtils.copyProperties(medioPago, debitoAutomaticoEnTarjetaView);
                
    			//Manganeta temporal, borrar cuando el nombre del colegio aparezca bien en el medioDePago.txt
                if(debitoAutomaticoEnTarjetaView.getNombreFantasia().equalsIgnoreCase("COL SAN ESTEBAN")){
                	debitoAutomaticoEnTarjetaView.setNombreFantasia("COL SAN ESTEBAN - CUIT 30672972961");
                }
                
                listaEmpresasView.add(debitoAutomaticoEnTarjetaView);
            }

            empresaDebitoAutomaticoTarjetaView.setEmpresas(listaEmpresasView);
            empresaDebitoAutomaticoTarjetaView
                    .setMensajeError(mensajeBO.obtenerMensajePorCodigo(SIN_COINCIDENCIAS).getMensaje());

            return respuestaFactory.crearRespuestaOk(empresaDebitoAutomaticoTarjetaView);

        } catch (DAOException e) {
            LOGGER.error("Error al realizar la busqueda", e);
            String mensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_GENERICO_BUSCADOR).getMensaje();
            return respuestaFactory.crearRespuestaError(EmpresaDebitoAutomaticoTarjetaView.class, mensaje,
                    StringUtils.EMPTY);
        }

    }

    /**
     * Crear respuesta OK.
     *
     * @param listaEmpresas
     *            the lista empresas
     * @param codigoMensajeSinCoincidencias
     *            the codigo mensaje sin coincidencias
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    private Respuesta<BuscadorEmpresaRta> crearRespuestaOK(List<MedioPagoView> listaEmpresas,
            String codigoMensajeSinCoincidencias, String terminoBusqueda) {

        BuscadorEmpresaRta buscadorEmpresaRta = new BuscadorEmpresaRta();
        buscadorEmpresaRta.setEmpresas(listaEmpresas);
        if (SIN_COINCIDENCIAS_PAGO_AUTOMATICO.equals(codigoMensajeSinCoincidencias)) {
            String mensajeSinCoincidencias = mensajeBO.obtenerMensajePorCodigo(codigoMensajeSinCoincidencias)
                    .getMensaje();
            mensajeSinCoincidencias = MessageFormat.format(mensajeSinCoincidencias, terminoBusqueda);
            buscadorEmpresaRta.setMensajeError(mensajeSinCoincidencias);
        } else {
            buscadorEmpresaRta
                    .setMensajeError(mensajeBO.obtenerMensajePorCodigo(codigoMensajeSinCoincidencias).getMensaje());
        }
        buscadorEmpresaRta.setMensajeImporteLimite(mensajeBO.obtenerMensajePorCodigo(IMPORTE_LIMITE).getMensaje());
        buscadorEmpresaRta
                .setMensajeNoPermitePago(mensajeBO.obtenerMensajePorCodigo(NO_PERMITE_PAGO_AUTOMATICO).getMensaje());
        buscadorEmpresaRta.setPagoComprasAyuda(mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_COMPRAS_IDENTIFICACION_AYUDA).getMensaje());
        buscadorEmpresaRta.setMensajeInformacionPagoAdebitar(mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_PAGO_PENDIENTE_DE_CONFIRMACION ).getMensaje());
        Respuesta<BuscadorEmpresaRta> respuesta = respuestaFactory.crearRespuestaOk(buscadorEmpresaRta);
        respuesta.setRespuestaVacia(CollectionUtils.isEmpty(listaEmpresas));
        return respuesta;
    }

    /**
     * Crear respuesta error.
     *
     * @param listaEmpresas
     *            the lista empresas
     * @return the respuesta
     */
    private Respuesta<BuscadorEmpresaRta> crearRespuestaError(List<MedioPagoView> listaEmpresas) {

        Respuesta<BuscadorEmpresaRta> respuestaView = new Respuesta<BuscadorEmpresaRta>();
        BuscadorEmpresaRta buscadorEmpresaRta = new BuscadorEmpresaRta();
        buscadorEmpresaRta.setEmpresas(listaEmpresas);
        buscadorEmpresaRta.setMensajeError(mensajeBO.obtenerMensajePorCodigo(ERROR_BUSCADOR).getMensaje());
        buscadorEmpresaRta.setMensajeImporteLimite(mensajeBO.obtenerMensajePorCodigo(IMPORTE_LIMITE).getMensaje());
        buscadorEmpresaRta
                .setMensajeNoPermitePago(mensajeBO.obtenerMensajePorCodigo(NO_PERMITE_PAGO_AUTOMATICO).getMensaje());
        respuestaView.setRespuesta(buscadorEmpresaRta);
        respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);

        return respuestaView;
    }

    /**
     * Search empresa por cuit Y servicio.
     *
     * @param cuit the cuit
     * @param servicio the servicio
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO#
     * searchEmpresaPorCuitYServicio(java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<MedioPago> searchEmpresaPorCuitYServicio(String cuit, String servicio) {
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        Set<MedioPago> busqueda;
        try {
            busqueda = buscadorEmpresaDAO.buscarMedioPagoPorCuitServicio(cuit, servicio);
			if (busqueda !=  null && !busqueda.isEmpty()) {
            respuesta.setRespuesta((MedioPago) busqueda.toArray()[0]);
			}
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuestaVacia(false);
        } catch (ParseException e) {
            LOGGER.error("Error al realizar la busqueda por Cuit y Servicio", e);
        }
        return respuesta;
    }

    /**
     * Search empresa recarga transporte.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchEmpresaRecargaTransporte() {
        LOGGER.info("Buscando empresas de transporte ");
        BuscadorEmpresaRta empresas = new BuscadorEmpresaRta();
        List<MedioPagoView> listaEmpresasView = new ArrayList<MedioPagoView>();
        try {
            Set<MedioPago> medioPagos = buscadorEmpresaDAO.searchRecargaTransporte();
            for (MedioPago medioPago : medioPagos) {
                MedioPagoView pago = new MedioPagoView(medioPago);
                pago.setPesIdentificacion(WordUtils.capitalizeFully(medioPago.getPesIdentificacion()));
                pago.setPesGifFactura(medioPago.getPesGifFactura().replaceAll(";", ""));
                TipoNuevoPagoEnum tipoNuevoPagoEnum = mediosPagoBO.obtenerTipoNuevoPagoEnum(medioPago);
                if (tipoNuevoPagoEnum != null) {
                    pago.setTipoNuevoPago(tipoNuevoPagoEnum.getId());
                }
                listaEmpresasView.add(pago);
                LOGGER.debug("Se agrega la Empresa {}", medioPago);
            }
            empresas.setEmpresas(listaEmpresasView);
            empresas.setMensajeError(mensajeBO.obtenerMensajePorCodigo(SIN_COINCIDENCIAS).getMensaje());
            return respuestaFactory.crearRespuestaOk(empresas);
        } catch (DAOException e) {
            String mensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_GENERICO_BUSCADOR).getMensaje();
            return respuestaFactory.crearRespuestaError(BuscadorEmpresaRta.class, mensaje, StringUtils.EMPTY);
        }
    }

	/**
	 * Obtener empresas.
	 *
	 * @param empresa the empresa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(String empresa) {
        LOGGER.info("RECURRENCIAS - Busqueda de empresas - termino de busqueda: " + empresa);
        Respuesta<EmpresasDebinRecurrenteView> empresasView;
		try {
			List<SellerWithCategoryDTO> listaEmpresas = obtenerEmpresasDAOWrapper(empresa);
            List<VendedorView> vendedorViewList = new ArrayList<VendedorView>();
            for (SellerWithCategoryDTO empresaDTO : listaEmpresas) {
                VendedorView vendedorView = new VendedorView(
                        empresaDTO.getCategoryNames(),
                        empresaDTO.getCuit(),
                        empresaDTO.getFancyName());
                vendedorViewList.add(vendedorView);
            }
            empresasView = crearRespuestaEmpresasDebinRecurrenteOK(vendedorViewList);
		} catch (Exception e) {
            empresasView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		return empresasView;
	}

    /**
     * Obtener servicios.
     *
     * @param cuitEmpresa the cuit empresa
     * @return the respuesta
     */

	@Override
	public Respuesta<SellerWithProvisionDTO> obtenerServicios(String cuitEmpresa) {
		Respuesta<SellerWithProvisionDTO> respuesta = new Respuesta<SellerWithProvisionDTO>(); 
		try {
			SellerWithProvisionDTO respuestaServicio =  debinRecurrenteDAO.obtenerServicios(cuitEmpresa);
			respuesta = respuestaFactory.crearRespuestaOk(respuestaServicio);
		} catch (Exception e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		return respuesta;
	}

    private List<SellerWithCategoryDTO> obtenerEmpresasDAOWrapper(String empresa) throws Exception {
        List<SellerWithCategoryDTO> respuestaServicio = debinRecurrenteDAO.obtenerEmpresas(empresa);
        if (respuestaServicio.isEmpty()) {
            respuestaServicio = debinRecurrenteDAO.obtenerEmpresas(empresa.toUpperCase());
        }
        return respuestaServicio;
    }

    private Respuesta<EmpresasDebinRecurrenteView> crearRespuestaEmpresasDebinRecurrenteOK(List<VendedorView> listaEmpresas) {
        Respuesta<EmpresasDebinRecurrenteView> respuesta;
        boolean sinCoincidencias = CollectionUtils.isEmpty(listaEmpresas);
        if (sinCoincidencias) {
            String mensajeSinCoincidencias = mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINREC_NO_SELLERS_FOUND)
                    .getMensaje();
            respuesta = respuestaFactory.crearRespuestaWarning(
                    EmpresasDebinRecurrenteView.class,
                    mensajeSinCoincidencias,
                    "");
        } else {
            EmpresasDebinRecurrenteView empresasDebinRecurrenteView = new EmpresasDebinRecurrenteView();
            empresasDebinRecurrenteView.setEmpresas(listaEmpresas);
            respuesta = respuestaFactory.crearRespuestaOk(empresasDebinRecurrenteView);
        }
        return respuesta;
    }

}