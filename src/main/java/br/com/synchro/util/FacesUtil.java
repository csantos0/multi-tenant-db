package br.com.synchro.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cvs
 * @create Jul 7, 2015
 * 
 *         Utility class to JSF context operations
 */
public class FacesUtil {

    /**
     * Get Http Request from JSF context
     * 
     * @return request object
     */
    public static HttpServletRequest getRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * Get Http Session from JSF context
     * 
     * @return session object
     */
    public static HttpSession getSession() {
	return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Get SessionId (JSessionId) from JSF context
     * 
     * @return sessionId object
     */
    public static String getSessionId() {
	return getSession().getId();
    }

    /**
     * Get User Id from HttpSession
     * 
     * @return userId string
     */
    public static String getUserId() {
	final HttpSession session = getSession();
	if (session != null) {
	    return (String) session.getAttribute("userid");
	}
	return null;
    }

    /**
     * Get username from HttpSession
     * 
     * @return username string
     */
    public static String getUserName() {
	return getSession().getAttribute("username").toString();
    }
}
