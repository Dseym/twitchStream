package ru.dseymo.twitchStream;

public interface IMessagesListener {
	
	public void onMessage(String nick, String message);
	
}
