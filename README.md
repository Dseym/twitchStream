# twitchStream
Плагин-библиотека позволяет связать майнкрафт и стрим на твиче / The plugin-lib allows you to link Minecraft and Twitch stream

# ENG
## Compile
1. Download the source code and upload it to Eclipse for example.
2. Add External JARs: [Server Core](https://getbukkit.org/download/craftbukkit) 1.8-1.16.
3. Now you have the code that you can edit!

## Install for Server
1. Download the compiled [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar) and upload it to your server.
2. Have fun!

## Using
Get your [oauth](https://twitchapps.com/tmi/)
```java
IMessagesListener listener = new IMessagesListener() {
			
	@Override
	public void onMessage(String nick, String message) {
		
		System.out.println(nick + ": " + message);
		
	}
	
};

Twitch twitch = new Twitch(channel, oauth, yourNick, listener);

twitch.connect();
```

# RUS
## Компиляция
1. Скачайте исходный код и загрузите, к примеру, в Eclipse.
2. Добавьте External Jars в проект: [Серверное ядро](https://getbukkit.org/download/craftbukkit) 1.8-1.16.
3. Теперь у Вас есть код для редактирования!

## Установка на сервер
1. Скачайте скомпилированный [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar) и загрузите на свой сервер.
2. Веселитесь!

## API
Получите свой [oauth](https://twitchapps.com/tmi/)
```java
IMessagesListener listener = new IMessagesListener() {
			
	@Override
	public void onMessage(String nick, String message) {
		
		System.out.println(nick + ": " + message);
		
	}
	
};

Twitch twitch = new Twitch(channel, oauth, yourNick, listener);

twitch.connect();
```

Для свободного использование.