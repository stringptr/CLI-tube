package Utils;

import java.util.*;

import DataStructures.PlaylistNode;
import DataStructures.Video;

public class Picker {
     public static List<Video> videoRandom(List<Video> videos, int total) {
        if (videos == null || videos.isEmpty()) {
            return null;
        }

        int listSize = videos.size();
        Random random = new Random();
        List<Video> randomVideos = new ArrayList<>();

        while (randomVideos.size() <= total && randomVideos.size() <= listSize) {
            if (listSize <= 20) {
                // If the list size is 20 or less, pick a video with uniform probability.
                int randomIndex = random.nextInt(listSize);
                randomVideos.add(videos.get(randomIndex));
            } else {
                // If the list size is greater than 20, adjust the probability.
                // Generate a random number between 0 and listSize.
                int randomIndex = random.nextInt(listSize);

                // Accept the video at randomIndex with a probability of 20/listSize.
                if (random.nextDouble() < (20.0 / listSize)) {
                    randomVideos.add(videos.get(randomIndex));
                } else {
                    // If the video at randomIndex is rejected, we need to re-pick.
                    // This can be optimized to avoid infinite loops in edge cases,
                    // but for simplicity, we'll just return null if we fail to pick after a few tries.
                    for (int i = 0; i < 5; i++) { // Try a few times to avoid returning null unnecessarily.
                        int newRandomIndex = random.nextInt(listSize);
                        if (random.nextDouble() < (20.0 / listSize)) {
                            randomVideos.add(videos.get(newRandomIndex));
                        }
                    }
                }
            }
        }

        return randomVideos;
    }

    public static List<PlaylistNode> playlistNodeRandom(List<PlaylistNode> playlist, int total) {
        if (playlist == null || playlist.isEmpty()) {
            return null;
        }

        int listSize = playlist.size();
        Random random = new Random();
        List<PlaylistNode> randomPlaylists = new ArrayList<>();

        while (randomPlaylists.size() <= total && randomPlaylists.size() <= listSize) {
            if (listSize <= 20) {
                // If the list size is 20 or less, pick a video with uniform probability.
                int randomIndex = random.nextInt(listSize);
                randomPlaylists.add(playlist.get(randomIndex));
            } else {
                // If the list size is greater than 20, adjust the probability.
                // Generate a random number between 0 and listSize.
                int randomIndex = random.nextInt(listSize);

                // Accept the video at randomIndex with a probability of 20/listSize.
                if (random.nextDouble() < (20.0 / listSize)) {
                    randomPlaylists.add(playlist.get(randomIndex));
                } else {
                    // If the video at randomIndex is rejected, we need to re-pick.
                    // This can be optimized to avoid infinite loops in edge cases,
                    // but for simplicity, we'll just return null if we fail to pick after a few tries.
                    for (int i = 0; i < 5; i++) { // Try a few times to avoid returning null unnecessarily.
                        int newRandomIndex = random.nextInt(listSize);
                        if (random.nextDouble() < (20.0 / listSize)) {
                            randomPlaylists.add(playlist.get(newRandomIndex));
                        }
                    }
                }
            }
        }

        return randomPlaylists;
    }
}
