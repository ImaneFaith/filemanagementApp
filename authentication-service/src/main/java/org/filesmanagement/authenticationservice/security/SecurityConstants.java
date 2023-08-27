package org.filesmanagement.authenticationservice.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
public class SecurityConstants {

    // TODO: 8/24/2023  use Vault to store Secret Keys
    public static final String ISSUER = "org.filemanag";
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    public static final long EXPIRATION = 86400000; //a day

    public static final long REFRESH_EXPRIRATION = 604800000; //7 days

    public static final String sKEY = "hgSamak/kpYq9R5OdD0oH+5FwsJIs9Pd3ykV0r3M7yhO5LIr9uvRgb4d9QYuoeLZs79M/HYKvQWEgoi4HXIXUQ==";





}
