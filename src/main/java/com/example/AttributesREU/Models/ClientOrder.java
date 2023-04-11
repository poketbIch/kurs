package com.example.AttributesREU.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotNull
    public int amount;
    @NotNull
    public String commentary;
    @OneToMany(mappedBy = "clientOrder", fetch =FetchType.LAZY)
    public Collection<Cheque> cheque;
    @ManyToMany
    @JoinTable(name="itemorder",
            joinColumns=@JoinColumn(name = "orderid"),
            inverseJoinColumns=@JoinColumn(name = "itemid"))
    public List<Item> items;
    public ClientOrder() {
    }
    public ClientOrder(int amount, String commentary, List<Item> items, Collection<Cheque> cheque) {
        this.amount = amount;
        this.commentary = commentary;
        this.items = items;
        this.cheque = cheque;
    }
    public Collection<Cheque> getCheque() {
        return cheque;
    }
    public void setCheque(Collection<Cheque> cheque) {
        this.cheque = cheque;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
