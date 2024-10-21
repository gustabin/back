/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasBO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TipoClienteCitiDTO;
import ar.com.santanderrio.obp.servicios.tenencias.exception.CopiarListaException;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.InversionesHelper;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasEstadisticaHelper;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;
import ar.com.santanderrio.obp.servicios.tenencias.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DesafioPresentarView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FirmanteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.RendimientoFondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasLegalView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

/**
 * The Class TenenciasManagerImpl.
 *
 * @author
 */
@Component("tenenciasManager")
public class TenenciasManagerImpl implements TenenciasManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasManagerImpl.class);

    /** The Constant ERROR_PARSEAR_VIEW. */
    private static final String ERROR_PARSEAR_VIEW = "Error al parsear TenenciasView";

    /** The Constant LOGGER. */
    private static final String ANIO2017EXCITY = "2017";

    /** The Constant LOGGER. */
    private static final String ANIO2018RESFINANCIERO = "2018";
    
    /** The Constant LOGGER. */
    private static final int ANIO_CONSULTA_PL = 2019;
    
    /** The tenencias BO. */
    @Autowired
    private TenenciasBO tenenciasBO;

    /** The Respuesta Factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The Estadistica helper *. */
    @Autowired
    private TenenciasEstadisticaHelper estHelper;

    /** The Legal BO. */
    @Autowired
    private LegalBO legalBO;

    /** The codigos BP. */
    @Value("#{'${TENENCIAS.PERIODO}'.split('\\|')}")
    private List<String> aniosTenencias;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<DesafioPresentarView> desafioOperacionRSA;

    /**
     * consultar Tenencias.
     *
     * @param viewRequest
     *            the view request
     * @return respuesta tenenciasView.
     */
    public Respuesta<TenenciasView> consultarTenencias(TenenciasInView viewRequest) {
        List<String> listaAnios = armarListaAnios(aniosTenencias);
        String anio = StringUtils.isNotBlank(viewRequest.getAnio()) ? viewRequest.getAnio() : listaAnios.get(0);
        Respuesta<TenenciasDTO> tenenciaDTO = tenenciasBO.consultarTenencias(createInDTO(anio));
        Respuesta<FirmantesDTO> firmanteDTO = tenenciasBO.consultarFirmantes(createInDTO(anio));
        Respuesta<TipoClienteCitiDTO> tenenciaCitiDTO = null;

        Respuesta<TenenciasView> respuesta = null;
        estHelper.setAnio(anio);
        try {
            if (tenenciaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                TenenciasView view;
                estHelper.repEstadisticaTenencia(false);
                view = createRetornoView(tenenciaDTO.getRespuesta(), firmanteDTO.getRespuesta(), listaAnios);
                setLegalesGeneralesInversones(view);
                if (ANIO2017EXCITY.equals(anio)) {
                    tenenciaCitiDTO = tenenciasBO.consultarTipClienteTenencias(createInDTO(anio));
                    if (tenenciaCitiDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                        view.setTipoCliente(tenenciaCitiDTO.getRespuesta().getTipCliente());
                        if ("CITI".equals(tenenciaCitiDTO.getRespuesta().getTipCliente())) {
                            view.setIsCiti(true);
                            setLegalesExclusivo(view);
                        } else if ("SOLAPADO".equals(tenenciaCitiDTO.getRespuesta().getTipCliente())) {
                            setLegalesSolapado(view);
                            view.setIsSolapado(true);
                        } else {
                            view.setIsBsr(true);
                        }
                    } else {
                        setLegalesExclusivo(view);
                        view.setIsCiti(true);
                    }
                }            
                if (sesionCliente.getCliente().getClienteBancaPrivada()) {
                	view.setIsBancaPriv(true);
                }

                respuesta = respuestaFactory.crearRespuestaOk(TenenciasView.class, view);
            } else {
                estHelper.repEstadisticaTenencia(true);
                respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.TENENCIAS_ERROR_GENERICO);

            }
        } catch (CopiarListaException e) {
            estHelper.repEstadisticaTenencia(true);
            LOGGER.error(ERROR_PARSEAR_VIEW, e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.TENENCIAS_ERROR_GENERICO);
        }
        return respuesta;

    }

    private List<String> armarListaAnios(List<String> aniosTenencias) {
        Collections.sort(aniosTenencias);
        Collections.reverse(aniosTenencias);
        return aniosTenencias.subList(0, 10);
    }

    /**
     * Setea los legales para CITI SOLAPADO.
     *
     * @param view
     *            the new legales solapado
     */
    private void setLegalesSolapado(TenenciasView view) {
        view.setTenenciasLegal(new TenenciasLegalView());
        view.getTenenciasLegal().setLegalesPrincipalSolapadoCuentas(obtenerLegalPrincipalSolapadoCuentas());
        view.getTenenciasLegal().setLegalesPrincipalSolapadoImpuestos(obtenerLegalPrincipalSolapadoImpuestos());
        view.getTenenciasLegal().setLegalesPrincipalSolapadoInversiones(obtenerLegalPrincipalSolapadoInversiones());
        view.getTenenciasLegal().setLegalesPrincipalSolapadoPrestamos(obtenerLegalPrincipalSolapadoPrestamos());
        view.getTenenciasLegal().setLegalesDetalleSolapadoImpuestos(obtenerLegalDetalleSolapadoImpuestos());
        view.getTenenciasLegal().setLegalesDetalleSolapadoInversiones(obtenerLegalDetalleInversiones());
        view.getTenenciasLegal().setLegalesDetalleSolapadoPrestamos(obtenerLegalDetalleSolapadoPrestamos());
    }
    
    /**
     * Setea los legales para PESTAÑA INVERSIONES 2019.
     *
     * @param view        the new legales exclusivo
     */
    private void setLegalesInversiones(TenenciasView view) {
        view.setTenenciasLegal(new TenenciasLegalView());
        view.getTenenciasLegal().setLegalesPrincipalExclusivoCuentas(obtenerLegalPrincipalExclusivoCuentas());
    }
    
    /**
     * Setea los legales para CITI EXCLUSIVO.
     *
     * @param view
     *            the new legales exclusivo
     */
    private void setLegalesGeneralesInversones(TenenciasView view) {
        view.setTenenciasLegal(new TenenciasLegalView());
        view.getTenenciasLegal().setLegalesInversiones(obtenerLegalPrincipalGeneralInversiones());
    }
    
    
    /**
     * Obtener legal principal exclusivo cuentas.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalGeneralInversiones() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_GENERAL_PRINCIPAL_INVERSIONES);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }
    
    
    /**
     * Setea los legales para CITI EXCLUSIVO.
     *
     * @param view
     *            the new legales exclusivo
     */
    private void setLegalesExclusivo(TenenciasView view) {
        view.setTenenciasLegal(new TenenciasLegalView());
        view.getTenenciasLegal().setLegalesPrincipalExclusivoCuentas(obtenerLegalPrincipalExclusivoCuentas());
    }

    /**
     * Obtener legal principal exclusivo cuentas.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalExclusivoCuentas() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_EXCLUSIVO_PRINCIPAL_2017_UNO);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal detalle solapado impuestos.
     *
     * @return the list
     */
    private List<String> obtenerLegalDetalleSolapadoImpuestos() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_IMPUESTOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_IMPUESTOS_ME);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_IMPUESTOS_TJC);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal detalle inversiones.
     *
     * @return the list
     */
    private List<String> obtenerLegalDetalleInversiones() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_ACCIONES);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_BONOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_FONDOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_MON_EXT);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_PLAZO_FIJO);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal detalle solapado prestamos.
     *
     * @return the list
     */
    private List<String> obtenerLegalDetalleSolapadoPrestamos() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_DETALLE_2017_PRESTAMOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal principal solapado cuentas.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalSolapadoCuentas() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_CUENTAS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal principal solapado impuestos.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalSolapadoImpuestos() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_CUS_PESOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_IMPUESTOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal principal solapado inversiones.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalSolapadoInversiones() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_ACCIONES);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_BONOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_FONDOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_FON_RESC);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_MON_EXT);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_PLAZO_FIJO);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /**
     * Obtener legal principal solapado prestamos.
     *
     * @return the list
     */
    private List<String> obtenerLegalPrincipalSolapadoPrestamos() {
        List<String> retorno = new ArrayList<String>();
        String legal = legalBO
                .obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CITI_SOLAPADO_PRINCIPAL_2017_PRESTAMOS);
        if (!StringUtils.isBlank(legal)) {
            retorno.add(legal);
        }
        return retorno;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasManager#
     * challengePresentar(ar.com.santanderrio.obp.servicios.tenencias.view.
     * DesafioPresentarView)
     */
    @Override
    public Respuesta<DesafioPresentarView> challengePresentar(DesafioPresentarView viewRequest) {
        return desafioOperacionRSA
                .validarOperacionRSA(viewRequest, 1, null);
    }

    /**
     * crear un DTO para llamar al BO.
     *
     * @param viewRequest
     *            the view request
     * @return dto.
     */
    private TenenciasInDTO createInDTO(String anio) {
        TenenciasInDTO inDTO = new TenenciasInDTO();
        inDTO.setAnioDesde(anio);
        inDTO.setAnioHasta(anio);
        inDTO.setNup(sesionCliente.getCliente().getNup());

        return inDTO;
    }

    /**
     * crear un View para retornar al TenenciasSEI.
     *
     * @param tenenciasDTO
     *            the tenencias DTO
     * @param firmantesDTO
     *            the firmantes DTO
     * @param aniosTenencias
     * @return view.
     * @throws CopiarListaException
     *             the copiar lista exception
     */
    private TenenciasView createRetornoView(TenenciasDTO tenenciasDTO, FirmantesDTO firmantesDTO,
            List<String> aniosTenencias) throws CopiarListaException {
        TenenciasView view = new TenenciasView();
        List<CuentaView> cuentas = new ArrayList<CuentaView>();
        TenenciasHelper.copiarLista(tenenciasDTO.getCuentasDTO(), cuentas, CuentaView.class);
        List<FirmanteView> firmantesCuentas = new ArrayList<FirmanteView>();
        TenenciasHelper.copiarLista(firmantesDTO.getCuentas(), firmantesCuentas, FirmanteView.class);
        if (!firmantesCuentas.isEmpty()) {
            TenenciasHelper.agregarCuentaYSaldos(view, cuentas, firmantesCuentas);
        }
        view.setListaAnios(aniosTenencias);
        view.setInversiones(TenenciasHelper.obtenerInversiones(tenenciasDTO, firmantesDTO));
        view.setImpuestos(TenenciasHelper.obtenerImpuestosView(tenenciasDTO.getImpuestosDTO()));
        view.setPrestamos(
                TenenciasHelper.obtenerPrestamosView(tenenciasDTO.getPrestamosDTO(), firmantesDTO.getPrestamos()));

        List<TarjetaView> tarjeta = new ArrayList<TarjetaView>();
        TenenciasHelper.copiarLista(tenenciasDTO.getTarjetasDTO(), tarjeta, TarjetaView.class);
        view.setTarjetas(tarjeta);

        List<RendimientoFondoView> rendimientoFondos = new ArrayList<RendimientoFondoView>();
        TenenciasHelper.copiarLista(tenenciasDTO.getRendimientoFondosDTO(), rendimientoFondos,
                RendimientoFondoView.class);
        for (RendimientoFondoView rendimientoFondo : rendimientoFondos) {
            rendimientoFondo.setCuenta(TenenciasHelper.nroCuentaCanonico(rendimientoFondo.getCuenta()));
            rendimientoFondo.setDivisa(TenenciasHelper.monedaCanonico(rendimientoFondo.getDivisa()));
        }
        view.setRendimientoFondos(rendimientoFondos);

        view.setCotiDolar(InversionesHelper.formatearSaldo4Decimales(tenenciasDTO.getCotiDolar()));
        TenenciasHelper.agregarImpMonedaExtranjera(view, tenenciasDTO.getImpuestoMonedaExtranjeraDTO());

        TenenciasHelper.formatearSaldosTenencias(view);
        if (view.getSaldoTotalDolares() == null || view.getSaldoTotalDolares().equals(""))
            view.setSaldoTotalDolares("0,00");
        if (view.getSaldoTotalPesos() == null || view.getSaldoTotalPesos().equals(""))
            view.setSaldoTotalPesos("0,00");
        return view;
    }

}
