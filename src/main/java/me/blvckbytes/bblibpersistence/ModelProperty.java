package me.blvckbytes.bblibpersistence;

import me.blvckbytes.bblibpersistence.models.APersistentModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Marks a field of a model as one of it's properties to persist.

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
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelProperty {

  // Whether this property's value has to be unique accross
  // all models of the same type. When a single model has multiple
  // unique fields, they're combined into a single unique constraint
  boolean isUnique() default false;

  // Whether this field can be set to a NULL value
  boolean isNullable() default false;

  // Whether this field will be inherited
  boolean isInherited() default true;

  // Whether this field is inlined when using a transformer
  boolean isInlineable() default true;

  // What value to use when migrating this column to an existing data-structure
  MigrationDefault migrationDefault() default MigrationDefault.UNSPECIFIED;

  // Foreign key constraint target model, APersistentModel.class means none
  Class<? extends APersistentModel> foreignKey() default APersistentModel.class;

  // Action to take when the foreign key changes
  ForeignKeyAction foreignChanges() default ForeignKeyAction.RESTRICT;
}
