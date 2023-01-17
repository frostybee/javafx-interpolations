package org.frostybee.animation.controllers;

import java.util.List;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.util.Duration;

/**
 * This is the FXML controller of the main window.
 *
 * @author frostybee.
 */
public class MainAppController {

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private Pane animationPane;
    @FXML
    private Slider sliderAnimSpeed;
    private AminationController animationController = new AminationController();
    private ParallelTransition animation = new ParallelTransition();
    private double currentRate = 2;
    // The initial starting Y corrdinate of the first node to be animated.
    double startingY = 20;

    @FXML
    void initialize() {
        configureControlButtons();
        //
        initSpeedSlider();
        loadVisualComponents();
        initAnimation();
        // Disable the pause and the stop buttons.
        disableControlButtons(false, true, true);
    }

    private void configureControlButtons() {
        // Attach click event handlers the animation control buttons.
        btnPlay.setOnAction(this::handlePlayAnimation);
        btnStop.setOnAction(this::handleStopAnimation);
        btnPause.setOnAction(this::handlePauseAnimation);
    }

    /**
     * Makes and injects the the card views into the animation panel.
     */
    private void loadVisualComponents() {
        // Add the newly created card to the animation pane.    
        List<Node> cardViews = animationController.getCardViews();
        cardViews.forEach((node) -> {
            // Push down each node by the specified number of pixel 
            // below the previous one in the list.
            node.setTranslateY(startingY);
            startingY += 80;
        });
        animationPane.getChildren().addAll(cardViews);
    }

    /**
     * Configures a parallel animation that runs a set of translate transitions
     * animating previously created card views.
     */
    private void initAnimation() {
        // Add the new translate transition to the list.        
        animation.getChildren().addAll(animationController.getTranslateTransitions());
        animation.setCycleCount(animation.INDEFINITE);
        animation.setAutoReverse(true);
    }

    private void handlePlayAnimation(ActionEvent event) {
        System.out.println("Playing...");
        animation.play();
        disableControlButtons(true, false, false);
    }

    private void handleStopAnimation(ActionEvent event) {
        System.out.println("Stopping...");
        animation.jumpTo(Duration.ZERO);
        animation.stop();
        disableControlButtons(false, true, true);
    }

    private void handlePauseAnimation(ActionEvent event) {
        System.out.println("Pausing...");
        animation.pause();
        disableControlButtons(false, true, false);
    }

    private void disableControlButtons(boolean play, boolean pause, boolean stop) {
        btnPlay.setDisable(play);
        btnPause.setDisable(pause);
        btnStop.setDisable(stop);
    }

    public void stopAnimation() {
        if (animation != null) {
            animation.stop();
        }
    }

    /**
     * Adjusts the animation speed based on the current value of the slider.
     */
    private void initSpeedSlider() {
        sliderAnimSpeed.setShowTickLabels(true);
        sliderAnimSpeed.setShowTickMarks(true);
        // Add a value changed listener to the slider of the animation speed.
        sliderAnimSpeed.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    currentRate = newValue.doubleValue();
                    // Update the running rate of the animation. 
                    animation.setRate(currentRate);
                });
    }
}
