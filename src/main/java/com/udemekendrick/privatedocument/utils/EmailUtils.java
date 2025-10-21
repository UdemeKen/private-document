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
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + ", \n\nYour new account has been created. PLease click on the link below to very your account.\n\n" +
                getVerificationUrl(host,token) + "\n\nThe Support Team";
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/verify/account?token=" + token;
    }
}
