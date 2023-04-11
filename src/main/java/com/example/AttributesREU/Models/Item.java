package com.example.AttributesREU.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    public String name;
    @NotNull
    public String brand;

    @Column(precision = 10,scale=2)
    public Double Cost;

    @Column(precision = 10,scale=2)
    public Double length;

    @Nullable
    @Lob
    @Column( length = 1000)
    public byte[] photo;

    public String description;

    public String color;
    @NotNull
    public String articul;
    @NotNull
    public String professionalism;
    @NotNull
    public int releaseYear;

    @ManyToOne(optional =true)
    public Category category;

    @ManyToOne(optional =true)
    public Storage storage;
    @ManyToMany
    @JoinTable(name="itemorder",
            joinColumns=@JoinColumn(name = "itemid"),
            inverseJoinColumns=@JoinColumn(name = "orderid"))
    public List<ClientOrder> clientOrders;
    public Item() {
    }

    public Item(String name, String brand, Double cost, Double length, byte[] photo, String description, String color, String articul, String professionalism, int releaseYear, Category category, Storage storage, List<ClientOrder> clientOrders) {
        this.name = name;
        this.brand = brand;
        Cost = cost;
        this.length = length;
        this.photo = photo;
        this.description = description;
        this.color = color;
        this.articul = articul;
        this.professionalism = professionalism;
        this.releaseYear = releaseYear;
        this.category = category;
        this.storage = storage;
        this.clientOrders = clientOrders;
    }

    public List<ClientOrder> getOrders() {
        return clientOrders;
    }

    public void setOrders(List<ClientOrder> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getProfessionalism() {
        return professionalism;
    }

    public void setProfessionalism(String professionalism) {
        this.professionalism = professionalism;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
