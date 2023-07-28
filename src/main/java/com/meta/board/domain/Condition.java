package com.meta.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Condition {
    private String keyword = "";
    private String sort = "create_date";
    private String list = "title";
    private String dir = "ASC";
}
