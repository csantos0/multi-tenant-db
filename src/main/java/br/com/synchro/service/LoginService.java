package br.com.synchro.service;

import br.com.synchro.domain.User;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public interface LoginService {

    /**
     * @param user
     * @param password
     * @return true if valid user, false otherwise
     */
    public User doLogin(final String user, final String password);

    /**
     * logout
     */
    public void doLogout();
}
