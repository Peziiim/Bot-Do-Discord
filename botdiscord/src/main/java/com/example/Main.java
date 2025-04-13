package com.example;

import java.util.EnumSet;

import com.example.commands.Ping;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    
    public static JDA jda;
    public static void main(String[] args) {

            jda = JDABuilder.create("MTM2MDc0NjY5NDI4Njc3NDMzNA.GgIeGZ.Ytosa6A-84WcWpNMcfKn3cOQpWA27Q9TWZEFG8",
                EnumSet.allOf(GatewayIntent.class)).build();

            jda.addEventListener(new Ping());
    }
}