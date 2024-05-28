package com.hg.hgblogback.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hg.hgblogback.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;


    //# 요청, 응답을 처리하는 필터 체인에서 호출될 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // TODO Auto-generated method stub
        try {
            String token = bearerTokenExtraction(request);
            if(token==null){
                filterChain.doFilter(request, response);
                return;
            }

            String email = jwtProvider.verification(token);
            if(email==null){
                filterChain.doFilter(request, response);
                return;
            }

            AbstractAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    } //* doFilterInternal */

    //# HTTP 요청에서 베어러 토큰 추출하는 메서드
    private String bearerTokenExtraction(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        
        boolean hasTextAuthorization = StringUtils.hasText(authorization);
        if(!hasTextAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

        String token = authorization.substring(7);
        return token;
    } //* bearerTokenExtraction */


} //* JwtAuthenticationFilter */


