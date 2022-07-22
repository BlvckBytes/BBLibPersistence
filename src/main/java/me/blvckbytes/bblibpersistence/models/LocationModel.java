package me.blvckbytes.bblibpersistence.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.blvckbytes.bblibpersistence.ModelProperty;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  A specific position within a world of the server where only the
  coordinates as well as the world-name is stored for revival, used
  with the LocationTransformer.
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationModel extends APersistentModel {

  @ModelProperty
  private double x, y, z, yaw, pitch;

  @ModelProperty
  private String world;
}
