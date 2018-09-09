# 811000500 - Princess No (pno)
response = sm.sendAskYesNo("Would you like to leave?")

if response == 1:
    sm.clearPartyInfo(811000100)
sm.dispose()
