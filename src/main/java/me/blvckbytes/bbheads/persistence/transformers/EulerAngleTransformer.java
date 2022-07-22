package me.blvckbytes.bbheads.persistence.transformers;

import me.blvckbytes.bbheads.persistence.models.EulerAngleModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.util.EulerAngle;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 06/03/2022

  Transforms bukkit euler angles to their components within a model wrapper.
*/
@AutoConstruct
public class EulerAngleTransformer implements IDataTransformer<EulerAngleModel, EulerAngle> {

  @Override
  public EulerAngle revive(EulerAngleModel data) {
    if (data == null)
      return null;

    return new EulerAngle(data.getX(), data.getY(), data.getZ());
  }

  @Override
  public EulerAngleModel replace(EulerAngle data) {
    if (data == null)
      return null;

    return new EulerAngleModel(data.getX(), data.getY(), data.getZ());
  }

  @Override
  public Class<EulerAngleModel> getKnownClass() {
    return EulerAngleModel.class;
  }

  @Override
  public Class<EulerAngle> getForeignClass() {
    return EulerAngle.class;
  }
}
