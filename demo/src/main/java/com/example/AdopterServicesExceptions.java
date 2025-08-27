package com.example;

//we will make it checked cause its usage in the cases of the user mistakes and it can be recoverable 
public class AdopterServicesExceptions extends Exception {
  public AdopterServicesExceptions(String mass)
  {
    super(mass);
  }
}
