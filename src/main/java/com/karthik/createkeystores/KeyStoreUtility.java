package com.karthik.createkeystores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author Karthik Suresh
 *
 */
public class KeyStoreUtility {

//	public static void main(String args[]) {
//		InputStream inputStream = null;
//		String pass = null;
//		String pass1 = null;
//		String password = "@?keystore?@";
//		String baseDir = System.getProperty("user.dir");
//		Properties properties = PropertyFileReader.getDefaultFileProperties("application.properties");
//		String relativeDir = properties.getProperty("store").toString();
//		String applicationFile = properties.getProperty("propertykeystore").toString();
//		try {
//			KeyStore ks = KeyStore.getInstance("PKCS12");
//			inputStream = new FileInputStream(new File(baseDir + relativeDir + applicationFile));
//			ks.load(inputStream, password.toCharArray());
//			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");
//			
//			// dbpwd
//			SecretKeyEntry ske1 = (SecretKeyEntry) ks.getEntry("dbpwd", new PasswordProtection(password.toCharArray()));
//			PBEKeySpec keySpec;
//			keySpec = (PBEKeySpec) factory.getKeySpec(ske1.getSecretKey(), PBEKeySpec.class);
//			pass1 = new String(keySpec.getPassword());
//			System.out.println(pass1);
//			
//			// dbuser
//			SecretKeyEntry ske = (SecretKeyEntry) ks.getEntry("dbuser", new PasswordProtection(password.toCharArray()));
//			PBEKeySpec keySpec1;
//			keySpec1 = (PBEKeySpec) factory.getKeySpec(ske.getSecretKey(), PBEKeySpec.class);
//			pass = new String(keySpec1.getPassword());
//			System.out.println(pass);
//			
//			inputStream.close();
//		} catch (Exception e) {
//			System.out.println("Exceptin occured while retrieveing the value from keystore");
//		} 
//	}

	public static boolean importNewKeyStoreEntry(String entryAlias, String entryPassword, String keyStoreLocation,
			String keyStorePassword) throws Exception {
		InputStream inputStream = null;
		boolean isImported = false;
		FileOutputStream fos = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");
			SecretKey generatedSecret = factory.generateSecret(new PBEKeySpec(entryPassword.toCharArray()));

			KeyStore ks = KeyStore.getInstance("PKCS12");
			inputStream = new FileInputStream(new File(keyStoreLocation));
			ks.load(inputStream, keyStorePassword.toCharArray());

			KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());

			ks.setEntry(entryAlias, new KeyStore.SecretKeyEntry(generatedSecret), keyStorePP);

			fos = new java.io.FileOutputStream(keyStoreLocation);
			ks.store(fos, keyStorePassword.toCharArray());
			isImported = true;
		} catch (Exception ex) {
			System.out.println("Exceptin occured while importing the entry into the keystore :" + ex.getMessage());
		} finally {
			if (null != fos) {
				fos.close();
				inputStream.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return isImported;
	}
}
