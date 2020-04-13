package com.karthik.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * @author Karthik Suresh
 *
 */
public class PropertyFileReader {

	public static Properties getDefaultFileProperties(String fileName) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
			prop.load(input);
			input.close();
		} catch (IOException io) {
			System.out.println("Could not load property file " + fileName);
		}
		return prop;
	}

	public static Properties getCustomProperties() {
		InputStream inputStream = null;
		Properties properties = new Properties();
		Properties defaultProperties = PropertyFileReader.getDefaultFileProperties("application.properties");
		String baseDir = System.getProperty("user.dir");
		String relativeDir = defaultProperties.get("property").toString();
		String applicationFile = defaultProperties.get("applicationproperty").toString();
		try {
			inputStream = new FileInputStream(new File(baseDir + relativeDir + applicationFile));
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Could not load property file " + applicationFile);
		} catch (IOException e) {
			System.out.println("Could not load property file " + applicationFile);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception occured while closing the resource in getCustomProperties");
			}
		}
		return properties;
	}

	public static Properties getKSProperties() {
		InputStream inputStream = null;
		Properties properties = new Properties();
		Properties defaultProperties = PropertyFileReader.getDefaultFileProperties("application.properties");
		String baseDir = System.getProperty("user.dir");
		String relativeDir = defaultProperties.get("property").toString();
		String applicationFile = defaultProperties.get("property1").toString();
		try {
			inputStream = new FileInputStream(new File(baseDir + relativeDir + applicationFile));
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Could not load property file " + applicationFile);
		} catch (IOException e) {
			System.out.println("Could not load property file " + applicationFile);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception occured while closing the resource in getCustomProperties");
			}
		}
		return properties;
	}
}
