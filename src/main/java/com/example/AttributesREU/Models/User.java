package com.example.AttributesREU.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class User {
    public User() {
    }

    public User(String name, String surname, String patronymic, String username, String password, @Nullable String phonenumber, Set<Role> role) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 30, message = "Поле должно содержать не менее 3х и не более 30 символов")
    private String name;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 30, message = "Поле должно содержать не менее 3х и не более 30 символов")
    private String surname;


    private String patronymic;

    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,30}$",
            message = "Логин должен быть не более 30 и не менее 5 символов")
    private String username;
    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")


    private String password;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean active;
    @Nullable
    @Pattern(regexp = "(^$|[0-9]{11})")
    private String phonenumber;
    @ElementCollection(targetClass = Role.class,
            fetch = FetchType.EAGER)
    @CollectionTable(name="role",
            joinColumns = @JoinColumn(name="userid"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    @OneToMany(mappedBy = "user", fetch =FetchType.LAZY)
    private Collection<Cheque> cheque;
    public Long getId() {
        return id;
    }


    public User(String name, String surname, String patronymic, String username, String password, Boolean active, @Nullable String phonenumber, Set<Role> role, Collection<Cheque> cheque) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.active = active;
        this.phonenumber = phonenumber;
        this.role = role;
        this.cheque = cheque;
    }

    public Collection<Cheque> getCheque() {
        return cheque;
    }

    public void setCheque(Collection<Cheque> cheque) {
        this.cheque = cheque;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(@Nullable String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
