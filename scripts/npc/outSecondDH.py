response = sm.sendAskYesNo("Are you done with the Knighthood Exam? Should I let you out?")

if response == 1:
    sm.warp(130020000)
else:
    sm.sendSayOkay("Okay, good luck hunting.")
sm.dispose()
