package com.example.chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginManagerTest {

    // USERNAME TESTS
    @Test
    public void shouldAcceptValidUsername() {
        LoginManager manager = new LoginManager();
        assertTrue(manager.validateUsername("abc_1"));
    }

    @Test
    public void shouldRejectInvalidUsername() {
        LoginManager manager = new LoginManager();
        assertFalse(manager.validateUsername("abcdef"));
    }

    // PASSWORD TESTS
    @Test
    public void shouldAcceptStrongPassword() {
        LoginManager manager = new LoginManager();
        assertTrue(manager.validatePassword("Strong@123"));
    }

    @Test
    public void shouldRejectWeakPassword() {
        LoginManager manager = new LoginManager();
        assertFalse(manager.validatePassword("simple"));
    }

    // CELLPHONE TESTS
    @Test
    public void shouldAcceptCorrectPhoneNumber() {
        LoginManager manager = new LoginManager();
        assertTrue(manager.validatePhoneNumber("+27838968976"));
    }

    @Test
    public void shouldRejectWrongPhoneNumber() {
        LoginManager manager = new LoginManager();
        assertFalse(manager.validatePhoneNumber("08212345"));
    }

    // REGISTRATION TESTS
    @Test
    public void shouldRegisterSuccessfully() {
        LoginManager manager = new LoginManager();
        String result = manager.registerAccount("abc_1", "Strong@123", "+27838968976");

        assertEquals("User registered successfully.", result);
    }

    @Test
    public void shouldFailRegistrationForUsername() {
        LoginManager manager = new LoginManager();
        String result = manager.registerAccount("abcdef", "Strong@123", "+27838968976");

        assertTrue(result.contains("Invalid username"));
    }

    @Test
    public void shouldFailRegistrationForPassword() {
        LoginManager manager = new LoginManager();
        String result = manager.registerAccount("abc_1", "weak", "+27838968976");

        assertTrue(result.contains("Invalid password"));
    }

    @Test
    public void shouldFailRegistrationForPhone() {
        LoginManager manager = new LoginManager();
        String result = manager.registerAccount("abc_1", "Strong@123", "08123");

        assertTrue(result.contains("Invalid cellphone"));
    }

    // LOGIN TESTS
    @Test
    public void shouldLoginSuccessfully() {
        LoginManager manager = new LoginManager();
        manager.registerAccount("abc_1", "Strong@123", "+27838968976");

        assertTrue(manager.verifyLogin("abc_1", "Strong@123"));
    }

    @Test
    public void shouldFailLogin() {
        LoginManager manager = new LoginManager();
        manager.registerAccount("abc_1", "Strong@123", "+27838968976");

        assertFalse(manager.verifyLogin("abc_1", "wrongpass"));
    }

    @Test
    public void shouldReturnSuccessMessage() {
        LoginManager manager = new LoginManager();
        String result = manager.getLoginMessage(true, "abc_1");

        assertTrue(result.contains("welcome back"));
    }

    @Test
    public void shouldReturnFailureMessage() {
        LoginManager manager = new LoginManager();
        String result = manager.getLoginMessage(false, "abc_1");

        assertEquals("Login failed. Incorrect username or password.", result);
    }
}