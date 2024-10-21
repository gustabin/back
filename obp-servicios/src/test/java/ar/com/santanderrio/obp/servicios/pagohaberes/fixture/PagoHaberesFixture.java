package ar.com.santanderrio.obp.servicios.pagohaberes.fixture;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.ClienteDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.AccesosInversiones;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.*;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesPagoSimpleMultipleView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PagoHaberesFixture {

    public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewOK(){
        ValidacionesPagoPorCBUView validacionesPagoPorCBUView = new ValidacionesPagoPorCBUView();
        validacionesPagoPorCBUView.setBanco("SANTANDER");
        validacionesPagoPorCBUView.setCuitCuil("27-23061586-7");
        validacionesPagoPorCBUView.setTipoCuentaOrigen("CUP");
        validacionesPagoPorCBUView.setImporte("10000");
        validacionesPagoPorCBUView.setIngresoDatosManual(false);
        validacionesPagoPorCBUView.setNumeroCBUDestino("0720277588000036016518");
        validacionesPagoPorCBUView.setCuentaOrigen("277-360165/1");
        return validacionesPagoPorCBUView;
    }

     public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewIsCVU(){
         ValidacionesPagoPorCBUView validacionesPagoPorCBUView = new ValidacionesPagoPorCBUView();
         validacionesPagoPorCBUView.setBanco("SANTANDER");
         validacionesPagoPorCBUView.setCuitCuil("27-23061986-7");
         validacionesPagoPorCBUView.setTipoCuentaOrigen("CUP");
         validacionesPagoPorCBUView.setImporte("10000");
         validacionesPagoPorCBUView.setIngresoDatosManual(false);
         validacionesPagoPorCBUView.setNumeroCBUDestino("0000003100018343161417");
         validacionesPagoPorCBUView.setCuentaOrigen("277-360165/1");
         return validacionesPagoPorCBUView;
     }

     public static Cliente getClienteGold(){
         Cliente cliente = new Cliente();
         cliente.setClienteGold(true);
         cliente.setClienteSelect(false);
         cliente.setClientePlatinum(false);
         cliente.setClienteBancaPrivada(true);
         cliente.setAccesosInversiones(getAccesosInversiones());
         cliente.setNombre("Antonio");
         cliente.setApellido1("Perez");
         cliente.setApellido2("Prat");
         cliente.setContratos(getContratosTransferenciaTrue());
         cliente.setNup("30370358");
         return  cliente;
     }
    public static Cliente getClienteGold2(){
        Cliente cliente = new Cliente();
        cliente.setClienteGold(true);
        cliente.setClienteSelect(false);
        cliente.setClientePlatinum(false);
        cliente.setClienteBancaPrivada(true);
        cliente.setAccesosInversiones(getAccesosInversiones());
        cliente.setNombre("Antonio");
        cliente.setApellido1("Perez");
        cliente.setApellido2("Prat");
        cliente.setContratos(getContratosTransferenciaTrue());
        cliente.setNup("30370358");
        return  cliente;
    }


    public static HashMap<TipoContratoEnum, Boolean> getContratosTransferenciaTrue(){
         HashMap<TipoContratoEnum, Boolean> contratos = new HashMap<TipoContratoEnum, Boolean>();
         contratos.put(TipoContratoEnum.TRANSFERENCIA, true);
         return contratos;
     }

     public static AccesosInversiones getAccesosInversiones(){
        AccesosInversiones ai = new AccesosInversiones();
         List<Menu> accesos = new ArrayList<Menu>();
         accesos.add(getMenu1());
         accesos.add(getMenu2());
         accesos.add(getMenu3());
        ai.setAccesos(accesos);
        return ai;
     }

     public static Menu getMenu1(){
        Menu menu = new Menu();
        menu.setLink("Link1");
        menu.setTitulo("título1");
        return menu;
     }

     public static Menu getMenu2(){
         Menu menu = new Menu();
         menu.setLink("Link2");
         menu.setTitulo("título2");
         return menu;
     }

     public static Menu getMenu3(){
         Menu menu = new Menu();
         menu.setLink("Link3");
         menu.setTitulo("título3");
         return menu;
     }

     public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewCBU(){
         ValidacionesPagoPorCBUView datos = new ValidacionesPagoPorCBUView();
         datos.setBanco("SANTANDER");
         datos.setCuentaOrigen("CUP");
         datos.setImporte("10000");
         datos.setCuitCuil("27-23061586-7");
         datos.setNumeroCBUDestino("0720277588000036016518");
         datos.setCuentaOrigen("277-360165/1");

         return datos;
     }

    public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewCBU2(){
        ValidacionesPagoPorCBUView datos = new ValidacionesPagoPorCBUView();
        datos.setBanco("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setCuitCuil("27-23061586-7");
        datos.setNumeroCBUDestino("0720066388000040204668");
        datos.setCuentaOrigen("166-015474/5");

        return datos;
    }


    public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewCVU(){
        ValidacionesPagoPorCBUView datos = new ValidacionesPagoPorCBUView();
        datos.setBanco("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setCuitCuil("27-23061586-7");
        datos.setNumeroCBUDestino("0000006200000016061099");
        datos.setCuentaOrigen("707-025637/3");

        return datos;
    }


    public static ValidacionesPagoPorCBUView getValidacionesPagoPorCBUViewCVU2(){
        ValidacionesPagoPorCBUView datos = new ValidacionesPagoPorCBUView();
        datos.setBanco("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setCuitCuil("27-23061586-7");
        datos.setNumeroCBUDestino("0000006200000016061099");
        datos.setCuentaOrigen("707-025637/3");

        return datos;
    }

    public static DatosDestinatarioView getDatosDestinatarioViewSantanderCBU(){
        DatosDestinatarioView datos = new DatosDestinatarioView();
        datos.setBancoDestinatario("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setConcepto("Pago de haberes");
        datos.setCuilCuitDestinatario("27-23061586-7");
        datos.setNumeroCBUDestino("0720277588000036016518");
        datos.setCuentaOrigen("277-360165/1");

        return datos;
    }

    public static DatosDestinatarioView getDatosDestinatarioViewSantanderCBU2(){
        DatosDestinatarioView datos = new DatosDestinatarioView();
        datos.setBancoDestinatario("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setConcepto("Pago de haberes");
        datos.setCuilCuitDestinatario("27-23061586-7");
        datos.setNumeroCBUDestino("0720066388000040204668");
        datos.setCuentaOrigen("166-015474/5");

        return datos;
    }


    public static DatosDestinatarioView getDatosDestinatarioViewSantanderCVU(){
        DatosDestinatarioView datos = new DatosDestinatarioView();
        datos.setBancoDestinatario("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setConcepto("Pago de haberes");
        datos.setCuilCuitDestinatario("27-23061586-7");
        datos.setNumeroCBUDestino("0000006200000016061099");
        datos.setCuentaOrigen("707-025637/3");

        return datos;
    }


    public static DatosDestinatarioView getDatosDestinatarioViewSantanderCVU2(){
        DatosDestinatarioView datos = new DatosDestinatarioView();
        datos.setBancoDestinatario("SANTANDER");
        datos.setCuentaOrigen("CUP");
        datos.setImporte("10000");
        datos.setConcepto("Pago de haberes");
        datos.setCuilCuitDestinatario("27-23061586-7");
        datos.setNumeroCBUDestino("0000006200000016061099");
        datos.setCuentaOrigen("707-025637/3");

        return datos;
    }

    public static Respuesta<DatosDestinatarioView> getRespuestaDatosDestinatarioViewCBU(){
        Respuesta<DatosDestinatarioView> res = new Respuesta<DatosDestinatarioView>();
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        return res;
    }

    public static Respuesta<DatosDestinatarioView> getRespuestaDatosDestinatarioViewCBU2(){
        Respuesta<DatosDestinatarioView> res = new Respuesta<DatosDestinatarioView>();
        res.setRespuesta(getDatosDestinatarioViewSantanderCBU2());
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return res;
    }

    public static Respuesta<DatosDestinatarioView> getRespuestaDatosDestinatarioViewCVU(){
        Respuesta<DatosDestinatarioView> res = new Respuesta<DatosDestinatarioView>();
        res.setRespuesta(getDatosDestinatarioViewSantanderCVU());
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return res;
    }

    public static Respuesta<DatosDestinatarioView> getRespuestaDatosDestinatarioViewCVU2(){
        Respuesta<DatosDestinatarioView> res = new Respuesta<DatosDestinatarioView>();
        res.setRespuesta(getDatosDestinatarioViewSantanderCVU2());
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        return res;
    }

    public static Respuesta<ValidacionesPagoPorCBUView> getRespuestaValidacionesPagoPorCBUViewCBU(){
        Respuesta<ValidacionesPagoPorCBUView> res = new Respuesta<ValidacionesPagoPorCBUView>();
        res.setRespuesta(getValidacionesPagoPorCBUViewCBU());
        return res;
    }


    public static Respuesta<ValidacionesPagoPorCBUView> getRespuestaValidacionesPagoPorCBUViewCVU(){
        Respuesta<ValidacionesPagoPorCBUView> res = new Respuesta<ValidacionesPagoPorCBUView>();
        res.setRespuesta(getValidacionesPagoPorCBUViewCVU());
        return res;
    }

    public static Respuesta<PagoHaberesEntity> getRespuestaPagoHaberesEntityOK(){
        Respuesta<PagoHaberesEntity> res = new Respuesta<PagoHaberesEntity>();
        res.setRespuesta(getPagoHaberesEntity1());
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        res.setRespuestaVacia(Boolean.FALSE);
        res.setItemMensajeRespuesta(getItemMensajeRespuestaListOK());
        return res;
    }

    public static Respuesta<PagoHaberesEntity> getRespuestaPagoHaberesEntityOK2(){
        Respuesta<PagoHaberesEntity> res = new Respuesta<PagoHaberesEntity>();
        res.setRespuesta(getPagoHaberesEntity2());
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        res.setRespuestaVacia(Boolean.FALSE);
        res.setItemMensajeRespuesta(getItemMensajeRespuestaListOK());
        return res;
    }

    public static Respuesta<PagoHaberesEntity> getRespuestaPagoHaberesEntityOK3(){
        Respuesta<PagoHaberesEntity> res = new Respuesta<PagoHaberesEntity>();
        res.setRespuesta(getPagoHaberesEntity3());
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        res.setRespuestaVacia(Boolean.FALSE);
        res.setItemMensajeRespuesta(getItemMensajeRespuestaListOK());
        return res;
    }

    public static Respuesta<PagoHaberesEntity> getRespuestaPagoHaberesEntityError(){
        Respuesta<PagoHaberesEntity> res = new Respuesta<PagoHaberesEntity>();
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        res.setRespuestaVacia(Boolean.TRUE);
        res.setItemMensajeRespuesta(getItemMensajeRespuestaListError());
        return res;
    }

    public static List<ItemMensajeRespuesta> getItemMensajeRespuestaListOK() {
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(getItemMensajeRespuestaOK());
        return itemMensajeRespuestaList;
    }


    public static List<ItemMensajeRespuesta> getItemMensajeRespuestaListWarning() {
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(getItemMensajeRespuestaWarning());
        return itemMensajeRespuestaList;
    }

    private static ItemMensajeRespuesta getItemMensajeRespuestaWarning() {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje de prueba Pago de haberes Masivos");
        itemMensajeRespuesta.setDetalleTipoError("");
        itemMensajeRespuesta.setExtra("");
        itemMensajeRespuesta.setTag("");
        return itemMensajeRespuesta;
    }


    public static List<ItemMensajeRespuesta> getItemMensajeRespuestaListError() {
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(getItemMensajeRespuestaError());
        return itemMensajeRespuestaList;
    }

    public static ItemMensajeRespuesta getItemMensajeRespuestaOK() {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje de prueba Pago de haberes Masivos");
        itemMensajeRespuesta.setTipoError("");
        itemMensajeRespuesta.setDetalleTipoError("");
        itemMensajeRespuesta.setExtra("");
        itemMensajeRespuesta.setTag("");
        return itemMensajeRespuesta;
    }

    public static ItemMensajeRespuesta getItemMensajeRespuestaError() {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje de prueba de Error Pago de haberes Masivos");
        itemMensajeRespuesta.setTipoError("Error PHM");
        itemMensajeRespuesta.setDetalleTipoError("Detalle Error PHM");
        itemMensajeRespuesta.setExtra("");
        itemMensajeRespuesta.setTag("EEROR");
        return itemMensajeRespuesta;
    }

    public static PagoHaberesEntity getPagoHaberesEntity1(){
        PagoHaberesEntity pagoHaberesEntity = new PagoHaberesEntity();
        pagoHaberesEntity.setPagosInformadosList(getPagosInformadosList1());
        pagoHaberesEntity.setTotalNomina(BigDecimal.valueOf(700.05));
        pagoHaberesEntity.setPagosProgramadosList(getPagosProgramadosEntityList1());
        pagoHaberesEntity.setTotalPagosProgramados(BigDecimal.valueOf(1200.01));

        return pagoHaberesEntity;
    }

    public static PagoHaberesEntity getPagoHaberesEntity2(){
        PagoHaberesEntity pagoHaberesEntity = new PagoHaberesEntity();
        pagoHaberesEntity.setPagosInformadosList(getPagosInformadosList2());
        pagoHaberesEntity.setTotalPagosProgramados(BigDecimal.valueOf(100.35));
        pagoHaberesEntity.setTotalNomina(BigDecimal.valueOf(200.05));
        pagoHaberesEntity.setPagosProgramadosList(getPagosProgramadosEntityList2());
        return pagoHaberesEntity;
    }


    public static PagoHaberesEntity getPagoHaberesEntity3(){
        PagoHaberesEntity pagoHaberesEntity = new PagoHaberesEntity();
        pagoHaberesEntity.setPagosInformadosList(getPagosInformadosList3());
        pagoHaberesEntity.setTotalNomina(BigDecimal.valueOf(200.05));
        pagoHaberesEntity.setPagosProgramadosList(getPagosProgramadosEntityList3());
        pagoHaberesEntity.setTotalPagosProgramados(BigDecimal.valueOf(300.35));
        return pagoHaberesEntity;
    }

    public static List<PagosProgramadosEntity> getPagosProgramadosEntityList1() {
        List<PagosProgramadosEntity> pagosProgramadosEntities = new ArrayList<PagosProgramadosEntity>();
        pagosProgramadosEntities.add(getPagosProgramadosEntity1());
        pagosProgramadosEntities.add(getPagosProgramadosEntity2());
        return pagosProgramadosEntities;
    }

    public static List<PagosProgramadosEntity> getPagosProgramadosEntityList2() {
        List<PagosProgramadosEntity> pagosProgramadosEntities = new ArrayList<PagosProgramadosEntity>();
        pagosProgramadosEntities.add(getPagosProgramadosEntity2());
        return pagosProgramadosEntities;
    }

    public static List<PagosProgramadosEntity> getPagosProgramadosEntityList3() {
        List<PagosProgramadosEntity> pagosProgramadosEntities = new ArrayList<PagosProgramadosEntity>();
        pagosProgramadosEntities.add(getPagosProgramadosEntity1());
        pagosProgramadosEntities.add(getPagosProgramadosEntity2());
        pagosProgramadosEntities.add(getPagosProgramadosEntity3());
        return pagosProgramadosEntities;
    }

    public static PagosProgramadosEntity getPagosProgramadosEntity1() {
        PagosProgramadosEntity pagosProgramadosEntity = new PagosProgramadosEntity();
        pagosProgramadosEntity.setCuitCuil("30-56026344-5");
        pagosProgramadosEntity.setImporte(BigDecimal.valueOf(700.01));
        pagosProgramadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosProgramadosEntity.setFechaNac("12/01/1985");
        pagosProgramadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosProgramadosEntity.setDescripcionConcepto("Pago de haberes Programado 1");
        pagosProgramadosEntity.setFechaBaseRecurrencia("20220707");
        pagosProgramadosEntity.setEmpleado("Lucas Hernandez");
        pagosProgramadosEntity.setSucCtaCredito("05");
        pagosProgramadosEntity.setMaxRecurrencia("5");
        pagosProgramadosEntity.setTipoCtaCredito("1");
        pagosProgramadosEntity.setNroCtaCredito("3662377");
        pagosProgramadosEntity.setSucCtaDebito("01");
        pagosProgramadosEntity.setNroCtaDebito("3644500");
        pagosProgramadosEntity.setTipoCtaDebito("1");
        pagosProgramadosEntity.setFrecRecurrencia("2");
        pagosProgramadosEntity.setTipoRecurrencia("02");
        pagosProgramadosEntity.setTienePagoProgramado(Boolean.TRUE);
        return pagosProgramadosEntity;
    }

    public static PagosProgramadosEntity getPagosProgramadosEntity2() {
        PagosProgramadosEntity pagosProgramadosEntity = new PagosProgramadosEntity();
        pagosProgramadosEntity.setCuitCuil("30-56026344-5");
        pagosProgramadosEntity.setImporte(BigDecimal.valueOf(500.00));
        pagosProgramadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosProgramadosEntity.setFechaNac("04/11/1970");
        pagosProgramadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosProgramadosEntity.setDescripcionConcepto("Pago de haberes Programado 2");
        pagosProgramadosEntity.setFechaBaseRecurrencia("20220707");
        pagosProgramadosEntity.setEmpleado("María Cortazar");
        pagosProgramadosEntity.setSucCtaCredito("07");
        pagosProgramadosEntity.setMaxRecurrencia("6");
        pagosProgramadosEntity.setTipoCtaCredito("1");
        pagosProgramadosEntity.setNroCtaCredito("3662377");
        pagosProgramadosEntity.setSucCtaDebito("01");
        pagosProgramadosEntity.setNroCtaDebito("3644500");
        pagosProgramadosEntity.setTipoCtaDebito("1");
        pagosProgramadosEntity.setFrecRecurrencia("2");
        pagosProgramadosEntity.setTipoRecurrencia("02");
        pagosProgramadosEntity.setTienePagoProgramado(Boolean.FALSE);
        return pagosProgramadosEntity;
    }

    public static PagosProgramadosEntity getPagosProgramadosEntity3() {
        PagosProgramadosEntity pagosProgramadosEntity = new PagosProgramadosEntity();
        pagosProgramadosEntity.setCuitCuil("30-56026346-5");
        pagosProgramadosEntity.setImporte(BigDecimal.valueOf(500.00));
        pagosProgramadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosProgramadosEntity.setFechaNac("04/11/1970");
        pagosProgramadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosProgramadosEntity.setDescripcionConcepto("Pago de haberes Programado 3");
        pagosProgramadosEntity.setFechaBaseRecurrencia("20220707");
        pagosProgramadosEntity.setEmpleado("Oriana Martinez");
        pagosProgramadosEntity.setSucCtaCredito("07");
        pagosProgramadosEntity.setMaxRecurrencia("6");
        pagosProgramadosEntity.setTipoCtaCredito("1");
        pagosProgramadosEntity.setNroCtaCredito("3662377");
        pagosProgramadosEntity.setSucCtaDebito("01");
        pagosProgramadosEntity.setNroCtaDebito("3644501");
        pagosProgramadosEntity.setTipoCtaDebito("1");
        pagosProgramadosEntity.setFrecRecurrencia("2");
        pagosProgramadosEntity.setTipoRecurrencia("02");
        pagosProgramadosEntity.setTienePagoProgramado(Boolean.TRUE);
        return pagosProgramadosEntity;
    }

    public static List<PagosInformadosEntity> getPagosInformadosList1() {
        List<PagosInformadosEntity> pagosInformadosEntityList = new ArrayList<PagosInformadosEntity>();
        pagosInformadosEntityList.add(getPagosInformadosEntity1());
        pagosInformadosEntityList.add(getPagosInformadosEntity2());
        return pagosInformadosEntityList;
    }

    public static List<PagosInformadosEntity> getPagosInformadosList2() {
        List<PagosInformadosEntity> pagosInformadosEntityList = new ArrayList<PagosInformadosEntity>();
        pagosInformadosEntityList.add(getPagosInformadosEntity2());
        return pagosInformadosEntityList;
    }

    public static List<PagosInformadosEntity> getPagosInformadosList3() {
        List<PagosInformadosEntity> pagosInformadosEntityList = new ArrayList<PagosInformadosEntity>();
        pagosInformadosEntityList.add(getPagosInformadosEntity1());
        pagosInformadosEntityList.add(getPagosInformadosEntity2());
        pagosInformadosEntityList.add(getPagosInformadosEntity3());
        return pagosInformadosEntityList;
    }

    public static PagosInformadosEntity getPagosInformadosEntity1() {
        PagosInformadosEntity pagosInformadosEntity = new PagosInformadosEntity();
        pagosInformadosEntity.setCuitCuil("30-56026344-6");
        pagosInformadosEntity.setImporte(BigDecimal.valueOf(200.05));
        pagosInformadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosInformadosEntity.setFechaNac("12/01/1985");
        pagosInformadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosInformadosEntity.setDescripcionConcepto("Pago de haberes 1");
        pagosInformadosEntity.setFechaBaseRecurrencia("20220707");
        pagosInformadosEntity.setEmpleado("Lucas Hernandez");
        pagosInformadosEntity.setSucCtaCredito("05");
        pagosInformadosEntity.setMaxRecurrencia("5");
        pagosInformadosEntity.setTipoCtaCredito("1");
        pagosInformadosEntity.setNroCtaCredito("3662377");
        pagosInformadosEntity.setSucCtaDebito("01");
        pagosInformadosEntity.setNroCtaDebito("3644500");
        pagosInformadosEntity.setTipoCtaDebito("1");
        pagosInformadosEntity.setTienePagoProgramado(Boolean.FALSE);
        return pagosInformadosEntity; 
    }

    public static PagosInformadosEntity getPagosInformadosEntity2() {
        PagosInformadosEntity pagosInformadosEntity = new PagosInformadosEntity();
        pagosInformadosEntity.setCuitCuil("30-56026355-7");
        pagosInformadosEntity.setImporte(BigDecimal.valueOf(500.00));
        pagosInformadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosInformadosEntity.setFechaNac("04/11/1970");
        pagosInformadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosInformadosEntity.setDescripcionConcepto("Pago de haberes 2");
        pagosInformadosEntity.setFechaBaseRecurrencia("20220707");
        pagosInformadosEntity.setEmpleado("María Cortazar");
        pagosInformadosEntity.setSucCtaCredito("07");
        pagosInformadosEntity.setMaxRecurrencia("6");
        pagosInformadosEntity.setTipoCtaCredito("1");
        pagosInformadosEntity.setNroCtaCredito("3642379");
        pagosInformadosEntity.setSucCtaDebito("01");
        pagosInformadosEntity.setNroCtaDebito("3644500");
        pagosInformadosEntity.setTipoCtaDebito("1");
        pagosInformadosEntity.setTienePagoProgramado(Boolean.FALSE);
        return pagosInformadosEntity;
    }

    public static PagosInformadosEntity getPagosInformadosEntity3() {
        PagosInformadosEntity pagosInformadosEntity = new PagosInformadosEntity();
        pagosInformadosEntity.setCuitCuil("30-56026355-7");
        pagosInformadosEntity.setImporte(BigDecimal.valueOf(800.39));
        pagosInformadosEntity.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagosInformadosEntity.setFechaNac("02/02/1966");
        pagosInformadosEntity.setNroRecurrencia(String.valueOf(2));
        pagosInformadosEntity.setDescripcionConcepto("Pago de haberes 3");
        pagosInformadosEntity.setFechaBaseRecurrencia("20220707");
        pagosInformadosEntity.setEmpleado("Julian Aguada");
        pagosInformadosEntity.setSucCtaCredito("07");
        pagosInformadosEntity.setMaxRecurrencia("6");
        pagosInformadosEntity.setTipoCtaCredito("1");
        pagosInformadosEntity.setNroCtaCredito("3642378");
        pagosInformadosEntity.setSucCtaDebito("01");
        pagosInformadosEntity.setNroCtaDebito("3644500");
        pagosInformadosEntity.setTipoCtaDebito("1");
        pagosInformadosEntity.setTienePagoProgramado(Boolean.FALSE);
        return pagosInformadosEntity;
    }

    public static AbstractCuenta getAbstractCuenta(){
        return getCuentaCerrada();
    }

    public static CuentaCerrada getCuentaCerrada(){
        CuentaCerrada cuentaCerrada = new CuentaCerrada();
        cuentaCerrada.setCalidadDeParticipacion("calidad de participacion");
        cuentaCerrada.setEstadoRelacion("Es");
        cuentaCerrada.setTipoCuenta("visa");
        cuentaCerrada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuentaCerrada.setNroCuentaProducto(String.valueOf(203));
        cuentaCerrada.setFechaAltaContrato("10/10/2021");
        cuentaCerrada.setNroSucursalContinuadora("203");
        return cuentaCerrada;
    }

    public static PagoInformadoView getPagosInformadosView1() {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setId("1");
        pagoInformadoView.setCuil("22-42644834-9");
        //falla con decimales
        pagoInformadoView.setImporte("0.01");
        pagoInformadoView.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagoInformadoView.setFechaNac("02/02/1966");
        pagoInformadoView.setNroRecurrencia(String.valueOf(2));
        pagoInformadoView.setConcepto("PPHM");
        pagoInformadoView.setFechaBaseRecurrencia("20220707");
        pagoInformadoView.setAliasCuentaOrigen("Alvarez.Hernan.Diego");
        pagoInformadoView.setMaxRecurrencia("6");
        pagoInformadoView.setTienePagoProgramado(Boolean.FALSE);
        pagoInformadoView.setSaldoCuentaOrigen("563000.96");
        pagoInformadoView.setChallenge(Boolean.TRUE);
        pagoInformadoView.setCantDiasUltimoCambioClave(1);
        pagoInformadoView.setDocumento("42644834");
        pagoInformadoView.setCantDiasUltimoCambioMail(1);
        pagoInformadoView.setFechaPago("15/04/2024");
        pagoInformadoView.setPagoProgramado(false);
        pagoInformadoView.setPif(true);
        return pagoInformadoView;
    }


    public static PagoInformadoView getPagosInformadosView2() {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setId("3");
        pagoInformadoView.setCuil("20-49644834-9");
        //falla con decimales
        pagoInformadoView.setImporte("0.01");
        pagoInformadoView.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagoInformadoView.setFechaNac("20010223");
        pagoInformadoView.setNroRecurrencia(String.valueOf(2));
        pagoInformadoView.setConcepto("PPHM");
        pagoInformadoView.setFechaBaseRecurrencia("20220707");
        pagoInformadoView.setAliasCuentaOrigen("Mora.Luis");
        pagoInformadoView.setMaxRecurrencia("6");
        pagoInformadoView.setTienePagoProgramado(Boolean.FALSE);
        pagoInformadoView.setSaldoCuentaOrigen("563000.96");
        pagoInformadoView.setChallenge(Boolean.TRUE);
        pagoInformadoView.setCantDiasUltimoCambioClave(1);
        pagoInformadoView.setDocumento("49644834");
        pagoInformadoView.setCantDiasUltimoCambioMail(1);
        return pagoInformadoView;
    }


    public static PagoInformadoView getPagosInformadosView3() {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setCuil("30-56026355-7");
        pagoInformadoView.setId("3");
        //falla con decimales
        pagoInformadoView.setImporte("0.01");
        pagoInformadoView.setDivisa(DivisaEnum.PESO.getSimbolo());
        pagoInformadoView.setFechaNac("20010223");
        pagoInformadoView.setNroRecurrencia(String.valueOf(2));
        pagoInformadoView.setConcepto("PPHM");
        pagoInformadoView.setFechaBaseRecurrencia("20220707");
        pagoInformadoView.setAliasCuentaOrigen("Albinoni.Luisa");
        pagoInformadoView.setMaxRecurrencia("6");
        pagoInformadoView.setTienePagoProgramado(Boolean.FALSE);
        pagoInformadoView.setSaldoCuentaOrigen("563000.96");
        pagoInformadoView.setChallenge(Boolean.TRUE);
        pagoInformadoView.setCantDiasUltimoCambioClave(1);
        pagoInformadoView.setDocumento("15644834");
        pagoInformadoView.setCantDiasUltimoCambioMail(1);
        return pagoInformadoView;
    }

    public static ComprobantePagoHaberesPagoSimpleMultipleEntity getComprobantePagoHaberesPagoSimpleMultipleEntity(){
        ComprobantePagoHaberesPagoSimpleMultipleEntity comprobante = new ComprobantePagoHaberesPagoSimpleMultipleEntity();
        comprobante.setPagos(getDatosEmpleadoPagoHaberesSimpleMultipleEntity());
        comprobante.setPagoHaberesPagoSimpleMultipleView(getPagoHaberesPagoSimpleMultipleViewModoEjecucionTrue());
        return  comprobante;
    }

    public static ComprobantePagoHaberesPagoSimpleMultipleEntity getComprobantePagoHaberesPagoSimpleMultipleEntityDesafioNotNull(){
        ComprobantePagoHaberesPagoSimpleMultipleEntity comprobante = new ComprobantePagoHaberesPagoSimpleMultipleEntity();
        comprobante.setPagos(getDatosEmpleadoPagoHaberesSimpleMultipleEntity());
        comprobante.setPagoHaberesPagoSimpleMultipleView(getPagoHaberesPagoSimpleMultipleViewModoEjecucionTrue());
        comprobante.setDesafio(getAutentificacionDTOWithToken());

        return  comprobante;
    }

    public static ComprobantePagoHaberesPagoSimpleMultipleEntity getComprobantePagoHaberesPagoSimpleMultipleEntityMETrue(){
        ComprobantePagoHaberesPagoSimpleMultipleEntity comprobante = new ComprobantePagoHaberesPagoSimpleMultipleEntity();
        comprobante.setPagos(getDatosEmpleadoPagoHaberesSimpleMultipleEntity());
        comprobante.setPagoHaberesPagoSimpleMultipleView(getPagoHaberesPagoSimpleMultipleViewModoEjecucionTruePSTrue());
        return  comprobante;
    }

    public static ComprobantePagoHaberesPagoSimpleMultipleEntity getComprobantePagoHaberesPagoSimpleMultipleEntityModoEjecucionFalse(){
        ComprobantePagoHaberesPagoSimpleMultipleEntity comprobante = new ComprobantePagoHaberesPagoSimpleMultipleEntity();
        comprobante.setPagos(getDatosEmpleadoPagoHaberesSimpleMultipleEntity());
        comprobante.setPagoHaberesPagoSimpleMultipleView(getPagoHaberesPagoSimpleMultipleViewModoEjecucionFalse());
        return  comprobante;
    }


    public static PagoHaberesPagoSimpleMultipleView getPagoHaberesPagoSimpleMultipleViewModoEjecucionTrue() {
        PagoHaberesPagoSimpleMultipleView pagoHaberes = new PagoHaberesPagoSimpleMultipleView();
        pagoHaberes.setIsPagoSimple(Boolean.FALSE);
        pagoHaberes.setPagoHaberesEmpleadosView(getPagosInformadosViewList());
        pagoHaberes.setFecha("08/08/2022");
        pagoHaberes.setPrimeraVez(Boolean.FALSE);
        pagoHaberes.setModoEjecucion(Boolean.TRUE);
        return pagoHaberes;
    }
    public static PagoHaberesPagoSimpleMultipleView getPagoHaberesPagoSimpleMultipleViewModoEjecucionTruePSTrue() {
        PagoHaberesPagoSimpleMultipleView pagoHaberes = new PagoHaberesPagoSimpleMultipleView();
        pagoHaberes.setIsPagoSimple(Boolean.TRUE);
        pagoHaberes.setPagoHaberesEmpleadosView(getPagosInformadosViewList());
        pagoHaberes.setFecha("08/08/2022");
        pagoHaberes.setPrimeraVez(Boolean.FALSE);
        pagoHaberes.setModoEjecucion(Boolean.TRUE);
        return pagoHaberes;
    }


    private static PagoHaberesPagoSimpleMultipleView getPagoHaberesPagoSimpleMultipleViewModoEjecucionFalse() {
        PagoHaberesPagoSimpleMultipleView pagoHaberes = new PagoHaberesPagoSimpleMultipleView();
        pagoHaberes.setIsPagoSimple(Boolean.TRUE);
        pagoHaberes.setPagoHaberesEmpleadosView(getPagosInformadosViewList());
        pagoHaberes.setFecha("02/09/2022");
        pagoHaberes.setPrimeraVez(Boolean.FALSE);
        pagoHaberes.setModoEjecucion(Boolean.FALSE);
        return pagoHaberes;
    }

    public static List<PagoInformadoView> getPagosInformadosViewList() {
        List<PagoInformadoView> pagos = new ArrayList<PagoInformadoView>();
        pagos.add(getPagosInformadosView1());
        pagos.add(getPagosInformadosView2());
        pagos.add(getPagosInformadosView3());
        return pagos;
    }

    public static List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> getDatosEmpleadoPagoHaberesSimpleMultipleEntity() {
        List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> datosEmpleados = new ArrayList<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        datosEmpleados.add(getDatosEmpleadoPagoHaberesSimpleMultipleEntity1());
        datosEmpleados.add(getDatosEmpleadoPagoHaberesSimpleMultipleEntity2());
        datosEmpleados.add(getDatosEmpleadoPagoHaberesSimpleMultipleEntity3());
        return datosEmpleados;
    }

    public static DatosEmpleadoPagoHaberesSimpleMultipleEntity getDatosEmpleadoPagoHaberesSimpleMultipleEntity1() {
        DatosEmpleadoPagoHaberesSimpleMultipleEntity datoEmpleado = new DatosEmpleadoPagoHaberesSimpleMultipleEntity();
        datoEmpleado.setId("1");
        datoEmpleado.setDescripcionEmpleado("Alvarez  Hernan Diego");
        datoEmpleado.setConcepto("Pago de haberes de julio");
        datoEmpleado.setCuentaOrigen("175-371572/9");
        datoEmpleado.setConcepto("PPHM");
        datoEmpleado.setComprobante(null);
        datoEmpleado.setImporte("0.01");
        datoEmpleado.setCuentaDestino("175-376912/9");
        datoEmpleado.setCuil("22-42644834-9");
        datoEmpleado.setFecha("19900830");
        datoEmpleado.setId("1");
        datoEmpleado.setMensaje("");
        return datoEmpleado;
    }

    public static DatosEmpleadoPagoHaberesSimpleMultipleEntity getDatosEmpleadoPagoHaberesSimpleMultipleEntity2() {
        DatosEmpleadoPagoHaberesSimpleMultipleEntity datoEmpleado = new DatosEmpleadoPagoHaberesSimpleMultipleEntity();
        datoEmpleado.setId("2");
        datoEmpleado.setDescripcionEmpleado("Mora Luis");
        datoEmpleado.setConcepto("Pago de haberes de julio");
        datoEmpleado.setCuentaOrigen("175-371545/9");
        datoEmpleado.setConcepto("PPHM");
        datoEmpleado.setComprobante(null);
        datoEmpleado.setImporte("0.01");
        datoEmpleado.setCuentaDestino("175-376912/9");
        datoEmpleado.setCuil("20-49644834-9");
        datoEmpleado.setFecha("20010223");
        datoEmpleado.setId("1");
        datoEmpleado.setMensaje("");
        return datoEmpleado;
    }

    public static DatosEmpleadoPagoHaberesSimpleMultipleEntity getDatosEmpleadoPagoHaberesSimpleMultipleEntity3() {
        DatosEmpleadoPagoHaberesSimpleMultipleEntity datoEmpleado = new DatosEmpleadoPagoHaberesSimpleMultipleEntity();
        datoEmpleado.setId("3");
        datoEmpleado.setDescripcionEmpleado("Albinoni Luisa");
        datoEmpleado.setConcepto("Pago de haberes de julio");
        datoEmpleado.setCuentaOrigen("175-375672/9");
        datoEmpleado.setConcepto("PPHM");
        datoEmpleado.setComprobante(null);
        datoEmpleado.setImporte("0.01");
        datoEmpleado.setCuentaDestino("175-376912/9");
        datoEmpleado.setCuil("21-15644834-9");
        datoEmpleado.setFecha("19480630");
        datoEmpleado.setId("1");
        datoEmpleado.setMensaje("");
        return datoEmpleado;
    }

    public static Respuesta<List<BigDecimal>> getBigDecimalRespuestaListOK() {
        Respuesta<List<BigDecimal>> respuesta = new Respuesta<List<BigDecimal>>();
        respuesta.setRespuesta(getBigDecimalList());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    public static Respuesta<List<BigDecimal>> getBigDecimalRespuestaListWarning() {
        Respuesta<List<BigDecimal>> respuesta = new Respuesta<List<BigDecimal>>();
        respuesta.setRespuesta(getBigDecimalList());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        return respuesta;
    }

    public static Respuesta<List<BigDecimal>> getBigDecimalRespuestaListError() {
        Respuesta<List<BigDecimal>> respuesta = new Respuesta<List<BigDecimal>>();
        respuesta.setRespuesta(getBigDecimalList());
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return respuesta;
    }

    private static List<BigDecimal> getBigDecimalList() {
        List<BigDecimal> list = new ArrayList<BigDecimal>();
        list.add(BigDecimal.valueOf(1.0));
        list.add(BigDecimal.valueOf(0.2));
        return list;
    }

    public static Respuesta<AutentificacionDTO> getRespuestaAutentificacionDTOOK() {
        Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();
        respuesta.setRespuesta(getAutentificacionDTO());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    public static Respuesta<AutentificacionDTO> getRespuestaAutentificacionDTOWarning() {
        Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();
        respuesta.setRespuesta(getAutentificacionDTO());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setItemMensajeRespuesta(getItemMensajeRespuestaListWarning());
        return respuesta;
    }

    public static Respuesta<AutentificacionDTO> getRespuestaAutentificacionDTOError() {
        Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();
        respuesta.setRespuesta(getAutentificacionDTO());
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setItemMensajeRespuesta(getItemMensajeRespuestaListError());
        return respuesta;
    }

    public static AutentificacionDTO getAutentificacionDTO() {
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setClienteDTO(getClienteDto());
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
        autentificacionDTO.setTokenOperacion(getTokenOperacionNotNull());
        return autentificacionDTO;
    }

    public static AutentificacionDTO getAutentificacionDTOWithToken() {
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setClienteDTO(getClienteDto());
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
        autentificacionDTO.setTokenOperacion(getTokenOperacionNotNull());
        return autentificacionDTO;
    }

    public static TokenOperacionDTO getTokenOperacionNotNull() {
        TokenOperacionDTO tokenOperacionDTO = new TokenOperacionDTO();
        tokenOperacionDTO.setIngresoToken("928344");
        tokenOperacionDTO.setMensaje(" <p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>");
        tokenOperacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
        return tokenOperacionDTO;
    }

    public static ClienteDTO getClienteDto() {
        ClienteDTO clienteDTO = new ClienteDTO();
        return clienteDTO;
    }

    public static Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity1Error() {
        Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> respuesta = new Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        respuesta.setRespuesta(getDatosEmpleadoPagoHaberesSimpleMultipleEntity1());
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return respuesta;
    }

    public static Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity2() {
        Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> respuesta = new Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        respuesta.setRespuesta(getDatosEmpleadoPagoHaberesSimpleMultipleEntity2());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }
    public static Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity3() {
        Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> respuesta = new Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        respuesta.setRespuesta(getDatosEmpleadoPagoHaberesSimpleMultipleEntity3());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        return respuesta;
    }

    public static ContadorIntentos getContadorDeIntentos() {
        ContadorIntentos cont = new ContadorIntentos();
        cont.setContador(2);
        cont.setEstado(Boolean.TRUE);
        return cont;
    }

    public static ContadorIntentos getContadorNoPermiteReintentos() {
        ContadorIntentos cont = new ContadorIntentos();
        cont.setContador(5);
        cont.setEstado(Boolean.FALSE);
        return cont;
    }

    public static Respuesta<ActionCode> getRespuestaActionCode() {
        Respuesta<ActionCode> respuesta = new Respuesta<ActionCode>();
        respuesta.setRespuesta(getActionCode());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setId(23L);
        return respuesta;
    }

    public static Respuesta<ActionCode> getRespuestaActionCodeAllow() {
        Respuesta<ActionCode> respuesta = new Respuesta<ActionCode>();
        respuesta.setRespuesta(getActionCodeAllow());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        return respuesta;
    }

    public static ActionCode getActionCode() {
        return  ActionCode.CHALLENGE;
    }

    public static ActionCode getActionCodeAllow() {
        return  ActionCode.ALLOW;
    }


    public static Desafio<AutentificacionDTO> getDesafioEnCurso() {
        Desafio<AutentificacionDTO> desafio = new Desafio<AutentificacionDTO>() {
            @Override
            public Respuesta<AutentificacionDTO> solicitar() {
                return getRespuestaAutentificacionDTOWarning();
            }

            @Override
            public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO auntentificacionDTO) {
                return getRespuestaAutentificacionDTOWarning();
            }

            @Override
            public int compareTo(Desafio<AutentificacionDTO> o) {
                return 0;
            }
        };
        return desafio;
    }

    public static Desafio<AutentificacionDTO> getDesafioEnCursoNull() {
     return null;
    }


    public static Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntity() {
        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        respuesta.setRespuesta(getComprobantePagoHaberesPagoSimpleMultipleEntity());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        return respuesta;
    }


    public static Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntityOK() {
        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        respuesta.setRespuesta(getComprobantePagoHaberesPagoSimpleMultipleEntity());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    public static Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntityOKMEFalse() {
        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        respuesta.setRespuesta(getComprobantePagoHaberesPagoSimpleMultipleEntityModoEjecucionFalse());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }
    public static Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntityWarningMEFalse() {
        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        respuesta.setRespuesta(getComprobantePagoHaberesPagoSimpleMultipleEntityModoEjecucionFalse());
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        return respuesta;
    }


    public static Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntityErrorMEFalse() {
        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        respuesta.setRespuesta(getComprobantePagoHaberesPagoSimpleMultipleEntityModoEjecucionFalse());
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setItemMensajeRespuesta(Arrays.asList(new ItemMensajeRespuesta()));
        return respuesta;
    }
    public static Respuesta<RsaNotifyResponseData> getRespuestaNotifyResponseDataOK(){
        Respuesta<RsaNotifyResponseData> respuesta = new Respuesta<RsaNotifyResponseData>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(getNotifyResponseData());
        return respuesta;
    }

    public static RsaNotifyResponseData getNotifyResponseData() {
        RsaNotifyResponseData response = new RsaNotifyResponseData();
        response.setRsaGenericResponseData(getRSAGenericResponseData());
        return response;
    }

    public static RsaGenericResponseData getRSAGenericResponseData() {
        RsaGenericResponseData response = new RsaGenericResponseData();
        response.setTransactionId("3602:3f9e234a381:0b873ff7-_TRX");
        return response;
    }

    public static Mensaje getMensaje() {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("este es un mensaje de prueba");
        mensaje.setTag("Tag");
        mensaje.setCodigo("12346-COD");
        mensaje.setGenerico(Boolean.TRUE);
        return mensaje;
    }

    public static Respuesta<Object> getEstadoRespuestaError() {
        Respuesta res = new Respuesta();
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return res;
    }

    public static RsaGenericRequestData getRsaGenericRequestDataNotNull() {
        RsaGenericRequestData requestData = new RsaGenericRequestData();
        requestData.setDevicePrint("version=3.0.0.0_5&amp;pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/107.0.0.0 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36|Win32&amp;pm_fpsc=24|1920|1080|1040&amp;pm_fpsw=&amp;pm_fptz=-3&amp;pm_fpln=lang=es-ES|syslang=|userlang=&amp;pm_fpjv=0&amp;pm_fpco=1&amp;pm_fpasw=internal-pdf-viewer|internal-pdf-viewer|internal-pdf-viewer|internal-pdf-viewer|internal-pdf-viewer&amp;pm_fpan=Netscape&amp;pm_fpacn=Mozilla&amp;pm_fpol=true&amp;pm_fposp=&amp;pm_fpup=&amp;pm_fpsaw=1920&amp;pm_fpspd=24&amp;pm_fpsbd=&amp;pm_fpsdx=&amp;pm_fpsdy=&amp;pm_fpslx=&amp;pm_fpsly=&amp;pm_fpsfse=&amp;pm_fpsui=&amp;pm_os=Windows&amp;pm_brmjv=107&amp;pm_br=Chrome&amp;pm_inpt=&amp;pm_expt=");
        requestData.setDeviceTokenCookie("PMV6nEz71DV2GtfBF2z1gL1Bzh%2BXl3ewh4Qlfz5z3%2B1xc42FEjfS324ycP42240KHLnPrSxRTGbKIHdLrmNvF%2F97iMVA%3D%3D");
        requestData.setHttpAccept("application/json, text/plain, */*");
        requestData.setTransactionId(null);
        return requestData;
    }

    public static RsaGenericResponseData getRsaGenericResponseDataNotNull() {
        RsaGenericResponseData responseData = new RsaGenericResponseData();
        responseData.setTransactionId("8c93-:235eceaa481:4e0ee6d5_TRX");
        responseData.setDeviceTokenCookie("PMV6nExspzk6TaTfq%2BuLQvFtorJryOgfc9AEba5XFZT8Wv4Pmx6cz4LXQhmyIFLEq7%2Fvc%2FmHCJNvmbUzgLQ35KKsg4vA%3D%3D");
        responseData.setShStatusCode("SUCCESS");
        return responseData;
    }
}
