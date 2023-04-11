package com.example.AttributesREU.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    Администратор, Клиент, Поставщик;
    @Override
    public String getAuthority() {
        return name();
    }

}
