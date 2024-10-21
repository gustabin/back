package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePagarDebin;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.OperacionEstado;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.PagoDebin;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.PagoDebinDTO;


/**
 * The Class PagarDebinEntityBuilder.
 */
public class PagarDebinEntityBuilder extends AltaComprobanteRequestBuilder {

	/** The Constant NUMERO_DE_VERSION. */
	private static final String NUMERO_DE_VERSION = "100";

    /** The Constant CANAL. */
	private static final String CANAL = "04";
    
	/** The Constant SUB_CANAL. */
	private static final String SUB_CANAL = "99";
	
	/** The Constant TIPO_COMPROBANTE_PAGAR_DEBIN. */
	private static final String TIPO_COMPROBANTE_PAGAR_DEBIN = "20";
	
	/** The Constant SUBTIPO_COMPROBANTE_PAGAR_DEBIN. */
	private static final String SUBTIPO_COMPROBANTE_PAGAR_DEBIN = "27";

	/** The Constant ACEPTADA. */
	private static final String ACEPTADA = "Aceptada";
	
	/** The Constant POSICION_INICIAL_HORA. */
	private static final int POSICION_INICIAL_HORA = 11;
	
	/** The pago debin dto. */
	private PagoDebinDTO pagoDebinDTO;
	
    /**
	 * Instantiates a new pagar debin entity builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
    public PagarDebinEntityBuilder(Cliente cliente) {
		super(cliente);
	}
    

    /**
	 * Sets the pago Debin DTO.
	 *
	 * @param pagoDebinDTO
	 *            the pago debin DTO
	 * @return the pago debin entity builder
	 */
    public PagarDebinEntityBuilder setPagoDebinDTO(PagoDebinDTO pagoDebinDTO) {
        this.pagoDebinDTO = pagoDebinDTO;
        return this;
    }
    
	@Override
	public AltaComprobanteRequest buildComprobanteRequest() {
        AltaComprobanteRequest request = obtenerAltaBase(TIPO_COMPROBANTE_PAGAR_DEBIN, SUBTIPO_COMPROBANTE_PAGAR_DEBIN);
        request.setComprobante(obtenerComprobanteComprobantePagarDebin());
        request.setVersion(NUMERO_DE_VERSION);
        return request;
	}

	/**
	 * Obtiene comprobante
	 * 
	 * @return Comprobante
	 */
	private Comprobante obtenerComprobanteComprobantePagarDebin() {
        ComprobantePagarDebin comprobante = new ComprobantePagarDebin();
        comprobante.setCanal(CANAL);
        comprobante.setSubCanal(SUB_CANAL);
        comprobante.setTpoComprobante(TIPO_COMPROBANTE_PAGAR_DEBIN);
        comprobante.setSubTpoComprobante(SUBTIPO_COMPROBANTE_PAGAR_DEBIN);
        comprobante.setEstadoOper(OperacionEstado.A);
        comprobante.setDescEstado(ACEPTADA);        
        comprobante.setPagoDebin(obtenerPagoDebin());
        comprobante.setCliente(cliente);
        String fechaActualConHora = obtenerFechaActualConHora();
        comprobante.setFechaOper(fechaActualConHora);
        comprobante.setHoraOper(fechaActualConHora.substring(POSICION_INICIAL_HORA, fechaActualConHora.length()-1));
        comprobante.setFechaGen(fechaActualConHora);        
        return comprobante;
	}

	/**
	 * Obtiene pago debin
	 * 
	 * @return PagoDebin
	 */
	private PagoDebin obtenerPagoDebin() {
		PagoDebin pagoDebin = new PagoDebin();
		pagoDebin.setTipoDeCuenta(pagoDebinDTO.getTipoDeCuenta());
		pagoDebin.setNumeroCuenta(pagoDebinDTO.getNumeroCuenta());
		pagoDebin.setImporteDebitado(pagoDebinDTO.getImporteDebitado());
		pagoDebin.setFechaSolicitado(pagoDebinDTO.getFechaSolicitado());
		pagoDebin.setFechaVencimiento(pagoDebinDTO.getFechaVencimiento());
		pagoDebin.setAliasCuenta(pagoDebinDTO.getAliasCuenta());
		pagoDebin.setSolicitante(pagoDebinDTO.getSolicitante());
		pagoDebin.setIdSolicitante(pagoDebinDTO.getIdSolicitante());
		pagoDebin.setCbu(pagoDebinDTO.getCbu());
		pagoDebin.setAlias(pagoDebinDTO.getAlias());
		pagoDebin.setDescripcion(pagoDebinDTO.getDescripcion());
		pagoDebin.setConcepto(pagoDebinDTO.getConcepto());
		pagoDebin.setIdDebin(pagoDebinDTO.getIdDebin());
		pagoDebin.setNroComprobante(pagoDebinDTO.getNroComprobante());
		return pagoDebin;
	}
	
}
