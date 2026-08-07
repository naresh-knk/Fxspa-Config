package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;

public class GeminiReportAnalyzer {

    private static String apiKey = System.getenv("GEMINI_API_KEY"); 
    private static final String MODEL_NAME = "gemini-2.5-flash"; 

    public static String analyzeFailure(String testName, String exceptionMessage, String stackTrace, File screenshotFile) {
        
        // 1. Verify environment API key availability safely
        if (apiKey == null || apiKey.isEmpty()) { 
            return "{\"category\":\"Error\",\"confidence\":0,\"diagnosis\":\"Missing API Key\",\"fix\":\"Set the GEMINI_API_KEY environment variable on this host machine.\"}"; 
        }

        apiKey = apiKey.trim();
        String geminiUrl = "https://generativelanguage.googleapis.com/v1beta/models/" + MODEL_NAME + ":generateContent?key=" + apiKey;

        try {
            String systemInstructions = "You are an elite Automation QA AI analyzing a Selenium framework failure. "
        + "Review the provided context details and optional dashboard screenshot image meticulously to identify technical bugs.\n\n"
        + "CRITICAL FOR CONFIDENCE METRIC:\n"
        + "The 'confidence' key must represent an integer percentage value from 0 to 100.\n"
        + "- If you can identify a clear root cause from the exception message or stack trace, assign a confidence score between 80 and 100.\n"
        + "- Do not drop the confidence score below 75 unless the log context is completely corrupt, missing, or entirely unrelated to the screenshot.";
            JSONObject requestBody = new JSONObject();
            
            // Core Gemini Request Configurations (Structured Schema Type System Enforcement)
            JSONObject config = new JSONObject();
            config.put("response_mime_type", "application/json");
            
            JSONObject responseSchema = new JSONObject();
            responseSchema.put("type", "OBJECT");
            
            JSONObject properties = new JSONObject();
            properties.put("category", new JSONObject().put("type", "STRING"));
            properties.put("confidence", new JSONObject().put("type", "INTEGER"));
            properties.put("diagnosis", new JSONObject().put("type", "STRING"));
            properties.put("fix", new JSONObject().put("type", "STRING"));
            
            JSONArray requiredFields = new JSONArray();
            requiredFields.put("category");
            requiredFields.put("confidence");
            requiredFields.put("diagnosis");
            requiredFields.put("fix");
            
            responseSchema.put("properties", properties);
            responseSchema.put("required", requiredFields);
            config.put("response_schema", responseSchema);
            
            requestBody.put("generationConfig", config);

            // Set system instructions as context definition parameters
            JSONObject systemInstructionObj = new JSONObject();
            JSONArray partsInstArray = new JSONArray();
            partsInstArray.put(new JSONObject().put("text", systemInstructions));
            systemInstructionObj.put("parts", partsInstArray);
            requestBody.put("systemInstruction", systemInstructionObj);

            // Prepare Content Payload Context Lifecycle Container
            JSONArray contentsArray = new JSONArray();
            JSONObject contentObj = new JSONObject();
            contentObj.put("role", "user");
            JSONArray partsArray = new JSONArray();

            // Append textual test log context
            JSONObject textPart = new JSONObject();
            textPart.put("text", String.format("Test Case Name: %s\nException Message: %s\nStack Trace Log:\n%s", 
                    testName, exceptionMessage, stackTrace.length() > 1200 ? stackTrace.substring(0, 1200) : stackTrace));
            partsArray.put(textPart);

            // Multi-modal processing attachment for screenshot matrix layer
            if (screenshotFile != null && screenshotFile.exists()) {
                byte[] fileContent = Files.readAllBytes(screenshotFile.toPath());
                String base64Image = Base64.getEncoder().encodeToString(fileContent);

                JSONObject imagePart = new JSONObject();
                JSONObject inlineData = new JSONObject();
                inlineData.put("mime_type", "image/png");
                inlineData.put("data", base64Image);
                imagePart.put("inline_data", inlineData);
                partsArray.put(imagePart);
            }

            contentObj.put("parts", partsArray);
            contentsArray.put(contentObj);
            requestBody.put("contents", contentsArray);

            // Execute HTTP Request Handshake Operations
            URL url = new URL(geminiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name());
                String responseBody = scanner.useDelimiter("\\A").next();
                scanner.close();

                JSONObject jsonResponse = new JSONObject(responseBody);
                return jsonResponse.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text");
            } else {
                return "{\"category\":\"API Error\",\"confidence\":0,\"diagnosis\":\"HTTP Session Response Code Error " + responseCode + "\",\"fix\":\"Check framework configuration parameters or API limits.\"}";
            }
        } catch (Exception e) {
            String cleanMsg = e.getMessage() != null ? e.getMessage().replace("\"", "\\\"") : "NullPointerException caught inside Analyzer.";
            return "{\"category\":\"Exception\",\"confidence\":0,\"diagnosis\":\"" + cleanMsg + "\",\"fix\":\"Check the network configuration, endpoint parameters, or JSON payload structural integrity.\"}";
        }
    }
}