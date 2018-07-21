# [Theme Dungeon] Ellinel Fairy Academy
status = -1

def init():
        sm.setSpeakerID(1040002)
        sm.sendNext("Are you the one I invited to help out with the ruckus at the Ellinel Fairy Academy?")


def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("You don't look as strong as I'd hoped. But, you're famous, so I'll leave it to you.")
        sm.completeQuest(32151)
        sm.dispose()
