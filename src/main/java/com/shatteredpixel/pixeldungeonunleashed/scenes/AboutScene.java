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

import android.content.Intent;
import android.net.Uri;

import com.shatteredpixel.pixeldungeonunleashed.ShatteredPixelDungeon;
import com.shatteredpixel.pixeldungeonunleashed.ui.ExitButton;
import com.watabou.input.Touchscreen.Touch;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.TouchArea;
import com.shatteredpixel.pixeldungeonunleashed.effects.Flare;
import com.shatteredpixel.pixeldungeonunleashed.ui.Archs;
import com.shatteredpixel.pixeldungeonunleashed.ui.Icons;
import com.shatteredpixel.pixeldungeonunleashed.ui.Window;

public class AboutScene extends PixelScene {

	private static final String TTL_TITLE = "Unleashed Pixel Dungeon";

	private static final String TXT_UNLSH =
			"Code & Graphics: David Mitchell\n\n" +
			"Based on the Shattered Pixel Dungeon by Evan\n\n" +
			"and the Original Pixel Dungeon by Watabou.";

	private static final String LNK_UNLSH = "father-natures.blogspot.com";
	private static final String LNK_SHPX = "ShatteredPixel.com";


	private static final String TTL_WATA = "Original Pixel Dungeon";

	private static final String TXT_WATA =
			"Code & Graphics: Watabou\n" +
			"Music: Cube_Code\n\n" +
			"Visit Watabou for more info:";
	
	private static final String LNK_WATA = "pixeldungeon.watabou.ru";
	
	@Override
	public void create() {
		super.create();

		final float colWidth = Camera.main.width / (ShatteredPixelDungeon.landscape() ? 2 : 1);
		final float colTop = (Camera.main.height / 2) - (ShatteredPixelDungeon.landscape() ? 30 : 90);
		final float wataOffset = ShatteredPixelDungeon.landscape() ? colWidth : 0;

		Image unlsh = Icons.UNLEASHED.get();
		unlsh.x = align( (colWidth - unlsh.width()) / 2 );
		unlsh.y = align( colTop );
		add( unlsh );

		new Flare( 7, 64 ).color( 0xFFFF66, true ).show( unlsh, 0 ).angularSpeed = +20;

		BitmapTextMultiline shpxtitle = createMultiline( TTL_TITLE, 8 );
		shpxtitle.maxWidth = (int) Math.min( colWidth, 120 );
		shpxtitle.measure();
		shpxtitle.hardlight( Window.SHPX_COLOR );
		add( shpxtitle );

		shpxtitle.x = align( (colWidth - shpxtitle.width()) / 2 );
		shpxtitle.y = align( unlsh.y + unlsh.height + 5 );

		BitmapTextMultiline shpxtext = createMultiline( TXT_UNLSH, 8 );
		shpxtext.maxWidth = shpxtitle.maxWidth;
		shpxtext.measure();
		add( shpxtext );

		shpxtext.x = align( (colWidth - shpxtext.width()) / 2 );
		shpxtext.y = align( shpxtitle.y + shpxtitle.height() + 12 );

		BitmapTextMultiline shpxlink = createMultiline( LNK_UNLSH, 8 );
		shpxlink.maxWidth = shpxtitle.maxWidth;
		shpxlink.measure();
		shpxlink.hardlight( Window.SHPX_COLOR );
		add( shpxlink );

		shpxlink.x = shpxtext.x;
		shpxlink.y = shpxtext.y + shpxtext.height();

		TouchArea shpxhotArea = new TouchArea( shpxlink ) {
			@Override
			protected void onClick( Touch touch ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://father-natures.blogspot.com") );
				Game.instance.startActivity( intent );
				ShatteredPixelDungeon.switchNoFade(TitleScene.class);
			}
		};
		add( shpxhotArea );

		Image wata = Icons.WATA.get();
		wata.x = align( wataOffset + (colWidth - wata.width()) / 2 );
		wata.y = align( ShatteredPixelDungeon.landscape() ?
						colTop:
						shpxlink.y + wata.height + 20);
		add( wata );

		new Flare( 7, 64 ).color( 0x112233, true ).show( wata, 0 ).angularSpeed = +20;

		BitmapTextMultiline wataTitle = createMultiline( TTL_WATA, 8 );
		wataTitle.maxWidth = (int) Math.min( colWidth, 120 );
		wataTitle.measure();
		wataTitle.hardlight(Window.TITLE_COLOR);
		add( wataTitle );

		wataTitle.x = align( wataOffset + (colWidth - wataTitle.width()) / 2 );
		wataTitle.y = align( wata.y + wata.height + 11 );

		BitmapTextMultiline wataText = createMultiline( TXT_WATA, 8 );
		wataText.maxWidth = wataTitle.maxWidth;
		wataText.measure();
		add( wataText );
		
		wataText.x = align( wataOffset + (colWidth - wataText.width()) / 2 );
		wataText.y = align( wataTitle.y + wataTitle.height() + 12 );
		
		BitmapTextMultiline wataLink = createMultiline( LNK_WATA, 8 );
		wataLink.maxWidth = wataTitle.maxWidth;
		wataLink.measure();
		wataLink.hardlight(Window.TITLE_COLOR);
		add(wataLink);
		
		wataLink.x = wataText.x;
		wataLink.y = wataText.y + wataText.height();
		
		TouchArea hotArea = new TouchArea( wataLink ) {
			@Override
			protected void onClick( Touch touch ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://" + LNK_WATA ) );
				Game.instance.startActivity( intent );
				ShatteredPixelDungeon.switchNoFade(TitleScene.class);
			}
		};
		add( hotArea );

		
		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchNoFade(TitleScene.class);
	}
}
