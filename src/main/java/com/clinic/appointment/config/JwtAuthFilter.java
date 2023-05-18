package com.clinic.appointment.config;/* @auther a.elsaid on 18/05/2023*/

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService2;
    private final BasicTokenUtil basicTokenUtil;
    private final String tokenHeader;

    public JwtAuthFilter( UserDetailsService userDetailsService2, String tokenHeader, BasicTokenUtil basicTokenUtil) {
        this.userDetailsService2 = userDetailsService2;
        this.tokenHeader = tokenHeader;
        this.basicTokenUtil = basicTokenUtil;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


       final String requestHeader = request.getHeader(Authorization.class.toString());
        if (requestHeader == null || !requestHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = null;
        String userEmail = io.javabrains.springsecurityjwt.util.JwtUtils.;
        String jwtToken = requestHeader.substring(7);
        username = basicTokenUtil.getUsernameFromToken(jwtToken);
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService2.loadUserByUsername(userEmail);
            final boolean isValidToken;

            if(isValidToken){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request,response);

    }
}
