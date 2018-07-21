# Nora the Explorer (1514002) | Nora's Cove

status = -1

def init():
    sm.sendNext("You have to be calm... very calm... Mother and Father, please give me strength...")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("Huh? The monsters are gone! Where have they gone to?")

    elif status == 1:
        sm.sendNext("Brave warrior! You saved me! I don't know how to thank you but... Thank you so much.")

    elif status == 2:
        sm.sendNext("My name is #bNora#k, an Archaeologist.\r\n\r\n"
                    "I came to Rien because my interest is in the Riena Straits"
                    "I was doing research on the mysterious fossil found here, but it started to move and attack me."
                    "I was to scared back then.")

    elif status == 3:
        sm.sendNext("But why were you there, brave warrior?")

    elif status == 4:
        sm.sendNext("#b(I told Nora that I was in search of a witch to help the residents here.)#k\r\n\r\n#e#r[This needs to be Player Avatar]#k#n")

    elif status == 5:
        sm.sendNext("A witch? Are you saying the appearance of the monsters and the rising of the sea levels is all done by the witch?")

    elif status == 6:
        sm.sendNext("No way. "
                    "These fossils fell off as the eternal frost started to melt due to the rising temperature and as the foundation rock plate was influenced by the ores with its magical power."
                    "Which means, these monsters appeared naturally. They have not been summoned by anyone.")

    elif status == 7:
        sm.sendNext("As for the rising of the sea levels.. Would that be really the work of the witch? Wouldn't there be other reasons behind it?")

    elif status == 8:
        sm.sendNext("I have a favor to ask. Can I come along as you investigate the area? Please take me as your colleague!")

    elif status == 9:
        sm.sendNext("#b(The knowledge of this young scholar would be a good help when solving problems.)\r\n\r\n#e#r[This need to be Player Avatar]#k#n")

    elif status == 10:
        sm.sendNext("Thank you! You will not regret it.\r\n"
                    "(You will be transported to the 1st Observatory.)")
        sm.completeQuestNoRewards(32170) # [Riena Strait] Nerd Rescue
        sm.giveExp(27718) # Exp Given
        sm.warp(141010000,0) # Ice Station 1
        sm.dispose()