package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;

public class DatosItemDinamico {
	
	@JsonProperty("ListaMenu")
	private List<Menu> listaMenu;
	
	public List<Menu> getListaMenu() {
		return listaMenu;
	}


	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	
}
