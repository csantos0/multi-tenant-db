/**
 * 
 */
package br.com.synchro.hibernate.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.synchro.jsf.FacesUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class TenantResolver {

    private static Logger logger = Logger.getLogger(TenantResolver.class);

    private static Map<String, String> map = new HashMap<String, String>();

    /**
     * @param tenant
     */
    public static void begin(final String tenant) {
	logger.info("Tenant Identifier: " + tenant);
	map.put(FacesUtil.getSessionId(), tenant);
    }

    /**
     * 
     */
    public static void end() {
	map.remove(FacesUtil.getSessionId());
    }

    /**
     * @return current tenancy
     */

    public static String get() {
	final String var1 = map.get(FacesUtil.getSessionId());
	if (var1 == null) {
	    return TenantDatabase.GENERAL.name();
	}
	return var1;
    }
}
