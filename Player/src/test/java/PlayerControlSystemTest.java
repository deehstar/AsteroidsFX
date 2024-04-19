import dk.sdu.mmmi.playersystem.Player;
import dk.sdu.mmmi.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerControlSystemTest {
  @Test
  void testMove() {
    PlayerControlSystem playerControlSystem = new PlayerControlSystem();
    Player player = new Player("Player");
    GameData gameData = new GameData();
    World world = new World();

    world.addEntity(player);

    double initialX = player.getX();


    gameData.getKeys().setKey(GameKeys.UP, true);

    playerControlSystem.process(gameData, world);

    System.out.println("Final X: " + player.getX() + ", Final Y: " + player.getY()); // print final position

    assertNotEquals(initialX, player.getX());

  }
}
