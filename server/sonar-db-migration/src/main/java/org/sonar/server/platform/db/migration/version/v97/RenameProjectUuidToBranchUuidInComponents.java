/*
 * SonarQube
 * Copyright (C) 2009-2022 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration.version.v97;

import java.sql.Connection;
import java.sql.SQLException;
import org.sonar.db.Database;
import org.sonar.db.DatabaseUtils;
import org.sonar.server.platform.db.migration.def.VarcharColumnDef;
import org.sonar.server.platform.db.migration.sql.RenameColumnsBuilder;
import org.sonar.server.platform.db.migration.step.DdlChange;

import static org.sonar.server.platform.db.migration.def.VarcharColumnDef.newVarcharColumnDefBuilder;

public class RenameProjectUuidToBranchUuidInComponents extends DdlChange {
  private static final String TABLE_NAME = "components";

  private static final VarcharColumnDef columnDefinition = newVarcharColumnDefBuilder()
    .setColumnName("branch_uuid")
    .setIsNullable(false)
    .setLimit(50)
    .build();

  public RenameProjectUuidToBranchUuidInComponents(Database db) {
    super(db);
  }

  @Override
  public void execute(Context context) throws SQLException {
    try (Connection connection = getDatabase().getDataSource().getConnection()) {
      if (!DatabaseUtils.tableColumnExists(connection, TABLE_NAME, "branch_uuid") && DatabaseUtils.tableColumnExists(connection, TABLE_NAME, "project_uuid")) {
        context.execute(new RenameColumnsBuilder(getDialect(), TABLE_NAME)
          .renameColumn("project_uuid", columnDefinition)
          .build());
      }
    }
  }
}
