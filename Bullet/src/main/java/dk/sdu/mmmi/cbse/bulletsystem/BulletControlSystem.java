package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.TargetSPI;

import java.util.Optional;
import java.util.ServiceLoader;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
                double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
                double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
                bullet.setX(bullet.getX() + changeX * 3);
                bullet.setY(bullet.getY() + changeY * 3);

                BulletTargetSystem.updateBulletPosition(bullet.getX(), bullet.getY());
        }

    }

        @Override
        public Entity createBullet (Entity shooter, GameData gameData){


            Entity bullet;

            if (shooter.getName().equals("Player")) {
                bullet = new Bullet("Player Bullet");
                bullet.setPolygonCoordinates(2, -2, 2, 2, -2, 2, -2, -2);
                bullet.setX(shooter.getX());
                bullet.setY(shooter.getY());
                bullet.setRotation(shooter.getRotation());
            } else  {
                bullet = new Bullet("Enemy Bullet");
                bullet.setPolygonCoordinates(2, -2, 2, 2, -2, 2, -2, -2);
                bullet.setX(shooter.getX());
                bullet.setY(shooter.getY());

                Optional<TargetSPI> targetService = ServiceLoader.load(TargetSPI.class).findFirst();
                double angleToCenter = 0;
                if(targetService.isPresent()) {
                    double playerX = targetService.get().returnTargetX();
                    double playerY = targetService.get().returnTargetY();

                    double dx = playerX - shooter.getX();
                    double dy = playerY - shooter.getY();

                    angleToCenter = Math.toDegrees(Math.atan2(dy, dx));

                    System.out.println("Angle to center = " + angleToCenter);
                }
                bullet.setRotation(angleToCenter);





            }
            return bullet;
        }


    }
