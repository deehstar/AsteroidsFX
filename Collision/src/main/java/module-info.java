import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.CollisionPostProcessingSystem;
}
