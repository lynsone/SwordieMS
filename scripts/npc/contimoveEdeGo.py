map = [200090710, 200090610]


def init():
    sm.sendSay("Where would you like to go? \r\n#L0#Victoria Island#l\r\n#L1#Orbis#l")

def action(response,answer):
    sm.warp(map[answer], 0)
    sm.dispose()