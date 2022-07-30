package com.example.springrest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

  @Override Optional<Friend> findById(Long aLong);

}
