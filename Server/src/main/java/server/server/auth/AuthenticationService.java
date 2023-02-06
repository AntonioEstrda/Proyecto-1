/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import server.server.Model.Domain.User;
import server.server.Model.Services.Impls.UserDetailCustomService;
import server.server.config.JwtService;
import server.server.utilities.Labels;
import server.server.utilities.errors.UserErrors;

@Service
@EnableTransactionManagement
public class AuthenticationService {

    @Autowired
    private UserDetailCustomService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Map<Labels, Object> register(RegisterRequest request) {

        Map<Labels, Object> auth = new HashMap();

        User user = User.builder()
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        AuthenticationResponse build = null;

        Map<Labels, Object> save = userService.save(user);
        List<String> errs = (List<String>) save.get(Labels.errors);
        if (errs.isEmpty()) {
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            System.out.println(userDetails.getAuthorities().toString());
            String jwtToken = jwtService.generateToken(userDetails);
            build = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        auth.put(Labels.errors, errs);
        auth.put(Labels.objectReturn, build);
        return auth;
    }

    public Map<Labels, Object> authenticate(AuthenticationRequest request) {
        Map<Labels, Object> returns = new HashMap();
        List<String> errs = new ArrayList();
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AuthenticationResponse build = null;
        if (authenticate.isAuthenticated()) {
            UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
            System.out.println(userDetails.getAuthorities().toString());
            String jwtToken = jwtService.generateToken(userDetails);
            build = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            errs.add(UserErrors.USR101.name());
        }
        returns.put(Labels.errors, errs);
        returns.put(Labels.objectReturn, build);
        return returns;
    }
}
