package pathfinder.service;

import org.camunda.bpm.model.bpmn.instance.FlowNode;

import java.util.List;

public interface PathFinder {
    List<String> findPath(FlowNode startNode, FlowNode endNode);
}
