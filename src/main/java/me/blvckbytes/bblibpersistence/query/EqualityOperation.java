package me.blvckbytes.bblibpersistence.query;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/06/2022

  Represents all existing equality operations that can be performed on various columns.

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
public enum EqualityOperation {
  // Equals
  EQ,

  // Equals ignorecase
  EQ_IC,

  // Contains
  CONT,

  // Contains ignorecase
  CONT_IC,

  // Starts with
  STARTS,

  // Starts with ignorecase
  STARTS_IC,

  // Ends with
  ENDS,

  // Ends with ignorecase
  ENDS_IC,

  // Not equals
  NEQ,

  // Not equals ignorecase
  NEQ_IC,

  // Greater than
  GT,

  // Less than
  LT,

  // Greater than or equal
  GTE,

  // Less than or equal
  LTE
}
