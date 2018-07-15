# [Riena Strait] The Heart of a Witch
status = -1

frostWitchBarbara = 1510008
noraTheExplorer = 1510007
exp = 11087

def init():
    sm.setSpeakerID(frostWitchBarbara)
    sm.sendNext("What you still have something to say to me?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(noraTheExplorer)
        sm.sendNext("Granny, I have a question.")

    if status == 1:
        sm.setSpeakerID(frostWitchBarbara)
        sm.sendNext("What do you want to know? Are you curious when this old lady will die? "
                    "If you mention about defeating and sort one more time in front of me, boy, "
                    "I'll destroy that mouth of yours first.")

    if status == 2:
        sm.setSpeakerID(noraTheExplorer)
        sm.sendNext("No.. What I am saying is that the penguins, the malamutes, and the seals here are doubting you. "
                    "Is it perhaps due to your magic that the glaciers are melting and the sea level is rising?")

    if status == 3:
        sm.setSpeakerID(frostWitchBarbara)
        sm.sendNext("Magic? What is that? Do you eat that?")

    if status == 4:
        sm.setSpeakerID(0) #Needs to be Player Avatar
        sm.sendNext("There also is a rumor that she is conducting some experiments with the children."
                    ""
                    "\r\n\r\n#r#e[This is meant to be Player Avatar]#k#n")

    if status == 5:
        sm.setSpeakerID(frostWitchBarbara)
        sm.sendNext("WHAT?! Shut your mouth! What makes you think that I would do such a thing to these cute little things?!")

    if status == 6:
        sm.sendNext("These poor kids lost their home and parents as the glacier is melting"
                    "If no one takes care of them, its unimaginable how they will turn out!"
                    "They were floating on the sea pitifully, and I picked them up and am taking care of them.")

    if status == 7:
        sm.setSpeakerID(noraTheExplorer)
        sm.sendNext("You have been raising the children?")

    if status == 8:
        sm.setSpeakerID(frostWitchBarbara)
        sm.sendNext("I was just taking care of them temporarily..")

    if status == 9:
        sm.sendNext("It is said, the penguins should grow with the penguins, "
                    "and the seals need to grow with the seals. "
                    "After curing a baby, and if it looks like it has recovered enough, "
                    "I would bring him to his people secretly at night.")

    if status == 10: #Needs to be Player Avatar
        sm.setSpeakerID(0)
        sm.sendNext("But why would you do such a thing despite all the misunderstandings?..."
                    ""
                    "\r\n\r\n#r#e[This is meant to be Player Avatar]#k#n")

    if status == 11:
        sm.setSpeakerID(frostWitchBarbara)
        sm.sendNext("Whew.. That mouth of yours.. How does it help you to talk so much? "
                    "If you have time for talking, shut up and do me a favor.")
        sm.completeQuestNoRewards(parentID)
        sm.giveExp(exp)
        sm.dispose()