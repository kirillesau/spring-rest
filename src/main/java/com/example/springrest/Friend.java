package com.example.springrest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstname;
  private String lastname;

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Friend friend = (Friend) o;
    return id == friend.id && Objects.equals(firstname, friend.firstname) && Objects.equals(lastname, friend.lastname);
  }

  @Override public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

}
