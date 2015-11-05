/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2015 Evan Debenham
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
package com.shatteredpixel.pixeldungeonunleashed.actors.hero;

import com.shatteredpixel.pixeldungeonunleashed.Assets;
import com.shatteredpixel.pixeldungeonunleashed.Badges;
import com.shatteredpixel.pixeldungeonunleashed.Challenges;
import com.shatteredpixel.pixeldungeonunleashed.Dungeon;
import com.shatteredpixel.pixeldungeonunleashed.ShatteredPixelDungeon;
import com.shatteredpixel.pixeldungeonunleashed.items.Generator;
import com.shatteredpixel.pixeldungeonunleashed.items.TomeOfMastery;
import com.shatteredpixel.pixeldungeonunleashed.items.armor.ClothArmor;
import com.shatteredpixel.pixeldungeonunleashed.items.artifacts.CloakOfShadows;
import com.shatteredpixel.pixeldungeonunleashed.items.food.Food;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfExperience;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfFrost;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfHealing;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfInvisibility;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfMight;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfMindVision;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfSlowness;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfSpeed;
import com.shatteredpixel.pixeldungeonunleashed.items.potions.PotionOfStrength;
import com.shatteredpixel.pixeldungeonunleashed.items.rings.Ring;
import com.shatteredpixel.pixeldungeonunleashed.items.rings.RingOfHaste;
import com.shatteredpixel.pixeldungeonunleashed.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.pixeldungeonunleashed.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.pixeldungeonunleashed.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.Weapon;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.melee.MagesStaff;
import com.shatteredpixel.pixeldungeonunleashed.items.wands.WandOfMagicMissile;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.melee.Dagger;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.melee.ShortSword;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.missiles.Dart;
import com.shatteredpixel.pixeldungeonunleashed.items.weapon.missiles.Boomerang;
import com.watabou.utils.Bundle;

public enum HeroClass {

	WARRIOR( "warrior" ), MAGE( "mage" ), ROGUE( "rogue" ), HUNTRESS( "huntress" );
	
	private String title;
	
	HeroClass( String title ) {
		this.title = title;
	}
	
	public static final String[] WAR_PERKS = {
		"The Warrior starts with 11 points of Strength.",
		"The Warrior starts with a unique short sword. This sword can be later \"reforged\" to upgrade another melee weapon.",
		"The Warrior is less proficient with missile weapons.",
		"Any piece of food restores some health when eaten.",
		"Potions of Strength are identified from the beginning.",
	};
	
	public static final String[] MAG_PERKS = {
		"The Mage starts with a unique Staff, which can be imbued with the properties of a wand.",
		"The Mage's staff can be used as a melee weapon or a more powerful wand.",
		"The Mage partially identifies wands after using them.",
		"When eaten, any piece of food restores 1 charge for all wands in the inventory.",
		"Scrolls of Upgrade are identified from the beginning."
	};
	
	public static final String[] ROG_PERKS = {
		"The Rogue starts with a unique Cloak of Shadows.",
		"The Rogue identifies a type of a ring on equipping it.",
		"The Rogue is proficient with light armor, dodging better with excess strength.",
		"The Rogue is more proficient in detecting hidden doors and traps.",
		"The Rogue can go without food longer.",
		"Scrolls of Magic Mapping are identified from the beginning."
	};
	
	public static final String[] HUN_PERKS = {
		"The Huntress starts with 15 points of Health and a unique upgradeable boomerang.",
		"The Huntress is proficient with missile weapons, getting bonus damage from excess strength.",
		"The Huntress is able to recover a single used missile weapon from each enemy.",
		"The Huntress gains more health from dewdrops.",
		"The Huntress senses neighbouring monsters even if they are hidden behind obstacles.",
		"Potions of Mind Vision are identified from the beginning."
	};

	public void initHero( Hero hero ) {

		hero.heroClass = this;

		initCommon( hero );

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;
		}

		if (Badges.isUnlocked( masteryBadge() )) {
			new TomeOfMastery().collect();
		}

		//hero.updateAwareness();
	}

	private static void initCommon( Hero hero ) {
		if ((!Dungeon.isChallenged(Challenges.NO_ARMOR)) &&
				((Dungeon.difficultyLevel <= Dungeon.DIFF_HARD) || (Dungeon.difficultyLevel == Dungeon.DIFF_ENDLESS)))
			(hero.belongings.armor = new ClothArmor()).identify();

		if ((!Dungeon.isChallenged(Challenges.NO_FOOD)) &&
				((Dungeon.difficultyLevel <= Dungeon.DIFF_NORM) || (Dungeon.difficultyLevel == Dungeon.DIFF_ENDLESS)))
			new Food().identify().collect();

		if ((!Dungeon.isChallenged(Challenges.NO_FOOD)) && (Dungeon.difficultyLevel <= Dungeon.DIFF_EASY)) {
			for (int i = 0; i < 3; i++) {
				new Food().identify().collect();
				new ScrollOfIdentify().identify().collect();
			}
		}
		if (Dungeon.difficultyLevel == Dungeon.DIFF_TEST) {
			testHero(hero);
		}
	}

	public static void testHero(Hero hero) {
		hero.HT = 80;
		hero.HP = 80;
		// things we only want a few of..
		Ring ring1 = (Ring) Generator.random(Generator.Category.RING);
		ring1.collect();
		Generator.random(Generator.Category.WAND).collect();
		for (int i = 0; i < 3; i++) {
			new PotionOfSpeed().collect();
			new PotionOfSlowness().collect();
			new PotionOfInvisibility().collect();
			new ScrollOfMagicMapping().identify().collect();
			new PotionOfMight().collect();
		}

		// things we want a bunch of...
		for (int i = 0; i < 6; i++) {
			new Food().collect();
			new ScrollOfIdentify().identify().collect();
			new PotionOfHealing().identify().collect();
			new ScrollOfUpgrade().collect();
			new PotionOfFrost().identify().collect();
		}
		try {
			Weapon wpn2 = (Weapon) Generator.random(Generator.Category.WEAPON);
			wpn2.upgrade(true);
			wpn2.collect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new RingOfHaste().collect();


	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		hero.STR = hero.STR + 1;

		(hero.belongings.weapon = new ShortSword()).identify();
		Dart darts = new Dart( 8 );
		darts.identify().collect();

		Dungeon.quickslot.setSlot(0, darts);

		new PotionOfStrength().setKnown();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff = new MagesStaff(new WandOfMagicMissile());
		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfUpgrade().setKnown();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.misc1 = cloak).identify();
		hero.belongings.misc1.activate( hero );

		Dart darts = new Dart( 8 );
		darts.identify().collect();

		Dungeon.quickslot.setSlot(0, cloak);
		if (ShatteredPixelDungeon.quickSlots() > 1)
			Dungeon.quickslot.setSlot(1, darts);

		new ScrollOfMagicMapping().setKnown();
	}

	private static void initHuntress( Hero hero ) {

		hero.HP = (hero.HT -= 5);

		(hero.belongings.weapon = new Dagger()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();

		Dungeon.quickslot.setSlot(0, boomerang);

		new PotionOfMindVision().setKnown();
	}
	
	public String title() {
		return title;
	}
	
	public String spritesheet() {
		
		switch (this) {
		case WARRIOR:
			return Assets.WARRIOR;
		case MAGE:
			return Assets.MAGE;
		case ROGUE:
			return Assets.ROGUE;
		case HUNTRESS:
			return Assets.HUNTRESS;
		}
		
		return null;
	}
	
	public String[] perks() {
		
		switch (this) {
		case WARRIOR:
			return WAR_PERKS;
		case MAGE:
			return MAG_PERKS;
		case ROGUE:
			return ROG_PERKS;
		case HUNTRESS:
			return HUN_PERKS;
		}
		
		return null;
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
