answer = sm.sendAskYesNo("Once I lay my hand on the statue, a strange light covers me and it feels like I'm being sucked "
                         + "into somewhere else. Is it okay to be moved to somewhere else randomly just like that?")

if answer == 1:
    map = 910530000
    sm.warp(map)