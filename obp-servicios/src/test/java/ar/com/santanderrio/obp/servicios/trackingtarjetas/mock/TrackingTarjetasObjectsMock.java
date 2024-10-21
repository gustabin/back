package ar.com.santanderrio.obp.servicios.trackingtarjetas.mock;

import java.util.ArrayList;
import java.util.List;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasEvento;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasPieza;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasPiezas;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.TarjetaMonederoEntity;


/**
 * TrackingTarjetasObjectsMock
 * @author Silvina_Luque
 *
 */
public final class TrackingTarjetasObjectsMock {
    
    private TrackingTarjetasObjectsMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * obtenerTrackingTarjetasOutOK
     * @return
     * @throws DAOException
     */
    public static TrackingTarjetasOut obtenerTrackingTarjetasOutOK() throws DAOException {
        TrackingTarjetasOut outResponse = new TrackingTarjetasOut();
        outResponse.setCodRetorno("0");
        outResponse.setPiezas(obtenerTrackingPiezas());
        return outResponse;
    }

    /**
     * obtenerTrackingPiezas
     * @return
     */
    public static TrackingTarjetasPiezas obtenerTrackingPiezas() {
        TrackingTarjetasPiezas piezas = new TrackingTarjetasPiezas();
        List<TrackingTarjetasPieza> listaPiezas = new ArrayList<TrackingTarjetasPieza>();
        //PIEZA STATUS 19  DESTRUIDA VISA CREDITO
        TrackingTarjetasPieza pieza1 = new TrackingTarjetasPieza();
        pieza1.setPiezaAbierta(" ");
        pieza1.setStatusPieza("0");
        pieza1.setTipoCuenta("07");
        pieza1.setSucUbic("099");
        pieza1.setNroIdComponente("AVIS4050710007042851");
        TrackingTarjetasEvento evento1 = new TrackingTarjetasEvento();
        evento1.setEstado("Envió en sucursal de Andreani");
        evento1.setIdEstado("19");
        pieza1.setEvento(evento1);
        pieza1.setStatusEvento("0");
        pieza1.setFecCodEstadoActual("20131226");
        pieza1.setCodEstadoActual("C6");
        listaPiezas.add(pieza1);
        
        //PIEZA STATUS 14 EN TRANSITO AMEX CREDITO
        TrackingTarjetasPieza pieza2 = new TrackingTarjetasPieza();
        pieza2.setPiezaAbierta(" ");
        pieza2.setStatusPieza("0");
        pieza2.setTipoCuenta("42");
        pieza2.setSucUbic("000");
        pieza2.setNroIdComponente("AEXP3777910011145850");
        pieza2.setStatusEvento("0");
        pieza2.setFecCodEstadoActual("20131226");
        pieza2.setCodEstadoActual("TT");
        listaPiezas.add(pieza2);
 
        //PIEZA STATUS 16 EN SUCURSAL DEBITO
        TrackingTarjetasPieza pieza3 = new TrackingTarjetasPieza();
        pieza3.setPiezaAbierta(" ");
        pieza3.setStatusPieza("0");
        pieza3.setTipoCuenta("05");
        pieza3.setSucUbic("019");
        pieza3.setNroIdComponente("ABAE4517660057185414");
        pieza3.setStatusEvento("0");
        pieza3.setFecCodEstadoActual("20131226");
        pieza3.setCodEstadoActual("SU");
        listaPiezas.add(pieza3);
        
        
        //PIEZA STATUS 23  ENREGADA MONEDERO
        TrackingTarjetasPieza pieza4 = new TrackingTarjetasPieza();
        pieza4.setPiezaAbierta(" ");
        pieza4.setStatusPieza("0");
        pieza4.setTipoCuenta("43");
        pieza4.setSucUbic("099");
        pieza4.setNroIdComponente("AMON4050710007042851");
        TrackingTarjetasEvento evento3 = new TrackingTarjetasEvento();
        evento3.setEstado("Envió en sucursal de Andreani");
        evento3.setIdEstado("23");
        pieza4.setEvento(evento3);
        pieza4.setStatusEvento("0");
        pieza4.setFecCodEstadoActual("20131226");
        pieza4.setCodEstadoActual("C6");
        listaPiezas.add(pieza4);      
        
        
        //PIEZA STATUS 17 
        TrackingTarjetasPieza pieza5 = new TrackingTarjetasPieza();
        pieza5.setPiezaAbierta(" ");
        pieza5.setStatusPieza("0");
        pieza5.setTipoCuenta("43");
        pieza5.setSucUbic("099");
        pieza5.setNroIdComponente("AMON4050710007042851");
        TrackingTarjetasEvento evento4 = new TrackingTarjetasEvento();
        evento4.setEstado("Envió en sucursal de Andreani");
        evento4.setIdEstado("17");
        evento4.setSucursal("019");
        pieza5.setEvento(evento4);
        pieza5.setStatusEvento("0");
        pieza5.setFecCodEstadoActual("20131226");
        pieza5.setCodEstadoActual("C6");
        listaPiezas.add(pieza5);      
        
        
        //PIEZA STATUS 35
        TrackingTarjetasPieza pieza6 = new TrackingTarjetasPieza();
        pieza6.setPiezaAbierta(" ");
        pieza6.setStatusPieza("0");
        pieza6.setTipoCuenta("43");
        pieza6.setSucUbic("099");
        pieza6.setNroIdComponente("AMON4050710007042851");
        TrackingTarjetasEvento evento6 = new TrackingTarjetasEvento();
        evento6.setEstado("Descirpcion que viene en el servicio de prueba");
        evento6.setIdEstado("35");
        evento6.setSucursal("019");
        pieza6.setEvento(evento6);
        pieza6.setStatusEvento("0");
        pieza6.setFecCodEstadoActual("20131226");
        pieza6.setCodEstadoActual("C6");
        listaPiezas.add(pieza6); 
        
        
        piezas.setPieza(listaPiezas);
        
        return piezas;
    }

    /**
     * 
     * @return
     */
    public static TrackingTarjetasIn obtenerTrackingTarjetasIn() {
        return new TrackingTarjetasIn();
    }

    /**
     * obtenerCnsTarjetasMonederoOutOK
     * @return
     */
    public static ConsultaTarjetasMonederoOutEntity obtenerCnsTarjetasMonederoOutOK() {
        ConsultaTarjetasMonederoOutEntity   cnsTarjetasMonedero = new ConsultaTarjetasMonederoOutEntity();
        cnsTarjetasMonedero.setCantidadTarjetas(1L);
        cnsTarjetasMonedero.setTarjetasMonedero(obtenerListaTarjetasMonederoEntity());
        return cnsTarjetasMonedero;
    }

    /**
     * obtenerListaTarjetasMonederoEntity
     * @return
     */
    private static List<TarjetaMonederoEntity> obtenerListaTarjetasMonederoEntity() {
        List<TarjetaMonederoEntity> listaMonedero = new ArrayList<TarjetaMonederoEntity>();
        TarjetaMonederoEntity tarjetaMonedero = new TarjetaMonederoEntity();
        tarjetaMonedero.setCodigoProducto("");
        tarjetaMonedero.setDescripcionProducto("descripcionProducto");
        tarjetaMonedero.setEstadoTarjetaTag("");
        tarjetaMonedero.setNumeroCuenta("");
        tarjetaMonedero.setNumeroTarjetaTag("");
        tarjetaMonedero.setSucursal("");
        tarjetaMonedero.setTipoCuenta("");
        tarjetaMonedero.setTipoTarjeta("");
        listaMonedero.add(tarjetaMonedero);
        return listaMonedero;
    }
    
    /**
     * obtenerDatosClienteOKTest
     * @return
     */
    public static Cliente obtenerDatosClienteOKTest()  {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

        cliente.setNup("123456");    
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cliente.setTipoPersona("");
        cliente.setTipoDocumento("N");
        cliente.setDni("30385112");
        cliente.setFechaNacimiento("29091983");
        //Cuenta banelco sucursal larga
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("05");
        cuenta.setNroSucursal("0082");
        cuenta.setNroTarjetaCredito("1234123412341234");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("ABAE");
        cuenta.setNroCuentaProducto("231237123");
        listaCuentas.add(cuenta);
        //Cuenta VISA Titular
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuenta("07");
        cuenta2.setNroSucursal("0082");
        cuenta2.setCbu("0123456789012345678901");
        cuenta2.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta2.setCodigoTitularidad("TI");
        cuenta2.setNroCuentaProducto("231237123");
        cuenta2.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta2);
        //Cuenta Amex Adicional
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setTipoCuenta("42");
        cuenta3.setNroSucursal("0082");
        cuenta3.setCbu("0123456789012345678901");
        cuenta3.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta3.setCodigoTitularidad("A");
        cuenta3.setNroCuentaProducto("231237123");
        cuenta3.setCodigoAplicacion("");
        cuenta3.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta3);
        //Cuenta Amex Titular
        Cuenta cuenta4 = new Cuenta();
        cuenta4.setTipoCuenta("42");
        cuenta4.setNroSucursal("0082");
        cuenta4.setCbu("0123456789012345678901");
        cuenta4.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta4.setCodigoTitularidad("TI");
        cuenta4.setNroCuentaProducto("231237123");
        cuenta4.setCodigoAplicacion("");
        cuenta4.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta4);
        //Cuenta VISA Recargable Adicional
        Cuenta cuenta5 = new Cuenta();
        cuenta5.setTipoCuenta("77");
        cuenta5.setNroSucursal("82");
        cuenta5.setCbu("0123456789012345678901");
        cuenta5.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        cuenta5.setNroTarjetaCredito("1234123412341234");
        cuenta5.setCodigoTitularidad("A");
        cuenta5.setNroCuentaProducto("231237123");
        listaCuentas.add(cuenta5);
        //Cuenta banelco sucursal corta
        Cuenta cuenta6 = new Cuenta();
        cuenta6.setTipoCuenta("05");
        cuenta6.setNroSucursal("82");
        cuenta6.setNroTarjetaCredito("1234123412341234");
        cuenta6.setCbu("0123456789012345678901");
        cuenta6.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta6.setNroOrdenFirmante("001");
        cuenta6.setCodigoAplicacion("ABAE");
        cuenta6.setNroCuentaProducto("231237123");
        listaCuentas.add(cuenta6);
        cliente.setCuentas(listaCuentas);
        
        return cliente;

    }

    /**
     * obtenerTarjetasCreditoOutOK
     * @return
     */
    public static ConsultaDatosTarjetasOut obtenerTarjetasCreditoOutOK() {
        ConsultaDatosTarjetasOut tarjetasOut = new ConsultaDatosTarjetasOut();
        List<TarjetaDatos> listaTarjetas = new ArrayList<TarjetaDatos>();
        TarjetaDatos tarjeta = new TarjetaDatos();
        tarjeta.setApliCtaRelacionada("");
        tarjeta.setEstadoTarjeta("");
        tarjeta.setNroCuenta("");
        tarjeta.setNroTarjeta("1234123412341234");
        tarjeta.setSucursalCtaRelacionada("019");
        listaTarjetas.add(tarjeta);
        tarjetasOut.setTarjetas(listaTarjetas);
        return tarjetasOut;
    }

    /**
     * obtenerTrackingTarjetasOutError
     * @return
     */
    public static TrackingTarjetasOut obtenerTrackingTarjetasOutError() {
        TrackingTarjetasOut outResponse = new TrackingTarjetasOut();
        outResponse.setCodRetorno("1");
        outResponse.setPiezas(null);
        return outResponse;
    }


    /**
     * obtenerTrackingTarjetasOutPiezasError
     * @return
     */
    public static TrackingTarjetasOut obtenerTrackingTarjetasOutPiezasError() {
        TrackingTarjetasOut outResponse = new TrackingTarjetasOut();
        outResponse.setCodRetorno("0");
        outResponse.setPiezas(obtenerTrackingPiezasError());
        return outResponse;
    }

    /**
     * obtenerTrackingPiezasError
     * @return
     */
    public static TrackingTarjetasPiezas obtenerTrackingPiezasError() {
        TrackingTarjetasPiezas piezas = new TrackingTarjetasPiezas();
        List<TrackingTarjetasPieza> listaPiezas = new ArrayList<TrackingTarjetasPieza>();
        //PIEZA STATUS 19  DESTRUIDA VISA CREDITO
        TrackingTarjetasPieza pieza1 = new TrackingTarjetasPieza();
        pieza1.setPiezaAbierta(" ");
        pieza1.setStatusPieza("1");
        listaPiezas.add(pieza1);
        //PIEZA STATUS 14 EN TRANSITO AMEX CREDITO
        TrackingTarjetasPieza pieza2 = new TrackingTarjetasPieza();
        pieza2.setPiezaAbierta(" ");
        pieza2.setStatusPieza("1");
        listaPiezas.add(pieza2);
        piezas.setPieza(listaPiezas);
        
        return piezas;
    }

    /**
     * obtenerRespuestaPiezasDTO
     * @return
     */
    public static List<TrackingPiezaDTO> obtenerRespuestaPiezasDTO() {
        List<TrackingPiezaDTO> listaPiezasDTO = new ArrayList<TrackingPiezaDTO>();
        TrackingTarjetasPieza piezaEntity = new TrackingTarjetasPieza();
        piezaEntity.setStatusPieza("");
        piezaEntity.setTipoCuenta("05");
        piezaEntity.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento = new TrackingTarjetasEvento();
        evento.setIdEstado("12");
        evento.setEstado("Estado");
        piezaEntity.setEvento(evento );
        piezaEntity.setFecCodEstadoActual("20171212");
        piezaEntity.setCodEstadoActual("C6");
        piezaEntity.setSucUbic("0123");
        
        
        TrackingPiezaDTO pieza = new TrackingPiezaDTO(piezaEntity);
        pieza.setEstado("1");
        pieza.setFecha("20171212");
        pieza.setIdEstado("12");
        pieza.setMarcaTarjeta("");
        pieza.setNumeroTarjetaComponente("");
        pieza.setStatusPieza("");
        pieza.setSucursal("0019");
        pieza.setTipoCuentaEnum(TipoCuenta.AMEX);
        listaPiezasDTO.add(pieza);
        
        TrackingTarjetasPieza piezaEntity2 = new TrackingTarjetasPieza();
        piezaEntity2.setStatusPieza("");
        piezaEntity2.setTipoCuenta("05");
        piezaEntity2.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento2 = new TrackingTarjetasEvento();
        evento2.setIdEstado("24");
        evento2.setEstado("Estado");
        piezaEntity2.setEvento(evento2);
        piezaEntity2.setFecCodEstadoActual("20171212");
        piezaEntity2.setCodEstadoActual("C6");
        piezaEntity2.setSucUbic("0123");
        
        
        TrackingPiezaDTO pieza2 = new TrackingPiezaDTO(piezaEntity2);
        pieza2.setEstado("1");
        pieza2.setFecha("20171212");
        pieza2.setIdEstado("24");
        pieza2.setMarcaTarjeta("");
        pieza2.setNumeroTarjetaComponente("");
        pieza2.setStatusPieza("");
        pieza2.setSucursal("0019");
        pieza2.setTipoCuentaEnum(TipoCuenta.AMEX);
        listaPiezasDTO.add(pieza2);
        
        TrackingTarjetasPieza piezaEntity3 = new TrackingTarjetasPieza();
        piezaEntity3.setStatusPieza("");
        piezaEntity3.setTipoCuenta("05");
        piezaEntity3.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento3 = new TrackingTarjetasEvento();
        evento3.setIdEstado("12");
        evento3.setEstado("Estado");
        piezaEntity3.setEvento(evento3);
        piezaEntity3.setFecCodEstadoActual("20171212");
        piezaEntity3.setCodEstadoActual("C6");
        piezaEntity3.setSucUbic("0123");
        
        TrackingPiezaDTO pieza3 = new TrackingPiezaDTO(piezaEntity3);
        pieza3.setEstado("1");
        pieza3.setFecha("20171212");
        pieza3.setIdEstado("12");
        pieza3.setMarcaTarjeta("");
        pieza3.setNumeroTarjetaComponente("");
        pieza3.setStatusPieza("");
        pieza3.setSucursal("0019");
        pieza3.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        listaPiezasDTO.add(pieza3);

        TrackingTarjetasPieza piezaEntity4 = new TrackingTarjetasPieza();
        piezaEntity4.setStatusPieza("");
        piezaEntity4.setTipoCuenta("05");
        piezaEntity4.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento4 = new TrackingTarjetasEvento();
        evento4.setIdEstado("SU");
        evento4.setEstado("Estado");
        piezaEntity4.setEvento(evento4);
        piezaEntity4.setFecCodEstadoActual("20171212");
        piezaEntity4.setCodEstadoActual("C6");
        piezaEntity4.setSucUbic("0123");
        
        TrackingPiezaDTO pieza4 = new TrackingPiezaDTO(piezaEntity4);
        pieza4.setEstado("3");
        pieza4.setFecha("20171212");
        pieza4.setIdEstado("SU");
        pieza4.setMarcaTarjeta("");
        pieza4.setNumeroTarjetaComponente("");
        pieza4.setStatusPieza("");
        pieza4.setSucursal("0019");
        pieza4.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        listaPiezasDTO.add(pieza4);
        
        TrackingTarjetasPieza piezaEntity5 = new TrackingTarjetasPieza();
        piezaEntity5.setStatusPieza("");
        piezaEntity5.setTipoCuenta("05");
        piezaEntity5.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento5 = new TrackingTarjetasEvento();
        evento5.setIdEstado("17");
        evento5.setEstado("Descripcion de prueba");
        evento5.setSucursal("019");
        piezaEntity5.setEvento(evento5);
        piezaEntity5.setFecCodEstadoActual("20171212");
        piezaEntity5.setCodEstadoActual("C6");
        piezaEntity5.setSucUbic("0123");
        
        TrackingPiezaDTO pieza5 = new TrackingPiezaDTO(piezaEntity5);
        pieza5.setEstado("3");
        pieza5.setFecha("20171212");
        pieza5.setIdEstado("17");
        pieza5.setMarcaTarjeta("");
        pieza5.setNumeroTarjetaComponente("");
        pieza5.setStatusPieza("");
        pieza5.setSucursal("0019");
        pieza5.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        listaPiezasDTO.add(pieza5);
        
        TrackingTarjetasPieza piezaEntity6 = new TrackingTarjetasPieza();
        piezaEntity6.setStatusPieza("");
        piezaEntity6.setTipoCuenta("05");
        piezaEntity6.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento6 = new TrackingTarjetasEvento();
        evento6.setIdEstado("35");
        evento6.setEstado("Descripcion de prueba");
        evento6.setSucursal("019");
        piezaEntity6.setEvento(evento6);
        piezaEntity6.setFecCodEstadoActual("20171212");
        piezaEntity6.setCodEstadoActual("35");
        piezaEntity6.setSucUbic("0123");
        
        
        TrackingPiezaDTO pieza6 = new TrackingPiezaDTO(piezaEntity6);
        pieza6.setEstado("3");
        pieza6.setFecha("20171212");
        pieza6.setIdEstado("35");
        pieza6.setMarcaTarjeta("");
        pieza6.setNumeroTarjetaComponente("");
        pieza6.setStatusPieza("");
        pieza6.setSucursal("0019");
        pieza6.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        listaPiezasDTO.add(pieza6);
        
        return listaPiezasDTO;
    }

    /**
     * obtenerRespuestaSucursal
     * @return
     */
    public static Respuesta<Sucursal> obtenerRespuestaSucursal() {
        Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
        respuestaSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("La descripcion");
        sucursal.setDireccion("La direccion");
        sucursal.setIdSucursal("2");
        respuestaSucursal.setRespuesta(sucursal);
        return respuestaSucursal;
    }

    /**
     * obtenerDatosClienteOKReimpresionTest
     * @return
     */
    public static Cliente obtenerDatosClienteOKReimpresionTest() {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

        cliente.setNup("123456");    
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cliente.setTipoPersona("");
        cliente.setTipoDocumento("N");
        cliente.setDni("30385112");
        cliente.setFechaNacimiento("29091983");
        //Cuenta banelco sucursal larga
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("05");
        cuenta.setNroSucursal("0082");
        cuenta.setNroTarjetaCredito("1234123412341234");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("ABAE");
        cuenta.setNroCuentaProducto("231237123");
        listaCuentas.add(cuenta);
        //Cuenta VISA Titular
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuenta("07");
        cuenta2.setNroSucursal("0082");
        cuenta2.setCbu("0123456789012345678901");
        cuenta2.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta2.setCodigoTitularidad("TI");
        cuenta2.setNroCuentaProducto("231237123");
        cuenta2.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta2);
        //Cuenta Amex Adicional
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setTipoCuenta("42");
        cuenta3.setNroSucursal("0082");
        cuenta3.setCbu("0123456789012345678901");
        cuenta3.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta3.setCodigoTitularidad("A");
        cuenta3.setNroCuentaProducto("231237123");
        cuenta3.setCodigoAplicacion("");
        cuenta3.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta3);
        //Cuenta Amex Titular
        Cuenta cuenta4 = new Cuenta();
        cuenta4.setTipoCuenta("42");
        cuenta4.setNroSucursal("0082");
        cuenta4.setCbu("0123456789012345678901");
        cuenta4.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta4.setCodigoTitularidad("TI");
        cuenta4.setNroCuentaProducto("231237123");
        cuenta4.setCodigoAplicacion("");
        cuenta4.setNroTarjetaCredito("1234123412341234");
        listaCuentas.add(cuenta4);
        //Cuenta VISA Recargable Adicional
        Cuenta cuenta5 = new Cuenta();
        cuenta5.setTipoCuenta("77");
        cuenta5.setNroSucursal("82");
        cuenta5.setCbu("0123456789012345678901");
        cuenta5.setTipoCuentaEnum(TipoCuenta.VISA_RECARGABLE);
        cuenta5.setNroTarjetaCredito("1234123412341234");
        cuenta5.setCodigoTitularidad("A");
        cuenta5.setNroCuentaProducto("231237123");
        listaCuentas.add(cuenta5);
        //Cuenta banelco sucursal corta
        Cuenta cuenta6 = new Cuenta();
        cuenta6.setTipoCuenta("05");
        cuenta6.setNroSucursal("82");
        cuenta6.setNroTarjetaCredito("1234123412341234");
        cuenta6.setCbu("0123456789012345678901");
        cuenta6.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta6.setNroOrdenFirmante("001");
        cuenta6.setCodigoAplicacion("ABAE");
        cuenta6.setNroCuentaProducto("231237123");
        cuenta6.setCodigoTitularidad("TI");
        listaCuentas.add(cuenta6);
        cliente.setCuentas(listaCuentas);
        
        return cliente;
    }

    /**
     * obtenerRespuestaPiezasErrorFechaDTO
     * @return
     */
    public static List<TrackingPiezaDTO> obtenerRespuestaPiezasErrorFechaDTO() {
        List<TrackingPiezaDTO> listaPiezasDTO = new ArrayList<TrackingPiezaDTO>();
        TrackingTarjetasPieza piezaEntity = new TrackingTarjetasPieza();
        piezaEntity.setStatusPieza("");
        piezaEntity.setTipoCuenta("05");
        piezaEntity.setNroIdComponente("1234123412341234");
        TrackingTarjetasEvento evento = new TrackingTarjetasEvento();
        evento.setIdEstado("12");
        evento.setEstado("Estado");
        piezaEntity.setEvento(evento );
        piezaEntity.setFecCodEstadoActual("20171212");
        piezaEntity.setCodEstadoActual("C6");
        piezaEntity.setSucUbic("0123");
        
        
        TrackingPiezaDTO pieza = new TrackingPiezaDTO(piezaEntity);
        pieza.setEstado("1");
        pieza.setFecha("");
        pieza.setIdEstado("12");
        pieza.setMarcaTarjeta("");
        pieza.setNumeroTarjetaComponente("");
        pieza.setStatusPieza("");
        pieza.setSucursal("0019");
        pieza.setTipoCuentaEnum(TipoCuenta.AMEX);
        listaPiezasDTO.add(pieza);
        return listaPiezasDTO;
    }
    
    
}
