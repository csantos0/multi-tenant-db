package br.com.synchro.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.synchro.domain.OrgTenant;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         Simple DAO implementation for OrgTenant Domain
 */
public class OrgTenantDaoImpl implements OrgTenantDao {

    /*
     * (non-Javadoc)
     * @see br.com.synchro.dao.OrgTenantDao#getTenantFromOrg(java.lang.Integer)
     */
    @Override
    public String getTenantFromOrg(final Integer pOrgId) {
	final Session session = HibernateUtil.getSession();
	final OrgTenant orgTenantObj = (OrgTenant) session.createCriteria(OrgTenant.class).add(Restrictions.eq("orgId", pOrgId))
		.uniqueResult();
	session.close();
	return orgTenantObj.getTenantName();
    }

}
