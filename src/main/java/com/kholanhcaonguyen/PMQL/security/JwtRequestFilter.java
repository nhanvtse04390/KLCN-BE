package com.kholanhcaonguyen.PMQL.security;

import com.kholanhcaonguyen.PMQL.service.JwtUtils;
import com.kholanhcaonguyen.PMQL.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Trích xuất JWT từ request
        String jwtToken = jwtUtils.extractJwtFromRequest(request);

        if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails != null) {
                // Tạo đối tượng UsernamePasswordAuthenticationToken từ UserDetails
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
