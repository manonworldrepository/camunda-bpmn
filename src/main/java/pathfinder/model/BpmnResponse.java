package pathfinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BpmnResponse(@JsonProperty("bpmn20Xml") String bpmn20Xml) {}
