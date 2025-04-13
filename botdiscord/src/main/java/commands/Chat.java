package commands;

import org.jetbrains.annotations.NotNull;

import main.Bot;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Chat extends ListenerAdapter {

    private TextChannel text;
    private HttpRequisitions requisitions = new HttpRequisitions();
    private static Bot bot = new Bot();


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String messages = event.getMessage()
                                 .getContentRaw();

                    
        if (event.getAuthor().isBot() == false) {
            text = event.getChannel().asTextChannel();
            String aiResponse = requisitions.getAIResponse(messages);
            text.sendMessage(aiResponse).queue();
        } 

        

        if (event.getAuthor().isBot() == false && messages.equalsIgnoreCase("!shutdown")) {
            text = event.getChannel().asTextChannel();
            text.sendMessage("O bot está sendo desligado...").queue();
            bot.jda.shutdownNow();
        }
    }
    
    
    
}
