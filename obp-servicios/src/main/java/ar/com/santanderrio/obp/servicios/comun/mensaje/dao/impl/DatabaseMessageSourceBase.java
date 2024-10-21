/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * The Class DatabaseMessageSourceBase.
 *
 * @author Jonatan_Bocian
 */
public abstract class DatabaseMessageSourceBase extends AbstractMessageSource {

	/** The messages. */
	private Messages messages;

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.support.AbstractMessageSource#resolveCode(
	 * java.lang.String, java.util.Locale)
	 */
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = messages.getMessage(code, locale);
		return createMessageFormat(msg, locale);

	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		String i18nQuery = this.getI18NSqlQuery();
		this.messages = (Messages) jdbcTemplate.query(i18nQuery, new ResultSetExtractor<Messages>() {
			@Override
			public Messages extractData(ResultSet rs) throws SQLException {

				return extractI18NData(rs);
			}
		});
	}

	/**
	 * Returns sql query used to fetch the messages from the database.
	 * 
	 * @return sql query string
	 */
	protected abstract String getI18NSqlQuery();

	/**
	 * Extracts messages from the given result set.
	 * 
	 * @param rs
	 *            is a result set
	 * @return initialized {@link Messages} instance
	 * @throws SQLException
	 *             if a SQLException is encountered getting column values or
	 *             navigating (that is, there's no need to catch SQLException)
	 */
	protected abstract Messages extractI18NData(ResultSet rs) throws SQLException;

	/**
	 * Messages bundle.
	 */
	protected static final class Messages {

		/** The messages. */
		/* <code, <locale, message>> */
		private Map<String, Map<Locale, String>> messages;

		/**
		 * Adds the message.
		 *
		 * @param code
		 *            the code
		 * @param locale
		 *            the locale
		 * @param msg
		 *            the msg
		 */
		public void addMessage(String code, Locale locale, String msg) {
			if (messages == null) {
				messages = new HashMap<String, Map<Locale, String>>();
			}

			Map<Locale, String> data = messages.get(code);
			if (data == null) {
				data = new HashMap<Locale, String>();
				messages.put(code, data);
			}

			data.put(locale, msg);
		}

		/**
		 * Gets the message.
		 *
		 * @param code
		 *            the code
		 * @param locale
		 *            the locale
		 * @return the message
		 */
		public String getMessage(String code, Locale locale) {
			if (messages != null) {
				Map<Locale, String> data = messages.get(code);
				if (data != null) {
					return data.get(locale);
				}
			}
			return "???" + code + "???";
		}
	}

}