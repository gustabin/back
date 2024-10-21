package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;

public class InfoEmpleadoPrestamoTasaSubsidiada implements Comparable<InfoEmpleadoPrestamoTasaSubsidiada> {

	private String id;
	
	private String cuit;
	
	private String cbu;
	
	private String monto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	@Override
	public int compareTo(InfoEmpleadoPrestamoTasaSubsidiada o) {
        BigDecimal cbuPrimero = new BigDecimal(this.cbu);
        BigDecimal cbuParaComparar = new BigDecimal(o.cbu);
		
		if (cbuPrimero.compareTo(cbuParaComparar) == (1)) {
            return 1;
        }
        if (cbuPrimero.compareTo(cbuParaComparar) == (-1)) {
            return -1;
        }
        return 0;
	}
		
}
