# Field script for leaving Ellinel Fairy Academy

NORTH_FOREST_GIANT_TREE = 101030000 # MAP ID

def init():
    sm.invokeAfterDelay(4000, "warp", NORTH_FOREST_GIANT_TREE, 5)
    sm.dispose()