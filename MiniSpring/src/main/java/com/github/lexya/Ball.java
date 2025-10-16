package com.github.lexya;

import com.github.lexya.annotations.Autowired;
import com.github.lexya.annotations.Component;
import com.github.lexya.minispring.InitializingBean;

@Component
public class Ball implements InitializingBean {
    double radius;
    double startSpeed;
    double endSpeed;

    @Autowired
    private Roller roller;
    public Ball() {

    }
    @Override
    public void afterPropertiesSet() {
        radius = 5;
        startSpeed = 15;
        endSpeed = 30;
    }

    
}
