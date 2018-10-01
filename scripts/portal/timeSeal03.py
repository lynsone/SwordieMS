SEAL_CHECKER = 9300535
SEAL_OF_TIME = 2159367

if not sm.hasQuest(25673):
    sm.showFieldEffect("lightning/screenMsg/6")
    sm.createQuestWithQRValue(25673, "1", False)
    sm.spawnMob(SEAL_CHECKER, 0, -80, False)
    sm.spawnNpc(SEAL_OF_TIME, 0, -80)
    sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME, "summon", 0)
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSayOkay("The final seal is below the central staircase. I'm almost done.")
    
