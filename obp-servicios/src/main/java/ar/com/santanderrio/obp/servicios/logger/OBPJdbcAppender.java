/*
 * 
 */
package ar.com.santanderrio.obp.servicios.logger;

import org.apache.logging.log4j.core.appender.db.AbstractDatabaseAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * The Class OBPJdbcAppender.
 */
@Plugin(name = "OBPJDBC", category = "Core", elementType = "appender", printObject = true)
public class OBPJdbcAppender extends AbstractDatabaseAppender<OBPJdbcDatabaseManager> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The description. */
    private final String description;

    /**
     * Instantiates a new OBP jdbc appender.
     *
     * @param name the name
     * @param manager the manager
     */
    private OBPJdbcAppender(final String name, final OBPJdbcDatabaseManager manager) {
        super(name, null, true, manager);
        this.description = this.getName() + "{ manager=" + this.getManager() + " }";
    }

    /* (non-Javadoc)
     * @see org.apache.logging.log4j.core.appender.AbstractAppender#toString()
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Factory method for creating a JDBC appender within the plugin manager.
     *
     * @param name The name of the appender.
     * @return a new JDBC appender.
     */
    @PluginFactory
    public static OBPJdbcAppender createAppender(
            @PluginAttribute("name") final String name) {


        final StringBuilder managerName = new StringBuilder("jdbcManager{ description=").append(name);

        final OBPJdbcDatabaseManager manager = OBPJdbcDatabaseManager.getJDBCDatabaseManager(
                managerName.toString());
        if (manager == null) {
            return null;
        }

        return new OBPJdbcAppender(name, manager);
    }

}
