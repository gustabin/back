package ar.com.santanderrio.obp.base.security.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSourceWrapper.
 */
public class DataSourceWrapper implements DataSource {

	/** The Constant ORACLE_CREDENTIALS_ERROR. */
	private static final int ORACLE_CREDENTIALS_ERROR = 1017;

	/** The user. */
	private String user;

	/** The password. */
	private String password;

	/** The sistema. */
	private String sistema;

	/** The wrap. */
	private DataSource wrap;

	/**
	 * Instantiates a new data source wrapper.
	 *
	 * @param wrap
	 *            the wrap
	 */
	public DataSourceWrapper(DataSource wrap) {
		this.wrap = wrap;
	}

	/**
	 * Instantiates a new data source wrapper.
	 */
	public DataSourceWrapper() {
	}

	/**
	 * Gets the sistema.
	 *
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Setter para sistema.
	 *
	 * @param sistema
	 *            the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Setter para user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter para password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the wrap.
	 *
	 * @return the wrap
	 */
	public DataSource getWrap() {
		return wrap;
	}

	/**
	 * Setter para wrap.
	 *
	 * @param wrap
	 *            the wrap to set
	 */
	public void setWrap(DataSource wrap) {
		this.wrap = wrap;
	}

	/**
	 * Gets the log writer.
	 *
	 * @return the log writer
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return wrap.getLogWriter();
	}

	/**
	 * Unwrap.
	 *
	 * @param <T>
	 *            the generic type
	 * @param iface
	 *            the iface
	 * @return the t
	 * @throws SQLException
	 *             the SQL exception
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return wrap.unwrap(iface);
	}

	/**
	 * Setter para log writer.
	 *
	 * @param out
	 *            el nuevo log writer
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		wrap.setLogWriter(out);
	}

	/**
	 * Checks if is wrapper for.
	 *
	 * @param iface
	 *            the iface
	 * @return true, if is wrapper for
	 * @throws SQLException
	 *             the SQL exception
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return wrap.isWrapperFor(iface);
	}

	/**
	 * Setter para login timeout.
	 *
	 * @param seconds
	 *            el nuevo login timeout
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		wrap.setLoginTimeout(seconds);
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		Connection connection;
		try {
			connection = wrap.getConnection(this.user, this.password);
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_CREDENTIALS_ERROR) {
				refreshCredentials();
				connection = wrap.getConnection(this.user, this.password);
			} else {
				throw e;
			}
		}
		return connection;
	}

	/**
	 * Refresh credentials.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	private void refreshCredentials() throws SQLException {
		ApplicationContext ctx = ContextHolder.getContext();
		CredentialSecurityFactory factory = ctx.getBean(CredentialSecurityFactory.class);
		Credential credentials = factory.getCredentialBySistema(this.sistema);

		this.password = credentials.getPassword();
		this.user = credentials.getUsuario();
	}

	/**
	 * Gets the connection.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the connection
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return this.getConnection();
	}

	/**
	 * Gets the login timeout.
	 *
	 * @return the login timeout
	 * @throws SQLException
	 *             the SQL exception
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		return wrap.getLoginTimeout();
	}

	/**
	 * Gets the parent logger.
	 *
	 * @return the parent logger
	 * @throws SQLFeatureNotSupportedException
	 *             the SQL feature not supported exception
	 */
	// --
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}
}
