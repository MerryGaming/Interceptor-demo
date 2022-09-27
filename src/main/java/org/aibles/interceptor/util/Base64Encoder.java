package org.aibles.interceptor.util;


import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Encoder {

  private static final Base64 base64 = new Base64();

  private static final int USERNAME_INDEX = 0;

  private static final int PASSWORD_INDEX = 1;

  private static final String SPLITTER = ":";
  private static Base64Encoder instance;

  private Base64Encoder() {}

  public static Base64Encoder getInstance() {
    if (instance == null) {
      instance = new Base64Encoder();
    }

    return instance;
  }

  private String decodeToken(String token) {
    return new String(base64.decode(token.getBytes()));
  }

  public String getUsernameFromToken(String token) {
    return decodeToken(token).split(SPLITTER)[USERNAME_INDEX];
  }

  public String getPasswordFromToken(String token) {
    return decodeToken(token).split(SPLITTER)[PASSWORD_INDEX];
  }
}
