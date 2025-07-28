package ai.mcp;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String key = requestContext.getHeaderString("Authorization");
        if (!"Bearer my-secret-key".equals(key)) {
            requestContext.abortWith(Response.status(401).build());
        }
    }
}
