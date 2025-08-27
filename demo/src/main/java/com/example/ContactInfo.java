package com.example;

public class ContactInfo {
    private String phoneNumber;
    private String email;
    private String address;

    public ContactInfo(String phoneNumber, String email, String address) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    //  Setters
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

    // display the  contact info 
    public void displayContactInfo() {
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }

//we will use it in the delegation to improve the encapsulation 
    public void update(ContactInfo c ) {
      if (c.phoneNumber != null && !c.phoneNumber.isEmpty()) setPhoneNumber(c.phoneNumber);;
      if (c.email != null && !c.email.isEmpty()) setEmail(c.email);;
      if (c.address != null && !c.address.isEmpty()) setAddress(c.address);;
  }
  
}
