package org.frostybee.animation.helpers;

import javafx.animation.Interpolator;

/**
 * A custom interpolator that implements an ease-out bounce effect.
 * 
 * @author frostybee
 */
public class EaseOutBounceInterpolator extends Interpolator {

    /**
     * Easing functions specify the rate of change of a parameter over time.
     *
     * @see: https://easings.net/#easeOutBounce
     * @param time represents the absolute progress of the animation in the bounds
     * of 0 (beginning of the animation) and 1 (end of animation).
     * @return the interpolated value
     */
    @Override
    protected double curve(double time) {
        return EaseOutBounceInterpolator.easeOutBounce(time);
    }

    public static double easeOutBounce(double time) {
        final double n1 = 7.5625;
        final double d1 = 2.75;

        if (time < 1 / d1) {
            return n1 * time * time;
        } else if (time < 2 / d1) {
            return n1 * (time -= 1.5 / d1) * time + 0.75;
        } else if (time < 2.5 / d1) {
            return n1 * (time -= 2.25 / d1) * time + 0.9375;
        } else {
            return n1 * (time -= 2.625 / d1) * time + 0.984375;
        }
    }

    @Override
    public String toString() {
        return "EaseOutBounceInterpolator";
    }
}
