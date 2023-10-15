package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.CallingInfo.callingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {
    @Test
    @DisplayName("Проверка вывода последнего вызванного метода")
    void callingInfo_shouldReturnNameOfLastCalledMethod() {
        assertThat(callingInfo()).isEqualTo(new CallingInfo(
            "edu.hw2.task4.CallingInfoTest",
            "callingInfo_shouldReturnNameOfLastCalledMethod"
        ));
    }
}
