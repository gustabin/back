package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.feature;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AumentoLimiteTransferenciaInOutViewFeature {
    public static AumentoLimiteTransferenciaInOutView getAumentoLimiteTransferenciaInOutView() {
        AumentoLimiteTransferenciaInOutView view = new AumentoLimiteTransferenciaInOutView();
        view.setAliasDestino(null);
        view.setBanco("BANCO CREDICOOP");
        view.setCantDiasUltimoCambioClave(null);
        view.setCantDiasUltimoCambioToken(null);
        view.setCbu("1910014855001403030650");
        view.setCbu2(null);
        view.setConcepto(null);
        view.setControlSum(null);
        view.setCuentaPropia(Boolean.FALSE);
        view.setCuentasView(getCuentasView());
        view.setCuitCuil("30-70913308-6");
        view.setFechaCreacionDestinatario("017-03-14-03.56.48.000000");
        view.setFechaEjecucion("31/03/2023");
        view.setIdSesion("911688191509531648");
        view.setIgnorarRSA(Boolean.FALSE);
        view.setImporte("2500000");
        view.setMoneda("peso");
        view.setMonedasDisponibles(getMonedasDisponibles());
        view.setNroCuenta("033-366393/6");
        view.setTipoCuentaDescripcion("Cuenta única");
        view.setTipoOperacion(OperacionesRSAEnum.TRANSFERENCIA);
        view.setTitular("Brenson Autos Sa");
        view.setTransferenciaManual(Boolean.FALSE);
        view.setValidarBloqueo(Boolean.FALSE);
        view.setIsRioRio(Boolean.FALSE);
        view.setSaldoCuentaOrigen("1.233.002");
        view.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        view.setFechaOperacion("012-03-14-03.56.48.000000");
        view.setCuitCliente("30-70913506-6");
        view.setScoringDestinatario(1);
        BiocatchResponseDataDTO biocatchData = new BiocatchResponseDataDTO(1,"",1,"",false,false, false, false, false,1,1, Collections.<String>emptyList(),Collections.<String>emptyList());
        view.setBiocatchResponseDataDTO(biocatchData);
        view.setCantDiasUltimoCambioMail(8);
        return view;
    }

    public static AumentoLimiteTransferenciaInOutView getAumentoLimiteTransferenciaInOutView2() {
        AumentoLimiteTransferenciaInOutView view = new AumentoLimiteTransferenciaInOutView();
        view.setAliasDestino(null);
        view.setBanco("BANCO SANTANDER");
        view.setCantDiasUltimoCambioClave(null);
        view.setCantDiasUltimoCambioToken(null);
        view.setCbu("1910014855001403030650");
        view.setCbu2(null);
        view.setConcepto(null);
        view.setControlSum(null);
        view.setCuentaPropia(Boolean.FALSE);
        view.setCuentasView(getCuentasView());
        view.setCuitCuil("30-70913308-6");
        view.setFechaCreacionDestinatario("017-03-14-03.56.48.000000");
        view.setFechaEjecucion("31/03/2023");
        view.setIdSesion("911688191509531648");
        view.setIgnorarRSA(Boolean.FALSE);
        view.setImporte("2500000");
        view.setMoneda("peso");
        view.setMonedasDisponibles(getMonedasDisponibles());
        view.setNroCuenta("033-366393/6");
        view.setTipoCuentaDescripcion("Cuenta única");
        view.setTipoOperacion(OperacionesRSAEnum.TRANSFERENCIA);
        view.setTitular("Brenson Autos Sa");
        view.setTransferenciaManual(Boolean.FALSE);
        view.setValidarBloqueo(Boolean.FALSE);
        view.setIsRioRio(Boolean.TRUE);
        view.setSaldoCuentaOrigen("1.233.002");
        view.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        view.setControlSum(getTansferenciaSumResponse());
        view.setCantDiasUltimoCambioToken(2);
        view.setCantDiasUltimoCambioClave(2);
        view.setFechaOperacion("012-03-14-03.56.48.000000");
        view.setDesafio(null);
        view.setCuitCliente("30-70913506-6");
        view.setScoringDestinatario(0);
        BiocatchResponseDataDTO biocatchData = new BiocatchResponseDataDTO(1,"",1,"",false,false, false, false, false,1,1, Collections.<String>emptyList(),Collections.<String>emptyList());
        view.setBiocatchResponseDataDTO(biocatchData);
        return view;
    }

    private static TransferenciaSumResponse getTansferenciaSumResponse() {
        TransferenciaSumResponse response = new TransferenciaSumResponse();
        response.setDestinationCuit("27-29336562-5");
        response.setNup(1236548L);
        response.setNupAmount(new BigDecimal(20563));
        response.setDestinationCuitAmount(new BigDecimal(25000));
        response.setDestinationCuitQuantity(2);
        return response;
    }

    private static List<String> getMonedasDisponibles() {
        return Arrays.asList("Pesos", "Dolares");
    }

    public static CuentasView getCuentasView() {
        CuentasView cuentasView = new CuentasView();
        cuentasView.setCuentas(getCuentasAdhesionDebitoViewList());
        cuentasView.setHasCuentasActivas(Boolean.FALSE);
        cuentasView.setSelected(0);
        return cuentasView;
    }

    public static CuentasView getCuentasView2() {
        CuentasView cuentasView = new CuentasView();
        cuentasView.setCuentas(getCuentasAdhesionDebitoViewList());
        cuentasView.setHasCuentasActivas(Boolean.FALSE);
        cuentasView.setSelected(0);
        return cuentasView;
    }


    public static List<CuentasAdhesionDebitoView> getCuentasAdhesionDebitoViewList() {
        List<CuentasAdhesionDebitoView> list = new ArrayList<CuentasAdhesionDebitoView>();
        list.add(getCuentasAdhesionDebitoViewA());
        list.add(getCuentasAdhesionDebitoViewB());
        return list;
    }

    public static CuentasAdhesionDebitoView getCuentasAdhesionDebitoViewA() {
        CuentasAdhesionDebitoView cadView = new CuentasAdhesionDebitoView();
        cadView.setAbreviaturaTipoCuenta("CU");
        cadView.setAlias(null);
        cadView.setAliasCbu(null);
        cadView.setApellidoCliente("Muilando");
        cadView.setCbu("0720033588000036639368");
        cadView.setDescripcionTipoCuenta("Cuenta única");
        cadView.setFechaDesdeMovimiento(null);
        cadView.setFechaHastaMovimiento(null);
        cadView.setHasAlias(Boolean.FALSE);
        cadView.setIsCerrada(Boolean.FALSE);
        cadView.setIsCuentaSueldo(Boolean.FALSE);
        cadView.setIsCuentaUnica(Boolean.TRUE);
        cadView.setIsDescubiertoUtilizado(Boolean.FALSE);
        cadView.setIsFavorito(Boolean.TRUE);
        cadView.setIsSaldoCajaAhorroCero(Boolean.TRUE);
        cadView.setIsSaldoCajaAhorroNegativo(null);
        cadView.setIsSaldoCuentaCorrienteCero(Boolean.TRUE);
        cadView.setIsSaldoCuentaCorrienteNegativo(null);
        cadView.setIsSaldoDescubiertoCero(Boolean.TRUE);
        cadView.setIsSaldoDescubiertoNegativo(null);
        cadView.setIsSaldoDolaresCero(null);
        cadView.setIsSaldoPesosNegativo(null);
        cadView.setIsTraspasoAutomatico(Boolean.FALSE);
        cadView.setNombreCliente("Constancio Percy");
        cadView.setNumero("033-366393/6");
        cadView.setNumeroIdentificacion("21.531.419");
        cadView.setPoderCompra(null);
        cadView.setRepatriacion(null);
        cadView.setSaldoCajaAhorro("0,00");
        cadView.setSaldoCuentaCorriente("0,00");
        cadView.setSaldoDescubierto("0,00");
        cadView.setSaldoDolares("195.95");
        cadView.setSaldoPesos("3.376.488,32");
        cadView.setShowDescubierto(Boolean.FALSE);
        cadView.setShowRealizarTraspaso(Boolean.FALSE);
        cadView.setShowSaldoDolares(Boolean.TRUE);
        cadView.setShowSaldoPesos(Boolean.TRUE);
        cadView.setShowSolicitarTraspaso(Boolean.FALSE);
        cadView.setSignoSaldoCajaAhorro("");
        cadView.setSignoSaldoCuentaCorriente("");
        cadView.setSignoSaldoDolares("");
        cadView.setSignoSaldoPesos("");
        cadView.setTipoIdentificacion("DNI");
        cadView.setUrlReporteCBU("cuentas/imprimir?numeroCuenta=0000000003663936&sucursal=0033");
        return cadView;
    }


    public static CuentasAdhesionDebitoView getCuentasAdhesionDebitoViewB() {
        CuentasAdhesionDebitoView cadView = new CuentasAdhesionDebitoView();
        cadView.setAbreviaturaTipoCuenta("CCP");
        cadView.setAlias(null);
        cadView.setAliasCbu(null);
        cadView.setApellidoCliente("Muilando");
        cadView.setCbu("0720033520000000819954");
        cadView.setDescripcionTipoCuenta("Cuenta corriente en $");
        cadView.setFechaDesdeMovimiento(null);
        cadView.setFechaHastaMovimiento(null);
        cadView.setHasAlias(Boolean.FALSE);
        cadView.setIsCerrada(Boolean.FALSE);
        cadView.setIsCuentaSueldo(Boolean.FALSE);
        cadView.setIsCuentaUnica(Boolean.TRUE);
        cadView.setIsDescubiertoUtilizado(Boolean.FALSE);
        cadView.setIsFavorito(Boolean.TRUE);
        cadView.setIsSaldoCajaAhorroCero(Boolean.TRUE);
        cadView.setIsSaldoCajaAhorroNegativo(null);
        cadView.setIsSaldoCuentaCorrienteCero(Boolean.TRUE);
        cadView.setIsSaldoCuentaCorrienteNegativo(null);
        cadView.setIsSaldoDescubiertoCero(Boolean.TRUE);
        cadView.setIsSaldoDescubiertoNegativo(null);
        cadView.setIsSaldoDolaresCero(null);
        cadView.setIsSaldoPesosNegativo(null);
        cadView.setIsTraspasoAutomatico(Boolean.FALSE);
        cadView.setNombreCliente("Constancio Percy");
        cadView.setNumero("033-008199/5");
        cadView.setNumeroIdentificacion("21.531.419");
        cadView.setPoderCompra(null);
        cadView.setRepatriacion(null);
        cadView.setSaldoCajaAhorro("0,00");
        cadView.setSaldoCuentaCorriente("0,00");
        cadView.setSaldoDescubierto("0,00");
        cadView.setSaldoDolares("0,00");
        cadView.setSaldoPesos("3.394,49");
        cadView.setShowDescubierto(Boolean.FALSE);
        cadView.setShowRealizarTraspaso(Boolean.FALSE);
        cadView.setShowSaldoDolares(Boolean.FALSE);
        cadView.setShowSaldoPesos(Boolean.TRUE);
        cadView.setShowSolicitarTraspaso(Boolean.FALSE);
        cadView.setSignoSaldoCajaAhorro("");
        cadView.setSignoSaldoCuentaCorriente("");
        cadView.setSignoSaldoDolares("");
        cadView.setSignoSaldoPesos("");
        cadView.setTipoIdentificacion("DNI");
        cadView.setUrlReporteCBU("cuentas/imprimir?numeroCuenta=0000000000081995&sucursal=0033");
        return cadView;
    }

    public static AgendaDestinatarioDTO obtenerDestinatarioDTO() {
        AgendaDestinatarioDTO dto = new AgendaDestinatarioDTO();
        dto.setMensajeCabecera("");
        List<DestinatarioAgendaDTO> destinatariosView = new ArrayList<DestinatarioAgendaDTO>();
        dto.setListaDestinatarios(destinatariosView);
        return dto;
    }

    public static List<ItemMensajeRespuesta> obtenerListaMensajesRespuesta() {

        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        item.setMensaje("");
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(item);
        return items;

    }

    public static AumentoLimiteTransferenciaInOutView obtenerView() {

        AumentoLimiteTransferenciaInOutView view = new AumentoLimiteTransferenciaInOutView();
        DateTime fecha = new DateTime(new Date()).plusDays(7);
        if (fecha.getDayOfWeek() == DateTimeConstants.SATURDAY || fecha.getDayOfWeek() == DateTimeConstants.SUNDAY) {
            fecha = fecha.plusDays(2);
        }
        view.setFechaEjecucion(ISBANStringUtils.formatearFecha(fecha.toDate()));
        view.setNroCuenta("201-363238/1");
        view.setTipoCuentaDescripcion(TipoCuenta.CUENTA_UNICA.getDescripcion());
        view.setNroCuentaDestino("400-872651/1");
        view.setTipoCuentaDestinoDescripcion(TipoCuenta.CUENTA_UNICA.getDescripcion());
        view.setImporte("400000");
        view.setMoneda("peso");
        view.setCuentaPropia(true);
        view.setIsRioRio(false);
        view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        view.setTitular("Santana Carlos Baltazar");
        view.setDesafio(null);
        return view;
    }

    private static AutentificacionDTO getAutenticationDTO() {
        return new AutentificacionDTO();
    }

    public static Cliente obtenerCliente(boolean tieneSoftToken) {
        Cliente cliente = new Cliente();
        cliente.setNup("12312312");
        cliente.setApellido2("Apellido2");
        cliente.setApellido1("Apellido1");
        cliente.setNombre("Nombre");
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003632381");
        cuenta.setNroSucursal("2345");
        cuenta.setContratoAltair("201-363238/1");
        cuenta.setSaldoCUPesos("999999");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("Cuenta Única");
        cuenta.setMonedaAltair("ARS");
        cuenta.setProductoAltair("201-363238/1");
        cuenta.setSubproductoAltair("201-363238/1");
        cuenta.setTipoCuentaSinUnificar("3");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        if (tieneSoftToken) {
            cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
            cuenta.setGrupoAfinidad("012435");
            cuentas.add(cuenta);
        }
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        return cliente;
    }

    public static Cliente obtenerClienteA(boolean tieneSoftToken) {
        Cliente cliente = new Cliente();
        cliente.setNup("12312312");
        cliente.setApellido2("Apellido2");
        cliente.setApellido1("Apellido1");
        cliente.setNombre("Nombre");
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003663936");
        cuenta.setNroSucursal("2345");
        cuenta.setContratoAltair("201-363238/1");
        cuenta.setSaldoCUPesos("999999");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("Cuenta Única");
        cuenta.setMonedaAltair("ARS");
        cuenta.setProductoAltair("201-363238/1");
        cuenta.setSubproductoAltair("201-363238/1");
        cuenta.setTipoCuentaSinUnificar("3");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        if (tieneSoftToken) {
            cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
            cuenta.setGrupoAfinidad("012435");
            cuentas.add(cuenta);
        }
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        return cliente;
    }

    public static List<CuentasAdhesionDebitoView> obtenerCuentasAdhesionDebito() {
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuenta.setIsCerrada(false);
        List<CuentasAdhesionDebitoView> cuentasList = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasList.add(cuenta);
        return cuentasList;
    }

    public static DestinatarioAgendaDTO obtenerDestinatarioAgendaDto() {

        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
        dto.setReferenciaApodo("");
        dto.setNroCuenta("000-063917/0");
        dto.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda());
        dto.setBanco("Macro");
        dto.setTitular("Constancio Milando");
        dto.setCuitCuil("1231231");
        dto.setEmail("asdaq@aslkd.com");
        dto.setCbu("askjdhasukhd");
        dto.setDocumento("123123123");
        dto.setTipoDocumento(TipoDocumentoEnum.DNI.getDescripcion());
        dto.setEsCuentaPropia(true);
        dto.setFechaCreacion("22/12/2017");
        dto.setAlias("asdasd");
        return dto;
    }

    public static Respuesta<List<BigDecimal>> getRespuestaAntiguedad() {
        Respuesta<List<BigDecimal>> rta = new Respuesta<List<BigDecimal>>();
        rta.setRespuesta(getListBigDecimal());
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        return rta;
    }

    private static List<BigDecimal> getListBigDecimal() {
        return Arrays.asList(new BigDecimal(23), new BigDecimal(12), new BigDecimal(3));
    }


    public static Map<String, Object> crearMapaDeLaVista(AumentoLimiteTransferenciaInOutView inView) {
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("cuentaPropia", inView.isCuentaPropia());
        mapaAtributos.put("isRioRio", inView.getIsRioRio());
        mapaAtributos.put("tipoCuentaDescripcion",
                inView.getTipoCuentaDescripcion() != null ? inView.getTipoCuentaDescripcion() : StringUtils.EMPTY);
        mapaAtributos.put("banco", inView.getBanco() != null ? inView.getBanco() : StringUtils.EMPTY);
        mapaAtributos.put("cbu", inView.getCbu() != null ? inView.getCbu() : StringUtils.EMPTY);
        mapaAtributos.put("tipoCuentaDestinoDescripcion", inView.getTipoCuentaDestinoDescripcion() != null
                ? inView.getTipoCuentaDestinoDescripcion() : StringUtils.EMPTY);
        mapaAtributos.put("moneda", inView.getMoneda() != null ? inView.getMoneda() : StringUtils.EMPTY);
        mapaAtributos.put("importe", inView.getImporte() != null ? inView.getImporte() : StringUtils.EMPTY);
        mapaAtributos.put("nroCuenta", inView.getNroCuenta() != null ? inView.getNroCuenta() : StringUtils.EMPTY);
        mapaAtributos.put("fechaEjecucion", StringUtils.EMPTY);
        mapaAtributos.put("nroCuentaDestino",
                inView.getNroCuentaDestino() != null ? inView.getNroCuentaDestino() : StringUtils.EMPTY);

        return mapaAtributos;
    }

    public static BiocatchResponseDataDTO buildBiocatchResponseDataDTO() {

        BiocatchResponseDataDTO biocatchResponseDataDTO = new BiocatchResponseDataDTO();

        biocatchResponseDataDTO.setScore(30);
        biocatchResponseDataDTO.setPolicyAction("Allow");
        biocatchResponseDataDTO.setPolicyID(3502);
        biocatchResponseDataDTO.setPolicyName("DEF_OBP_PERSONAS");
        biocatchResponseDataDTO.setIsBot(false);
        biocatchResponseDataDTO.setIsEmulator(false);
        biocatchResponseDataDTO.setIsRat(false);
        biocatchResponseDataDTO.setIsRecentRat(false);
        biocatchResponseDataDTO.setIsMobileRat(false);
        biocatchResponseDataDTO.setVoiceScam(57);
        biocatchResponseDataDTO.setMax30DayScore(0);

        List<String> riskFactorList = Arrays.asList("R102", "R103", "", "", "");
        biocatchResponseDataDTO.setRiskFactor(riskFactorList);

        List<String> genFactorList = Arrays.asList("G011", "G030", "");
        biocatchResponseDataDTO.setGenFactor(genFactorList);

        return biocatchResponseDataDTO;
    }

    public static BiocatchResponseDataDTO buildBiocatchResponseDataDTOForCustomsFactTest() {

        BiocatchResponseDataDTO biocatchResponseDataDTO = new BiocatchResponseDataDTO();

        biocatchResponseDataDTO.setScore(30);
        biocatchResponseDataDTO.setPolicyAction("Allow");
        biocatchResponseDataDTO.setPolicyID(3502);
        biocatchResponseDataDTO.setPolicyName("DEF_OBP_PERSONAS");
        biocatchResponseDataDTO.setIsBot(false);
        biocatchResponseDataDTO.setIsEmulator(false);
        biocatchResponseDataDTO.setIsRat(false);
        biocatchResponseDataDTO.setIsRecentRat(false);
        biocatchResponseDataDTO.setIsMobileRat(false);
        biocatchResponseDataDTO.setVoiceScam(57);
        biocatchResponseDataDTO.setMax30DayScore(0);

        List<String> riskFactorList = Collections.singletonList("R102");
        biocatchResponseDataDTO.setRiskFactor(riskFactorList);

        List<String> genFactorList = Collections.singletonList("G011");
        biocatchResponseDataDTO.setGenFactor(genFactorList);

        return biocatchResponseDataDTO;
    }

    public static AumentoLimiteTransferenciaInOutView getAumentoLimiteTransferenciaInOutViewBiocatch() {

        AumentoLimiteTransferenciaInOutView view = new AumentoLimiteTransferenciaInOutView();

        view.setAliasDestino(null);
        view.setBanco("BANCO CREDICOOP");
        view.setCantDiasUltimoCambioClave(null);
        view.setCantDiasUltimoCambioToken(null);
        view.setCbu("1910014855001403030650");
        view.setCbu2(null);
        view.setConcepto(null);
        view.setControlSum(null);
        view.setCuentaPropia(Boolean.FALSE);
        view.setCuentasView(getCuentasView());
        view.setCuitCuil("30-70913308-6");
        view.setFechaCreacionDestinatario("017-03-14-03.56.48.000000");
        view.setFechaEjecucion("31/03/2023");
        view.setIdSesion("911688191509531648");
        view.setIgnorarRSA(Boolean.FALSE);
        view.setImporte("2500000");
        view.setMoneda("peso");
        view.setMonedasDisponibles(getMonedasDisponibles());
        view.setNroCuenta("033-366393/6");
        view.setTipoCuentaDescripcion("Cuenta única");
        view.setTipoOperacion(OperacionesRSAEnum.TRANSFERENCIA);
        view.setTitular("Brenson Autos Sa");
        view.setTransferenciaManual(Boolean.FALSE);
        view.setValidarBloqueo(Boolean.FALSE);
        view.setIsRioRio(Boolean.FALSE);
        view.setSaldoCuentaOrigen("1.233.002");
        view.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        view.setFechaOperacion("012-03-14-03.56.48.000000");
        view.setCuitCliente("30-70913506-6");
        view.setBiocatchResponseDataDTO(buildBiocatchResponseDataDTOForCustomsFactTest());

        return view;
    }

    public static Customers buildCustomersCambioMail1()  {

        Customers customers = new Customers();

        customers.setCreatedAt(new Date());
        customers.setUpdatedAt(new Date());

        return customers;
    }

    public static Customers buildCustomersCambioMail2() throws ParseException {

        Customers customers = new Customers();

        DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

        Date createdAt = formateador.parse("2023-04-01");
        Date updateAt = formateador.parse("2023-04-10");

        customers.setCreatedAt(createdAt);
        customers.setUpdatedAt(updateAt);

        return customers;
    }
}
