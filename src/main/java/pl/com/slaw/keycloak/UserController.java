package pl.com.slaw.keycloak;


import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class UserController
{

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realmName;
    @Value("${keycloak.resource}")
    private String clientId;

    @GetMapping("/userinfo")
    @PreAuthorize("hasAuthority('admin')")
    public String userInfoController(Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        String username = accessToken.getPreferredUsername();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        Set<String> roles = realmAccess.getRoles();




        //model.addAttribute("username", accessToken.getGivenName());
        return "page";
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasAuthority('admin')")
    public String formAdmin()
    {
        return "Hello admin";
    }
    @GetMapping("/for-user")
    @PreAuthorize("hasAuthority('user')")
    public String formUser()
    {
        return "Hello user";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
    }



}
