package lesson33.spring;

import lesson33.BlackWhiteCameraRoll;
import lesson33.Camera;
import lesson33.CameraRoll;
import lesson33.ColorCameraRoll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {

    @Bean
    public CameraRoll colorCameraRoll() {
        return new ColorCameraRoll();
    }

    @Bean
    public CameraRoll bwCameraRoll() {
        return  new BlackWhiteCameraRoll();
    }

    @Bean
    public Camera camera(CameraRoll colorCameraRoll) {
        Camera camera = new Camera();
        camera.setCameraRoll(colorCameraRoll);
        return camera;
    }
}
