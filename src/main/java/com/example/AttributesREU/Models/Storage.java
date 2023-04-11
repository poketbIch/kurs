package com.example.AttributesREU.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    private String adresStorage;
    @ManyToOne(optional =true)
    public Supplier supplier;
    @OneToMany(mappedBy = "storage", fetch =FetchType.LAZY)
    private Collection<Item> items;

    public String getAdresStorage() {
        return adresStorage;
    }

    public void setAdresStorage(String adresStorage) {
        this.adresStorage = adresStorage;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Storage(String adresStorage, Supplier supplier, Collection<Item> items) {
        this.adresStorage = adresStorage;
        this.supplier = supplier;
        this.items = items;
    }

    public Storage() {
    }
}
