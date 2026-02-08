package com.zipporah.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    Texture background;

    // character animations
    Texture spriteSheet;
    TextureRegion currFrame;
    Animation<TextureRegion> walk;
    Animation<TextureRegion> run;
    Animation<TextureRegion> jump;
    Animation<TextureRegion> idle;

    float time = 0;
    float x = 200;
    float y = 150;

    // camera
    FitViewport viewport;

    // sprites
    SpriteBatch batch;

    @Override
    public void create() {
        // characters
        batch = new SpriteBatch();

        // viewport (fixed)
        viewport = new FitViewport(1280, 720);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // background
        background = new Texture("Battleground2.png");

        // walk sprite sheet
        spriteSheet = new Texture("Walk.png");
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 128, 128);
        TextureRegion[] walkFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            walkFrames[i] = tmp[0][i];
        }
        walk = new Animation<>(0.1f, walkFrames);

    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your application here. The parameters represent the new window size.
        viewport.update(width, height, true);
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void render() {
        // Draw your application here.
        input();
        logic();
        draw();
    }

    private void input() {

    }

    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        time += Gdx.graphics.getDeltaTime();
        currFrame = walk.getKeyFrame(time, true);

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        batch.begin();

        // draw background
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // draw animated character
        batch.draw(currFrame, x, y, 250, 250);

        batch.end();

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
    public void dispose() {
        // Destroy application's resources here.
    }
}
