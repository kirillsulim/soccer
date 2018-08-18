package io.su0.test.soccer.domain.table;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class TableRow {

    @JsonValue
    private List<String> headers = new ArrayList<>();

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
