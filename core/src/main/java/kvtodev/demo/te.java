package kvtodev.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.*;

public class te extends InputAdapter implements Screen {

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/noto.ttf"));
    BitmapFont font;
    VfxManager vfxManager;
    float time = 0;
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture image = new Texture("libgdx.png");

    @Override
    public void show() {

    }

    public te() {

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters += "鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认,乃是世界自由、正义与和平的基础";
        font = generator.generateFont(parameter);
        generator.dispose();

        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        vfxManager.setBlendingEnabled(true);
        vfxManager.addEffect(new OldTvEffect());
        vfxManager.addEffect(new FilmGrainEffect());
        vfxManager.addEffect(new WaterDistortionEffect(1f, 1f));
        vfxManager.addEffect(new VignettingEffect(false));
        vfxManager.addEffect(new ChromaticAberrationEffect(3));
    }

    @Override
    public void render(float deltaTime) {
        time += Gdx.graphics.getDeltaTime();
        if (time > 1) {
            vfxManager.update(1.5f);
            if (time > 1.2) {
                time = 0;
            }
        } else {
            vfxManager.update(0.005f);
        }

        ScreenUtils.clear(0.1f, 0.15f, 0.12f, 1f);
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        ScreenUtils.clear(0.1f,0.1f,0.1f,1f);
        batch.begin();
        batch.draw(image, 600, 600);
        font.draw(batch, "鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认\n,乃是世界自由、正义与和平的基础", 600, 400);
        batch.end();
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
