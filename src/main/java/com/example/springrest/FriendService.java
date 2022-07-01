package com.example.springrest;

import java.util.List;

public interface FriendService {


  List<Friend> getFriends();

  Friend getFriend(long id);

  Friend addFriend(Friend friend, long id);

  void deleteFriend(long id);

}
