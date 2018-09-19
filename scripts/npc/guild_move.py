response = sm.sendAskYesNo("Would you like to go to the guild headquarters?")

if response == 1:
    sm.warp(200000301, 0)
sm.dispose()
