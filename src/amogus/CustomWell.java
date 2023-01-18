package amogus;

import battlecode.common.*;

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
