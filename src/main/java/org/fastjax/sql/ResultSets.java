/* Copyright (c) 2009 FastJAX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.fastjax.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility functions for operations pertaining to {@link ResultSet}.
 */
public final class ResultSets {
  /**
   * Releases the specified {@link ResultSet} object's database and JDBC
   * resources immediately instead of waiting for them to be automatically
   * released.
   * <p>
   * This method differs itself from {@link ResultSet#close()} by not throwing a
   * {@link SQLException} if a database access error occurs. If a database
   * access error occurs, a warning will be logged to the logger associated with
   * the {@code AuditConnection} class.
   *
   * @throws NullPointerException If {@code resultSet} is null.
   */
  public static void close(final ResultSet resultSet) {
    try {
      if (!resultSet.isClosed())
        resultSet.close();
    }
    catch (final SQLException e) {
    }
  }

  /**
   * Returns the number of rows for specified {@link ResultSet} objects that
   * have {@link ResultSet#getType()} not equal
   * {@link ResultSet#TYPE_FORWARD_ONLY}; otherwise this method returns
   * {@code -1}.
   *
   * @param resultSet The {@link ResultSet}.
   * @return The number of rows for specified {@link ResultSet} objects that
   *         have {@link ResultSet#getType()} not equal
   *         {@link ResultSet#TYPE_FORWARD_ONLY}; otherwise this method returns
   *         {@code -1}.
   * @throws SQLException If a database access error occurs or this method is
   *           called on a closed result set.
   * @throws NullPointerException If {@code resultSet} is null.
   */
  public static Integer getSize(final ResultSet resultSet) throws SQLException {
    if (resultSet.getType() <= ResultSet.TYPE_FORWARD_ONLY)
      return -1;

    resultSet.last();
    final int size = resultSet.getRow();
    resultSet.beforeFirst();
    return size;
  }

  private ResultSets() {
  }
}