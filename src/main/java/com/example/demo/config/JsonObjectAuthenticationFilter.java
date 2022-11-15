package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public JsonObjectAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            } //reading request, appending to string
            LoginCredentials authRequest = objectMapper.readValue(sb.toString(), LoginCredentials.class); // mapping stringed request to LoginCredentials object(for json)
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            ); // creating token from passed LoginCredentials (json)
            setDetails(request, token); //(setting context, assigning token to request (identifying whether it's authorized or not)
            return this.getAuthenticationManager().authenticate(token); /* returning Authentication to determine whether to call succes
                                                                           or failure auth handler (by Delegator) */
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}