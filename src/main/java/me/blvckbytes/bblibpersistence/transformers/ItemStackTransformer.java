package me.blvckbytes.bblibpersistence.transformers;

import me.blvckbytes.bblibpersistence.models.ItemStackModel;
import me.blvckbytes.bblibdi.AutoConstruct;
import me.blvckbytes.bblibdi.AutoInject;
import me.blvckbytes.bblibutil.logger.ILogger;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/21/2022

  Handles transforming bukkit itemstacks.

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
public class ItemStackTransformer implements IDataTransformer<ItemStackModel, ItemStack> {

  private final ILogger logger;

  public ItemStackTransformer(
    @AutoInject ILogger logger
  ) {
    this.logger = logger;
  }

  @Override
  public ItemStack revive(ItemStackModel data) {
    if (data == null)
      return null;

    try {
      byte[] bytes = Base64Coder.decodeLines(data.getBase64Item());
      ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

      ItemStack ret = (ItemStack) dataInput.readObject();

      dataInput.close();
      inputStream.close();
      return ret;
    } catch (Exception e) {
      logger.logError(e);
      return null;
    }
  }

  @Override
  public ItemStackModel replace(ItemStack data) {
    if (data == null)
      return null;

    String base64;

    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      dataOutput.writeObject(data);
      dataOutput.close();
      base64 = Base64Coder.encodeLines(outputStream.toByteArray());
      outputStream.close();
    } catch (Exception e) {
      logger.logError(e);
      base64 = "";
    }

    return new ItemStackModel(base64);
  }

  @Override
  public Class<ItemStackModel> getKnownClass() {
    return ItemStackModel.class;
  }

  @Override
  public Class<ItemStack> getForeignClass() {
    return ItemStack.class;
  }
}
