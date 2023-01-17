package org.frostybee.animation.ui;

import java.util.Random;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A class that represents a custom card view in which a rectangle and a label
 * are nested.
 *
 * @author frostybee.
 */
public class CardView extends VBox {

    private Random rand = new Random();
    // The text to be displayed below the card.
    private String cardText;
    // The starting X coordinate of node in the scene.
    double startingX = 80;

    public CardView(String inCardText) {
        cardText = inCardText;
        makeCardView();
    }

    /**
     * Makes a container in which a rectangular shape and a label are nested.
     *
     */
    private void makeCardView() {
        // Create a rectangle node that will be animated. 
        Rectangle rectangle = new Rectangle(50, 50, 50, 50);
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        // Fills the rectangle with a randomly generated color.
        rectangle.setFill(
                Color.rgb(
                        rand.nextInt(255),
                        rand.nextInt(255),
                        rand.nextInt(255)));
        rectangle.setStroke(
                Color.rgb(rand.nextInt(255),
                        rand.nextInt(255),
                        rand.nextInt(255)));        
        rectangle.setStrokeWidth(7);
        //-- Create a VBox to wrap the rectangle and its associated
        // interpolation label.        
        setSpacing(1);
        setTranslateX(startingX);
        // Create a label for the type of interpolation.
        Text interpolationText = new Text();
        interpolationText.setFont(
                Font.font("Verdana",
                        FontWeight.BOLD,
                        FontPosture.REGULAR,
                        10)
        );
        interpolationText.setText(cardText);
        // Add the shape and its label to the card.
        getChildren().addAll(rectangle, interpolationText);
    }
}
