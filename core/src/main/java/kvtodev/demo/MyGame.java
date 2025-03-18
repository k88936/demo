package kvtodev.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGame extends Game {

    static  public MyGame instance;
    static public MyGame getInstance() {
        return instance;
    }

    Resources resources;

    BitmapFont font1;

    @Override
    public void create() {
        instance = this;

        resources = new Resources();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters += Gdx.files.internal("charset.txt").readString();
        parameter.size = 30;
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/noto.ttf"));
        font1 = freeTypeFontGenerator.generateFont(parameter);
        freeTypeFontGenerator.dispose();

        VisUI.load();
        setScreen(new DemoStart());

    }

    @Override
    public void dispose() {
    }
}
