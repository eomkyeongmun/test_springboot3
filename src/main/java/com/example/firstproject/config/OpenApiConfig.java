package com.example.firstproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title(" API 목록")
                        .description("Swagger 연습을 위한 API 목록입니다.")
                        .version("v1.0.0")
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("개발용 서버")
                ));
//                .components(new Components() //세큐리티설정
//                        .addSecuritySchemes("JWT", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                                .in(SecurityScheme.In.HEADER)
//                                .name("Authorization")
//                        )
//                );
    }
    @Bean
    public GroupedOpenApi groupedOpenApiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOpenApiV2() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/api/v2/**")
                .build();
    }
}


//@Bean 세큐리티가 있을시
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    http
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll());
//
//    return http.build();
//}