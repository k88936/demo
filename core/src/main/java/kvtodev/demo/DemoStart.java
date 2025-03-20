package kvtodev.demo;

import com.aliasifkhan.hackLights.HackLightEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.*;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingLabel;

import static com.badlogic.gdx.Gdx.gl;


public class DemoStart extends InputAdapter implements Screen {

    Stage stage = new Stage(new FitViewport(1920, 1080)) {
        final HackLightEngine hackLightEngine = new HackLightEngine();

        {
            Image background = new Image();
            var game = MyGame.getInstance();
            game.resources.configure(background, "test_background");
            addActor(background);
            Image display = new Image() {
                final VfxManager vfxManager = new VfxManager(Pixmap.Format.RGBA8888);

                {
                    vfxManager.setBlendingEnabled(true);
                    vfxManager.addEffect(new OldTvEffect());
                    vfxManager.addEffect(new FilmGrainEffect());
                    vfxManager.addEffect(new CrtEffect(CrtEffect.LineStyle.HORIZONTAL_HARD, 1.3f, 0.5f));
                    vfxManager.addEffect(new WaterDistortionEffect(0.2f, 1f));
                    vfxManager.addEffect(new DarkenVignettingEffect(false));
                    vfxManager.addEffect(new ChromaticAberrationEffect(3));
                    addAction(Actions.forever(new TemporalAction(2f) {
                        @Override
                        protected void update(float percent) {
                            if (percent > 0.8f) {
                                vfxManager.update(1.5f);
                            } else {
                                vfxManager.update(0.005f);
                            }
                        }
                    }));
                }

                @Override
                public void draw(Batch batch, float parentAlpha) {
                    batch.end();
                    vfxManager.cleanUpBuffers();
                    vfxManager.beginInputCapture();
                    batch.begin();
                    super.draw(batch, parentAlpha);
                    batch.end();
                    vfxManager.endInputCapture();
                    vfxManager.applyEffects();
                    vfxManager.renderToScreen();
                    batch.begin();
                }
            };
            game.resources.configure(display, "test_display");
            addActor(display);
            Image girl = new Image();
            game.resources.configure(girl, "test_girl");
            addActor(girl);
            OrthographicCamera camera = (OrthographicCamera) getCamera();
            camera.position.set(Config.WIDTH / 4, Config.WIDTH / 4, 0);
            camera.zoom = 0.5f;
            addAction(
                Actions.sequence(
                    new DelayAction(25),
                    new VectorAction() {
                        {
                            setStart(Config.WIDTH / 4, Config.WIDTH / 4, 0.5f);
                            setEnd(Config.HALF_WIDTH, Config.HALF_HEIGHT, 1f);
                            setDuration(9f);
                            setInterpolation(Interpolation.pow2Out);
                        }

                        @Override
                        protected void update(float percent) {
                            super.update(percent);
                            Vector4 value = getValue();
                            camera.position.x = value.x;
                            camera.position.y = value.y;
                            camera.zoom = value.z;
                        }
                    }
                )
            );
        }
    };

    Stage stage0 = new Stage(new FitViewport(1920, 1080)) {
        final VfxManager vfxManager = new VfxManager(Pixmap.Format.RGBA8888);


        final DarkenVignettingEffect darkenVignettingEffect = new DarkenVignettingEffect(false);

        final Color clearColor = new Color(0.1f, 0.1f, 0.1f, 1f);

        private class MyFloatAction extends FloatAction {
            public MyFloatAction(float start, float end, float duration, Interpolation interpolation) {
                super(start, end, duration, interpolation);
            }

            @Override
            protected void update(float percent) {
                super.update(percent);
                darkenVignettingEffect.setIntensity(getValue());
            }
        }


        @Override
        public void draw() {
            vfxManager.cleanUpBuffers();
            vfxManager.beginInputCapture();
            ScreenUtils.clear(clearColor);
            super.draw();
            vfxManager.endInputCapture();
            vfxManager.applyEffects();
            vfxManager.renderToScreen();
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            vfxManager.update(delta);
        }

        {
            var game = MyGame.getInstance();
            vfxManager.setBlendingEnabled(true);
            vfxManager.addEffect(new OldTvEffect());
            vfxManager.addEffect(new FilmGrainEffect());
            vfxManager.addEffect(new CrtEffect(CrtEffect.LineStyle.HORIZONTAL_HARD, 1.3f, 0.5f));
            vfxManager.addEffect(new WaterDistortionEffect(0.2f, 1f));
            vfxManager.addEffect(darkenVignettingEffect);

            TypingLabel helloWorld = new TypingLabel();
            helloWorld.setFont(new Font(game.font1));
            helloWorld.setText("人类并非单一的颜色{WAIT=3} 而是{RAINBOW}色彩斑斓的{WAIT=3} \n {RESET}真正的颜色 自己的颜色{WAIT=3} \n {HIGHLIGHT}{JOLT}没有人知道是什么{WAIT=3} \n {RESET}{GRADIENT}只要色彩斑斓就好 请活出色彩斑斓的人生");
            helloWorld.setTextSpeed(0.01f);
            helloWorld.setSize(800, 200);
            helloWorld.setPosition(1920f / 2 - 400, 700);
            addAction(Actions.sequence(
                new MyFloatAction(0, 1, 5f, Interpolation.linear),
                new MyFloatAction(1, 4, 7f, Interpolation.linear),
                new MyFloatAction(4, 20, 8f, Interpolation.bounceIn),
                Actions.removeActor(helloWorld),
                new ParallelAction(
                    new ColorAction() {
                        {
                            setColor(clearColor);
                            setDuration(5);
                            setEndColor(Color.CLEAR);
                        }
                    }
                    ,
                    new MyFloatAction(20, 0, 5f, Interpolation.bounceOut)
                )
            ));
            addAction(Actions.forever(new TemporalAction(2f) {
                @Override
                protected void update(float percent) {
                    if (percent > 0.8f) {
                        vfxManager.update(1.5f);
                    } else {
                        vfxManager.update(0.005f);
                    }
                }
            }));
            addActor(helloWorld);
        }


    };

    DemoStart() {
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(this);
    }

    VfxManager vfxManager = new VfxManager(Pixmap.Format.RGBA8888);

    @Override
    public void render(float delta) {

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
//
//        stage0.act(delta);
//        stage0.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage0.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
