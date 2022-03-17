package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamExampleTest {
    ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

    @Test
    void testStringTransformOutputSize() {
        List<String> namesList = DataSet.namesList(); // given

        startTimer(); // when
        List<String> actualOutput = parallelStreamExample.stringTransform(namesList);
        timeTaken();

        assertEquals(4, actualOutput.size());
        actualOutput.forEach(name -> assertTrue(name.contains("-")));
    }
}