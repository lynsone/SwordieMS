# 272030400 - Arkarium

response = sm.sendAskYesNo("Would you like to leave?")

if response == 1:
    sm.clearPartyInfo(272030300)
sm.dispose()
