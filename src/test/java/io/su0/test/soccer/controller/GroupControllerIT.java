package io.su0.test.soccer.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.su0.test.soccer.IntegrationTestBase;
import io.su0.test.soccer.domain.Group;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupControllerIT extends IntegrationTestBase {

    @Test
    public void shouldGetEmptyGroups() throws Exception {
        getGroups();
    }

    private List<Group> getGroups() throws Exception {
        HttpResponse<Group[]> response = Unirest.get(URL + "groups").asObject(Group[].class);
        assertThat(response.getStatus(), is(200));
        return Arrays.asList(response.getBody());
    }
}
