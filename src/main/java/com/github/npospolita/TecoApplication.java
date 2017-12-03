package com.github.npospolita;

import com.github.npospolita.model.User;
import com.github.npospolita.model.repo.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableJpaRepositories
public class TecoApplication extends Application {

	private Parent rootNode;
	public static Stage primaryStage;

	public static void main(String[] args) throws IOException {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
        ConfigurableApplicationContext springContext = SpringApplication.run(TecoApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        TecoApplication.primaryStage = primaryStage;
		primaryStage.setScene(new Scene(rootNode));
		primaryStage.show();
	}

	@Bean
	public InitializingBean initializingBean(UserRepository userRepository) {
		return () -> {
			User user = new User();
			user.setLogin("tester");
			user.setPassword("tester");
			userRepository.save(user);
		};
	}

	@Bean
	public User currentUser() {
		return new User();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
	}
}
