package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.AceptarCompartirDatosDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PoliticasPrivacidadDTO;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class AceptarCompartirDatosDAOImpl extends BaseDatoDAOImpl implements AceptarCompartirDatosDAO{

private static final Logger LOGGER = LoggerFactory.getLogger(AceptarCompartirDatosDAOImpl.class);
	
	private static final String COMPARTIR_DATOS_PKG = "HB_PKG_ACEPTAR_COMPARTIR_DATOS";
	
	private static final String SCHEMA = "hbank";

	private static final String GUARDAR_PROCEDURE = "pcr_agregar_decision_cliente";

	private static final String IN_NUP = "p_nup";

	private static final String IN_DNI = "p_dni";

	private static final String IN_APELLIDO = "p_apellido";

	private static final String IN_NOMBRE = "p_nombre";

	private static final String IN_ACEPTACION = "p_aceptacion";

	private static final String IN_FECHA_ACEPTACION = "p_fecha_aceptacion";
	
	private static final String OUT_TECNICO = "p_err_tecnico";

	private static final String OUT_RESULTADO = "p_resultado";

	private static final String OUT_AMIGABLE = "p_err_amigable";

	
	@Override
	public String grabarDecisionCliente(PoliticasPrivacidadDTO politicasPrivacidadDTO) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, dni {} y decision {} en la fecha {}.",
				GUARDAR_PROCEDURE, politicasPrivacidadDTO.getNup(), politicasPrivacidadDTO.getDni(),
				politicasPrivacidadDTO.getAceptacion(), politicasPrivacidadDTO.getFechaAceptacion());
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		
		String codRetorno = "0";
		
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_APELLIDO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_NOMBRE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ACEPTACION, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA_ACEPTACION, Types.VARCHAR));
		
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_NUP, politicasPrivacidadDTO.getNup())
				.addValue(IN_DNI, politicasPrivacidadDTO.getDni())
				.addValue(IN_APELLIDO, politicasPrivacidadDTO.getApellido())
				.addValue(IN_NOMBRE, politicasPrivacidadDTO.getNombre())
				.addValue(IN_ACEPTACION, politicasPrivacidadDTO.getAceptacion())
				.addValue(IN_FECHA_ACEPTACION, politicasPrivacidadDTO.getFechaAceptacion());

		Map<String, Object> respuesta;
		try {
			respuesta = consultar(SCHEMA, COMPARTIR_DATOS_PKG, GUARDAR_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
		
			codRetorno = (String) respuesta.get(OUT_RESULTADO);
		} catch (SQLException e) {
			codRetorno = "1";
		}
		
		return codRetorno;
		
	}
	
}
