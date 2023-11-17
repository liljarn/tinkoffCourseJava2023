package edu.project3.model.metrics;

import edu.project3.model.Log;
import edu.project3.remote.HttpStatusCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricRequestMethodsInfoBuilder implements MetricBuilder {
    @Override
    public Metric build(List<Log> logs) {
        List<String> metricList = new ArrayList<>();
        metricList.add("HTTP запрос|Количество");
        Map<String, Integer> responseCodes = getMapOfHttpMethods(logs);
        for (Map.Entry<String, Integer> entry : responseCodes.entrySet()) {
            metricList.add(entry.getKey() + "|" + entry.getValue());
        }
        return new Metric("HTTP запросы", metricList);
    }

    private Map<String, Integer> getMapOfHttpMethods(List<Log> logs) {
        Map<String, Integer> responseCodes = new HashMap<>();
        for (Log log : logs) {
            String httpMethod = log.request().method();
            if (!responseCodes.containsKey(httpMethod)) {
                responseCodes.put(httpMethod, 1);
            } else {
                responseCodes.put(httpMethod, responseCodes.get(httpMethod) + 1);
            }
        }
        return responseCodes;
    }
}
