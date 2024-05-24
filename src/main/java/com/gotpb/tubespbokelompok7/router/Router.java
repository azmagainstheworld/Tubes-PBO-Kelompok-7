package com.gotpb.tubespbokelompok7.router;

import com.gotpb.tubespbokelompok7.views.View;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Router {
    private static Router INSTANCE;

    private static Stage STAGE;
    private ArrayList<View> viewList;
    public String focusedViewName;
    public View focusedView;

    public Router(Stage stage) {
        STAGE = stage;
        this.viewList = new ArrayList<>();
    }

    public static Router getInstance(Stage stage) {
        if (INSTANCE == null) {
            INSTANCE = new Router(stage);
        }

        return INSTANCE;
    }

    public static Router getInstance() {
        return INSTANCE;
    }

    public void setup(View[] views) {
        this.viewList.addAll(List.of(views));
    }

    public void open(String name) {
        this.focusedViewName = name;

        for (View view: this.viewList) {
            if (view.name.equals(name)) {
                this.focusedView = view;
                this.openView(view);

                return;
            }
        }
    }

    public boolean openView(View view) {
        return view.open(STAGE);
    }

    public ArrayList<View> getViewList() {
        return viewList;
    }

    public void setViewList(ArrayList<View> viewList) {
        this.viewList = viewList;
    }

    public void addView(View view) {
        this.viewList.add(view);
    }
}
