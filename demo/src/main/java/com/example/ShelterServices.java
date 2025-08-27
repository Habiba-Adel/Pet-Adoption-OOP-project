package com.example;
import java.util.HashMap;


public class ShelterServices {
  //uniqure id and its object 
  private static HashMap<Integer, Shelter> allShelters = new HashMap<>();

  public static HashMap<Integer, Shelter> getAllShelters() {
      return allShelters;
  }
//we use throws cause we use checked exceptions the another way is to using the try catch blocks 
  public static void addShelter(Shelter shelter) throws ShelterServicesExceptions {
    if (shelter == null) throw new ShelterServicesExceptions("Cannot add null shelter.");
    if (allShelters.containsKey(shelter.getId())) {
        throw new ShelterServicesExceptions("Shelter with ID " + shelter.getId() + " already exists.");
    }
    allShelters.put(shelter.getId(), shelter);
}

public static void deleteShelter(Shelter shelter) throws ShelterServicesExceptions {
  if (shelter == null) throw new ShelterServicesExceptions("Cannot delete null shelter.");
  if (!allShelters.containsKey(shelter.getId())) {
      throw new ShelterServicesExceptions("Shelter with ID " + shelter.getId() + " does not exist.");
  }
  allShelters.remove(shelter.getId());
}

//now lets handle the edit shelter information profile 
//we can edit its username or password
public static void editShelterName(String old,String newone)
{
  UserServices.updateUsername(old, newone);
}

public static void editShelterPassword(String name,String newpass)
{
  UserServices.updatePassword(name, newpass);
}

//or we can update the contact info
public static void editShelterContact(int id, ContactInfo newInfo) throws ShelterServicesExceptions {
  Shelter shelter = allShelters.get(id);
  if (shelter == null) {
      throw new ShelterServicesExceptions("shelter with ID " + id + " does not exist.");
  }
  shelter.updateContactInfo(newInfo); 
}

//and maybe he wants to edit the location but i need first to check if the shelter existed or no 
public static void editShelterLocation(int id, String newLocation) throws ShelterServicesExceptions {
  Shelter shelter = allShelters.get(id);
  if (shelter == null) {
      throw new ShelterServicesExceptions("Shelter with ID " + id + " does not exist.");
  }
  shelter.setLocation(newLocation);
}



}
