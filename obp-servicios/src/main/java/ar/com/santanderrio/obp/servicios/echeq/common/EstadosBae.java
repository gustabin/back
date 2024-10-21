package ar.com.santanderrio.obp.servicios.echeq.common;

/**
 * The Enum EstadosBae.
 */
public enum EstadosBae {

	E ("E"),
	F ("F"),
	P ("P"),
	R ("R"),
	X ("X"),
	C ("C");

	private String id;

	EstadosBae(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
