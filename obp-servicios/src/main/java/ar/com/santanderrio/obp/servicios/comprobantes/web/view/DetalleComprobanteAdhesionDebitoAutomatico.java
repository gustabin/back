package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

public class DetalleComprobanteAdhesionDebitoAutomatico  extends DetalleComprobanteView{


	  /**
	 * 
	 */
	private static final long serialVersionUID = -6271378195835191341L;

	/** The logo cabecera. */
    private String imagenLogoCabecera ="logo_cabecera_comprobante.png";

	  /** The logo pie. */
	private String imagenLogoPie ="logo_cierre_comprobante.png";
	
	private String tituloImgKey = "LOGO_CABECERA";
	
	private String imagenPieKey = "LOGO_CIERRE";
	
	private String empresaKey ="EMPRESA";
	
	private String identificacionkey ="PES_IDENTIFICACION";
	
	private String limiteKey ="LIMITE";
	
	private String nroComprobanteKey ="NRO_COMPROBANTE";
	
	private String fechaOperacionKey ="FECHA_OPERACION";
	
	private String numeroPagoKey="NUMERO_PAGO";
	
	private String nombrePagoKey="NOMBRE_PAGO";

	private String limite;
	
	private String nroComprobante;

	private String numeroPago;
	
	private String nombrePago;
	
	public String getLimiteKey() {
		return limiteKey;
	}

	public void setLimiteKey(String limiteKey) {
		this.limiteKey = limiteKey;
	}

	public String getNroComprobanteKey() {
		return nroComprobanteKey;
	}

	public void setNroComprobanteKey(String nroComprobanteKey) {
		this.nroComprobanteKey = nroComprobanteKey;
	}

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}

	public String getNombrePago() {
		return nombrePago;
	}

	public void setNombrePago(String nombrePago) {
		this.nombrePago = nombrePago;
	}
	
	
	/** The pmc servicio. */
	private final String adhesionDebitoAutomaticoJusper = "adhesionDebitoAutomatico/ComprobanteAdhesionDebitoAutomatico.jasper";
	
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(tituloImgKey, ResourceUtils.getFile(path + imagenLogoCabecera).getPath());
		parametros.put(empresaKey, getEmpresa());
		parametros.put(identificacionkey, getIdentificacion());
		parametros.put(nroComprobanteKey, getNroComprobante());
		parametros.put(limiteKey, getLimite());
		parametros.put(fechaOperacionKey, getFechaOperacion());
		parametros.put(imagenPieKey, ResourceUtils.getFile(path + imagenLogoPie).getPath());
		parametros.put(numeroPagoKey, getNumeroPago());
		parametros.put(nombrePagoKey, getNombrePago());
		return parametros;
	}
	
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + adhesionDebitoAutomaticoJusper).getPath();
	}
	
			
	
	
}
