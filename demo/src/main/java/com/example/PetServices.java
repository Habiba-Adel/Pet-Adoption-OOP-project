package com.example;
import java.util.HashMap;
//the best option in the services classes to define all its methods with static to do not need to create an object from it 
public class PetServices {
  //first we will need to store all the pets we have until now in the system 
  //and do not forget that it taked objects not primitive type which means we will use INTEGER rather than int 
  private static HashMap<Integer,Pet>allPets=new HashMap<>();

  public static HashMap<Integer,Pet> getAllPets()
  {
  return allPets;
  }

  //add pet profile and it return if it is sucess or fail
 public static void  addPet(Pet hola) throws PetServicesExceptions
 {
  if(hola==null)throw new PetServicesExceptions("can not add null pet ");
  //we will add this one if it is not existed if it is not existed so we can not put it 
  if(allPets.containsKey(hola.getID()))
  {
    //here we need to throw a checked exception cause it is recoverale and it is mistake from the user 
    throw new PetServicesExceptions("this pet is already existed ");
  }
  
    allPets.put(hola.getID(), hola);
  

 }

  //delete pet profile 
 public static void deletePet(Pet hehe)throws PetServicesExceptions{
  if(hehe==null)throw new PetServicesExceptions("can not add null pet ");

  if(allPets.containsKey(hehe.getID()))
  {
   allPets.remove(hehe.getID());
  }
  else
  throw new PetServicesExceptions("this pet is not existed to delete it ");
 }



}
