package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.table.Table;
import io.su0.test.soccer.service.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups/{groupId}/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public Table get(@PathVariable String groupId) {
        return tableService.getTableForGroup(groupId).getOrThrow();
    }
}
