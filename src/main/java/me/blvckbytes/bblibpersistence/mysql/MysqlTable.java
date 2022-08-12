package me.blvckbytes.bblibpersistence.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/05/2022

  Represents a table in a MySQL database.

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
public class MysqlTable {
  private String name;
  private List<MysqlColumn> columns;

  // Whether this table is used in combination with a
  // transformer and thus not an entity of it's own
  private boolean isTransformer;
}
