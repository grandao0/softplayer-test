package com.cassio.player.aop;

import com.cassio.player.annotations.OauthSecuredApi;
import com.cassio.player.exceptions.FilterException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Aspect
public class SecurityAspect {
    private static final String API_TOKEN_KEY = "X-API-Token";
    private static final String INSUFFICIENT_PRIVILEGES_MESSAGE = "Insufficient privileges.";
    private static final String SCOPE_CLAIM = "scope";

    public SecurityAspect() {
    }

    @Around("@annotation(com.cassio.player.annotations.OauthSecuredApi)")
    public Object logAdviseController(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String jwt = request.getHeader(API_TOKEN_KEY);
        if (jwt != null) {
            //DecodedJWT decodedJWT = this.validateMethodScopes(jwt, pjp);
            request.setAttribute("DECODED_JWT_TOKEN", null);
            return pjp.proceed();
        } else {
            throw new FilterException(401, INSUFFICIENT_PRIVILEGES_MESSAGE);
        }
    }

//    private DecodedJWT validateMethodScopes(String jwt, ProceedingJoinPoint pjp) throws FilterException {
//        DecodedJWT decodedJWT = JWT.decode(jwt);
//        MethodSignature signature = (MethodSignature)pjp.getSignature();
//        Method method = signature.getMethod();
//        OauthSecuredApi myAnnotation = (OauthSecuredApi)method.getDeclaredAnnotation(OauthSecuredApi.class);
//        List<String> methodScopes = Arrays.asList(myAnnotation.allowedScopes());
//        List<String> tokenScopes = decodedJWT.getClaim(SCOPE_CLAIM).asList(String.class);
//        Stream var10000 = methodScopes.stream();
//        tokenScopes.getClass();
//        boolean match = var10000.anyMatch(tokenScopes::contains);
//        if (!match) {
//            throw new FilterException(401, INSUFFICIENT_PRIVILEGES_MESSAGE);
//        } else {
//            return decodedJWT;
//        }
//    }
}
