/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.mock;

import java.text.MessageFormat;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock.AutentificacionDTOMock;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.entity.CompaniaCelularEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.CodigoEmpresaView;

/**
 * The Class ConfirmacionRecargaViewMock.
 *
 * @author florencia.n.martinez
 */
public class ConfirmacionRecargaViewMock {

    /** The Constant MSJ_CONFIRMAR_COORDENADAS. */
    private static final String MSJ_CONFIRMAR_COORDENADAS = "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los siguientes dígitos de tu  <b>Tarjeta de coordenadas </b> terminada en  <b>{0} </b>.</p>";

    /** The Constant MSJ_CONFIRMAR_OCHO_DIGITOS. */
    private static final String MSJ_CONFIRMAR_OCHO_DIGITOS = "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los últimos 8 dígitos de tu <b>tarjeta de débito</b>.</p>";

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * token desafio.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningTokenDesafio() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        view.setDesafio(new AutentificacionDTO(TipoDesafioEnum.TOKEN,
                new TokenOperacionDTO("<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>")));
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * token desafio sync error.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningTokenDesafioSyncError() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO(TipoDesafioEnum.TOKEN,
                new TokenOperacionDTO("<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>"));
        autentificacionDTO.setReintentosAgotados(false);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.getTokenOperacion().setIngresoToken("12345678");
        view.setIsFromCalendario(false);
        view.setCbuCuenta("0720025088000006005072");
        view.setDesafio(autentificacionDTO);
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * token desafio error bloqueo.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningTokenDesafioErrorBloqueo() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO(TipoDesafioEnum.TOKEN,
                new TokenOperacionDTO("<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>"));
        autentificacionDTO.setReintentosAgotados(true);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.getTokenOperacion().setIngresoToken("12345678");
        view.setIsFromCalendario(false);
        view.setCbuCuenta("0720025088000006005072");
        view.setDesafio(autentificacionDTO);
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * tarjeta coordenadas desafio.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningTarjetaCoordenadasDesafio() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        CoordenadasOperacionDTO coordenadasDTO = new CoordenadasOperacionDTO("12345667", "1", "3", "4", "5");
        coordenadasDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
        coordenadasDTO.setMensaje(MessageFormat.format(MSJ_CONFIRMAR_COORDENADAS, coordenadasDTO.getNumero().substring(coordenadasDTO.getNumero().length() - 4)));
        coordenadasDTO.setMensajeCoordenadas(MSJ_CONFIRMAR_COORDENADAS);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setReintentosAgotados(false);
        autentificacionDTO.setValorNotificarRSA(true);
        autentificacionDTO.setCoordenadasOperacion(coordenadasDTO);
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
        view.setIsFromCalendario(false);
        view.setCbuCuenta("0720025088000006005072");
        view.setDesafio(autentificacionDTO);
        view.setMonto("$ 20,00");
        view.setMontoId("2000");
        view.setSaldoCuenta("200.000,00");
        view.setIdentificacionCliente("1188888888");
        view.setAliasCelular("paco");
        view.setNombreEmpresa(CompaniaCelularEnum.CLARO.name());
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * tarjeta coordenadas desafio con reintentos agotados.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningTarjetaCoordenadasDesafioConReintentosAgotados() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setReintentosAgotados(true);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
        view.setIsFromCalendario(false);
        view.setCbuCuenta("0720025088000006005072");
        view.setDesafio(autentificacionDTO);
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view para warning
     * ocho digitos.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewWarningOchoDigitos() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        BanelcoOperacionDTO banelcoDTO = new BanelcoOperacionDTO();
        banelcoDTO.setDigitos("5678");
        banelcoDTO.setMensaje(MessageFormat.format(MSJ_CONFIRMAR_OCHO_DIGITOS, "12345678"));
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setReintentosAgotados(false);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.BANELCO);
        autentificacionDTO.setBanelcoOperacion(banelcoDTO);
        view.setIsFromCalendario(false);
        view.setCbuCuenta("0720025088000006005072");
        view.setDesafio(autentificacionDTO);
        return view;
    }

    /**
     * Completa la informacion del objeto pago mis cuentas view input.
     *
     * @return the pago mis cuentas view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewInputSolicitud() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        view.setIsFromCalendario(false);
        view.setMonto("$ 200,00");
        view.setMontoId("200");
        view.setSaldoCuenta("200.000,00");
        view.setIdentificacionCliente("0221 154911042");
        view.setNumeroCuenta("023-123456/7");
        view.setCbuCuenta("0720025088000006005072");
        view.setCodigoEmpresa("RECL");
        view.setNombreEmpresa("RECARGA CLARO");
        view.setSaldoCuenta("32.456,78");
        return view;
    }

    /**
     * Completa info confirmacion recarga view input validacion.
     *
     * @return the confirmacion recarga view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewInputValidacion() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        view.setIsFromCalendario(false);
        view.setCodigoEmpresa("RECL");
        view.setIdentificacionCliente("1160002010");
        view.setNumeroCuenta("023-123456/7");
        view.setCbuCuenta("0720025088000006005072");
        view.setMonto("$ 200,00");
        view.setSaldoCuenta("200,00");
        view.setMontoId("200");
        view.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        return view;
    }

    /**
     * Completa info confirmacion recarga view input validacion sin coordenadas.
     *
     * @return the confirmacion recarga view
     */
    public static ConfirmacionRecargaView completarInfoConfirmacionRecargaViewInputValidacionSinCoordenadas() {
        ConfirmacionRecargaView view = new ConfirmacionRecargaView();
        view.setIsFromCalendario(false);
        view.setCodigoEmpresa("RECL");
        view.setIdentificacionCliente("1160002010");
        view.setNumeroCuenta("023-123456/7");
        view.setCbuCuenta("0720025088000006005072");
        view.setMonto("$ 200,00");
        view.setMontoId("200");
        view.setSaldoCuenta("200.000,00");
        view.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        view.getDesafio().setCoordenadasOperacion(null);
        return view;
    }

}
