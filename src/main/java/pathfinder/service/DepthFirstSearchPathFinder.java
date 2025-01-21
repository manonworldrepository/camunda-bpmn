package pathfinder.service;

import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class DepthFirstSearchPathFinder implements PathFinder {

    @Override
    public List<String> findPath(FlowNode startNode, FlowNode endNode) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        if (dfs(startNode, endNode, visited, path)) {
            return path;
        } else {
            return null;
        }
    }

    private boolean dfs(FlowNode currentNode, FlowNode endNode, Set<String> visited, List<String> path) {
        path.add(currentNode.getId());
        visited.add(currentNode.getId());

        if (currentNode.equals(endNode)) {
            return true;
        }

        for (SequenceFlow outgoingFlow : currentNode.getOutgoing()) {
            FlowNode nextNode = outgoingFlow.getTarget();
            if (!visited.contains(nextNode.getId()) && dfs(nextNode, endNode, visited, path)) {
                return true;
            }
        }

        path.removeLast();
        return false;
    }
}
