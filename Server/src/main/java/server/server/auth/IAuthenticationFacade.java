/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.auth;

import org.springframework.security.core.Authentication;

/**
 *
 * @author anmon
 */
public interface IAuthenticationFacade {

    public Authentication getAuthentication();

    public Object getPrincipal();
}
