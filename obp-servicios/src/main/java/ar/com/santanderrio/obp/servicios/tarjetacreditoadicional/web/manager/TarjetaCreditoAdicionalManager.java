/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DomiciliosView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.PaisDeNacimientoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SexoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SolicitudTarjetaCreditoAdicionalView;

/**
 * The Interface TarjetaCreditoAdicionalManager.
 */
public interface TarjetaCreditoAdicionalManager {

    /**
     * Gets the datos iniciales.
     *
     * @return the datos iniciales
     */
    Respuesta<SolicitudTarjetaCreditoAdicionalView> getDatosIniciales();

    /**
     * Gets the datos del solicitante.
     *
     * @param datosSolicitanteCriterioView
     *            the datos solicitante criterio view
     * @return the datos del solicitante
     */
    Respuesta<DatosSolicitanteView> getDatosDelSolicitante(DatosSolicitanteCriterioView datosSolicitanteCriterioView);

    /**
     * Alta tarjeta credito adicional.
     *
     * @param datosSolicitanteConfirmadoViewResponse
     *            the datos solicitante confirmado view response
     * @return the respuesta
     */
    Respuesta<DatosConfirmadosDelSolicitanteView> altaTarjetaCreditoAdicional(DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoViewResponse);

    /**
     * Normalizacion de domicilios.
     *
     * @param datosDeDomicilio
     *            the datos de domicilio
     * @return the respuesta
     */
    Respuesta<DomiciliosView> getResultadoMerlin(DatosMerlinInEntity datosDeDomicilio);

    /**
     * Vaciar desafio.
     */
    void vaciarDesafio();

    /**
     * Descarga comprobante alta tarjeta.
     *
     * @return the respuesta
     */
    Respuesta<ReporteView> descargaComprobanteAltaTarjeta();

    /**
     * Obtener consulta unidad control in entity.
     *
     * @param cliente
     *            the cliente
     * @param altaCanalAutomaticoInEntity
     *            the alta canal automatico in entity
     * @throws DAOException
     *             the DAO exception
     */
    void obtenerConsultaUnidadControlInEntity(Cliente cliente, AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity)
            throws DAOException;

    /**
     * Dto to view paises.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    List<PaisDeNacimientoView> dtoToViewPaises(List<Opcion> lista);

    /**
     * Dto to view sexo.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    List<SexoView> dtoToViewSexo(List<Opcion> lista);

    /**
     * Dto to view estado civil.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    List<EstadoCivilView> dtoToViewEstadoCivil(List<Opcion> lista);

    /**
	 * Continuar solicitud.
	 *
	 * @param datosAdicionalSolicitudView
	 *            the datos adicional solicitud view
	 * @return the respuesta
	 */
    Respuesta<String> continuarSolicitud(DatosAdicionalSolicitudView datosAdicionalSolicitudView);
}
