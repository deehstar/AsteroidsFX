package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private Entity enemy;

    @Override
    public void process(GameData gameData, World world) {

        gameData.setTime(gameData.getTime() + 1);

        if(gameData.getTime() % 400 == 0){
            enemy = createEnemyShipFromLeft(gameData);
            world.addEntity(enemy);
        }

        for(Entity enemy : world.getEntities(Enemy.class)){
            enemy.setX(enemy.getX() + 1);

            if(gameData.getTime() % 250 == 0) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
            } if(enemy.getX() > gameData.getDisplayWidth() + 20){
                world.removeEntity(enemy);
            }
        }

        if(gameData.getTime() > 2100000000){
            gameData.setTime(0);
        }

    }

    private Entity createEnemyShipFromLeft(GameData gameData) {
        double randomHeight = 1 + (Math.random() * gameData.getDisplayHeight());
        Entity enemyShip = new Enemy("Enemy");
        enemyShip.setPolygonCoordinates(-15, 0, -7, 7, 7, 7, 15, 0, 7, -7, -7, -7);
        enemyShip.setX(-1);
        enemyShip.setY(randomHeight);
        return enemyShip;
    }

//    private Entity createEnemyShipFromRight(GameData gameData) {
//        double randomHeight = 1 + (Math.random() * gameData.getDisplayHeight());
//        Entity enemyShip = new Enemy();
//        enemyShip.setPolygonCoordinates(-15, 0, -7, 7, 7, 7, 15, 0, 7, -7, -7, -7);
//        enemyShip.setX(gameData.getDisplayWidth()-1);
//        enemyShip.setY(randomHeight);
//        return enemyShip;
//    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
