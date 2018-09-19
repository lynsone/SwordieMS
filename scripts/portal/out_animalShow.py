# 223030210 - Scarlion & Targa
response = sm.sendAskYesNo("Would you like to leave?")

if response == 1:
    sm.clearPartyInfo(223030200)
sm.dispose()
