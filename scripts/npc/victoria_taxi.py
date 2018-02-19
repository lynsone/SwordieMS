def init():
    sm.sendSay("Where would you like to go? \r\n#L0#Henesys#l\r\n#L1#Kerning City#l")
    sm.dispose()

def action(response, answer):
    sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
    sm.dispose()
