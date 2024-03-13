package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.asteroid.AsteroidSPI;


public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private Entity asteroid;
    @Override
    public void process(GameData gameData, World world) {

        if(gameData.getTime() % 200 == 0){
            asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        } if(gameData.getTime() % 200 == 0){
            asteroid = createAsteroidFromTop1(gameData);
            world.addEntity(asteroid);
        } if(gameData.getTime() % 400 == 0){
            asteroid = createAsteroidFromLeft2(gameData);
            world.addEntity(asteroid);
        } if(gameData.getTime() % 400 == 0){
            asteroid = createAsteroidFromTop2(gameData);
            world.addEntity(asteroid);
        }

//        for (int i = 0; i < 300; i++) {
//            asteroid = createAsteroid(gameData);
//            world.addEntity(asteroid);
//        }


        for(Entity asteroid : world.getEntities(Asteroid.class)){
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX * 1);
            asteroid.setY(asteroid.getY() + changeY * 1);

            if(asteroid.getX() > gameData.getDisplayWidth() + 20){
                world.removeEntity(asteroid);
            }
        }

    }

    @Override
    public Entity createAsteroid(GameData gameData) {
        double randomY = getRandomNumber(0, gameData.getDisplayHeight());
        Entity asteroid = new Asteroid("Asteroid");
        asteroid.setPolygonCoordinates(-8, -8, 0, -12, 8, -8, 4, 8, -4, 8);
        asteroid.setX(-2);
        asteroid.setY(randomY);
        asteroid.setRotation(asteroid.getRotation() + 30);
        return asteroid;
    }

    public Entity createAsteroidFromTop1(GameData gameData) {
        double randomX = getRandomNumber(0, gameData.getDisplayWidth());
        Entity asteroid = new Asteroid("Asteroid2");
        asteroid.setPolygonCoordinates(-8,0,0,-7,9,-8,8,8,-1,4);
        asteroid.setX(randomX);
        asteroid.setY(-2);
        asteroid.setRotation(asteroid.getRotation() + 30);
        return asteroid;
    }

    public Entity createAsteroidFromLeft2(GameData gameData) {
        double randomX = getRandomNumber(0, gameData.getDisplayWidth());
        Entity asteroid = new Asteroid("Asteroid3");
        asteroid.setPolygonCoordinates(-2,0,-6,-8,6,-5,8,5,-4,8,-6,2);
        asteroid.setX(randomX);
        asteroid.setY(-2);
        asteroid.setRotation(asteroid.getRotation() + 30);
        return asteroid;
    }

    public Entity createAsteroidFromTop2(GameData gameData) {
        double randomX = getRandomNumber(0, gameData.getDisplayWidth());
        Entity asteroid = new Asteroid("Asteroid4");
        asteroid.setPolygonCoordinates(0,-10,8,-5,2,8,-5,9,-8,2,-4,-6);
        asteroid.setX(randomX);
        asteroid.setY(-2);
        asteroid.setRotation(asteroid.getRotation() + 30);
        return asteroid;
    }

    public double getRandomNumber(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }
}
