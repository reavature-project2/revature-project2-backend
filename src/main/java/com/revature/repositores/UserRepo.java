package com.revature.repositores;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "select * from users where email like ? and pass like ?", nativeQuery = true)
    Optional<User> getByUsernameAndPassword(String userName, String userPassword);

    @Query(value = "insert into users (age, dr_lic_number, email, f_name, l_name, pass) values (:age, :dr_lic_number, :email, :f_name, :l_name, :pass) Returning *",
            nativeQuery = true)
    Optional<User> addUser(@Param(value = "age") int getAge, @Param(value = "dr_lic_number") String getDlNumber,
                           @Param(value = "email") String getEmail, @Param(value = "f_name") String getFirstName,
                           @Param(value = "l_name") String getLastName, @Param(value = "pass") String getPassword);

    @Query(value = "select * from users where user_id = ?", nativeQuery = true)
    Optional<User> getProfileById(int userId);

    @Query(value = "select * from users where email like ?", nativeQuery = true)
    Optional<User> getByEmail(String userEmail);

    @Query(value = "select * from users where dr_lic_number like ?", nativeQuery = true)
    Optional<User> getByDrLicense(String userDrLicense);
}
