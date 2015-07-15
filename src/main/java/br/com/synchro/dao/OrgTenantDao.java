package br.com.synchro.dao;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         Simple DAO for OrgTenant Domain
 */
public interface OrgTenantDao {

    /**
     * Selects the tenant to be set for some user
     * 
     * @param orgId
     *            reference id to find the correct tenant
     * @return correct tenant name
     */
    public String getTenantFromOrg(Integer orgId);
}
