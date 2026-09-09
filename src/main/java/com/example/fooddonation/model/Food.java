package com.example.fooddonation.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "food")
public class Food {

    public enum Status {
        AVAILABLE, BOOKED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foodType;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.AVAILABLE;

    // Donor relationship
    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;

    // Receiver relationship (optional, null until booked)
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    // Constructors
    public Food() {}

    public Food(String foodType, int quantity, String contactInfo, User donor) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.contactInfo = contactInfo;
        this.donor = donor;
        this.status = Status.AVAILABLE;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFoodType() { return foodType; }

    public void setFoodType(String foodType) { this.foodType = foodType; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getContactInfo() { return contactInfo; }

    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public User getDonor() { return donor; }

    public void setDonor(User donor) { this.donor = donor; }

    public User getReceiver() { return receiver; }

    public void setReceiver(User receiver) { this.receiver = receiver; }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food food)) return false;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
