import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Key
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Pastikan path sesuai nama dan lokasi file FXML kamu
        URL url = new File("src/main/java/view/Homepage.fxml").toURI().toURL();

        Scene scene = new Scene(FXMLLoader.load(url));

        stage.setTitle("Aplikasi Pemantauan Kesehatan Harian");
        stage.setScene(scene);
        stage.show();
    }
}
