package io.su0.test.soccer.domain;

import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.Map;

public class Group {

    @Id
    private String id;

    private String name;

    private Map<String, Team> teams = Collections.emptyMap();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, Team> teams) {
        this.teams = teams;
    }
}
