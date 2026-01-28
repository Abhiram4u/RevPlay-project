package com.revplay.util.ui;
import java.util.*;
import com.revplay.util.model.Album;
import com.revplay.util.service.FavoriteService;
import com.revplay.util.service.AlbumService;
import com.revplay.util.model.Artist;
import com.revplay.util.service.ArtistService;
import com.revplay.util.service.SongService;

import java.util.Scanner;

public class ArtistUI {
    private static AlbumService albumService = new AlbumService();
    private static FavoriteService favoriteService = new FavoriteService();
    private static ArtistService artistService = new ArtistService();
    private static SongService songService = new SongService();

    public static void artistMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- ARTIST MENU ---");
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
                    Artist artist = login(sc);
                    if (artist != null) {
                        artistDashboard(artist, sc);
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
        System.out.print("Genre: ");
        String genre = sc.nextLine();

        if (artistService.registerArtist(name, email, password, genre)) {
            System.out.println("Artist registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    private static Artist login(Scanner sc) {
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Artist artist = artistService.loginArtist(email, password);
        if (artist != null) {
            System.out.println("Welcome Artist " + artist.getName());
        } else {
            System.out.println("Invalid credentials!");
        }
        return artist;
    }

    private static void artistDashboard(Artist artist, Scanner sc) {

        while (true) {
            System.out.println("\n--- ARTIST DASHBOARD ---");
            System.out.println("1. Manage Artist Profile");
            System.out.println("2. Create Album");
            System.out.println("3. View My Albums");
            System.out.println("4. Upload Song (Add to Album)");
            System.out.println("5. View My Songs");
            System.out.println("6. Update Song");
            System.out.println("7. Update Album");
            System.out.println("8. Delete Song");
            System.out.println("9. Delete Album");
            System.out.println("10. View Play Count & Statistics");
            System.out.println("11. View Users Who Favorited My Song");
            System.out.println("12. Logout");


            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Bio: ");
                    String bio = sc.nextLine();
                    System.out.print("Genre: ");
                    String g = sc.nextLine();
                    System.out.print("Social Links: ");
                    String links = sc.nextLine();

                    if (artistService.updateProfile(artist.getArtistId(), bio, g, links))
                        System.out.println("Profile updated successfully");
                    else
                        System.out.println("Update failed");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Album Title: ");
                    String albumTitle = sc.nextLine();
                    System.out.print("Release Date (yyyy-mm-dd): ");
                    java.sql.Date date = java.sql.Date.valueOf(sc.nextLine());

                    if (albumService.createAlbum(artist.getArtistId(), albumTitle, date))
                        System.out.println("Album created successfully");
                    else
                        System.out.println("Failed to create album");
                    break;


                case 3:
                    List<Album> albums = albumService.viewMyAlbums(artist.getArtistId());

                    if (albums.isEmpty()) {
                        System.out.println("No albums found");
                    } else {
                        for (Album a : albums) {
                            System.out.println(a.getAlbumId() + " | " + a.getTitle());
                        }
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Song Title: ");
                    String title = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Duration (seconds): ");
                    int duration = sc.nextInt();

                    System.out.print("Album ID (0 if none): ");
                    int aid = sc.nextInt();
                    Integer albumId = (aid == 0) ? null : aid;

                    if (songService.uploadSong(artist.getArtistId(), albumId, title, genre, duration))
                        System.out.println("Song uploaded successfully");
                    else
                        System.out.println("Song upload failed");
                    break;
                case 5:
                    songService.viewMySongs(artist.getArtistId())
                            .forEach(s -> System.out.println(
                                    s.getSongId() + " | " +
                                            s.getTitle() + " | " +
                                            s.getGenre() + " | Plays: " +
                                            s.getPlayCount()
                            ));
                    break;
                case 6:
                    System.out.print("Song ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Title: ");
                    String nt = sc.nextLine();
                    System.out.print("New Genre: ");
                    String ng = sc.nextLine();

                    if (songService.updateSong(sid, nt, ng))
                        System.out.println("Song updated");
                    else
                        System.out.println("Update failed");
                    break;
                case 7:
                    System.out.print("Album ID: ");
                    int alid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Album Title: ");
                    String newTitle = sc.nextLine();

                    if (albumService.updateAlbum(alid, newTitle))
                        System.out.println("Album updated");
                    else
                        System.out.println("Update failed");
                    break;
                case 8:
                    System.out.print("Song ID to delete: ");
                    int dsid = sc.nextInt();

                    if (songService.deleteSong(dsid, artist.getArtistId()))
                        System.out.println("Song deleted");
                    else
                        System.out.println("Delete failed");
                    break;

                case 9:
                    System.out.print("Album ID to delete: ");
                    int daid = sc.nextInt();

                    if (albumService.deleteAlbum(daid, artist.getArtistId()))
                        System.out.println("Album deleted");
                    else
                        System.out.println("Delete failed");
                    break;
                case 10:
                    songService.viewMySongs(artist.getArtistId())
                            .forEach(s -> System.out.println(
                                    s.getTitle() + " | Plays: " + s.getPlayCount()
                            ));
                    break;
                case 11:
                    System.out.print("Enter your Song ID: ");
                    int songId = sc.nextInt();

                    List<String> users = favoriteService.viewUsersWhoFavoritedSong(songId);

                    if (users.isEmpty()) {
                        System.out.println("No users have favorited this song.");
                    } else {
                        System.out.println("Users who favorited this song:");
                        for (String name : users) {
                            System.out.println("- " + name);
                        }
                    }
                    break;
                case 12:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
