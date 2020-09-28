/* 
 * Colors Sports Club MDM 登入頁面 Controller
 * @author 黃郁授,吳彥儒
 * @date 2020/09/15
 */
package com.wj.clubmdm.weber.util;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class DBConnectionFactory {
	private Logger logger = Logger.getLogger(DBConnectionFactory.class);
	
	public Connection getSQLiteCon(String path, String dbName) throws Exception {
		/*
		 * 範例1(絕對路徑)
		 * path = "C:/xxxx/"
		 * dbname = "Club.org"
		 * 
		 * 範例2(相對路徑)
		 * path = ""
		 * dbname = "Club.org"
		 */
		path = "/Users/hectorwu/skating_work/ColorSportsClubMDM/原始碼/ColorSportsClubMDM_20200923/";
		dbName = "Club.dll";
		return DriverManager.getConnection("jdbc:sqlite:" + path + dbName);
	}
}
