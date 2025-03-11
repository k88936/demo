package kvtodev.easing;

public class LoopAnimator extends LinkedAnimator {
    LoopAnimator() {
        super();
    }
    public LoopAnimator(float startValue){
        super(startValue);
    }

    public LoopAnimator add(Animator animator) {
        appendToSeq(animator);
        return this;
    }
    public LoopAnimator add(Animator... animators) {
        for (Animator animator : animators) {
            appendToSeq(animator);
        }
        return this;
    }
    @Override
    public float value(float time) {
        while (time >= duration) {
            time -= duration;
            i = 0;
            t = 0;
        }
        return lookup(time);
    }

}
