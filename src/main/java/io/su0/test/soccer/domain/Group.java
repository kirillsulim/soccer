package io.su0.test.soccer.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Group {

    @Id
    private String id;

    private String name;

    private List<Team> teams;

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
