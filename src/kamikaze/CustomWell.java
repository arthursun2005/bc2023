package kamikaze;

import battlecode.common.MapLocation;
import battlecode.common.ResourceType;

public class CustomWell {
    MapLocation location;
    ResourceType resourceType;

    public CustomWell(MapLocation loc, ResourceType type) {
        location = loc;
        resourceType = type;
    }

    public MapLocation getMapLocation() {
        return location;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
