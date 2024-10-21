package ar.com.santanderrio.obp.servicios.perfil.entities;

public class ProductoContratoEntity implements Comparable<ProductoContratoEntity>{

	private String codigoProducto;
	
	private String codigoSubproducto;
	
	private String nombreProducto;
	
	private String linkContrato;

	
	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoSubproducto() {
		return codigoSubproducto;
	}

	public void setCodigoSubproducto(String codigoSubproducto) {
		this.codigoSubproducto = codigoSubproducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getLinkContrato() {
		return linkContrato;
	}

	public void setLinkContrato(String linkContrato) {
		this.linkContrato = linkContrato;
	}

	@Override
	public int compareTo(ProductoContratoEntity o) {
        String a=new String(String.valueOf(this.getCodigoProducto())+this.getCodigoSubproducto());
        String b=new String(String.valueOf(o.getCodigoProducto())+o.getCodigoSubproducto());
        return a.compareTo(b);
	}
	
}
