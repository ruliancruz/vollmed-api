package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.infrastructure.security.JWTTokenData;
import med.voll.api.infrastructure.security.TokenService;
import med.voll.api.model.user.AuthenticationData;
import med.voll.api.model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController
{
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService)
    {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.password()));

        return ResponseEntity.ok(new JWTTokenData(tokenService.generateToken((User) authentication.getPrincipal())));
    }
}