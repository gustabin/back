package ar.com.santanderrio.obp.servicios.extraccionefectivo.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosClienteExtraccionEfectivoView {

	private List<CuentaView> listaCuentas;
	
	private String nombreTitular;
	
	private String tipoDocumentoTitular;
	
	private String nroDocumentoTitular;
	
	private String mail;

	private String tooltip;
	
	
	public List<CuentaView> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<CuentaView> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getTipoDocumentoTitular() {
		return tipoDocumentoTitular;
	}

	public void setTipoDocumentoTitular(String tipoDocumentoTitular) {
		this.tipoDocumentoTitular = tipoDocumentoTitular;
	}

	public String getNroDocumentoTitular() {
		return nroDocumentoTitular;
	}

	public void setNroDocumentoTitular(String nroDocumentoTitular) {
		this.nroDocumentoTitular = nroDocumentoTitular;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

}
