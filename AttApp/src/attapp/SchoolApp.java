package attapp; 

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import attapp.gui.controller.LoginController;
import static javafx.application.Application.launch;

/**
 *
 * @author Philip
 */
public class SchoolApp extends Application
{
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws Exception
    {
        primaryStage = stage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SchoolApp.class.getResource("/attapp/gui/view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        
        rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle (MouseEvent event) {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        }
        });

        rootLayout.setOnMouseDragged(new EventHandler <MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
            }
        });

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        initView();
        CreateContextMenu();
        primaryStage.show();

    }

    private void CreateContextMenu()
    {
        ContextMenu cMenu = new ContextMenu();
        MenuItem mItem1 = new MenuItem();
        mItem1.setText("Close");

        cMenu.getItems().add(mItem1);

        rootLayout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getButton() == MouseButton.SECONDARY)
                {
                    cMenu.show(primaryStage, event.getScreenX(), event.getScreenY());
                }
            }
        });

        mItem1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Platform.exit();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private void initView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SchoolApp.class.getResource("/attapp/gui/view/LoginView.fxml"));
        AnchorPane logIn = (AnchorPane) loader.load();

        LoginController controller = loader.getController();
        controller.setRootLayout(rootLayout);

        // Set person overview into the center of root layout.
        rootLayout.setCenter(logIn);
    }

}
