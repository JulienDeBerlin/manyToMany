package com.example.selfreferencingmanytomany.service;

import com.example.selfreferencingmanytomany.domain.TestCaseXml;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * this class mocks an XML reader
 */
@Service
public class TestCaseXmlReaderMock {

  public List<TestCaseXml> readTestcaseXml() {

    final var testCaseXmlA = TestCaseXml.builder()
        .name("TestA")
        .description("description TestA")
        .requiredTestcases(List.of()).build();

    final var testCaseXmlB = TestCaseXml.builder()
        .name("TestB")
        .description("description TestB")
        .requiredTestcases(List.of("TestA", "TestC")).build();

    final var testCaseXmlC = TestCaseXml.builder()
        .name("TestC")
        .description("description TestC")
        .requiredTestcases(List.of()).build();

    final var testCaseXmlD = TestCaseXml.builder()
        .name("TestD")
        .description("description TestD")
        .requiredTestcases(List.of("TestB")).build();

    final var testCaseXmlE = TestCaseXml.builder()
        .name("TestE")
        .description("description TestE")
        .requiredTestcases(List.of("TestA")).build();

    return List.of(testCaseXmlA, testCaseXmlB, testCaseXmlC, testCaseXmlD, testCaseXmlE);
  }

}
