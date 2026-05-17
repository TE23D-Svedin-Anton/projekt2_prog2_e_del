// Anton Svedin TE23D
package server.com;

import java.util.Scanner;

import server.Book;
import server.Magazine;

public class Main {
    public static void main(String[] args) {
        boolean close = false;
        Scanner keyboard = new Scanner(System.in);
        Library lib = new Library();

        while (!close) {

            System.out.println("Welcome to your local libary");
            System.out.println("1. Fetch books");
            System.out.println("2. Fetch magazines");
            System.out.println("3. Print fetched books or magazines");
            System.out.println("4. Add a book");
            System.out.println("5. Add a magazine");
            System.out.println("6. Close");
            System.out.print("Choose between 1-6:");

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

                case 6:
                    close = true;
                    keyboard.close();
                    System.out.println("Hope to see you again!");
                    break;
            }
        }
    }
}