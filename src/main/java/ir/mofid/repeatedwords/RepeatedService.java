package ir.mofid.repeatedwords;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepeatedService {

    public List<Integer> getRepeated(List<Integer> numbers) {
        BitSet bitSet = new BitSet(numbers.size());

        return numbers.stream()
                .filter(Objects::nonNull)
                .map(number -> {
                            if (bitSet.get(number)) {
                                return number;
                            } else {
                                bitSet.set(number);
                                return null;
                            }
                        }
                ).filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}
