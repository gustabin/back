package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

public class ComprobantesPorCuentaView extends ResumenesMensualesCuentaView{

	private EstadoRespuesta estado;
	
	private String tipoError;
	
	private List<Interviniente> intervinientes;
	
	private String numeroCuentaTitulo;
	
	private String sucursal;
	

	public EstadoRespuesta getEstado() {
		return estado;
	}

	public void setEstado(EstadoRespuesta estado) {
		this.estado = estado;
	}

	public String getTipoError() {
		return tipoError;
	}

	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
			
}
