package com.votify.security;

import com.votify.config.JwtUtil;
import com.votify.shared.event.EventPublisher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthEntrypoint {
    private final EventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email, request.password));

            String token = jwtUtil.generateToken(request.email);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials for user:%s".formatted(request.email));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request) {
        var event = createUser(request);
        eventPublisher.publish(event);
        return login(new LoginRequest(request.email, request.password));
    }

    private UserCreated createUser(RegisterRequest request) {
        var user = userRepository.save(User.newUser(request.email, passwordEncoder.encode(request.password), request.firstName, request.lastName));
        return new UserCreated(user.id(), user.email(), request.communityName, request.communityLocation);
    }

    public record LoginRequest(@NotNull(message = "Email is required") String email,
                               @NotNull(message = "Password is required") String password) {

    }

    public record RegisterRequest(@NotNull(message = "Email is required") String email,
                                  @NotNull(message = "Password is required") String password,
                                  @NotNull(message = "First name is required") String firstName,
                                  @NotNull(message = "Last name is required") String lastName,
                                  @NotNull(message = "CommunityName is required") String communityName,
                                  @NotNull(message = "CommunityLocation is required") String communityLocation) {
    }
}

