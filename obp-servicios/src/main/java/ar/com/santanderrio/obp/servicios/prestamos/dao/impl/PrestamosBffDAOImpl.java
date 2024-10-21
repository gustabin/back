package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosBffDAO;

@Component("prestamosBffDAO")
public class PrestamosBffDAOImpl extends AbstractPrestamosBffDAOImpl implements PrestamosBffDAO {

	private static final String NOMBRE_WS = "PRESTAMOS-BFF";

	@Value("${PRESTAMOS-BFF.LIQUIDACION.ENCOLAR:/api/v1/liquidacion/encolar}")
	private String baseUriEncolar;

	@Value("${PRESTAMOS-BFF.LIQUIDACION.LIQUIDAR:/api/v1/liquidacion/liquidar}")
	private String baseUriLiquidar;

	public PrestamosBffDAOImpl() {
		super(NOMBRE_WS);
	}

	@Override
	protected String getBaseUriEncolar() {
		return baseUriEncolar;
	}

	@Override
	protected String getBaseUriLiquidar() {
		return baseUriLiquidar;
	}

}
