package me.blvckbytes.bblibpersistence.models;

import lombok.Getter;
import me.blvckbytes.bblibpersistence.ModelProperty;
import org.bukkit.OfflinePlayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  The base of all models which hoists up common fields.

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
public abstract class APersistentModel {

  protected static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
  protected static final SimpleDateFormat dfShort = new SimpleDateFormat("dd.MM.yyyy");

  @ModelProperty
  protected UUID id;

  @ModelProperty(isInlineable = false)
  protected Date createdAt;

  @ModelProperty(isInlineable = false, isNullable = true)
  protected Date updatedAt;

  /**
   * Get the createdAt timestamp as a human readable string
   */
  public String getCreatedAtStr() {
    return getCreatedAtStr(false);
  }

  /**
   * Get the createdAt timestamp as a human readable string
   * @param shortFormat Whether to display only the date without the time
   */
  public String getCreatedAtStr(boolean shortFormat) {
    return createdAt == null ? "/" : (shortFormat ? dfShort : df).format(createdAt);
  }

  /**
   * Get the updatedAt timestamp as a human readable string
   */
  public String getUpdatedAtStr() {
    return getUpdatedAtStr(false);
  }

  /**
   * Get the updatedAt timestamp as a human readable string
   * @param shortFormat Whether to display only the date without the time
   */
  public String getUpdatedAtStr(boolean shortFormat) {
    return updatedAt == null ? "/" : (shortFormat ? dfShort : df).format(updatedAt);
  }

  /**
   * Compares two players for equality
   * @param a Player A
   * @param b Player B
   * @return Equality state
   */
  public boolean comparePlayers(OfflinePlayer a, OfflinePlayer b) {
    return a.getUniqueId().equals(b.getUniqueId());
  }

  /**
   * Called as soon as the automated parsing of
   * this model has been completed
   */
  public void afterParsing() {}
}
