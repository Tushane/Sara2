package com.RTFN3RDSTECH.ManagerCore;

import net.dv8tion.jda.core.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.guild.GenericGuildEvent;
import net.dv8tion.jda.client.*;

public class newMemberAssist extends GenericGuildMemberEvent{

	private Member _member;
	
	public newMemberAssist(JDA api, long responseNumber, Member member) {
		
		super(api, responseNumber, member);
		// TODO Auto-generated constructor stub
	}
	
		
	public String onMemberJoin() {
		
		Member _member = super.getMember();
		return _member.getAsMention();
		
	}
	
	public void SetAlert() {
		
		MessageChannel message = guild.getTextChannelById("503322310397132810");
		message.sendTyping();
		message.sendMessage(":wave: Hi @everyone, Game Session will be on Friday November 2, 2018 and "
				+ "the game that will played is Paladins. So anyone that wants to join can "
				+ "Hope you all can join :nerd:").queue();
		
	}
	

}
