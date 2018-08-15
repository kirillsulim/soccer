package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @GetMapping("{id}")
    public Group getGroup(@PathVariable String id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable String id) {
        groupRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Group updateGroup(@PathVariable String id, @RequestBody Group group) {
        groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        group.setId(id);
        return groupRepository.save(group);
    }
}
