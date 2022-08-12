package me.blvckbytes.bblibpersistence.mysql;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 07/22/2022

  Implementations of this interface supply the persistence module with
  all required credentials in order to establish a database connection.

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
public interface IMysqlCredentialSupplier {

  /**
   * Username to use for authentication
   */
  String getUsername();

  /**
   * Password to use for authentication
   */
  String getPassword();

  /**
   * Host to connect to
   */
  String getHost();

  /**
   * Port of the database service
   */
  int getPort();

  /**
   * Database to manipulate
   */
  String getDatabase();

}
