package com.gotpb.tubespbokelompok7.views.components;

import com.gotpb.tubespbokelompok7.model.Testimoni;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TestimoniComponent extends VBox {
    public TestimoniComponent(Testimoni testimoni) {
        Label textLabel = new Label(testimoni.getText());

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(textLabel);
    }
}
