package com.example;

import java.time.LocalDate;

public class Adoption {
  private int id;
  private Pet adopted;
  private Adopter adopter;
  private AdoptionStatus status;
  private LocalDate adoptionDate;

  public Adoption(int id, Pet adopted, Adopter adopter, AdoptionStatus status) {
    this.id = id;
    this.adopted = adopted;
    this.adopter = adopter;
    this.status = status;
    this.adoptionDate = LocalDate.now();
}


public int getId() {
    return id;
}

public Pet getAdopted() {
    return adopted;
}

public Adopter getAdopter() {
    return adopter;
}

public AdoptionStatus getStatus() {
    return status;
}

public LocalDate getAdoptionDate() {
    return adoptionDate;
}


public void setId(int id) {
    this.id = id;
}

public void setAdopted(Pet adopted) {
    this.adopted = adopted;
}

public void setAdopter(Adopter adopter) {
    this.adopter = adopter;
}

public void setStatus(AdoptionStatus status) {
    this.status = status;
}

public void setAdoptionDate(LocalDate adoptionDate) {
    this.adoptionDate = adoptionDate;
}

}
