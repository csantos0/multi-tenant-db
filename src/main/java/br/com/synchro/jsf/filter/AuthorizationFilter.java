package br.com.synchro.jsf.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cvs
 * @create Jul 7, 2015
 * 
 *         Authorization Filter to check if there is a user logged
 */
public class AuthorizationFilter implements Filter {

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
	// Nothing to-do here

    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest pRequest, final ServletResponse pResponse, final FilterChain pChain) {
	try {
	    final HttpServletRequest reqt = (HttpServletRequest) pRequest;
	    final HttpServletResponse resp = (HttpServletResponse) pResponse;
	    final HttpSession ses = reqt.getSession(false);

	    final String reqURI = reqt.getRequestURI();
	    if (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
		    || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource"))
	    {
		pChain.doFilter(pRequest, pResponse);
	    } else {
		resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
	    }
	} catch (final Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig pFilterConfig) {
	// Nothing to-do here

    }
}
