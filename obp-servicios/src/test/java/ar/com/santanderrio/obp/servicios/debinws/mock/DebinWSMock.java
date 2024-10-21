package ar.com.santanderrio.obp.servicios.debinws.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.EstadoDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResumenDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dto.CompradorDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DebinWSDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.VendedorDTO;

/**
 * The Class DebinWSMock.
 */
public final class DebinWSMock {

	/**
	 * Instantiates a new debin WS mock.
	 */
	private DebinWSMock() {
		throw new IllegalAccessError("Clase para testing");
	}

	/**
	 * Obtener mock response lista debin.
	 *
	 * @return the response lista debin
	 * @throws DAOException the DAO exception
	 */
	public static ResponseListaDebin obtenerMockResponseListaDebin() throws DAOException {
		ResponseListaDebin response = new ResponseListaDebin();
		response.setSiguientePagina(2);
		List<ResumenDebinDTO> debines = new ArrayList<ResumenDebinDTO>();

		ResumenDebinDTO resumenDebinDTO1 = new ResumenDebinDTO();
		EstadoDebinDTO estadoDebin = new EstadoDebinDTO();
		estadoDebin.setCodigo("0");
		estadoDebin.setDescripcion("ACREDITADO");
		resumenDebinDTO1.setEstadoDebin(estadoDebin);
		CompradorDebinDTO comprador = new CompradorDebinDTO();
		comprador.setCuit("33123423432");
		comprador.setTitular("Andres Calamaro");
		comprador.setDescripcion("La desc del comprador");
		CuentaDebinDTO cuenta = new CuentaDebinDTO();
		cuenta.setCbu("0720000788000035232940");
		comprador.setCuenta(cuenta);
		resumenDebinDTO1.setComprador(comprador);
		VendedorDebinDTO vendedor = new VendedorDebinDTO();
		vendedor.setTitular("Charly Gacria");
		vendedor.setCuit("234234323332");
		resumenDebinDTO1.setVendedor(vendedor);
		resumenDebinDTO1.setImporte("3434.44");
		XMLGregorianCalendar c = new XMLGregorianCalendarImpl();
		resumenDebinDTO1.setVencimiento(c);
		resumenDebinDTO1.setId("1");
		resumenDebinDTO1.setTipo("debinplf");
		debines.add(resumenDebinDTO1);

		ResumenDebinDTO resumenDebinDTO2 = new ResumenDebinDTO();
		resumenDebinDTO2.setEstadoDebin(estadoDebin);
		resumenDebinDTO2.setComprador(comprador);
		resumenDebinDTO2.setVendedor(vendedor);
		resumenDebinDTO2.setImporte("11111.00");
		resumenDebinDTO2.setVencimiento(c);
		resumenDebinDTO2.setId("2");
		resumenDebinDTO2.setTipo("debinplf");
		debines.add(resumenDebinDTO2);

		ResumenDebinDTO resumenDebinDTO3 = new ResumenDebinDTO();
		resumenDebinDTO3.setEstadoDebin(estadoDebin);
		resumenDebinDTO3.setComprador(comprador);
		resumenDebinDTO3.setVendedor(vendedor);
		resumenDebinDTO3.setImporte("4444.00");
		resumenDebinDTO3.setId("3");
		resumenDebinDTO3.setVencimiento(c);
		resumenDebinDTO3.setTipo("debin");
		debines.add(resumenDebinDTO3);

		ResumenDebinDTO resumenDebinDTO4 = new ResumenDebinDTO();
		resumenDebinDTO4.setEstadoDebin(estadoDebin);
		resumenDebinDTO4.setComprador(comprador);
		resumenDebinDTO4.setVendedor(vendedor);
		resumenDebinDTO4.setImporte("444477");
		resumenDebinDTO4.setVencimiento(c);
		resumenDebinDTO4.setId("4");
		resumenDebinDTO4.setTipo("debin");
		debines.add(resumenDebinDTO4);

		Error error = new Error();
		error.setCodigo("00");
		error.setMensaje("");
		response.setError(error);

		response.setDebines(debines);
		return response;
	}

	/**
	 * Obtener mock response lista debin generico.
	 *
	 * @return the response lista debin
	 * @throws DAOException the DAO exception
	 */
	public static ResponseListaDebin obtenerMockResponseListaDebinGenerico() throws DAOException {
		ResponseListaDebin response = new ResponseListaDebin();
		Error error = new Error();
		error.setCodigo("56");
		error.setMensaje("Cuit mal formado");
		response.setError(error);
		return response;
	}

	/**
	 * Obtener mock response lista debin sin datos.
	 *
	 * @return the response lista debin
	 * @throws DAOException the DAO exception
	 */
	public static ResponseListaDebin obtenerMockResponseListaDebinSinDatos() throws DAOException {
		ResponseListaDebin response = new ResponseListaDebin();
		Error error = new Error();
		error.setCodigo("85");
		error.setMensaje("Sin datos");
		response.setError(error);
		return response;
	}

	/**
	 * Obtener mock detalle debin OK.
	 *
	 * @return the response debin
	 */
	public static ResponseDebin obtenerMockDetalleDebinOK() {
		ResponseDebin responseDebin = new ResponseDebin();
		responseDebin.setPreautorizado(false);
		CompradorDebinDTO comprador = new CompradorDebinDTO();
		CuentaDebinDTO cuentaComprador = new CuentaDebinDTO();
		cuentaComprador.setCbu("0720000788000035232940");
		comprador.setCuenta(cuentaComprador);
		responseDebin.setComprador(comprador);
		DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
		EstadoDebinDTO estadoDTO = new EstadoDebinDTO();
		estadoDTO.setCodigo("1");
		estadoDTO.setDescripcion("ACREDITADO");
		detalleDebin.setEstadoDebin(estadoDTO);
		detalleDebin.setConcepto("02");
		GregorianCalendar cal = new GregorianCalendar(2018, 1, 27);
		detalleDebin.setFechaCreacion(new XMLGregorianCalendarImpl(cal));
		GregorianCalendar cal2 = new GregorianCalendar(2018, 5, 27);
		detalleDebin.setFechaExpiracion(new XMLGregorianCalendarImpl(cal2));
		detalleDebin.setDescripcion(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m");
		detalleDebin.setIdDebin("dxcsd234vsdd");
		detalleDebin.setImporte("345.56");
		detalleDebin.setMoneda("032");
		responseDebin.setDetalleDebin(detalleDebin);
		Error error = new Error();
		error.setCodigo("00");
		error.setMensaje("El mensaje");
		responseDebin.setError(error);
		responseDebin.setEstado("ACREDITADO");
		VendedorDebinDTO vendedor = new VendedorDebinDTO();
		CuentaDebinDTO cuenta = new CuentaDebinDTO();
		cuenta.setCbu("0720000788000035232940");
		cuenta.setAlias("AliasVendedor");
		vendedor.setCuenta(cuenta);
		vendedor.setCuit("12345678912");
		vendedor.setTitular("Juan Perez");
		responseDebin.setVendedor(vendedor);

		return responseDebin;
	}

	/**
	 * Obtener respuesta consulta debin BO.
	 *
	 * @return the consulta debin WS out DTO
	 */
	public static ConsultaDebinWSOutDTO obtenerRespuestaConsultaDebinBO() {
		ConsultaDebinWSOutDTO outBO = new ConsultaDebinWSOutDTO();
		outBO.setSiguientePagina(2);
		List<DebinWSDTO> debinesDTO = new ArrayList<DebinWSDTO>();
		DebinWSDTO debin1 = new DebinWSDTO();
		debin1.setCuitSolicitante("27303851122");
		debin1.setDebinId("sdsdfdsf");
		debin1.setEstado(EstadoDebinEnum.ACREDITADO);
		debin1.setFechaVencimiento(new Date());
		debin1.setImporte("23432.22");
		debin1.setNombreSolicitante("nombreSolicitante");
		debinesDTO.add(debin1);

		DebinWSDTO debin2 = new DebinWSDTO();
		debin2.setCuitSolicitante("27303851122");
		debin2.setDebinId("sdsdfdsf");
		debin2.setEstado(EstadoDebinEnum.ACREDITADO);
		debin2.setFechaVencimiento(new Date());
		debin2.setImporte("23432.22");
		debin2.setNombreSolicitante("nombreSolicitante");
		debinesDTO.add(debin2);
		outBO.setDebinesDTO(debinesDTO);
		return outBO;
	}

	/**
	 * Obtener detalle out BO.
	 *
	 * @return the consulta detalle debin WS out DTO
	 */
	public static ConsultaDetalleDebinWSOutDTO obtenerDetalleOutBO() {
		ConsultaDetalleDebinWSOutDTO out = new ConsultaDetalleDebinWSOutDTO();
		VendedorDTO vendedor = new VendedorDTO();
		vendedor.setAlias("Alias vendedor");
		vendedor.setNombreTitular("Juan Perez");
		vendedor.setCuit("27303851122");
		vendedor.setCbu("0720033520000000819954");
		out.setVendedor(vendedor);
		CompradorDTO comprador = new CompradorDTO();
		comprador.setCbu("0720033520000000819954");
		comprador.setNumero("3123213213213");
		comprador.setNombreTitular("Diego Gonzales");
		out.setComprador(comprador);
		out.setConcepto("02");
		out.setDescripcion("La descripcion");
		out.setEstado("ACREDITADO");
		out.setFechaComprobante("29/09/1983");
		out.setFechaSolicitud(new Date());
		out.setFechaVencimiento(new Date());
		out.setIdDebin("34324");
		out.setImporteSolicitado("345.33");
		out.setMoneda("032");

		out.setNumeroComprobante("4324");
		out.setPreautorizado(false);
		return out;
	}

	/**
	 * Obtener request lista debin new.
	 *
	 * @return the request lista debin
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	public static RequestListaDebin obtenerRequestListaDebinNew() throws DatatypeConfigurationException {
		RequestListaDebin request = new RequestListaDebin();
		CompradorDebinDTO comprador = new CompradorDebinDTO();
		comprador.setCuit("20232523612");

		Calendar calendarHasta = new GregorianCalendar();
		calendarHasta.setTime(new Date());

		Calendar calendarDesde = new GregorianCalendar();
		calendarDesde.setTime(new Date());
		calendarDesde.add(Calendar.DAY_OF_YEAR, -90);

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(calendarDesde.getTime());
		XMLGregorianCalendar fechaDesde = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		request.setFechaCreacionDesde(fechaDesde);
		c.setTime(calendarHasta.getTime());
		XMLGregorianCalendar fechaHasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		request.setFechaCreacionHasta(fechaHasta);

		request.setComprador(comprador);
		request.setEstado("ACREDITADO");
		request.setNroPagina(1);
		request.setTipo("Debin");
		request.setCanal("E");
		request.setCuit("20232523612");
		request.setIp("127.0.0.1");
		request.setNroDocumento("23252361");
		request.setTipoDocumento("1");
		return request;
	}

	/**
	 * Obtener request debin new.
	 *
	 * @return the request debin
	 */
	public static RequestDebin obtenerRequestDebinNew() {
		RequestDebin request = new RequestDebin();
		request.setCanal("E");
		request.setCuit("20215871836");
		request.setIdDebin("O7L8GYKNXRYZPEWNMPRZ50");
		request.setIp("127.0.0.1");
		request.setNroDocumento("21587183");
		request.setTipoDocumento("1");
		return request;
	}

	/**
	 * Obtener consulta detalle debin WS in DTO.
	 *
	 * @return the consulta detalle debin WS in DTO
	 */
	public static ConsultaDetalleDebinWSInDTO obtenerConsultaDetalleDebinWSInDTO() {
		ConsultaDetalleDebinWSInDTO input = new ConsultaDetalleDebinWSInDTO();
		input.setCanal("E");
		input.setCuit("27216775134");
		input.setDirIP("180.166.12.5");
		input.setIdDebin("XUfu1374jjhDs");
		input.setNroDocumento("21677513");
		input.setTipoDocumento("N");
		return input;
	}

}
