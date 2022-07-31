package com.example.springrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FriendControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FriendService friendService;

  @Test
  void getFriends_should_return_empty_list() throws Exception {
    mockMvc.perform(get("/friend"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void getFriends_should_return_list_with_2_friends() throws Exception {
    final List<Friend> friends = Arrays.asList(
        new Friend(1L, "firstname1", "lastname1"),
        new Friend(2L, "firstname2", "lastname2"));

    when(friendService.getFriends()).thenReturn(friends);

    mockMvc.perform(get("/friend"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
        .andExpect(jsonPath("$[*].firstname", containsInAnyOrder("firstname1", "firstname2")))
        .andExpect(jsonPath("$[*].lastname", containsInAnyOrder("lastname1", "lastname2")));
  }

  @Test
  void getFriend_should_return_empty_body() throws Exception {
    when(friendService.getFriend(1L)).thenReturn(null);

    mockMvc.perform(get("/friend/{id}", 1L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  void getFriend_should_return_friend_with_id() throws Exception {
    when(friendService.getFriend(1L)).thenReturn(new Friend(1L, "firstname1", "lastname1"));

    mockMvc.perform(get("/friend/{id}", 1L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.firstname", is("firstname1")))
        .andExpect(jsonPath("$.lastname", is("lastname1")));
  }

  @Test
  void putFriend_should_return_friend_with_id() throws Exception {
    final Friend friend = new Friend(1L, "firstname1", "lastname1");
    when(friendService.addFriend(friend, 1L)).thenReturn(friend);

    mockMvc.perform(put("/friend/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "id": 1,
                  "firstname": "firstname1",
                  "lastname": "lastname1"
                }"""))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.firstname", is("firstname1")))
        .andExpect(jsonPath("$.lastname", is("lastname1")));
  }

  @Test
  void deleteFriend_should_delete_friend_with_id() throws Exception {
    doNothing().when(friendService)
        .deleteFriend(1L);

    mockMvc.perform(delete("/friend/{id}", 1L))
        .andDo(print())
        .andExpect(status().isOk());
  }

}