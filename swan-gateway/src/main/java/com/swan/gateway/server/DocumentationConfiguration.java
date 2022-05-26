package com.swan.gateway.server;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DocumentationConfiguration {

    private final RouteDefinitionLocator locator;

    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*_route")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("_route", "");
            GroupedOpenApi api = GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
            groups.add(api);
        });
        return groups;
    }

}