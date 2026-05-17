// Anton Svedin TE23D Main, det är själva programet där metoder och annat körs.
//Här får man olika typer av alternativ som man kan välja mellan för att matcha ens behov.
package server.com;

import java.util.Scanner;

import server.Book;
import server.Magazine;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean close = false;
        Library lib = new Library();
        System.out.println("Welcome to your local libary");

        while (!close) {

            System.out.println("What would you like to do? Choose between 1-6");
            System.out.println("1. Fetch books");
            System.out.println("2. Fetch magazines");
            System.out.println("3. Print fetched books or magazines");
            System.out.println("4. Add a book");
            System.out.println("5. Add a magazine");
            System.out.println("6. Close");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    lib.fetchBooks();
                    break;

                case 2:
                    lib.fetchMagazines();
                    break;

                case 3:
                    System.out.print("Books or Magazines?:");
                    String answere = keyboard.nextLine().toLowerCase();
                    switch (answere) {
                        case "books":
                            for (Book b : lib.getBookShelf()) {
                                System.out.println(b);
                            }

                            break;

                        case "magazines":
                            for (Magazine m : lib.getMagazineShelf()) {
                                System.out.println(m);
                            }
                            break;
                    }
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
                    lib.addBook(bookTitle, author, genre, pages);

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
                    lib.addMagazine(magazineTitle, issueNumber, category, publisherYear);

                    break;

                case 6:
                    close = true;
                    keyboard.close();
                    System.out.println("Hope to see you again!");
                    break;
            }
        }
    }
}