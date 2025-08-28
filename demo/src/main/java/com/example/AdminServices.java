package com.example;
import java.util.*;
import java.time.LocalDate;

public class AdminServices {
  //now the admin will having all queue of all pending requests for adoption until now to can get them to approve or reject
  //------------------------OUR DATA STRUCUTRES THAT ADMIN SERVICES HANDLE ---------------------------------------------
  private static Queue<Adoption> pendingRequests=new LinkedList<>();
  //we need this data strucuture to store on it each year with the trends on it 
  private static HashMap<Integer,HashMap<Species,Integer>>yearTrends=new HashMap<>();




  
  public static Queue<Adoption> getPendingRequests(){return pendingRequests;}
  //now the admin can reject or allow accept the pending request 
  public static void acceptRequest(Adoption d) throws AdminServicesExceptions
  {
    if(d==null)
    {
      throw new AdminServicesExceptions("there is wrong on the adoption object");
    }
    //firs thing we must ensure about it is checking if the adoption is already existed in the pending requests
    if(!pendingRequests.contains(d))throw new AdminServicesExceptions("this request is not a pending request ");
    d.setStatus(AdoptionStatus.APPROVED);
    d.getAdopted().setAdoptionAvailable(false);
    d.getAdopted().getPetShelter().incrementNumberOfAdoptions();//update the adoptions counter for the shelter 
    //and then we will need to remove it from the pending requests
    pendingRequests.remove(d);

    //and we can add it to the adoopter history
    d.getAdopter().addAdoption(d);

    //here in the accepted we will send notification to the adopter that notifiaction is accepted and will send to the shelter tha its pet is adopted 
    Notification newone=new Notification(d);//adding notification to the adopter 
    d.getAdopter().addNotification(newone);
    Shelter temp=d.getAdopted().getPetShelter();//adding the notification to the shelter 
    if (temp != null) temp.addNotification(newone);


    //and we will need here to save it in the year trends data structure to can track them easily there and efficient 
    handlingYearTrend(d);
  }
  //in the reject action there is no any update happened to the shelter 
  public static void rejectRequest(Adoption d) throws AdminServicesExceptions
  {
    if(d==null)
    {
      throw new AdminServicesExceptions("there is wrong on the adoption object");
    }
    if(!pendingRequests.contains(d))throw new AdminServicesExceptions("this request is not a pending request ");

    d.setStatus(AdoptionStatus.REJECTED);
    pendingRequests.remove(d);
    //adding the request in the adopter history 
    d.getAdopter().addAdoption(d);
//putting the notification 
    Notification newone=new Notification(d);
    d.getAdopter().addNotification(newone);
  }

  public static void displayAllRequests() {
    if (pendingRequests.isEmpty()) {
        System.out.println("No pending adoption requests.");
        return;
    }

    System.out.println("=== Pending Adoption Requests ===");
    for (Adoption adoption : pendingRequests) {
        String adopterName = adoption.getAdopter().getUserName(); // assuming getUsername() exists in User
        String petName = adoption.getAdopted().getName();
        int adoptionId = adoption.getId();
        AdoptionStatus status = adoption.getStatus();

        System.out.printf("Adoption ID: %d | Adopter: %s | Pet: %s | Status: %s%n",
                          adoptionId, adopterName, petName, status);
    }
    System.out.println("================================");
}

  //now this method will handling the year trend
  public static void handlingYearTrend(Adoption d)
  {
    //the first thing we neeed to ensure the adoption object is okay
    if(d==null)throw new RuntimeException("there is wrong on passing adoption object ");
  //then we need to extract the year of the adoption
  int year=d.getAdoptionDate().getYear();
  //and we want to make the species counter ready 
  yearTrends.putIfAbsent(year, new HashMap<>());
  //we need fisrt to check if the year key is existed or no
  HashMap<Species,Integer> temp=yearTrends.get(year);
  //and then we need to access this hash map
  Species stemp=d.getAdopted().getSpecies();
  int counter=temp.getOrDefault(stemp, 0);
  temp.put(stemp,counter+1);
  } 


  public static void displayShelterAdoptions() {
    if (ShelterServices.getAllShelters().isEmpty()) {
        System.out.println("No shelters available.");
        return;
    }

    System.out.println("=== Number of Adoptions per Shelter ===");
    for (Shelter shelter : ShelterServices.getAllShelters().values()) {
        System.out.printf("Shelter: %s | Adoptions: %d%n", 
                          shelter.getUserName(), 
                          shelter.getNumberOfAdoptions());
    }
    System.out.println("=======================================");
}


  //now lets implement the method that will display or report the year trending 
  public  static void  displayYearAdoptionTrend()
  {
 // get the current year
 int currentYear = LocalDate.now().getYear();

 // check if this year exists in trends
 if (!yearTrends.containsKey(currentYear)) {
     System.out.println("No adoption trends found for year " + currentYear);
     return;
 }

 HashMap<Species, Integer> trends = yearTrends.get(currentYear);

 if (trends.isEmpty()) {
     System.out.println("No adoption records found for year " + currentYear);
     return;
 }

 // sort entries by adoption count descending

 /*
  in java the hash map you can catch the key and value both in the same time and catch them and that by using the map<entry > this will for just 
  one key and its value but to get all the keys values you need to use the entry set 


  */

 List<Map.Entry<Species, Integer>> sortedList = new ArrayList<>(trends.entrySet());
 sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));//the order of putting here a first or b affects in the sorting order

 System.out.println("=== Adoption Trends for " + currentYear + " ===");
 for (Map.Entry<Species, Integer> entry : sortedList) {
     System.out.printf("Species: %s | Adoptions: %d%n", entry.getKey(), entry.getValue());
 }
 System.out.println("==========================================");
  }


  //now we will handle this part of statistics List of adopters with the highest number of adoptions
 public static void displayHighestAdopters()
 {
  
  //and then we need to put this hash map in list 
  List<Adopter> sortedAdopters=new ArrayList<>(AdopterServices.getAllAdopters().values());
    //then sorting it by using the lambda expression 
    sortedAdopters.sort((a,b) -> b.getNumberOfAdoptions().compareTo(a.getNumberOfAdoptions()) );//this b and then a to sort descnding 

    //and now display 
    System.out.println("=== Top 5 Adopters ===");
    // Print only the first 5 (or fewer)
    int limit = Math.min(5, sortedAdopters.size());
    for (int i = 0; i < limit; i++) {
      Adopter adopter =  sortedAdopters.get(i);
         
        int count =adopter.getNumberOfAdoptions();

        System.out.printf("%d. Adopter: %s | Adoptions: %d | Age: %d | Location: %s%n",
                          i + 1,
                          adopter.getUserName(),
                          count,
                          adopter.getAge(),
                          adopter.getLocation());
    }
    System.out.println("=======================");
  };

//now lets handle the second point in the report statistics that related to the adapters
//we will display adopters based on demographic information which will be age, location , gender


public static void displayDimographicalInformation()//this will print for each adopter one 
{
  if (AdopterServices.getAllAdopters().isEmpty()) {
    System.out.println("No adopters registered.");
    return;
}

System.out.println("=== Adopter Demographic Information ===");
//this will display the information in table with fixed size you can control about the size of them 
System.out.printf("%-15s %-5s %-15s %-10s %-10s%n","Adopter", "Age", "Location", "Gender", "Adoptions");
System.out.println("------------------------------------------------------------");

for (Adopter adopter : AdopterServices.getAllAdopters().values()) {
    String genderText = adopter.getGender() ? "Female" : "Male";

    System.out.printf("%-15s %-5d %-15s %-10s %-10d%n",
            adopter.getUserName(),
            adopter.getAge(),
            adopter.getLocation(),
            genderText,
            adopter.getNumberOfAdoptions());
}


System.out.println("=======================================");
}




 }





