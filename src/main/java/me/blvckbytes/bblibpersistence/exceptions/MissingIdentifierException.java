package me.blvckbytes.bblibpersistence.exceptions;

import lombok.Getter;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/05/2022

  Signals that a model is missing it's identifier (for updates or deletions).

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
public class MissingIdentifierException extends PersistenceException {

  private final String model;

  public MissingIdentifierException(String model) {
    super("The model '" + model + "' is missing it's identifier value!");

    this.model = model;
  }
}
