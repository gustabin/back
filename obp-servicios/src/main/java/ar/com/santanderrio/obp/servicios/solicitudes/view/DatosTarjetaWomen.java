package ar.com.santanderrio.obp.servicios.solicitudes.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.adhesionwomen.entities.DatosTarjetaWomenEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosTarjetaWomen implements Comparable<DatosTarjetaWomen>{

	private String numeroTarjeta;
	
	private String marcaTarjeta;
	
	private String condicionCliente;
	
	private String condicionClienteEntity;
	
	private String nombreCliente;
	
	private String tarjeta;
	
	private static final String CONDICION_TITULAR = "0";

	private static final String MARCA_VISA = "1";
	
	private static final String MARCA_MASTER = "3";
	
	public DatosTarjetaWomen() {
		super();
	}

	public DatosTarjetaWomen(DatosTarjetaWomen tarjetaDTO) {
		
		this.condicionCliente = CONDICION_TITULAR.equals(tarjetaDTO.getCondicionClienteEntity()) ? "Titular" : "Adicional";
		this.nombreCliente = tarjetaDTO.getNombreCliente().trim();
		this.tarjeta = armarDatosTarjeta(tarjetaDTO);
			
	}
	
	public DatosTarjetaWomen(DatosTarjetaWomenEntity tarjetaEntity) {
		
		this.condicionClienteEntity = tarjetaEntity.getAutorizadoTC();
		this.nombreCliente = tarjetaEntity.getApellidoNombreEmbozado();
		this.marcaTarjeta = tarjetaEntity.getMarcaTC();
		this.numeroTarjeta = tarjetaEntity.getNumeroTarjeta();
			
	}
	
	private String armarDatosTarjeta(DatosTarjetaWomen tarjetaDTO) {
		
		String marca;
		String numero;
		
		if (MARCA_VISA.equals(tarjetaDTO.getMarcaTarjeta())) {
			marca = TipoCuentaTarjeta.TIPOCTA_VISA.getAbreviatura();
			numero = TarjetaUtils.crearMascaraNroTarjeta(tarjetaDTO.getNumeroTarjeta(), TipoCuentaTarjeta.TIPOCTA_VISA);
		} else if (MARCA_MASTER.equals(tarjetaDTO.getMarcaTarjeta())) {
			marca = TipoCuentaTarjeta.TIPOCTA_MASTER.getAbreviatura();
			numero = TarjetaUtils.crearMascaraNroTarjeta(tarjetaDTO.getNumeroTarjeta(), TipoCuentaTarjeta.TIPOCTA_MASTER);
		} else {
			marca = TipoCuentaTarjeta.TIPOCTA_AMEX.getAbreviatura();
			numero = TarjetaUtils.crearMascaraNroTarjeta(tarjetaDTO.getNumeroTarjeta(), TipoCuentaTarjeta.TIPOCTA_AMEX);
		}

		return marca + " " + numero;
	}
		
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getMarcaTarjeta() {
		return marcaTarjeta;
	}

	public void setMarcaTarjeta(String marcaTarjeta) {
		this.marcaTarjeta = marcaTarjeta;
	}

	public String getCondicionCliente() {
		return condicionCliente;
	}

	public void setCondicionCliente(String condicionCliente) {
		this.condicionCliente = condicionCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getCondicionClienteEntity() {
		return condicionClienteEntity;
	}

	public void setCondicionClienteEntity(String condicionClienteEntity) {
		this.condicionClienteEntity = condicionClienteEntity;
	}

	@Override
	public int compareTo(DatosTarjetaWomen o) {

		if (Integer.parseInt(condicionClienteEntity) < Integer.parseInt(o.condicionClienteEntity)) {
			return -1;
		}
		
		if (Integer.parseInt(condicionClienteEntity) > Integer.parseInt(o.condicionClienteEntity)) {
			return 1;
		}
		
		return 0;
		
	}
	
}