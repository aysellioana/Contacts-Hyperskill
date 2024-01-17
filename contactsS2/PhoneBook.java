package contactsS2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    Scanner scanner = new Scanner(System.in);
    private List<Contact> phoneBook;

    public PhoneBook(){
        phoneBook = new ArrayList<>();
    }

    public void addContact(){
        System.out.print("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.print("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.print("Enter the number:");
        String phoneNumber = scanner.nextLine();
        Contact contact = new Contact(name, surname, phoneNumber);
        phoneBook.add(contact);
        System.out.println("The record added.");
    }

    public void removeContact() {
        displayPhoneBook();

        if (!phoneBook.isEmpty()) {
            System.out.print("Select a record:");
            int record = scanner.nextInt();

            Iterator<Contact> iterator = phoneBook.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if (contact.getContactNumber() == record) {
                    iterator.remove();
                    System.out.println("The record removed!");
                    updateContactNumbers();
                    return;
                }
            }

            System.out.println("Record not found!");
        } else {
            System.out.println("No records to remove!");
        }
    }

    private void updateContactNumbers() {
        for (int i = 0; i < phoneBook.size(); i++) {
            phoneBook.get(i).setContactNumber(i + 1);
        }
    }

    public void editContact(){
        displayPhoneBook();
        if(!phoneBook.isEmpty()){
            System.out.println("Select a record: ");
            int record = scanner.nextInt();
            scanner.nextLine();
            for(Contact contact: phoneBook){
                if(contact.getContactNumber() == record){
                    System.out.println("Select a field(name, surname, number) ");
                    String field = scanner.nextLine();
                    switch (field){
                        case "name":
                            System.out.println("Enter name:");
                            String name = scanner.nextLine();
                            if(name!=null && name != contact.getName()){
                                contact.setName(name);
                                System.out.println("The record updated!");
                            }else{
                                System.out.print("The name can't be changed!");
                            }
                            break;
                        case "surname":
                            System.out.println("Enter surname:");
                            String surname = scanner.nextLine();
                            if(surname!=null && surname != contact.getSurname()){
                                contact.setSurname(surname);
                                System.out.println("The record updated!");
                            }else{
                                System.out.print("The surname can't be changed!");
                            }
                            break;
                        case "number":
                            System.out.println("Enter number:");
                            String number = scanner.nextLine();
                            if (contact.isNumberValid(number)) {
                                contact.setPhoneNumber(number);
                                System.out.println("The record updated!");
                            } else {
                                System.out.println("Wrong number format!");
                                contact.setPhoneNumber("");
                            }
                            break;
                    }
                }
            }
        }else{
            System.out.println("No records to edit!");

        }
    }
    public void displayPhoneBook() {
        for (Contact c : phoneBook) {
            String phoneNumber = c.getPhoneNumber();
            if ("[no number]".equals(phoneNumber)) {
                System.out.println(c.getContactNumber() + ". " + c.getName() + " " + c.getSurname() + ", " + "[no number]");
            } else {
                System.out.println(c.toString());
            }
        }
    }


    public void count(){
        int no = 0;
        for(Contact c: phoneBook){
            no++;
        }
        System.out.println("The Phone Book has " + no + " records.");
    }

}
