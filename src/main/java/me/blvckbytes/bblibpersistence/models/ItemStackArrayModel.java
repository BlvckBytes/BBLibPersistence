package me.blvckbytes.bblibpersistence.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.blvckbytes.bblibpersistence.ModelProperty;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 08/06/2022

  An array which has a base64 encoded string of it's item contents that
  also contains the information about how many items are encoded within the string.
*/
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemStackArrayModel extends APersistentModel {

  @Getter
  @ModelProperty
  private String base64Items;
}
