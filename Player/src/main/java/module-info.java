
import dk.sdu.mmmi.cbse.PlayerControlSystem;
import dk.sdu.mmmi.cbse.PlayerPlugin;
import dk.sdu.mmmi.cbse.PlayerTargetSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.TargetSPI;

module Player {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
    provides TargetSPI with PlayerTargetSystem;

}
