package main;
import java.util.EnumSet;

import commands.Chat;
import commands.TokenBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {

    public static JDA jda;
    private static TokenBot tok = new TokenBot();
    public static void main(String[] args) {
              
        
           jda = JDABuilder.create(tok.getToken(),
            EnumSet.allOf(GatewayIntent.class)).build();

                jda.addEventListener(new Chat());

    }
}