package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Group> findGroupById(String id) {
        return groupRepository.findById(id);
    }

    public void deleteById(String id) {
        groupRepository.deleteById(id);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> updateGroup(String id, Group newData) {
        Optional<Group> found = groupRepository.findById(id);
        if (found.isPresent()) {
            newData.setId(id);
            return Optional.of(groupRepository.save(newData));
        } else {
            return Optional.empty();
        }
    }
}
