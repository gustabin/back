package ar.com.santanderrio.obp.servicios.extraccionefectivo.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.CardlessWithdrawal;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.CardlessWithdrawalResponse;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.Destination;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.Origin;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.entities.TipoDocumentoExtraccionEfectivoEnum;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

@Component("extraccionEfectivoSinTarjetaWS")

public class ExtraccionEfectivoDAOImpl implements ExtraccionEfectivoDAO{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionEfectivoDAOImpl.class);

    private static final String NOMBRE_WS = "EXTRACCION.EFECTIVO.SIN.DEBITO.WS";

    @Autowired
    private RestWebClient restWebClient;
    
	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource logoCabecera;
	
	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource logoPie;
	
	@Value("classpath:/report/extraccionEfectivo/comprobanteExtraccionEfectivo.jasper")
	private Resource archivoJasperExtraccionEfectivo;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	@Value("${WITHDRAWALS-API.SEC.ID}")
	private String withdrawalsApiSecId;

	/**
	 * Obtener codigo de extraccion mediante el WS de Tarjetas
	 *
	 */
	public CardlessWithdrawalResponse ejecutarSolicitud(Cuenta cuenta, Cliente cliente, int monto, String email) throws Exception {
		LOGGER.info("Consultando WS Extraccion");
		CardlessWithdrawal request = buildRequest(cuenta, cliente, monto, email);
		return callService(request);
	}

	private CardlessWithdrawal buildRequest(Cuenta cuenta, Cliente cliente, int monto, String email) {
		CardlessWithdrawal request = new CardlessWithdrawal();
		
		request.setAmount(new BigDecimal(monto));
		
		Origin origin = new Origin();
		origin.setAccountNumber(Long.parseLong(cuenta.getNroCuentaProducto()));
		origin.setAccountType(cuenta.getTipoCuentaSinUnificar());
		origin.setBranch(cuenta.getNroSucursal().substring(cuenta.getNroSucursal().length()-3, cuenta.getNroSucursal().length()));
		origin.setIdentificationNumber(ISBANStringUtils.eliminarCeros(cliente.getDni()));
		origin.setNup(cliente.getNup());
		origin.setIdentificationType(TipoDocumentoExtraccionEfectivoEnum.obtenerTipoDocumento(cliente.getTipoDocumento()).getDescripcion());
		origin.setNombreOrigen(cliente.getNombre());
		origin.setApellidoOrigen(cliente.getApellido1());
		origin.setEmailOrigen(email);
		origin.setFechaNacimientoOrigen(cliente.getFechaNacimiento().substring(0, 4) + "-" + cliente.getFechaNacimiento().substring(4, 6) + "-" + cliente.getFechaNacimiento().substring(6, cliente.getFechaNacimiento().length()));
		request.setOrigin(origin);
		
		Destination destination = new Destination();
		destination.setFirstName(cliente.getNombre());
		destination.setLastName(cliente.getApellido1());
		destination.setIdentificationNumber(ISBANStringUtils.eliminarCeros(cliente.getDni()));
		destination.setIdentificationType(TipoDocumentoExtraccionEfectivoEnum.obtenerTipoDocumento(cliente.getTipoDocumento()).getDescripcion());
		destination.setEmail(email);
		request.setDestination(destination);	
		
		return request;
	}

	public CardlessWithdrawalResponse callService(CardlessWithdrawal request) throws Exception {
		Credential credential = getCredentials();
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		String basicAuthHeader = "basic " +
				Base64Utils.encodeToString((credential.getUsuario() + ":" + credential.getPassword()).getBytes());

		client.accept(MediaType.APPLICATION_JSON);
		client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		client.header(HttpHeaders.AUTHORIZATION, basicAuthHeader);
	
		return client.post(request, CardlessWithdrawalResponse.class);
	}

	private Credential getCredentials() throws SQLException {
		try{
			return credentialSecurityFactory.getCredentialById(this.withdrawalsApiSecId);
		}catch (SQLException e) {
			LOGGER.error("Withdrawals-credentials: error access to credentials data base. {}.", e.toString());
			throw e;
		}
	}

	@Override
	public Reporte generarComprobantePDF(DatosComprobanteExtraccionEfectivoView datosComprobante) {		
		
		try {		
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperExtraccionEfectivo.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = datosComprobante.obtenerParametrosPDF();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());
		
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobanteExtraccionEfectivo.pdf");
			return reporte;
		} catch (JRException ex) {
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			throw new ISBANRuntimeException(e);
		}
	}
	
}
