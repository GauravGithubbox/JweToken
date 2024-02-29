package co.aurasphere;

import org.jose4j.lang.JoseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link JweTokenDecrypterTest}.
 * 
 * @author Donato Rimenti
 *
 */
public class JweTokenDecrypterTest {

	/**
	 * Test for {@link JweTokenDecrypter#decryptToken(String, String)}.
	 * 
	 * @throws JoseException if the token is invalid
	 */
	@Test
	public void decryptTokenTest() throws JoseException {
		String key = "mySecret";
		String token = "eyJhbGciOiJQQkVTMi1IUzI1NitBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiZXhwIjoiOTk5NDk2MTM4MjIwMDAiLCJhdWQiOiJteUF1ZGllbmNlIiwiaXNzIjoibXlJc3N1ZXIiLCJwMmMiOjgxOTIsInAycyI6IlVZSF9Qd2h5QmNORVd4QTQifQ.LF4nJD0m1uuv5GOdSAERphXSv_O6dhGEKB_QJvTXj-DuWE-bKCs4OQ.RdNrWnmURv5QDN-DmlkmzA.qypSoDf2jBLuNGSDCuwfwoQegZzoIFkjPpMhulkaBFpULhdmcecrQBiIvxtAWioY.xa7qJC6FAGCcZk8YpI1zyw";
		String tokenContent = JweTokenDecrypter.decryptToken(token, key);

		String expectedTokenContent = "{\"alg\":\"PBES2-HS256+A128KW\",\"enc\":\"A128CBC-HS256\",\"exp\":\"99949613822000\",\"aud\":\"myAudience\",\"iss\":\"myIssuer\",\"p2c\":8192,\"p2s\":\"UYH_PwhyBcNEWxA4\"}{\"myPayloadField\":\"myPayloadValue\"}";
		Assert.assertEquals(expectedTokenContent, tokenContent);
	}

}