package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2017-06-12T16:00:16.748-03:00
 * Generated source version: 3.0.0
 * 
 */
@WebService(targetNamespace = "http://webService.gestcli.ges.rio.com", name = "WS_GC")
@XmlSeeAlso({ObjectFactory.class})
public interface WSGC {

    @WebResult(name = "obtenerListaGestionesReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerListaGestionesResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerListaGestionesResponse")
    @RequestWrapper(localName = "obtenerListaGestiones", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerListaGestiones")
    @WebMethod(action = "obtenerListaGestiones")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoListaWS obtenerListaGestiones(
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.String nup,
        @WebParam(name = "dias", targetNamespace = "")
        java.lang.Integer dias,
        @WebParam(name = "tiposGestion", targetNamespace = "")
        java.lang.String tiposGestion,
        @WebParam(name = "estadoActual", targetNamespace = "")
        java.lang.String estadoActual
    );

    @WebResult(name = "resolverGestionParcialReturn", targetNamespace = "")
    @ResponseWrapper(localName = "resolverGestionParcialResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ResolverGestionParcialResponse")
    @RequestWrapper(localName = "resolverGestionParcial", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ResolverGestionParcial")
    @WebMethod(action = "resolverGestionParcial")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS resolverGestionParcial(
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.String ideGestionNro,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @ResponseWrapper(localName = "initResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.InitResponse")
    @RequestWrapper(localName = "init", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.Init")
    @WebMethod(action = "init")
    public void init(
        @WebParam(name = "arg_0_1", targetNamespace = "")
        java.lang.Object arg01
    );

    @WebResult(name = "obtenerListaReclamosReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerListaReclamosResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerListaReclamosResponse")
    @RequestWrapper(localName = "obtenerListaReclamos", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerListaReclamos")
    @WebMethod(action = "obtenerListaReclamos")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoListaWS obtenerListaReclamos(
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.String nup,
        @WebParam(name = "dias", targetNamespace = "")
        java.lang.Integer dias
    );

    @WebResult(name = "obtenerTiempoNetoSectorGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerTiempoNetoSectorGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerTiempoNetoSectorGestionResponse")
    @RequestWrapper(localName = "obtenerTiempoNetoSectorGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerTiempoNetoSectorGestion")
    @WebMethod(action = "obtenerTiempoNetoSectorGestion")
    public ar.com.santanderrio.obp.generated.webservices.productos.TiempoSector obtenerTiempoNetoSectorGestion(
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.Integer ideGestionNro,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector
    );

    @WebResult(name = "avanzarGestionConMontoReturn", targetNamespace = "")
    @ResponseWrapper(localName = "avanzarGestionConMontoResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionConMontoResponse")
    @RequestWrapper(localName = "avanzarGestionConMonto", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionConMonto")
    @WebMethod(action = "avanzarGestionConMonto")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS avanzarGestionConMonto(
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.String ideGestionNro,
        @WebParam(name = "accion", targetNamespace = "")
        java.lang.String accion,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "codRtaResolPredef", targetNamespace = "")
        java.lang.String codRtaResolPredef,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "monto", targetNamespace = "")
        java.lang.String monto,
        @WebParam(name = "moneda", targetNamespace = "")
        java.lang.String moneda,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector,
        @WebParam(name = "nroOrdenActor", targetNamespace = "")
        java.lang.String nroOrdenActor
    );

    @WebResult(name = "altaGestionUser2Return", targetNamespace = "")
    @ResponseWrapper(localName = "altaGestionUser2Response", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestionUser2Response")
    @RequestWrapper(localName = "altaGestionUser2", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestionUser2")
    @WebMethod(action = "altaGestionUser2")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaGestionUser2(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentarioCliente", targetNamespace = "")
        java.lang.String comentarioCliente,
        @WebParam(name = "comentarioReceptor", targetNamespace = "")
        java.lang.String comentarioReceptor,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @WebResult(name = "altaResolInmedita2Return", targetNamespace = "")
    @ResponseWrapper(localName = "altaResolInmedita2Response", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmedita2Response")
    @RequestWrapper(localName = "altaResolInmedita2", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmedita2")
    @WebMethod(action = "altaResolInmedita2")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaResolInmedita2(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentarioCliente", targetNamespace = "")
        java.lang.String comentarioCliente,
        @WebParam(name = "comentarioReceptor", targetNamespace = "")
        java.lang.String comentarioReceptor,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "comentarioResol", targetNamespace = "")
        java.lang.String comentarioResol,
        @WebParam(name = "codRtaResolPredefResol", targetNamespace = "")
        java.lang.String codRtaResolPredefResol,
        @WebParam(name = "comentarioResp", targetNamespace = "")
        java.lang.String comentarioResp,
        @WebParam(name = "codRtaResolPredefResp", targetNamespace = "")
        java.lang.String codRtaResolPredefResp,
        @WebParam(name = "favorabilidad", targetNamespace = "")
        java.lang.String favorabilidad
    );

    @ResponseWrapper(localName = "destroyResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.DestroyResponse")
    @RequestWrapper(localName = "destroy", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.Destroy")
    @WebMethod(action = "destroy")
    public void destroy();

    @WebResult(name = "avanzarGestionAvanceReturn", targetNamespace = "")
    @ResponseWrapper(localName = "avanzarGestionAvanceResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionAvanceResponse")
    @RequestWrapper(localName = "avanzarGestionAvance", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionAvance")
    @WebMethod(action = "avanzarGestionAvance")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS avanzarGestionAvance(
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.String ideGestionNro,
        @WebParam(name = "accion", targetNamespace = "")
        java.lang.String accion,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "codRtaResolPredef", targetNamespace = "")
        java.lang.String codRtaResolPredef,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "ideGestionSectorAvance", targetNamespace = "")
        java.lang.String ideGestionSectorAvance
    );

    @WebResult(name = "altaResolInmeditaUser2Return", targetNamespace = "")
    @ResponseWrapper(localName = "altaResolInmeditaUser2Response", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmeditaUser2Response")
    @RequestWrapper(localName = "altaResolInmeditaUser2", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmeditaUser2")
    @WebMethod(action = "altaResolInmeditaUser2")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaResolInmeditaUser2(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentarioCliente", targetNamespace = "")
        java.lang.String comentarioCliente,
        @WebParam(name = "comentarioReceptor", targetNamespace = "")
        java.lang.String comentarioReceptor,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "comentarioResol", targetNamespace = "")
        java.lang.String comentarioResol,
        @WebParam(name = "codRtaResolPredefResol", targetNamespace = "")
        java.lang.String codRtaResolPredefResol,
        @WebParam(name = "comentarioResp", targetNamespace = "")
        java.lang.String comentarioResp,
        @WebParam(name = "codRtaResolPredefResp", targetNamespace = "")
        java.lang.String codRtaResolPredefResp,
        @WebParam(name = "favorabilidad", targetNamespace = "")
        java.lang.String favorabilidad
    );

    @WebResult(name = "altaResolInmeditaUserReturn", targetNamespace = "")
    @ResponseWrapper(localName = "altaResolInmeditaUserResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmeditaUserResponse")
    @RequestWrapper(localName = "altaResolInmeditaUser", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmeditaUser")
    @WebMethod(action = "altaResolInmeditaUser")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaResolInmeditaUser(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "comentarioResol", targetNamespace = "")
        java.lang.String comentarioResol,
        @WebParam(name = "codRtaResolPredefResol", targetNamespace = "")
        java.lang.String codRtaResolPredefResol,
        @WebParam(name = "comentarioResp", targetNamespace = "")
        java.lang.String comentarioResp,
        @WebParam(name = "codRtaResolPredefResp", targetNamespace = "")
        java.lang.String codRtaResolPredefResp,
        @WebParam(name = "favorabilidad", targetNamespace = "")
        java.lang.String favorabilidad
    );

    @WebResult(name = "obtenerGestionesEjecutivoReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerGestionesEjecutivoResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerGestionesEjecutivoResponse")
    @RequestWrapper(localName = "obtenerGestionesEjecutivo", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerGestionesEjecutivo")
    @WebMethod(action = "obtenerGestionesEjecutivo")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoListaGestionClienteWS obtenerGestionesEjecutivo(
        @WebParam(name = "idEjecutivo", targetNamespace = "")
        java.lang.String idEjecutivo,
        @WebParam(name = "dias", targetNamespace = "")
        java.lang.Integer dias,
        @WebParam(name = "tiposGestion", targetNamespace = "")
        java.lang.String tiposGestion,
        @WebParam(name = "estadoActual", targetNamespace = "")
        java.lang.String estadoActual
    );

    @WebResult(name = "altaGestion2Return", targetNamespace = "")
    @ResponseWrapper(localName = "altaGestion2Response", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestion2Response")
    @RequestWrapper(localName = "altaGestion2", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestion2")
    @WebMethod(action = "altaGestion2")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaGestion2(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentarioCliente", targetNamespace = "")
        java.lang.String comentarioCliente,
        @WebParam(name = "comentarioReceptor", targetNamespace = "")
        java.lang.String comentarioReceptor,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @WebResult(name = "obtenerInfoAdjResolReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerInfoAdjResolResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerInfoAdjResolResponse")
    @RequestWrapper(localName = "obtenerInfoAdjResol", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerInfoAdjResol")
    @WebMethod(action = "obtenerInfoAdjResol")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoListaInfoAdjWS obtenerInfoAdjResol(
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.Integer ideGestionNro,
        @WebParam(name = "codSectorResol", targetNamespace = "")
        java.lang.String codSectorResol
    );

    @WebResult(name = "confirmarImpresionRectorReturn", targetNamespace = "")
    @ResponseWrapper(localName = "confirmarImpresionRectorResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ConfirmarImpresionRectorResponse")
    @RequestWrapper(localName = "confirmarImpresionRector", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ConfirmarImpresionRector")
    @WebMethod(action = "confirmarImpresionRector")
    public ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf15877034341440052060NillableString confirmarImpresionRector(
        @WebParam(name = "cod_user", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "cod_entidad", targetNamespace = "")
        java.lang.String codEntidad,
        @WebParam(name = "ide_gestion_sector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ide_gestion_nro", targetNamespace = "")
        int ideGestionNro,
        @WebParam(name = "cod_result_impresion", targetNamespace = "")
        int codResultImpresion,
        @WebParam(name = "desc_result_impresion", targetNamespace = "")
        java.lang.String descResultImpresion
    );

    @WebResult(name = "avanzarGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "avanzarGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionResponse")
    @RequestWrapper(localName = "avanzarGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestion")
    @WebMethod(action = "avanzarGestion")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS avanzarGestion(
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.String ideGestionNro,
        @WebParam(name = "accion", targetNamespace = "")
        java.lang.String accion,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "codRtaResolPredef", targetNamespace = "")
        java.lang.String codRtaResolPredef,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @WebResult(name = "resolverGestionFinalReturn", targetNamespace = "")
    @ResponseWrapper(localName = "resolverGestionFinalResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ResolverGestionFinalResponse")
    @RequestWrapper(localName = "resolverGestionFinal", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ResolverGestionFinal")
    @WebMethod(action = "resolverGestionFinal")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS resolverGestionFinal(
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.String ideGestionNro,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "codRtaResolPredef", targetNamespace = "")
        java.lang.String codRtaResolPredef,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "ideGestionSectorAvance", targetNamespace = "")
        java.lang.String ideGestionSectorAvance
    );

    @WebResult(name = "altaResolInmeditaReturn", targetNamespace = "")
    @ResponseWrapper(localName = "altaResolInmeditaResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmeditaResponse")
    @RequestWrapper(localName = "altaResolInmedita", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaResolInmedita")
    @WebMethod(action = "altaResolInmedita")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaResolInmedita(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida,
        @WebParam(name = "comentarioResol", targetNamespace = "")
        java.lang.String comentarioResol,
        @WebParam(name = "codRtaResolPredefResol", targetNamespace = "")
        java.lang.String codRtaResolPredefResol,
        @WebParam(name = "comentarioResp", targetNamespace = "")
        java.lang.String comentarioResp,
        @WebParam(name = "codRtaResolPredefResp", targetNamespace = "")
        java.lang.String codRtaResolPredefResp,
        @WebParam(name = "favorabilidad", targetNamespace = "")
        java.lang.String favorabilidad
    );

    @WebResult(name = "altaGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "altaGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestionResponse")
    @RequestWrapper(localName = "altaGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestion")
    @WebMethod(action = "altaGestion")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaGestion(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codSector", targetNamespace = "")
        java.lang.String codSector,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @WebResult(name = "avanzarGestionTramaReturn", targetNamespace = "")
    @ResponseWrapper(localName = "avanzarGestionTramaResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionTramaResponse")
    @RequestWrapper(localName = "avanzarGestionTrama", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AvanzarGestionTrama")
    @WebMethod(action = "avanzarGestionTrama")
    public java.lang.String avanzarGestionTrama(
        @WebParam(name = "data", targetNamespace = "")
        java.lang.String data
    );

    @WebResult(name = "anulaGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "anulaGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AnulaGestionResponse")
    @RequestWrapper(localName = "anulaGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AnulaGestion")
    @WebMethod(action = "anulaGestion")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoWS anulaGestion(
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.Integer ideGestionNro,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario
    );

    @WebResult(name = "reservarNroGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "reservarNroGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ReservarNroGestionResponse")
    @RequestWrapper(localName = "reservarNroGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ReservarNroGestion")
    @WebMethod(action = "reservarNroGestion")
    public java.lang.Integer reservarNroGestion(
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso
    );

    @WebResult(name = "consultaEstadoGestionReturn", targetNamespace = "")
    @ResponseWrapper(localName = "consultaEstadoGestionResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ConsultaEstadoGestionResponse")
    @RequestWrapper(localName = "consultaEstadoGestion", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ConsultaEstadoGestion")
    @WebMethod(action = "consultaEstadoGestion")
    public ar.com.santanderrio.obp.generated.webservices.productos.EstadoGestionWS consultaEstadoGestion(
        @WebParam(name = "ideGestionSector", targetNamespace = "")
        java.lang.String ideGestionSector,
        @WebParam(name = "ideGestionNro", targetNamespace = "")
        java.lang.Integer ideGestionNro
    );

    @WebResult(name = "altaGestionUserReturn", targetNamespace = "")
    @ResponseWrapper(localName = "altaGestionUserResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestionUserResponse")
    @RequestWrapper(localName = "altaGestionUser", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.AltaGestionUser")
    @WebMethod(action = "altaGestionUser")
    public ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS altaGestionUser(
        @WebParam(name = "codAsociacion", targetNamespace = "")
        java.lang.Integer codAsociacion,
        @WebParam(name = "tipoPersona", targetNamespace = "")
        java.lang.String tipoPersona,
        @WebParam(name = "nup", targetNamespace = "")
        java.lang.Integer nup,
        @WebParam(name = "codUser", targetNamespace = "")
        java.lang.String codUser,
        @WebParam(name = "medioIngreso", targetNamespace = "")
        java.lang.Integer medioIngreso,
        @WebParam(name = "comentario", targetNamespace = "")
        java.lang.String comentario,
        @WebParam(name = "infoRequerida", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida
    );

    @WebResult(name = "obtenerGestionesSucuAdminReturn", targetNamespace = "")
    @ResponseWrapper(localName = "obtenerGestionesSucuAdminResponse", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerGestionesSucuAdminResponse")
    @RequestWrapper(localName = "obtenerGestionesSucuAdmin", targetNamespace = "http://webService.gestcli.ges.rio.com", className = "ar.com.santanderrio.obp.generated.webservices.productos.ObtenerGestionesSucuAdmin")
    @WebMethod(action = "obtenerGestionesSucuAdmin")
    public ar.com.santanderrio.obp.generated.webservices.productos.RetornoListaGestionClienteWS obtenerGestionesSucuAdmin(
        @WebParam(name = "sucuAdmin", targetNamespace = "")
        java.lang.String sucuAdmin,
        @WebParam(name = "dias", targetNamespace = "")
        java.lang.Integer dias,
        @WebParam(name = "tiposGestion", targetNamespace = "")
        java.lang.String tiposGestion,
        @WebParam(name = "estadoActual", targetNamespace = "")
        java.lang.String estadoActual
    );
}
