# 105200310 (+ other RA bosses)
response = sm.sendAskYesNo("Would you like to leave?")

if response == 1:
    sm.warpPartyOut(105200000)
sm.dispose()
