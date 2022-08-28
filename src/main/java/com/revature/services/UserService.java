package com.revature.services;

import com.revature.dto.Credentials;
import com.revature.exceptions.UserCredentialsAlreadyInUse;
import com.revature.models.User;
import com.revature.repositores.OrderRepo;
import com.revature.repositores.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepo userRepo;
    private OrderRepo orderRepo;

//    Constructors
    @Autowired
    public UserService(UserRepo userRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

//    Methods
    @Transactional
    public User getByCredentials(Credentials userCredentials) {
        Optional<User> userInDB = userRepo.getByUsernameAndPassword(userCredentials.getUsername(),
                userCredentials.getPassword());

        if (userInDB.isPresent()) {
            log.info("Found user with username {}", userCredentials.getUsername());
            return userInDB.get();
        } else {
            log.warn("Username $ password combination did not match for user {}", userCredentials.getUsername());
            return null;
        }
    }

    @Transactional
    public User addUser(User newUser) {
        Optional<User> addedUser = userRepo.addUser(newUser.getAge(), newUser.getDr_lic_number(), newUser.getEmail(),
                newUser.getF_name(), newUser.getL_name(), newUser.getPass());
        return addedUser.orElse(null);
    }

    @Transactional
    public User getProfileById(int userId) {
        Optional<User> existingUser = userRepo.getProfileById(userId);
        return existingUser.orElse(null);

    }

    @Transactional
    public User getByEmail(String userEmail) {
        Optional<User> existingEmailInDB = userRepo.getByEmail(userEmail);
        return existingEmailInDB.orElse(null);

    }

    @Transactional
    public User getByDrLicense(String userDrLicense) {
        Optional<User> existingDrLicenseInDB = userRepo.getByDrLicense(userDrLicense);
        return existingDrLicenseInDB.orElse(null);

    }
}
