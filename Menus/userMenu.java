package Menus;

import java.util.*;

import javax.security.auth.PrivateCredentialPermission;

import java.lang.classfile.ClassHierarchyResolver.ClassHierarchyInfo;
import java.time.LocalDateTime;

import Utils.*;
import Database.*;
import DataStructures.*;

public class userMenu {
  public static void main() {
    if (CurrentUser.getUsername() == null)
      return;
    if (CurrentUser.getUser() == null)
      return;

    int Choice;
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

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

      switch (Choice) {
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
          break;
        case 3:
          System.out.print("\033[H\033[2J");
          watcHashSet();
          break;
        case 4:
          System.out.print("\033[H\033[2J");
          playlist();
          break;
        case 5:
          System.out.print("\033[H\033[2J");
          history();
          break;
        case 6:
          System.out.print("\033[H\033[2J");
          if (CurrentChannel.getChannel() != null) {
            System.out.print("\033[H\033[2J");
            channelMenu.main();
            System.out.print("\033[H\033[2J");
          } else {
            String channel_name = GetInput.stringLimitedCenter("Channel Name:", "Too many characters.", 16, 10);
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
    if (CurrentUser.getUsername() == null)
      return;
    int Choice;
    int outerPad = 10;

    FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
    FormattedPrint.center("", "||", outerPad);
    FormattedPrint.center("1. Change Display Name", "||", outerPad);
    FormattedPrint.center("9. DELETE ACCOUNT", "||", outerPad);
    FormattedPrint.center("", "||", outerPad);
    FormattedPrint.center("====================================", "###", outerPad);

    Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
    switch (Choice) {
      case 0:
        System.out.print("\033[H\033[2J");
        return;
      case 1:
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Change Display Name", "", outerPad);
        FormattedPrint.center("", "", outerPad);
        FormattedPrint.center("Current Display Name: " + CurrentUser.getName(), "", outerPad);
        FormattedPrint.center("", "", outerPad);
        String newDisplayName = GetInput.stringLimitedCenter("New Display Name:", "Too many characters.", 16, 10);
        QueryUser.getUser(CurrentUser.getUsername()).display_name = newDisplayName;
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Display Name changed successfully.", "", 0);
        System.out.println("");
        break;
      case 9:
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("######################################", "", outerPad);
        FormattedPrint.center("", "#", 13);
        FormattedPrint.center("DELETE ACCOUNT", "#", 13);
        FormattedPrint.center("", "#", 13);
        FormattedPrint.center("######################################", "", outerPad);
        FormattedPrint.center("", "", outerPad);
        String delete = GetInput.stringLimitedCenter("ARE YOU SURE? (TYPE \"DELETE MY ACCOUNT\"\" IF YOU SURE)",
            "Input isn't valid.", 17, 17);
        if (!delete.contentEquals("DELETE MY ACCOUNT"))
          break;

        CurrentUser.delete();
        CurrentUser.set(null);
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("ACCOUNT DELETED SUCCESSFULLY.", "", 0);
        System.out.println("");
        return;
      default:
        System.out.print("\033[H\033[2J");
        break;
    }

  }

  private static void queue() {
    int Choice;
    int outerPad = 10;

    while (true) {
      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);

      if (CurrentUser.getUserLibrary().getQueue().isEmpty()) {
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("Your queue is empty.", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("======================================", "###", outerPad);
      } else {
        int i = 1;
        FormattedPrint.center("", "||", outerPad);
        for (Video video : CurrentUser.getUserLibrary().getQueue()) {
          FormattedPrint.center(i + ". " + video.title, "||", outerPad);
          i++;
        }
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("======================================", "###", outerPad);
      }

      QueryChannel.getChannelLibrary(CurrentUser.getUsername()).getChannelVideos();

      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Watch One", "||", outerPad);
      FormattedPrint.center("2. Watch All", "||", outerPad);
      FormattedPrint.center("3. Remove One", "||", outerPad);
      FormattedPrint.center("4. Remove All", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
      if ((CurrentUser.getUserLibrary().getQueue() == null || CurrentUser.getUserLibrary().getQueue().isEmpty())
          && Choice > 0) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Your queue is empty.", "", 0);
        System.out.println("");
        break;
      }

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          CurrentUser.getUserLibrary().addHistory(CurrentUser.getUserLibrary().getQueue().element());
          CurrentUser.getUserLibrary().getQueue().poll();
          FormattedPrint.center("Video watched.", "", 0);
          break;
        case 2:
          while (!CurrentUser.getUserLibrary().getQueue().isEmpty()) {
            CurrentUser.getUserLibrary().addHistory(CurrentUser.getUserLibrary().getQueue().element());
            CurrentUser.getUserLibrary().getQueue().poll();
            FormattedPrint.center("Video watched.", "", 0);
          }
          System.out.print("\033[H\033[2J");
          System.out.println("");
          FormattedPrint.center("All video watched.", "", 0);
          break;
        case 3:
          CurrentUser.getUserLibrary().getQueue().poll();
          FormattedPrint.center("Video removed.", "", 0);
          break;
        case 4:
          while (!CurrentUser.getUserLibrary().getQueue().isEmpty()) {
            CurrentUser.getUserLibrary().getQueue().poll();
          }
          System.out.print("\033[H\033[2J");
          FormattedPrint.center("All video watched.", "", 0);
          System.out.println("");
          break;
        default:
          System.out.print("\033[H\033[2J");
          break;
      }
    }
  }

  private static void history() {
    int Choice;
    int outerPad = 10;

    while (true) {
      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);

      if (CurrentUser.getUserLibrary().getHistory().isEmpty()) {
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("Your history is empty.", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("======================================", "###", outerPad);
      } else {
        int i = 1;
        FormattedPrint.center("", "||", outerPad);
        for (Video video : CurrentUser.getUserLibrary().getHistory()) {
          FormattedPrint.center(i + ". " + video.title, "||", outerPad);
          i++;
        }
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("======================================", "###", outerPad);
      }

      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Remove One", "||", outerPad);
      FormattedPrint.center("2. Remove All", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
      if (CurrentUser.getUserLibrary().getHistory().isEmpty()) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Your queue is empty.", "", 0);
        System.out.println("");
        break;
      }

      if (CurrentUser.getUserLibrary().getHistory() == null
          || CurrentUser.getUserLibrary().getHistory().isEmpty() && Choice > 0) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Your queue is empty.", "", 0);
        System.out.println("");
        break;
      }

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          System.out.print("\033[H\033[2J");
          CurrentUser.getUserLibrary().getHistory().pop();
          FormattedPrint.center("Video removed.", "", 0);
          System.out.println("");
          break;
        case 2:
          System.out.print("\033[H\033[2J");
          while (!CurrentUser.getUserLibrary().getHistory().isEmpty()) {
            CurrentUser.getUserLibrary().getHistory().pop();
          }
          FormattedPrint.center("All video watched.", "", 0);
          System.out.println("");
          break;
        default:
          System.out.print("\033[H\033[2J");
          break;
      }
    }
  }

  private static void browse() {
    int Choice;
    int outerPad = 10;

    while (true) {
      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Browse Video", "||", outerPad);
      FormattedPrint.center("2. Browse Playlist", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

      switch (Choice) {
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

    FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
    if (videos == null || videos.isEmpty()) {
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("There's no video available.", "##", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    } else {
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("Recomended Videos", "##", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      for (int i = 1; i <= videos.size(); i++) {
        FormattedPrint.center(i + ". " + videos.get(i - 1).title, "||", outerPad);
      }
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    }
  }

  private static void browseVideos() {
    int Choice, ChoiceVideo;
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
      FormattedPrint.center("=====================================", "###", outerPad);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
      if (videos == null || videos.isEmpty()) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("There's no video available.", "", 0);
        System.out.println("");
        break;
      }

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          while (true) {
            System.out.print("\033[H\033[2J");
            browseVideosRecomended(videos);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoiceVideo = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
            if (ChoiceVideo == 0) {
              break;
            } else if (ChoiceVideo <= videos.size()) {
              CurrentUser.getUserLibrary().addHistory(videos.get(ChoiceVideo - 1));
              videos.remove(ChoiceVideo - 1);
              FormattedPrint.center("Video watched.", "", 0);
              System.out.println("");
            } else {
              FormattedPrint.center("Video not found.", "", 0);
              System.out.println("");
            }
          }
          System.out.print("\033[H\033[2J");
          break;
        case 2:
          while (true) {
            System.out.print("\033[H\033[2J");
            browseVideosRecomended(videos);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoiceVideo = GetInput.integerZeroPositiveCenter("Which video to add: ", "Input isn't valid", 1);
            if (ChoiceVideo == 0) {
              System.out.print("\033[H\033[2J");
              break;
            } else if (ChoiceVideo <= videos.size()) {
              CurrentUser.getUserLibrary().addQueue(videos.get(ChoiceVideo - 1));
              videos.remove(ChoiceVideo - 1);
              FormattedPrint.center("Video added to queue.", "", 0);
              System.out.println("");
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
            ChoiceVideo = GetInput.integerZeroPositiveCenter("Which video to add: ", "Input isn't valid", 1);
            if (ChoiceVideo == 0) {
              System.out.print("\033[H\033[2J");
              break;
            } else if (ChoiceVideo <= videos.size()) {
              CurrentUser.getUserLibrary().addWatchHashSetVideos(videos.get(ChoiceVideo - 1));
              videos.remove(ChoiceVideo - 1);
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
          int ChoicePlaylist;
          while (true) {
            System.out.print("\033[H\033[2J");
            LinkedList<PlaylistNode> playlists = CurrentUser.getUserLibrary().getAllPlaylistNode();
            browseVideosRecomended(videos);
            playlistUser(playlists);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoicePlaylist = GetInput.integerZeroPositiveCenter("Which playlist to add to: ", "Input isn't valid", 1);
            if (ChoicePlaylist == 0) {
              break;
            } else if (ChoicePlaylist <= playlists.size()) {
              FormattedPrint.center("Playlist: " + CurrentUser.getUserLibrary().getPlaylist(ChoicePlaylist).name, "",
                  0);
              System.out.println("");
            } else {
              FormattedPrint.center("Video not found.", "", 0);
              System.out.println("");
            }
            while (true) {
              ChoiceVideo = GetInput.integerZeroPositiveCenter("Which playlist to add from: ", "Input isn't valid", 1);
              if (ChoiceVideo == 0) {
                break;
              } else if (ChoiceVideo <= videos.size()) {
                CurrentUser.getUserLibrary().getPlaylist(ChoicePlaylist).addVideo(videos.get(ChoiceVideo - 1));
                videos.remove(ChoiceVideo - 1);
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

    FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
    if (playlists == null || playlists.isEmpty()) {
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("There's no plyalist available.", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    } else {
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("Recomended Playlists", "##", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      for (int i = 1; i <= playlists.size(); i++) {
        FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
      }
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    }
  }

  private static void browsePlaylists() {
    int Choice, ChoicePlaylist;
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
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
      if (playlists == null || playlists.isEmpty()) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("There's no playlist available.", "", 0);
        System.out.println("");
        break;
      }

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          while (true) {
            System.out.print("\033[H\033[2J");
            browsePlaylistsRecomended(playlists);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoicePlaylist = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
            if (ChoicePlaylist == 0) {
              break;
            } else if (ChoicePlaylist <= playlists.size()) {
              for (Video video : playlists.get(ChoicePlaylist - 1).playlist) {
                CurrentUser.getUserLibrary().addHistory(video);
              }
              playlists.remove(ChoicePlaylist - 1);
              FormattedPrint.center("Playlist watched.", "", 0);
              System.out.println("");
            } else {
              FormattedPrint.center("Playlist not found.", "", 0);
              System.out.println("");
            }
          }
          System.out.print("\033[H\033[2J");
          break;
        case 2:
          while (true) {
            System.out.print("\033[H\033[2J");
            browsePlaylistsRecomended(playlists);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoicePlaylist = GetInput.integerZeroPositiveCenter("Which playlist to add to queue: ", "Input isn't valid",
                1);
            if (ChoicePlaylist == 0) {
              break;
            } else if (ChoicePlaylist <= playlists.size()) {
              for (Video video : playlists.get(ChoicePlaylist - 1).playlist) {
                CurrentUser.getUserLibrary().addQueue(video);
              }
              playlists.remove(ChoicePlaylist - 1);
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
            ChoicePlaylist = GetInput.integerZeroPositiveCenter("Which video to watch: ", "Input isn't valid", 1);
            if (ChoicePlaylist == 0) {
              break;
            } else if (ChoicePlaylist <= playlists.size()) {
              for (Video video : playlists.get(ChoicePlaylist - 1).playlist) {
                CurrentUser.getUserLibrary().addWatchHashSetVideos(video);
              }
              playlists.remove(ChoicePlaylist - 1);
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
          int ChoiceUPlaylists;
          while (true) {
            System.out.print("\033[H\033[2J");
            LinkedList<PlaylistNode> uplaylists = CurrentUser.getUserLibrary().getAllPlaylistNode();
            browsePlaylistsRecomended(playlists);
            playlistUser(uplaylists);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");
            ChoiceUPlaylists = GetInput.integerZeroPositiveCenter("Which playlist to add: ", "Input isn't valid", 1);
            if (ChoiceUPlaylists == 0) {
              break;
            } else if (ChoiceUPlaylists <= playlists.size()) {
              CurrentUser.getUserLibrary().addSavedPlaylist(playlists.get(ChoiceUPlaylists - 1));
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
          playlists = Picker.playlistNodeRandom(QueryChannel.getEveryPlaylistNode(), 20);
          ;
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
      FormattedPrint.center("======================================", "###", outerPad);
    } else {
      FormattedPrint.center("", "||", outerPad);
      for (int i = 1; i <= playlists.size(); i++) {
        FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
      }
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    }

  }

  private static void savedPlaylistList(List<PlaylistNode> playlists) {
    int outerPad = 10;

    if (playlists.isEmpty()) {
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("You don't save any playlist yet", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    } else {
      FormattedPrint.center("", "||", outerPad);
      for (int i = 1; i <= playlists.size(); i++) {
        FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
      }
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
    }
  }

  private static void playlist() {
    int Choice;
    int outerPad = 10;

    while (true) {

      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. My Playlist", "||", outerPad);
      FormattedPrint.center("2. Saved Playlist", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          System.out.print("\033[H\033[2J");
          myPlaylist();
          System.out.print("\033[H\033[2J");
          break;
        case 2:
          System.out.print("\033[H\033[2J");
          savedPlaylist();
          System.out.print("\033[H\033[2J");
          break;
        default:
          System.out.print("\033[H\033[2J");
          break;
      }
    }
  }

  private static void savedPlaylist() {
    int Choice;
    int outerPad = 10;

    while (true) {
      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center(" Good Binge !! ", "##", outerPad);
      FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");
      LinkedList<PlaylistNode> recentPlaylists = CurrentUser.getUserLibrary().getSavedPlaylistNodeRecent(10);
      savedPlaylistList(recentPlaylists);
      System.out.println("");
      FormattedPrint.center("======================================", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Watch", "||", outerPad);
      FormattedPrint.center("2. Remove", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");

      int playlistChoice;

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);
      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          break;
        case 1:
          while (true) {
            playlistChoice = GetInput.integerPositiveCenter("Playlist to watch: ", "", 0);
            if (playlistChoice == 0) {
              break;
            } else if (playlistChoice <= recentPlaylists.size()) {
              FormattedPrint.center("Playlist watched.", "", 0);
            } else {
              FormattedPrint.center("Playlist Not Found.", "", 0);
            }
          }
          break;
        case 2:
          while (true) {
            System.out.print("\033[H\033[2J");
            FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
            recentPlaylists = CurrentUser.getUserLibrary().getSavedPlaylistNodeRecent(10);
            savedPlaylistList(recentPlaylists);
            playlistChoice = GetInput.integerPositiveCenter("Playlist to w  atch: ", "", 0);
            if (playlistChoice == 0) {
              break;
            } else if (playlistChoice <= recentPlaylists.size()) {
              FormattedPrint.center("Playlist removed", "", 0);
              CurrentUser.getUserLibrary().deleteSavedPlaylist(recentPlaylists.get(playlistChoice - 1));
            } else {
              FormattedPrint.center("Playlist Not Found.", "", 0);
            }
          }
          break;
        default:
          System.out.print("\033[H\033[2J");
          break;
      }
    }
  }

  private static void myPlaylist() {
    int Choice;
    int outerPad = 10;

    while (true) {
      FormattedPrint.center("============== CLI-tube ==============", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center(" Watch and create a fun list !! ", "##", outerPad);
      FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");
      FormattedPrint.center("Your Recent Playlists: ", "||", outerPad);
      LinkedList<PlaylistNode> recentPlaylists = CurrentUser.getUserLibrary().getPlaylistNodeRecent(10);
      playlistUser(recentPlaylists);
      System.out.println("");
      FormattedPrint.center("======================================", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Create a Playlist", "||", outerPad);
      FormattedPrint.center("2. Delete Playlists", "||", outerPad);
      FormattedPrint.center("3. Delete All Playlist ", "||", outerPad);
      FormattedPrint.center("4. Change Playlist Name", "||", outerPad);
      FormattedPrint.center("5. Remove Video from Playlist ", "||", outerPad);
      FormattedPrint.center("0. Back ", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("======================================", "###", outerPad);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

      switch (Choice) {
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
          if (title.equals("~"))
            break;
          System.out.println("");
          String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);
          if (title.equals("~"))
            break;

          CurrentUser.getUserLibrary().createPlaylist(title, description);
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

            LinkedList<PlaylistNode> playlist = CurrentUser.getUserLibrary().getAllPlaylistNode();
            for (int i = 1; i <= playlist.size(); i++) {
              FormattedPrint.center(i + ". " + playlist.get(i - 1).name, "||", outerPad2);
            }
            FormattedPrint.center("", "||", outerPad2);
            FormattedPrint.center("====================================", "###", outerPad2);
            FormattedPrint.center("Tip: 0 to back.", "", 0);
            System.out.println("");

            int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
            if (toDelete == 0) {
              System.out.print("\033[H\033[2J");
              break;
            } else if (toDelete >= playlist.size()) {
              CurrentUser.getUserLibrary().deletePlaylist(playlist.get(toDelete - 1));
            } else {
              FormattedPrint.center("Playlist not found.", "", 0);
              System.out.println("");
            }

            System.out.print("\033[H\033[2J");
            FormattedPrint.center("Playlist deleted succesfully.", "", 0);
            System.out.println("");
            break;
          }
          System.out.print("\033[H\033[2J");
          break;
        case 3:
          if (recentPlaylists.isEmpty()) {
            break;
          }

          for (PlaylistNode node : CurrentUser.getUserLibrary().getAllPlaylistNode()) {
            CurrentUser.getUserLibrary().deletePlaylist(node);
          }

          System.out.print("\033[H\033[2J");
          FormattedPrint.center("All playlist deleted succesfully.", "", 0);
          System.out.println("");
          break;
        case 4:
          System.out.print("\033[H\033[2J");
          playlistChangeName();
          System.out.print("\033[H\033[2J");
          break;
        case 5:
          System.out.print("\033[H\033[2J");
          playlistRemoveVideo();
          System.out.print("\033[H\033[2J");
          break;
        default:
          System.out.print("\033[H\033[2J");
          break;
      }
    }
  }

  private static void watcHashSet() {
    int Choice;
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
        FormattedPrint.center("====================================", "###", outerPad);
      } else {
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("You didn't save any video yet.", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "###", outerPad);
      }
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("1. Watch", "||", outerPad);
      FormattedPrint.center("2. Watch all", "||", outerPad);
      FormattedPrint.center("3. Remove", "||", outerPad);
      FormattedPrint.center("4. Remove all", "||", outerPad);
      FormattedPrint.center("0. Back", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("====================================", "###", outerPad);

      System.out.println("");

      int toDO;
      Choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid.", 1);

      if ((videos == null || videos.isEmpty()) && Choice > 0) {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Your watch later is empty.", "", 0);
        System.out.println("");
        break;
      }

      switch (Choice) {
        case 0:
          System.out.print("\033[H\033[2J");
          return;
        case 1:
          while (!videos.isEmpty()) {
            toDO = GetInput.integerZeroPositiveCenter("Which to Watch: ", "Input isn't valid", 2);

            if (toDO <= videos.size()) {
              int count = 1;
              for (Video video : videos) {
                if (count == toDO) {
                  CurrentUser.getUserLibrary().removeWatchHashSetVideos(video);
                  CurrentUser.getUserLibrary().addHistory(video);
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
            CurrentUser.getUserLibrary().addHistory(video);
            CurrentUser.getUserLibrary().removeWatchHashSetVideos(video);
          }
          System.out.print("\033[H\033[2J");
          FormattedPrint.center("All videos watched.", "", 0);
          System.out.println("");
          break;
        case 3:
          while (!videos.isEmpty()) {
            toDO = GetInput.integerZeroPositiveCenter("Which video to remove: ", "Input isn't valid", 2);

            if (toDO <= videos.size()) {
              int count = 1;
              for (Video video : videos) {
                if (count == toDO) {
                  CurrentUser.getUserLibrary().removeWatchHashSetVideos(video);
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
            CurrentUser.getUserLibrary().removeWatchHashSetVideos(video);
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

  public static void playlistChangeName() {
    int outerPad = 10;
    int Choice;

    while (true) {
      LinkedList<PlaylistNode> playlists = CurrentUser.getUserLibrary().getAllPlaylistNode();
      FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("Your Playlists", "||", outerPad);
      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("====================================", "###", outerPad);
      FormattedPrint.center("", "||", outerPad);

      for (int i = 1; i <= playlists.size(); i++) {
        FormattedPrint.center(i + ". " + playlists.get(i - 1).name, "||", outerPad);
      }

      FormattedPrint.center("", "||", outerPad);
      FormattedPrint.center("====================================", "###", outerPad);

      FormattedPrint.center("Tip: 0 to back.", "", 0);
      System.out.println("");

      Choice = GetInput.integerZeroPositiveCenter("Which playlist to rename: ", "Input isn't valid", 1);

      if (Choice == 0) {
        System.out.print("\033[H\033[2J");
        return;
      } else if (Choice <= playlists.size()) {
        FormattedPrint.center("Playlist: " + CurrentUser.getUserLibrary().getPlaylist(Choice - 1).name, "", 0);
        System.out.println("");
        String newName = GetInput.stringLimitedCenter("New playlist name: ", "The name is too long.", 36, 12);
        if (newName.equals("~"))
          continue;
        playlists.get(Choice - 1).name = newName;
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Playlist name changed successfully.", "", 0);
        System.out.println("");
      } else {
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Playlist not found.", "", 0);
        System.out.println("");
        continue;
      }
    }
  }

  public static void playlistRemoveVideo() {
    int outerPad = 10;
    int ChoiceUPlaylist;

    while (true) {
      LinkedList<PlaylistNode> uplaylists = CurrentUser.getUserLibrary().getAllPlaylistNode();
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

      FormattedPrint.center("Tip: 0 to back.", "", 0);
      System.out.println("");

      ChoiceUPlaylist = GetInput.integerZeroPositiveCenter("Which playlist to remove from: ", "Input isn't valid", 1);

      if (ChoiceUPlaylist == 0) {
        System.out.print("\033[H\033[2J");
        break;
      } else if (ChoiceUPlaylist <= uplaylists.size()) {
        FormattedPrint.center("Playlist: " + CurrentUser.getUserLibrary().getPlaylist(ChoiceUPlaylist - 1).name, "", 0);
        System.out.println("");
        while (true) {

          LinkedList<Video> playlistVideos = CurrentUser.getUserLibrary().getPlaylist(ChoiceUPlaylist - 1).playlist;
          FormattedPrint.center("====================================", "###", outerPad);
          FormattedPrint.center("", "||", outerPad);
          FormattedPrint.center("Playlist videos: ", "||", outerPad);
          FormattedPrint.center("", "||", outerPad);
          FormattedPrint.center("====================================", "###", outerPad);
          FormattedPrint.center("", "||", outerPad);

          if (playlistVideos == null || playlistVideos.isEmpty()) {
            FormattedPrint.center("This playlist has no video yet.", "||", outerPad);
          } else {
            int i = 1;
            for (Video video : playlistVideos) {
              FormattedPrint.center(i + ". " + video.title, "||", outerPad);
              i++;
            }
          }

          FormattedPrint.center("", "||", outerPad);
          FormattedPrint.center("====================================", "###", outerPad);
          FormattedPrint.center("Tip: 0 to back,", "", outerPad);
          System.out.println("");

          int ChoiceVideo = GetInput.integerZeroPositiveCenter("Which video to remove: ", "Input isn't valid", 1);
          if (ChoiceVideo == 0) {
            System.out.print("\033[H\033[2J");
            break;
          } else if (ChoiceVideo <= playlistVideos.size()) {
            CurrentUser.getUserLibrary().getPlaylist(ChoiceUPlaylist - 1)
                .removeVideo(playlistVideos.get(ChoiceVideo - 1));
            System.out.print("\033[H\033[2J");
            FormattedPrint.center("Video removed from playlist.", "", 0);
            System.out.println("");
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
  }
}
