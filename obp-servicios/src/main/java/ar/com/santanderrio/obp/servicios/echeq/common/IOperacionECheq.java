package ar.com.santanderrio.obp.servicios.echeq.common;

import java.text.ParseException;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.IOperacionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public interface IOperacionECheq {

	//Operation Execution Related Methods
	OperacionEcheqEnum getOperacion();

	Boolean getOperacionDisponible();

	void setOperationContext(Cheque echeq, Cliente cliente, ConfirmarOperacionInDTO confirmarOperacionInDTO);

	void execute() throws DAOException, ParseException;

	OperationEcheqResponse getOperationResult();

	IOperacionECheqInEntity<?> getInEntity() throws ParseException;

	void cargarCuentasHabilitadas(List<Cuenta> cuentasHabilitadas);

	Boolean tieneComprobante();

	ComprobanteECheqDTO generarComprobante(Cheque echeq, ConfirmarOperacionInDTO confirmarOperacionInDTO,
		   String numeroComprobante, String legales);

	//Statistics Related Methods
	Estadistica getEstadisticaOperacion();

	String getCodigoEstadistica();

	//Info Methods
	String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO);

	String getMensajeError(String errorCodeItax);

	String getMensajeErrorTimeOut(ConfirmarOperacionInDTO confirmarOperacionInDTO);

}
