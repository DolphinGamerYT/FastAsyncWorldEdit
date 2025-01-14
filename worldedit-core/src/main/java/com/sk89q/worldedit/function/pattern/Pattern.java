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

package com.sk89q.worldedit.function.pattern;

import com.boydti.fawe.beta.Filter;
import com.boydti.fawe.beta.implementation.filter.block.FilterBlock;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.internal.util.DeprecationUtil;
import com.sk89q.worldedit.internal.util.NonAbstractForCompatibility;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;

/**
 * Returns a {@link BaseBlock} for a given position.
 */
// FAWE Start
public interface Pattern extends Filter {

    /**
     * Return a {@link BaseBlock} for the given position.
     *
     * @param position the position
     * @return a block
     * @deprecated use {@link Pattern#applyBlock(BlockVector3)}
     */
    @Deprecated
    BaseBlock apply(BlockVector3 position);

    default boolean apply(Extent extent, BlockVector3 get, BlockVector3 set) throws WorldEditException {
        return set.setFullBlock(extent, apply(get));
    }

    @Override
    default void applyBlock(final FilterBlock block) {
        apply(block, block, block);
    }

    // FAWE End

    /**
     * Return a {@link BaseBlock} for the given position.
     *
     * @param position the position
     * @return a block
     * @apiNote This must be overridden by new subclasses. See {@link NonAbstractForCompatibility}
     *          for details
     */
    @NonAbstractForCompatibility(
            delegateName = "apply",
            delegateParams = { BlockVector3.class }
    )
    default BaseBlock applyBlock(BlockVector3 position) {
        DeprecationUtil.checkDelegatingOverride(getClass());

        return apply(position);
    }
}
