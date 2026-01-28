package com.revplay.util.ui;

import com.revplay.util.service.HistoryService;
import com.revplay.util.model.User;
import com.revplay.util.model.Song;
import com.revplay.util.service.PlayerService;
import com.revplay.util.service.SongService;

import java.util.List;
import java.util.Scanner;

public class PlayerUI {

    private static PlayerService playerService = new PlayerService();
    private static SongService songService = new SongService();
    private static HistoryService historyService = new HistoryService();

    public static void openPlayer(Scanner sc, User user) {

        while (true) {
            System.out.println("\nMUSIC PLAYER");
            System.out.println("1. Play Song");
            System.out.println("2. Pause");
            System.out.println("3. Skip");
            System.out.println("4. Repeat");
            System.out.println("5. Exit Player");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Song> songs = songService.viewAllSongs();
                    System.out.println("\n--- SONG LIST ---");
                    for (Song s : songs) {
                        System.out.println(s.getSongId() + " | " + s.getTitle());
                    }

                    System.out.print("Enter Song ID to play: ");
                    int songId = sc.nextInt();

                    Song selected = songs.stream()
                            .filter(s -> s.getSongId() == songId)
                            .findFirst()
                            .orElse(null);

                    if (selected != null) {
                        playerService.play(selected);
                        historyService.recordPlay(user.getUserId(), selected);
                    } else {
                        System.out.println("Invalid song ID");
                    }
                    break;

                case 2:
                    playerService.pause();
                    break;

                case 3:
                    playerService.skip();
                    break;

                case 4:
                    playerService.repeat();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

