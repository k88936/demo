/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package kvtodev.easing.bounce;

import kvtodev.easing.Animator;

public class BounceEaseOut extends Animator {
    public BounceEaseOut(float duration, float startValue, float endValue) {
        super(duration, startValue, endValue);
    }

    public BounceEaseOut(float duration, float endValue) {
        super(duration, endValue);
    }
    @Override
    public float value(float time) {
        if ((time /= duration) < (1/2.75f)) {
            return deltaValue *(7.5625f* time * time) + startValue;
        } else if (time < (2/2.75f)) {
            return deltaValue *(7.5625f*(time -=(1.5f/2.75f))* time + .75f) + startValue;
        } else if (time < (2.5/2.75)) {
            return deltaValue *(7.5625f*(time -=(2.25f/2.75f))* time + .9375f) + startValue;
        } else {
            return deltaValue *(7.5625f*(time -=(2.625f/2.75f))* time + .984375f) + startValue;
        }
    }
}
