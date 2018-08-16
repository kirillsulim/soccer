package io.su0.test.soccer.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private DockerComposeContainer soccer;

    @BeforeEach
    void setUp() {

        soccer = new DockerComposeContainer<>(new File("src/test/resources/docker-compose-it.yml"))
                .withExposedService("soccer", 8080);



        MockitoAnnotations.initMocks(this);
    }

    @Test
    void name() {

        soccer.start();

        HttpResponse<JsonNode> json = Unirest.get("http://localhost:8080/groups").asJson();
        System.out.println(json);

        soccer.stop();
    }
}
