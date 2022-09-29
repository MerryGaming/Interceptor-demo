package org.aibles.interceptor.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aibles.interceptor.dto.response.LoginResponse;
import org.aibles.interceptor.exception.UnauthorizedException;
import org.aibles.interceptor.service.LoginService;
import org.aibles.interceptor.util.Base64Encoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class LogInterceptor implements HandlerInterceptor {

  public final LoginService service;
  private static final String URI_API_LOGIN = "/api/v1/users/login";
  private static final String URI_API_REGISTRY = "/api/v1/users";
  private static final String HTTP_METHOD_POST = "POST";
  public static final String START_OF_HEADER = "Basic ";
  public static final int TOKEN_INDEX = 6;


  public LogInterceptor(LoginService service) {
    this.service = service;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info(
        "(preHandle)header : {}, path : {}",
        request.getHeader(AUTHORIZATION),
        request.getServletPath());

    if (HTTP_METHOD_POST.equalsIgnoreCase(request.getMethod()) && request.getRequestURI()
        .equals(URI_API_LOGIN)) {
      return true;
    }

    if (HTTP_METHOD_POST.equalsIgnoreCase(request.getMethod()) && request.getRequestURI()
        .equals(URI_API_REGISTRY)) {
      return true;
    }
    String authorizationHeader = request.getHeader(AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith(START_OF_HEADER)) {
      log.info("(preHandle)header : {}", authorizationHeader);
      String token = authorizationHeader.substring(TOKEN_INDEX);
      String username = Base64Encoder.getInstance().getUsernameFromToken(token);
      String password = Base64Encoder.getInstance().getPasswordFromToken(token);

      LoginResponse loginResponse = service.getByUsername(username);
      if (loginResponse.getPassword().equals(password) && loginResponse.getEnabled()) {
        return true;
      } else {
        throw new UnauthorizedException();
      }
    } else {
      throw new UnauthorizedException();
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler, ModelAndView modelAndView) throws Exception {

    System.out.println("\n-------- LogInterception.postHandle --- ");
    System.out.println("Request URL: " + request.getRequestURL());

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    System.out.println("\n-------- LogInterception.afterCompletion --- ");

    long startTime = (Long) request.getAttribute("startTime");
    long endTime = System.currentTimeMillis();
    System.out.println("Request URL: " + request.getRequestURL());
    System.out.println("End Time: " + endTime);

    System.out.println("Time Taken: " + (endTime - startTime));
  }
}

