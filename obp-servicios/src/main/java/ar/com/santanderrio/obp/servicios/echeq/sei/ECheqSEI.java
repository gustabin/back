package ar.com.santanderrio.obp.servicios.echeq.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.echeq.view.BeneficiarioView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAdhesionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoOperacionECheqView;

/**
 * The Interface ECheqSEI.
 *
 * @author IT Resources
 */
@Path("/eCheq")
public interface ECheqSEI {

    @POST
    @Path("/ingreso")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<IngresoECheqOutView> ingresoModulo();

    @POST
    @Path("/consultar")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<GrillaECheqOutView> consultar(GrillaECheqInView grillaECheqInView, @Context MessageContext mc);

    @POST
    @Path("/confirmar")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarOperacionECheqOutView> confirmar(ConfirmarOperacionECheqInView confirmarOperacionECheqInView, @Context MessageContext mc);

    @POST
    @Path("/confirmarAdhesion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarOperacionECheqOutView> confirmarAdhesion(ConfirmarAdhesionECheqInView confirmarAdhesionInView);

    @POST
    @Path("/confirmarAlta")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarAltaEndosarECheqView> confirmarAlta(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, @Context MessageContext mc);

    @POST
    @Path("/confirmarEndoso")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarAltaEndosarECheqView> confirmarEndoso(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, @Context MessageContext mc);

    @POST
    @Path("/confirmarAltaCED")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarAltaEndosarECheqView> confirmarAltaCED(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, @Context MessageContext mc);

    @POST
    @Path("/descargarComprobanteAlta")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteAlta();

    @POST
    @Path("/descargarComprobanteEndoso")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteEndoso();

    @POST
    @Path("/descargarDetalle")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarDetalle();
    
    @POST
    @Path("/descargarComprobanteAltaCED")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteAltaCED();

    @POST
    @Path("/ingresoOperacion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<IngresoOperacionECheqView> ingresoOperacion(IngresoOperacionECheqView ingresoOperacionECheqInView, @Context MessageContext mc);

    @POST
    @Path("/validarBeneficiario")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<BeneficiarioView> validarBeneficiario(BeneficiarioView beneficiarioInView, @Context MessageContext mc);

}
