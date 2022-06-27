package com.example.selfreferencingmanytomany.service;

import com.example.selfreferencingmanytomany.domain.TestCase;
import com.example.selfreferencingmanytomany.domain.TestCaseXml;
import com.example.selfreferencingmanytomany.persistence.TestCaseRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestCaseImporter {

  private TestCaseXmlReaderMock testCaseXmlReaderMock;

  private TestCaseRepository testCaseRepository;

  @Transactional
  public void importTestcase() {

    cleanDB();

    final var testCaseXmls = testCaseXmlReaderMock.readTestcaseXml();

    for (final TestCaseXml xml : testCaseXmls) {

      final TestCase testCase = testCaseRepository.findByName(xml.getName())
          .orElse(new TestCase());

      testCase.setDescription(xml.getDescription());
      testCase.setName(xml.getName());
      testCase.setRequiredTestcases(fetchAlreadyExistentOrCreateRequiredTestcasesDeclaredIn(xml));
      testCaseRepository.saveAndFlush(testCase);
    }
  }

  private void cleanDB() {
    testCaseRepository.deleteAll();
  }

  private List<TestCase> fetchAlreadyExistentOrCreateRequiredTestcasesDeclaredIn(
      final TestCaseXml xml) {

//    return xml.getRequiredTestcases().stream()
//        .map(name -> testCaseRepository.findByName(name)
//            .orElse(testCaseRepository.save(createNewTestCaseWithName(name))))
//        .collect(Collectors.toList());

    final var requiredTestsAsString = xml.getRequiredTestcases();
    final var requiredTests = new ArrayList<TestCase>();

    for (final String name : requiredTestsAsString) {
      final TestCase test = testCaseRepository.findByName(name).isPresent()
          ? testCaseRepository.findByName(name).get()
          : testCaseRepository.save(createNewTestCaseWithName(name));
      requiredTests.add(test);
    }

    return requiredTests;
  }

  private TestCase createNewTestCaseWithName(final String name) {
    final var testCase = new TestCase();
    testCase.setName(name);
    return testCase;
  }


}
