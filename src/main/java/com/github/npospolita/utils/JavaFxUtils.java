package com.github.npospolita.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * INFO
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class JavaFxUtils {

    final private ConfigurableApplicationContext springContext;

    @Autowired
    public JavaFxUtils(ConfigurableApplicationContext springContext) {
        this.springContext = springContext;
    }

    public Pane getPane(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
        fxmlLoader.setControllerFactory(springContext::getBean);
        return fxmlLoader.load();
    }
}
