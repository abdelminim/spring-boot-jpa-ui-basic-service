package com.clinic.appointment.config;
/*
* @auther a.elsaid
* on 18/05/2023
* */

import com.clinic.appointment.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDao userDao;
    private final BasicTokenUtil basicTokenUtil;
    private final JwtUtils jwtUtils;

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
        String jwtToken = requestHeader.substring(7);
        String username = basicTokenUtil.getUsernameFromToken(jwtToken);
        String userEmail = jwtUtils.extractUsername(jwtToken);
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDao.findUserByEmail(userEmail);//fetch userDetailsService
            final boolean isValidToken;

            if(jwtUtils.isValidToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request,response);

    }
}
