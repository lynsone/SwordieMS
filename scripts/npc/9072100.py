def init():
    sm.sendSay("SwordieMS uses python scripts!")
    sm.dispose()

def action(response):
    sm.sendSay("Response was " + str(response))
    sm.dispose()
