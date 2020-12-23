package myApp.repository;

import myApp.domain.User;

/**
 * Синголтон класс, для получения информации по пользователю
 */
public final class UserHolderSingleton {

    private final static UserHolderSingleton INSTANCE = new UserHolderSingleton();
    private User user;

    private UserHolderSingleton() {
        //do nothing
    }

    public static UserHolderSingleton getFindUser() {
        return INSTANCE;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
