package com.jediminer543.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config
{
	Properties config;
	File configFile;

	public Config(File f) throws IOException
	{
		configFile = f;
		config = new Properties();
		FileInputStream configreader = new FileInputStream(f);
		config.load(configreader);
		configreader.close();
	}

	
	/*
	public Object read(String path) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(config));
		String line = null;
		while ((line = reader.readLine()) != null)
		{
		if (!line.startsWith("#"))
		{
			if (line.replaceAll("\\s+", "").startsWith(path.replaceAll("\\s+", "")))
			{
				for (line.length()
			}
		}
		}
		reader.close();
		return null;
	}
	*/

	public void set(String path, Object value) throws IOException
	{
		if (value instanceof String) {
			config.setProperty(path, (String) value);
		}
		else if (value instanceof Boolean) {
			config.setProperty(path, Boolean.toString((Boolean) value));
		}
		else if (value instanceof Integer) {
			config.setProperty(path, Integer.toString((Integer) value));
		}
		else if (value instanceof Long) {
			config.setProperty(path, Long.toString((Long) value));
		}
		else if (value instanceof Float) {
			config.setProperty(path, Float.toString((Float) value));
		}
		else if (value instanceof Double) {
			config.setProperty(path, Double.toString((Double) value));
		}
		FileOutputStream output = new FileOutputStream(configFile);
		config.store(output, "Main Config File");
	}

	public String readString(String path)
	{
		return config.getProperty(path);
	}

	public boolean readBoolean(String path)
	{
		String value = config.getProperty(path);
		return Boolean.parseBoolean(value);
	}

	public int readInteger(String path)
	{
		String value = config.getProperty(path);
		return Integer.parseInt(value);
	}

	public long readLong(String path)
	{
		String value = config.getProperty(path);
		return Long.parseLong(value);
	}

	public float readFloat(String path)
	{
		String value = config.getProperty(path);
		return Float.parseFloat(value);
	}

	public double readDouble(String path)
	{
		String value = config.getProperty(path);
		return Double.parseDouble(value);
	}

	public boolean keyExists(String path)
	{
		return config.containsKey(path);
	}

}
