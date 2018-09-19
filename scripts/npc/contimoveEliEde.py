map = 200090701

response = sm.sendAskYesNo("Do you want to go to Edelstein?")

if response == 1:
    sm.warp(map, 0)
sm.dispose()
