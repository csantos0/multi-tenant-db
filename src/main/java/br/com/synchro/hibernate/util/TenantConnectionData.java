package br.com.synchro.hibernate.util;

import java.io.Serializable;

/**
 * @author cvs
 * @create Jul 14, 2015
 * 
 *         Domain class used to wrap metadata information to serve the connection provider
 */
public class TenantConnectionData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tenantName;

    private String url;

    private String username;

    private String password;

    private String driverClass;

    private Integer maxActive;

    private Integer initialSize;

    /**
     * @return the driverClass
     */
    public String getDriverClass() {
	return driverClass;
    }

    /**
     * @return the initialSize
     */
    public Integer getInitialSize() {
	return initialSize;
    }

    /**
     * @return the maxActive
     */
    public Integer getMaxActive() {
	return maxActive;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @return the tenantName
     */
    public String getTenantName() {
	return tenantName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * @param pDriverClass
     *            the driverClass to set
     */
    public void setDriverClass(final String pDriverClass) {
	driverClass = pDriverClass;
    }

    /**
     * @param pInitialSize
     *            the initialSize to set
     */
    public void setInitialSize(final Integer pInitialSize) {
	initialSize = pInitialSize;
    }

    /**
     * @param pMaxActive
     *            the maxActive to set
     */
    public void setMaxActive(final Integer pMaxActive) {
	maxActive = pMaxActive;
    }

    /**
     * @param pPassword
     *            the password to set
     */
    public void setPassword(final String pPassword) {
	password = pPassword;
    }

    /**
     * @param pTenantName
     *            the tenantName to set
     */
    public void setTenantName(final String pTenantName) {
	tenantName = pTenantName;
    }

    /**
     * @param pUrl
     *            the url to set
     */
    public void setUrl(final String pUrl) {
	url = pUrl;
    }

    /**
     * @param pUsername
     *            the username to set
     */
    public void setUsername(final String pUsername) {
	username = pUsername;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TenantConnectionData [tenantName=" + tenantName + ", url=" + url + ", username=" + username + "]";
    }
}
