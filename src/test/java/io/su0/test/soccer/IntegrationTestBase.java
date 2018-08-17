package io.su0.test.soccer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.io.IOException;

public class IntegrationTestBase {

    protected static final String URL = "http://localhost:8080/";

    public static DockerComposeContainer soccer = new DockerComposeContainer<>(new File("docker-compose-it.yml"))
            .withExposedService("soccer", 8080);

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        soccer.start();
    }
}
