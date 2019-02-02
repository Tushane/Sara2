package AIBackEnd;

import java.lang.module.Configuration;
import java.util.Scanner;

import org.xeustechnologies.googleapi.spelling.Language;
import org.xeustechnologies.googleapi.spelling.SpellCheckException;
import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellCorrection;
import org.xeustechnologies.googleapi.spelling.SpellRequest;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

public class spellchecking {
		
	
	public static void main(String[] args) {
		
	try {
			SpellChecker newChecker = new SpellChecker();
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a Wrong Word: ");
			String word = in.next();
			in.close();
			try {
				SpellResponse feedback = newChecker.check(word);
				for(SpellCorrection sc : feedback.getCorrections() ){
						
					System.out.print(sc.getValue());
					
				}
				
				//	SpellCheck(word);
					
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}catch(SpellCheckException e){
			
			e.printStackTrace();
			
		}
		
	}
	
	/*public static void SpellCheck(String arg) {
		
		SpellChecker newChecker = new SpellChecker();
		newChecker.setOverHttps(true);
		
		newChecker.setLanguage(Language.ENGLISH);
		
		SpellRequest SpellCheck = new SpellRequest();
		
		SpellCheck.setText(arg);
		//SpellCheck.setIgnoreDuplicates(1);
		
		SpellResponse feedback = newChecker.check(arg);
		
		for(SpellCorrection sc : feedback.getCorrections())
			System.out.println(sc.getValue());
		
	}*/
	
}
