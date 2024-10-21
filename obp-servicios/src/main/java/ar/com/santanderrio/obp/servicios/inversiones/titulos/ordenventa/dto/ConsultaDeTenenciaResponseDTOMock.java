/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosConsultaDeTenenciaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosTenencia;

/**
 * The Class ConsultaDeTenenciaResponseDTOMock.
 */
public class ConsultaDeTenenciaResponseDTOMock {

    /**
	 * Instantiates a new consulta de tenencia response DTO mock.
	 */
    private ConsultaDeTenenciaResponseDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
	
	/**
	 * Armar consulta de tenencia response DTO.
	 *
	 * @return the consulta de tenencia response DTO
	 */
	public static ConsultaDeTenenciaResponseDTO armarConsultaDeTenenciaResponseDTO() {
		ConsultaDeTenenciaResponseDTO consultaDeTenencia = new ConsultaDeTenenciaResponseDTO();	
		DatosConsultaDeTenenciaResponse datos = new DatosConsultaDeTenenciaResponse();
		
		List<DatosTenencia> listaTenencia = new ArrayList<DatosTenencia>();
		
		DatosTenencia datosTenencia = new DatosTenencia();
		datosTenencia.setFechaCustodia("2018/02/28");
		datosTenencia.setFechaLiquidacion("2018/02/28");
		datosTenencia.setTenenciaNominalNegociable(new BigDecimal("100000"));
		datosTenencia.setCtaTitulo(25798066);
		datosTenencia.setPlazo(48);
		datosTenencia.setCodigoAmigable("MTCFO");
		datosTenencia.setCodigoEspecieRossi("91689");
		datosTenencia.setCodigoEspecieCajaValores("91689");
		datosTenencia.setTipoEspecie("BON");
		datosTenencia.setInstrumento("TÍTULOS PRIVADOS");
		datosTenencia.setCodigoMonedaEmision("ARS");
		datosTenencia.setEstadoCuentaTitulo("0");
		datosTenencia.setMotivoBloqueo("");
		datosTenencia.setEspecieCodigo("");
		datosTenencia.setCodigoEstado("0");
		datosTenencia.setDescrEstado("NEGOCIABLE");
		datosTenencia.setSegmento("RTL");
		datosTenencia.setDescripcionEspecie("MASTELLONE HERMANOS S.A.12.625%  VTO 03/07/2021");
		datosTenencia.setEstadoTenencia("Libre Disponibilidad");
		datosTenencia.setTenenciaTotal(new BigDecimal("40000"));
		
		listaTenencia.add(datosTenencia);
		
		DatosTenencia datosTenencia2 = new DatosTenencia();
		datosTenencia2.setFechaCustodia("2018/02/28");
		datosTenencia2.setFechaLiquidacion("2018/02/28");
		datosTenencia2.setTenenciaNominalNegociable(new BigDecimal("200000"));
		datosTenencia2.setCtaTitulo(2013893);
		datosTenencia2.setPlazo(48);
		datosTenencia2.setCodigoAmigable("TS");
		datosTenencia2.setCodigoEspecieRossi("91689");
		datosTenencia2.setCodigoEspecieCajaValores("91689");
		datosTenencia2.setTipoEspecie("ACC");
		datosTenencia2.setInstrumento("ACCIONES");
		datosTenencia2.setCodigoMonedaEmision("ARS");
		datosTenencia2.setEstadoCuentaTitulo("0");
		datosTenencia2.setMotivoBloqueo("");
		datosTenencia2.setEspecieCodigo("");
		datosTenencia2.setCodigoEstado("0");
		datosTenencia2.setDescrEstado("NEGOCIABLE");
		datosTenencia2.setSegmento("RTL");
		datosTenencia2.setDescripcionEspecie("TENARIS S.A.");
		datosTenencia2.setEstadoTenencia("Libre Disponibilidad");
		datosTenencia2.setTenenciaTotal(new BigDecimal("400000"));
		listaTenencia.add(datosTenencia2);

		
		datos.setListaTenencia(listaTenencia);
		consultaDeTenencia.setDatos(datos);
		
		return consultaDeTenencia;
	}
	
}