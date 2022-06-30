package com.example.springrest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Friend {

  private long id;
  private String firstname;
  private String lastname;

}
