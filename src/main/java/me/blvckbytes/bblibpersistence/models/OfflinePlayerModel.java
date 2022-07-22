package me.blvckbytes.bblibpersistence.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.blvckbytes.bblibpersistence.ModelProperty;

import java.util.UUID;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  An offline player where only their UUID is stored for revival,
  used with the OfflinePlayerTransformer.
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfflinePlayerModel extends APersistentModel {

  @ModelProperty
  private UUID uuid;
}
