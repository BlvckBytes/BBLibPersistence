package me.blvckbytes.bbheads.persistence.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.blvckbytes.bbheads.persistence.ModelProperty;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/06/2022

  An inventory which has a base64 encoded string of it's item contents that
  also contains the information about how many items are encoded within the string.
*/
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryModel extends APersistentModel {

  @Getter
  @ModelProperty
  private String base64Items;
}
