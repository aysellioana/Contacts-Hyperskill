package contactsS3;

public class Contact {
    private static int nextContactNumber = 1;
    private int contactNumber;
    private String name;

    private String phoneNumber;

    public Contact(String name, String phoneNumber){
        this.name = name;
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


    public boolean hasNumber(){
        return !phoneNumber.isEmpty();
    }
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contactNumber).append(". ").append(name);

        if (this instanceof Person) {
            stringBuilder.append(" ").append(((Person) this).getSurname());
        } else if (this instanceof Organization) {
        }

        return stringBuilder.toString();
    }




    public boolean isNumberValid(String phoneNo) {
        String validatedPhoneNumber = RegexValidator.validateAndResetPhoneNumber(phoneNo);
        // Returnează true doar dacă numărul de telefon este valid și diferit de "" sau "[no number]"
        return !"".equals(validatedPhoneNumber) && !"[no number]".equals(validatedPhoneNumber);
    }


}
