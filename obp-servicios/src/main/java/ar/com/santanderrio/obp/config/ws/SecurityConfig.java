/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomWebSecurityExpressionHandler;

/**
 * Configurar lo relacionado a la seguridad de las url y los permisos sobre las
 * acciones.<br/>
 * Si se desea filtrar url por expresion regular agregar el matchers como figura
 * a continuacion.
 * 
 * <pre>
 * <code>
 *  .antMatchers("/cuentas/**").access(getPermiso(AccionController.IR_INICIO_CUENTAS))
 * </code>
 * </pre>
 * 
 * Esto como exclusion a los permisos filtrados por anotacion de los metodos
 * SEI.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** The application context. */
    @Autowired
    /** Poder acceder al contexto. */
    private ApplicationContext applicationContext;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.
     * config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests().antMatchers("/login/**")
                .permitAll().antMatchers("/inicial/doInit").permitAll().antMatchers("/inicial/preLogin").permitAll()
                .antMatchers("/monitoreo/**").permitAll().antMatchers("/claveOnline/**").permitAll()
                .antMatchers("/api/**").permitAll().anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager());
    }

    /**
     * Armar la funcion tienePermisoIsban para la accion recibida como parametro.
     * 
     * <PRE>
     * <CODE>
     *  tienePermisoIsban('irInicioTarjetas')
     * </CODE>
     * </PRE>
     *
     * @param accion
     *            the accion
     * @return the permiso
     */
    @SuppressWarnings("unused")
    private String getPermiso(AccionController accion) {
        return String.format("tienePermisoIsban('%1$s')", accion.getDescripcion());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.
     * config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.expressionHandler(
                applicationContext.getBean("customExpressionHandler", CustomWebSecurityExpressionHandler.class));
    }

    /**
     * Web expression voter.
     *
     * @param customExpressionHandler
     *            the custom expression handler
     * @return the web expression voter
     */
    @Bean(name = "webExpressionVoter")
    @Order(1)
    public WebExpressionVoter webExpressionVoter(CustomWebSecurityExpressionHandler customExpressionHandler) {
        WebExpressionVoter voter = new WebExpressionVoter();
        voter.setExpressionHandler(customExpressionHandler);
        return voter;
    }

    /**
     * Access decision manager.
     *
     * @return the access decision manager
     */
    @SuppressWarnings("unchecked")
    @Bean
    @DependsOn(value = { "webExpressionVoter" })
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(new RoleVoter(),
                new AuthenticatedVoter(), applicationContext.getBean("webExpressionVoter", WebExpressionVoter.class));
        return new UnanimousBased(decisionVoters);
    }

}
