package com.Brits.Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.Brits.Constants.Constant;
import com.google.gson.JsonParser;


public class Heper {
	/**
	 To load Values from property files (Utils.properties)
	 * @param data
	 * @return
	 * @throws IOException
	 */
	
	/**
	 FileReader f=new FileReader("util.properties"); // initialize 
	 Properties p= new Properties();
	 p.load(f);
	 p.getproperty(Browser);
	 * @param data
	 * @return
	 * @throws IOException
	 */
	
	public static String  load_Properties(String data) throws IOException { 
		FileReader reader=null;
try {
		 reader = new FileReader("C:\\Users\\Sathiyamoorthi\\Desktop\\rajasekar\\Interview java\\restAssured_learnings\\BDD_Framework\\Session_August_Sphark\\util.properties");
           }
      catch(FileNotFoundException e) {
           e.printStackTrace();
                }
	Properties pp = new Properties();
		try { 
	    pp.load(reader);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return (String)pp.getProperty(data);


}
	
	public static void return_Browser(String browser_type) throws IOException {
	 Constant.browser=	load_Properties(browser_type);
	}
	
	public static void retun_URL(String url) throws IOException {
		Constant.url=load_Properties(url);
	}
	
	/**
	 To Read values from JSON
	 * @param filename
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String read_JSON(String filename,String key) throws IOException, ParseException {
		FileReader reader = null;
		
		try {
		 reader=new FileReader("C:\\Users\\Sathiyamoorthi\\Desktop\\rajasekar\\Interview java\\restAssured_learnings\\BDD_Framework\\Session_August_Sphark\\Toast_messages\\"+filename+".json");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		JSONParser jp = new JSONParser();
		 Object par = jp.parse(reader);
		 JSONObject jo=(JSONObject)par;
	String json_value = (String)jo.get(key);	
	return json_value;
		}
        


}
