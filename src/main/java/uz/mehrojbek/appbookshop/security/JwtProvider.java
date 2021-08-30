package uz.mehrojbek.appbookshop.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.mehrojbek.appbookshop.exception.RestException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    private static final String secretKey = "G6NXDyRfffcbzzPcCrPsMwPA65SFad7Fz6";

    public String generateToken(UUID id, int days) {
        LocalDateTime time = LocalDateTime.now().plusDays(days);
        Date expireDate = convertToDateViaInstant(time);
        String token = Jwts
                .builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return "Bearer "+token;
    }

    public String getIdFromToken(String token) {
        token = token.substring(7);
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException ex) {
            throw new RestException("token expired get refresh token", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return null;
        }
    }


    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.of("Asia/Tashkent"))
                        .toInstant());
    }
}