package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ListaFiltradaPorFecha;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.MonedaACView;

public class FiltroPorFechaDTOMock {

	private FiltroPorFechaDTOMock() {
		throw new IllegalAccessError("Clase para testing");
	}
		
	public static FiltroPorFechaDTO armarFiltroPorFechaDTOMock() {
		
    	FiltroPorFechaDTO filtroPorFechaDTO = new FiltroPorFechaDTO();
    	
    	List<ListaFiltradaPorFecha> periodos = new ArrayList<ListaFiltradaPorFecha>();
    	 
    	ListaFiltradaPorFecha periodo30D = new ListaFiltradaPorFecha();
    	periodo30D.setCodigoPeriodo("30D");
    	periodo30D.setDescripcionPeriodo("Ultimos 30 dias");
    	periodo30D.setDisponibilidadInformacion(Boolean.TRUE);
    	periodo30D.setDisponibilidadPeriodo(30);
    	periodo30D.setEtiquetaPeriodoCombo("30 dias");
    	periodo30D.setFechaInicio("2018-07-25T00:00:00");
    	periodo30D.setFechaFin("2018-08-24T00:00:00");
    	List<MonedaACView> listaMonedas = new ArrayList<MonedaACView>();
    	MonedaACView monedaView30D = new MonedaACView();
    	monedaView30D.setId("ARS");
    	monedaView30D.setDescripcion("Tenencia en Pesos");
    	monedaView30D.setDefecto(Boolean.TRUE);
    	listaMonedas.add(monedaView30D);
    	periodo30D.setListaMonedas(listaMonedas);
    	periodo30D.setPorDefecto(Boolean.TRUE);
    	periodos.add(periodo30D);
    	
    	ListaFiltradaPorFecha periodoANIO = new ListaFiltradaPorFecha();
    	periodoANIO.setCodigoPeriodo("ANIO");
    	periodoANIO.setDescripcionPeriodo("Año en curso");
    	periodoANIO.setDisponibilidadInformacion(Boolean.TRUE);
    	periodoANIO.setDisponibilidadPeriodo(186);
    	periodoANIO.setEtiquetaPeriodoCombo("Año en curso");
    	periodoANIO.setFechaInicio("2018-01-01T00:00:00");
    	periodoANIO.setFechaFin("2018-08-24T00:00:00");
    	List<MonedaACView> listaMonedasANIO = new ArrayList<MonedaACView>();
    	MonedaACView monedaViewANIO = new MonedaACView();
    	monedaViewANIO.setId("ARS");
    	monedaViewANIO.setDescripcion("Tenencia en Pesos");
    	listaMonedas.add(monedaViewANIO);
    	periodoANIO.setListaMonedas(listaMonedasANIO);
    	periodos.add(periodoANIO);
    	
    	filtroPorFechaDTO.setPeriodos(periodos);
    	
    	return filtroPorFechaDTO;
	}
	
}
