# streamInfoTwitch
Плагин позволяет связать майнкрафт и стрим на твиче, имеет API / The plugin allows you to link Minecraft and Twitch stream, has an API

# ENG
## Compile
1. Download the source code and upload it to Eclipse for example.
2. Add External JARs: [Server Core](https://getbukkit.org/download/craftbukkit) 1.12-1.16.
3. Now you have the code that you can edit!

## Install for Server
1. Download the compiled [StreamInfoTwitch](https://github.com/Dseym/streamInfoTwitch/releases/download/streamInfoTwitch/streamInfoTwitch.jar) and upload it to your server.
2. Have fun!

## Instruction
1. Get your [oauth](https://twitchapps.com/tmi/)
2. In the plugins/streamInfoTwitch/config.yml, write - oauth: oauth:... nick: nick

## API
```
 Instantiate the class - new Twitch(channel)
 channel - channel name #(name) (#dsey)
 Class Methods:
 connect() - join the chat
 disconnect() - disconnect from chat
```

# RUS
## Компиляция
1. Скачайте исходный код и загрузите, к примеру, в Eclipse.
2. Добавьте External Jars в проект: [Серверное ядро](https://getbukkit.org/download/craftbukkit) 1.12-1.16.
3. Теперь у Вас есть код для редактирования!

## Установка на сервер
1. Скачайте скомпилированный [StreamInfoTwitch](https://github.com/Dseym/streamInfoTwitch/releases/download/streamInfoTwitch/streamInfoTwitch.jar) и загрузите на свой сервер.
2. Веселитесь!

## Инструкции
1. Получите свой [oauth](https://twitchapps.com/tmi/)
4. И напишите в plugins/streamInfoTwitch/config.yml - oauth: oauth:... nick: nick

## API
```
 Создайте экземпляр класса - new Twitch(channel)
 channel - название канала #(name) (#dsey)
 Методы класса:
 connect() - присоединиться к чату
 disconnect() - отсоединиться от чата
```

Для свободного использование.