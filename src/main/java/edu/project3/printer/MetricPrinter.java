package edu.project3.printer;

import edu.project3.formatter.Adoc;
import edu.project3.formatter.FormatPrinter;
import edu.project3.formatter.Markdown;
import edu.project3.model.metrics.Metric;
import java.util.List;

public class MetricPrinter {
    private FormatPrinter getFormatPrinter(String format) {
        FormatPrinter printer = switch (format) {
            case "markdown" -> new Markdown();
            case "adoc" -> new Adoc();
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
        return printer;
    }

    public void printMetrics(String format, List<Metric> metrics) {
        FormatPrinter printer = getFormatPrinter(format);
        for (Metric metric : metrics) {
            printer.print(metric);
        }
    }
}
