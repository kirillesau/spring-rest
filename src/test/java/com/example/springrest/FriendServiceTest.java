package com.example.springrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        new Friend(-1L, "firstname1", "lastname1"),
        new Friend(-1L, "firstname1", "lastname1"),
        new Friend(-1L, "firstname1", "lastname1"),
        new Friend(-1L, "firstname1", "lastname1"),
        new Friend(-1L, "firstname1", "lastname1"));

    when(friendRepository.findAll()).thenReturn(friends);
    // when
    final List<Friend> actual = friendService.getFriends();

    // then
    assertThat(actual.size()).isEqualTo(5);
    assertThat(actual).isEqualTo(friends);
  }

  @Test
  void getFriend_with_non_existing_id_should_return_null() {
    // given
    // when
    final Friend actual = friendService.getFriend(1L);

    // then
    assertThat(actual).isNull();
  }

  @Test
  void getFriend_with_existing_id_should_return_friend() {
    // given
    final Friend friend = new Friend(-1L, "firstname1", "lastname1");
    when(friendRepository.findById(friend.getId())).thenReturn(Optional.of(friend));

    // when
    final Friend actual = friendService.getFriend(friend.getId());

    // then
    assertThat(actual).isEqualTo(friend);
  }

  @Test
  void addFriend_with_existing_friend_id_should_update_friend() {
    // given
    final Friend friend = new Friend(-1L, "firstname1", "lastname1");
    final long generatedId = friend.getId();
    final Friend oldFriendWithSameId = new Friend(generatedId, "oldFirstname", "oldLastname");

    when(friendRepository.findById(generatedId)).thenReturn(Optional.of(oldFriendWithSameId));

    // when
    final Friend actual = friendService.addFriend(friend, generatedId);

    // then
    assertThat(actual).isEqualTo(friend);
    verify(friendRepository).findById(friend.getId());
    verify(friendRepository).save(friend);
    verifyNoMoreInteractions(friendRepository);
  }

  @Test
  void addFriend_with_non_existing_friend_id_should_add_to_repository() {
    // given
    final Friend friend = new Friend(-1L, "firstname1", "lastname1");
    when(friendRepository.findById(5L)).thenReturn(Optional.empty());

    // when
    final Friend actual = friendService.addFriend(friend, 5L);

    // then
    assertThat(actual).isEqualTo(friend);
    verify(friendRepository).findById(5L);
    verify(friendRepository).save(friend);
    verifyNoMoreInteractions(friendRepository);
  }

  @Test
  void deleteFriend_with_id_should_delete_friend() {
    // given
    // when
    friendService.deleteFriend(1L);

    // then
    verify(friendRepository).deleteById(1L);
    verifyNoMoreInteractions(friendRepository);
  }

}