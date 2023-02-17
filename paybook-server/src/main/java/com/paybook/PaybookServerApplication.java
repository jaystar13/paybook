package com.paybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        PaybookServerApplication.class,
        Jsr310JpaConverters.class
})
public class PaybookServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaybookServerApplication.class, args);
    }
}
