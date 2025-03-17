package kvtodev.demo;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.tommyettinger.textra.Styles;
import com.github.tommyettinger.textra.TypingLabel;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;

public class InnerWorld implements Screen, InputProcessor {
    final Stage world = new Stage(new ExtendViewport(10, 10));
    final Stage ui = new Stage(new FitViewport(1920, 1080));
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

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters += "鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认,乃是世界自由、正义与和平的基础资本家可干死我了头上尖你";
        parameter.size = 30;
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/noto.ttf"));
        BitmapFont font = freeTypeFontGenerator.generateFont(parameter);

        freeTypeFontGenerator.dispose();

        final Texture dialog_image = new Texture("libgdx.png");
        Alice = new VisImage(dialog_image);
        Alice.setSize(500, 1080);
        Alice.setPosition(0, 0);

        Bob = new VisImage(dialog_image);
        Bob.setSize(500, 1080);
        Bob.setPosition(1920 - 500, 0);

        // Create some text with tokens
        String text = "[GREEN]Hello,{WAIT} world!"
            + "[ORANGE]{SLOWER} Did you know orange {SLIDE}鉴于对人类家庭所有成员的固有尊严及其平等的和{FADE}不移的权利的承认,乃是 is my{JOLT} favorite color?";

// Create a TypingLabel instance with your custom text
        label = new TypingLabel("", new Styles.LabelStyle(font, Color.RED));
        label.setPosition(300, 300);
        label.setTextSpeed(0.2f);

// Add the actor to any widget like you normally would


        this.game = game;

        final JsonReader jsonReader = new JsonReader();
        JsonValue scenes = jsonReader.parse(Gdx.files.internal("lines.json"));
        JsonValue scene = scenes.get(0);
        lines = scene.get("lines");
        Texture bck = new Texture(scene.get("background").asString());
        Image image_bck = new Image(bck);
        image_bck.setSize(1920, 500);

        table = new VisTable();
        table.setPosition(600,600);
        table.add(label);

        ui.addActor(Alice);
        ui.addActor(Bob);
        ui.addActor(table);

        Alice.setVisible(false);
        Bob.setVisible(false);
        Gdx.input.setInputProcessor(new InputMultiplexer(this, ui));

        in.setAlpha(255);
        in.setDuration(1);
        out.setAlpha(1);
        out.setDuration(1);
    }

    VisImage Alice;

    VisTable table;
    VisImage Bob;

    @Override
    public void show() {

    }

    TypingLabel label;
    int i = 0;

    AlphaAction in = new AlphaAction();
    AlphaAction out = new AlphaAction();

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);
        world.act(delta);
        world.draw();

        if (dialog_pass && i < lines.size) {
            Alice.setVisible(true);
            Bob.setVisible(true);

            table.addAction(Actions.parallel(Actions.moveBy(100,50,2f, Interpolation.fade),Actions.alpha(0.2f,8f,Interpolation.fade),Actions.scaleBy(0.8f,0.8f,2f,Interpolation.fade)));
            dialog_pass = false;
            JsonValue line = lines.get(i);
            label.restart(line.getString("text"));
            i++;
            String role = line.getString("role");
            String pos = line.getString("pos");
            if (role.equals("Alice")) {
//                Alice.setVisible(true);
//                Bob.setVisible(false);
//                Alice.clearActions();
                Alice.addAction(in);
//                if (pos.equals("left")){
//                    Alice.setPosition(0,0);
//                }else if (pos.equals("right")){
//                    Alice.setPosition(1920 - 500,0);
//                }
//                Bob.clearActions();

                Bob.addAction(out);
            }
            if (role.equals("Bob")) {
                Bob.setVisible(true);
                Alice.setVisible(false);
//                Bob.clearActions();
//                Bob.addAction(in);
//                if (pos.equals("left")){
//                    Bob.setPosition(0,0);
//                }else if (pos.equals("right")){
//                    Bob.setPosition(1920 - 500,0);
//                }
//                Alice.clearActions();
//                Alice.addAction(out);
            }
        }

        ui.act(delta);
        ui.draw();
    }

    static class OutAction extends MoveToAction {
        @Override
        public boolean act(float delta) {
            return false;
        }
    }

    JsonValue lines;

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

    boolean dialog_pass = false;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            dialog_pass = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
