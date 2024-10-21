/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

/**
 * The Class DatoClienteCuotaPagaInEntityTest.
 *
 * @author florencia.n.martinez
 */
public class DatoClienteCuotaPagaInEntityTest {

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant DOS_ENTERO. */
	private static final int DOS_ENTERO = 2;

	/** The Constant CUATRO_ENTERO. */
	private static final int CUATRO_ENTERO = 4;

	/** The Constant TI. */
	private static final String TI = "TI";

	/** The Constant UNO_ENTERO. */
	private static final int UNO_ENTERO = 1;

	/**
	 * Test para probar las regexp del objeto de entrada al DAO.
	 *
	 * @return the dato cliente cuota paga in entity test
	 */
	@Test
	public void getDatoClienteCuotaPagaInEntityTest() {
		DatoClienteCuotaPagaInEntity inEntity = obtenerInEntity();
		Boolean r = validate(inEntity);
		Assert.assertTrue(r);
		Assert.assertTrue(inEntity.getAplicacion().matches("APTM"));
		Assert.assertTrue(inEntity.getSucursal().matches("[0-9]{4}"));
		Assert.assertTrue(inEntity.getCodigoProducto().matches("0{2}|[a-zA-Z0-9]{2}"));
		Assert.assertTrue(inEntity.getCodigoSubproducto().matches("0{4}|[a-zA-Z0-9]{4}"));
		Assert.assertTrue(inEntity.getNumeroContrato().matches("[a-zA-Z0-9]{12}"));
		Assert.assertTrue(inEntity.getCalidadParticipacion().matches("TI|[a-zA-Z0-9]{2}"));
		Assert.assertTrue(inEntity.getCantidadParticipantes().matches("0{1}|[0-9]{1}"));
	}

	/**
	 * Valida las regexp.
	 *
	 * @param bean
	 *            the bean
	 * @return the boolean
	 */
	private Boolean validate(DatoClienteCuotaPagaInEntity bean) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<DatoClienteCuotaPagaInEntity>> errores = validator.validate(bean);
			for (ConstraintViolation<DatoClienteCuotaPagaInEntity> cv : errores) {
				System.out.println(cv.getMessage());
			}
			return CollectionUtils.isEmpty(errores);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Obtiene in entity.
	 *
	 * @return the consulta agenda destinatario in entity
	 */
	private DatoClienteCuotaPagaInEntity obtenerInEntity() {
		DatoClienteCuotaPagaInEntity inEntity = new DatoClienteCuotaPagaInEntity();
		inEntity.setAplicacion("APTM");
		inEntity.setSucursal(StringUtils.repeat(CERO_STRING, CUATRO_ENTERO));
		inEntity.setCodigoProducto(StringUtils.repeat(CERO_STRING, DOS_ENTERO));
		inEntity.setCodigoSubproducto(StringUtils.repeat(CERO_STRING, CUATRO_ENTERO));
		inEntity.setNumeroContrato("000001234567");
		inEntity.setCalidadParticipacion(TI);
		inEntity.setCantidadParticipantes(StringUtils.repeat(CERO_STRING, UNO_ENTERO));
		return inEntity;
	}
}
