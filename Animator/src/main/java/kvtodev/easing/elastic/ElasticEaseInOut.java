/*
 *
 *  * The MIT License (MIT)
 *  *
 *  * Copyright (c) 2014 daimajia
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package kvtodev.easing.elastic;

import kvtodev.easing.Animator;

public class ElasticEaseInOut extends Animator {
    public ElasticEaseInOut(float duration, float startValue, float endValue) {
        super(duration, startValue, endValue);
    }
    public ElasticEaseInOut(float duration, float endValue) {
        super(duration, endValue);
    }
    @Override
    public float value(float time) {
        if (time ==0) return startValue;  if ((time /= duration /2)==2) return startValue + deltaValue;
        float p= duration *(.3f*1.5f);
        float a= deltaValue;
        float s=p/4;
        if (time < 1) return -.5f*(a*(float)Math.pow(2,10*(time -=1)) * (float)Math.sin( (time * duration -s)*(2*(float)Math.PI)/p )) + startValue;
        return a*(float)Math.pow(2,-10*(time -=1)) * (float)Math.sin( (time * duration -s)*(2*(float)Math.PI)/p )*.5f + deltaValue + startValue;
    }
}
