package com.example.springrest.impl;

import com.example.springrest.Friend;
import com.example.springrest.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

  private final List<Friend> friends = new ArrayList<>(); // data should be stored in a repository

  @PostConstruct
  public void initialize() {
    // 2 Examples
    friends.add(Friend.builder()
        .id(1)
        .firstname("friend1_firstname")
        .lastname("friend1_lastname")
        .build());
    friends.add(Friend.builder()
        .id(2)
        .firstname("friend2_firstname")
        .lastname("friend2_lastname")
        .build());
  }

  @Override public List<Friend> getFriends() {
    return friends;
  }

  @Override public Friend getFriend(long id) {
    return friends.stream()
        .filter(f -> f.getId() == id)
        .findAny()
        .orElse(null);
  }

  @Override public Friend addFriend(Friend newFriend, long id) {
    friends.stream()
        .filter(f -> f.getId() == id)
        .findAny()
        .ifPresentOrElse(oldFriend -> updateFriend(oldFriend, newFriend), () -> friends.add(newFriend));
    return newFriend;
  }

  private void updateFriend(Friend oldFriend, Friend newFriend) {
    oldFriend.setFirstname(newFriend.getFirstname());
    oldFriend.setLastname(newFriend.getLastname());
  }

  @Override public void deleteFriend(long id) {
    friends.removeIf(f -> f.getId() == id);
  }

}
