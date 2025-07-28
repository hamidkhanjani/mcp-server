package ai.mcp;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey = "openai-client")
@Path("/v1/chat/completions")
@ClientHeaderParam(name = "Authorization", value = "${openai.api.key}")
public interface OpenAIClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<jakarta.ws.rs.core.Response> chat(ChatRequest request);
}
