package br.com.synchro.hibernate.tenancy;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import br.com.synchro.hibernate.util.TenantResolver;

/**
 * @author cvs
 * @create Jan 15, 2015
 * 
 *         Schema Resolver that is set on hibernate.cfg.xml (hibernate.tenant_identifier_resolver). This resolver is responsible to select the correct
 *         tenant to be used once a hibernate session is created
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    /*
     * (non-Javadoc)
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#resolveCurrentTenantIdentifier()
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
	return TenantResolver.get();
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#validateExistingCurrentSessions()
     */
    @Override
    public boolean validateExistingCurrentSessions() {
	// TODO Auto-generated method stub
	return false;
    }
}
