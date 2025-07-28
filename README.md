# MCP Server

MCP Server is a proxy service for OpenAI's API built with Quarkus. It forwards requests to OpenAI's API while adding authentication and injecting a system prompt.

## Features

- Proxies requests to OpenAI's chat completions API
- Adds authentication via API key
- Automatically injects a system prompt to all requests
- Built with Quarkus for fast startup and low memory usage

## Architecture

The application consists of the following components:

- **MCPController**: Handles incoming requests and forwards them to OpenAI
- **OpenAIClient**: Interface for communicating with OpenAI's API
- **ApiKeyFilter**: Validates API keys for incoming requests
- **ChatRequest**: Data model for chat completion requests

## Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher
- Docker (optional, for containerized deployment)

## Setup and Installation

### Local Development

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/mcp-server.git
   cd mcp-server
   ```

2. Configure your OpenAI API key in `src/main/resources/application.properties`:
   ```
   openai.api.key=Bearer sk-your-openai-api-key
   ```

3. Build the application:
   ```
   ./mvnw clean package
   ```

4. Run the application in development mode:
   ```
   ./mvnw quarkus:dev
   ```

### Production Deployment

1. Build the application:
   ```
   ./mvnw clean package
   ```

2. Run the application:
   ```
   java -jar target/quarkus-app/quarkus-run.jar
   ```

### Docker Deployment

1. Build the Docker image:
   ```
   docker build -t mcp-server .
   ```

2. Run the container:
   ```
   docker run -p 8080:8080 -e OPENAI_API_KEY="Bearer sk-your-openai-api-key" mcp-server
   ```

## Usage

### Authentication

All requests to the MCP Server require authentication with an API key. Include the API key in the `Authorization` header:

```
Authorization: Bearer my-secret-key
```

### Making Requests

Send POST requests to the `/v1/chat/completions` endpoint with a JSON body:

```bash
curl -X POST \
  http://localhost:8080/v1/chat/completions \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer my-secret-key' \
  -d '{
    "model": "gpt-3.5-turbo",
    "messages": [
      {
        "role": "user",
        "content": "Hello, how are you?"
      }
    ],
    "temperature": 0.7,
    "max_tokens": 150
  }'
```

The server will automatically inject a system prompt ("You are a helpful assistant.") at the beginning of the messages array before forwarding the request to OpenAI.

## Configuration

The application can be configured through the `application.properties` file:

```
# OpenAI API URL
quarkus.rest-client.openai-client.url=https://api.openai.com

# OpenAI API Key (replace with your actual API key)
openai.api.key=Bearer sk-your-openai-api-key
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.