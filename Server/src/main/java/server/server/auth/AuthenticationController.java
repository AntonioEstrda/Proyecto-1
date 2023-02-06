/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.auth;

import java.util.ArrayList;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.utilities.Labels;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request,
            Errors errors
    ) {
        ResponseEntity<AuthenticationResponse> response;
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }

        Map<Labels, Object> register = service.register(request);
        ArrayList<String> errors2 = (ArrayList<String>) register.get(Labels.errors);
        if (errors2.isEmpty()) {
            AuthenticationResponse res = (AuthenticationResponse) register.get(Labels.objectReturn);
            response = new ResponseEntity<>(res, headers, HttpStatus.OK);
        } else {
            headers.add(Labels.errors.name(), errors2.toString());
            response = new ResponseEntity<>(null, headers, HttpStatus.NOT_MODIFIED);
        }

        return response;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request,
            Errors errors
    ) {
        ResponseEntity<AuthenticationResponse> response;
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }

        Map<Labels, Object> authenticate = service.authenticate(request);
        ArrayList<String> errors2 = (ArrayList<String>) authenticate.get(Labels.errors);
        if (errors2.isEmpty()) {
            AuthenticationResponse res = (AuthenticationResponse) authenticate.get(Labels.objectReturn);
            response = new ResponseEntity<>(res, headers, HttpStatus.OK);
        } else {
            headers.add(Labels.errors.name(), errors2.toString());
            response = new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
