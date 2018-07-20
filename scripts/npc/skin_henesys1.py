# Ms. Tan (1012105) | Henesys Skin-Care
def init():
    sm.sendNext("Well, hello! Welcome to the Henesys Skin-Care! "
                "Would you like to have a firm, tight, healthy looking skin like mine? "
                "With a #b#t5153000##k, you can let us take care of the rest and have the kind of skin you've always wanted!")

def action(response, answer):
    sm.sendSayOkay("You don't seem to have any #b#t5153000##k.")
    sm.dispose()