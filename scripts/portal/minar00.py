fields = {
    200090500 : 240000110  #  Way to Temple of Time -> Leafre Station
}

portals = {
    200090500 : 0
}

def init():
    sm.warp(fields[sm.getFieldID()], portals[sm.getFieldID()])