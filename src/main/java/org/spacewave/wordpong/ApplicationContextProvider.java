package org.spacewave.wordpong;

import org.spacewave.wordpong.game.GameComponent;
import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.spacewave.wordpong.menu.ConnectDialog;
import org.spacewave.wordpong.menu.HostCard;
import org.spacewave.wordpong.menu.MenuComponent;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
}
