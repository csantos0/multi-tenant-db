package br.com.synchro.dao;

import br.com.synchro.domain.User;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         Simple DAO for User Domain
 */
public interface UserDao {

    /**
     * Validates if user exists
     * 
     * @param username
     *            provided to be validated
     * @param password
     *            provided to be validated
     * @return user if exists, null otherwise
     */
    public User validateUser(final String username, final String password);
}
