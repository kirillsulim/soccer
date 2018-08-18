package io.su0.test.soccer.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.su0.test.soccer.IntegrationTestBase;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TeamControllerIT extends IntegrationTestBase {

    @Test
    public void name() throws Exception {
        Group group = new Group();
        group.setName("A");

        HttpResponse<Group> createGroupResponse = Unirest.post(URL + "groups")
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .body(group)
                .asObject(Group.class);
        assertThat(createGroupResponse.getStatus(), is(200));

        String id = createGroupResponse.getBody().getId();

        HttpResponse<Team[]> teamsResponse = Unirest.get(URL + "groups/{id}/teams")
                .routeParam("id", id)
                .asObject(Team[].class);

        assertThat(teamsResponse.getStatus(), is(200));
        assertThat(teamsResponse.getBody().length, is(0));
    }
}
