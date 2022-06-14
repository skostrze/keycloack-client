package pl.com.slaw.keycloak;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController
{
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
