package kz.yermek.NeoProject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;

import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(
//                        name = "yermek",
//                        email = "shmanovermek@gmail.com"
//                ),
//                description = "OpenAPI documentation",
//                title = "OpenAPI specification - yermek",
//                version = "1.0"
//        ),
//        servers = {
//                @Server(
//                        description = "Local ENV",
//                        url = "http://localhost:8000"
//                ),
//                @Server(
//                        description = "Team city Cloud",
//                        url = "http://172.20.10.2:8000" // TODO?????
//                )
//        }
//)
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Neo tour API Title")
                        .description("Description")
                        .version("1.0"));
    }
}

