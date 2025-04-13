package com.example;

import java.util.EnumSet;

import com.example.commands.Ping;
import com.example.commands.TokenBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    
    public static JDA jda;
    private static TokenBot tok;
    public static void main(String[] args) {

            jda = JDABuilder.create(tok.getToken(),
                EnumSet.allOf(GatewayIntent.class)).build();

            jda.addEventListener(new Ping());
    }
}