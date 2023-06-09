package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);
    Optional<User> findByNameAndHashedPass(String name, String hashedPass);
    List<User> findAllByIsAdmin(Boolean isAdmin);
}
