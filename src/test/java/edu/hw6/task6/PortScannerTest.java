package edu.hw6.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.task6.PortScanner.checkPorts;
import static edu.hw6.task6.PortScanner.printUsedPorts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PortScannerTest {
    @Test
    @DisplayName("checkPorts test")
    public void checkPorts_shouldReturnListOfUsedPorts() {
        assertThat(checkPorts()).isNotEmpty();
    }

    @Test
    @DisplayName("printUsedPorts test")
    public void printUsedPorts_shouldPrintListOfUsedPorts() {
        assertDoesNotThrow(() -> printUsedPorts(checkPorts()));
    }
}
