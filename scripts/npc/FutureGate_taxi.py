maps = [273030300, 273020400, 273050000, 273040300, 273060300]


def init():
    selList = "Where would you like to go?"
    i = 0
    while i < len(maps):
        selList += "\r\n#L" + str (i) + "##m" + str (maps[i]) + "##l"
        i += 1
    sm.sendNext(selList)
def action(response,answer):
    sm.warp(maps[answer], 0)
    sm.dispose()