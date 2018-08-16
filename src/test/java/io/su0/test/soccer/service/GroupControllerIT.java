package io.su0.test.soccer.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import io.su0.test.soccer.domain.Group;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * GroupControllerIT
 *
 * @author Kirill Sulim
 */

public class GroupControllerIT {

    public static final String URL = "http://localhost:8080/";

    @ClassRule
    public static DockerComposeContainer soccer = new DockerComposeContainer<>(new File("docker-compose-it.yml"))
            .withExposedService("soccer", 8080);

    @BeforeClass
    public static void setUp() throws Exception {
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
    }

    @Test
    public void name() throws Exception {
        getGroups();

    }

    private List<Group> getGroups() throws Exception {
        HttpResponse<Group[]> response = Unirest.get(URL + "groups").asObject(Group[].class);
        assertThat(response.getStatus(), is(200));
        return Arrays.asList(response.getBody());
    }
}
