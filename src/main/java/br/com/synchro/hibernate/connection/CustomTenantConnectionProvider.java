package br.com.synchro.hibernate.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import br.com.synchro.hibernate.util.TenantConnectionData;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class CustomTenantConnectionProvider implements ConnectionProvider {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(CustomTenantConnectionProvider.class);

    private final BasicDataSource basicDataSource;

    /**
     * @param tcd
     * 
     */
    public CustomTenantConnectionProvider(final TenantConnectionData tcd) {
	logger.info("Initializing a custom connnection provider for tenant: " + tcd.getTenantName());

	this.basicDataSource = new BasicDataSource();
	basicDataSource.setDriverClassName(tcd.getDriverClass());
	basicDataSource.setInitialSize(tcd.getInitialSize());
	basicDataSource.setMaxActive(tcd.getMaxActive());
	basicDataSource.setUrl(tcd.getUrl());
	basicDataSource.setUsername(tcd.getUsername());
	basicDataSource.setPassword(tcd.getPassword());

	logger.info("Connection Provider set - URL: " + basicDataSource.getUrl() + " Schema: " + basicDataSource.getUsername());
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#closeConnection(java.sql.Connection)
     */
    @Override
    public void closeConnection(final Connection pConn) throws SQLException {
	pConn.close();

    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#getConnection()
     */
    @Override
    public Connection getConnection() throws SQLException {
	logger.info("Getting connection from BasicDataSource Object.");
	return this.basicDataSource.getConnection();
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
     * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#supportsAggressiveRelease()
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
