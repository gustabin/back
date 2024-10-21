/*
 * 
 */
package ar.com.santanderrio.obp.servicios.buscadorempresa.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * 
 * Migracion de BuscadorEmpresaController
 * 
 * Created by pablo.martin.gore on 7/22/2016.
 */
@Path("/buscador")
public interface BuscadorEmpresaSEI {

    /**
     * Este servicio graba la estadistica a la entrada de nuevo Pago.
     *
     * @return the boolean
     */
    @POST
    @Path("/grabarEstadistica")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Boolean estadistica();

    /**
     * Este servicio retorna las ocurrencias encontradas en el indice de lucene
     * donde se encuentre el contenido del parametro buscadorValue.
     *
     * @param buscador
     *            the buscador
     * @return the respuesta
     */
    @POST
    @Path("/empresa")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<BuscadorEmpresaRta> searchByRubroEmpresa(BuscadorEmpresaView buscador);

    /**
     * Obtiene la lista de medios de pago habilitados para pago automatico: que
     * cumplan con los siguientes requisitos Pes_Habilitado = S Pes_prepago = N
     * (PesPA_habilitado = S O Addi_habilitado = S).
     *
     * @param buscador
     *            the buscador
     * @return the respuesta
     */
    @POST
    @Path("/empresaPagoAutomatico")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<BuscadorEmpresaRta> empresaPagoAutomatico(BuscadorEmpresaView buscador);

    /**
     * Buscador recarga celulares.
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    @POST
    @Path("/empresaRecargaCelulares")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<EmpresaRecargaCelularView> buscadorRecargaCelulares();

    /**
     * Buscador debito automatico en tarjeta.
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    @POST
    @Path("/empresaDebitoAutomaticoEnTarjeta")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<EmpresaDebitoAutomaticoTarjetaView> buscadorDebitoAutomaticoEnTarjeta(
            BuscadorEmpresaView terminoBusqueda);

    /**
     * Estadistica adhesion debito automatico en tarjeta.
     *
     * @return the boolean
     */
    @POST
    @Path("/estadisticaAdhesionDebitoAutomaticoEnTarjeta")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Boolean estadisticaAdhesionDebitoAutomaticoEnTarjeta();

    /**
	 * Empresa nuevo pago.
	 *
	 * @param buscador
	 *            the buscador
	 * @return the respuesta
	 */
    @POST
    @Path("/empresaNuevoPago")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<BuscadorEmpresaRta> empresaNuevoPago(BuscadorEmpresaView buscador);
    
    /**
     * Buscador recarga empresa transporte.
     *
     * @return the respuesta
     */
    @POST
    @Path("/empresaRecargaTransporte")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<BuscadorEmpresaRta> buscadorRecargaTransporte();

    
    /**
     * Obtener empresas.
     *
     * @param buscador the buscador
     * @return the respuesta
     */
    @POST
    @Path("/empresaDebinRecurrente")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(BuscadorEmpresaView buscador);

    @POST
    @Path("/servicioDebinRecurrente")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<VendedorPrestacionesView> obtenerServicios(BuscadorEmpresaView buscador);
}
