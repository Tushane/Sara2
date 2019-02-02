/*
 * @author Tushane Mclean 
 */

package  com.RTFN3RDSTECH.sara2;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.Route.Applications;
import net.dv8tion.jda.core.utils.cache.SnowflakeCacheView;
import com.RTFN3RDSTECH.TimeManager.AdminTimeManager;
import net.dv8tion.jda.core.events.*;
import net.dv8tion.jda.client.*;
import java.lang.*;
import java.lang.String;
import com.RTFN3RDSTECH.AIBackEnd.SearchWeb;

public class botEventHandler extends ListenerAdapter {

		private String SenStarter;
		private static String prevMesHistory;
		private static String prevAuthor;
		private static Boolean AssistOnline;
		private static Boolean Uninterrupt;
		private static String AdminName;
		private static Boolean error;
		private static SearchWeb _sw;
		private static Boolean boot;
		private static String _mes;
		private String mes;

		//default constructor
		public botEventHandler(){
			SenStarter = "Hi ";
			prevMesHistory = "";
			prevAuthor = "";
			AssistOnline = false;
			Uninterrupt = true;
			AdminName = "Tushane";
			error = false;
			_sw = new SearchWeb();
			boot = true;
		}

		//function that miontors whether or not an message has been sent in the server
		public void onMessageReceived(MessageReceivedEvent temp) {
			if(temp.getAuthor().isBot()||temp.getAuthor().isFake()) {
				
				return;
				
			}else if(!temp.getAuthor().isBot() && !temp.getAuthor().isFake()) {
			
					Message _con = temp.getMessage();
					String _temp = _con.getContentRaw();
					MessageChannel location = _con.getChannel();
					String _user = temp.getMember().getNickname();
					
					System.out.print(_user + ": " +_temp + "\n");
					
					if((_temp.equalsIgnoreCase("Goodbye Sara")||_temp.equalsIgnoreCase("good bye sara"))&&!_user.startsWith(AdminName)
							&& AssistOnline == true) {
						
						AssistOnline = false;
						location.sendMessage("Goodbye " + _user + " :kissing:").queue();
						
					}else if((_temp.equalsIgnoreCase("Goodbye Sara")|| 
							 _temp.equalsIgnoreCase("good bye Sara"))
							&& _user.startsWith(AdminName) && AssistOnline == true) {
						
						AssistOnline = false;
						location.sendMessage("Goodbye Admin" + " :kissing:, I will Miss You. :blush:").queue();
						
					}
					
					if (AssistOnline == true && !_user.startsWith(prevAuthor) 
							&& _temp.equalsIgnoreCase("Hi Sara")) {
						
						location.sendMessage(":thinking: " + _user + "," + " I am currently speaking to " 
									+ prevAuthor + ". So Give me a Minute and I will get to you.").queue();
						Uninterrupt = false;
						location.sendMessage("So " + prevAuthor + " Did You Want Anything else?").queue();
						
					}else if(AssistOnline == false) {
						
						AssistOnline = StandardResponse(_temp, _user, location);
						
					}else if(AssistOnline == true &&  _user.startsWith(prevAuthor)) {
							
						Uninterrupt = true;
							 error = false;
							 HumanLikeResponse(_temp, _user, location);	
							 
					}
					
					if (AssistOnline == true && Uninterrupt == true && error == false) {
							
						SetprevConDetials(_temp, _user);
						
					}
					if(boot == false) {
				         setMes(":wave: Hey @everyone, Remember that tonight is GAME NIGHT!!!!!!! AND DON't "
				         		+ "YOU MISS IT. Also "
								+ "the game that will played is Paladins. So anyone that wants to join can "
								+ "Hope you all can join :nerd:");
						location.sendMessage(getMes()).queue();
						
						boot = true;
						
					}
			}
		}
		
	private Boolean StandardResponse(String _mes, String _user, MessageChannel location) {
		
			if (_mes.startsWith("Hi Sara")||_mes.startsWith("hi sara")
					 && !_user.startsWith(AdminName)) {
				
				location.sendMessage(":wave:" + SenStarter + _user + ", :nerd: I am your Assistant  and I am here to help"
						+ " you all. Along with Tushane to make your experience here"
						+ " AWESOME!!!!!!!").queue();
				return true;
				
			}else if (_mes.equalsIgnoreCase("Hi Sara") && _user.startsWith(AdminName)) {
				
					location.sendMessage(":heartbeat: :heartpulse: Hi Admin, Oh I have missed you :kissing_heart: ").queue();
					return true;
			}else if(_mes.equalsIgnoreCase("what is tushane steam username?")) {
				
				location.sendMessage("His gamer tag is Gamelanderforever :video_game:").queue();
				
			}else if(_mes.equalsIgnoreCase("hi bot")) {
				
				location.sendMessage(_user + " :angry: My Name is not bot, it's SARA.").queue();
				
			}else if(_mes.equalsIgnoreCase("Sara notice me!")){
				
				location.sendMessage("Hi there........   ").queue();
				location.sendMessage("Sorry you name is no where to found....").queue();
				location.sendTyping().complete();
				location.sendMessage(":kissing: It's not you..... I am just not interested in what your selling").complete();
				
			}
			return false;
	}
	
	private void SetprevConDetials(String _temp, String _temp2) {
		prevMesHistory = _temp;
		prevAuthor = _temp2;
	}
	
		private void HumanLikeResponse(String _mes, String _user, MessageChannel location) {
			
			if(_mes.startsWith("What Overlord Business?") && prevMesHistory.startsWith("Where is Tushane?")
					&& !_user.startsWith(AdminName)){
				
				location.sendMessage("Hmm... You Know.. I am not allowed to Say :laughing:").queue();
				error = true;
				 
			}else if (_mes.startsWith("What is Overlord Business?") && (prevMesHistory.startsWith("Where is Tushane?") ||
					prevMesHistory.startsWith("What Overlord Business?"))) {
				
				location.sendMessage(":nerd: Overlord Business is the Business of None of "
						+ _user + " Business :laughing:").queue();
				error = false;
				
			}else if((_mes.startsWith("Where is Tushane?"))&& !_user.startsWith(AdminName)) { 
				
					location.sendMessage(":smirk: " + _user + "," + " Tushane is busy right now. "
										+ "Doing Overlord Business.").queue();
					error = false;

			}else if(_mes.startsWith(prevMesHistory) && !_user.startsWith(AdminName)) {
				
				location.sendMessage(":face_palm: " + _user + ", You Said This Already.").queue();
				error = false;
				
			}else if(_mes.startsWith(prevMesHistory) && _user.startsWith(AdminName)) {
				
				location.sendMessage("@Admin, :robot: I know you know better my :nerd:").queue();
				error = false;
			
		}else if(_mes.startsWith("Yes")|| _mes.startsWith("Yes I do")||
					_mes.startsWith("Yes i do")||_mes.startsWith("yes i do")||_mes.startsWith("yes i do")||_mes.startsWith("yes")){
				
					location.sendMessage(":blush: Ok " + _user + " .... What else can I help you with?").queue();		
					error = false;
					
			}else if(_mes.startsWith("No")||_mes.startsWith("no")||_mes.startsWith("NO")||_mes.startsWith("nO")){
					
			 	location.sendMessage("OK " + _user + ", Hope you swing by soon! :hugging:").queue();
			 	AssistOnline = false;
			
			}else if(_mes.startsWith("What time is it for Tushane?") || _mes.startsWith("what time is it for tushane?")
					|| _mes.startsWith("what time is it for Admin?") || _mes.startsWith("what time is it for admin?")
							||_mes.startsWith("What time is it where tushane lives?")
									||_mes.startsWith("What time is it where Tushane lives?")) {
				
							AdminTimeManager._main();
							String curdate = AdminTimeManager.Get_Date();
							location.sendMessage("Here you go :nerd: \n" + curdate).queue();
							error = false;
				
			
		}else if (_mes.startsWith("Google")) {
			
			String keyword = RemovePrefix(_mes);
			Boolean SearchFound = _sw.LetSearch(keyword);
			location.sendTyping();
			int c = _sw.GetTotalExplicitFound();
			 if(SearchFound == true && c <= 0) {
				 String[] title = _sw.GetTitle();
				 String[] url = _sw.GetURL();
				 int i = 3;
			  
				 for(int m = 0; m<i; m++) {
					 location.sendMessage(title[m] + ": " + url[m]).queue();
					 System.out.println(title[m] + ": " + url[m]);
					 location.sendTyping();
				 }
				 
				 location.sendMessage("This was everything I have Found. Hope it was helpful.").queue();
				 
			 }else if(SearchFound == false) {
				 
				 location.sendMessage("I am Sorry :sad:, I was unable to complete your search.").queue();
				 
			 }else{
				 location.sendMessage("because it contains explicit language.").queue();
			 }
			error = false;
			
		}else if(_mes.contains("When is the Game Session?")) {
			
			location.sendMessage( _user + ", I wish I could answer but Tushane has not mention "
					+ "it to me yet but I will ask him the next him and I talk").queue();
			error = false;
			
		}else if(_mes.equalsIgnoreCase("What is Tushane steam username?")) {
			
			location.sendMessage("His gamer tag is Gamelanderforever :video_game:").queue();
			error = false;
			
		}else if(_mes.equalsIgnoreCase("What is my name?")){

				String[] m = _user.split("-");
			location.sendMessage("Your Name within the server is " + m[0]).queue();
		
		}else {
				
				location.sendMessage("OH! DEAR!:fearful:, I am sorry but I don't understand"
						+ "what your saying. Please, :cry: inform Tushane."
						+ " He will know what to do. :crying_cat_face: ").queue();
					error = true;
			}
			
		}
		
		private static String Get_Name(String _temp_name) {
			
			_temp_name.split("-");
			return _temp_name;
			
		}
		
		private String RemovePrefix(String _temp) {
			String prefix = "Google";
			String[] new_word = _temp.split(prefix);
			
			final String word = new_word[1];
			
			return word;
			
		}
		
		private void SetAlerts() {
			
			
			
		}

		public static String getMes() {
			return _mes;
		}

		public static void setMes(String mes) {
			_mes = mes;
		}
	
}


	
