package br.com.synchro.hibernate.tenancy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import br.com.synchro.hibernate.connection.CustomTenantConnectionProvider;
import br.com.synchro.hibernate.util.TenantSchema;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class DatabaseMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(DatabaseMultiTenantConnectionProvider.class);

    private HashMap<String, ConnectionProvider> connProviderMap = new HashMap<String, ConnectionProvider>();

    /**
     * 
     */
    public DatabaseMultiTenantConnectionProvider() {
	logger.info("DatabaseMultiTenantConnectionProvider::INITIALIZED");
	final List<String> providerNames = new ArrayList<String>();
	providerNames.add(TenantSchema.TENANCYGERAL.name());
	providerNames.add(TenantSchema.tenancy1.name());
	providerNames.add(TenantSchema.tenancy2.name());

	for (final String providerName : providerNames) {
	    this.connProviderMap.put(providerName, new CustomTenantConnectionProvider(providerName));
	}
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider#getAnyConnectionProvider()
     */
    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
	logger.info("inside DatabaseMultiTenantConnectionProvider::getAnyConnectionProvider");
	return this.connProviderMap.get(TenantSchema.TENANCYGERAL.name());
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider#selectConnectionProvider(java.lang.String)
     */
    @Override
    protected ConnectionProvider selectConnectionProvider(final String pTenantIdentifier) {
	logger.info("DatabaseMultiTenantConnectionProvider::selectConenctionProvider");
	ConnectionProvider connectionProvider = this.connProviderMap.get(pTenantIdentifier);
	if (connectionProvider == null) {
	    connectionProvider = new CustomTenantConnectionProvider(TenantSchema.TENANCYGERAL.name());
	}
	return connectionProvider;
    }

}
