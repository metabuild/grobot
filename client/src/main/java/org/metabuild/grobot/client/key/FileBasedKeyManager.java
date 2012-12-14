package org.metabuild.grobot.client.key;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jburbridge
 * @since 11/21/2021
 */
public class FileBasedKeyManager implements KeyManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedKeyManager.class);
	
	private static final String keyDirectoryName = ".grobot";
	private static final String keyFileName = "key.properties";
	private static final File keyFile = getKeyFile();
	
	private Properties keyProperties = new Properties();
	
	public FileBasedKeyManager() {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(keyFile);
			this.keyProperties.load(inputStream);
		}catch (FileNotFoundException fnfe) {
			LOGGER.warn("Could not find the keyFile, creating a new one", fnfe);
			createKeyFile(keyFile);
		} catch (IOException ioe) {
			LOGGER.warn("Could not read from the keyFile", ioe);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				LOGGER.warn("Could not close the input stream", e);
			}
		}
	}
	
	protected FileBasedKeyManager(Properties keyProperties) { 
		this.keyProperties = keyProperties;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.key.KeyManager#loadKey()
	 */
	@Override
	public String loadKey() {
		return keyProperties.getProperty("grobot.client.key");
	}

	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.key.KeyManager#storeKey(java.lang.String)
	 */
	@Override
	public void storeKey(String key) {
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(keyFile);
			keyProperties.store(outputStream, "The Grobot Client's Key");
		} catch (IOException e) {
			LOGGER.warn("Could not write to the keyFile", e);
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				LOGGER.warn("Could not close the outputStream", ioe);
			}
		}
	}
	
	/**
	 * @param keyFile
	 */
	protected boolean createKeyFile(File keyFile) {
		try {
			if (!keyFile.exists()) {
				return keyFile.createNewFile();
			}
		} catch (IOException e) {
			LOGGER.warn("Could not create the keyFile", e);
		}
		return false;
	}
	
	protected static File getKeyFile() {
		return new File(keyDirectoryName + File.separator + keyFileName);
	}
}
