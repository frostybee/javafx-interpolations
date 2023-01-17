package org.frostybee.animation.helpers;

import javafx.animation.Interpolator;

/**
 * A custom interpolator that implements an ease-in-out bounce effect.
 *
 * @author frostybee
 */
public class EaseInOutBounceInterpolator extends Interpolator {

    /**
     * Easing functions specify the rate of change of a parameter over time.
     *
     * @see: https://easings.net/#easeInOutBounce
     * @param t represents the absolute progress of the animation in the bounds
     * of 0 (beginning of the animation) and 1 (end of animation).
     * @return the interpolated value
     */
    @Override
    protected double curve(double t) {
        return t < 0.5
                ? (1 - EaseOutBounceInterpolator.easeOutBounce(1 - 2 * t)) / 2
                : (1 + EaseOutBounceInterpolator.easeOutBounce(2 * t - 1)) / 2;
    }

    @Override
    public String toString() {
        return "EaseInOutBounceInterpolator";
    }
}
