package ru.dseymo.twitchStream;

public enum Result {
	
	SUCCESS("Success"),
	ALREADY_CONNECTED("The connection has already been made");
	
	String mess;
	
	Result(String mess) {
		
		this.mess = mess;
		
	}
	
}
