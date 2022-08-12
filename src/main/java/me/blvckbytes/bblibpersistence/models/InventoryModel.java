package me.blvckbytes.bblibpersistence.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.blvckbytes.bblibpersistence.ModelProperty;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/06/2022

  An inventory which has a base64 encoded string of it's item contents that
  also contains the information about how many items are encoded within the string.

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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryModel extends APersistentModel {

  @Getter
  @ModelProperty
  private String base64Items;
}
