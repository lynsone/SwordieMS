map = 200090600

response = sm.sendAskYesNo("Would you like to go to Edelstein?")

if response == 1:
    sm.warp(map, 0)
sm.dispose()
