package com.ferry.myifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyifoodApiApplication {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(MyifoodApiApplication.class, args);
    }

    @Override
    public final String toString() {
        return "MyifoodApiApplication []";
    }
}
