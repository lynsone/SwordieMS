# 130030001 ~ 130030004
def init():
    fieldID = sm.getFieldID()
    sm.warp(fieldID + 1, 0)
    sm.dispose()