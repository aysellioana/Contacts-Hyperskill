package contactsS4;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 4500947781881581312L;
    private String adress;
    private LocalDateTime timeCreated;
    private LocalDateTime lastEdit;

    public Organization(){
        super();

    }
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
