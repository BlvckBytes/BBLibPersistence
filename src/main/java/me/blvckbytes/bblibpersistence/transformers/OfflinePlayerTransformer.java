package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.OfflinePlayerModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Handles transforming bukkit offline-players.

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
@AutoConstruct
public class OfflinePlayerTransformer implements IDataTransformer<OfflinePlayerModel, OfflinePlayer> {

  @Override
  public OfflinePlayer revive(OfflinePlayerModel data) {
    if (data == null)
      return null;

    return Bukkit.getOfflinePlayer(data.getUuid());
  }

  @Override
  public OfflinePlayerModel replace(OfflinePlayer data) {
    if (data == null)
      return null;

    return new OfflinePlayerModel(data.getUniqueId());
  }

  @Override
  public Class<OfflinePlayerModel> getKnownClass() {
    return OfflinePlayerModel.class;
  }

  @Override
  public Class<OfflinePlayer> getForeignClass() {
    return OfflinePlayer.class;
  }
}
