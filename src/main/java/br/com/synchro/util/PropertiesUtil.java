package br.com.synchro.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author cvs
 * @create Jul 14, 2015
 * 
 *         Utility class to load a properties file and perform operation with it
 */
public class PropertiesUtil {

    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    /**
     * Get specific property
     * 
     * @param filename
     *            of the property file
     * @param property
     *            to be found (key)
     * @return property value
     */
    public static String getProperty(final String filename, final String property) {
	final Properties properties = loadPropertiesFile(filename);
	if (properties == null) {
	    return null;
	}
	return properties.getProperty(property);
    }

    /**
     * Load a properties file
     * 
     * @param filename
     *            of the property file
     * @return properties file loaded if exists, null otherwise
     */
    public static Properties loadPropertiesFile(final String filename) {

	final Properties prop = new Properties();
	InputStream input = null;

	try {
	    input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
	    if (input == null) {
		logger.error("Unable to find " + filename);
		return null;
	    }
	    prop.load(input);
	    return prop;
	} catch (final IOException ex) {
	    logger.error(ex.getMessage());
	    return null;
	} finally {
	    if (input != null) {
		try {
		    input.close();
		} catch (final IOException e) {
		    logger.error(e.getMessage());
		}
	    }
	}
    }
}
