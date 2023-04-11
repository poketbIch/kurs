package com.example.AttributesREU.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Supplier {
    public Supplier() {
    }

    public Supplier(String contactInfo, @Nullable String phoneNumber, Collection<Storage> storages) {
        this.contactInfo = contactInfo;
        this.phoneNumber = phoneNumber;
        this.storages = storages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull

    private String contactInfo;
    @Nullable

    private String phoneNumber;

    public Collection<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Collection<Storage> storages) {
        this.storages = storages;
    }

    @OneToMany(mappedBy = "supplier", fetch =FetchType.LAZY)
    public Collection<Storage> storages;

}
