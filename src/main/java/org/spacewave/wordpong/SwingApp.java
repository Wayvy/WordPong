package org.spacewave.wordpong;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;

public class SwingApp {

    public static void main(String[] args) {
        var ctx = new SpringApplicationBuilder(GameFrame.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(GameFrame.class);
            ex.setVisible(true);
            ex.SetMenuFrame();
        });
    }
}
