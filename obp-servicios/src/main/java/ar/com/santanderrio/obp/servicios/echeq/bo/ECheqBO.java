package ar.com.santanderrio.obp.servicios.echeq.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.echeq.common.IOperacionECheq;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConsultaImporteTotalesInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.TotalesECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Interface ECheqBO.
 */
public interface ECheqBO {

	Respuesta<GrillaECheqOutDTO> obtenerECheqs(GrillaECheqInDTO grillaECheqInDTO);

	Respuesta<TotalesECheqOutDTO> obtenerTotales(ConsultaImporteTotalesInDTO consultaImporteTotalesInDTO);
	
	Respuesta<Void> obtenerCuit();

	Respuesta<BeneficiarioDTO> validarBeneficiario(BeneficiarioDTO beneficiarioInDTO);

	Respuesta<Void> validarEstado(String id, String tipoGrilla, String jSessionId, OperacionEcheqEnum operacion);

    Respuesta<Cheque> obtenerDetalle(String numeroCheque, String numeroCUILCUIT, String jSessionId);

    Respuesta<ConfirmarOperacionOutDTO> confirmarOperacion(IOperacionECheq operacion,
		   ConfirmarOperacionInDTO confirmarOperacionInDTO, Cliente cliente);

	Respuesta<Reporte> descargarComprobante(ComprobanteECheqDTO comprobanteDTO);
	
	Respuesta<ResponseFull> obtenerListadoEntidadesHabilitadas(String jsessionid);

}
