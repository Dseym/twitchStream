package streamInfoTwitch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Twitch {

	protected static String oauth = "";
	protected static String nick = "";
	private String server = "irc.chat.twitch.tv";
	private int port = 6667;
	@SuppressWarnings("unused")
	private String channel = "";
	private Map<String, String> messages = new HashMap<String, String>();
	private Thread thread;
	
	public Twitch(String channel) {
		
		this.channel = channel;
		
		if(oauth == "")
			Main.plugin.getLogger().warning("ERROR: Укажите oauth в конфиге");
		if(nick == "")
			Main.plugin.getLogger().warning("ERROR: Укажите nick в конфиге");
		
		
		Runnable runnable = new Runnable() {
			
			@SuppressWarnings("resource")
			@Override
			public void run() {
				
				try {
					
					Socket socket = new Socket(server, port);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
					BufferedWriter bwriter = new BufferedWriter(outputStreamWriter);
					
					sendString(bwriter, "PASS " + oauth);
					sendString(bwriter, "NICK " + nick);
					sendString(bwriter, "JOIN " + channel);
					
					InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
					BufferedReader breader = new BufferedReader(inputStreamReader);
					
					String line = null;
					while ((line = breader.readLine()) != null) {
						
						if(line.indexOf("PRIVMSG " + channel + " :") > -1)
							messages.put(line.split(":")[1].split("!")[0], line.split("PRIVMSG " + channel + " :")[1]);

						if(line.indexOf("PING :tmi.twitch.tv") > -1)
							sendString(bwriter, "PONG :tmi.twitch.tv");
						
					}
					
				} catch (Exception e) {}
				
			}
			
		};
		
		thread = new Thread(runnable, "irc" + channel);
		
	}
	
	private void sendString(BufferedWriter bw, String str) throws Exception {
		
        bw.write(str + "\n");
        bw.flush();
        
	}
	
	public void connect() {
		
		thread.start();
		
	}
	
	@SuppressWarnings("deprecation")
	public void disconnect() {
		
		thread.stop();
		
	}
	
	public Map<String, String> getMessages() {
		
		Map<String, String> _messages = messages;
		
		messages = new HashMap<String, String>();
		
		return _messages;
		
	}
	
}
