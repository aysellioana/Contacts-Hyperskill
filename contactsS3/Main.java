package contactsS3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);


        String act;
        do{
            System.out.println();
            System.out.print("Enter action (add, remove, edit, count, info, exit):");
            act = scanner.nextLine().toLowerCase();

            switch (act){
                case "add":
                    phoneBook.addContact();
                    break;
                case "remove":
                    phoneBook.removeContact();
                    break;
                case "edit":
                    phoneBook.editContact();
                    break;
                case "count":
                    phoneBook.count();
                    break;
                case "info":
                    phoneBook.displayInfoContact();
                    break;
                case "exit":
                    break;
                default:
                    break;
            }
        }while(!act.equals("exit"));
    }

}
