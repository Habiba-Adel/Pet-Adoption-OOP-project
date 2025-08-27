package com.example;
import java.util.HashMap;
import java.util.Map;
 

public class UserServices {
  //this class will having all the methods will needed to manage the user profilies 
  // and will having a list of all users in our system to can use them easily in managing them 

private static Map<String, User> allUsers = new HashMap<>();//i will use the map cause to make the checking on the user name in o(1) rather than making it o(n)

//we need getter for the all users and we will not make add to protect the data
public static Map<String,User> getAllUsers ()
{
  return allUsers;
}


 public static boolean logIn(String userName, String password)
 {
   if(!allUsers.containsKey(userName))
   {
   throw new UserServicesExceptions("This user name not existed in the system ");
    //return false;
   }

   //else that means the user name is existed so what is remaining is to compre with the password 
   if(allUsers.get(userName).getPassword().equals(password))//casue == will compare the references not the actual data be careful
   return true;
   else
   throw new UserServicesExceptions("The entered password is not correct ");
   //return false;

 }

 //here we learning more about the java reflection API nad how to use generics in coding and making the code more flexable
 public static  User createUser(String userName,String password, Class <? extends User> userType)//to tell it you will taking an object from type class and it is generics from any class extends user class or user itself 
 {
  //adding exception handling
  try
  {
    //here the getconstructor method is reflection method and it takes class objects to know whcih constructor in the class will match this paramters types and if we found take the parameter values and then pass to them this values by new instance 
    return userType.getConstructor(String.class,String.class).newInstance(userName,password);
  }
  catch (Exception e){
    throw new UserServicesExceptions("Failed to create user");
    // return null;
  }
 }

 public static  boolean register(String userName,String password,Class <? extends User> userType)//it is more proffessional to take the needed data as input rather than taking them from the user as an input 
 {
//first we need to make the username unique to can know which user want to log in now and make it more secure
//so we will need to check about the user names 
if(allUsers.containsKey(userName))//if it contain so we can not continue registering
throw new UserServicesExceptions("This user name is already existed and it must be unique");

User newUser=createUser(userName, password, userType);
if(newUser!=null)
{
  allUsers.put(userName, newUser);
  return true;
}
throw new UserServicesExceptions("there is problem in creating object for you");


 }


//it will be handled later if i will store the data in files/data base or handling it in the gui
 public void logOut()
 {
//that means i will need to 

 }

//now what about if there is any user in the system want to change its name or its password we will use delegation here to imporve encapsulation 
//and it is better to update username seprate and password seprate 
 public static void updateUsername(String oldUsername, String newUsername) {
  if (!allUsers.containsKey(oldUsername)) {//firs ensure it is already existed 
      throw new UserServicesExceptions("User with username '" + oldUsername + "' does not exist.");
  }
  if (allUsers.containsKey(newUsername)) {
      throw new UserServicesExceptions("Username '" + newUsername + "' is already taken.");
  }

  User user = allUsers.remove(oldUsername);
  user.setUserName(newUsername);            
  allUsers.put(newUsername, user);         
}

//we will passing to it the user name who wants to change its pass and the new pass 
public static void updatePassword(String username, String newPassword) {
  if (!allUsers.containsKey(username)) {
      throw new UserServicesExceptions("User with username '" + username + "' does not exist.");
  }

  User user = allUsers.get(username);
  user.setPassword(newPassword);
}


}
