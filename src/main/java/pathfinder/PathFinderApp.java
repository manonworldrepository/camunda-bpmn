package pathfinder;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import pathfinder.service.BpmnFetcher;
import pathfinder.service.DepthFirstSearchPathFinder;
import pathfinder.service.FlowNodeFactory;
import pathfinder.service.PathFinder;

import java.util.List;
import java.io.ByteArrayInputStream;

public class PathFinderApp {

    public void run(String startNodeId, String endNodeId) {
        try {
            String bpmnXml = BpmnFetcher.fetchBpmnXml();

            BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(bpmnXml.getBytes()));

            FlowNode startNode = FlowNodeFactory.findFlowNodeById(modelInstance, startNodeId);
            FlowNode endNode = FlowNodeFactory.findFlowNodeById(modelInstance, endNodeId);

            if (startNode == null || endNode == null) {
                System.err.println("Invalid start or end node ID.");
                System.exit(-1);
            }

            PathFinder pathFinder = new DepthFirstSearchPathFinder();
            List<String> path = pathFinder.findPath(startNode, endNode);

            if (path != null) {
                System.out.println("The path from " + startNodeId + " to " + endNodeId + " is:");
                System.out.println(path);
            } else {
                System.err.println("No path found between the specified nodes.");
                System.exit(-1);
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Program requires exactly two arguments: <startNodeId> <endNodeId>");
            System.exit(-1);
        }

        String startNodeId = args[0];
        String endNodeId = args[1];
        PathFinderApp app = new PathFinderApp();
        app.run(startNodeId, endNodeId);
    }
}
