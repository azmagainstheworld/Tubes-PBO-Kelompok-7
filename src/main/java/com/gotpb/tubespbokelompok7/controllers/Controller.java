package com.gotpb.tubespbokelompok7.controllers;

import com.gotpb.tubespbokelompok7.router.Router;

public class Controller {
    public Router router;

    public Controller() {
        this.router = Router.getInstance();
    }
}
