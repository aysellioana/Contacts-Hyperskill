package contactsS2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);


        String act;
        do{
            System.out.print("Enter action (add, remove, edit, count, list, exit):");
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
                case "list":
                    phoneBook.displayPhoneBook();
                    break;
                case "exit":
                    break;
                default:
                    break;
            }
        }while(!act.equals("exit"));
    }

}
