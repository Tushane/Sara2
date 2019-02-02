/*
 * @author Tushane Mclean 
 */

package com.RTFN3RDSTECH.TimeManager;

import java.util.Date;
import java.util.*;
import java.lang.*;


public class AdminTimeManager {

	private static String _time;
	private static String _currentDate;
	
	
	public AdminTimeManager() {
		
		_time = " ";
		_currentDate = " ";
		
	}
	
	public static void _main() {
		
		Date _tempdate = new Date();
		String date = _tempdate.toString();
		Set_Date(date);
		//System.out.println(_tempdate.toString());
		
		
	}
	
	private static void Set_Date(String temp_date) {
		
		_currentDate = temp_date;
		
	}
	
	
	public static String Get_Date() {
		
		return _currentDate;
		
	}	
	
}
