package com.example;

import java.time.LocalDateTime;

public class Notification {
   private Adoption adoption;  
    private LocalDateTime timestamp;

    public Notification(Adoption adoption) {
        this.adoption = adoption;
        this.timestamp = LocalDateTime.now();
    }

    public Adoption getAdoption() {
        return adoption;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
  }

    
