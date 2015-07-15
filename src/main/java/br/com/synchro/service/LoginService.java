package br.com.synchro.service;

import br.com.synchro.domain.User;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         Simple Service for Login operations
 */
public interface LoginService {

    /**
     * Perform the user login for authetication purposes and set the tenant identifier
     * 
     * @param user
     *            to be validated
     * @param password
     *            to be validated
     * @return true if valid user, false otherwise
     */
    public User doLogin(final String user, final String password);

    /**
     * Perform the user logout and removes the tenant identifier
     * 
     */
    public void doLogout();
}
