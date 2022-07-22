package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.OfflinePlayerModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Handles transforming bukkit offline-players.
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