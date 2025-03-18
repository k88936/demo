package kvtodev.demo;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Null;

public class VectorAction extends TemporalAction {
    private Vector4 start;
    private Vector4 end;
    private Vector4 value = new Vector4();

    /** Creates a FloatAction that transitions from 0 to 1. */
    public VectorAction() {
        start = new Vector4(0, 0, 0, 0);
        end = new Vector4(1, 1, 1, 1);
    }

    /** Creates a FloatAction that transitions from start to end. */
    public VectorAction(Vector4 start, Vector4 end) {
        this.start = new Vector4(start);
        this.end = new Vector4(end);
    }


    /** Creates a FloatAction that transitions from start to end. */
    public VectorAction(Vector4 start, Vector4 end, float duration) {
        super(duration);
        this.start = new Vector4(start);
        this.end = new Vector4(end);
    }

    /** Creates a FloatAction that transitions from start to end. */
    public VectorAction(Vector4 start, Vector4 end, float duration, @Null Interpolation interpolation) {
        super(duration, interpolation);
        this.start = new Vector4(start);
        this.end = new Vector4(end);

    }


    protected void begin() {
        value.set(start);
    }

    protected void update(float percent) {
        if (percent == 0) value.set(start);
        else if (percent == 1) value.set(end);
        else value.set(start).lerp(end, percent);
    }

    /** Gets the current float value. */
    public Vector4 getValue() {
        return value;
    }

    /** Sets the current float value. */
    public void setValue(Vector4 value) {
        this.value = value;
    }

    public Vector4 getStart() {
        return start;
    }

    /** Sets the value to transition from. */
    public void setStart(Vector4 start) {
        this.start = start;
    }

    public void setStart(float x, float y, float z, float w) {
        start.set(x, y, z, w);
    }

    public void setStart(float x, float y, float z) {
        start.set(x, y, z, 0);
    }

    public void setStart(float x, float y) {
        start.set(x, y, 0, 0);
    }

    public Vector4 getEnd() {
        return end;
    }

    /** Sets the value to transition to. */
    public void setEnd(Vector4 end) {
        this.end = end;
    }

    public void setEnd(float x, float y, float z, float w) {
        end.set(x, y, z, w);
    }

    public void setEnd(float x, float y, float z) {
        end.set(x, y, z, 0);
    }

    public void setEnd(float x, float y) {
        end.set(x, y, 0, 0);
    }
}
