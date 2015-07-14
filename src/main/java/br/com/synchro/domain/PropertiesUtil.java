package br.com.synchro.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author cvs
 * @create Jul 14, 2015
 */
public class PropertiesUtil {

    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    /**
     * @param filename
     * @param property
     * @return property
     */
    public static String getProperty(final String filename, final String property) {
	final Properties properties = loadPropertiesFile(filename);
	if (properties == null) {
	    return null;
	}
	return properties.getProperty(property);
    }

    /**
     * 
     * @param filename
     * @return properties file loaded
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
