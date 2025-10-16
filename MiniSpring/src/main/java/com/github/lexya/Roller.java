package com.github.lexya;

import com.github.lexya.annotations.Component;
import com.github.lexya.minispring.InitializingBean;

@Component
public class Roller implements InitializingBean {
    double a;
    public Roller() {

    }

    @Override
    public void afterPropertiesSet(){
        a = 2.5;
    }

    public double rollSpeedy(double speed, int t){
        return speed  + (a * t * t)/2;
    }
}
