package com.example.AttributesREU.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotNull
    public Date date_of_order;
    @NotNull
    public Double summary;
    @NotNull
    public int INN=1094284981;
    @Nullable
    @Pattern(regexp = "(^$|[0-9]{11})")
    public String phoneNumber="78542883584";
    @ManyToOne(optional =true)
    public User user;
    @ManyToOne(optional =true)
    public ClientOrder clientOrder;

    public Cheque() {
    }

    public Cheque(Date date_of_order, Double summary, int INN, @Nullable String phoneNumber, User user, ClientOrder clientOrder) {
        this.date_of_order = date_of_order;
        this.summary = summary;
        this.INN = INN;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.clientOrder = clientOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public Double getSummary() {
        return summary;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    public int getINN() {
        return INN;
    }

    public void setINN(int INN) {
        this.INN = INN;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClientOrder getOrder() {
        return clientOrder;
    }

    public void setOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }
}
