package kz.yermek.NeoProject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;

import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "yermek",
                        email = "shmanovermek@gmail.com"
                ),
                description = "OpenAPI documentation",
                title = "OpenAPI specification - yermek",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Api Title",
                        url = "https://neotour-production-8df4.up.railway.app/" // TODO?????
                )
        }
)
public class OpenAPIConfig {

}

