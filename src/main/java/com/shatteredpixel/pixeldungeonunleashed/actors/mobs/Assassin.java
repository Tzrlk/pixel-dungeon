/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2015 Evan Debenham
 *
 * Unleashed Pixel Dungeon
 * Copyright (C) 2015 David Mitchell
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.pixeldungeonunleashed.actors.mobs;

import com.shatteredpixel.pixeldungeonunleashed.actors.Char;
import com.shatteredpixel.pixeldungeonunleashed.sprites.AssassinSprite;
import com.watabou.utils.Random;

public class Assassin extends Mob {

    {
        name = "assassin";
        spriteClass = AssassinSprite.class;

        HP = HT = 30;
        defenseSkill = 10;

        EXP = 6;
        maxLvl = 12;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 10);
    }

    @Override
    public int attackSkill( Char target ) {
        return 14;
    }

    @Override
    public int dr() {
        return 5;
    }

    @Override
    public String description() {
        return
                "A trainee Assassin, eager to make his first kill so he can complete his training.";
    }
}
