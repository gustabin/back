/*
 * 
 */
package ar.com.santanderrio.obp.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class CrossOriginResourceSharingFilter.
 */
@WebFilter(urlPatterns = { "/*" })
public class CrossOriginResourceSharingFilter implements Filter {

    /**
     * Instantiates a new cross origin resource sharing filter.
     */
    public CrossOriginResourceSharingFilter() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers",
                "Content-Type, Authorization, Accept, Wil, NONE");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        chain.doFilter(request, response);
    }
}
