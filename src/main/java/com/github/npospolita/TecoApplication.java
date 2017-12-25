package com.github.npospolita;

import com.github.npospolita.model.User;
import com.github.npospolita.model.repo.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(rootNode));
		primaryStage.show();
	}

	@Bean
	public MediaPlayer musicPlayer() {
		Media media = new Media(getClass().getResource("/music/tecoSoundtrack.mp3").toString()); //replace /Movies/test.mp3 with your file
		MediaPlayer player = new MediaPlayer(media);
		player.play();
		player.setVolume(0.0);
		return player;
	}

	@Bean
	public InitializingBean initializingBean(UserRepository userRepository) {
		return () -> {
			User user = new User();
			user.setLogin("tester");
			user.setPassword("tester");
			userRepository.save(user);
			User user1 = new User();
			user.setLogin("admin");
			user.setPassword("admin");
			userRepository.save(user);
			User user2 = new User();
			user.setLogin("user");
			user.setPassword("user");
			userRepository.save(user);
		};
	}

	@Bean
	public UserDetails currentUserDetails() {
		return new UserDetails();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
	}

}
