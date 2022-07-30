package com.example.springrest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
