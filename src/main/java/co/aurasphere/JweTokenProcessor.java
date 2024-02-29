package co.aurasphere;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JweTokenProcessor {

    public static void main(String[] args) throws ParseException, JOSEException {

        // Your secret key
        try {
            String secretKey = "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";
            String jwtToken =   "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..1QiFc3YlhlBc6zXL.viOx2FBJOUpVvZ0KYeyYpljnhewGkdqXrkfIDTKGSUc1-LeQe7i3gtHFt4Rr6VttVINkRF-cHEv-KORyjOdxmolYyhCuHzScznpCalfvuRoCZ0yu1j8_F8oZnb07gHmv6mC3Ho_AeFCXFEVyab6xsqteuWotBsTPc4A8hR00IM6XTZBIJiQ41MYjGFVT50Xc6EjijIzt-F8ppTIzWe26UdvlcVSBe8i6AB2PTt_5aA45cRdhqj1tmytIAlS_FH9eHJMCR4ed1I5Nxi1g2ExGtXECUbikx4domypkOwbBiEEJzVzvQ3EoPmqqO10325EG2-mUBux-Kztj4cgUJIFhvxsCCs-3kwVKOusb9lUOmfL50oRhHmTN6-56z-a_HP0.mY9KwG51pqGcLuaALGo5Dg";

            // Decode JWT token
            String decodedToken = decodeJWTToken(jwtToken);
            System.out.println("Decoded Token: " + decodedToken);

            // Check if the token is valid
            boolean isValid = isTokenValid(jwtToken, secretKey);
            System.out.println("Is Token Valid? " + isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decodeJWTToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return header + " " + payload;
    }

    public static boolean isTokenValid(String token, String secretKey) throws Exception {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            // Additional checks if needed
            // For example, check expiration, issuer, etc.

            return true;
        } catch (Exception e) {
            throw new Exception("Secret key used for  might be incorrect", e);
        }
    }
}