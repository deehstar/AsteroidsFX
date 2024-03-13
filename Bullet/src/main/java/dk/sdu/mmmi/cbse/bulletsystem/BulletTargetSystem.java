package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.services.TargetSPI;

public class BulletTargetSystem implements TargetSPI {

    private static volatile double x,y = 0;

    @Override
    public double returnTargetX() {

        return x;
    }

    @Override
    public double returnTargetY() {

        return y;
    }

    public static void updateBulletPosition(double updatedX, double updatedY){
        x = updatedX;
        y = updatedY;
    }
}
