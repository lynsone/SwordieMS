def init():
    if sm.getChr().getJob() != 2310:
        sm.sendSayOkay("Sorry, your job is not elible for this.")
        sm.dispose()
    sm.sendAskYesNo("Do you wish to job advance?")

def action(response, answer):
    if response == 1:
        sm.jobAdvance(2311)
        sm.addSP(5)
        sm.dispose()
