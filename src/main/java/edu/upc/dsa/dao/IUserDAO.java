package edu.upc.dsa.dao;

import edu.upc.dsa.models.User;

public interface IUserDAO {
    User addUser(String username, String password, String email);
    int size();
    User getUserByName(String name);
    User getUserByEmail(String email);
}
