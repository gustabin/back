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
 * The Class ConsultaCuotaPagaInEntityTest.
 *
 * @author florencia.n.martinez
 */
public class ConsultaCuotaPagaInEntityTest {

	/**
	 * Test para probar las regexp del objeto de entrada al DAO.
	 *
	 * @return the consulta cuota paga in entity test
	 */
	@Test
	public void getConsultaCuotaPagaInEntityTest() {
		ConsultaCuotaPagaInEntity inEntity = obtenerInEntity();
		Boolean r = validate(inEntity);
		Assert.assertTrue(r);
		Assert.assertTrue(inEntity.getNumRegistros().matches("000"));
		Assert.assertTrue(inEntity.getOficina().matches("[a-zA-B0-9]{4}"));
		Assert.assertTrue(inEntity.getCuenta().matches("[a-zA-B0-9]{12}"));
		Assert.assertTrue(inEntity.getCodEvento().matches("COBR"));
		Assert.assertTrue(inEntity.getFecInicio().matches("([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])"));
		Assert.assertTrue(inEntity.getFecFin().matches("([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])"));
		Assert.assertTrue(inEntity.getTipomov().matches("[ ]{1}|[a-zA-B0-9]{1}"));
		Assert.assertTrue(inEntity.getTimestamp().matches("[ ]{1}|[a-zA-B0-9.-]{26}"));
		Assert.assertTrue(inEntity.getCodconli().matches("[ ]{3}|[a-zA-B0-9]{3}"));
		Assert.assertTrue(inEntity.getNumSecuencia().matches("[ ]{1}|[0-9]{3}"));
	}

	/**
	 * Valida las regexp.
	 *
	 * @param bean
	 *            the bean
	 * @return the boolean
	 */
	private Boolean validate(ConsultaCuotaPagaInEntity bean) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<ConsultaCuotaPagaInEntity>> errores = validator.validate(bean);
			for (ConstraintViolation<ConsultaCuotaPagaInEntity> cv : errores) {
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
	private ConsultaCuotaPagaInEntity obtenerInEntity() {
		ConsultaCuotaPagaInEntity inEntity = new ConsultaCuotaPagaInEntity();
		inEntity.setNumRegistros("000");
		inEntity.setOficina("0000");
		inEntity.setCuenta("000001234567");
		inEntity.setCodEvento("COBR");
		inEntity.setFecInicio("2015-09-23");
		inEntity.setFecFin("2017-09-23");
		inEntity.setTipomov(StringUtils.repeat(" ", 1));
		inEntity.setTimestamp(StringUtils.repeat(" ", 1));
		inEntity.setCodconli(StringUtils.repeat(" ", 3));
		inEntity.setNumSecuencia(StringUtils.repeat(" ", 1));
		return inEntity;
	}
}
