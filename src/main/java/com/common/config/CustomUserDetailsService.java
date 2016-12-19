package com.common.config;

import com.common.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        com.common.dao.entity.user.User user = userService.getUserByLogin((ssoId));
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(com.common.dao.entity.user.User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getId_role().getAuthority()));
        System.out.print("authorities :"+authorities);
        return authorities;
    }

}