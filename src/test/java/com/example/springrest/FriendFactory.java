package com.example.springrest;

public final class FriendFactory {


  public static Friend createDefaultFriend() {
    return new Friend(-1L, "firstname1", "lastname1");
  }

}
