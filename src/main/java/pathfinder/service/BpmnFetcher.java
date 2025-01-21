package pathfinder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pathfinder.model.BpmnResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class BpmnFetcher {

    private static final Logger logger = LoggerFactory.getLogger(BpmnFetcher.class);
    private static final String BPMN_FETCH_URL = "https://n35ro2ic4d.execute-api.eu-central-1.amazonaws.com/prod/engine-rest/process-definition/key/invoice/xml";
    private static final int HTTP_OK = 200;

    private BpmnFetcher() {}

    public static String fetchBpmnXml() throws IOException {
        HttpURLConnection connection = null;

        try {
            connection = initializeHttpConnection(BPMN_FETCH_URL);

            if (connection.getResponseCode() != HTTP_OK) {
                handleErrorResponse(connection);
            }

            String response = readResponse(connection);
            return parseBpmnResponse(response).bpmn20Xml();
        } catch (Exception e) {
            logger.error("Exception while fetching BPMN XML: {}", e.getMessage(), e);
            throw e;
        } finally {
            Objects.requireNonNullElse(connection, connection).disconnect();
        }
    }

    private static HttpURLConnection initializeHttpConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(10000);
        return connection;
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    private static BpmnResponse parseBpmnResponse(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, BpmnResponse.class);
    }

    private static void handleErrorResponse(HttpURLConnection connection) throws IOException {
        throw new IOException(String.format("Failed to fetch BPMN XML: HTTP code %d", connection.getResponseCode()));
    }
}