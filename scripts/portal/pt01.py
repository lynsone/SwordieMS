def init():
    fieldID = sm.getFieldID()
    if fieldID == 811000100:
        sm.warp(811000200, 0)
    if fieldID == 811000200:
        sm.warp(811000300, 0)
    if fieldID == 811000300:
        sm.warp(811000400, 0)
    if fieldID == 811000400:
        sm.warp(811000500, 0)
    if fieldID == 811000500:
        sm.warpPartyOut(811000008)
    sm.dispose()