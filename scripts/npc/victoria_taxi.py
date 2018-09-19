maps = [104000000, 100000000, 103000000, 101000000, 102000000]

sm.sendSay("Where would you like to go? \r\n#L0#Lith Harbor#l\r\n#L1#Henesys#l\r\n#L2#Kerning City#l"
           + "\r\n#L3#Ellinia#l\r\n#L4#Perion#l")

# sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
sm.warp(maps[answer], 0)
sm.dispose()
