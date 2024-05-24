package com.gotpb.tubespbokelompok7.views.components;

import com.gotpb.tubespbokelompok7.HelloApplication;
import com.gotpb.tubespbokelompok7.model.Video;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VideoItemComponent extends HBox {
    public Video video;

    public VideoItemComponent(Video video) {
        this.video = video;

        Button thumbnailBtn = this.getThumbnailComponent();
        VBox descriptionComponent = this.getDescriptionComponent();

        HBox container = new HBox();
        container.getChildren().add(thumbnailBtn);
        container.getChildren().add(descriptionComponent);

        this.getChildren().add(container);
    }

    public Button getThumbnailComponent() {
        Image image = new Image(video.getThumbnailImageAsStream());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(imageView);

        return button;
    }

    public VBox getDescriptionComponent() {
        VBox container = new VBox();

        Label title = new Label(video.getJudul());
        title.setWrapText(true);
        title.setFont(Font.font(12));

//        Label description = new Label(video.getDeskripsi());
//        description.setWrapText(true);
//        description.setFont(Font.font(9));

        container.getChildren().add(title);
//        container.getChildren().add(description);

        return container;
    }
}
