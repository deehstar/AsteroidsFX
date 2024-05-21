package dk.sdu.mmmi.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CollisionPostProcessingSystem implements IPostEntityProcessingService {
  private HttpClient httpClient = HttpClient.newHttpClient();
  private String scoringSystemUrl = "http://localhost:8080/scoringsystem";
    @Override
    public void process(GameData gameData, World world) {

        System.out.println(world.getEntities().size());
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.equals(e2)) continue;


                double distanceX = e1.getX() - e2.getX();
                double distanceY = e1.getY() - e2.getY();
                double distanceBetween = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

                if (distanceBetween < 10) {
                    if ((e1.getName().equals("Enemy Bullet") && e2.getName().equals("Player")) ||
                            e2.getName().equals("Player") && e1.getName().equals("Enemy Bullet")) {
                        world.removeEntity(e1);
                        world.removeEntity(e2);

                        //Update score
                      sendPutRequest(scoringSystemUrl + "/score/update/" + (-50));
                      System.out.println("You were hit by an enemy bullet!");
                    }

                    if ((e1.getName().equals("Player Bullet") && e2.getName().equals("Enemy")) ||
                            e2.getName().equals("Enemy") && e1.getName().equals("Player Bullet")) {
                        world.removeEntity(e1);
                        world.removeEntity(e2);

                        //Update score
                      sendPutRequest(scoringSystemUrl + "/score/update/" + 50);
                      System.out.println("You killed an enemy!");
                    }

                    if ((e1.getName().equals("Player Bullet") && e2.getName().equals("Asteroid")) ||
                            e2.getName().equals("Asteroid") && e1.getName().equals("Player Bullet")) {
                        world.removeEntity(e1);
                        world.removeEntity(e2);

                        //Update score
                      sendPutRequest(scoringSystemUrl + "/score/update/" + 50);
                      System.out.println("You destroyed an asteroid!");
                    }
                }
            }
        }
    }
  private void sendPutRequest(String url) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .PUT(HttpRequest.BodyPublishers.noBody())
        .build();

      httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
