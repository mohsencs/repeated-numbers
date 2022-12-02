package ir.mofid.repeatedwords;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RepeatedServiceTests {

    @Autowired
    private RepeatedService service;


    @Test
    void testGetRepeatedInputContainsNullValues(){
        List<Integer> input = Arrays.asList(2, null , 1, 5, 4, 3, 2, 1, null, null);
        service.getRepeated(input).forEach(Assertions::assertNotNull);
    }

    @Test
    void testGetRepeatedOutputDontContainsNullValuesOnNonRepeateds(){
        List<Integer> input = Arrays.asList(2, 1, 5, 4, 3, 2, 1);
        service.getRepeated(input).forEach(Assertions::assertNotNull);
    }

    @Test
    void testGetRepeatedDontRepeatInResult(){
        List<Integer> input = Arrays.asList(2, 1, 5, 4, 3, 2, 1, 1 , 2);
        List<Integer> output = service.getRepeated(input);
        Assertions.assertSame(2, output.size());
        Assertions.assertTrue(output.stream().anyMatch(x -> x==1));
        Assertions.assertTrue(output.stream().anyMatch(x -> x==2));
    }

}
