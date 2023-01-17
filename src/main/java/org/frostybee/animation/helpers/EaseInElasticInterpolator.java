package org.frostybee.animation.helpers;

import javafx.animation.Interpolator;

/**
 * A custom interpolator that implements an ease-in elastic effect.
 *
 * @author frostybee
 */
public class EaseInElasticInterpolator extends Interpolator {

    /**
     * Easing functions specify the rate of change of a parameter over time.
     *
     * @see: https://easings.net/#easeInElastic
     * @param time represents the absolute progress of the animation in the
     * bounds of 0 (beginning of the animation) and 1 (end of animation).
     * @return the interpolated value
     */
    @Override
    protected double curve(double time) {
        double c4 = (2 * Math.PI) / 3;
        //return Math.abs(0.5 - t) * 2;
        return time == 0
                ? 0
                : time == 1
                        ? 1
                        : -Math.pow(2, 10 * time - 10) * Math.sin((time * 10 - 10.75) * c4);
    }

    @Override
    public String toString() {
        return "EaseInElasticInterpolator";
    }
}
