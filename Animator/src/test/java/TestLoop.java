import kvtodev.easing.LoopAnimator;
import kvtodev.easing.linear.Linear;
import org.junit.jupiter.api.Test;

public class TestLoop {
    @Test
    public void loop(){
        LoopAnimator easingMethod = new LoopAnimator(0).add(new Linear(1,1));

        assert easingMethod.value(0.5f) == 0.5f;
        assert easingMethod.value(1.5f) == 0.5f;
        assert easingMethod.value(2.5f) == 0.5f;
        assert easingMethod.value(3.5f) == 0.5f;
    }
}
