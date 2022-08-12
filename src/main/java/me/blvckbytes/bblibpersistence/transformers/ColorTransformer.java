package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.ColorModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import org.bukkit.Color;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/31/2022

  Handles transforming bukkit colors.

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
