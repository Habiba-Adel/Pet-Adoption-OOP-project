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

   //now each adapter can request specific pet to adopt it so he will request adoption and then will need to wait until the admin approve or reject
   public static void requestAdoption(Adoption obj ) throws AdopterServicesExceptions
   {
    //we will take it object directly rather than creating it from the start and if there is change happened in the attribute of the adoption class 
    //will lead us to change here too and that is bad design so the best option is to get the object directly 
    //now we will make the status of the obj is pending
    if(obj==null||obj.getAdopter() == null || obj.getAdopted() == null)throw new AdopterServicesExceptions("there is wrong on the adoption request");
    
    obj.setStatus(AdoptionStatus.PENDING);
    //and then add it in the queue of the pending requests
    AdminServices.getPendingRequests().add(obj);
    obj.getAdopter().incrementAdoptionNumber();

   }

   //this is for tracking the status of each adoption request 
   public static void trackAdoptionHistory(int adopterId) throws AdopterServicesExceptions {
    if(!allAdopters.containsKey(adopterId))
    throw new AdopterServicesExceptions("Adopter not found.");
    // delegate to the adopter object
    else
    allAdopters.get(adopterId).displayAdoptionHistory();//here we use delegation too 
}

}
