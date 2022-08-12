package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.LocationModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Handles transforming bukkit locations.

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
public class LocationTransformer implements IDataTransformer<LocationModel, Location> {

  @Override
  public Location revive(LocationModel data) {
    if (data == null)
      return null;

    World world = Bukkit.getWorld(data.getWorld());

    if (world == null)
      return null;

    return new Location(
      world,
      data.getX(), data.getY(), data.getZ(),
      (float) data.getYaw(), (float) data.getPitch()
    );
  }

  @Override
  public LocationModel replace(Location data) {
    if (data == null)
      return null;

    World world = data.getWorld();

    if (world == null)
      return null;

    return new LocationModel(
      data.getX(), data.getY(), data.getZ(),
      data.getYaw(), data.getPitch(),
      data.getWorld().getName()
    );
  }

  @Override
  public Class<LocationModel> getKnownClass() {
    return LocationModel.class;
  }

  @Override
  public Class<Location> getForeignClass() {
    return Location.class;
  }
}
