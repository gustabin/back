/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class TokenTest.
 *
 * @author sergio.e.goldentair
 */
public class TokenTest {

	/**
	 * Pipe token sin atributo con valor.
	 */
	@Test
	public void pipeTokenSinAtributoConValor() {
		GoCanjePuntosPipeToken token = new GoCanjePuntosPipeToken();
		Assert.assertEquals("El valor generado contiene un solo atributo.", "|", token.toString());
	}

	/**
	 * Pipe token con un atributo con valor.
	 */
	@Test
	public void pipeTokenConUnAtributoConValor() {
		GoCanjePuntosPipeToken token = new GoCanjePuntosPipeToken();
		token.setCanal("HB");
		Assert.assertEquals("El valor generado contiene un solo atributo.", "HB|", token.toString());
	}

	/**
	 * Pipe token con dos atributo con valor.
	 */
	@Test
	public void pipeTokenConDosAtributoConValor() {
		GoCanjePuntosPipeToken token = new GoCanjePuntosPipeToken();
		token.setCanal("HB");
		token.setHabilitadoCanje("S");
		Assert.assertEquals("El valor generado no contiene dos atributo con el separador entre ellos.", "HB|S",
				token.toString());
	}

	/**
	 * String token con un salto de linea.
	 */
	@Test
	public void stringTokenConUnSaltoDeLinea() {
		PMCStringToken token = new PMCStringToken();
		token.setDireccionCliente("XXX");

		Assert.assertEquals("El valor generado no contiene salto de linea ni retorno de carro.",
				"Direccion-Cliente:XXX\r\n", token.toString());
	}

	/**
	 * String token con dos salto de linea ordenados.
	 */
	@Test
	public void stringTokenConDosSaltoDeLineaOrdenados() {
		PMCStringToken token = new PMCStringToken();
		token.setDireccionCliente("XXX");
		token.setTipoDocumento("DNI");
		Assert.assertEquals("El valor generado no contiene salto de linea ni retorno de carro.",
				"Direccion-Cliente:XXX\r\nTipo-Documento:DNI\r\n", token.toString());
	}

	/**
	 * String token con un atributo sin formato.
	 */
	@Test
	public void stringTokenConUnAtributoSinFormato() {
		PMCStringToken token = new PMCStringToken();
		token.setPrueba("XXX");
		Assert.assertEquals("El valor generado no contiene lineas.", "", token.toString());
	}

	/**
	 * No se debe agregar el atributo erroneoConFormato ya que el tipo (Long) no
	 * es String.
	 */
	@Test
	public void stringTokenConUnAtributoTipoErroneoConFormato() {
		PMCStringToken token = new PMCStringToken();
		token.setErroneoConFormato(123L);
		Assert.assertEquals("El valor generado no contiene lineas.", "", token.toString());
	}

	/**
	 * Xml token.
	 */
	@Test
	public void xmlToken() {
		VerazXMLToken token = new VerazXMLToken();
		token.setDato("prueba");

		Assert.assertNotNull("Se genera el xml.", token.toString());
	}

	/**
	 * Xml token sin datos.
	 */
	@Test
	public void xmlTokenSinDatos() {
		VerazXMLToken token = new VerazXMLToken();

		Assert.assertNotNull("Se genera el xml.", token.toString());
	}
}
