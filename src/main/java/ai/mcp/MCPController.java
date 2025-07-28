package ai.mcp;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.smallrye.mutiny.Uni;
import java.util.Map;
import java.util.List;

@Path("/v1/chat/completions")
@ApplicationScoped
public class MCPController {
    @Inject
    @RestClient
    OpenAIClient openAIClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<jakarta.ws.rs.core.Response> forward(ChatRequest request) {
        injectSystemPrompt(request);
        return openAIClient.chat(request);
    }

    private void injectSystemPrompt(ChatRequest request) {
        request.messages.add(0, Map.of("role", "system", "content", "You are a helpful assistant."));
    }
}
