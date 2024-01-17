package contactsS4;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;
    Scanner scanner = new Scanner(System.in);
    private List<Contact> phoneBook;
    private String fileName;

    public PhoneBook(String fileName){

        this.fileName = fileName;
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
        updateContactNumbers();
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
            String inputBirthDate = scanner.nextLine().trim();  //trim() -> remove leading and trailing whitespaces from a string

            if (inputBirthDate.isEmpty()) {
                birthDate = null;
                validDate = true;
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

    private void updateContactNumbers() {
        for (int i = 0; i < phoneBook.size(); i++) {
            phoneBook.get(i).setContactNumber(i + 1);
        }
    }

    public void editContact(int record){
        //displayPhoneBook();
        if(!phoneBook.isEmpty()){
            for(Contact contact: phoneBook){
                if(contact.getContactNumber() == record && contact instanceof Person){
                    editPerson(record);
                    break;
                }else if(contact.getContactNumber() == record && contact instanceof Organization){
                    editOrganization(record);
                }
            }
            savePhonebookToFile();
        }else{
            System.out.println("No records to edit!");
        }
    }
    public void displayInfoContact() {
        System.out.println("[list] Enter action ([number], back): ");
        readFromfile();

        String action = scanner.nextLine();
        switch (action) {
            case "back":
                return;
            default:
                try {
                    int contactNumberInput = Integer.parseInt(action.trim());
                    boolean contactFound = findContact(contactNumberInput, phoneBook);

                    if (!contactFound) {
                        System.out.println("Contact not found.");
                    } else {
                        recordAction(contactNumberInput, phoneBook);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number");
                }
        }
    }


    public void displayPhoneBook() {
        readFromfile();
        for (Contact c : phoneBook) {
            System.out.println(c.toString());
        }
    }


    public int count() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Contact> contactList = (List<Contact>) inputStream.readObject();
            phoneBook = new ArrayList<>(contactList);

            int no = phoneBook.size();
            System.out.println("The Phone Book has hhhhhhh " + no + " records.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The Phone Book has 0 records.");
        }
        return 0;
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
                                ((Person) contact).setGender(newGender);
                                System.out.println("The record updated!");
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Bad gender!");
                                break;
                            }
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

    public void savePhonebookToFile(){
       try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(phoneBook);
            //System.out.println("The contact was saved!");
        }catch(IOException e){
           e.printStackTrace();
       }
    }

    public void readFromfile(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
            phoneBook = (List<Contact>) objectInputStream.readObject();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void search(){
        System.out.println("Enter search query: ");
        int no = 0;
        List<Contact> newList = new ArrayList<>();
        String query = scanner.nextLine();
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            List<Contact> listcontact = (List<Contact>) inputStream.readObject();
            for(Contact contact: listcontact){
                if(contact instanceof Person){
                    Person person = (Person) contact;
                    if(person.getName().toLowerCase().contains(query.toLowerCase()) || person.getSurname().toLowerCase().contains(query.toLowerCase())
                    || person.getPhoneNumber().contains(query)
                    ){
                        no ++;
                        newList.add(person);
                    }
                }else if( contact instanceof Organization){
                    Organization organization = (Organization) contact;
                    if(organization.getName().toLowerCase().contains(query.toLowerCase())|| organization.getPhoneNumber().contains(query)){
                        no++;
                        newList.add(organization);
                    }
                }
            }
            System.out.println("Found " + no + " results: ");
            for(Contact contact: newList){
                if(contact instanceof Person){
                    System.out.println(contact.getContactNumber()+ " "+ contact.getName() + " "  +  ((Person)contact).getSurname());
                }else{
                    System.out.println(contact.getContactNumber()+ " "+ contact.getName());
                }
            }

            System.out.println("[search] Enter action([number], back, again): ");
            String action = scanner.nextLine();
            switch (action){
                case "back":
                    return;
                case "again":
                    search();
                    break;
                default:
                    try{
                        int contactNumberInput = Integer.parseInt(action);
                        findContact(contactNumberInput, newList);
                        recordAction(contactNumberInput, newList);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid number");
                    }
            }

        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(
                    "",e);
        }
    }

    private boolean findContact(int contactNumberInput, List<Contact> contacts){
        for(Contact contact: contacts){
            if(contact.getContactNumber() == contactNumberInput){
                if(contact instanceof Person){
                    System.out.println(((Person)contact).toStringP());
                }else if(contact instanceof Organization){
                    System.out.println(((Organization)contact).toStringO());
                }
                return true;
            }
        }
        return false;
    }

    private void recordAction(int contactNumber, List<Contact> contactList){
        boolean check= false;
        System.out.println("[record] Enter action (edit, delete, menu): ");
        String action = scanner.nextLine();
        do{
            switch (action){
                case "edit":
                    editContact(contactNumber);
                    check = true;
                    return;
                case "delete":
                    deleteContactByNumber(contactNumber);
                    recordAction(contactNumber, contactList);
                    //savePhonebookToFile();
                    break;
                case "menu":
                    return;
            }
        }while(check);
    }

    private void deleteContactByNumber(int contactNumber) {
        Iterator<Contact> iterator = phoneBook.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getContactNumber() == contactNumber) {
                iterator.remove();
                System.out.println("Contact with number " + contactNumber + " deleted successfully.");
                savePhonebookToFile();
                updateContactNumbers();
                return;
            }
        }
        System.out.println("Contact with number " + contactNumber + " not found.");
    }



}
