package ar.com.santanderrio.obp.servicios.billetera.web.util;

import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class BilleteraHelperTest {

    @Test
    public void validarMail_emailsInvalidos() {
        assertFalse(BilleteraHelper.validarMail(StringUtils.EMPTY));
        assertFalse(BilleteraHelper.validarMail("direcciondeemailmuylarga@santandertecnologia.com.ar"));
    }

    @Test
    public void validarMail_emailValido() {
        assertTrue(BilleteraHelper.validarMail("direccionvalida@santandertecnologia.com.ar"));
    }

    @Test
    public void validarCampos_claveInvalida() {
        BilleteraView billeteraView = new BilleteraView();
        billeteraView.setClave("invalida");
        assertFalse(BilleteraHelper.validarCampos(billeteraView));
    }

    @Test
    public void validarCampos_respuestaSeguridadInvalida() {
        BilleteraView billeteraView = new BilleteraView();
        billeteraView.setClave("Clave1234*");
        billeteraView.setRespuestaSeguridad("ä");
        assertFalse(BilleteraHelper.validarCampos(billeteraView));
    }

    @Test
    public void validarCampos_celularInvalido() {
        BilleteraView billeteraView = new BilleteraView();
        billeteraView.setClave("Clave1234*");
        billeteraView.setRespuestaSeguridad("valida");
        billeteraView.setCodigoArea("999");
        billeteraView.setTelefono("41111111");
        assertFalse(BilleteraHelper.validarCampos(billeteraView));
    }

    @Test
    public void validarCampos_camposValidos() {
        BilleteraView billeteraView = new BilleteraView();
        billeteraView.setClave("Clave1234*");
        billeteraView.setRespuestaSeguridad("valida");
        billeteraView.setCodigoArea("11");
        billeteraView.setTelefono("41111111");
        billeteraView.setEmpresaSeleccionada("empresa");
        assertTrue(BilleteraHelper.validarCampos(billeteraView));
    }
}
