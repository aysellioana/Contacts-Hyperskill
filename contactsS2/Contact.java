package contactsS2;

public class Contact {
    private static int nextContactNumber = 1;
    private int contactNumber;
    private String name;
    private String surname;
    private String phoneNumber;

    public Contact(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        if(isNumberValid(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }else{
            this.phoneNumber = "[no number]";
        }
        this.contactNumber = nextContactNumber++;
    }
    public int getContactNumber(){
        return contactNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            if (!phoneNumber.isEmpty() && isNumberValid(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            } else {
                this.phoneNumber = "";
            }
            if(this.phoneNumber==""){
                this.phoneNumber= "[no number]";
            }
        } else {
            this.phoneNumber = "[no number]";
        }
    }



    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name!=null){
            this.name = name;
        }
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        if(surname!=null){
            this.surname = surname;
        }
    }

    public boolean hasNumber(){
        return !phoneNumber.isEmpty();
    }
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return contactNumber +". "+ name + " " + surname + ", " + phoneNumber;
    }


    public boolean isNumberValid(String phoneNo) {
        String validatedPhoneNumber = RegexValidator.validateAndResetPhoneNumber(phoneNo);
        // Utilizează validatedPhoneNumber în continuare în codul tău
        // De exemplu, poți să returnezi true dacă numărul validat nu este "[no number]"
        return !"[no number]".equals(validatedPhoneNumber);

    }

}
