/*
 * @author Tushane Mclean 
 */
package com.RTFN3RDSTECH.sara2;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantDriver {
		private static String _token = "NTAzMjAwOTYwNzcyODMzMjgx.DvcQbA.E8c-QJhqgEbDd1cPHm9UMM8JR78";
		private static botEventHandler _bot;

	/*	public static void main(String[] args){
			try {
				 
				//bring the bot online
				JDA api = new JDABuilder(AccountType.BOT).setToken(_token).buildAsync();
				_bot = new botEventHandler();
				api.addEventListener(_bot);
				
			}catch(Exception e){
				System.out.println(e);
			} 
			
			//String _newMember = newMemberAssist
	}*/
	
	//function to set the token that is going to be us
	public void Set_token(String temp) {
		_token = temp;
	}

	@RequestMapping("/")
	//function to bring the bot online 
	public void BringOnline() {
		try {
			 
			//bring the bot online
			JDA api = new JDABuilder(AccountType.BOT).setToken(_token).buildBlocking();
			api.addEventListener(new botEventHandler());
			
		}catch(Exception e){
			System.out.println(e);
		} 
	}
}
