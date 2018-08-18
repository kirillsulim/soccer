package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.table.Table;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    private final GroupService groupService;

    public TableService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Result<Table, RuntimeException> getTableForGroup(String groupId) {
        return groupService.findGroupById(groupId).map(TableConverter::convert);
    }
}
