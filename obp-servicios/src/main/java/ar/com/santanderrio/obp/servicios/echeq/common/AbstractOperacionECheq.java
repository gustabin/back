package ar.com.santanderrio.obp.servicios.echeq.common;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;

public abstract class AbstractOperacionECheq implements IOperacionECheq {

    protected Cheque cheque;
    protected Cliente cliente;
    protected ConfirmarOperacionInDTO operationDetails;
                                                        //Not Executed Operation
    protected OperationEcheqResponse operationResult = OperationEcheqResponse.builder()
            .numeroComprobante("")
            .status(Boolean.FALSE).build();

    public OperationEcheqResponse getOperationResult() {
        return operationResult;
    }

    public void setOperationContext(Cheque echeq, Cliente cliente, ConfirmarOperacionInDTO operationDetails) {
        setCheque(echeq);
        setCliente(cliente);
        setOperationDetails(operationDetails);
    }

    public Boolean getOperacionDisponible() {
        return Boolean.TRUE;
    }

    //Checks para Comprobante de operacion
    public Boolean tieneComprobante() {
        return Boolean.FALSE;
    }

    public ComprobanteECheqDTO generarComprobante(Cheque echeq, ConfirmarOperacionInDTO confirmarOperacionInDTO,
            String numeroComprobante, String legales) {
        return null;
    }

    //Utils Estadisticas
    public abstract String getCodigoEstadistica();

    public Estadistica getEstadisticaOperacion() {
        Estadistica estadisticaModel = new Estadistica();
        estadisticaModel.setMoneda("ARS");
        estadisticaModel.setImporte(operationDetails.getImporte());
        estadisticaModel.setCodigoTransaccion(getCodigoEstadistica());
        estadisticaModel.setCodigoError(operationResult.getStatus() ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        estadisticaModel.setNroComprobante(operationResult.getNumeroComprobante());
        return estadisticaModel;
    }

    public String getMensajeErrorTimeOut(ConfirmarOperacionInDTO confirmarOperacionInDTO) {
        if (confirmarOperacionInDTO.getIngresoDesdeEndosados()) {
            return CodigoMensajeConstantes.ECHEQ_ERROR_CONFIRMAR_OPERACION_ENDOSADOS;
        }
        if (confirmarOperacionInDTO.getIngresoDesdeEmitidos()) {
            return CodigoMensajeConstantes.ECHEQ_ERROR_CONFIRMAR_OPERACION_EMITIDOS;
        }
        return CodigoMensajeConstantes.ECHEQ_ERROR_CONFIRMAR_OPERACION_RECIBIDOS;
    }

    //Utils Methods
    public void cargarCuentasHabilitadas(List<Cuenta> cuentasHabilitadas) { }

    protected Cuenta obtenerCuentaPorCbu(Cliente cliente, String cbu) {
    	for (Cuenta cuenta : cliente.getCuentas()) {
    		if (cbu.equals(cuenta.getCbu()) && MonedaEspecieEnum.ARS.getCodigo().equals(cuenta.getMonedaAltair())) {
    			return cuenta;
    		}
        }
    	if (cliente.getCuentasCerradas() != null) {
			for (CuentaCerrada cuentaCerrada : cliente.getCuentasCerradas()) {
				if (cbu.equals(cuentaCerrada.getCbu())) {
					return CuentasUtils.encontrarCuentaPorSucursalYnumeroProducto(cliente.getCuentas(), cuentaCerrada.getNroCuentaProductoContinuador(), cuentaCerrada.getNroSucursalContinuadora());
				}
			}
		}
        return null;
    }

    protected Cuenta obtenerCuenta(List<Cuenta> cuentas, String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
        	if (ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta).equals(numeroCuenta)) {
        		return cuenta;
        	}
        }
        return null;
    }

    //Getters & Setters
    protected Cheque getCheque() {
        return cheque;
    }

    protected void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    protected Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    protected ConfirmarOperacionInDTO getOperationDetails() {
        return operationDetails;
    }

    protected void setOperationDetails(ConfirmarOperacionInDTO operationDetails) {
        this.operationDetails = operationDetails;
    }

    public void setOperationResult(OperationEcheqResponse operationResult) {
        this.operationResult = operationResult;
    }
}
