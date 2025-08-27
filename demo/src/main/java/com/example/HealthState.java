package com.example;

//this health state enum in this project will be used only in the pet class but i put it in seperated class to make it 
//scalable and may when make this project bigger later we can reuse this one 

public enum HealthState {
  HEALTHY,
  SICK,
  INJURED,
  RECOVERING,
  CRITICAL
}