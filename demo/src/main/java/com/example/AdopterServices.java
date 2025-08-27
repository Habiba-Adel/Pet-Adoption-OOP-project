package com.example;
import java.util.HashMap;

public class AdopterServices {
   private static HashMap<Integer, Adopter> allAdopters = new HashMap<>();
//it is must to define it static cause it will deal with static members
   public static HashMap<Integer, Adopter> getAllAdopters() {
       return allAdopters;
   }
   //the best choice here is to define all the service class methods static to do not must defining an object from it to can use teh enter methods 
   public static void addAdopter(Adopter adopter) throws AdopterServicesExceptions {
       if (adopter == null) {
           throw new AdopterServicesExceptions("Cannot add null adopter.");
       }

       int id = adopter.getId();
       if (allAdopters.containsKey(id)) {
           throw new AdopterServicesExceptions("Adopter with ID " + id + " already exists.");
       }

       allAdopters.put(id, adopter);
   }


   public static void deleteAdopter(Adopter adopter) throws AdopterServicesExceptions {

       if (adopter == null) {
           throw new AdopterServicesExceptions("Cannot delete null adopter.");
       }

       int id = adopter.getId();
       if (!allAdopters.containsKey(id)) {
           throw new AdopterServicesExceptions("Adopter with ID " + id + " does not exist.");
       }

       allAdopters.remove(id);
   }

   public static boolean isExisted(Adopter adopter) throws AdopterServicesExceptions
   {
    if(adopter==null) throw new AdopterServicesExceptions("we can not use null adopter object");
    if(allAdopters.containsKey(adopter.getId()))
    {
      return true;
    }
    else
    return false;
   }



   //we will seperate the update functionalities cause to make each method having just only one single task
   public static void updateAdopterContactInfo(int id, ContactInfo newInfo) throws AdopterServicesExceptions {
    Adopter adopter = allAdopters.get(id);
    if (adopter == null) {
        throw new AdopterServicesExceptions("Adopter with ID " + id + " does not exist.");
    }
    adopter.updateContactInfo(newInfo); 
}


   public static void updateAdopterName(String old,String neww)
   {
    UserServices.updateUsername(old, neww);
   }

   public static void updateAdopterPassword(String name,String newpass)
   {
    UserServices.updatePassword(name, newpass);
   }

   

}
