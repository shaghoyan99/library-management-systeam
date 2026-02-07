package com.carrental.librarymanagementsysteam.service.security;

import com.carrental.librarymanagementsysteam.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Objects;

@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user){
        super(user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user =user;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CurrentUser that = (CurrentUser) object;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }
}
