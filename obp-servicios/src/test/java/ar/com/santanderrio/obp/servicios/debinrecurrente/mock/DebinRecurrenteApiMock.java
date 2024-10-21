package ar.com.santanderrio.obp.servicios.debinrecurrente.mock;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.*;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.PrestacionView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see {@link TokenOperacionDTO}
 */
public class DebinRecurrenteApiMock {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteApiMock.class);

    public static List<SellerWithCategoryDTO> getDefaultSellers() {
        List<SellerWithCategoryDTO> sellers = new ArrayList<SellerWithCategoryDTO>();
        int document = 11222333;
        StringBuilder fancyName = new StringBuilder("FANCY TEST");
        for (int i = 0; i < 5; i++) {
            document += i;
            fancyName.append(i);
            SellerWithCategoryDTO sellerToBeAdded = new SellerWithCategoryDTO(
                    "",
                    Arrays.asList(new CategoryRequestDTO("", "VARIOS")),
                    "",
                    "20" + document + "5",
                    "",
                    fancyName.toString()
            );
            sellers.add(sellerToBeAdded);
        }
        return sellers;
    }

    public static List<VendedorView> getDefaultVendedoresView(List<SellerWithCategoryDTO> listaEmpresas) {
        List<VendedorView> vendedorViewList = new ArrayList<VendedorView>();
        for (SellerWithCategoryDTO empresaDTO : listaEmpresas) {
            VendedorView vendedorView = new VendedorView(
                    empresaDTO.getCategories().size() >= 1 ? empresaDTO.getCategories().get(0).getName() : "",
                    empresaDTO.getCuit(),
                    empresaDTO.getFancyName());
            vendedorViewList.add(vendedorView);
        }
        return vendedorViewList;
    }

    public static SellerWithProvisionDTO getDefaultSellerWithProvisions(String cuit) {
        List<ProvisionDTO> provisions = new ArrayList<ProvisionDTO>();
        for (int i = 0; i < 5; i++) {
            provisions.add(
                    new ProvisionDTO(0, 256, "Provision " + i, "Ayuda ref " + i)
            );
        }
        return new SellerWithProvisionDTO(
                cuit,
                "FANCY TEST",
                provisions
        );
    }

    public static SellerWithProvisionDTO getDefaultSellerWithoutProvisions(String cuit) {
        List<ProvisionDTO> provisions = new ArrayList<ProvisionDTO>();
        return new SellerWithProvisionDTO(
                cuit,
                "FANCY TEST",
                provisions
        );
    }


    public static VendedorPrestacionesView getDefaultVendedorPrestacionesView(SellerWithProvisionDTO sellerWithProvisions) {
        List<PrestacionView> prestacionViewList = new ArrayList<PrestacionView>();
        for(ProvisionDTO prov : sellerWithProvisions.getServicios()) {
            PrestacionView prestacionView = new PrestacionView(
                    prov.getMinimo(),
                    prov.getMaximo(),
                    prov.getNombre(),
                    prov.getTooltipReferencia());
            prestacionViewList.add(prestacionView);
        }
        return new VendedorPrestacionesView(
                sellerWithProvisions.getCuit(),
                sellerWithProvisions.getNombreFantasia(),
                prestacionViewList
        );
    }
    public static BuyerRecurrenceListRequestDTO getDefaultRecurrenceListDTO() {
        ObjectMapper objectMapper = new ObjectMapper();
        BuyerRecurrenceListRequestDTO recurrenceListDTO = null;
        try {
             recurrenceListDTO = objectMapper.readValue(jsonRecurrenceDebinApi(), BuyerRecurrenceListRequestDTO.class);
        }catch (Exception e){
            LOGGER.error("There was an error parsing");
        }
        return recurrenceListDTO;
    }

    public static ProviderErrorDTO getDefaultProviderErrorDTO(int rootCode){
        ProviderErrorDTO providerErrorDTO = new ProviderErrorDTO();
        providerErrorDTO.setProvider("COELSA");
        providerErrorDTO.setRootCode(rootCode);
        DetailErrorDTO detailErrorDTO = new DetailErrorDTO();
        List<ErrorDTO> errorDTOS = new ArrayList<ErrorDTO>() ;
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("C0-5198");
        errorDTO.setType("invalid_field");
        errorDTO.setMessage("JSON INCORRECTO");
        errorDTOS.add(errorDTO);
        detailErrorDTO.setErrors(errorDTOS);
        providerErrorDTO.setRootDetail(detailErrorDTO);
        return providerErrorDTO;
    }
    private static String jsonRecurrenceDebinApi(){
        return "{\n" +
                "    \"recurrences\": [\n" +
                "        {\n" +
                "            \"sellerCuit\": \"20045528996\",\n" +
                "            \"sellerName\": \"CARLOS ALBERTO CUPI\",\n" +
                "            \"buyerCuit\": \"27473501231\",\n" +
                "            \"buyerCbu\": \"0720000701560000000178\",\n" +
                "            \"debin\": {\n" +
                "                \"currency\": \"ARS\",\n" +
                "                \"detail\": \"DEGUSTACION DE CHOCOLATES\",\n" +
                "                \"concept\": \"VAR\",\n" +
                "                \"provision\": \"prueba aviso7\",\n" +
                "                \"reference\": \"1234\"\n" +
                "            },\n" +
                "            \"recurrenceId\": 14677,\n" +
                "            \"status\": \"INACTIVE_TEMP\",\n" +
                "            \"creationTimestamp\": \"2020-12-22T13:01:39.743\",\n" +
                "            \"creationCuit\": \"20045528996\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"sellerCuit\": \"20045528996\",\n" +
                "            \"sellerName\": \"CARLOS ALBERTO CUPI\",\n" +
                "            \"buyerCuit\": \"27473501231\",\n" +
                "            \"buyerCbu\": \"0720000701560000000178\",\n" +
                "            \"debin\": {\n" +
                "                \"currency\": \"ARS\",\n" +
                "                \"detail\": \"DEGUSTACION DE CHOCOLATES\",\n" +
                "                \"concept\": \"VAR\",\n" +
                "                \"provision\": \"Telefono\",\n" +
                "                \"reference\": \"1234\"\n" +
                "            },\n" +
                "            \"recurrenceId\": 14676,\n" +
                "            \"status\": \"INACTIVE_DEF\",\n" +
                "            \"creationTimestamp\": \"2020-12-22T12:58:56.233\",\n" +
                "            \"creationCuit\": \"20045528996\"\n" +
                "        },\n" +
                "       {\n" +
                "            \"sellerCuit\": \"20045528996\",\n" +
                "            \"sellerName\": \"CARLOS ALBERTO CUPI\",\n" +
                "            \"buyerCuit\": \"27473501231\",\n" +
                "            \"buyerCbu\": \"0720000701560000000178\",\n" +
                "            \"debin\": {\n" +
                "                \"currency\": \"ARS\",\n" +
                "                \"detail\": \"DEGUSTACION DE CHOCOLATES\",\n" +
                "                \"concept\": \"VAR\",\n" +
                "                \"provision\": \"prueba aviso7\",\n" +
                "                \"reference\": \"1234\"\n" +
                "            },\n" +
                "            \"recurrenceId\": 14677,\n" +
                "            \"status\": \"PENDING\",\n" +
                "            \"creationTimestamp\": \"2020-12-22T13:01:39.743\",\n" +
                "            \"creationCuit\": \"20045528996\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalPages\": 4\n" +
                "}";
    }

    public static CrearRecurrenciaView obtenerCrearRecurrenciaView() {
        CrearRecurrenciaView crearRecurrenciaView = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            crearRecurrenciaView = objectMapper.readValue(jsonCrearRecurrenciaView(), CrearRecurrenciaView.class);
        }catch (Exception e){
            LOGGER.error("There was an error parsing");
        }
        return crearRecurrenciaView;
    }

    private static String jsonCrearRecurrenciaView(){
        return "{\n" +
                "   \"nombreFantasia\":\"VALERIA ROCIO PE#ALVA\",\n" +
                "   \"cbuComprador\":\"0720000788000006388018\",\n" +
                "   \"cuitComprador\":\"20209204100\",\n" +
                "   \"concepto\":\"VAR\",\n" +
                "   \"moneda\":\"ARS\",\n" +
                "   \"detalle\":\"shine\",\n" +
                "   \"provision\":\"Prueba Aviso\",\n" +
                "   \"referencia\":\"mi cuit es 20270567860\",\n" +
                "   \"cuitVendedor\":\"30693283724\",\n" +
                "   \"desafio\":{\n" +
                "      \"operacion\":0,\n" +
                "      \"valorNotificarRSA\":false,\n" +
                "      \"reintentosAgotados\":false,\n" +
                "      \"clienteDTO\":null,\n" +
                "      \"tipoDesafio\":\"TOKEN\",\n" +
                "      \"tokenOperacion\":{\n" +
                "         \"mensaje\":\" <p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>\",\n" +
                "         \"tipoDesafio\":\"TOKEN\",\n" +
                "         \"ingresoToken\":123456\n" +
                "      },\n" +
                "      \"coordenadasOperacion\":null,\n" +
                "      \"banelcoOperacion\":null,\n" +
                "      \"cvv2Operacion\":null,\n" +
                "      \"codigoEstadisticaSolicitudToken\":null,\n" +
                "      \"codigoEstadisticaValidacionToken\":null,\n" +
                "      \"codigoEstadisticaSolicitudCoordenadas\":null,\n" +
                "      \"codigoEstadisticaValidacionCoordenadas\":null,\n" +
                "      \"codigoEstadisticaSolicitudBanelco\":null,\n" +
                "      \"codigoEstadisticaValidacionBanelco\":null,\n" +
                "      \"codigoEstadisticaSinDesafios\":null\n" +
                "   },\n" +
                "   \"mensajeFeedback\":\"\",\n" +
                "   \"nroCuenta\":\"000-063880/1\",\n" +
                "   \"datosComprobante\":null\n" +
                "}";
    }

    public static Respuesta<CrearRecurrenciaView> respuestaCrearRecurrenciaView(EstadoRespuesta estadoRespuesta){

        Respuesta<CrearRecurrenciaView> respuesta = new Respuesta<CrearRecurrenciaView>();

        respuesta.setEstadoRespuesta(estadoRespuesta);

        return respuesta;
    }

    public static Respuesta<AutentificacionDTO> respuestaAutentificacionDTO(EstadoRespuesta estadoRespuesta){

        Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();

        respuesta.setEstadoRespuesta(estadoRespuesta);

        return respuesta;
    }

    public static Respuesta<RecurrenceDTO> respuestaOkRecurrenceDTO(){

        Respuesta<RecurrenceDTO> respuesta = new Respuesta<RecurrenceDTO>();

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        RecurrenceDTO recurrenceDTO = new RecurrenceDTO(123456);

        respuesta.setRespuesta(recurrenceDTO);

        return respuesta;
    }

    public static CrearRecurrenciaView obtenerRespuestaFinal() {
        CrearRecurrenciaView crearRecurrenciaView = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            crearRecurrenciaView = objectMapper.readValue(jsonCrearRecurrenciaViewFinal(), CrearRecurrenciaView.class);
        }catch (Exception e){
            LOGGER.error("There was an error parsing");
        }
        return crearRecurrenciaView;
    }

    private static String jsonCrearRecurrenciaViewFinal(){
        return "{\n" +
                "      \"datosComprobante\":{\n" +
                "         \"mostrarDevolucionDA\":false,\n" +
                "         \"servicio\":\"Prueba Aviso\",\n" +
                "         \"fechaOperacion\":\"11/03/2021\",\n" +
                "         \"empresa\":\"VALERIA ROCIO PE#ALVA\",\n" +
                "         \"pmcServicioJasper\":\"pmc-servicio.jasper\",\n" +
                "         \"esDebitoAutomatico\":false,\n" +
                "         \"cuitVendedor\":\"30693283724\",\n" +
                "         \"referencia\":\"mi cuit es 20270567860\",\n" +
                "         \"numeroCuenta\":\"000-063880/1\",\n" +
                "         \"tipoCuenta\":\"Cuenta única\",\n" +
                "         \"cuitComprador\":\"20-20920410-0\",\n" +
                "         \"concepto\":\"VAR\",\n" +
                "         \"descripcion\":\"Varios\",\n" +
                "         \"numeroComprobante\":\"PROXIMAMENTE\",\n" +
                "         \"idRecurrencia\":\"990975444311532750\",\n" +
                "         \"efectuada\":false\n" +
                "      }\n" +
                "}";
    }

    public static Respuesta<CrearRecurrenciaView> obtenerRespuestaCrearRecurrenciaViewFinal(
            CrearRecurrenciaView crearRecurrenciaView,
            String tag,
            String mensaje
    ){
        Respuesta<CrearRecurrenciaView> rta = new Respuesta<CrearRecurrenciaView>();
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        rta.setRespuesta(crearRecurrenciaView);
        rta.setRespuestaVacia(false);
        rta.add(obtenerItemMensajeRespuesta(tag, mensaje));
        return rta;
    }

    public static DatosComprobanteDebinRecurrente obtenerComprobante(CrearRecurrenciaView crearRecurrenciaView, Cliente cliente){

        DatosComprobanteDebinRecurrente comprobante = new DatosComprobanteDebinRecurrente();
        comprobante.setEmpresa(crearRecurrenciaView.getNombreFantasia());
        comprobante.setServicio(crearRecurrenciaView.getProvision());
        comprobante.setCuitVendedor(crearRecurrenciaView.getCuitVendedor());
        comprobante.setReferencia(crearRecurrenciaView.getReferencia());
        comprobante.setNumeroCuenta(crearRecurrenciaView.getNroCuenta());
        comprobante.setCuitComprador(cliente.getNumeroCUILCUIT());
        comprobante.setConcepto(crearRecurrenciaView.getConcepto());
        comprobante.setDescripcion(ConceptoTransferenciaEnum.getConceptoFromCodigo(crearRecurrenciaView.getConcepto()).getDescripcion());
        comprobante.setFechaOperacion(new DateTime().toString("dd/MM/yyyy"));
        comprobante.setNumeroComprobante("PROXIMAMENTE");
        comprobante.setIdRecurrencia("990975444311532750");

        return comprobante;

    }

    public static Cliente obtenerCliente(){

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();

        Cuenta cuentaDos = new Cuenta(cuenta, TipoCuenta.CUENTA_UNICA);
        cuentaDos.setCbu("0720000788000006388018");

        cuentas.add(cuentaDos);
        cliente.setCuentas(cuentas);
        cliente.setNumeroCUILCUIT("20-20920410-0");
        cliente.getCuenta("0720000788000006388018").setNroCuentaProducto("0638801");
        cliente.setNup("03992184");

        return cliente;
    }

    private static ItemMensajeRespuesta obtenerItemMensajeRespuesta(String tag, String mensaje) {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError(TipoError.OK.getDescripcion());
        itemMensajeRespuesta.setTag(tag);
        itemMensajeRespuesta.setMensaje(mensaje);
        return itemMensajeRespuesta;
    }

}
