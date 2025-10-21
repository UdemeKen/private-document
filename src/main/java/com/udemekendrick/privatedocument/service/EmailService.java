package com.udemekendrick.privatedocument.service;

/**
 * Author: Udeme Kendrick
 *
 * @version 1.0
 * @license MIT License
 * @see <a href="mailto:udemekendrick@gmail.com">udemekendrick@gmail.com</a>
 * @see <a href="https://udemekendrick.vercel.app">https://udemekendrick.vercel.app</a>
 * @since 10/20/2025
 */
public interface EmailService {
    void sendNewAccountEmail(String name, String email, String token);
    void sendPasswordResetEmail(String name, String email, String token);
}
