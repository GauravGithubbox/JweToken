package co.aurasphere;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JweTokenDecoder {




        public static void main(String[] args) throws ParseException, JOSEException {
            // Your Base64-encoded decryption key
            String decryptionKeyString = "5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=";

            // Decode the key
            byte[] decryptionKeyBytes = Base64.getDecoder().decode(decryptionKeyString);
            SecretKey decryptionKey = new SecretKeySpec(decryptionKeyBytes, "AES");

            // Your JWE token
            String jweToken = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..x8iUgAwF_yEYyQu5.jzS3vCjqJijVnbXkgk4gdJedvEcqqXLDsrSOdsaSWDqO-d0Woi8haSxqqpxwO1kLXXwD6O6iEGyJvV-BWWIxxCQgpE7-rXqJ6ldZlil1onFzaWSfySB4knPu3-Jo3PVo7l_rLFIltg-BrT9PF1fk9XBaNqAunyCT5Ysai8JyKOT5lFHmnqBrEDYhkOxC1PN6MejwnVEDhF_c093NgNPqopYsCTZOaSuF2ZYNJ0OP0tZGMTzS_bJMlJt79_RuAm3g3hj_GR5yk0RZypVMx6gtHNCIevBqfZu4iW5DdTgs8V6kgcO00Vu8VhL-lKUs9CdLj7fmkIQfYwkv5B9lSgZr2RTbxSI_y9rPoTmA-fD0sbFBUiOQTLwQvKnaGlmaCNk.bjyqrkRsTwsA8Eq6Exd1Tg";

            // Parse and decrypt the JWE token
            EncryptedJWT encryptedJWT = EncryptedJWT.parse(jweToken);
            DirectDecrypter decrypter = new DirectDecrypter(decryptionKey);
            encryptedJWT.decrypt(decrypter);

            // Get the JWT claims
            JWTClaimsSet jwtClaimsSet = encryptedJWT.getJWTClaimsSet();
            System.out.println("Decoded Token: " + jwtClaimsSet.toJSONObject());
        }

}

