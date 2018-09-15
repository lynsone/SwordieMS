
response = sm.sendAskYesNo("Are you sure you want to leave?")

if response == 1:
    sm.warpPartyOut(401060000)
sm.dispose()
