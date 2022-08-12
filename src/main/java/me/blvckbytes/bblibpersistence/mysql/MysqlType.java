package me.blvckbytes.bblibpersistence.mysql;

import com.google.common.primitives.Primitives;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.blvckbytes.bblibpersistence.query.EqualityOperation;

import java.util.Date;
import java.util.Optional;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/05/2022

  Maps a data-type in a MySQL database to it's corresponding Java type and provides
  flags like whether the type is of fixed length and what operations it supports.

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
@AllArgsConstructor
public enum MysqlType {
  UUID(
    new String[] {
      "BINARY(16)"
    },
    new Class[] {
      java.util.UUID.class
    },
    new Class[]{},
    true,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.CONT,
      EqualityOperation.CONT_IC,
      EqualityOperation.STARTS,
      EqualityOperation.STARTS_IC,
      EqualityOperation.ENDS,
      EqualityOperation.ENDS_IC
    }
  ),

  TEXT(
    new String[] {
      "LONGTEXT"
    },
    new Class[] {
      String.class
    },
    new Class[]{},
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.EQ_IC,
      EqualityOperation.NEQ_IC,
      EqualityOperation.CONT,
      EqualityOperation.CONT_IC,
      EqualityOperation.STARTS,
      EqualityOperation.STARTS_IC,
      EqualityOperation.ENDS,
      EqualityOperation.ENDS_IC
    }
  ),

  VARCHAR(
    new String[] {
    "VARCHAR(255)"
    },
    new Class[] {
      String.class, Enum.class
    },
    new Class[]{},
    true,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.EQ_IC,
      EqualityOperation.NEQ_IC,
      EqualityOperation.CONT,
      EqualityOperation.CONT_IC,
      EqualityOperation.STARTS,
      EqualityOperation.STARTS_IC,
      EqualityOperation.ENDS,
      EqualityOperation.ENDS_IC
    }
  ),

  BOOLEAN(
    new String[] {
      "BOOL", "tinyint(1)"
    },
    new Class[] {
      boolean.class, Boolean.class
    },
    new Class[] {},
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ
    }
  ),

  LONG(
    new String[] {
      "BIGINT"
    },
    new Class[] {
      long.class, Long.class
    },
    new Class[] {
      int.class, Integer.class,
      float.class, Float.class,
      long.class, Long.class,
      double.class, Double.class
    },
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.GT,
      EqualityOperation.GTE,
      EqualityOperation.LT,
      EqualityOperation.LTE
    }
  ),

  INTEGER(
    new String[] {
      "INT", "int"
    },
    new Class[] {
      int.class, Integer.class
    },
    new Class[] {
      int.class, Integer.class,
      float.class, Float.class,
      double.class, Double.class,
      long.class, Long.class
    },
    true,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.GT,
      EqualityOperation.GTE,
      EqualityOperation.LT,
      EqualityOperation.LTE
    }
  ),

  DOUBLE(
    new String[] {
      "DOUBLE"
    },
    new Class[] {
      double.class, Double.class
    },
    new Class[] {
      int.class, Integer.class,
      float.class, Float.class,
      double.class, Double.class,
      long.class, Long.class
    },
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.GT,
      EqualityOperation.GTE,
      EqualityOperation.LT,
      EqualityOperation.LTE
    }
  ),

  FLOAT(
    new String[] {
      "FLOAT"
    },
    new Class[] {
      float.class, Float.class
    },
    new Class[] {
      int.class, Integer.class,
      float.class, Float.class,
      double.class, Double.class,
      long.class, Long.class
    },
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.GT,
      EqualityOperation.GTE,
      EqualityOperation.LT,
      EqualityOperation.LTE
    }
  ),

  DATETIME(
    new String[] {
      "DATETIME"
    },
    new Class[] {
      Date.class
    },
    new Class[] {
      long.class, Long.class,
      int.class, Integer.class
    },
    false,
    new EqualityOperation[] {
      EqualityOperation.EQ,
      EqualityOperation.NEQ,
      EqualityOperation.GT,
      EqualityOperation.GTE,
      EqualityOperation.LT,
      EqualityOperation.LTE
    }
  )
  ;

  private final String[] queryTypes;

  @Getter
  private final Class<?>[] javaEquivalents;

  @Getter
  private final Class<?>[] javaEquivalentsForFieldOps;

  private final boolean hasLength;
  private final EqualityOperation[] supportedOps;

  @Override
  public String toString() {
    // Just choose the first type, as multiple types are only used for "aliases"
    return queryTypes[0];
  }

  /**
   * Checks whether the provided sql type string (as spit out by the DESC command)
   * matches the current type
   * @param sqlTypeStr SQL type string
   * @return True on match, false otherwise
   */
  public boolean matchesSQLTypeStr(String sqlTypeStr) {
    sqlTypeStr = sqlTypeStr.toLowerCase().replace(" ", "");

    for (String type : queryTypes) {
      if (sqlTypeStr.equalsIgnoreCase(type))
        return true;
    }

    return false;
  }

  /**
   * Converts a java type to a MySQL type
   * @param javaType Java type to convert
   * @param lengthRequired Whether this type has to have a specified length
   * @return Optional MySQL type
   */
  public static Optional<MysqlType> fromJavaType(Class<?> javaType, boolean lengthRequired) {
    if (Primitives.isWrapperType(javaType))
      javaType = Primitives.unwrap(javaType);

    for (MysqlType type : MysqlType.values()) {
      for (Class<?> javaEq : type.javaEquivalents) {
        if (javaEq.isAssignableFrom(javaType) && (!lengthRequired || type.hasLength))
          return Optional.of(type);
      }
    }
    return Optional.empty();
  }

  /**
   * Check if this type supports a given equality operation
   * @param op Equality operation to check
   */
  public boolean supportsOp(EqualityOperation op) {
    for (EqualityOperation supOp : supportedOps) {
      if (supOp == op)
        return true;
    }
    return false;
  }
}
