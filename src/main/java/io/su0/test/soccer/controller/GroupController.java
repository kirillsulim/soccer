package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group).getOrThrow();
    }

    @GetMapping("{id}")
    public Group getGroup(@PathVariable String id) {
        return groupService.findGroupById(id).getOrThrow();
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.deleteById(id).getOrThrow();
    }

    @PutMapping("{id}")
    public Group updateGroup(@PathVariable String id, @RequestBody Group group) {
        return groupService
                .updateGroup(id, original -> {
                    group.setId(id);
                    return group;
                })
                .getOrThrow();
    }
}
