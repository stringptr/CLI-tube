package Menus;

import java.util.*;
import java.time.LocalDateTime;

import Utils.*;
import Database.*;
import DataStructures.*;

public class userMenu {
	public static void main() {
        if (CurrentUser.getUsername() == null) return;
        if (CurrentUser.getUser() == null) return;

        int choice;
        int outerPad = 11;
        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("What are we doing today?", "||", outerPad);
            FormattedPrint.center(CurrentUser.getName(), "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Browse Video & Playlist", "||", outerPad);
            FormattedPrint.center("2. Watch Queue", "||", outerPad);
            FormattedPrint.center("3. Check Watch Later", "||", outerPad);
            FormattedPrint.center("4. Playlist Collection", "||", outerPad);
            if (CurrentChannel.getChannel() != null) {
                FormattedPrint.center("5. Watch History", "||", outerPad);
                FormattedPrint.center("6. Switch to Channel Mode ", "||", outerPad);
            } else {
                FormattedPrint.center("6. Create Channel ", "||", outerPad);
            }
            FormattedPrint.center("7. Accout Setting", "||", outerPad);
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
                    browse();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    queue();
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    watcHashSet();
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    System.out.print("\033[H\033[2J");
                    playlist();
                    System.out.print("\033[H\033[2J");
                    break;
                case 5:
                    System.out.print("\033[H\033[2J");
                    history();
                    System.out.print("\033[H\033[2J");
                    break;
                case 6:
                    System.out.print("\033[H\033[2J");
                    if (CurrentChannel.getChannel() != null) {
                        System.out.print("\033[H\033[2J");
                        channelMenu.main();
                        System.out.print("\033[H\033[2J");
                    } else {
                        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 10);
                        CurrentUser.getUser().channel = new Channel(channel_name, null, LocalDateTime.now());
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Account with Channel successfully created.", "", 0);
                        System.out.println("");
                    }
                    break;
                case 7:
                    System.out.print("\033[H\033[2J");
                    settings();
                    System.out.println("");
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void settings() {
        if (CurrentUser.getUsername() == null) return;
        int choice;
        int outerPad = 10;

        FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Change Display Name", "||", outerPad);
        FormattedPrint.center("9. DELETE ACCOUNT", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "###", outerPad);

        choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
        switch (choice) {
            case 0:
                System.out.print("\033[H\033[2J");
                return;
            case 1:
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Change Display Name", "", outerPad);
                FormattedPrint.center("", "", outerPad);
                String newDisplayName = GetInput.stringLimitedCenter("New Display Name:", "Too many characters.", 16, 10);
                QueryUser.getUser(CurrentUser.getUsername()).display_name = newDisplayName;
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Display Name changed successfully.", "", 0);
                System.out.println("");
                break;
            case 9:
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("DELETE ACCOUNT", "", outerPad);
                FormattedPrint.center("", "", outerPad);
                int exit = GetInput.integerBoolCenter("Are you sure (1 to yes, 0 to no):",  "Input isn't valid", 1);
                if (exit == 0) break;
                QueryUser.deleteUser(CurrentUser.getUsername());
                CurrentUser.set(null);
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Account deleted successfully.", "", 0);
                System.out.println("");
                return;
            default:
                System.out.print("\033[H\033[2J");
                break;
        }

    }

    private static void queue() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);

            if (CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("Your queue is empty.", "##", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                int i = 1;
                FormattedPrint.center("", "||", outerPad);
                for (Video video : CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername())) {
                    FormattedPrint.center(i + ". " + video.title, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Watch One", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("2. Watch All", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("3. Remove One", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("4. Remove All", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
            if (CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).isEmpty()) {
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Your queue is empty.", "", 0);
                System.out.println("");
                break;
            }

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    CurrentUser.getUserLibrary().addHistory(CurrentUser.getUsername(), CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).element());
                    CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).poll();
                    FormattedPrint.center("Video watched.", "", 0);
                    break;
                case 2:
                    while (!CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).isEmpty()) {
                        CurrentUser.getUserLibrary().addHistory(CurrentUser.getUsername(), CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).element());
                        CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).poll();
                    FormattedPrint.center("Video watched.", "", 0);
                    }
                    FormattedPrint.center("All video watched.", "", 0);
                    break;
                case 3:
                    CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).poll();
                    FormattedPrint.center("Video removed.", "", 0);
                    break;
                case 4:
                    while (!CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).isEmpty()) {
                        CurrentUser.getUserLibrary().getQueue(CurrentUser.getUsername()).poll();
                    }
                    FormattedPrint.center("All video watched.", "", 0);
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void history() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);

            if (CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername()).isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("Your history is empty.", "##", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                int i = 1;
                FormattedPrint.center("", "||", outerPad);
                for (Video video : CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername())) {
                    FormattedPrint.center(i + ". " + video.title, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Remove One", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("2. Remove All", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
            if (CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername()).isEmpty()) {
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Your queue is empty.", "", 0);
                System.out.println("");
                break;
            }

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername()).pop();
                    FormattedPrint.center("Video removed.", "", 0);
                    break;
                case 2:
                    while (!CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername()).isEmpty()) {
                        CurrentUser.getUserLibrary().getHistory(CurrentUser.getUsername()).pop();
                    }
                    FormattedPrint.center("All video watched.", "", 0);
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void browse() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Browse Video", "||", outerPad);
            FormattedPrint.center("2. Browse Playlist", "||", outerPad);
            FormattedPrint.center("0. Back", "||", outerPad);
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
                    browseVideos();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    browsePlaylists();
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void browseVideosRecomended(List<Video> videos) {
        int outerPad = 10;

            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            if (videos == null || videos.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("There's no video available.", "##", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("Recomended Videos", "##", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
                FormattedPrint.center("", "||", outerPad);
                for (int i = 1; i <= videos.size(); i++) {
                    FormattedPrint.center(i + ". " + videos.get(i - 1).title, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }
    }

    private static void browseVideos() {
        int choice, choiceVideo;
        int outerPad = 10;

        List<Video> videos = Picker.videoRandom(QueryChannel.getAllChannelVideos(), 20);
        if (videos == null) {
            videos = new LinkedList<>();
        }

        while (true) {
            browseVideosRecomended(videos);

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Pick and Watch", "||", outerPad);
            FormattedPrint.center("2. Add to Queue", "||", outerPad);
            FormattedPrint.center("3. Add to Watch Later", "||", outerPad);
            FormattedPrint.center("4. Add to Playlist", "||", outerPad);
            FormattedPrint.center("5. Refresh Recomendation", "||", outerPad);
            FormattedPrint.center("0. Back", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
            if (videos == null || videos.isEmpty()) {
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("There's no video available.", "", 0);
                System.out.println("");
                break;
            }
            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browseVideosRecomended(videos);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choiceVideo = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
                        if (choiceVideo == 0) {
                            break;
                        } else if (choiceVideo <= videos.size()) {
                            CurrentUser.getUserLibrary().addHistory(CurrentUser.getUsername(), videos.get(choiceVideo - 1));
                            videos.remove(choiceVideo - 1);
                            FormattedPrint.center("Video watched.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Video not found.", "", 0);
                            System.out.println("");
                        }
                    }
                case 2:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browseVideosRecomended(videos);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choiceVideo = GetInput.integerZeroPositiveCenter("Which video to add: ", "Input isn't valid", 1);
                        if (choiceVideo == 0) {
                            break;
                        } else if (choiceVideo <= videos.size()) {
                            CurrentUser.getUserLibrary().addQueue(CurrentUser.getUsername(), videos.get(choiceVideo - 1));
                            videos.remove(choiceVideo - 1);
                            FormattedPrint.center("Video added to queue.", "", 0);
                            System.out.println("");
                        } else if (choiceVideo == 0) {
                            break;
                        } else {
                            FormattedPrint.center("Video not found.", "", 0);
                            System.out.println("");
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browseVideosRecomended(videos);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choiceVideo = GetInput.integerZeroPositiveCenter("Which video to add: ", "Input isn't valid", 1);
                        if (choiceVideo == 0) {
                            break;
                        } else if (choiceVideo <= videos.size()) {
                            CurrentUser.getUserLibrary().addWatchHashSetVideos(CurrentUser.getUsername(), videos.get(choiceVideo - 1));
                            videos.remove(choiceVideo - 1);
                            FormattedPrint.center("Video added to watch later.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Video not found.", "", 0);
                            System.out.println("");
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    int choicePlaylist;
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        LinkedList<PlaylistNode> playlists = CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername());
                        browseVideosRecomended(videos);
                        playlistUser(playlists);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choicePlaylist = GetInput.integerZeroPositiveCenter("Which playlist to add to: ", "Input isn't valid", 1);
                        if (choicePlaylist == 0) {
                            break;
                        } else if (choicePlaylist <= playlists.size()) {
                            FormattedPrint.center("Playlist: " + CurrentUser.getUserLibrary().getPlaylist(CurrentUser.getUsername(),choicePlaylist).name, "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Video not found.", "", 0);
                            System.out.println("");
                        }
                        while (true) {
                            choiceVideo = GetInput.integerZeroPositiveCenter("Which playlist to add from: ", "Input isn't valid", 1);
                            if (choiceVideo == 0) {
                                break;
                            } else if (choiceVideo <= videos.size()) {
                                CurrentUser.getUserLibrary().getPlaylist(CurrentUser.getUsername(),choicePlaylist).addVideo(videos.get(choiceVideo - 1));
                                videos.remove(choiceVideo - 1);
                                FormattedPrint.center("Video added to playlist.", "", 0);
                                System.out.println("");
                            } else {
                                FormattedPrint.center("Video not found.", "", 0);
                                System.out.println("");
                            }
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 5:
                    videos = Picker.videoRandom(QueryChannel.getAllChannelVideos(), 20);
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void browsePlaylistsRecomended(List<PlaylistNode> playlists) {
        int outerPad = 10;

            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            if (playlists == null || playlists.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("There's no plyalist available.", "||", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("Recomended Playlists", "##", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
                FormattedPrint.center("", "||", outerPad);
                for (int i = 1; i <= playlists.size(); i++) {
                    FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }
    }

    private static void browsePlaylists() {
        int choice, choicePlaylist;
        int outerPad = 10;

        List<PlaylistNode> playlists = Picker.playlistNodeRandom(QueryChannel.getEveryPlaylistNode(), 20);
        if (playlists == null) {
            playlists = new LinkedList<>();
        }
        while (true) {
            browsePlaylistsRecomended(playlists);

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Pick and Watch", "||", outerPad);
            FormattedPrint.center("2. Add to Queue", "||", outerPad);
            FormattedPrint.center("3. Add to Watch Later", "||", outerPad);
            FormattedPrint.center("4. Save", "||", outerPad);
            FormattedPrint.center("5. Refresh Recomendation", "||", outerPad);
            FormattedPrint.center("0. Back", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
            if (playlists == null || playlists.isEmpty()) {
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("There's no playlist available.", "", 0);
                System.out.println("");
                break;
            }

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browsePlaylistsRecomended(playlists);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choicePlaylist = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
                        if (choicePlaylist == 0) {
                            break;
                        } else if (choicePlaylist <= playlists.size()) {
                            for (Video video : playlists.get(choicePlaylist - 1).playlist) {
                                CurrentUser.getUserLibrary().addHistory(CurrentUser.getUsername(), video);
                            }
                            playlists.remove(choicePlaylist - 1);
                            FormattedPrint.center("Playlist watched.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Playlist not found.", "", 0);
                            System.out.println("");
                        }
                    }
                case 2:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browsePlaylistsRecomended(playlists);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choicePlaylist = GetInput.integerZeroPositiveCenter("Which playlist to add to queue: ", "Input isn't valid", 1);
                        if (choicePlaylist == 0) {
                            break;
                        } else if (choicePlaylist <= playlists.size()) {
                            for (Video video : playlists.get(choicePlaylist - 1).playlist) {
                                CurrentUser.getUserLibrary().addQueue(CurrentUser.getUsername(), video);
                            }
                            playlists.remove(choicePlaylist - 1);
                            FormattedPrint.center("Playlist added to queue.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Playlist not found.", "", 0);
                            System.out.println("");
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        browsePlaylistsRecomended(playlists);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choicePlaylist = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
                        if (choicePlaylist == 0) {
                            break;
                        } else if (choicePlaylist <= playlists.size()) {
                            for (Video video : playlists.get(choicePlaylist - 1).playlist) {
                                CurrentUser.getUserLibrary().addWatchHashSetVideos(CurrentUser.getUsername(), video);
                            }
                            playlists.remove(choicePlaylist - 1);
                            FormattedPrint.center("Playlist added to watch later.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Playlist not found.", "", 0);
                            System.out.println("");
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    int choiceUPlaylists;
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        LinkedList<PlaylistNode> uplaylists = CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername());
                        browsePlaylistsRecomended(playlists);
                        playlistUser(uplaylists);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");
                        choiceUPlaylists = GetInput.integerZeroPositiveCenter("Which playlist to add: ", "Input isn't valid", 1);
                        if (choiceUPlaylists == 0) {
                            break;
                        } else if (choiceUPlaylists <= uplaylists.size()) {
                            CurrentUser.getUserLibrary().addPlaylist(CurrentUser.getUsername(),uplaylists.get(choiceUPlaylists-1));
                            FormattedPrint.center("Playlist added to your library.", "", 0);
                            System.out.println("");
                        } else {
                            FormattedPrint.center("Playlist not found.", "", 0);
                            System.out.println("");
                        }
                    }
                    System.out.print("\033[H\033[2J");
                    break;
                case 5:
                    playlists = Picker.playlistNodeRandom(QueryChannel.getEveryPlaylistNode(), 20);;
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void playlistUser(List<PlaylistNode> playlists) {
        int outerPad = 10;

        if (playlists.isEmpty()) {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("You don't have any playlists yet.", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
        } else {
            FormattedPrint.center("", "||", outerPad);
            for (int i = 1; i <= playlists.size(); i++) {
                FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
            }
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
        }

    }

    private static void playlist() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Watch and create a fun list !! ", "##", outerPad);
            FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            LinkedList<PlaylistNode> recentPlaylists = CurrentUser.getUserLibrary().getPlaylistNodeRecent(CurrentUser.getUsername(), 10);
            playlistUser(recentPlaylists);

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

            switch (choice) {
                case 0:
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
                    String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                    CurrentUser.getUserLibrary().createPlaylist(CurrentUser.getUserLibrary().getPlaylistsParentNode(CurrentUser.getUsername()), title, description);
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("Playlist created successfully.", "", 0);
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

                        LinkedList<PlaylistNode> playlist = CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername());
                        for (int i = 1; i <= playlist.size(); i++) {
                            FormattedPrint.center(i + ". " + playlist.get(i - 1).name, "||", outerPad2);
                        }
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");

                        int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                        if (toDelete == 0) {
                            break;
                        } else {
                            CurrentUser.getUserLibrary().deletePlaylist(CurrentUser.getUsername(), playlist.get(toDelete - 1));
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

                    for (PlaylistNode node : CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername())) {
                        CurrentUser.getUserLibrary().deletePlaylist(CurrentChannel.getUsername(), node);
                    }

                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("All playlist deleted succesfully.", "", 0);
                    System.out.println("");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void watcHashSet() {
        int choice;
        int outerPad = 11;

        while (true) {
        FormattedPrint.center("====================================", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Watch the Watch Later ", "||", outerPad);
        FormattedPrint.center(CurrentUser.getName() + " ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "###", outerPad);

        LinkedHashSet<Video> videos = CurrentUser.getUserLibrary().getWatchHashSetVideos(CurrentUser.getUsername());
        if (!videos.isEmpty()) {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Your watch later list: ", "||", outerPad);
            int counter = 1;
            for (Video video : videos) {
                FormattedPrint.center(counter + ". " + video.title, "||", outerPad);
                counter++;
            }
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        } else {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("You didn't save any video yet.", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        }
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Watch", "||", outerPad);
        FormattedPrint.center("2. Watch all", "||", outerPad);
        FormattedPrint.center("3. Remove", "||", outerPad);
        FormattedPrint.center("4. Remove all", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "###", outerPad);

        System.out.println("");

        int toDO;
        choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid.", 1);
        switch (choice) {
            case 0:
                System.out.print("\033[H\033[2J");
                return;
            case 1:
                while (!videos.isEmpty()) {
                    toDO = GetInput.integerZeroPositiveCenter("Which to Watch: ", "Input isn't valid", 2);

                    if (toDO <= videos.size()){
                        int count = 1;
                        for (Video video : videos) {
                            if (count == toDO) {
                                CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.currentUsername, video);
                                CurrentUser.getUserLibrary().addHistory(CurrentUser.currentUsername, video);
                            }
                            count++;
                        }
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video watched.", "", 0);
                        System.out.println("");
                    } else {
                        break;
                    }
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos watched.", "", 0);
                System.out.println("");
            case 2:
                for (Video video : videos) {
                    CurrentUser.getUserLibrary().addHistory(CurrentUser.getUsername(), video);
                    CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos watched.", "", 0);
                System.out.println("");
                break;
            case 3:
                while (!videos.isEmpty()) {
                    toDO = GetInput.integerZeroPositiveCenter("Which video to remove: ", "Input isn't valid", 2);

                    if (toDO <= videos.size()){
                        int count = 1;
                        for (Video video : videos) {
                            if (count == toDO) {
                                CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                            }
                            count++;
                        }
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video removed.", "", 0);
                        System.out.println("");
                        break;
                    } else {
                        break;
                    }
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos removed.", "", 0);
                System.out.println("");
            case 4:
                for (Video video : videos) {
                    CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos removed.", "", 0);
                System.out.println("");
                break;
            default:
                break;
            }
        }
    }
}
