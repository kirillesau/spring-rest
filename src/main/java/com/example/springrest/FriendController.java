package com.example.springrest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@AllArgsConstructor
public class FriendController {

  private FriendService friendService;

  @GetMapping
  public List<Friend> getFriends() {
    return friendService.getFriends();
  }

  @GetMapping("/{id}")
  public Friend getFriend(@PathVariable("id") long id) {
    return friendService.getFriend(id);
  }

  @PutMapping("/{id}")
  public Friend putFriend(@RequestBody Friend friend, @PathVariable("id") long id) {
    return friendService.addFriend(friend, id);
  }

  @DeleteMapping("/{id}")
  public void deleteFriend(@PathVariable("id") long id) {
    friendService.deleteFriend(id);
  }

}
