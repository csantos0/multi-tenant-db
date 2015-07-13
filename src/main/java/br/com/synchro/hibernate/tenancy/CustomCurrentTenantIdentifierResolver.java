package br.com.synchro.hibernate.tenancy;

import org.apache.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class CustomCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static Logger logger = Logger.getLogger(CustomCurrentTenantIdentifierResolver.class);

    /**
     * 
     */
    public static ThreadLocal<String> tenantIdentifier = new ThreadLocal<String>();

    /**
     * 
     */
    public static String DEFULT_TENANT_ID = "default_db";

    /*
     * (non-Javadoc)
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#resolveCurrentTenantIdentifier()
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
	logger.info("from inside resolveCurrentTenantIdentifier....");
	String tenantId = tenantIdentifier.get();
	if (tenantId == null) {
	    tenantId = DEFULT_TENANT_ID;
	}

	logger.info("threadlocal tenant id =" + tenantId);

	return tenantId;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#validateExistingCurrentSessions()
     */
    @Override
    public boolean validateExistingCurrentSessions() {
	return true;
    }

}
