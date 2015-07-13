package br.com.synchro.hibernate.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import br.com.synchro.hibernate.util.TenantSchema;

/**
 * @author cvs
 * @create Jul 13, 2015
 */
public class CustomTenantConnectionProvider implements ConnectionProvider {

    private static final long serialVersionUID = 1L;

    private final BasicDataSource basicDataSource = new BasicDataSource();

    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final int INITIAL_SIZE = 10;
    private static final int MAX_ACTIVE = 10;

    /**
     * @param tenant
     * 
     */
    public CustomTenantConnectionProvider(final String tenant) {
	basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
	basicDataSource.setInitialSize(INITIAL_SIZE);
	basicDataSource.setMaxActive(MAX_ACTIVE);

	// jdbc:oracle:thin:@localhost:1521:xe
	if (tenant.equals(TenantSchema.tenancy1.name())) {
	    basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    basicDataSource.setUsername("TENANCY1");
	    basicDataSource.setPassword("TENANCY1");
	} else if (tenant.equals(TenantSchema.tenancy2.name())) {
	    basicDataSource.setUrl("jdbc:oracle:thin:@goweb.cbkn7tylbre3.sa-east-1.rds.amazonaws.com:1521:goweb");
	    basicDataSource.setUsername("FISCI_GOWEB");
	    basicDataSource.setPassword("FISCI_GOWEB129");
	} else {
	    basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    basicDataSource.setUsername("TENANCYGERAL");
	    basicDataSource.setPassword("TENANCYGERAL");
	}
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
