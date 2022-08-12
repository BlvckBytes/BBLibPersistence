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

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.

  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
