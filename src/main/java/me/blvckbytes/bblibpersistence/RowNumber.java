package me.blvckbytes.bblibpersistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/18/2022

  Marks a field of a model as a row number receiver which itself is
  figured out at runtime and not stored in the table of the model.

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
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RowNumber {

  /**
   * Specifies by what field of the model this row number
   * counter will be partitioned by, i.e. set to 1 again
   */
  String partitionedBy() default "id";
}
