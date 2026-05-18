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
            System.out.println("6. Who can rent a book?");
            System.out.println("7. Close");
            System.out.print("Choose between 1-7:");

            int mainMenuChoice = keyboard.nextInt();
            keyboard.nextLine();

            switch (mainMenuChoice) {

                case 1:
                    System.out.println("1. Books");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.println("4. Suspended users");
                    System.out.print("What would you like to do?:");
                    int fetchChoice = keyboard.nextInt();

                    switch (fetchChoice) {
                        case 1:
                            System.out.println("1. All books");
                            System.out.println("2. Individual book");
                            System.out.print("What would you like to do?:");
                            int bookFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (bookFetchChoice) {
                                case 1:
                                    lib.fetchBooks();
                                    break;

                                case 2:
                                    System.out.print("Type in the books id:");
                                    String bookIdToGet = keyboard.nextLine();
                                    lib.fetchBook(bookIdToGet);
                                    break;
                            }

                            break;

                        case 2:
                            System.out.println("1. All magazines");
                            System.out.println("2. Individual magazine");
                            System.out.print("What would you like to do?:");
                            int magazineFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (magazineFetchChoice) {
                                case 1:
                                    lib.fetchMagazines();
                                    break;

                                case 2:
                                    System.out.print("Type in the magazines id:");
                                    String magazineIdToGet = keyboard.nextLine();
                                    lib.fetchMagazine(magazineIdToGet);
                                    break;
                            }
                            break;

                        case 3:
                            System.out.println("1. All users");
                            System.out.println("2. Individual user");
                            System.out.print("What would you like to do?:");
                            int userFetchChoice = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (userFetchChoice) {
                                case 1:
                                    lib.fetchUsers();
                                    break;

                                case 2:
                                    System.out.print("Type in the users id:");
                                    String userIdToGet = keyboard.nextLine();
                                    lib.fetchUser(userIdToGet);
                                    break;
                            }
                            break;

                        case 4:
                            lib.fetchSuspendedUsers();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1. Books");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.println("4. Suspended users");
                    System.out.print("What would you like to do?:");
                    int addChoice = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (addChoice) {
                        case 1:
                            System.out.print("Title:");
                            String bookTitle = keyboard.nextLine();
                            System.out.print("Author:");
                            String author = keyboard.nextLine();
                            System.out.print("Genre:");
                            String genre = keyboard.nextLine();
                            System.out.print("Pages:");
                            int pages = keyboard.nextInt();
                            lib.addbook(bookTitle, author, genre, pages);
                            break;

                        case 2:
                            System.out.print("Title:");
                            String magazineTitle = keyboard.nextLine();
                            System.out.print("IssueNumber:");
                            int issueNumber = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Category");
                            String category = keyboard.nextLine();
                            System.out.print("PulisherYear:");
                            int publisherYear = keyboard.nextInt();
                            lib.addMagazine(magazineTitle, issueNumber, category, publisherYear);
                            break;

                        case 3:
                            System.out.print("Type in your namn:");
                            String name = keyboard.nextLine();
                            System.out.print("Type in your email:");
                            String email = keyboard.nextLine();
                            lib.addUser(name, email);
                            break;

                        case 4:
                            System.out.print("Type in the users id that youre trying to ban:");
                            String UserIdToBan = keyboard.nextLine();
                            lib.addSuspendedUser(UserIdToBan);
                            break;
                    }
                    break;

                case 3:
                    System.out.println("1. Book");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.print("What would you like to do?:");
                    int findChoice = keyboard.nextInt();
                    keyboard.nextLine();
                    switch (findChoice) {
                        case 1:
                            System.out.print("Type in the title of the book youre trying to find:");
                            String bookTitle = keyboard.nextLine();
                            lib.findBookByTitle(bookTitle);
                            break;

                        case 2:
                            System.out.print("Type in the title of the magazine youre trying to find:");
                            String magazineTitle = keyboard.nextLine();
                            lib.findMagazineByTitle(magazineTitle);
                            break;

                        case 3:
                            System.out.print("Type in the email of the user youre trying to find:");
                            String userEmail = keyboard.nextLine();
                            lib.findUserByEmail(userEmail);
                            break;
                    }
                    break;

                case 4:
                    System.out.println("1. Books");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.println("4. Suspended users");
                    System.out.print("What would you like to do?:");
                    int removeChoice = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (removeChoice) {
                        case 1:
                            System.out.print("Type in the title of the book youre trying to remove:");
                            String bookTitle = keyboard.nextLine();
                            lib.removeBookByTitle(bookTitle);
                            break;

                        case 2:
                            System.out.print("Type in the title of the magazine youre trying to remove:");
                            String magazineTitle = keyboard.nextLine();
                            lib.removeMagazineByTitle(magazineTitle);
                            break;

                        case 3:
                            System.out.print("Type in the email of the user youre trying to remove:");
                            String userEmail = keyboard.nextLine();
                            lib.removeUserByEmail(userEmail);
                            break;

                        case 4:
                            System.out.print("Type in the id of the suspended user youre trying to remove:");
                            String suspendedUserId = keyboard.nextLine();
                            lib.removeSuspendedUserById(suspendedUserId);
                            break;
                    }
                    break;

                case 5:
                    System.out.println("1. Books");
                    System.out.println("2. Magazines");
                    System.out.println("3. Users");
                    System.out.println("4. Suspended users");
                    System.out.print("What would you like to do?:");
                    int sortChoice = keyboard.nextInt();
                    switch (sortChoice) {
                        case 1:
                            lib.sortBooks();
                            break;
                    
                        case 2:
                            lib.sortMagazines();
                            break;

                        case 3:
                            lib.sortUsers();
                            break;
                    }
                    break;

                case 6:
                    System.out.print("Type in the email:");
                            String userId = keyboard.nextLine();
                    lib.canUserBorrow(userId);
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