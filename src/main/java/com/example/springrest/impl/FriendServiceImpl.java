package com.example.springrest.impl;

import com.example.springrest.Friend;
import com.example.springrest.FriendRepository;
import com.example.springrest.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {


  private final FriendRepository friendRepository;

  @Override public List<Friend> getFriends() {
    return friendRepository.findAll();
  }

  @Override public Friend getFriend(long id) {
    return friendRepository.findById(id)
        .orElse(null);
  }

  @Transactional
  @Override public Friend addFriend(Friend newFriend, long id) {
    friendRepository.findById(id)
        .ifPresentOrElse(oldFriend -> updateFriend(oldFriend, newFriend), () -> friendRepository.save(newFriend));
    return newFriend;
  }

  private void updateFriend(Friend oldFriend, Friend newFriend) {
    oldFriend.setFirstname(newFriend.getFirstname());
    oldFriend.setLastname(newFriend.getLastname());
    friendRepository.save(oldFriend);
  }

  @Override public void deleteFriend(long id) {
    friendRepository.deleteById(id);
  }

}
