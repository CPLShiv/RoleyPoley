import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static String token;

    private static final String LOUDSPEAKER = "\uD83D\uDCE2";
    private static final String SMILE = "\uD83D\uDE04";
    private static final String CALENDAR = "\uD83D\uDDD3\uFE0F";
    private static final String PAINTBRUSH = "\uD83D\uDD8C\uFE0F";
    private static final String DESKTOP = "\uD83D\uDDA5\uFE0F";
    private static final String CHAIR = "\uD83E\uDE91";
    private static final String CAMERA = "\uD83D\uDCF7";
    private static final String PIANO = "\uD83C\uDFB9";
    private static final String HAND = "\u270D\uFE0F";
    private static final String KEYBOARD = "\u2328\uFE0F";


    public static void main(String[] args) {
        try {
            File credFile = new File("cred.txt");
            Scanner in = new Scanner(credFile);
            token = in.nextLine();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        api.updateActivity(ActivityType.WATCHING, "you type ~info and get some roles!");

        api.addMessageCreateListener(event -> {
            String message = event.getMessageContent();
            TextChannel channel = event.getChannel();
            if (message.equalsIgnoreCase("~info")) {
                infoEmbed(channel, api.getYourself().getAvatar());
            }
        });

        Map<String, String> namesMap = new HashMap<>();
        namesMap.put(LOUDSPEAKER, "Announcements");
        namesMap.put(SMILE, "Emote Contest");
        namesMap.put(CALENDAR, "Daily Prompt");
        namesMap.put(PAINTBRUSH, "Traditional Artist");
        namesMap.put(DESKTOP, "Digital Artist");
        namesMap.put(CHAIR, "3D Artist");
        namesMap.put(CAMERA, "Photographer");
        namesMap.put(PIANO, "Musician");
        namesMap.put(HAND, "Writer");
        namesMap.put(KEYBOARD, "Developer");

        Message setup = api.getMessageById(736682938414006296L, api.getTextChannelById(736678296783290369L).get()).join();

        setup.addReactionAddListener(event -> {
            event.getServer().ifPresent(server0 -> {
                User user = event.getUser();

                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case LOUDSPEAKER:
                        assignRole(server0, user, namesMap.get(LOUDSPEAKER));
                        break;
                    case SMILE:
                        assignRole(server0, user, namesMap.get(SMILE));
                        break;
                    case CALENDAR:
                        assignRole(server0, user, namesMap.get(CALENDAR));
                        break;
                    case PAINTBRUSH:
                        assignRole(server0, user, namesMap.get(PAINTBRUSH));
                        break;
                    case DESKTOP:
                        assignRole(server0, user, namesMap.get(DESKTOP));
                        break;
                    case CHAIR:
                        assignRole(server0, user, namesMap.get(CHAIR));
                        break;
                    case CAMERA:
                        assignRole(server0, user, namesMap.get(CAMERA));
                        break;
                    case PIANO:
                        assignRole(server0, user, namesMap.get(PIANO));
                        break;
                    case HAND:
                        assignRole(server0, user, namesMap.get(HAND));
                        user.sendMessage("Hi there!\n\n" +
                                "I've noticed that you've just chosen to get the Writer role on the Art Prompts" +
                                " server! If you're interested in writing, you might want to check out the Eledris Blog, ran by" +
                                " the creator of AP. The blog covers topics like worldbuilding and fiction writing!\n\n" +
                                "Check it out through this link: <https://eledris.com>");
                        break;
                    case KEYBOARD:
                        assignRole(server0, user, namesMap.get(KEYBOARD));
                        break;
                    //case "":
                }
            });
        });

        setup.addReactionRemoveListener(event -> {
            event.getServer().ifPresent(server0 -> {
                User user = event.getUser();

                String emoji = event.getEmoji().asUnicodeEmoji().orElse("");

                switch (emoji) {
                    case LOUDSPEAKER:
                        removeRole(server0, user, namesMap.get(LOUDSPEAKER));
                        break;
                    case SMILE:
                        removeRole(server0, user, namesMap.get(SMILE));
                        break;
                    case CALENDAR:
                        removeRole(server0, user, namesMap.get(CALENDAR));
                        break;
                    case PAINTBRUSH:
                        removeRole(server0, user, namesMap.get(PAINTBRUSH));
                        break;
                    case DESKTOP:
                        removeRole(server0, user, namesMap.get(DESKTOP));
                        break;
                    case CHAIR:
                        removeRole(server0, user, namesMap.get(CHAIR));
                        break;
                    case CAMERA:
                        removeRole(server0, user, namesMap.get(CAMERA));
                        break;
                    case PIANO:
                        removeRole(server0, user, namesMap.get(PIANO));
                        break;
                    case HAND:
                        removeRole(server0, user, namesMap.get(HAND));
                        break;
                    case KEYBOARD:
                        removeRole(server0, user, namesMap.get(KEYBOARD));
                        break;
                    //case "":
                }
            });
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

    private static void assignRole(Server server, User user, String roleName) {
        Role role = server.getRolesByName(roleName).get(0);
        server.addRoleToUser(user, role);
    }

    private static void removeRole(Server server, User user, String roleName) {
        Role role = server.getRolesByName(roleName).get(0);
        server.removeRoleFromUser(user, role);
    }
}