package com.example;

public abstract class User { // i make it abstract cause we will not having an user instance it will not having any meaning
  /*
   * 
   * ID, username, password, role (admin, adopter).
   * the role for now we will nto need it cause we will make the another classes to inherit from it so we will not need the role 
   * 
   */
   private static int nextId=0;//this will be used to gneerate automatically the id for each user 
   private int id; //java naming conventions making any variable except constants in camel case
   private String password ;// i need to store it hashing after taking it from the user to ensure the data security
   private String userName;

 
  //now lets make the constructor 
  public User(String name,String password)
  {
    userName=name;
    this.password=password;
    id=++nextId;

  }

  //now putting the getters and setters to controlling the access to the data , applying the encapsulation , security , saving the data 
  public int getId() { //we do not need for setter for id cause it is auto generated 
    return id;
}

public String getUserName() {
    return userName;
}

public void setUserName(String userName) {
    if (userName != null && !userName.isEmpty()) {
        this.userName = userName;
    }
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    if (password != null && password.length() >= 6) { 
        this.password = password;
    }
}




}
