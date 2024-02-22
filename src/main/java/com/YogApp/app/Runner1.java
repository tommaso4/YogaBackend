package com.YogApp.app;

import com.YogApp.app.model.request.UserReq;
import com.YogApp.app.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner1 implements CommandLineRunner {

    @Autowired
    UserSvc userSvc = new UserSvc();
    @Override
    public void run(String... args) throws Exception {

    }
}
