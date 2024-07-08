package com.franciscoosorio.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.franciscoosorio.todolist.user.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.var;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/")) {
            String authorization = request.getHeader("Authorization");

            String authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            String authString = new String(authDecode);
            
            String[] credentials = authString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            var user = userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
            }else{
                var passwordVerify = new BCryptPasswordEncoder().matches(password, user.getPassword());
                if (passwordVerify) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                }
                else{
                    response.sendError(401);
                }
            }
        }else{
            filterChain.doFilter(request, response);
        }
        
    }
}
