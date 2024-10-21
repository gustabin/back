/*
 * 
 */
package ar.com.santanderrio.obp.servicios.delete.account.dao.impl;

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
import ar.com.santanderrio.obp.servicios.delete.account.dao.DiscadorDAO;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.GuardarInfoClienteDto;

/**
 * @author A308529
 *
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class DiscadorDAOImpl extends BaseDatoDAOImpl implements DiscadorDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiscadorDAOImpl.class);
	
	private static final String DISCADOR_PKG = "HB_PKG_DISCADOR";
	
	/** The Constant SCHEMA. */
	private static final String SCHEMA = "hbank";

	private static final String GUARDAR_PROCEDURE = "PRC_ADD_DISCADOR_INFO";

	private static final String IN_COMMENT = "p_comentario";

	private static final String IN_NUP = "p_nup";

	private static final String IN_LLAMAR = "p_llamar";

	private static final String IN_COMPROBANTE = "p_comprobante";

	private static final String IN_MOTIVO = "p_motivo";
	
	/** Codigo error tecnico. */
	private static final String OUT_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String OUT_AMIGABLE = "p_err_amigable";

	@Override
	public String guardarInfoDiscador(GuardarInfoClienteDto infoDto) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, comentario {} comprobante{} , llamar {} y motivo {}.",
				GUARDAR_PROCEDURE, infoDto.getNup(), infoDto.getComentario(), infoDto.getComprobante(), infoDto.getLlamar(), infoDto.getMotivo());
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		
		String codRetorno = "0";
		
		parametros.add(new SqlParameter(IN_COMMENT, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_COMPROBANTE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_LLAMAR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_MOTIVO, Types.VARCHAR));
		
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_COMMENT, infoDto.getComentario())
				.addValue(IN_NUP, infoDto.getNup())
				.addValue(IN_COMPROBANTE, infoDto.getComprobante())
				.addValue(IN_LLAMAR, infoDto.getLlamar())
				.addValue(IN_MOTIVO, infoDto.getMotivo());

		Map<String, Object> respuesta;
		try {
			respuesta = consultar(SCHEMA, DISCADOR_PKG, GUARDAR_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
		
			codRetorno = (String) respuesta.get(OUT_RESULTADO);
		} catch (SQLException e) {
			codRetorno = "1";
		}
		
		return codRetorno;
		
	}
	
}
