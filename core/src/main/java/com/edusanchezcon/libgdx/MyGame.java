package com.edusanchezcon.libgdx;

import com.badlogic.gdx.Game;
import com.edusanchezcon.libgdx.screens.Screen1;
import com.edusanchezcon.libgdx.screens.Screen2;
import com.edusanchezcon.libgdx.screens.Screen3;
import com.edusanchezcon.libgdx.screens.Screen4;
import com.edusanchezcon.libgdx.screens.Screen5;
import com.edusanchezcon.libgdx.screens.Screen6;
import com.edusanchezcon.libgdx.screens.Screen7;
import com.edusanchezcon.libgdx.screens.Screen9;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGame extends Game {
	@Override
	public void create() {
		setScreen(new Screen9());
	}
}