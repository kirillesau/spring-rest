package com.example.springrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FriendRepositoryTest {

  @Autowired
  FriendRepository friendRepository;

  @Test
  void findById_should_find_Friend_by_id() {
    // given
    final Friend expected = friendRepository.save(new Friend(123L, "firstname1", "lastname2"));
    long id = expected.getId(); // generated

    // when
    final Optional<Friend> actual = friendRepository.findById(id);

    // then
    assertThat(actual).isNotEmpty();
    assertThat(actual.get()).isEqualTo(expected);
  }

}