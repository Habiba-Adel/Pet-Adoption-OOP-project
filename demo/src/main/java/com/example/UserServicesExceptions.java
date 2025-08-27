package com.example;

//this one must be checked cause it is used in cases where the user is wrong and there is a chance for the caller to revocer this 
public class UserServicesExceptions extends RuntimeException {
  public UserServicesExceptions (String message)
  {
    super(message);
  }
}
