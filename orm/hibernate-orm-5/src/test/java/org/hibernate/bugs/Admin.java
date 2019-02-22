/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.*;

import java.io.Serializable;

/**
 * @author sukhvir
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User implements Serializable {

    @Column(name = "type")
    @Convert(converter = AdminTypeConverter.class)
    private AdminType type;

    public Admin() {
    }

    public Admin(String username, String password, String email, AdminType type) {
        super(username, password, email, 0l);
        this.type = type;
    }

    @Override
    public UserType getUserType() {
        return UserType.Admin;
    }


    public AdminType getType() {
        return type;
    }

    public void setType(AdminType type) {
        this.type = type;
    }
}
