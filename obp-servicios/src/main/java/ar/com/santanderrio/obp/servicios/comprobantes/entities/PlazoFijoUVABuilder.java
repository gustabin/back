/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteAltaPlazoFijoUVA;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.OperacionEstado;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.AltaComprobantePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionImpuestosPlazoFijoEntity;

/**
 * The Class PlazoFijoUVABuilder.
 */
public class PlazoFijoUVABuilder extends AltaComprobanteRequestBuilder {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PlazoFijoUVABuilder.class);

    /** The alta Comprobante PlazoFijo DTO. */
    private AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO;

    /** The fecha nacimiento cliente. */
    private String fechaNacimientoCliente;

    /**
	 * Instantiates a new plazo fijo UVA builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
    public PlazoFijoUVABuilder(Cliente cliente) {
        super(cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.entities.
     * AltaComprobanteRequestBuilder#buildComprobanteRequest()
     */
    @Override
    public AltaComprobanteRequest buildComprobanteRequest() {
        AltaComprobanteRequest request = obtenerAltaBase("20", "26");
        request.setComprobante(obtenerComprobanteAltaPlazoFijoUVA());
        request.setVersion("100");
        return request;
    }

    /**
	 * Obtener comprobante alta plazo fijo UVA.
	 *
	 * @return the comprobante alta plazo fijo UVA
	 */
    private ComprobanteAltaPlazoFijoUVA obtenerComprobanteAltaPlazoFijoUVA() {
        ComprobanteAltaPlazoFijoUVA comprobanteAltaPlazoFijoUVA = new ComprobanteAltaPlazoFijoUVA();
        comprobanteAltaPlazoFijoUVA.setCanal("04");
        comprobanteAltaPlazoFijoUVA.setSubCanal("99");
        comprobanteAltaPlazoFijoUVA.setTpoComprobante("20");
        comprobanteAltaPlazoFijoUVA.setSubTpoComprobante("26");
        comprobanteAltaPlazoFijoUVA.setEstadoOper(OperacionEstado.A);
        comprobanteAltaPlazoFijoUVA.setFechaOper(this.completarFecha(altaComprobantePlazoFijoDTO.getFechaAltaReal()));
        comprobanteAltaPlazoFijoUVA.setTitulo("ComprobanteUVA");
        comprobanteAltaPlazoFijoUVA.setImporteEnPesos(convertBigDecimalToStringWithFormat(altaComprobantePlazoFijoDTO.getImporteCertificado()));
        comprobanteAltaPlazoFijoUVA.setTipoPlazoFijo("UVA");
        comprobanteAltaPlazoFijoUVA.setPlazo(altaComprobantePlazoFijoDTO.getPlazo());
        comprobanteAltaPlazoFijoUVA.setMoneda(altaComprobantePlazoFijoDTO.getMoneda());
        comprobanteAltaPlazoFijoUVA.setValorUva(convertBigDecimalToStringWithFormat(altaComprobantePlazoFijoDTO.getCotizacionCodigoUr()));
        comprobanteAltaPlazoFijoUVA.setCapitalUVA(convertBigDecimalToStringWithFormat(altaComprobantePlazoFijoDTO.getSaldoInicUr()));
        comprobanteAltaPlazoFijoUVA.setCuentaDebito(altaComprobantePlazoFijoDTO.getNroCuentaDebito());
        comprobanteAltaPlazoFijoUVA.setTasaNominal(altaComprobantePlazoFijoDTO.getTasaNominal());
        BigDecimal tena = formatBigDecimalDosDecimales(altaComprobantePlazoFijoDTO.getTasaEfectiva());
        comprobanteAltaPlazoFijoUVA.setTasaEfectiva(convertBigDecimalToStringWithFormat(tena));
        comprobanteAltaPlazoFijoUVA.setIntereses(convertBigDecimalToStringWithFormat(altaComprobantePlazoFijoDTO.getIntereses()));
        comprobanteAltaPlazoFijoUVA.setFechaConstitucion(this.completarFecha(altaComprobantePlazoFijoDTO.getFechaAltaReal()));
        comprobanteAltaPlazoFijoUVA.setFechaVencimiento(this.completarFecha(altaComprobantePlazoFijoDTO.getFechaVencimiento()));
        comprobanteAltaPlazoFijoUVA.setAccionVencimiento(formatStrToCamelCaseWithOutSpace(altaComprobantePlazoFijoDTO.getDescripcionAccionVencimiento()));
        comprobanteAltaPlazoFijoUVA.setCuentaDeposito(altaComprobantePlazoFijoDTO.getNroCuentaDebito());
        comprobanteAltaPlazoFijoUVA.setEstado("Aceptado");
        comprobanteAltaPlazoFijoUVA.setFechaHora(this.obtenerFechaActualConHora());
        comprobanteAltaPlazoFijoUVA.setNroComprobante(altaComprobantePlazoFijoDTO.getEntidadCuentaPlazo()+altaComprobantePlazoFijoDTO.getNumeroCuentaPlazo()+altaComprobantePlazoFijoDTO.getSecuencia());
        setImpuestos(comprobanteAltaPlazoFijoUVA,altaComprobantePlazoFijoDTO);
        comprobanteAltaPlazoFijoUVA.setCliente(cliente);
        comprobanteAltaPlazoFijoUVA.getCliente().setFechaNac(this.formatearFechaPMC(this.fechaNacimientoCliente));
        return comprobanteAltaPlazoFijoUVA;
    }

    /**
	 * Sets the impuestos.
	 *
	 * @param comprobanteAltaPlazoFijoUVA
	 *            the comprobante alta plazo fijo UVA
	 * @param altaComprobantePlazoFijoDTO
	 *            the alta comprobante plazo fijo DTO
	 */
    private void setImpuestos(ComprobanteAltaPlazoFijoUVA comprobanteAltaPlazoFijoUVA, AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO) {
        List<ImposicionImpuestosPlazoFijoEntity> impuestosList = altaComprobantePlazoFijoDTO.getImpuestosPF(); 
        if(!impuestosList.isEmpty()){
            int sizeImpuestoList = impuestosList.size();
            if(sizeImpuestoList > 0){
                comprobanteAltaPlazoFijoUVA.setCampoImpuestoUno(impuestosList.get(0).getDescripcionImpuesto().trim());
                comprobanteAltaPlazoFijoUVA.setDatoImpuestoUno(convertImporteIATXToFormatStr(impuestosList.get(0).getMontoMonedaLocal()));
            }
            if(sizeImpuestoList > 1){
                comprobanteAltaPlazoFijoUVA.setCampoImpuestoDos(impuestosList.get(1).getDescripcionImpuesto().trim());
                comprobanteAltaPlazoFijoUVA.setDatoImpuestoDos(convertImporteIATXToFormatStr(impuestosList.get(1).getMontoMonedaLocal()));
            }
            if(sizeImpuestoList > 2){
                comprobanteAltaPlazoFijoUVA.setCampoImpuestoTres(impuestosList.get(2).getDescripcionImpuesto().trim());
                comprobanteAltaPlazoFijoUVA.setDatoImpuestoTres(convertImporteIATXToFormatStr(impuestosList.get(2).getMontoMonedaLocal()));
            }
            if(sizeImpuestoList > 3){
                comprobanteAltaPlazoFijoUVA.setCampoImpuestoCuatro(impuestosList.get(3).getDescripcionImpuesto().trim());
                comprobanteAltaPlazoFijoUVA.setDatoImpuestoCuatro(convertImporteIATXToFormatStr(impuestosList.get(3).getMontoMonedaLocal()));
            }
            if(sizeImpuestoList > 4){
                comprobanteAltaPlazoFijoUVA.setCampoImpuestoCinco(impuestosList.get(4).getDescripcionImpuesto().trim());
                comprobanteAltaPlazoFijoUVA.setDatoImpuestoCinco(convertImporteIATXToFormatStr(impuestosList.get(4).getMontoMonedaLocal()));
            }
            comprobanteAltaPlazoFijoUVA.setInteresesNetosImpuestos(calcularInteresesNetosImpuestos(impuestosList, altaComprobantePlazoFijoDTO.getIntereses()));
        }
    }

    /**
	 * Calcular intereses netos impuestos.
	 *
	 * @param impuestosList
	 *            the impuestos list
	 * @param intereses
	 *            the intereses
	 * @return the string
	 */
    private String calcularInteresesNetosImpuestos(List<ImposicionImpuestosPlazoFijoEntity> impuestosList, BigDecimal intereses) {
        BigDecimal acumulador = BigDecimal.ZERO;
        for(ImposicionImpuestosPlazoFijoEntity i : impuestosList){
            if(i.getMomentoCobro().equals("V")){
                try{
                    BigDecimal val = ISBANStringUtils.convertirStrToBigDecimal(i.getMontoMonedaLocal(), 2);
                    acumulador = acumulador.add(val);                    
                }catch(Exception e){
                    LOGGER.error("Error al convertir string a bigdecimal");
                }
            }
        }
        acumulador = intereses.subtract(acumulador);
        return convertBigDecimalToStringWithFormat(acumulador);
    }

    /**
	 * Sets the alta comprobante plazo fijo DTO.
	 *
	 * @param altaComprobantePlazoFijoDTO
	 *            the alta comprobante plazo fijo DTO
	 * @return the plazo fijo UVA builder
	 */
    public PlazoFijoUVABuilder setAltaComprobantePlazoFijoDTO(AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO) {
        this.altaComprobantePlazoFijoDTO = altaComprobantePlazoFijoDTO;
        return this;
    }

    /**
	 * Sets the fecha nac cliente.
	 *
	 * @param fechaNacCliente
	 *            the fecha nac cliente
	 * @return the plazo fijo UVA builder
	 */
    public PlazoFijoUVABuilder setFechaNacCliente(String fechaNacCliente){
        this.fechaNacimientoCliente = fechaNacCliente;
        return this;
    };

    /**
	 * Completa con T00:00:00Z la fecha. Ej : in: 2010-10-09 out:
	 * 2010-10-09T00:00:00Z
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
    private String completarFecha (String fecha){
        StringBuilder sb = new StringBuilder();
        sb.append(fecha);
        sb.append("T00:00:00Z");
        return sb.toString();
    }

    /**
	 * Saca tildes, convierte a camel case y saca espacios. Ej: in: Déposito en
	 * Cuenta out: DepositoEnCuenta
	 *
	 * @param in
	 *            the in
	 * @return the string
	 */
    private String formatStrToCamelCaseWithOutSpace(String in){
        String retorno = ISBANStringUtils.normalizarCaraceteres(in);
        retorno = ISBANStringUtils.convertirStringToCamelcase(retorno);
        retorno = retorno.replaceAll("\\s+","");
        return retorno;
    }


    /**
	 * Redondea bigdecimal a dos decimales EJ in: 20.010 out: 20.01 in: 20.015
	 * out: 20.02
	 *
	 * @param in
	 *            the in
	 * @return the big decimal
	 */
    private BigDecimal formatBigDecimalDosDecimales (BigDecimal in){
        return in.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    /**
	 * Convierte un bigdecimal a string y reemplaza el punto por coma.
	 *
	 * @param in
	 *            the in
	 * @return the string
	 */
    private String convertBigDecimalToStringWithFormat(BigDecimal in){
        String retorno = String.valueOf(in);
        retorno = retorno.replace(".", ",");
        return retorno;
    }
    
    
    /**
	 * Convierte formato de IATX a formato de comprobante EJ: in: 00000000100000
	 * out: 1000,00.
	 *
	 * @param in
	 *            the in
	 * @return the string
	 */
    private String convertImporteIATXToFormatStr(String in){
        String retorno = in;
        retorno = ISBANStringUtils.importePtoFijo2Canonico(retorno);
        retorno = retorno.replace(".", ",");
        return retorno;
    }

}
