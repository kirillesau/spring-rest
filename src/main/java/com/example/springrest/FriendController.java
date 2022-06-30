package com.example.springrest;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {


  @GetMapping
  public List<Friend> getFriends() {
    return Collections.emptyList();
  }

  @GetMapping("/{id}")
  public Friend getFriend(@PathVariable("id") long id) {
    return Friend.builder()
        .id(id)
        .firstname("example")
        .lastname("example")
        .build();
  }

  @PutMapping("/{id}")
  public Friend putFriend(@RequestBody Friend friend, @PathVariable("id") long id) {
    return friend;
  }

  @DeleteMapping("/{id}")
  public void deleteFriend(@PathVariable("id") long id) {
  }

}
