package system;
/**
 * Config.java
 * Configuration file
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTORING PENDING
 * TODO add configuration file reader
 * TODO check overlap with CoreComponents
 * 
 */


public class Config {
	/* Pre TDB
	public static String M_DB_URL="jdbc:mysql://localhost/osm2";
	public static String M_DB_USER="osm";
	public static String M_DB_PASSWD="osmp";
	public static String M_DB="MySQL";
	public static String M_DBDRIVER_CLASS="com.mysql.jdbc.Driver";
	*/
	public static String thisResource="http://www.bootstrep.eu/system#this";
	public static String isInternalTypeProperty="http://www.bootstrep.eu/system#isInternalType";
	
	
	public static String versionInfo="v.0.2.0";
	public static String baseDir=".ontoServer";
	
	
	public static int socketPort=9010; // terminal socket connection
	public static String defaultTdbName="BeatTDB";
	public static String defaultJosekiPort="8030";
	
	public static int defaultVerboseLevel=1;
		
}
