package com.udemekendrick.privatedocument.utils;

/**
 * Author: Udeme Kendrick
 *
 * @version 1.0
 * @license MIT License
 * @see <a href="mailto:udemekendrick@gmail.com">udemekendrick@gmail.com</a>
 * @see <a href="https://udemekendrick.vercel.app">https://udemekendrick.vercel.app</a>
 * @since 10/20/2025
 */
public class EmailUtils {
    public static String getEmailMessage(String name, String host, String key) {
        return "Hello " + name + ", \n\nYour new account has been created. Please click on the link below to verify your account.\n\n" +
                getVerificationUrl(host,key) + "\n\nThe Support Team";
    }

    public static String getResetPasswordMessage(String name, String host, String token) {
        return "We received a request to reset your password. " +
            "If you made this request, please click the link below to reset your password:\n\n" +
            getResetPasswordUrl(host, token) + token + "\n\n" +
            "If you did not request a password reset, please ignore this message. " +
            "Your account will remain secure.\n\n" +
            "Best regards,\n" +
            "The Support Team";
    }

    private static String getVerificationUrl(String host, String key) {
        return host + "/verify/account?key=" + key;
    }
    private static String getResetPasswordUrl(String host, String token) {
        return host + "verify/password?token=" + token;
    }
}
