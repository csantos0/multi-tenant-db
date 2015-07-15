package br.com.synchro.hibernate.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.synchro.hibernate.tenancy.SchemaResolver;
import br.com.synchro.util.FacesUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 * 
 *         Utility class to be used by SchemaResolver as a tenant pool to keep the tenants by session
 * 
 * @see SchemaResolver
 * 
 */
public class TenantResolver {

    private static Logger logger = Logger.getLogger(TenantResolver.class);

    private static Map<String, String> map = new HashMap<String, String>();

    /**
     * Initialize a new tenant in the pool provided by the logged user and identified by a JSessionId
     * 
     * @param tenant
     *            identifier
     */
    public static void begin(final String tenant) {
	logger.info("Tenant Identifier: " + tenant);
	map.put(FacesUtil.getSessionId(), tenant);
    }

    /**
     * Removes a tenant from the tenant pool
     */
    public static void end() {
	map.remove(FacesUtil.getSessionId());
    }

    /**
     * Gets the current tenant based on the JSessionId
     * 
     * @return current tenancy identifier
     */

    public static String get() {
	final String var1 = map.get(FacesUtil.getSessionId());
	if (var1 == null) {
	    return TenantDatabase.MAIN.name();
	}
	return var1;
    }
}
