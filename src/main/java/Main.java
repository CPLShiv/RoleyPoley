import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static String token;

    private static final String LOUDSPEAKER = "\uD83D\uDCE2";
    private static final String CALENDAR = "\uD83D\uDDD3\uFE0F";
    private static final String POPPER = "\uD83C\uDF89";
    private static final String MEDAL = "\uD83C\uDFC5";
    private static final String PAINTBRUSH = "\uD83D\uDD8C\uFE0F";
    private static final String DESKTOP = "\uD83D\uDDA5\uFE0F";
    private static final String CHAIR = "\uD83E\uDE91";
    private static final String CAMERA = "\uD83D\uDCF7";
    private static final String PIANO = "\uD83C\uDFB9";
    private static final String HAND = "\u270D\uFE0F";
    private static final String KEYBOARD = "\u2328\uFE0F";
    private static final String BULB = "\uD83D\uDCA1";

    public static void main(String[] args) {
        try {
            File credFile = new File("cred.txt");
            Scanner in = new Scanner(credFile);
            token = in.nextLine();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        DiscordApi api = new DiscordApiBuilder().setToken(token).setIntents(Intent.GUILDS, Intent.GUILD_MEMBERS, Intent.GUILD_MESSAGES, Intent.GUILD_MESSAGE_REACTIONS).login().join();
        api.updateActivity(ActivityType.WATCHING, "you type ~info and get some roles!");

        api.addMessageCreateListener(event -> {
            String message = event.getMessageContent();
            TextChannel channel = event.getChannel();
            if (message.equalsIgnoreCase("~info")) {
                infoEmbed(channel, api.getYourself().getAvatar());
            }
        });

        Map<String, Long> namesMap = new HashMap<>();

        // SUB ROLES
        namesMap.put(LOUDSPEAKER, 736628175660122152L);
        namesMap.put(CALENDAR, 736389511310999605L);
        namesMap.put(POPPER, 760210606019182642L);
        namesMap.put(MEDAL, 760210322987548682L);

        // IDENTIFICATION ROLES
        namesMap.put(PAINTBRUSH, 690265991275741243L);
        namesMap.put(DESKTOP, 690266142698242119L);
        namesMap.put(CHAIR, 692823566332199084L);
        namesMap.put(CAMERA, 743345628968517714L);
        namesMap.put(PIANO, 690266101854371848L);
        namesMap.put(HAND, 690266133907243017L);
        namesMap.put(KEYBOARD, 690266156262752326L);

        // PERMISSION ROLES
        namesMap.put(BULB, 769318013144268831L);

        // REACTION HANDLING

        //SUBSCRIPTION ROLES MESSAGE
        Message subRoles = api.getMessageById(736682938414006296L, api.getTextChannelById(736678296783290369L).get()).join();

        subRoles.addReactionAddListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user = event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case LOUDSPEAKER:
                        assignRole(server, user, namesMap.get(LOUDSPEAKER));
                        break;
                    case CALENDAR:
                        assignRole(server, user, namesMap.get(CALENDAR));
                        break;
                    case POPPER:
                        assignRole(server, user, namesMap.get(POPPER));
                        break;
                    case MEDAL:
                        assignRole(server, user, namesMap.get(MEDAL));
                        break;
                }
            }
        });

        subRoles.addReactionRemoveListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user = event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case LOUDSPEAKER:
                        removeRole(server, user, namesMap.get(LOUDSPEAKER));
                        break;
                    case CALENDAR:
                        removeRole(server, user, namesMap.get(CALENDAR));
                        break;
                    case POPPER:
                        removeRole(server, user, namesMap.get(POPPER));
                        break;
                    case MEDAL:
                        removeRole(server, user, namesMap.get(MEDAL));
                        break;
                }
            }
        });

        // IDENTIFICATION ROLES MESSAGE
        Message identifRoles = api.getMessageById(760211493312004126L, api.getTextChannelById(736678296783290369L).get()).join();

        identifRoles.addReactionAddListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user = event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case PAINTBRUSH:
                        assignRole(server, user, namesMap.get(PAINTBRUSH));
                        break;
                    case DESKTOP:
                        assignRole(server, user, namesMap.get(DESKTOP));
                        break;
                    case CHAIR:
                        assignRole(server, user, namesMap.get(CHAIR));
                        break;
                    case CAMERA:
                        assignRole(server, user, namesMap.get(CAMERA));
                        break;
                    case PIANO:
                        assignRole(server, user, namesMap.get(PIANO));
                        break;
                    case HAND:
                        assignRole(server, user, namesMap.get(HAND));
                        user.sendMessage("Hi there!\n\n" +
                                "I've noticed that you've just chosen to get the Writer role on the Art Prompts" +
                                " server! If you're interested in writing, you might want to check out the Eledris Blog, ran by" +
                                " the creator of AP. The blog covers topics like worldbuilding and fiction writing!\n\n" +
                                "Check it out through this link: <https://eledris.com>");
                        break;
                    case KEYBOARD:
                        assignRole(server, user, namesMap.get(KEYBOARD));
                        break;
                    //case "":
                }
            }
        });

        identifRoles.addReactionRemoveListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user = event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case PAINTBRUSH:
                        removeRole(server, user, namesMap.get(PAINTBRUSH));
                        break;
                    case DESKTOP:
                        removeRole(server, user, namesMap.get(DESKTOP));
                        break;
                    case CHAIR:
                        removeRole(server, user, namesMap.get(CHAIR));
                        break;
                    case CAMERA:
                        removeRole(server, user, namesMap.get(CAMERA));
                        break;
                    case PIANO:
                        removeRole(server, user, namesMap.get(PIANO));
                        break;
                    case HAND:
                        removeRole(server, user, namesMap.get(HAND));
                        break;
                    case KEYBOARD:
                        removeRole(server, user, namesMap.get(KEYBOARD));
                        break;
                    //case "":
                }
            }
        });


        // PERMISSIONS ROLES MESSAGE
        Message permRoles = api.getMessageById(769318387808469032L, api.getTextChannelById(736678296783290369L).get()).join();

        permRoles.addReactionAddListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user = event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case BULB:
                        assignRole(server, user, namesMap.get(BULB));
                        break;
                }
            }
        });

        permRoles.addReactionRemoveListener(event -> {
            if (event.getServer().isPresent() && event.getUser().isPresent()) {
                Server server = event.getServer().get();
                User user =  event.getUser().get();
                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case BULB:
                        removeRole(server, user, namesMap.get(BULB));
                        break;
                }
            }
        });

    }

    private static void infoEmbed(TextChannel channel, Icon avatar) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Roley Poley")
                .setColor(new Color(255, 101, 47))
                .setDescription("About Roley Poley and Elereth")
                .addField("Roley Poley", "Roley Poley is a role management bot created for the Art Prompts" +
                        " Discord server by Elereth#4300. It was created early in the server's history and has undergone" +
                        " many changes throughout its life. It is constantly being improved and new functionality" +
                        " is always on the burner. Roley Poley looks forward to serving you your new roles!")
                .addField("Elereth, the Creator", "Elereth is currently the only moderator of the Art Prompts" +
                        " Discord server, and has been for a while. He is in charge of upkeep of and additions to the Roley" +
                        " Poley role management bot, an ongoing programming project. You can contact him by DMing him here" +
                        " on Discord, through LinkedIn, and E-Mail; you can even pay him to create" +
                        " a custom bot for your Discord server through Fiverr!")
                .addField("Elereth Contact Information", "Discord: Elereth#4300\n" +
                        "LinkedIn: https://www.linkedin.com/in/caleb-garcia-5226717a/\n" +
                        "E-Mail: caleb.g2001@gmail.com\n" +
                        "Fiverr: https://fiverr.com/caleb_garcia")
                .setThumbnail("https://eledris.com/art-prompts/logo")
                .setFooter("Roley Poley - Art Prompts Discord Server Role Management Bot", avatar);
        channel.sendMessage(embed);
    }

    private static void assignRole(Server server, User user, Long roleID) {
        server.getRoleById(roleID).ifPresent(role -> {
            server.addRoleToUser(user, role);
        });
    }

    private static void removeRole(Server server, User user, Long roleID) {
        server.getRoleById(roleID).ifPresent(role -> {
            server.removeRoleFromUser(user, role);
        });
    }
}