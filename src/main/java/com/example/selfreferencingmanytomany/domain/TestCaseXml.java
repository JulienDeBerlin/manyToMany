package com.example.selfreferencingmanytomany.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseXml {

  private String name;
  private String description;
  private List<String> requiredTestcases;

}
