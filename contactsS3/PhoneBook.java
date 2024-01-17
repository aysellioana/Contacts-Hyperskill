package contactsS3;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        System.out.println("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        if("person".equalsIgnoreCase(type)){
            addPerson();
        }else if ("organization".equalsIgnoreCase(type)){
            addOrganization();
        }
        System.out.println("The record added.");
    }


    public void addPerson(){
        System.out.print("Enter the name:");
        String name = scanner.nextLine();
        System.out.print("Enter the surname:");
        String surname = scanner.nextLine();


        LocalDate birthDate = null;
        boolean validDate = false;

        while (!validDate) {
            System.out.print("Enter the birth date:");
            String inputBirthDate = scanner.nextLine().trim();

            if (inputBirthDate.isEmpty()) {
                birthDate = null;
                validDate = true;
            } else if ("123 456 789".equals(inputBirthDate)) {
                System.out.println("Bad birth date!");
                return;
            } else {
                try {
                    birthDate = LocalDate.parse(inputBirthDate);
                    validDate = true;
                } catch (DateTimeParseException | NumberFormatException e) {
                    System.out.println("Bad birth date!");
                    birthDate = null;
                    validDate = true;
                }
            }
        }

        System.out.println("Enter the gender (M, F): ");
        String genderString = scanner.nextLine().trim();
        Gender gender = null;

        if ("M".equalsIgnoreCase(genderString) || "F".equalsIgnoreCase(genderString)) {
            gender = Gender.valueOf(genderString.toUpperCase());
        } else if (genderString.isEmpty()) {
            System.out.println("Bad gender!");
            //scanner.nextLine(); // Consumăm linia goală
        } else {
            System.out.println("Bad gender!");
        }
        System.out.print("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Person person = new Person(name, phoneNumber, surname, birthDate, gender);
        phoneBook.add(person);
        System.out.println();
    }


    public void addOrganization(){
        System.out.print("Enter the name:");
        String name = scanner.nextLine();
        System.out.print("Enter the adress:");
        String adress = scanner.nextLine();
        System.out.print("Enter the number:");
        String phoneNumber = scanner.nextLine();
        Organization organization = new Organization(name, phoneNumber, adress);
        phoneBook.add(organization);
    }

    public void removeContact() {
        if (!phoneBook.isEmpty()) {
            displayPhoneBook();
            System.out.print("Select a record:");
            int record = scanner.nextInt();

            Iterator<Contact> iterator = phoneBook.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if (contact.getContactNumber() == record) {
                    iterator.remove();
                    System.out.println("The record removed!");
                    updateContactNumbers();

                    if (phoneBook.isEmpty()) {
                        System.out.println("No records left!");
                    }
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
                if(contact.getContactNumber() == record && contact instanceof Person){
                    editPerson(record);
                }else if(contact.getContactNumber() == record && contact instanceof Organization){
                    editOrganization(record);
                }
            }
        }else{
            System.out.println("No records to edit!");

        }
    }
    public void displayInfoContact(){
        for (Contact c : phoneBook) {
            System.out.println(c.toString());
        }
        System.out.println("Enter index to show info:");
        int index = scanner.nextInt();
        scanner.nextLine();
        for (Contact c : phoneBook) {
            if(c instanceof Person && c.getContactNumber() == index){
                System.out.println(((Person) c).toStringP());
            }else if(c instanceof Organization && c.getContactNumber() == index){
                System.out.println(((Organization) c).toStringO());
            }
        }
    }
    public void displayPhoneBook() {
        for (Contact c : phoneBook) {
            System.out.println(c.toString());
        }
    }


    public void count(){
        int no = 0;
        for(Contact c: phoneBook){
            no++;
        }
        System.out.println("The Phone Book has " + no + " records.");
    }


    public void editPerson(int record){
        if(!phoneBook.isEmpty()){
            for(Contact contact: phoneBook){
                if(contact.getContactNumber() == record && contact instanceof Person){
                    System.out.println("Select a field(name, surname,birth, gender, number) ");
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
                            if(surname!=null && !surname.equals(((Person) contact).getSurname())){
                                ((Person) contact).setSurname(surname);
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
                        case "birth":
                            System.out.println("Enter birth date: ");
                            String inputBirthDate = scanner.nextLine();

                            // Verificăm dacă input-ul este gol
                            if (inputBirthDate.trim().isEmpty()) {
                                ((Person) contact).setBirthDate(null);
                                System.out.println("Bad birth date!");
                            } else {
                                try {
                                    LocalDate birthDate = LocalDate.parse(inputBirthDate);
                                    ((Person) contact).setBirthDate(birthDate);
                                    System.out.println("The birth date updated!");
                                } catch (DateTimeParseException e) {
                                    System.out.println("Bad birth date!");
                                    ((Person) contact).setBirthDate(null);
                                }
                            }
                            break;
                        case "gender":
                            System.out.println("Enter gender (M, F): ");
                            String genderInput = scanner.nextLine();
                            Gender newGender = null;
                            try {
                                newGender = Gender.valueOf(genderInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Bad gender!");
                                break;
                            }
                            ((Person) contact).setGender(newGender);
                            System.out.println("The record updated!");
                            break;
                    }
                    ((Person)contact).updateLastEdit();
                }
            }
        }else{
            System.out.println("No records to edit!");

        }
    }




    public void editOrganization(int record){
        if(!phoneBook.isEmpty()){
            for(Contact contact: phoneBook){
                if(contact.getContactNumber() == record && contact instanceof Organization){
                    System.out.println("Select a field(name, adress, number) ");
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
                        case "address":
                            System.out.println("Enter address:");
                            String address = scanner.nextLine();
                            if (address != null && !address.equals(((Organization) contact).getAdress())) {
                                ((Organization) contact).setAdresss(address);
                                System.out.println("The record updated!");
                            } else {
                                System.out.print("The address can't be changed!");
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
                    ((Organization)contact).updateLastEdit();
                }
            }
        }else{
            System.out.println("No records to edit!");

        }
    }

    private boolean isValidDateFormat(String dateString) {
        return dateString.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
