package med.voll.api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Service
public class TokenService
{
    @Value("${api.security.token.secret}")
    private String secret;

    private static final TemporalUnit TIME_UNIT = ChronoUnit.DAYS;
    private static final int TIME_AMOUNT = 1;
    private static final String TIME_ZONE_OFFSET = "-03:00";

    public String generateToken(User user)
    {
        try
        {
            return JWT.create()
                    .withIssuer("Voll.med API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plus(TIME_AMOUNT, TIME_UNIT).toInstant(ZoneOffset.of(TIME_ZONE_OFFSET)))
                    .sign(Algorithm.HMAC256(secret));
        }
        catch(JWTCreationException exception)
        {
            throw new RuntimeException("Error generating JWT Token", exception);
        }
    }
}