package contactsS3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

enum Gender{
    F, M
}
public class Person extends Contact{
    private String surname;
    private LocalDateTime timeCreated;
    private LocalDateTime lastEdit;
    private LocalDate birthDate;
    private Gender gender;
    Scanner scanner =  new Scanner(System.in);

    public Person(String name, String phoneNumber, String surname, LocalDate birthDate, Gender gender) {
        super(name, phoneNumber);
        this.surname = surname;
        this.gender = gender;
        timeCreated = LocalDateTime.now();
        lastEdit = LocalDateTime.now();
        setBirthDate(birthDate);
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        if(surname!=null){
            this.surname = surname;
        }
    }

    public Gender getGender(){
        return gender;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }

    public LocalDate getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        try {
            this.birthDate = birthDate;
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
    }


    public String toStringP() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(getName()).append('\n');
        stringBuilder.append("Surname: ").append(surname).append('\n');
        stringBuilder.append("Birth date: ").append(birthDate != null ? birthDate : "[no data]").append('\n');
        stringBuilder.append("Gender: ").append(gender != null ? gender : "[no data]").append('\n');
        stringBuilder.append("Number: ").append(getPhoneNumber()).append('\n');
        stringBuilder.append("Time created: ").append(timeCreated.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))).append('\n');
        stringBuilder.append("Time last edit: ").append(lastEdit.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))).append('\n');

        return stringBuilder.toString();
    }


    public void updateLastEdit() {
        this.lastEdit = LocalDateTime.now();
    }

}
