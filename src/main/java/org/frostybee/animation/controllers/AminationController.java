package org.frostybee.animation.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.frostybee.animation.helpers.EaseInElasticInterpolator;
import org.frostybee.animation.helpers.EaseInOutBounceInterpolator;
import org.frostybee.animation.helpers.EaseOutBounceInterpolator;
import org.frostybee.animation.ui.CardView;

/**
 * A controller for managing the animation components.
 *
 * @author frostybee.
 */
public class AminationController {

    private final Map<String, Interpolator> interpolations = new LinkedHashMap<>();
    private final List<Animation> shapeTransitions = new ArrayList<>();
    private final List<Node> cardViews = new ArrayList<>();

    public AminationController() {
        loadInterpolationsList();
        createShapes();
    }

    /**
     * Loads instances of built-in and custom interpolation into a map whose
     * elements will be used later in the animations.
     */
    private void loadInterpolationsList() {
        // Load JavaFX built-in interpolators.        
        interpolations.put("LINEAR", Interpolator.LINEAR);
        interpolations.put("EASE_IN", Interpolator.EASE_IN);
        interpolations.put("EASE_OUT", Interpolator.EASE_OUT);
        interpolations.put("EASE_BOTH", Interpolator.EASE_BOTH);        
        // Load custom interpolators.
        interpolations.put("EASE_IN_ELASTIC", new EaseInElasticInterpolator());
        interpolations.put("EASE_OUT_BOUNCE", new EaseOutBounceInterpolator());
        interpolations.put("EASE_INOUT_BOUNCE", new EaseInOutBounceInterpolator());
    }

    private void createShapes() {
        // Position the new node 80 pixels below the previous one.
        double startingY = 20;
        for (Map.Entry<String, Interpolator> entry : interpolations.entrySet()) {
            String interpolationType = entry.getKey();
            Interpolator interpolation = entry.getValue();
            // Make a new card for the current type of interpolation.
            CardView cardNode = new CardView(interpolationType);
            startingY += 80;
            // Add the newly created card to the list of nodes that will be injected
            // into the main scene.
            cardViews.add(cardNode);
            // Create and configure a translate transition animation.
            shapeTransitions.add(
                    makeTanslateTransition(interpolation, cardNode)
            );
        }
    }

    /**
     * Makes a Transition-based JavaFX animation. The returned translate
     * transition object will be later added to a list of animations which will
     * be ran in parallel.
     *
     * @param interpolation The interpolation method to be used when animation
     * the specified node.
     * @param cardView The custom node to be animated.
     * @return 
     */
    private Animation makeTanslateTransition(Interpolator interpolation, VBox cardView) {
        // The X coordinate to which the specified node shoudl translate to. 
        double toXPosition = 500;
        TranslateTransition translateTranstion = new TranslateTransition(
                Duration.seconds(3),
                cardView);
        translateTranstion.setToX(toXPosition);
        translateTranstion.setInterpolator(interpolation);
        translateTranstion.setRate(2);
        return translateTranstion;
    }

    /**
     * Returns a list of card views (that is, the list of nodes) to be animated.
     *
     * @return The list of card views.
     */
    public List<Node> getCardViews() {
        return cardViews;
    }

    /**
     * Returns a list containing configured instances of TranslateTransition
     * animations. These instances will be ran in parallel.
     *
     * @return The list of translate transitions.
     */
    public List<Animation> getTranslateTransitions() {
        return shapeTransitions;
    }

}
