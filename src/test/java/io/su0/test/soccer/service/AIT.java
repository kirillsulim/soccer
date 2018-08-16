package io.su0.test.soccer.service;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

/**
 * AIT
 *
 * @author Kirill Sulim
 */

public class AIT {

    @ClassRule
    public static DockerComposeContainer soccer = new DockerComposeContainer<>(new File("src/test/resources/docker-compose-it.yml"))
            .withExposedService("soccer", 8080);

    /*

    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("src/test/resources/docker-compose-it.yml")
            .build();*/


    @Test
    public void name() throws Exception {
        HttpResponse<JsonNode> json = Unirest.get("http://localhost:8080/groups").asJson();
        System.out.println(json);
    }
}
