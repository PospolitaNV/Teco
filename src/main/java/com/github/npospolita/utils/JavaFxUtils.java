package com.github.npospolita.utils;

import com.github.npospolita.TecoApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * INFO
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class JavaFxUtils {

    private static final Logger logger = LoggerFactory.getLogger(JavaFxUtils.class);

    final private ConfigurableApplicationContext springContext;

    private final Map<String, Stage> stages = new HashMap<>();

    @Autowired
    public JavaFxUtils(ConfigurableApplicationContext springContext) {
        this.springContext = springContext;
    }

    public Pane getPane(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
        fxmlLoader.setControllerFactory(springContext::getBean);
        return fxmlLoader.load();
    }

    public void proceedToScene(String sceneName) {
        Stage stage = TecoApplication.primaryStage;
        stage.setTitle(sceneName.toUpperCase());
        Pane myPane = null;
        try {
            myPane = this.getPane("/fxml/" + sceneName + ".fxml");
        } catch (IOException e) {
            logger.error("FUCK!", e);
        }
        Scene scene = new Scene(myPane);
        stage.setScene(scene);

        stage.show();
    }

    public void createNewStageWithScene(String sceneName) {
        Stage stage = new Stage();
        stage.setTitle(sceneName.toUpperCase());
        Pane myPane = null;
        try {
            myPane = this.getPane("/fxml/" + sceneName + ".fxml");
        } catch (IOException e) {
            logger.error("FUCK!", e);
        }
        Scene scene = new Scene(myPane);
        stage.setScene(scene);

        stages.put(sceneName, stage);

        stage.show();
    }

    public void closeStage(String sceneName) {
        stages.get(sceneName).close();
        stages.remove(sceneName);
    }
}
