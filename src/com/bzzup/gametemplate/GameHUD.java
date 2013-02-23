package com.bzzup.gametemplate;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

public class GameHUD {
	protected HUD hud;
	public Text hudTimerTextLabel;
	public Text hudTimerTextValue;
	public Text hudMoneyTextValue;

	public Text introTextValue;
	public ParallelEntityModifier introTextModifier;
	public ParallelEntityModifier hudIntroModifier;

	public GameHUD() {

		hud = new HUD();

//		hudTimerTextLabel = new Text(0, 0, ResourceManager.getInstance().splashBitmapFont, "TIME ROCKIN' THE CHOPPA:", new TextOptions(HorizontalAlign.CENTER), ResourceManager.getInstance()
//				.getActivityReference().getVertexBufferObjectManager());
//		hudTimerTextValue = new Text(550, 0, ResourceManager.getInstance().mDroidFont, "0.0", 10, new TextOptions(HorizontalAlign.CENTER), ResourceManager.getInstance().getActivityReference()
//				.getVertexBufferObjectManager());
		hudMoneyTextValue = new Text(700, 0, ResourceManager.getInstance().mDroidFont, "Money: 0", 20, new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getActivityReference()
				.getVertexBufferObjectManager());

//		hud.attachChild(hudTimerTextLabel);
//		hud.attachChild(hudTimerTextValue);
		hud.attachChild(hudMoneyTextValue);

		// intro text & modifier
		introTextValue = new Text(500, 350, ResourceManager.getInstance().mDroidFont, "-GET READY-", new TextOptions(HorizontalAlign.CENTER), ResourceManager.getInstance().getActivityReference()
				.getVertexBufferObjectManager());
		introTextModifier = new ParallelEntityModifier(new AlphaModifier(2, 1, 0), new ScaleModifier(2, 2, 0.5f));
		introTextValue.registerEntityModifier(introTextModifier);
		attachButtons();
	}
	
	private void attachButtons() {
		AnimatedSprite hud_button_pause = new AnimatedSprite(30, 10, ResourceManager.getInstance().hud_pause_button, ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (this.getCurrentTileIndex() == 0) {
						this.setCurrentTileIndex(1);
					} else {
						this.setCurrentTileIndex(0);
					}
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		hud_button_pause.setScale(1.5f);
		hud.attachChild(hud_button_pause);
		GameScene.getInstance().registerTouchArea(hud_button_pause);
	}

	public HUD getHud() {
		return this.hud;
	}

	public Text getIntroText() {
		return this.introTextValue;
	}

	public ParallelEntityModifier getIntroTextModifier() {
		return this.introTextModifier;
	}

	public void updateMoney(long count) {
		hudMoneyTextValue.setText("Money: " + count);
	}
}
