# [Commerci Republic] Neinheart's Request

sm.setSpeakerID(9390200) # Parbell
def init():
    sm.sendNext("Well butter mah biscuits! You must be that #h0# I hears about from the long-haired feller. "
                "Didn't yet pappy even learn you not t' keep an old man a' waitin'?")

def action(response, answer):
    sm.completeQuest(parentID)
    sm.dispose()