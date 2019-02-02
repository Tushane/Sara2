package com.RTFN3RDSTECH.AIBackEnd;

import java.awt.Desktop;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.lang.String;

import org.jsoup.Jsoup;   
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;

import java.awt.*;

public class SearchWeb {
	
		private static int size = 100;
	//arrays that is going to hold the search result information
			private static String[] _title = new String[size];
			private String[] _url = new String[size];
			private static int i = 0;
			private String[] explicits = {"porn", "sex", "fuck", "shit"};
			private boolean shouldSearch = false;
			int TotalExplicitFound = 0;
			
			private static String GoogleUrl = "http://www.google.com/search?q=";
			//private static String YoutubeUrl = "https://www.youtube.com/search?q=";
	
/*	public static void main(String[] args) throws IOException, URISyntaxException {
		Scanner in = new Scanner(System.in);
		//Boolean isbrowserOpen = Open_Browser();
		
		 try {
			 		 
				 System.out.println("Enter What You want to Search for: ");
				 String keyword = in.nextLine();
				 LetSearch(keyword);
			
		}catch(Exception e) {
			 
			 System.out.println(e);
			 
		 }
		 in.close();
	}*/
	
	public Boolean LetSearch(String _keyword) {
		
		try {
				boolean search = KeywordCheck(_keyword);
			 if(search == true) {
				 Document doc = Jsoup.connect(GoogleUrl
						 + _keyword).userAgent("Chrome/69.0.3497.100").get();


				 for (Element result : doc.select("h3.r a")) {

					 _title[i] = result.text();
					 _url[i] = result.attr("abs:href");
					 i++;

				 }
				 return true;
			 }
		}catch(ConnectException e) {
			
			System.out.print(e);
			
		}catch(Exception e) {
			
			System.out.print(e);
			
		}
		
		return false;
		
	}

	private boolean KeywordCheck(String _keyword){

		String[] wordToCheck = _keyword.split(" ");
		int x = wordToCheck.length;
		int o = explicits.length;


		for(int a = 0; a <= x; a++){
			for(int z = 0; z <= o; z++) {
				if (wordToCheck[a].equalsIgnoreCase(explicits[z])){
					TotalExplicitFound++;
				}
			}
		}

		shouldSearch = TotalExplicitFound <= 0;

		return shouldSearch;

	}
	
	public String[] GetTitle() {
		
		return _title;
		
	}

	public int GetTotalExplicitFound(){
		return TotalExplicitFound;
	}
	
	public String[] GetURL() {
		
		return _url;
		
	}
	
	public int GetCount() {
		
		return i;
		
	}
	
	/*private static Boolean Open_Browser() {
		
		String default_browser = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
		String website = "http:/www.google.com";
		
		try {
			
			String[] headToWeb = {default_browser, website};
			Runtime.getRuntime().exec(headToWeb);
			return true;
			
		}catch(Exception  e) {
			
			System.out.println(e);
			
		}
		
		return false;
		
	}*/

}
