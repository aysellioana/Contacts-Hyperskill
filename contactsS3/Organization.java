package contactsS3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Organization extends Contact{
    private String adress;
    private LocalDateTime timeCreated;
    private LocalDateTime lastEdit;
    Scanner scanner = new Scanner(System.in);
    public Organization(String name, String phoneNumber, String adress) {
        super(name, phoneNumber);
        this.adress = adress;
        timeCreated = LocalDateTime.now();
        lastEdit = LocalDateTime.now();

    }

    public String getAdress(){
        return adress;
    }

    public void setAdresss(String adress){
        this.adress = adress;
    }

    public String toStringO() {
        return "Organization name: " + getName() + '\n' +
                "Address: " + adress + '\n' +
                "Number: " + getPhoneNumber() + '\n'+
                "Time created: " + timeCreated.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + '\n' +
                "Time last edit: " + lastEdit.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + '\n';
    }

    public void updateLastEdit() {
        this.lastEdit = LocalDateTime.now();
    }
}
