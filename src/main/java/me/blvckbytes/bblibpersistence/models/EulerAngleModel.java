package me.blvckbytes.bblibpersistence.models;

import lombok.*;
import me.blvckbytes.bblibpersistence.ModelProperty;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 06/03/2022

  Wraps all components of a bukkit's EulerAngle.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EulerAngleModel extends APersistentModel {

  @ModelProperty
  private double x, y, z;
}
