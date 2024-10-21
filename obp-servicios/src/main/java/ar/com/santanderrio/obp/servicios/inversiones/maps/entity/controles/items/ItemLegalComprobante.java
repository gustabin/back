package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import java.util.ArrayList;
import java.util.List;

public class ItemLegalComprobante extends ItemComprobante {

	protected List<String> legales;

	

	/**
	 * @return the legales
	 */
	public List<String> getLegales() {
		return legales;
	}


	/**
	 * @param legales the legales to set
	 */
	public void setLegales(List<String> legales) {
		this.legales = legales;
	}



	public String getTipoComprobante() {
		return "legal";
	}


	public void agregarLegal(Object valor) {
			if(this.legales==null){
			this.legales = new ArrayList<String>();
		}
			legales.add(valor.toString());
	}

}
