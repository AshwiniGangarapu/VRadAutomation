package com.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.base.BaseClass;

public class PropertiesFile {
	
	public static void readPropertiesFile() {
		//How to get data from properties file
		//https://www.youtube.com/watch?v=bljA8dpfWeQ
		
		//STEP 1 : Create a object of class Properties class
		Properties prop = new Properties();
		try {
		//STEP 2 : Create a object of class InputStream
		InputStream input = new FileInputStream("C:\\Users\\ashwini.g\\git\\VRadAutomation\\VRadAutomationFramework\\src\\test\\java\\com\\config\\config.properties");

		//STEP 3 : Load Properties file
		prop.load(input);
		
		//STEP 4 : Get values from Properties file
			
		BaseClass.BaseURL=prop.getProperty("BaseURL");
		BaseClass.LoginURL=prop.getProperty("LoginUrl");
		BaseClass.ForgotPasswordURL=prop.getProperty("ForgotPasswordUrl");
		BaseClass.DashboardURL=prop.getProperty("DashboardUrl");

		
		//How to set data to properties file
			
		//STEP 2 : Create a object of class OutputStream
		OutputStream output = new FileOutputStream("C:\\Users\\ashwini.g\\git\\VRadAutomation\\VRadAutomationFramework\\src\\test\\java\\com\\config\\config.properties");

		//STEP 3 : Set values
		prop.setProperty("tester", "Ashwini");//for example.

		//STEP 4 : Store values in properties file
		prop.store(output, null);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
