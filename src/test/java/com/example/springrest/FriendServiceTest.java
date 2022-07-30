package com.example.springrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class FriendServiceTest {

  @Autowired
  FriendService friendService;

  @MockBean
  private FriendRepository friendRepository;

  @Test
  void getFriends_without_friends_should_return_empty_list() {
    // given
    // when
    final List<Friend> actual = friendService.getFriends();

    // then
    assertThat(actual).isEmpty();
  }

  @Test
  void getFriends_with_5_friends_should_return_list_with_5_entries() {
    // given
    final List<Friend> friends = Arrays.asList(
        FriendFactory.createDefaultFriend(),
        FriendFactory.createDefaultFriend(),
        FriendFactory.createDefaultFriend(),
        FriendFactory.createDefaultFriend(),
        FriendFactory.createDefaultFriend());

    when(friendRepository.findAll()).thenReturn(friends);
    // when
    final List<Friend> actual = friendService.getFriends();

    // then
    assertThat(actual.size()).isEqualTo(5);
    assertThat(actual).isEqualTo(friends);
  }

}