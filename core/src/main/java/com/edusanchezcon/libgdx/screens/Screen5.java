package com.edusanchezcon.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class Screen5 implements Screen {

	private ShapeRenderer shapeRenderer;
	private static final float WORLD_WIDTH = 10;
	private static final float WORLD_HEIGHT = 10;

	private Camera cam;
	private Viewport viewport;

	public Screen5(){
		shapeRenderer = new ShapeRenderer();
		viewport = new ExtendViewport(WORLD_WIDTH/2, WORLD_HEIGHT/2);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		cam = viewport.getCamera();

		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(2, 2, 3, 1);
		shapeRenderer.end();
	}

	@Override
	public void show() {
		// Prepare your screen here.
	}

	@Override
	public void render(float delta) {
		// Draw your screen here. "delta" is the time since last render in seconds.
		Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(2, 2, 3, 1);
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		// Resize your screen here. The parameters represent the new window size.
		viewport.update(width, height, true);
	}

	@Override
	public void pause() {
		// Invoked when your application is paused.
	}

	@Override
	public void resume() {
		// Invoked when your application is resumed after pause.
	}

	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}

	@Override
	public void dispose() {
		// Destroy screen's assets here.
		shapeRenderer.dispose();
	}
}