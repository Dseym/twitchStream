package ru.dseymo.twitchStream;

public enum Result {
	
	SUCCESS("Success"),
	ERROR("An unknown error has occurred"),
	NOT_FOUND_CHANNEL("Channel not found, check channelName"),
	ALREADY_CONNECTED("The connection has already been made");
	
	private String mess;
	
	Result(String mess) {
		
		this.mess = mess;
		
	}
	
	public String getMessage() {
		
		return mess;
		
	}
	
}
