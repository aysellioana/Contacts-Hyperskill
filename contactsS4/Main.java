package contactsS4;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "phonebook.dat";
        File file = new File(fileName);
//        if(!file.exists()){
//            try {
//                if(file.createNewFile()){
//                    System.out.println("File was created!");
//                }else{
//                    System.out.println("File could not be created");
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        PhoneBook phoneBook = new PhoneBook(fileName);
        Scanner scanner = new Scanner(System.in);

        String act;
        do{
            System.out.println();
            System.out.print("[menu] Enter action (add, list, search, count, exit):");
            act = scanner.nextLine().toLowerCase();

            switch (act){
                case "add":
                    phoneBook.addContact();
                    phoneBook.savePhonebookToFile();
                    break;
                case "list":
                    phoneBook.displayPhoneBook();
                    phoneBook.displayInfoContact();
                    break;
                case "search":
                    phoneBook.search();
                    break;
                case "count":
                    phoneBook.count();
                    break;
                case "exit":
                    break;
                default:
                    break;
            }
        }while(!act.equals("exit"));
    }

}
