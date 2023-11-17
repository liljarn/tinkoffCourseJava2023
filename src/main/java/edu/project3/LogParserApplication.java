package edu.project3;

import edu.project3.logparser.LogParser;
import edu.project3.logparser.NginxLogParser;
import edu.project3.model.Log;
import edu.project3.model.ParseFormat;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricMainInfoBuilder;
import edu.project3.model.metrics.MetricRequestMethodsInfoBuilder;
import edu.project3.model.metrics.MetricResourcesInfoBuilder;
import edu.project3.model.metrics.MetricResponseCodesInfoBuilder;
import edu.project3.printer.MetricPrinter;
import edu.project3.receiver.HttpLogReceiver;
import edu.project3.receiver.PathLogReceiver;
import edu.project3.receiver.Receiver;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.receiver.PathParser.getPaths;

public class LogParserApplication {
    public void run(String[] args) {
        ParseFormat parseFormat = getParseFormat(String.join(" ", args));
        Receiver receiver;
        if (parseFormat.path().startsWith("http")) {
            receiver = new HttpLogReceiver(parseFormat.path());
        } else {
            List<Path> pathsToLogs = getPaths(parseFormat.path());
            receiver = new PathLogReceiver(pathsToLogs);
        }
        LogParser logParser = new NginxLogParser();
        List<Log> logs = logParser.parseLogs(receiver.receive());
        List<Metric> metrics = getAllMetrics(parseFormat, logs);
        MetricPrinter printer = new MetricPrinter();
        printer.printMetrics(parseFormat.format(), metrics);
    }

    private ParseFormat getParseFormat(String args) {
        String path = "";
        String fromDate = "";
        String toDate = "";
        String format = "";
        Pattern pattern = Pattern.compile("--(path|from|to|format)\\s(\\S+)");
        Matcher matcher = pattern.matcher(args);
        while (matcher.find()) {
            String command = matcher.group(1);
            String value = matcher.group(2);
            switch (command) {
                case "path":
                    path = value;
                    break;
                case "from":
                    fromDate = value;
                    break;
                case "to":
                    toDate = value;
                    break;
                case "format":
                    format = value;
                    break;
                default:
                    break;
            }
        }

        return new ParseFormat(path, fromDate, toDate, format);
    }

    private List<Metric> getAllMetrics(ParseFormat parseFormat, List<Log> logs) {
        MetricBuilder metricMainInfoBuilder = new MetricMainInfoBuilder(
            !parseFormat.fromDate().isEmpty() ? OffsetDateTime.parse(parseFormat.fromDate()) : OffsetDateTime.MIN,
            !parseFormat.toDate().isEmpty() ? OffsetDateTime.parse(parseFormat.toDate()) : OffsetDateTime.MAX,
            List.of(parseFormat.path())
        );
        MetricBuilder metricResourcesInfoBuilder = new MetricResourcesInfoBuilder();
        MetricBuilder metricResponseCodesInfoBuilder = new MetricResponseCodesInfoBuilder();
        MetricBuilder metricRequestMethodsInfoBuilder = new MetricRequestMethodsInfoBuilder();
        return List.of(
            metricMainInfoBuilder.build(logs),
            metricResourcesInfoBuilder.build(logs),
            metricResponseCodesInfoBuilder.build(logs),
            metricRequestMethodsInfoBuilder.build(logs)
        );
    }
}
