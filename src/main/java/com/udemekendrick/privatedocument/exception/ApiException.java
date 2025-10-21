package com.udemekendrick.privatedocument.exception;

/**
 * Author: Udeme Kendrick
 *
 * @version 1.0
 * @license MIT License
 * @see <a href="mailto:udemekendrick@gmail.com">udemekendrick@gmail.com</a>
 * @see <a href="https://udemekendrick.vercel.app">https://udemekendrick.vercel.app</a>
 * @since 10/19/2025
 */
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super("An error occur");
    }
}
