/**
 * 
 */
package ar.com.santanderrio.obp.base.respuesta.entities;

// TODO: Auto-generated Javadoc
/**
 * The Enum EstadoRespuesta.
 *
 * @author Jonatan_Bocian
 * 
 *         No agregar mas errores a esta clase
 * 
 *         Significado:
 * 
 *         OK: Leo respuesta y no leo lista de item mensaje Ejemplo : Ultimos
 *         movimientos de cuentas me devolvio 5 movimientos y ningun mensaje de
 *         warning ni error ERROR: Leo lista de item mensaje y no leo respuesta
 *         Ejemplo: El servicio fallo y no me devolvio nada, armo mensaje de
 *         error Ejemplo 2: Me pasaron un dato de tipo no valido desde el
 *         frontend y no puedo ni intentar llamar al servicio WARNING: Leo
 *         respuesta y leo item mensaje Ejemplo: Ultimos movimientos de cuentas
 *         me devuelven 0 movimientos de forma correcta y debo mostrar el
 *         mensaje "no hay movimientos para este periodo) Ejemplo 2: El selector
 *         de cuentas me devuelve una lista de 3 cuentas , 1 correcta y 2 con
 *         error de las cuales no pude obtener el saldo.
 * 
 * 
 *         TODO: A futuro se sacara ERROR_CONCURRENTE de esta clase. Tal dato
 *         ira en uno de los item mensajes asociados
 * 
 *         NO AGREGAR MAS TIPOS DE ERROR A ESTE ENUM
 */
public enum EstadoRespuesta {

	/** The ok. */
	OK,

	/** The error. */
	ERROR,

	/** The warning. */
	WARNING,

	/** The error concurrente. */
	ERROR_CONCURRENTE

}
