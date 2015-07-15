package br.com.synchro.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.synchro.domain.ModeloDof;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 15, 2015
 */
public class ModeloDaoImpl implements ModeloDao {

    /*
     * (non-Javadoc)
     * @see br.com.synchro.dao.ModeloDao#listarModelosDof()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ModeloDof> listarModelosDof() {
	List<ModeloDof> list;
	final Session session = HibernateUtil.getSession();
	list = session.createCriteria(ModeloDof.class).list();
	session.close();
	return list;
    }

}
