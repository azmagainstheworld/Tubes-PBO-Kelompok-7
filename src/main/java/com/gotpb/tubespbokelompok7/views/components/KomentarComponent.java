package com.gotpb.tubespbokelompok7.views.components;

import com.gotpb.tubespbokelompok7.model.Komentar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class KomentarComponent extends VBox {
    public KomentarComponent(Komentar komentar) {
        Label textLabel = new Label(komentar.getText());

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(textLabel);
    }
}
