package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.exceptions.NotFoundException;
import io.su0.test.soccer.persistence.GroupRepository;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

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

    private static NotFoundException getGroupNotFoundException(String id) {
        return new NotFoundException(String.format("Group by id '%s'", id));
    }

    public Result<Group, RuntimeException> findGroupById(String id) {
        return Result.fromOptional(
                groupRepository.findById(id),
                () -> getGroupNotFoundException(id)
        );
    }

    public Result<Void, RuntimeException> deleteById(String id) {
        return findGroupById(id).map(group -> {
            groupRepository.deleteById(group.getId());
            return null;
        });
    }

    public Result<Group, Void> createGroup(Group group) {
        return Result.ok(groupRepository.save(group));
    }

    @Transactional
    public Result<Group, RuntimeException> updateGroup(String id, Function<Group, Group> updater) {
        return findGroupById(id).map(group -> groupRepository.save(updater.apply(group)));
    }
}
