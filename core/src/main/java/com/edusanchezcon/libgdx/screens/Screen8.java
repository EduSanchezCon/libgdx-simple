package com.edusanchezcon.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class Screen8 implements Screen {

	private SpriteBatch batch;
	private static final float WORLD_WIDTH = 10;
	private static final float WORLD_HEIGHT = 10;

	private Camera cam;
	private Viewport viewport;

	private TextureAtlas atlas;
	private float stateTime;
	private Sprite sprite;
	private Animation<TextureRegion> animation;

	private Vector2 pos = new Vector2();
	private Vector2 destination = new Vector2(0,0);

	public Screen8(){
		batch = new SpriteBatch();
		viewport = new ExtendViewport(WORLD_WIDTH/2, WORLD_HEIGHT/2);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		cam = viewport.getCamera();

		atlas = new TextureAtlas("Punk_Run-packed/pack.atlas");

		Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");

		sprite = new Sprite();
		sprite.setSize(2, 2);
		sprite.setPosition(0,0);
		animation = new Animation<>(0.1f, run, Animation.PlayMode.LOOP);
		stateTime = 0;

		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button){
				Vector3 coords = cam.unproject(new Vector3(screenX, screenY, 0f));
				destination.set(coords.x, coords.y);
				return true;
			}
		});
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
		sprite.setRegion(animation.getKeyFrame(stateTime));

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		sprite.draw(batch);
//		batch.draw(animation.getKeyFrame(stateTime), pos.x,pos.y, 2, 2);
		batch.end();
	}

	private void update(){
		float delta = Gdx.graphics.getDeltaTime();
		pos.set(sprite.getX(), sprite.getY());
		if (pos.dst(destination) > 0.1f){
			pos.x += (destination.x - pos.x) * delta;
			pos.y += (destination.y - pos.y) * delta;
		}

		sprite.setX(MathUtils.clamp(pos.x, 0, WORLD_WIDTH - 2));
		sprite.setY(MathUtils.clamp(pos.y, 0, WORLD_HEIGHT - 2));
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