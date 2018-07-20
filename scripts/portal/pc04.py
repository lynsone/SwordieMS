def init():
    currentMap = sm.getFieldID()

    if currentMap == 865010200 and sm.hasQuestCompleted(17612) and not sm.hasQuestCompleted(17613):
        sm.startQuestNoCheck(17613) # [Commerci Republic] The Minister's Son
        sm.warp(865090001, 1)
    sm.dispose()