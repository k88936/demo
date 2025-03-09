package kvtodev.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.widget.VisImage;

public class InnerWorld implements Screen {
    final Stage world = new Stage(new ExtendViewport(10, 10));
    final Stage ui = new Stage(new FitViewport(1920, 1080));
    final Texture dialog_image = new Texture("dialog_window.png");
    private final Game game;

    public InnerWorld(Game game) {
//        VisWindow win = new VisWindow("Inner World");
//        win.add(new VisTextButton("Back")).row();
//        win.left();
//        win.centerWindow();
//        ui.addActor(win);

//        Skin skin = new Skin();
//        VisTable visTable = new VisTable();
//
//
//        Actor actor = new Actor() {
//            @Override
//            public void draw(Batch batch, float parentAlpha) {
//                batch.draw(dialog_image, 0, 0, 1920, 1080 * 0.3f);
//            }
//        };
//        ui.addActor(actor);

        VisImage visImage = new VisImage(dialog_image);
        visImage.setSize(1920, 1080 * 0.3f);
        visImage.setPosition(0, 0);
        ui.addActor(visImage);

        this.game = game;


        Gdx.input.setInputProcessor(ui);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);
        world.act(delta);
        world.draw();
        ui.act(delta);
        ui.draw();
    }

    @Override
    public void resize(int width, int height) {

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
