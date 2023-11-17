package edu.project3.model.metrics;

import edu.project3.model.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricResourcesInfoBuilder implements MetricBuilder {
    @Override
    public Metric build(List<Log> logs) {
        List<String> metricList = new ArrayList<>();
        metricList.add("Ресурсы|Количество");
        Map<String, Integer> resourcesNumber = getMapOfNumberOfResources(logs);
        for (Map.Entry<String, Integer> entry : resourcesNumber.entrySet()) {
            metricList.add("`"  + entry.getKey() + ".html`" + "|" + entry.getValue());
        }
        return new Metric("Запрашиваемые ресурсы", metricList);
    }

    private Map<String, Integer> getMapOfNumberOfResources(List<Log> logs) {
        Map<String, Integer> resourcesNumber = new HashMap<>();
        for (Log log : logs) {
            String resource = log.request().url();
            if (!resourcesNumber.containsKey(resource)) {
                resourcesNumber.put(resource, 1);
            } else {
                resourcesNumber.put(resource, resourcesNumber.get(resource) + 1);
            }
        }
        return resourcesNumber;
    }
}
