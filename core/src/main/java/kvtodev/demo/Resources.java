package kvtodev.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.box2d.Box2dPlus;
import com.badlogic.gdx.box2d.structs.b2Hull;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Arrays;

public class Resources {
    protected final JsonReader jsonReader = new JsonReader();
    protected final ObjectMap<String, b2Hull> polygonVerticesData = new ObjectMap<>();
    private final AssetManager assetManager;
    private final TextureAtlas atlas;
    private final JsonValue dimensions;

    public Resources() {
        this.assetManager = new AssetManager();
        assetManager.load("packed/assets.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        this.atlas = assetManager.get("packed/assets.atlas", TextureAtlas.class);
        dimensions = jsonReader.parse(Gdx.files.internal("dimensions.json"));
//
//        JsonValue polygonsVertices = jsonReader.parse(Gdx.files.internal("packed/polygons.json"));
//        JsonValue polygons = polygonsVertices.get("polygons");
//        for (JsonValue polygon : polygons) {
//
//            polygonVerticesData.put(polygon.name(), Box2dPlus.b2ComputeHull(polygon.asFloatArray()));
//        }
    }

    //    public AssetManager getAssetManager() {
//        return assetManager;
//    }
//
    public TextureRegion getTexureRegion(String name) {

        return atlas.findRegion(name);
    }

    //
//    public TextureRegion getTexureRegion(String name) {
//        return new TextureRegion(new Texture(Gdx.files.internal(name)));
//    }
//
//    public b2Hull getHull(String name) {
//        return polygonVerticesData.get(name);
//    }
    public Table configureTable(Table table, String name) {
        JsonValue dimension = dimensions.get(name);
        table.setSize(dimension.getFloat("width"), dimension.getFloat("height"));
        table.setPosition(dimension.getFloat("x"), dimension.getFloat("y"));
        table.setBackground(new TextureRegionDrawable(atlas.findRegion(name)));
        return table;
    }

    public Image configureImage(Image image, String name) {
        image.setDrawable(new TextureRegionDrawable(getTexureRegion(name)));
        JsonValue dimension = dimensions.get(name);
        image.setSize(dimension.getFloat("width"), dimension.getFloat("height"));
        image.setPosition(dimension.getFloat("x"), dimension.getFloat("y"));
        return image;
    }

    public AnimationImage configureAnimationImage(AnimationImage image, String name) {
        JsonValue dimension = dimensions.get(name);
        Animation<TextureRegionDrawable> animation = new Animation<>(dimension.getFloat("speed"), Arrays.stream(atlas.findRegions(name).items).map(TextureRegionDrawable::new).toArray(TextureRegionDrawable[]::new));
        image.setSize(dimension.getFloat("width"), dimension.getFloat("height"));
        image.setPosition(dimension.getFloat("x"), dimension.getFloat("y"));
        image.setAnimation(animation);
        return image;
    }
}
