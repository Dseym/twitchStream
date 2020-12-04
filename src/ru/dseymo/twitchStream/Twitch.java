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
		
		this.channel = channel;
		this.oauth = oauth;
		this.nick = nick;
		this.listener = listener;
		
	}
	
	private void sendString(BufferedWriter bw, String str) throws Exception {
		
        bw.write(str + "\n");
        bw.flush();
        
	}
	
	public Result connect() {
		
		if(thread != null) return Result.ALREADY_CONNECTED;
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
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
						
					}
					
					socket.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					return;
					
				}
				
			}
			
		};
		
		thread = new Thread(runnable, "irc" + channel);
		thread.start();
		
		return Result.SUCCESS;
		
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
	
}
