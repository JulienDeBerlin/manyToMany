package com.example.selfreferencingmanytomany.service;

import com.example.selfreferencingmanytomany.domain.TestCaseXml;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * this class mocks an XML reader
 */
@Service
public class TestCaseXmlReader {

  public List<TestCaseXml> readTestcaseXml() {

    var testCaseXmlA = TestCaseXml.builder()
        .name("TestA")
        .description("description TestA")
        .requiredTestcases(List.of("TestB", "TestC")).build();

    var testCaseXmlB = TestCaseXml.builder()
        .name("TestB")
        .description("description TestB")
        .requiredTestcases(List.of()).build();

    var testCaseXmlC = TestCaseXml.builder()
        .name("TestC")
        .description("description TestC")
        .requiredTestcases(List.of()).build();
    
    return List.of(testCaseXmlA, testCaseXmlB, testCaseXmlC);
  }

}
