package az.company.orders.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "http://localhost:9090",
                        description = "API Gateway"
                )
        }
)
public class OpenApiConfig {
}
