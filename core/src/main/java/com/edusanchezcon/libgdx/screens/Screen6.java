package com.edusanchezcon.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class Screen6 implements Screen {

	private SpriteBatch batch;
	private static final float WORLD_WIDTH = 10;
	private static final float WORLD_HEIGHT = 10;

	private Camera cam;
	private Viewport viewport;

	TextureAtlas atlas;
	private float stateTime;
	private Animation<TextureRegion> animation;

	private Vector2 pos = new Vector2();
	private float velocity = 3f;

	public Screen6(){
		batch = new SpriteBatch();
		viewport = new ExtendViewport(WORLD_WIDTH/2, WORLD_HEIGHT/2);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		cam = viewport.getCamera();

		atlas = new TextureAtlas("Punk_Run-packed/pack.atlas");

		Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");

		animation = new Animation<>(0.1f, run, Animation.PlayMode.LOOP);
		stateTime = 0;
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

		update();

		stateTime += delta;

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(animation.getKeyFrame(stateTime), pos.x,pos.y, 2, 2);
		batch.end();
	}

	private void update(){
		float delta = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			pos.x += velocity * delta;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			pos.x -= velocity * delta;
		}

		pos.x = MathUtils.clamp(pos.x, 0, WORLD_WIDTH - 2);
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
		atlas.dispose();
		batch.dispose();
	}
}