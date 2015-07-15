package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         OrgTenant domain class used by main database
 */
@Entity
@Table(name = "ORG_TENANT")
@SuppressWarnings("serial")
public class OrgTenant implements Serializable {

    @Id
    @Column(name = "TEN_ORG_ID")
    private Integer orgId;

    @Column(name = "TEN_TENANT_DS")
    private String tenantName;

    /**
     * @return the orgId
     */
    public Integer getOrgId() {
	return orgId;
    }

    /**
     * @return the tenantName
     */
    public String getTenantName() {
	return tenantName;
    }

    /**
     * @param pOrgId
     *            the orgId to set
     */
    public void setOrgId(final Integer pOrgId) {
	orgId = pOrgId;
    }

    /**
     * @param pTenantName
     *            the tenantName to set
     */
    public void setTenantName(final String pTenantName) {
	tenantName = pTenantName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "OrgTenant [orgId=" + orgId + "]";
    }

}
