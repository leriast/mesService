package com.common.dao.entity.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by root on 11/18/16.
 */
@Entity
@Table(name = "AUTHORITIES")
public class Role  implements GrantedAuthority {
    @Column(name = "ID_ROLE", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    private
    Set<User> user;
    @Column(name="AUTHORITY")
    private String authority;

    public Role() {
    }

    public Role(int id_role, String authority) {
        this.id_role = id_role;
        this.authority = authority;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
