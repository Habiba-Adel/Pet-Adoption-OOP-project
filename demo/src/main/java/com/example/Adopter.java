package com.example;
import java.util.ArrayList;

public class Adopter extends User {
  //here it having the same user attributes but with just w extra things contact information and adoption history
  private ContactInfo info;
  private ArrayList<Adoption>adoptionHistory;
  private Integer numOfAdoptions;
  //this both for demograohic statical reports 
  private int age;
  private String location;
  private boolean gender;//0 for man and 1 for woman


  //constructor
  public Adopter(String name,String password,ContactInfo c,int age,String location, boolean gender)
  {
    super(name, password);
    info=c;
    adoptionHistory=new ArrayList<>();
    numOfAdoptions=0;
    this.age=age;
    this.location=location;
    this.gender=gender;
  }


  //getters and setters 
public ContactInfo getInfo() {
    return info;
}

public ArrayList<Adoption> getAdoptionHistory() {
    return adoptionHistory;
}


public void setInfo(ContactInfo info) {
    this.info = info;
}

public void setAdoptionHistory(ArrayList<Adoption> adoptionHistory) {
    this.adoptionHistory = adoptionHistory;
}

public Integer getNumberOfAdoptions(){return numOfAdoptions;}

public void incrementAdoptionNumber(){numOfAdoptions++;}

public int getAge(){return age;}
public void setAge(int age){this.age=age;}

public String getLocation(){return location;}
public void setLocation(String s){location=s;}

public boolean getGender(){return gender;}



//now we will define the edit adopter profile method here casue the same reason of the pet update method too 
//in the adopter it can only update the contact info cause if he wants to change the username or passwors so it is relaed to the 
//user class itself not special to the adopter class and the second thing here we will use the delegation 
//and that will happen by making the contact info class update itself without entering any any details 
public void updateContactInfo(ContactInfo c ) throws AdopterServicesExceptions {

  this.info.update(c); // delegation

}

//the last thing we can make here is to adding the method of updating the adoption history 
   //tracking the adoption history is meaning that can add adoption to the history of all the adoption history and can see display all of them 
   //and track the status it is meaning that can change specific adoption status into another one 
   /*
    * 

    we will put this methods into the adapter class cause it is each adapter its role is to handle its data 
     the adapter service its role is to handle all the adapters in abstraction way
    */


//tracking all the adoption history
//we will make it boolean to can know if it is success or fail
public boolean addAdoption(Adoption adoption) {
  if (adoption != null) {
      this.adoptionHistory.add(adoption);
      return true;
  }
  else
  return false;
}

// display all adoptions
public void displayAdoptionHistory() {
  for (Adoption adoption : this.adoptionHistory) {
      System.out.println("Adoption ID: " + adoption.getId() +
                         ", Pet: " + adoption.getAdopted().getName() +
                         ", Status: " + adoption.getStatus() +
                         ", Date: " + adoption.getAdoptionDate());
  }
}

//tracing the status 
// Update status of a specific adoption
public void updateAdoptionStatus(int adoptionId, AdoptionStatus newStatus) {
  for (Adoption adoption : adoptionHistory) {
      if (adoption.getId() == adoptionId) {//cause each adoption having its special id and we can not comparing 2 objects togather directly cause it will compare memory addredd reference and that is will give always wrong 
          adoption.setStatus(newStatus);
          break;
      }
  }
}


//now we need to override the notification message to the adopter 
public void getNotificationMessage(Notification n )
{
System.out.println("your request for pet "+n.getAdoption().getAdopted().getName()+ "its status is "+ n.getAdoption().getStatus());
}

}
