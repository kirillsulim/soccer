package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GroupService
 *
 * @author Kirill Sulim
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
