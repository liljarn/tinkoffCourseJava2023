package edu.project3.formatter;

import edu.project3.model.metrics.Metric;

public class Adoc extends AbstractFormatter {
    @Override
    public String print(Metric metric) {
        StringBuilder metricSb = new StringBuilder();
        appendHeader(metricSb, "==== ", metric.header());
        int[] maxSpaces = getMaxSpacesForEachColumn(metric.table());
        makeSeparatorLine(metricSb, maxSpaces);
        makeRow(metric.table().get(0), maxSpaces, metricSb);
        metricSb.append('\n');
        for (int i = 1; i < metric.table().size(); i++) {
            makeRow(metric.table().get(i), maxSpaces, metricSb);
        }
        makeSeparatorLine(metricSb, maxSpaces);
        return metricSb.toString();
    }

    private void makeSeparatorLine(StringBuilder metricSb, int[] maxSpaces) {
        metricSb.append(SPLITTER);
        for (int maxSpace : maxSpaces) {
            metricSb.append("=".repeat(maxSpace));
        }
        metricSb.append("=").append('\n');
    }

    private void makeRow(String row, int[] maxSpaces, StringBuilder metricSb) {
        String[] rowData = row.split(REGEX_SPLITTER);
        for (int i = 0; i < rowData.length; i++) {
            metricSb.append(SPLITTER).append(" ".repeat(maxSpaces[i] - rowData[i].length())).append(rowData[i]);
        }
        metricSb.append('\n');
    }
}
