/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.command.tool;

import com.boydti.fawe.config.Caption;
import com.sk89q.worldedit.LocalConfiguration;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.extension.platform.Platform;
import com.sk89q.worldedit.util.Location;

public enum NavigationWand implements DoubleActionTraceTool {
  INSTANCE;

  @Override
  public boolean actSecondary(Platform server, LocalConfiguration config, Player player, LocalSession session) {
      if (!player.hasPermission("worldedit.navigation.jumpto.tool")) {
          return false;
      }
      final int maxDist = config.navigationWandMaxDistance;
      if (maxDist <= 0) {
          return false;
      }
      Location pos = player.getSolidBlockTrace(maxDist);
      if (pos != null) {
          player.findFreePosition(pos);
      } else {
          player.print(Caption.of("worldedit.jumpto.none"));
      }
      return true;
  }

    @Override
    public boolean actPrimary(Platform server, LocalConfiguration config, Player player, LocalSession session) {
        if (!player.hasPermission("worldedit.navigation.thru.tool")) {
            return false;
        }
        final int maxDist = config.navigationWandMaxDistance;
        if (maxDist <= 0) {
            return false;
        }

        if (!player.passThroughForwardWall(Math.max(1, maxDist - 10))) {
            player.print(Caption.of("worldedit.thru.obstructed"));
        }
        return true;
    }

    @Override
    public boolean canUse(Actor actor) {
        return actor.hasPermission("worldedit.navigation.jumpto.tool"); // check should be here
    }
}
