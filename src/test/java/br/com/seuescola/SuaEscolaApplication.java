package br.com.seuescola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "br.com.seuescola",
        "suaEscola.suaEscola"
})
public class SuaEscolaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuaEscolaApplication.class, args);
    }
}
