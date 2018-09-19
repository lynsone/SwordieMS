# Black Jack - Resistance Headquarters : Secret Plaza
response = sm.sendAskYesNo("Do you want to enter the jaguar habitat?")

if response == 1:
    sm.warp(931000500, 0)
sm.dispose()
