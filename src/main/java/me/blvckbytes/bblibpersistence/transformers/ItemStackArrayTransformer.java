package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibdi.AutoConstruct;
import me.blvckbytes.bblibdi.AutoInject;
import me.blvckbytes.bblibpersistence.models.ItemStackArrayModel;
import me.blvckbytes.bblibutil.logger.ILogger;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 08/06/2022

  Handles transforming arrays of bukkit item stacks.

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
public class ItemStackArrayTransformer implements IDataTransformer<ItemStackArrayModel, ItemStack[]> {

  private final ILogger logger;

  public ItemStackArrayTransformer(
    @AutoInject ILogger logger
  ) {
    this.logger = logger;
  }

  @Override
  public ItemStack[] revive(ItemStackArrayModel data) {
    if (data == null)
      return null;

    ItemStack[] res;

    try {
      byte[] bytes = Base64Coder.decodeLines(data.getBase64Items());
      ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

      int streamLen = dataInput.readInt();
      res = new ItemStack[streamLen];

      for (int i = 0; i < streamLen; i++)
        res[i] = (ItemStack) dataInput.readObject();

      dataInput.close();
      inputStream.close();
    } catch (Exception e) {
      logger.logError(e);
      res = null;
    }

    return res;
  }

  @Override
  public ItemStackArrayModel replace(ItemStack[] data) {
    if (data == null)
      return null;

    String base64;

    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

      dataOutput.writeInt(data.length);
      for (ItemStack datum : data)
        dataOutput.writeObject(datum);

      dataOutput.close();
      base64 = Base64Coder.encodeLines(outputStream.toByteArray());
      outputStream.close();
    } catch (Exception e) {
      logger.logError(e);
      base64 = "";
    }

    return new ItemStackArrayModel(base64);
  }

  @Override
  public Class<ItemStackArrayModel> getKnownClass() {
    return ItemStackArrayModel.class;
  }

  @Override
  public Class<ItemStack[]> getForeignClass() {
    return ItemStack[].class;
  }
}
