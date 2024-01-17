package contactsS1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.print("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.print("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Contact c1 = new Contact(name, surname, phoneNumber);
        System.out.print("A record created!\n" +
                "A Phone Book with a single record created!");
    }
}
