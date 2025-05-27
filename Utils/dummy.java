package Utils;

import java.time.LocalDateTime;

import DataStructures.*;
import Database.*;

public class dummy {
    public static void insert(){
        Database.Users.put("user", new User("user",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now()),"user"));
        Database.Users.put("username", new User("username",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now()),"password"));
        Database.Users.put("onething", new User("onething",LocalDateTime.now(),new Channel("onething", null, LocalDateTime.now()),"onething"));
        Database.Users.put("queen", new User("queen",LocalDateTime.now(),new Channel("queen", null, LocalDateTime.now()),"queen"));
        Database.Users.put("songbykeane", new User("queen",LocalDateTime.now(),new Channel("queen", null, LocalDateTime.now()),"queen"));

        QueryChannel.getChannelLibrary("username").uploadChannelVideo( new Video("Code a Java Program Overnight", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("user").uploadChannelVideo( new Video("Code Slow and Steady", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("username").uploadChannelVideo( new Video("One Night Thousand Lines", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("user").uploadChannelVideo( new Video("Don't Code a Java Program Overnight", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("One Thing I Don't Know Why", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("It Doesn't Matter", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("How Hard You Code", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("Keep That In Mind", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("I Design This Program", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("To Remind Myself", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("All I Know", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo( new Video("Time is a Valuable Thing", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("I came across A VL tree", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("I  felt the branches", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Of it looking at me", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Is this the we use to love?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Is this the place that?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("I've been dreaming of?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Oh, simple thing,", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Where have you gone?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("I'm getting old,", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("And i need something to rely on", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("So, tell me when?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("you're gonne let me in?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("I'm getting tired", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("And i need somewhere to begin", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("And if you have a minute?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Why don't we go?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Talk about it somewhere only we know", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("This could be the end of everything", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("So why don't we go?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo( new Video("Somewhere only we know", null, LocalDateTime.now()));



        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Too late, my time has come", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Body's aching all the time", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Sends shivers down my spine", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Goodbye, everybody, I've got to go", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Gotta leave you all behind", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("And face the truth", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Mamaaaa..... Uuuuuuu......", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("I Don't Wanna Die", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("Sometime I Wish I Never Born At All", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo( new Video("(Insert Brian May Solo Here)", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("queen").createPlaylist("Rhapsody","a song");
        QueryChannel.getChannelLibrary("songbykeane").createPlaylist("Somewhere","a song");
        QueryChannel.getChannelLibrary("username").createPlaylist("Java Speedrun","Faster, Better");
        QueryChannel.getChannelLibrary("onething").createPlaylist("In The End","");
        QueryChannel.getChannelLibrary("user").createPlaylist("How to Code","");

        for (Video video : QueryChannel.getChannelLibrary("queen").getChannelVideos()) {
            QueryChannel.getChannelLibrary("queen").getPlaylist(0).playlist.add(video);
        }
        for (Video video : QueryChannel.getChannelLibrary("songbykeane").getChannelVideos()) {
            QueryChannel.getChannelLibrary("songbykeane").getPlaylist(0).playlist.add(video);
        }
        for (Video video : QueryChannel.getChannelLibrary("onething").getChannelVideos()) {
            QueryChannel.getChannelLibrary("onething").getPlaylist(0).playlist.add(video);
        }
        for (Video video : QueryChannel.getChannelLibrary("username").getChannelVideos()) {
            QueryChannel.getChannelLibrary("username").getPlaylist(0).playlist.add(video);
        }
        for (Video video : QueryChannel.getChannelLibrary("user").getChannelVideos()) {
            QueryChannel.getChannelLibrary("user").getPlaylist(0).playlist.add(video);
        }
    }
}
