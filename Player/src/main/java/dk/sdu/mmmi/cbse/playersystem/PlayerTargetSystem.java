package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.services.TargetSPI;

public class PlayerTargetSystem implements TargetSPI {

    private static volatile double x,y = 0;

    @Override
    public double returnTargetX() {

        return x;
    }

    @Override
    public double returnTargetY() {

        return y;
    }

    public static void updatePlayerPosition(double updatedX, double updatedY){
        x = updatedX;
        y = updatedY;
    }
}
