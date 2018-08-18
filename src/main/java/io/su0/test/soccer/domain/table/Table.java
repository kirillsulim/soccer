package io.su0.test.soccer.domain.table;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Table {

    @JsonValue
    private List<TableRow> rows = new ArrayList<>();

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }
}
