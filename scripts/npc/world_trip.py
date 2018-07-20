# Spinel | World Tour Guide
status = -1

def init():
    if sm.canHold(1452002):
        sm.sendNext("You CAN hold an Equip.")
    else:
        sm.sendNext("You CANNOT hold an Equip.")

def action(response, answer):
    if response == 1:
        status += 1
    else:
        status -= 1


    global status
