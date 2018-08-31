# 272030400 - Arkarium
from net.swordie.ms.scripts import ScriptType

def init():
    sm.sendAskYesNo("Would you like to leave?")

def action(response, answer):
    if response == 1:
        sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
        sm.clearPartyInfo(272030300)
    sm.dispose()