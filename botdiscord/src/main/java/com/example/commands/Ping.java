package com.example.commands;

import org.jetbrains.annotations.NotNull;

import com.example.Main;


import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {

    TextChannel text;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String messages = event.getMessage()
                                 .getContentRaw();
                    
        if (event.getAuthor().isBot() == false && messages.equalsIgnoreCase("!" + "ping") ) {

            text = event.getChannel().asTextChannel();
            text.sendMessage("Esta mensagem foi enviada em: " + Main.jda.getGatewayPing() + "ms").queue();
        } 

        

        if (event.getAuthor().isBot() == false && messages.equalsIgnoreCase("!shutdown")) {
            text = event.getChannel().asTextChannel();
            text.sendMessage("O bot está sendo desligado...").queue();
            Main.jda.shutdownNow();
        }
    }
    
    
    
}
