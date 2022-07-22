package me.blvckbytes.bblibpersistence.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.blvckbytes.bblibpersistence.ForeignKeyAction;
import me.blvckbytes.bblibpersistence.MigrationDefault;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/05/2022

  Represents a column in a MySQL database.
*/
@Getter
@AllArgsConstructor
public class MysqlColumn {
  private final String name;
  private final MysqlType type;
  private final boolean isNullable;
  private final MigrationDefault migrationDefault;
  private final boolean isUnique;

  // Whether this column can be inlined when using transformers
  private final boolean isInlineable;

  // The corresponding field within the persistent model
  private final Field modelField;

  // The field within a transformer's known class that has been inlined
  // through this cloned column, null for non-transformed fields
  @Nullable private final Field knownModelField;

  // The table which this column references
  @Nullable private final MysqlTable foreignKey;

  // Action to take when the foreign key changes
  private final ForeignKeyAction foreignAction;

  // The "id" field is reserved for the primary key
  public boolean isPrimaryKey() {
    return name.equals("id");
  }
}
