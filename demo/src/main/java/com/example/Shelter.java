package com.example;
import java.util.*;

public class Shelter extends User{
private String location;
private ContactInfo info;
private ArrayList<Pet>shelterPets;

public Shelter(String name,String pass,String locaion, ContactInfo c)
{
super(name,pass);
this.location=locaion;
info=c;
shelterPets=new ArrayList<>();
}

public String getLocation() {
  return location;
}

public ContactInfo getInfo() {
  return info;
}

public ArrayList<Pet> getPets()
{
  return shelterPets;
}

public void setLocation(String location) {
  this.location = location;
}

public void setInfo(ContactInfo info) {
  this.info = info;
}

//managing the list of pets in each shelter thats can be happened byy
//adding pet to the list
public boolean addPet(Pet newone)
{
if(newone!=null&&!shelterPets.contains(newone))
{
  //so we will add it 
  shelterPets.add(newone);
  return true;
}
return false;
}

//or by deleting pet from teh list
public boolean deletePet(Pet pet)
{
  if(pet!=null&&shelterPets.contains(pet))
  {
    shelterPets.remove(pet);
    return true;
  }
  return false;
}


//or update specific pet in the list
public boolean updatePet(int petId,Pet newdata)
{
  //we will delegate the updation to the pet services 
  for(Pet temp:shelterPets)
  {
    if(temp.getID()==petId)
    {
      try{
        temp.update(newdata);
        return true;
      }
      catch(PetServicesExceptions p)
      {
       return false;
      }
      
    
    }
  }
  return false;
}

public void updateContactInfo(ContactInfo c)
{
  this.info.update(c);
}


}
