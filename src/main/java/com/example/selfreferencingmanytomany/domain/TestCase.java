package com.example.selfreferencingmanytomany.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TestCase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;


  @Column(name = "description", columnDefinition = "text")
  private String description;

  @ManyToMany
  @JoinTable(
      name = "tf_required_testcases",
      joinColumns = @JoinColumn(name = "testcase_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "required_testcase_id", referencedColumnName = "id")
  )
  private Set<TestCase> requiredTestcases;


}
