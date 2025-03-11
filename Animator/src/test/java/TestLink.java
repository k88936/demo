import kvtodev.easing.LinkedAnimator;
import kvtodev.easing.linear.Linear;
import org.junit.jupiter.api.Test;

public class TestLink {
    @Test
    public void single() {
        LinkedAnimator l = new LinkedAnimator()
            .add(new Linear(1, 1));
        assert l.value(0) == 0f;
        assert l.value(0.5f) == 0.5f;
        assert l.value(1) == 1f;
        assert l.value(1.5f) == 1f;
    }

    @Test
    public void start() {
        LinkedAnimator l = new LinkedAnimator(1)
            .add(new Linear(1, 1));
        assert l.value(0) == 1f;
        assert l.value(0.5f) == 1f;
        assert l.value(1) == 1f;
    }

    @Test
    public void doubleLink() {
        LinkedAnimator l = new LinkedAnimator()
            .add(new Linear(1, 1))
            .add(new Linear(1, 2));
        assert l.value(0) == 0f;
        assert l.value(0.5f) == 0.5f;
        assert l.value(1) == 1f;
        assert l.value(1.5f) == 1.5f;
        assert l.value(2f) == 2f;
        assert l.value(2.5f) == 2f;
    }

    @Test
    public void backAndForth() {
        LinkedAnimator l = new LinkedAnimator()
            .add(new Linear(1, 1))
            .add(new Linear(1, 2))
            .add(new Linear(1, 3))
            .add(new Linear(1, 4));
        assert l.value(0) == 0f;
        assert l.value(0.5f) == 0.5f;
        assert l.value(1) == 1f;
        assert l.value(1.5f) == 1.5f;

        assert l.value(3.5f) == 3.5f;

        assert l.value(2.5f) == 2.5f;
        assert l.value(2) == 2f;
        assert l.value(1.5f) == 1.5f;
        assert l.value(1) == 1f;

    }

    @Test
    public void differentDuration() {
        LinkedAnimator l = new LinkedAnimator()
            .add(new Linear(1, 1))
            .add(new Linear(2, 3))
            .add(new Linear(3, 6))
            .add(new Linear(4, 10));

        assert l.value(0) == 0f;
        assert l.value(0.5f) == 0.5f;
        assert l.value(1) == 1f;
        assert l.value(1.5f) == 1.5f;
        assert l.value(2) == 2f;
        assert l.value(2.5f) == 2.5f;
        assert l.value(3) == 3f;
        assert l.value(3.5f) == 3.5f;
        assert l.value(4) == 4f;
        assert l.value(4.5f) == 4.5f;
        assert l.value(5) == 5f;
        assert l.value(5.5f) == 5.5f;
        assert l.value(6) == 6f;
        assert l.value(6.5f) == 6.5f;
        assert l.value(6) == 6f;
        assert l.value(5.5f) == 5.5f;
        assert l.value(5) == 5f;
        assert l.value(4.5f) == 4.5f;
        assert l.value(4) == 4f;
        assert l.value(3.5f) == 3.5f;
        assert l.value(3) == 3f;
        assert l.value(2.5f) == 2.5f;
        assert l.value(2) == 2f;
        assert l.value(1.5f) == 1.5f;
        assert l.value(1) == 1f;
        assert l.value(0.5f) == 0.5f;
        assert l.value(0) == 0f;


    }
}
