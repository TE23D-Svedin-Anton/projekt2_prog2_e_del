// Anton Svedin TE23D Main, det är själva programet där metoder och annat körs.
//Här får man olika typer av alternativ som man kan välja mellan för att matcha ens behov.
package server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean close = false;
        Library lib = new Library();
        System.out.println("Welcome to your local libary");

        while (!close) {

            System.out.println("What would you like to do?");
            System.out.println("1. Fetch object/objects from server");
            System.out.println("2. Add object to server");
            System.out.println("3. Find object");
            System.out.println("4. Remove object");
            System.out.println("5. Sort objects");
            System.out.println("6. Ban/unban users");
            System.out.println("7. Close");
            System.out.print("Choose between 1-7:");

            int mainMenuChoice = keyboard.nextInt();
            keyboard.nextLine();

            switch (mainMenuChoice) {

                case 1:
                    System.out.println("1. Book");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.println("4. Suspended users");
                    int fetchChoice = keyboard.nextInt();

                    switch (fetchChoice) {
                        case 1:
                            System.out.println("1. All books");
                            System.out.println("2. Individual book");
                            int bookFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (bookFetchChoice) {
                                case 1:
                                    lib.fetchBooks();
                                    break;

                                case 2:
                                    System.out.print("Nämn bokens id:");
                                    String bookId = keyboard.nextLine();
                                    lib.fetchBook(bookId);
                                    break;
                            }

                            break;

                        case 2:
                            System.out.println("1. All magazines");
                            System.out.println("2. Individual magazine");
                            int magazineFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (magazineFetchChoice) {
                                case 1:
                                    lib.fetchMagazines();
                                    break;

                                case 2:
                                    System.out.print("Nämn tidningens id:");
                                    String magazineId = keyboard.nextLine();
                                    lib.fetchMagazine(magazineId);
                                    break;
                            }
                            break;

                        case 3:
                            System.out.println("1. All users");
                            System.out.println("2. Individual user");
                            int userFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (userFetchChoice) {
                                case 1:
                                    lib.fetchUsers();
                                    break;

                                case 2:
                                    System.out.print("Nämn användarens id:");
                                    String userId = keyboard.nextLine();
                                    lib.fetchUser(userId);
                                    break;
                            }
                            break;

                        case 4:
                            lib.fetchSuspendedUsers();
                            break;
                    }
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    System.out.print("Title:");
                    String bookTitle = keyboard.nextLine();
                    System.out.print("Author");
                    String author = keyboard.nextLine();
                    System.out.print("Genre:");
                    String genre = keyboard.nextLine();
                    System.out.print("Pages:");
                    int pages = keyboard.nextInt();

                    break;

                case 5:
                    System.out.print("Title:");
                    String magazineTitle = keyboard.nextLine();
                    System.out.print("IssueNumber:");
                    int issueNumber = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.print("Category");
                    String category = keyboard.nextLine();
                    System.out.print("PulisherYear:");
                    int publisherYear = keyboard.nextInt();

                    break;

                case 6:

                    break;

                case 7:
                    close = true;
                    keyboard.close();
                    System.out.println("Hope to see you again!");
                    break;
            }
        }
    }
}