package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.APersistentModel;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Represents the functionality of a data transformer that's used
  when handling foreign data for R/W.

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
public interface IDataTransformer<Known extends APersistentModel, Foreign> {

  /**
   * Revive a known object back into it's foreign form it was in before persisting
   * @param data Known data loaded from persistence
   * @return Foreign data after the transformation
   */
  Foreign revive(Known data);

  /**
   * Replace a foreign object into it's known persistable representation before writing
   * @param data Foreign data to be stored
   * @return Known data to be saved
   */
  Known replace(Foreign data);

  /**
   * Get the known class (internal model)
   */
  Class<Known> getKnownClass();

  /**
   * Get the foreign class (external model)
   */
  Class<Foreign> getForeignClass();
}
