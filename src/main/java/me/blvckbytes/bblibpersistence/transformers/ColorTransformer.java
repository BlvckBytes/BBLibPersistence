package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.ColorModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.Color;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/31/2022

  Handles transforming bukkit colors.
*/
@AutoConstruct
public class ColorTransformer implements IDataTransformer<ColorModel, Color> {

  @Override
  public Color revive(ColorModel data) {
    if (data.getR() == null || data.getG() == null || data.getB() == null)
      return null;

    return Color.fromRGB(data.getR(), data.getG(), data.getB());
  }

  @Override
  public ColorModel replace(Color data) {
    if (data == null)
      return null;

    return new ColorModel(
      data.getRed(),
      data.getGreen(),
      data.getBlue()
    );
  }

  @Override
  public Class<ColorModel> getKnownClass() {
    return ColorModel.class;
  }

  @Override
  public Class<Color> getForeignClass() {
    return Color.class;
  }
}
