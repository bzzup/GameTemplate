package com.bzzup.gametemplate;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GameScene extends Scene implements IOnSceneTouchListener, IUpdateHandler {

	
	public GameHUD gameHud;
	private boolean isReadyForClearScene = false;

//	private float dropSpeed = 2f; // sec
//	private final float touchArea = 150;

	private static GameScene gameScene;
	private PhysicsWorld mWorld;
	private Main mainActivity;
	private float mGravityX;
	private float mGravityY;

	public static GameScene getInstance() {
		return gameScene;
	}

	public GameScene(Main mainActivity) {
		this.mainActivity = mainActivity;
		gameScene = this;
		this.gameHud = new GameHUD();
		this.mWorld = new PhysicsWorld(new Vector2(0, 0), false);
		this.setBackground(new Background(Color.BLACK));

		this.setOnSceneTouchListener(this);
		setTouchAreaBindingOnActionDownEnabled(true);
		setTouchAreaBindingOnActionMoveEnabled(true);
		this.registerUpdateHandler(sceneUpdateHandler);
		this.registerUpdateHandler(this.mWorld);
//		enemyPool = new EnemyPool(ResourceManager.getInstance().baloonEnemy, this);
	}

	public GameHUD getHUD() {
		return this.gameHud;
	}

	private void createWalls() {
		final VertexBufferObjectManager vertexBufferObjectManager = ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager();
		final Rectangle ground = new Rectangle(0, ResolutionManager.getInstance().getCameraHeight() - 5, ResolutionManager.getInstance().getCameraWidth(), 5, vertexBufferObjectManager);
		final Rectangle roof = new Rectangle(0, 0, ResolutionManager.getInstance().getCameraWidth(), 5, vertexBufferObjectManager);
		final Rectangle left = new Rectangle(0, 0, 5, ResolutionManager.getInstance().getCameraHeight(), vertexBufferObjectManager);
		final Rectangle right_top = new Rectangle(ResolutionManager.getInstance().getCameraWidth() - 5, 0, 5, ResolutionManager.getInstance().getCameraHeight(), vertexBufferObjectManager);
		final Rectangle right_middle = new Rectangle(ResolutionManager.getInstance().getCameraWidth() - 75, 0, 5, ResolutionManager.getInstance().getCameraHeight(), vertexBufferObjectManager);
		
		PhysicsFactory.createBoxBody(this.mWorld, ground, BodyType.StaticBody, ResourceManager.getInstance().FIXTURE_DEF_WALL);
		PhysicsFactory.createBoxBody(this.mWorld, roof, BodyType.StaticBody, ResourceManager.getInstance().FIXTURE_DEF_WALL);
		PhysicsFactory.createBoxBody(this.mWorld, left, BodyType.StaticBody, ResourceManager.getInstance().FIXTURE_DEF_WALL);
		PhysicsFactory.createBoxBody(this.mWorld, right_top, BodyType.StaticBody, ResourceManager.getInstance().FIXTURE_DEF_WALL);

		this.attachChild(ground);
		this.attachChild(roof);
		this.attachChild(left);
		this.attachChild(right_top);

	}

	IUpdateHandler sceneUpdateHandler = new IUpdateHandler() {

		@Override
		public void reset() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdate(float pSecondsElapsed) {
//			updateHUD();
		}
	};
	

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		return false;
	}

	public void prepareSceneForIntro() {
		// add countdown text
		this.attachChild(gameHud.getIntroText());
		gameHud.getIntroTextModifier().reset();
	}

	public void cleanupAfterIntro() {
		// remove intro text
		this.detachChild(gameHud.getIntroText());
	}

	public void prepareSceneForPlay() {
		createWalls();
//		Road road = new Road();
		EngineOptionsManager.getInstance().getCamera().setHUD(gameHud.getHud());
	}

	public void cleanupAfterPlay() {
		EngineOptionsManager.getInstance().getCamera().setHUD(null);
//		this.detachChildren();
//		this.clearChildScene();
		isReadyForClearScene = true;
	}

	private void updateHUD() {
//		gameHud.updateMoney(PlayerDictionary.Money.getTotalMoney());
	}

}
