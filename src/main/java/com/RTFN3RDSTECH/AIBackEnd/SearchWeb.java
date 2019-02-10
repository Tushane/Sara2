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
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class SearchWeb {


	private static int size = 100;
	private int TotalExplicitFound = 0;
	//arrays that is going to hold the search result information
	private static String[] _title = new String[size];
	private static String[] _url = new String[size];
	private static int i = 0;
	private static String[] explicits = {"porn", "sex", "fuck", "shit"};
	private static String GoogleUrl =  "http://www.google.com/search?q=";
	//private static String YoutubeUrl = "https://www.youtube.com/search?q=";

	/*public static void main(String[] args) throws IOException, URISyntaxException {
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

	public boolean LetSearch(String _keyword) {

		try {
			boolean search = KeywordCheck(_keyword);
			if(search == true) {

				Element doc;
				doc = webSearch(_keyword);

				for (Element result : doc.select("h3.r a")) {

					_title[i] = result.text();
					_url[i] = result.attr("abs:href");
					System.out.println(_title[i]);
					System.out.println(_url[i]);
					i++;

				}
				return true;
			}
		}catch(Exception e) {

			System.out.print(e);

		}

		return false;

	}

	private Element webSearch(String _keyword){
		try {
			Element doc = Jsoup.connect(GoogleUrl
					+ _keyword).userAgent("Chrome/69.0.3497.100").get();

			return doc;
		}catch(IOException e) {

			System.out.print(e);

		}
		return null;
	}

	//function that checks the keyword for any explicit language
	private  boolean KeywordCheck(String _keyword){
		boolean shouldSearch;

		String[] wordToCheck = _keyword.split(" ");
		int x = wordToCheck.length;
		int o = explicits.length;


		for(int a = 0; a < x; a++){
			for(int z = 0; z < o; z++) {
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

}
