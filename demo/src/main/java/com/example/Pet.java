package com.example;

public class Pet {
    private static int counter = 1; // we will use it in the id auto generated 
    private int ID;
    private String name;
    private Species species;
    private String breed;
    private Integer age;
    private HealthState state;
    private Boolean adoptionAvailable;
    private Shelter owner;

    public Pet() {
        this.ID = counter++;
        this.name = "Unknown";
        this.species = Species.DOG;//this can be changed and puting the default value on it 
        this.breed = "Unknown";
        this.age = 0;
        this.state = HealthState.HEALTHY;
        this.adoptionAvailable=true;
        owner=null;
    }


    public Pet(String name, Species species, String breed, int age, HealthState state,boolean available,Shelter shelter) {
        this.ID = counter++;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.state = state;
        this.adoptionAvailable=available;
        owner=shelter;
    }

    //we can not making set for the id cause it is auto generated 
    public int getID() {
        return ID;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public HealthState getState() { return state; }
    public void setState(HealthState state) { this.state = state; }

    public boolean isAdoptionAvailable() { return adoptionAvailable; }
    public void setAdoptionAvailable(boolean adoptionAvailable) { this.adoptionAvailable = adoptionAvailable; }

    public Shelter getPetShelter(){return this.owner;}
    public void setPetShelter(Shelter s){this.owner=s;}

  
    
    //we will put the only method here is the update pet profile and that cause it is deponds on the pet attributes class and if there is change happened 
    //related to the pet attribute the update will also need to be update so the best otpion to put it here to change only one class rather than chaning 2 classes 
    void update(Pet newone) throws PetServicesExceptions
    {
      //here the user will send to us the value of the attribut need to be changes and teh rest of the attributes will be passed null
      //first we need to check if this pet is already existed or no 
      if(PetServices.getAllPets().containsKey(this.getID()))//if the caller pet object is existed or no 
      {
        //here we will make conditions about which attribute will be changed 
        if (newone.name != null && !newone.name.isEmpty()) setName(newone.name);
        if (newone.species!= null) setSpecies(newone.species);
        if (newone.breed!= null && !newone.breed.isEmpty()) setBreed(newone.breed);
        if (newone.age != null && newone.age >= 0) setAge(newone.age);
        if (newone.state != null) setState(newone.state);
        if (newone.adoptionAvailable != null) setAdoptionAvailable(newone.adoptionAvailable);
      }
      else 
        throw new PetServicesExceptions("this pet is not existed to update it ");
    }

    //now the last thing we need to make now is the display pet details 
    //and the best choice to define it is inside the pet class cause if there is any changes happened in the details or attributes just one class changed
    public void displayDetails() {//cause this will call it so there is no need fo passing pet object 
      System.out.println("Pet ID: " + getID());
      System.out.println("Name: " + getName());
      System.out.println("Species: " + getSpecies());
      System.out.println("Breed: " + getBreed());
      System.out.println("Age: " + getAge());
      System.out.println("Health State: " + getState());
      System.out.println("Available for Adoption: " + (isAdoptionAvailable() ? "Yes" : "No"));
  }
  

}
