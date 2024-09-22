package com.samhcoco.ip.bookingapp.user.repository;

import com.samhcoco.ip.bookingapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
