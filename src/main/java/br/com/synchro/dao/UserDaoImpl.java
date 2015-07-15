package br.com.synchro.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.synchro.domain.User;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 7, 2015
 * 
 *         Simple DAO implementation for User Domain
 */
public class UserDaoImpl implements UserDao {

    /*
     * (non-Javadoc)
     * @see br.com.synchro.dao.UserDao#validateUser(java.lang.String, java.lang.String)
     */
    @Override
    public User validateUser(final String pUsername, final String pPassword) {
	final Session session = HibernateUtil.getSession();
	final User userObj = (User) session.createCriteria(User.class).add(Restrictions.eq("username", pUsername))
		.add(Restrictions.eq("password", pPassword)).uniqueResult();
	session.close();
	return userObj;
    }
}
