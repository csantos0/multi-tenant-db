package br.com.synchro.hibernate.tenancy;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import br.com.synchro.domain.PropertiesUtil;
import br.com.synchro.hibernate.connection.CustomTenantConnectionProvider;
import br.com.synchro.hibernate.util.TenantConnectionData;
import br.com.synchro.hibernate.util.TenantDatabase;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class DatabaseMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    private ConnectionProvider defaultConnectionProvider;

    private static Logger logger = Logger.getLogger(DatabaseMultiTenantConnectionProvider.class);

    private HashMap<String, ConnectionProvider> connProviderMap;

    private Properties properties;

    /**
     * 
     */
    public DatabaseMultiTenantConnectionProvider() {
	logger.info("Initializing a new Custom Connnection Tenants Provider");
	this.properties = PropertiesUtil.loadPropertiesFile("multi-tenant.properties");

	if (this.properties != null) {
	    this.connProviderMap = new HashMap<String, ConnectionProvider>();
	    final int totalTenants = Integer.parseInt(this.properties.getProperty("multitenant.tenants.total"));
	    String providerNames = "";

	    for (int i = 1; i <= totalTenants; i++) {
		final TenantConnectionData tcd = new TenantConnectionData();

		tcd.setDriverClass(this.properties.getProperty("multitenant.shared.driver"));
		tcd.setInitialSize(Integer.parseInt(this.properties.getProperty("multitenant.shared.initialSize")));
		tcd.setMaxActive(Integer.parseInt(this.properties.getProperty("multitenant.shared.maxActive")));

		tcd.setTenantName(TenantDatabase.TENANT.name() + i);
		tcd.setUrl(this.properties.getProperty("multitenant.tenant" + i + ".url"));
		tcd.setUsername(this.properties.getProperty("multitenant.tenant" + i + ".user"));
		tcd.setPassword(this.properties.getProperty("multitenant.tenant" + i + ".pass"));

		this.connProviderMap.put(tcd.getTenantName(), new CustomTenantConnectionProvider(tcd));
		providerNames += tcd.getTenantName() + ", ";
	    }

	    logger.info("Connection Provider Tenant Mappings: [" + providerNames.trim() + "]");

	} else {
	    logger.error("Connection Provider failed to be created.");
	}
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider#getAnyConnectionProvider()
     */
    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
	logger.info("Getting Any Connection Provider - Default Tenant");
	return this.getDefaultConnectionProvider();
    }

    /**
     * 
     * @return default connection provider
     */
    private ConnectionProvider getDefaultConnectionProvider() {
	logger.info("Creating a new Default Connection Provider: " + TenantDatabase.MAIN.name());

	if (this.defaultConnectionProvider == null) {
	    final TenantConnectionData tcd = new TenantConnectionData();

	    tcd.setDriverClass(this.properties.getProperty("multitenant.shared.driver"));
	    tcd.setInitialSize(Integer.parseInt(this.properties.getProperty("multitenant.shared.initialSize")));
	    tcd.setMaxActive(Integer.parseInt(this.properties.getProperty("multitenant.shared.maxActive")));

	    tcd.setTenantName(TenantDatabase.MAIN.name());
	    tcd.setUrl(this.properties.getProperty("multitenant.main.url"));
	    tcd.setUsername(this.properties.getProperty("multitenant.main.user"));
	    tcd.setPassword(this.properties.getProperty("multitenant.main.pass"));

	    this.defaultConnectionProvider = new CustomTenantConnectionProvider(tcd);
	}

	return this.defaultConnectionProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider#selectConnectionProvider(java.lang.String)
     */
    @Override
    protected ConnectionProvider selectConnectionProvider(final String pTenantIdentifier) {
	logger.info("Trying to select the correct connection Provider: " + pTenantIdentifier);

	ConnectionProvider connectionProvider = this.connProviderMap.get(pTenantIdentifier);
	if (connectionProvider == null) {
	    logger.info("Connection Provider [" + pTenantIdentifier + "] not found. Default Tenant ["
		    + TenantDatabase.MAIN.name() + "] has been set.");

	    connectionProvider = getDefaultConnectionProvider();
	}

	logger.info("[" + pTenantIdentifier + "] set successfully.");
	return connectionProvider;
    }

}
