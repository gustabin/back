package ar.com.santanderrio.obp.servicios.echeq.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.view.BeneficiarioView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAdhesionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoOperacionECheqView;

public interface ECheqManager {

	Respuesta<GrillaECheqOutView> obtenerECheqs(GrillaECheqInView grillaECheqInView);
	
    Respuesta<ConfirmarOperacionECheqOutView> confirmar(ConfirmarOperacionECheqInView confirmarOperacionECheqInView);

	Respuesta<ConfirmarOperacionECheqOutView> confirmarAdhesion(ConfirmarAdhesionECheqInView confirmarAdhesionInView);

	Respuesta<ConfirmarAltaEndosarECheqView> confirmarAltaEndosar(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView);

	Respuesta<ReporteView> descargarComprobante(OperacionEcheqEnum operacion);

	Respuesta<IngresoECheqOutView> ingresoModulo();

	Respuesta<IngresoOperacionECheqView> ingresoOperacion(IngresoOperacionECheqView ingresoOperacionECheqInView);

	Respuesta<BeneficiarioView> validarBeneficiario(BeneficiarioView beneficiarioInView);

}
