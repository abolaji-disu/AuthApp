package com.auth2app.auth2app.config.jwt;

import com.auth2app.auth2app.config.security.PrincipalUserUserDetailsService;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PrincipalUserUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = userDetailsService.loadUserByUsername(username);

           if (jwtService.isTokenValid(token, userDetails)){

               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }

        filterChain.doFilter(request, response);
    }
}
