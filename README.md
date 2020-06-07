# DuplicatePlayerJoin
A mod/plugin for Minecraft using Fabric/Paper that allows duplicate player joins

Downloads: https://github.com/oxygencraft/DuplicatePlayerJoin/releases

When you have joined a server, you can only join once. If you try to join again, it will kick your old client out and log you back
in using the new one. This mod replaces this behaviour to allow you to join multiple times using a single account.

When you join for the first time, the first login will be normal as if no mod was installed. The second time you try to login
with the old client still logged in, instead of kicking the old client out, the server will rename the new login as name2 and
change the UUID to be something different to the original player. When you join again, your name will be name3 and your UUID
will be different from the last two logins. And so on.

I do have a Spigot version but won't be sharing it (mentioned below) but I might try making a plugin (not a patch) for Paper 
(which is a fork of Spigot) because it might be possible based on me reading Paper's javadocs. That is just a guess and I'm 
not sure if it is actually possible or not but if it is and it does work, I will share that too.

**EDIT: The Paper plugin works and it has skins too, you can find it in the releases**

This is a bit of a useless mod/plugin for most players but it can be a useful tool for developers for testing who only have one MC 
account (like me) and doesn't want to buy a new MC account or mess with the launcher to get offline accounts. I have made a Spigot
version of this mod which I use to test the plugin I am currently working on so this mod is useful for some people. I won't be sharing
the Spigot version of this mod because I had to make a patch which gets tedious to update it with every single new build of Spigot.

There may be some bugs and this mod/plugin may be incompatible with some plugins. Unfortunately I will not be fixing this bugs
but if someone find some bugs then please put them in issues. If someone does fix the bug and sends me a PR, I will merge their
work after testing that the bug is fixed.

This was originally built for 1.14.4 but I tested it on the 1.15.2 (the latest version at the time I tested it though it still
the latest version as of writing this) and it still works. Hopefully it will work on 1.16 and beyond but I won't be updating it
if it doesn't work (unless I need it for something then maybe I will update it but that's probably not going to happen).

And yes, this was something that was shoved together with no care whatsoever about the code quality or anything like that. The Fabric 
version uses @Overwrite mixins which is not recommended but I don't care because this is just for fun and all I want it to do is just 
work, no matter what I have to do and how bad the code is. If someone does find this and decides to fix up my mess, I'll happily merge 
their work (but I doubt that is going to happen).

I shared this in the hope of it being useful for people but I don't think many people will find this anyway.
