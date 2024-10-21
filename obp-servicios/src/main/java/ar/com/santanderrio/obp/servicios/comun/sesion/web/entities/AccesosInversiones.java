package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;

public class AccesosInversiones {

	private List<Menu> Accesos; 
	
	private Boolean isConsulted = false;

	public List<Menu> getAccesos() {
		return Accesos;
	}

	public void setAccesos(List<Menu> accesos) {
		Accesos = accesos;
	}

	public Boolean getIsConsulted() {
		return isConsulted;
	}

	public void setIsConsulted(Boolean isConsulted) {
		this.isConsulted = isConsulted;
	}
	
}
