package pathfinder.service;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

public class FlowNodeFactory {

    private static final String NULL_INPUT_ERROR = "Input cannot be null: %s";

    private FlowNodeFactory() {}

    public static FlowNode findFlowNodeById(BpmnModelInstance modelInstance, String nodeId) {
        validateNotNull(modelInstance, "modelInstance");
        validateNotNull(nodeId, "nodeId");
        return modelInstance.getModelElementById(nodeId);
    }

    private static void validateNotNull(Object input, String fieldName) {
        if (input == null) {
            throw new IllegalArgumentException(String.format(NULL_INPUT_ERROR, fieldName));
        }
    }
}