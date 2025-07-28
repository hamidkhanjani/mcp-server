package ai.mcp;

import java.util.List;
import java.util.Map;

public class ChatRequest {
    public String model;
    public List<Map<String, String>> messages;
    public Double temperature;
    public Integer max_tokens;
}
