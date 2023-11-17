package edu.project3.formatter;

import edu.project3.model.metrics.Metric;
import java.util.List;

public class Markdown implements FormatPrinter {
    private static final String REGEX_SPLITTER = "\\|";
    private static final String SPLITTER = "|";

    @Override
    public void print(Metric metric) {
        StringBuilder metricSb = new StringBuilder();
        metricSb.append("#### ").append(metric.header()).append('\n');
        int[] maxSpaces = getMaxSpacesForEachColumn(metric.table());
        makeMarkdownRow(metric.table().get(0), maxSpaces, metricSb);
        for (int maxSpace : maxSpaces) {
            metricSb.append(SPLITTER).append(":").append("-".repeat(maxSpace - 1));
        }
        metricSb.append(SPLITTER).append('\n');
        for (int i = 1; i < metric.table().size(); i++) {
            makeMarkdownRow(metric.table().get(i), maxSpaces, metricSb);
        }
        System.out.println(metricSb);
    }

    private void makeMarkdownRow(String row, int[] maxSpaces, StringBuilder metricSb) {
        String[] rowData = row.split(REGEX_SPLITTER);
        for (int i = 0; i < rowData.length; i++) {
            metricSb.append(SPLITTER).append(rowData[i]).append(" ".repeat(maxSpaces[i] - rowData[i].length()));
        }
        metricSb.append(SPLITTER).append('\n');
    }

    private int[] getMaxSpacesForEachColumn(List<String> table) {
        int columnsCount = table.get(0).split(REGEX_SPLITTER).length;
        int[] maxSpaces = new int[columnsCount];
        for (String row : table) {
            String[] rowString = row.split(REGEX_SPLITTER);
            for (int i = 0; i < rowString.length; i++) {
                if (rowString[i].length() > maxSpaces[i]) {
                    maxSpaces[i] = rowString[i].length();
                }
            }
        }
        return maxSpaces;
    }
}
