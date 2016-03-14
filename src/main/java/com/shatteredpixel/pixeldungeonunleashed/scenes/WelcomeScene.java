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
package com.shatteredpixel.pixeldungeonunleashed.scenes;

import com.shatteredpixel.pixeldungeonunleashed.Chrome;
import com.shatteredpixel.pixeldungeonunleashed.ShatteredPixelDungeon;
import com.shatteredpixel.pixeldungeonunleashed.ui.Archs;
import com.shatteredpixel.pixeldungeonunleashed.ui.RedButton;
import com.shatteredpixel.pixeldungeonunleashed.ui.ScrollPane;
import com.shatteredpixel.pixeldungeonunleashed.ui.Window;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.ui.Component;

//TODO: update this class with relevant info as new versions come out.
public class WelcomeScene extends PixelScene {

	private static final String TTL_Welcome = "Welcome!";

	private static final String TTL_Update = "v0.1.5: Content Push";

	private static final String TTL_Future = "Wait What?";

	private static final String TXT_Welcome =
			"Unleashed Pixel Dungeon is a rework/expansion of Shattered Pixel Dungeon.\n\n"+
			"The latest round of updates finishes up our Difficulty Level Fixes!\n"+
			"To change difficulty levels click on the Out-of-Game Settings (little gears) in"+
			"the top left corner, difficulty will also change when you load games.\n\n"+
			"Enjoy";

	private static final String TXT_Update =
            "v01.5: Content Push - It's all about the Mobs!\n"+
                    "- Red and Brown Slimes\n"+
                    "- Robot Spiders\n"+
                    "- Lost Souls\n"+
                    "- Squids in the Pool Rooms\n"+
                    "- The Mad Tinkerer, Minotaur and the Chaos Mage\n"+
                    "- tutorial mode fixes\n"+
                    "     - update tutorial verbiage\n"+
                    "     - better branding on load/save screen\n"+
                    "     - hard mode can only save after boss levels\n"+
                    "     - announce when important badges aren't earned\n"+
                    "- dew vial helps sate hunger\n"+
                    "- reduce hunger when descending stairs\n"+
                    "- remove seeds from drop tables on levels\n"+
                    "- reduce random crashes\n\n"+
			"v01.4: Difficulty Levels\n"+
			    "- click version (v01.4) for change history\n"+
				"- return to title scene after visiting links\n"+
				"- increase level-caps for items\n"+
				"- altar donation changes (simplified)\n"+
			    "- add difficulty levels\n"+
			    "     - badges, scores, display on new/load buttons\n"+
			    "     - hero - stats/equipment, leveling up, regeneration\n"+
			    "     - mobs - health, attack and defense skills\n"+
			    "     - number of mobs, number of items, drop tables\n"+
			    "     - item levels, upgrade failures, cursed status\n"+
			    "- tutorial level messages.\n"+
				"- bug fixes";
	private static final String TXT_Future =
			"It seems that your current saves are from a future version of Unleashed Pixel Dungeon!\n\n"+
			"Either you're messing around with older versions of the app, or something has gone buggy.\n\n"+
			"Regardless, tread with caution! Your saves may contain things which don't exist in this version, "+
			"this could cause some very weird errors to occur.";

	private static final String LNK = "https://play.google.com/store/apps/details?id=com.shatteredpixel.pixeldungeonunleashed";

	@Override
	public void create() {
		super.create();

		final int gameversion = ShatteredPixelDungeon.version();

		BitmapTextMultiline title;
		BitmapTextMultiline text;

		if (gameversion == 0) {

			text = createMultiline(TXT_Welcome, 8);
			title = createMultiline(TTL_Welcome, 16);

		} else if (gameversion <= Game.versionCode) {

			text = createMultiline(TXT_Update, 6 );
			title = createMultiline(TTL_Update, 9 );

		} else {

			text = createMultiline( TXT_Future, 8 );
			title = createMultiline( TTL_Future, 16 );

		}

		int w = Camera.main.width;
		int h = Camera.main.height;

		int pw = w - 10;
		int ph = h - 50;

		title.maxWidth = pw;
		title.measure();
		title.hardlight(Window.SHPX_COLOR);

		title.x = align( (w - title.width()) / 2 );
		title.y = align( 8 );
		add( title );

		NinePatch panel = Chrome.get(Chrome.Type.WINDOW);
		panel.size( pw, ph );
		panel.x = (w - pw) / 2;
		panel.y = (h - ph) / 2;
		add( panel );

		ScrollPane list = new ScrollPane( new Component() );
		add( list );
		list.setRect(
				panel.x + panel.marginLeft(),
				panel.y + panel.marginTop(),
				panel.innerWidth(),
				panel.innerHeight());
		list.scrollTo( 0, 0 );

		Component content = list.content();
		content.clear();

		text.maxWidth = (int) panel.innerWidth();
		text.measure();

		content.add(text);

		content.setSize( panel.innerWidth(), text.height() );

		RedButton okay = new RedButton("Okay!") {
			@Override
			protected void onClick() {
				ShatteredPixelDungeon.version(Game.versionCode);
				Game.switchScene(TitleScene.class);
			}
		};

		/*
		okay.setRect(text.x, text.y + text.height() + 5, 55, 18);
		add(okay);

		RedButton changes = new RedButton("Changes") {
			@Override
			protected void onClick() {
				parent.add(new WndChanges());
			}
		};

		changes.setRect(text.x + 65, text.y + text.height() + 5, 55, 18);
		add(changes);*/

		okay.setRect((w - pw) / 2, h - 22, pw, 18);
		add(okay);

		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		fadeIn();
	}
}


