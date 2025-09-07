package services;

import models.User;

public class UserService {
    private static final ExchangeService exchange = new ExchangeService();

    public static void registerUser(User user) throws Exception {
        exchange.registerUser(user);
    }

    public static User login(String email, String password) throws Exception {
        return exchange.login(email, password);
    }
}