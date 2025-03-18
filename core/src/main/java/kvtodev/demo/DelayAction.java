package kvtodev.demo;

import com.badlogic.gdx.scenes.scene2d.Action;

public class DelayAction extends Action {

    private float duration, time;
    private boolean complete;

    DelayAction(float duration) {
        this.duration = duration;
    }

    @Override
    public boolean act(float delta) {
        if(complete) return true;
        if (time >= duration) {
            complete = true;
            onEnd();
            return true;
        }
        time += delta;
        return false;
    }

    void setDuration(float duration) {
        this.duration = duration;
    }

    boolean isComplete() {
        return  complete;
    }
    void onEnd(){

    }


    @Override
    public void restart() {
        time = 0;
    }

    @Override
    public void reset() {
        super.reset();
    }
}
