package com.karthik.createkeystores;

import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.Properties;

import com.karthik.property.PropertyFileReader;

/**
 * @author Karthik Suresh
 *
 */
public class GenerateKeyStores {

	public static boolean generateKeyStores(String pwd) {
		boolean icCreated = false;
		String baseDir = System.getProperty("user.dir");
		Properties properties = PropertyFileReader.getDefaultFileProperties("application.properties");
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			char[] pwdArray = pwd.toCharArray();
			ks.load(null, pwdArray);

			// First Key Store - SSC
			generateKeyPair(baseDir + properties.get("store").toString() + "ki.p12");

			// Second Key Store
			FileOutputStream fos2 = new FileOutputStream(baseDir + properties.get("store").toString() + "ki.p12");
			ks.store(fos2, pwdArray);

			icCreated = true;
		} catch (Exception e) {
			System.out.println("Could not create a Key stores :" + e.getMessage());
		}
		return icCreated;
	}

	// Generate keypair
	public static void generateKeyPair(String filePath) {
		execute(filePath);
	}

	// Execute the commands
	public static void execute(String command) {
		try {
			sun.security.tools.keytool.Main.main(parse(command));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Parse command
	private static String[] parse(String filePath) {
		return new String[] { "-genkeypair", "-keyalg", "RSA", "-keysize", "2048", "-storetype", "PKCS12", "-keystore",
				filePath, "-validity", "3650" };
	}

}
