/**
 * 
 */
package ar.com.santanderrio.obp.base.database;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;

// TODO: Auto-generated Javadoc
/**
 * The Class Params.
 *
 * @author B039636
 */
public class Params {

	/** The in params. */
	private List<Object> inParams;

	/** The out params. */
	private List<Object> outParams;

	/**
	 * Agrega un parametro de entrada a la lista.
	 *
	 * @param param
	 *            the param
	 */
	public void addParametroIn(Object param) {
		if (inParams == null) {
			inParams = new ArrayList<Object>();
		}
		inParams.add(param);
	}

	/**
	 * Retorna la lista de parametros de entrada.
	 *
	 * @return the parametros in
	 */
	public List<Object> getParametrosIn() {
		return inParams;
	}

	/**
	 * Agrega un parametro de salida a la lista.
	 *
	 * @param param
	 *            the param
	 */
	public void addParametroOut(Object param) {
		if (outParams == null) {
			outParams = new ArrayList<Object>();
		}
		outParams.add(param);
	}

	/**
	 * Retorna la lista de parametros de salida.
	 *
	 * @return the parametros out
	 */
	public List<Object> getParametrosOut() {
		return outParams;
	}

	/**
	 * Setea el valor que obtuvo de la base de datos.
	 *
	 * @param pos
	 *            the pos
	 * @param element
	 *            the element
	 */
	public void setOutParameter(int pos, Object element) {
		/*
		 * TODO: Revisar outParams==null y si es genuino generar la excepcion
		 * 
		 */
		if (outParams != null) {
			outParams.set(pos, element);
		} else {
			throw new ISBANRuntimeException("outParams == null in database call");
		}
	}
}
