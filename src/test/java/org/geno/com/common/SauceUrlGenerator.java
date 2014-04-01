package org.geno.com.common;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SauceUrlGenerator {
    private static final String ALGORITHM = "HMACMD5";
    private final String sauceUser;
    private final String sauceKey;
    private final String sessionId;

    public SauceUrlGenerator(String sauceUser, String sauceKey, String sessionId) {
        this.sauceUser = sauceUser;
        this.sauceKey = sauceKey;
        this.sessionId = sessionId;
    }

    /**
     * Creates the link to the Sauce Labs results
     *
     * @return URL
     */
    public String getResultsUrl() {
        String PUBLICURL = "https://saucelabs.com/jobs/%1$s";
        String JOB_ID_FORMAT = PUBLICURL + "?auth=%2$s";
        String token = generateToken(sessionId);
        return String.format(JOB_ID_FORMAT, sessionId, token);
    }


    private static String tokenGenerate(String message, String jobId) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        String message = "example_user:123456-1234-4268-asdf-8dcf81f1fc71";
//        String jobId   = "5f9fef27854ca50a3c132ce331cb6034";
        SecretKeySpec secretKey = new SecretKeySpec(message.getBytes(), ALGORITHM);

        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] result = mac.doFinal(jobId.getBytes());

        return toHexString(result);
    }

    private static String toHexString(byte[] bytes) {
        StringBuffer hash = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            String hex = Integer.toHexString(0xFF &  bytes[i]);
            if (hex.length() == 1) {
                hash.append('0');
            }
            hash.append(hex);
        }
        return hash.toString();
    }

    /**
     * Generates the token used for creating the public Sauce Labs
     * results URL
     * @param jobId The job ID generated from Sauce Labs
     * @return token
     */
    private String generateToken(String jobId) {
        String message = sauceUser + ":" + sauceKey;
        try {
            return tokenGenerate(message, jobId);
        } catch (Exception e) {
            throw new RuntimeException("Token Generation Failed");
        }
        //System.out.println("https://saucelabs.com/jobs/" + jobId +"?auth=" + token);
    }

}
