package com.wallet.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.Application;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class TokenAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final String serverSecret = "secret";

    private final List<Client> clients;

    public TokenAuthenticationInterceptor() {
        List<Client> clients = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            clients = mapper.readValue(Application.class.getClassLoader().getResourceAsStream("auth.json"),
                    new TypeReference<List<Client>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clients = clients;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("Auth-Token");
        String authorization = request.getHeader("Authorization");
        if (authToken == null) {
            Client client = clientByAuthorizationCredentials(authorization);
            if (client != null) {
                response.setHeader("Auth-Token", createAuthToken(client));
                return true;
            }
        } else {
            if (checkAuthToken(authToken)) return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write("{\"Success\": false}");
        return false;
    }

    private Client clientByAuthorizationCredentials(String authorization) {
        AtomicReference<Client> clientByAuthorization = new AtomicReference<>(null);
        if (authorization != null && authorization.startsWith("Basic")) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String[] credentials = new String(DatatypeConverter.parseBase64Binary(base64Credentials)).split(":");
            Client checkClient = new Client(tryParseId(credentials[0]), credentials[1]);
            clients.forEach((client -> {
                if (client.equals(checkClient)) {
                    clientByAuthorization.set(client);
                }
            }));
        }
        return clientByAuthorization.get();
    }

    private Boolean checkAuthToken(String token) {
        try {
            AtomicReference<String> clientSecret = new AtomicReference<>("");
            String authToken = token.split(":")[0];
            String expirationToken = token.split(":")[1];
            Integer clientId = Integer.parseInt(token.split(":")[2]);
            Date expirationDate = new Date(Long.parseLong(token.split(":")[3]));

            clients.forEach(client -> {
                if (client.getId().equals(clientId)) { clientSecret.set(client.getSecret()); }
            });

            MessageDigest authDigest = MessageDigest.getInstance("SHA-1");
            authDigest.update((clientSecret + serverSecret).getBytes("UTF-8"));
            String authTokenCanonical = DatatypeConverter.printHexBinary(authDigest.digest());

            authDigest.reset();
            authDigest.update((expirationDate.getTime() + serverSecret).getBytes("UTF-8"));
            String expirationTokenCanonical = DatatypeConverter.printHexBinary(authDigest.digest());

            return (authToken.equals(authTokenCanonical)
                    && expirationToken.equals(expirationTokenCanonical)
                    && expirationDate.after(new Date())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String createAuthToken(Client client) {
        Date expirationDate = new Date(System.currentTimeMillis() + (60 * 60 * 1000));
        String authToken = "", expirationToken = "";
        String auth = client.getSecret() + serverSecret;
        String expiration = expirationDate.getTime() + serverSecret;
        try {
            MessageDigest authDigest = MessageDigest.getInstance("SHA-1");
            authDigest.update(auth.getBytes("UTF-8"));
            authToken = DatatypeConverter.printHexBinary(authDigest.digest());

            authDigest.reset();
            authDigest.update(expiration.getBytes("UTF-8"));
            expirationToken = DatatypeConverter.printHexBinary(authDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authToken + ":" + expirationToken + ":" + client.getId() + ":" + expirationDate.getTime();
    }

    private Integer tryParseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}

final class Client {
    private final Integer id;
    private final String secret;

    public Integer getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    Client(Integer id, String secret) {
        this.id = id;
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(secret, client.secret);
    }
}