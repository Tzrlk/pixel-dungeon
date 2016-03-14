package com.shatteredpixel.pixeldungeonunleashed.actors.mobs;

import com.shatteredpixel.pixeldungeonunleashed.actors.Char;
import com.shatteredpixel.pixeldungeonunleashed.actors.buffs.Buff;
import com.shatteredpixel.pixeldungeonunleashed.actors.buffs.Poison;
import com.shatteredpixel.pixeldungeonunleashed.sprites.SlimeRedSprite;
import com.watabou.utils.Random;

public class SlimeRed  extends Mob {

    {
        name = "red slime";
        spriteClass = SlimeRedSprite.class;

        HP = HT = 15;
        defenseSkill = 5;
        atkSkill = 11;
        dmgRed = 4;
        dmgMin = 3;
        dmgMax = 6;

        EXP = 5;
        maxLvl = 11;

        mobType = MOBTYPE_DEBUFF;
    }

    @Override
    public int attackProc( Char enemy, int damage ) {

        if (Random.Int( 3 ) == 0) {
            Buff.affect(enemy, Poison.class).set(Random.Int(1, 3) * Poison.durationFactor(enemy));
        }

        return damage;
    }

    @Override
    public String description() {
        return
                "Slimes look like icky little piles of goo, but they can pack a bite." +
                        "The red slimes are poisonous in addition to other icky characteristics.";
    }
}
