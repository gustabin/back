/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * The Class DatabaseMessageSource.
 *
 * @author Jonatan_Bocian
 */
public class DatabaseMessageSource extends DatabaseMessageSourceBase {

	/** The Constant I18N_QUERY. */
	private static final String I18N_QUERY = "select code, locale, msg from i18n";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.mensaje.dao.impl.DatabaseMessageSourceBase#
	 * getI18NSqlQuery()
	 */
	@Override
	protected String getI18NSqlQuery() {
		return I18N_QUERY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.mensaje.dao.impl.DatabaseMessageSourceBase#
	 * extractI18NData(java.sql.ResultSet)
	 */
	@Override
	protected Messages extractI18NData(ResultSet rs) throws SQLException {

		Messages messages = new Messages();
		while (rs.next()) {
			Locale locale = new Locale(rs.getString("locale"));
			messages.addMessage(rs.getString("code"), locale, rs.getString("msg"));
		}
		return messages;
	}
}