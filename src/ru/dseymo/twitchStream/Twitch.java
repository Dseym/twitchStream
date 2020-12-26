package ru.dseymo.twitchStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Twitch {

	private String server = "irc.chat.twitch.tv";
	private int port = 6667;
	private String channel = "";
	private Thread thread;
	private String oauth;
	private String nick;
	private IMessagesListener listener;
	
	public Twitch(String channel, String oauth, String nick, IMessagesListener listener) {
		
		this.channel = channel.toLowerCase();
		this.oauth = oauth;
		this.nick = nick.toLowerCase();
		this.listener = listener;
		
	}
	
	private void sendString(BufferedWriter bw, String str) throws Exception {
		
        bw.write(str + "\n");
        bw.flush();
        
	}
	
	private boolean isChannelFind = false;
	public synchronized Result connect() {
		
		if(thread != null) return Result.ALREADY_CONNECTED;
		
		Runnable run = new Runnable() {
			
			@Override
			public synchronized void run() {
				
				try {
					
					Socket socket = new Socket(server, port);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
					BufferedWriter bwriter = new BufferedWriter(outputStreamWriter);
					
					sendString(bwriter, "PASS " + oauth);
					sendString(bwriter, "NICK " + nick);
					sendString(bwriter, "JOIN #" + channel);
					
					InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
					BufferedReader breader = new BufferedReader(inputStreamReader);
					
					String line = null;
					while ((line = breader.readLine()) != null) {
						
						if(line.indexOf("PRIVMSG #" + channel + " :") > -1)
							listener.onMessage(line.split(":")[1].split("!")[0], line.split("PRIVMSG #" + channel + " :")[1]);

						if(line.indexOf("PING :tmi.twitch.tv") > -1)
							sendString(bwriter, "PONG :tmi.twitch.tv");
						
						if(!isChannelFind && line.indexOf(".tmi.twitch.tv JOIN #" + channel) > -1) {
							
							isChannelFind = true;
							notify();
							
						}
						
					}
					
					socket.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					return;
					
				}
				
			}
			
		};
		
		thread = new Thread(run, "irc" + channel);
		thread.start();
		
		try {wait(2000);} catch (Exception e) {
			
			e.printStackTrace();
			return Result.ERROR;
			
		}
		
		return isChannelFind ? Result.SUCCESS : Result.NOT_FOUND_CHANNEL;
		
	}
	
	public String getChannel() {
		
		return channel;
		
	}
	
	@SuppressWarnings("deprecation")
	public void disconnect() {
		
		if(thread != null)
			thread.stop();
		thread = null;
		
	}
	
	@Override
	public void finalize() {
		
		disconnect();
		
	}
	
}
