package me.blvckbytes.bblibpersistence.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  5reated On: 05/06/2022

  Conveniently wraps an equality query for a given field into a record.
  The field that is queried might be an operation result performed on two fields.

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
public class FieldQuery {

  private final String fieldA;
  @Nullable private final FieldOperation fieldOp;
  @Nullable private final String fieldB;
  private final EqualityOperation eqOp;
  private final Object value;

  public FieldQuery(String field, EqualityOperation eqOp, Object value) {
    this(field, null, null, eqOp, value);
  }
}
