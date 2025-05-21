package Menus;

import java.text.Format;
import java.time.LocalDateTime;
import java.util.*;

import Utils.*;
import Database.*;
import DataStructures.*;
import Menus.*;

public class channelMenu {
    public static void main() {
        int choice;
        int outerPad = 11;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Now's your time to shine !!", "||", outerPad);
            FormattedPrint.center(CurrentUser.getUsername(), "||", outerPad);
            FormattedPrint.center(CurrentChannel.getName() + " ", "||", outerPad);;
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);;
            FormattedPrint.center("1. Manage Video ", "||", outerPad);
            FormattedPrint.center("2. Manage Playlist", "||", outerPad);
            FormattedPrint.center("3. Channel Setting", "||", outerPad);
            FormattedPrint.center("4. Switch to User Mode", "||", outerPad);
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    video();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    playlist();
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    //channelSetting();
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    System.out.print("\033[H\033[2J");
                    userMenu.main();
                    System.out.print("\033[H\033[2J");
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void video() {
        int choice;
        int outerPad = 11;

        while (true) {
        FormattedPrint.center("============ CLI-tube ============", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Now is your time to shine !! ", "##", outerPad);
        FormattedPrint.center(CurrentChannel.getName() + " ", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);

        LinkedList<Video> recentVideos = CurrentChannel.getChannelLibrary().getChannelVideosRecent(CurrentChannel.getUsername(),10);
        if (!recentVideos.isEmpty()) {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Your recent videos: ", "||", outerPad);
            for (int i = 1; i <= recentVideos.size(); i++) {
                FormattedPrint.center(i + ". " + recentVideos.get(i - 1).title, "||", outerPad);
           }
           FormattedPrint.center("", "||", outerPad);
           FormattedPrint.center("==================================", "###", outerPad);
        } else {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("You don't have any video.", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        }

        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Upload Video ", "||", outerPad);
        FormattedPrint.center("2. Delete Video ", "||", outerPad);
        FormattedPrint.center("3. Edit Video ", "||", outerPad);
        FormattedPrint.center("0. Back ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);
        System.out.println("");

        choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

        switch (choice) {
            case 0:
                return;
            case 1:
                int outerPad1 = 15;

                System.out.print("\033[H\033[2J");

                FormattedPrint.center("=== CLI-tube ===", "###", outerPad1);
                FormattedPrint.center("", "||", outerPad1);
                FormattedPrint.center("Upload Video", "||", outerPad1);
                FormattedPrint.center("", "||", outerPad1);
                FormattedPrint.center("==========================", "###", outerPad1);
                System.out.println("");

                String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                System.out.println("");
                String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                CurrentChannel.getChannelLibrary().uploadChannelVideo(CurrentChannel.getUsername(), new Video(title, description, LocalDateTime.now()));
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                System.out.println("");
                break;
            case 2:
                int outerPad2 = 11;
                while (!recentVideos.isEmpty()) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("============ CLI-tube ============", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("Delete Video", "||", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("==================================", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);

                    LinkedList<Video> allChannelVideos = CurrentChannel.getChannelLibrary().getChannelVideos(CurrentChannel.getUsername());
                    for (int i = 1; i <= allChannelVideos.size(); i++) {
                        FormattedPrint.center(i + ". " + allChannelVideos.get(i - 1).title, "||", outerPad2);
                    }

                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("==================================", "###", outerPad2);
                    FormattedPrint.center("Tip: 0 to back.", "", 0);
                    System.out.println("");

                    int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                    if (toDelete == 0) {
                        break;
                    } else if (toDelete > 0 && toDelete <= allChannelVideos.size()) {
                        CurrentChannel.getChannelLibrary().deleteChannelVideo(CurrentChannel.getUsername(), allChannelVideos.get(toDelete - 1));
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video deleted successfully.", "", 0);
                        System.out.println("");
                    } else {
                        break;
                    }

                    System.out.print("\033[H\033[2J");
                }
            case 3:
                System.out.print("\033[H\033[2J");
                //channelVideoEdit();
                break;
            default:
                System.out.print("\033[H\033[2J");
                break;
            }
        }
    }

	private static void playlist() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Let's create a fun list !! ", "##", outerPad);
            FormattedPrint.center(CurrentChannel.getName() + " ", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            LinkedList<PlaylistNode> recentPlaylists = CurrentChannel.getChannelLibrary().getPlaylistNodeRecent(CurrentChannel.getUsername(), 10);
            if (recentPlaylists.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("This channel has no playlists yet.", "||", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                FormattedPrint.center("", "||", outerPad);
                for (int i = 1; i <= recentPlaylists.size(); i++) {
                    FormattedPrint.center(i + ". " + recentPlaylists.get(i - 1).name, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            LinkedList<Video> videos = CurrentChannel.getChannelLibrary().getChannelVideos(CurrentChannel.getUsername());
            if (!videos.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("This channel has no video yet.", "||", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Create a Playlist ", "||", outerPad);
            FormattedPrint.center("2. Delete a Playlist ", "||", outerPad);
            FormattedPrint.center("3. Delete All Playlist ", "||", outerPad);
            FormattedPrint.center("4. Edit Playlist ", "||", outerPad);
            FormattedPrint.center("0. Back ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
            if (recentPlaylists == null || videos == null || recentPlaylists.isEmpty() || videos.isEmpty()) {
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("This channel has no playlists yet.", "", 0);
                System.out.println("");
                return;
            }

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    int outerPad1 = 16;
                    System.out.print("\033[H\033[2J");

                    FormattedPrint.center("======== CLI-tube ========", "###", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("Create a Playlist", "||", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("==========================", "###", outerPad1);
                    System.out.println("");

                    String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                    System.out.println("");
                    String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                    CurrentChannel.getChannelLibrary().createPlaylist(CurrentChannel.getChannel().library.getPlaylistsParentNode(CurrentChannel.getUsername()), title, description);
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("Video uploaded successfully.", "", 0);
                    break;
                case 2:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }
                    int outerPad2 = 10;
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("============= CLI-tube =============", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("Delete Playlist", "||", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);

                        LinkedList<PlaylistNode> allChannelPlaList = CurrentChannel.getChannelLibrary().getAllPlaylistNode(CurrentChannel.getUsername());
                        for (int i = 1; i <= allChannelPlaList.size(); i++) {
                            FormattedPrint.center(i + ". " + allChannelPlaList.get(i - 1).name, "||", outerPad2);
                        }
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");

                        int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                        if (toDelete == 0) {
                            break;
                        } else {
                            CurrentChannel.getChannelLibrary().deletePlaylist(CurrentChannel.getUsername(),allChannelPlaList.get(toDelete - 1));
                        }

                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Playlist deleted succesfully.", "", 0);
                        System.out.println("");
                        break;
                    }
                case 3:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }

                    for (PlaylistNode node : CurrentChannel.getChannelLibrary().getAllPlaylistNode(CurrentChannel.getUsername())) {
                        CurrentChannel.getChannelLibrary().deletePlaylist(CurrentChannel.getUsername(), node);
                    }

                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("All playlist deleted succesfully.", "", 0);
                    System.out.println("");
                    break;
                case 4:
                    int choiceUPlaylist;
                    int from=1, to=15;
                    while (true) {
                        LinkedList<PlaylistNode> uplaylists = CurrentChannel.getChannelLibrary().getAllPlaylistNode(CurrentChannel.getUsername());
                        LinkedList<Video> recentVideos = CurrentChannel.getChannelLibrary().getChannelVideosRecentRange(CurrentChannel.currentUsername, from, to);
                        FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("Your Playlists", "||", outerPad);
                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("====================================", "###", outerPad);
                        FormattedPrint.center("", "||", outerPad);

                        for (int i = 1; i <= uplaylists.size(); i++) {
                            FormattedPrint.center(i + ". " + uplaylists.get(i - 1).name, "||", outerPad);
                        }

                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("====================================", "###", outerPad);
                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("Your Recent Videos", "", 0);
                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("====================================", "###", outerPad);
                        FormattedPrint.center("", "||", outerPad);

                        for (Video video : recentVideos) {
                            FormattedPrint.center(video.title, "||", outerPad);
                        }

                        FormattedPrint.center("", "||", outerPad);
                        FormattedPrint.center("====================================", "###", outerPad);


                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");

                        choiceUPlaylist = GetInput.integerZeroPositiveCenter("Which playlist to add: ", "Input isn't valid", 1);

                        if (choiceUPlaylist == 0) {
                            System.out.print("\033[H\033[2J");
                            break;
                        } else if (choiceUPlaylist <= uplaylists.size()) {
                            FormattedPrint.center("Playlist: " + CurrentChannel.getChannelLibrary().getPlaylist(CurrentChannel.getUsername(),choiceUPlaylist).name, "", 0);
                            System.out.println("");
                            while (true) {
                                int choiceVideo = GetInput.integerZeroPositiveCenter("Which video to add: ", "Input isn't valid", 1);
                                if (choiceVideo == 0) {
                                    break;
                                } else if (choiceVideo <= recentVideos.size()) {
                                    if (CurrentChannel.getChannelLibrary().getPlaylist(CurrentChannel.getUsername(),choiceUPlaylist).playlist.contains(recentVideos.get(choiceVideo - 1))) {
                                        FormattedPrint.center("Video already in playlist.", "", 0);
                                        System.out.println("");
                                    } else {
                                        CurrentChannel.getChannelLibrary().getPlaylist(CurrentChannel.getUsername(),choiceUPlaylist).addVideo(recentVideos.get(choiceVideo - 1));
                                        recentVideos.remove(choiceVideo - 1);
                                        FormattedPrint.center("Video added to playlist.", "", 0);
                                        System.out.println("");
                                    }
                                } else {
                                    FormattedPrint.center("Video not found.", "", 0);
                                    System.out.println("");
                                }
                            }
                        } else {
                            FormattedPrint.center("Playlist not found.", "", 0);
                            System.out.println("");
                        }
                    }
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }
}
