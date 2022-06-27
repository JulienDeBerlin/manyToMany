package com.example.selfreferencingmanytomany;

import com.example.selfreferencingmanytomany.service.TestCaseImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SelfReferencingManyToManyApplication {

  @Autowired
  TestCaseImporter testCaseImporter;

  public static void main(final String[] args) {
    SpringApplication.run(SelfReferencingManyToManyApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startImport() {
    testCaseImporter.importTestcase();
  }
}
