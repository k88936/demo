package kvtodev.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.*;
import com.kotcrab.vis.ui.widget.VisWindow;
import kvtodev.easing.Animator;
import kvtodev.easing.LinkedAnimator;
import kvtodev.easing.bounce.BounceEaseIn;
import kvtodev.easing.cubic.CubicEaseIn;
import kvtodev.easing.linear.Linear;

import javax.sound.sampled.Line;

public class te extends InputAdapter implements Screen {

    Stage ui = new Stage();

    private final Game game;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/noto.ttf"));
    BitmapFont font;
    VfxManager vfxManager;
    float time = 0;
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture image = new Texture("libgdx.png");

    @Override
    public void show() {

    }

    public te(Game game) {
        this.game = game;

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters += "鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认,乃是世界自由、正义与和平的基础";
        font = generator.generateFont(parameter);
        generator.dispose();

        Label test = new Label("test", new Label.LabelStyle(font, Color.WHITE));
        test.setPosition(1000, 500);
        VisWindow test1 = new VisWindow("test");
        test1.add(test).row();
        test1.centerWindow();
        ui.addActor(test1);
        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        vfxManager.setBlendingEnabled(true);
        vfxManager.addEffect(new OldTvEffect());
        vfxManager.addEffect(new FilmGrainEffect());
        vfxManager.addEffect(new CrtEffect(CrtEffect.LineStyle.HORIZONTAL_HARD, 1.3f, 0.5f));
        vfxManager.addEffect(new WaterDistortionEffect(1f, 1f));
        effect = new DarkenVignettingEffect(false);
        vfxManager.addEffect(effect);
//        vfxManager.addEffect(new ChromaticAberrationEffect(3));


//        ani = new LinkedAnimator(0).add(new BounceEaseOut(5, 3f)).add(new CircEaseOut(3, 4f)).add(new CubicEaseIn(3, 10));
        ani = new LinkedAnimator(1).add(new Linear(6, 3f)).add(new CubicEaseIn(5,  10f));

        Gdx.input.setInputProcessor(ui
        );
    }

    Animator ani;

    DarkenVignettingEffect effect;
    float intensity = 0;

    @Override
    public void render(float deltaTime) {
        float calculate = ani.getFloat(time);
        System.out.println(calculate);
        effect.setIntensity(calculate);
        time += Gdx.graphics.getDeltaTime();
        vfxManager.update(deltaTime);
//        if (time > 6) {
//            vfxManager.update(1.5f);
//        } else {
//            vfxManager.update(0.005f);
//        }

        ScreenUtils.clear(0.1f, 0.15f, 0.12f, 1f);
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);
        batch.begin();
        batch.draw(image, 600, 600);
        font.draw(batch, "鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认\n,乃是世界自由、正义与和平的基础", 600, 400);
        batch.end();
        ui.act();
        ui.draw();

        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();

    }

    @Override
    public void resize(int width, int height) {
        vfxManager.resize(width, height);
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
