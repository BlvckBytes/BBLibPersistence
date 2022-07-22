package me.blvckbytes.bblibpersistence.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/05/2022

  Represents a table in a MySQL database.
*/
@Getter
@AllArgsConstructor
public class MysqlTable {
  private String name;
  private List<MysqlColumn> columns;

  // Whether this table is used in combination with a
  // transformer and thus not an entity of it's own
  private boolean isTransformer;
}
