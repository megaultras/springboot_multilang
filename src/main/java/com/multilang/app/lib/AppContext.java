package com.multilang.app.lib;

import com.multilang.app.lib.Data;
import com.multilang.app.lib.ActionAlert;
import com.multilang.app.lib.Session;
import com.multilang.app.config.DbConfig;
import com.multilang.app.config.AppConfig;

import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.connection.DBConnectionPool;
import com.wizarius.orm.database.mysql.driver.MysqlDriver;

import org.springframework.stereotype.Component;

@Component
public class AppContext 
{
	private static AppContext instance;
	
	private Data data;
	private ActionAlert alert;
	
	private DbConfig dbConfig;
	private AppConfig appConfig;
	
	private DBConnectionPool connectionPool;

	private Session session;

	private AppContext(DbConfig dbConfig, AppConfig appConfig)
	{
//	Initialize configuration
		this.dbConfig = dbConfig;
		this.appConfig = appConfig;
		
//	Initiate DB connections pool
		System.out.println("Init DB connections pool...");
		this.setConnectionPool();
		System.out.println(" - OK");

//	Init session
		System.out.println("Init session...");
		this.session = new Session();
		System.out.println(" - OK");

//	Init alert
		System.out.println("Set alert...");
		this.setAlert();
		System.out.println(" - OK");
	}
	
	public static void init(DbConfig dbConfig, AppConfig appConfig)
	{
		AppContext.instance = new AppContext(dbConfig, appConfig);
	}
	
	public static AppContext getInstance()
	{
		return AppContext.instance;
	}
	
//	Connections pool ========================
	public DBConnectionPool getConnectionPool()
	{
		return this.connectionPool;
	}
	
	private void setConnectionPool()
	{
		try {
			this.connectionPool = new DBConnectionPool(new MysqlDriver(
				this.dbConfig.getDrivers(),
    			this.dbConfig.getUsername(),
    			this.dbConfig.getPassword(),
    			this.dbConfig.getDatabase(),
	            this.dbConfig.getHost(),
	            this.dbConfig.getPort(),
	            null)
	    	);
	    } catch (DBException ex) {
	    	System.out.println("Error: " + ex.getMessage());
	    	System.exit(0);
	    }
	}
	
//	DB Config ===============================
	public DbConfig getDbConfig()
	{
		return this.dbConfig;
	}
	
	public AppConfig getAppConfig()
	{
		return this.appConfig;
	}
	
//	Data ====================================
	public Data getData()
	{
		return this.data;
	}
	
	public void setData()
	{
		this.data = new Data();
	}

//	Session =================================
	public Session getSession()
	{
		return this.session;
	}

//	Alerts ==================================
	public ActionAlert getAlert()
	{
		return this.alert;
	}
	
	private void setAlert()
	{
		this.alert = new ActionAlert();
	}
}