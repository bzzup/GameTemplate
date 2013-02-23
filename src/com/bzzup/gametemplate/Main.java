package com.bzzup.gametemplate;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.view.KeyEvent;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Main extends SimpleBaseGameActivity {
	public static Main instance;

	public int CAMERA_WIDTH;
	public int CAMERA_HEIGHT;
	// public BitmapTextureAtlas mTextureAtlas;
	// public TiledTextureRegion mCircleFaceTextureRegion;
	// public TiledTextureRegion baloonPlayer;
	// public TiledTextureRegion baloonEnemy;
	// public Scene mCurrentScene;
	// public Font mFont;
	// public Camera mCamera;
	// public int test;
	// Handler handler;

	public static final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		ResolutionManager.initializeResolutionManager(this);
		EngineOptionsManager.initializeEngineOptionsManager(getEngine());

		return EngineOptionsManager.getInstance().getEngineOptions();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return StateManager.getInstance().getCurrentState().onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreateResources() {
		ResourceManager.initializeResourceManager(this);

	}

	@Override
	protected Scene onCreateScene() {
		StateManager.getInstance();
		StateManager.initializeStateManager(this);
		return StateManager.getInstance().getCurrentState().getScene();

	}

}
