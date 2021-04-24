package hf.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import hf.exception.UnauthorizedException;
import hf.service.JWTService;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
    	if (request.getMethod().equals("OPTIONS")) {
			return true;
    	} else {
    		final String token = request.getHeader(HEADER_AUTH).split(" ")[1];
    		if(token != null && jwtService.isUsable(token)){
    			return true;
    		} else {
    			throw new UnauthorizedException();
    		}
    	}
    }
}
