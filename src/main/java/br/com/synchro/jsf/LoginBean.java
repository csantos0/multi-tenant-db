package br.com.synchro.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.synchro.domain.User;
import br.com.synchro.service.LoginService;
import br.com.synchro.service.LoginServiceImpl;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String user;
    private LoginService loginService;
    private String orgId;

    /**
     * 
     */
    public LoginBean() {
	this.loginService = new LoginServiceImpl();
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
	return orgId;
    }

    /**
     * @return pwd
     */
    public String getPwd() {
	return pwd;
    }

    /**
     * @return user
     */
    public String getUser() {
	return user;
    }

    /**
     * logout event, invalidate session
     * 
     * @return nav
     */
    public String logout() {
	this.loginService.doLogout();
	FacesUtil.getSession().invalidate();
	return "login";
    }

    /**
     * @param pOrgId
     *            the orgId to set
     */
    public void setOrgId(final String pOrgId) {
	orgId = pOrgId;
    }

    /**
     * @param pPwd
     *            the pwd to set
     */
    public void setPwd(final String pPwd) {
	pwd = pPwd;
    }

    /**
     * @param user1
     */
    public void setUser(final String user1) {
	this.user = user1;
    }

    // validate login
    /**
     * @return nav
     */
    public String validateUsernamePassword() {
	final User userRes = this.loginService.doLogin(user, pwd);

	if (user != null) {
	    final HttpSession session = FacesUtil.getSession();
	    session.setAttribute("username", user);
	    this.setOrgId(userRes.getOrganization().getId() + "");
	    return "admin";
	}
	FacesContext.getCurrentInstance().addMessage(
		null,
		new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd",
			"Please enter correct username and Password"));
	return "login";
    }

}