package br.com.synchro.hibernate.tenancy;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import br.com.synchro.hibernate.connection.CustomTenantConnectionProvider;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class MultiTenantConnectionProviderDatabasePool implements MultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    private final ConnectionProvider connectionProvider = new CustomTenantConnectionProvider(
	    CustomCurrentTenantIdentifierResolver.DEFULT_TENANT_ID);

    private static Logger logger = Logger.getLogger(MultiTenantConnectionProviderDatabasePool.class);

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#getAnyConnection()
     */
    @Override
    public Connection getAnyConnection() throws SQLException {
	logger.info("inside MultiTenantConnectionProviderDatabasePool::getAnyConnection");
	return this.connectionProvider.getConnection();
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#getConnection(java.lang.String)
     */
    @Override
    public Connection getConnection(final String pTenantIdentifier) throws SQLException {
	final Connection connection = getAnyConnection();
	try {
	    connection.createStatement().execute("USE " + pTenantIdentifier);
	} catch (final SQLException e) {
	    throw new HibernateException(
		    "MultiTenantConnectionProviderDatabasePool::Could not alter JDBC connection to specified schema ["
			    + pTenantIdentifier + "]", e);
	}
	return connection;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.service.spi.Wrapped#isUnwrappableAs(java.lang.Class)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(final Class pUnwrapType) {
	return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#releaseAnyConnection(java.sql.Connection)
     */
    @Override
    public void releaseAnyConnection(final Connection pConnection) throws SQLException {
	this.connectionProvider.closeConnection(pConnection);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#releaseConnection(java.lang.String, java.sql.Connection)
     */
    @Override
    public void releaseConnection(final String pTenantIdentifier, final Connection pConnection) throws SQLException {
	try {
	    // check
	    pConnection.createStatement().execute("USE default_db");
	} catch (final SQLException e) {
	    throw new HibernateException(
		    "MultiTenantConnectionProviderDatabasePool::Could not alter JDBC connection to specified schema ["
			    + pTenantIdentifier + "]", e);
	}
	connectionProvider.closeConnection(pConnection);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#supportsAggressiveRelease()
     */
    @Override
    public boolean supportsAggressiveRelease() {
	return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.service.spi.Wrapped#unwrap(java.lang.Class)
     */
    @Override
    public <T> T unwrap(final Class<T> pUnwrapType) {
	return null;
    }

}
