package kvtodev.demo;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimationImage extends Image {
    protected float stateTime = 0;

    public Animation<TextureRegionDrawable> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegionDrawable> animation) {
        this.animation = animation;
    }

    Animation<TextureRegionDrawable> animation;

    @Override
    public void act(float delta) {
        super.act(delta);
        setDrawable(animation.getKeyFrame(stateTime, true));
        stateTime += delta;
    }
}
