package kvtodev.easing;

import java.util.ArrayList;
import java.util.List;

public class LinkedAnimator extends Animator {
    public LinkedAnimator() {
        super(0, 0, 0);
    }

    public LinkedAnimator(float startValue) {
        super(0, startValue, startValue);
    }

    List<Animator> seq = new ArrayList<>();

    public void appendToSeq(Animator animator) {
        float end = animator.startValue + animator.deltaValue;
        animator.startValue = this.startValue + this.deltaValue;
        animator.deltaValue = end - animator.startValue;
        this.deltaValue = end - this.startValue;
        this.duration += animator.duration;
        seq.add(animator);
    }

    public LinkedAnimator add(Animator animator) {
        appendToSeq(animator);
        return this;
    }

    public LinkedAnimator add(Animator... animators) {
        for (Animator animator : animators) {
            appendToSeq(animator);
        }
        return this;
    }

    protected int i = 0;
    protected float t = 0;

    @Override
    public float value(float time) {
        if (time >= duration) {
            return this.startValue + this.deltaValue;
        }
        return lookup(time);
    }

    float lookup(float time) {
        while (true) {
            float delta = time - t;
            if (delta <= seq.get(i).duration) {
                return seq.get(i).value(delta);
            } else if (delta > 0) {
                t += seq.get(i).duration;
                i++;
            } else {
                t -= seq.get(i).duration;
                i--;
            }
        }
    }
}
