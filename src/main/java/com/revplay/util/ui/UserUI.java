package com.revplay.util.ui;

import com.revplay.util.service.PlaylistService;

import com.revplay.util.ui.PlayerUI;
import com.revplay.util.service.FavoriteService;
import com.revplay.util.service.SearchService;
import com.revplay.util.model.User;
import com.revplay.util.service.UserService;
import com.revplay.util.service.HistoryService;
import com.revplay.util.service.SongService;
import com.revplay.util.model.Song;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    private static PlaylistService playlistService = new PlaylistService();
    private static FavoriteService favoriteService = new FavoriteService();
    private static SearchService searchService = new SearchService();
    private static UserService userService = new UserService();
    private static SongService songService = new SongService();
    private static HistoryService historyService = new HistoryService();

    public static void userMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    User user = login(sc);
                    if (user != null) {
                        userDashboard(user, sc);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void register(Scanner sc) {
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (userService.registerUser(name, email, password)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    private static User login(Scanner sc) {
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("Invalid credentials!");
        }
        return user;
    }

    private static void userDashboard(User user, Scanner sc) {

        while (true) {
            System.out.println("\n--- USER DASHBOARD ---");
            System.out.println("1. View All Songs");
            System.out.println("2. Search Songs");
            System.out.println("3. Add Song to Favorites");
            System.out.println("4. View Favorite Songs");
            System.out.println("5. Create Playlist");
            System.out.println("6. View My Playlists");
            System.out.println("7. Add Song to Playlist");
            System.out.println("8. Remove Song from Playlist");
            System.out.println("9. Update Playlist");
            System.out.println("10. Delete Playlist");
            System.out.println("11. View Public Playlists");
            System.out.println("12. Open Music Player");
            System.out.println("13. View Listening History");
            System.out.println("14. View Recently Played");

            System.out.println("15. Logout");

            System.out.print("Choice: ");



            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Song> songs = songService.viewAllSongs();
                    System.out.println("\n--- SONG LIST ---");
                    for (Song s : songs) {
                        System.out.println(
                                s.getSongId() + " | " +
                                        s.getTitle() + " | " +
                                        s.getGenre() + " | Plays: " +
                                        s.getPlayCount()
                        );
                    }
                    break;

                case 2:
                    sc.nextLine(); // consume newline
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();

                    List<Song> results = searchService.searchSongs(keyword);

                    if (results == null || results.isEmpty()) {
                        System.out.println("No songs found.");
                    } else {
                        System.out.println("\n--- SEARCH RESULTS ---");
                        for (Song s : results) {
                            System.out.println(
                                    s.getSongId() + " | " +
                                            s.getTitle() + " | " +
                                            s.getGenre()
                            );
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Song ID to favorite: ");
                    int songId = sc.nextInt();

                    if (favoriteService.addToFavorites(user.getUserId(), songId)) {
                        System.out.println("Song added to favorites!");
                    } else {
                        System.out.println("Failed to add favorite.");
                    }
                    break;

                case 4:
                    List<Song> favSongs = favoriteService.viewFavorites(user.getUserId());

                    if (favSongs.isEmpty()) {
                        System.out.println("No favorite songs found.");
                    } else {
                        System.out.println("\n--- FAVORITE SONGS ---");
                        for (Song s : favSongs) {
                            System.out.println(
                                    s.getSongId() + " | " +
                                            s.getTitle() + " | " +
                                            s.getGenre()
                            );
                        }
                    }
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Playlist name: ");
                    String pname = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Public? (true/false): ");
                    boolean pub = sc.nextBoolean();

                    playlistService.createPlaylist(user.getUserId(), pname, desc, pub);
                    System.out.println("Playlist created!");
                    break;

                case 6:
                    playlistService.viewMyPlaylists(user.getUserId())
                            .forEach(p -> System.out.println(
                                    p.getPlaylistId() + " | " + p.getName() + " | Public: " + p.isPublic()
                            ));
                    break;

                case 7:
                    System.out.print("Playlist ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Song ID: ");
                    int sid = sc.nextInt();

                    playlistService.addSong(pid, sid);
                    System.out.println("Song added!");
                    break;

                case 8:
                    System.out.print("Playlist ID: ");
                    pid = sc.nextInt();
                    System.out.print("Song ID: ");
                    sid = sc.nextInt();

                    playlistService.removeSong(pid, sid);
                    System.out.println("Song removed!");
                    break;

                case 9:
                    System.out.print("Playlist ID: ");
                    pid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    pname = sc.nextLine();
                    System.out.print("New description: ");
                    desc = sc.nextLine();
                    System.out.print("Public? ");
                    pub = sc.nextBoolean();

                    playlistService.updatePlaylist(pid, pname, desc, pub);
                    System.out.println("Playlist updated!");
                    break;

                case 10:
                    System.out.print("Playlist ID: ");
                    pid = sc.nextInt();
                    playlistService.deletePlaylist(pid);
                    System.out.println("Playlist deleted!");
                    break;

                case 11:
                    playlistService.viewPublicPlaylists()
                            .forEach(p -> System.out.println(
                                    p.getPlaylistId() + " | " + p.getName() + " | " + p.getDescription()
                            ));
                    break;
                case 12:
                    PlayerUI.openPlayer(sc,user);
                    break;

                case 13:
                    historyService.viewHistory(user.getUserId())
                            .forEach(s -> System.out.println(
                                    s.getSongId() + " | " + s.getTitle() + " | " + s.getGenre()
                            ));
                    break;

                case 14:
                    historyService.viewRecentlyPlayed(user.getUserId())
                            .forEach(s -> System.out.println(
                                    s.getSongId() + " | " + s.getTitle()
                            ));
                    break;

                case 15:
                    return;



                default:
                    System.out.println("Invalid choice!");
            }

        }
    }
}

