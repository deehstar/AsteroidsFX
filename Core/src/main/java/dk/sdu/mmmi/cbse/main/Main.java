package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

  private final GameData gameData = new GameData();
  private final World world = new World();
  private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
  private final Pane gameWindow = new Pane();

  public static void main(String[] args) {

    launch(Main.class);
  }

  @Override
  public void start(Stage stage) throws Exception {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfiguration.class);

    for (String bean : context.getBeanDefinitionNames()) {
      System.out.println(bean);
    }

    Game game = context.getBean(Game.class);
    game.start(stage);
    game.render();
  }
}

