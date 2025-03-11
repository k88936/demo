package kvtodev.demo;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    @Override
    public void create() {
        VisUI.load();
        setScreen(new te(this));
    }

    @Override
    public void dispose() {
    }
}
