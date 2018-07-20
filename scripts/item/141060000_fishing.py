# Barbara's Fishing Kit (2431796) | Used during Riena Strait Quest Line

smallfish = 4030027
bigfish = 4030028
randomInt = sm.getRandomIntBelow(3)

def init():
    if not sm.getFieldID() == 141060000:
        sm.setSpeakerID(1510007) # Nora
        sm.sendSayOkay("#b(Barbara said there was a great place to fish, just north of her house.)")
        sm.dispose()
    else:
        sm.setSpeakerID(1510006) # Helmsman Tanya

        if randomInt == 0:      # Big Fish Caught
            sm.sendNext("#bNavigator, You caught #v"+ str(bigfish) +"##t"+ str(bigfish) +"#!")

        elif randomInt == 1:    # Small Fish Caught
            sm.sendNext("#bYou caught #v"+ str(smallfish) +"##t"+ str(smallfish) +"#...")

        elif randomInt == 2:    # Nothing Caught
            sm.sendNext("#bYou caught nothing..")


def action(response, answer):
    sm.setSpeakerID(1510007) # Nora

    if randomInt == 0: # Big Fish Caught
        sm.sendNext("#bGreat job!")
        sm.giveItem(bigfish)

    elif randomInt == 1:    # Small Fish Caught
        sm.sendNext("#bI'm afraid this fish is too small for Barbara\r\n\r\nWe'll set it free.")

    elif randomInt == 2:    # Nothing Caught
        sm.sendNext("#bThat's too bad, nothing..\r\n\r\nLet's try again!")
    sm.dispose()