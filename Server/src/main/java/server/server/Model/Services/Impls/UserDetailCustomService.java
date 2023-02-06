/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOUser;
import server.server.Model.Domain.Rol;
import server.server.Model.Domain.User;
import server.server.Model.Access.DAORol;
import server.server.utilities.Labels;
import server.server.utilities.errors.UserErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class UserDetailCustomService implements UserDetailsService {

    @Autowired
    private DAOUser repouser;

    @Autowired
    private DAORol repoRol;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repouser.findByUsername(username).orElse(null);
        if (user != null) {
            Set<Rol> roles = repoRol.findByUserId(user.getId());
            System.out.println(roles.toString()); 
            CustomUserDetails customUserDetails = new CustomUserDetails(user, roles);
            return customUserDetails;
        }

        throw new UsernameNotFoundException("User not found");
    }

    @Transactional(value = "DataTransactionManager", readOnly = true)
    public User findByUsername(String username) {
        return repouser.findByUsername(username).orElse(null);
    }

    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(User user) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        User findByUsername = repouser.findByUsername(user.getUsername()).orElse(null);
        if (findByUsername != null) {
            errors.add(UserErrors.USR102.name());
        } else {
            user = repouser.save(user);
        }
        returns.put(Labels.objectReturn, user);
        returns.put(Labels.errors, errors);
        return returns;
    }
    
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Long findAsscDepartment(String username) {
        return repouser.findByDepartmentId(username);
    }

}
