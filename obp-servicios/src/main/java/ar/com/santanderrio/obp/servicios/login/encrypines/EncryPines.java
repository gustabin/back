/**
 * 
 */
package ar.com.santanderrio.obp.servicios.login.encrypines;

/**
 * Manejar los datos que se le envian en el login a iatx.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface EncryPines {

    /**
	 * Recibe la cadena de cryto.js encriptada y aplicarle 3des. Si hay un error
	 * propaga el dato recibido del js como lo recibio.
	 *
	 * @param dato
	 *            the dato
	 * @return the string
	 */
    String obtenerCadena3Des(String dato);

    /**
     * Obtener clave publica para devolver a front que aplicara al crypto.js.<br/>
     * Si hay error o esta apagado el permiso operativamente retorna cadena vacia.
     * 
     * @return the encpines3Pub
     */
    String obtenerEncPub();

    /**
	 * Obtener el dato con crypto.js proveniente del front.<br/>
	 * Aplicar clave privada RSA.
	 *
	 * @param dato
	 *            the dato
	 * @return the string
	 */
    String obtenerSoloCrypto(String dato);

    /**
	 * Se recibe el json plano y poder continuar flujo como si hubiera realizado
	 * el binding cxf.
	 *
	 * @param <T>
	 *            the generic type
	 * @param cryptoInput
	 *            the crypto input
	 * @param clazz
	 *            the clazz
	 * @return the t
	 */
    <T> T obtenerViewFromJson(String cryptoInput, Class<T> clazz);
}
