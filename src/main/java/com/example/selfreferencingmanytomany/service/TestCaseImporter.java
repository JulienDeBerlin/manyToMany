package com.example.selfreferencingmanytomany.service;

import com.example.selfreferencingmanytomany.domain.TestCase;
import com.example.selfreferencingmanytomany.domain.TestCaseXml;
import com.example.selfreferencingmanytomany.persistence.TestCaseRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestCaseImporter {

  private TestCaseXmlReader testCaseXmlReader;

  private TestCaseRepository testCaseRepository;

  @Transactional
  public void importTestcase() {

    cleanDB();

    final var testCaseXmls = testCaseXmlReader.readTestcaseXml();

    for (final TestCaseXml xml : testCaseXmls) {

      TestCase testCase = testCaseRepository.findByName(xml.getName())
          .orElse(new TestCase());
      
      testCase.setDescription(xml.getDescription());
      testCase.setName(xml.getName());
      testCase.setRequiredTestcases(fetchAlreadyExistentOrCreateRequiredTestcasesDeclaredIn(xml));
      testCaseRepository.save(testCase);
    }
  }

  private void cleanDB() {
    testCaseRepository.deleteAll();
  }

  private Set<TestCase> fetchAlreadyExistentOrCreateRequiredTestcasesDeclaredIn(
      final TestCaseXml xml) {

    return xml.getRequiredTestcases().stream()
        .map(name -> testCaseRepository.findByName(name)
            .orElse(testCaseRepository.save(createNewTestCaseWithName(name))))
        .collect(Collectors.toSet());
  }

  private TestCase createNewTestCaseWithName(final String name) {
    final var testCase = new TestCase();
    testCase.setName(name);
    return testCase;
  }


}
