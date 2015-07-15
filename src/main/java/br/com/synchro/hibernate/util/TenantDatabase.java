/**
 * 
 */
package br.com.synchro.hibernate.util;

/**
 * @author cvs
 * @create Jan 16, 2015
 * 
 *         Enum Constants to centralize naming patterns for tenants
 */
public enum TenantDatabase {

    /**
     * Main Tenant for the application
     */
    MAIN,

    /**
     * Prefix used to call tenants after the setup
     */
    TENANT;

}
