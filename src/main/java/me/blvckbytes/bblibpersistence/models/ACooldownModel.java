package me.blvckbytes.bblibpersistence.models;

import lombok.*;
import me.blvckbytes.bblibpersistence.IPersistence;
import me.blvckbytes.bblibpersistence.ModelProperty;
import me.blvckbytes.bblibpersistence.exceptions.PersistenceException;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Field;
import java.util.Date;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/08/2022

  Represents a model that has a cooldown to it's use.

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
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ACooldownModel extends APersistentModel implements ICooldownable {

  @ModelProperty
  protected int cooldownSeconds;

  /**
   * Generate a token which will be unique for this model instance's properties
   */
  public String generateToken() {
    Class<?> c = getClass();
    StringBuilder token = new StringBuilder(c.getSimpleName().toLowerCase());

    try {

      // Loop the class hierarchy to find all declared fields
      boolean uniqueFieldFound = false;
      while (c.getSuperclass() != null) {
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
          Field f = fields[i];

          // Not a model property or not unique
          ModelProperty mp = f.getAnnotation(ModelProperty.class);
          if (mp == null || !mp.isUnique())
            continue;

          f.setAccessible(true);

          // Append the unique field's value to the token
          token
            .append(i == 0 ? "__" : "_")
            .append(f.get(this));

          uniqueFieldFound = true;
        }
        c = c.getSuperclass();
      }

      if (!uniqueFieldFound)
        throw new IllegalStateException("Model contains no unique fields");

    } catch (Exception e) {
      throw new PersistenceException("Could not generate a unique token for " + c + ": " + e.getMessage());
    }

    return token.toString();
  }

  /**
   * Create a new cooldown for a given player which will be targetting
   * the current model's unique properties and store it.
   * @param holder Cooldown holder
   * @param pers Persistency ref
   */
  public void storeCooldownFor(OfflinePlayer holder, IPersistence pers) throws PersistenceException {
    pers.store(new CooldownSessionModel(
      holder,
      new Date(System.currentTimeMillis() + cooldownSeconds * 1000L),
      generateToken()
    ));
  }

  /**
   * Get the remaining cooldown duration in seconds for a given player
   * which is targetting the current model's unique properties. If the cooldown
   * is already expired, this call will delete the unneeded entry.
   * @param holder Cooldown holder
   * @param pers Persistency ref
   * @return -1 if there's no active cooldown or the number of seconds remaining
   */
  public long getCooldownRemaining(OfflinePlayer holder, IPersistence pers) throws PersistenceException {
    return CooldownSessionModel.getCooldownRemaining(holder, pers, generateToken());
  }

  /**
   * Gets the number of seconds this cooldown lasts for
   */
  public int getDurationSeconds() {
    return this.cooldownSeconds;
  }
}
