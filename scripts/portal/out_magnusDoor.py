from net.swordie.ms.scripts import ScriptType

response = sm.sendAskYesNo("Are you sure you want to leave?")

if response == 1:
    sm.warpPartyOut(401060000)
    sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
sm.dispose()
