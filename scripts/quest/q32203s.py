# 32203 The New Explorer, Mai : Maple Road | Snail Park
sm.setSpeakerID(10303)
sm.sendNext("Ohmygoodness! Hi! Im Mai, an aspiring hero. it took me four years, but i just completed my freshmen year at hero school. You must be a new #bExplorer#n.")
sm.setPlayerAsSpeaker()
sm.sendNext("A new ... #bExplorer#n? What's that?")
sm.setSpeakerID(10303)
sm.sendNext("This was on that test I flunked ten time... Oh, right! Explorers are people who come to Maple World from other worlds! They start their journey right here on #bMaple Island#n.")
sm.setPlayerAsSpeaker()
sm.sendNext("I'm on... #bMaple Island#n?")
sm.setSpeakerID(10303)
sm.sendNext("You sure are. We used to be just some tiny island, but then Explorers started popping out. Now, we even got our own outhouse!")
sm.sendNext("so, your name is #h #, right? You have two options now. You can listen to some explanations about starting out, take a few small tests, get some free gifts and become my new best friend in the entire world...")
sm.sendNext("Or you can be teleported straight to town, but you'll miss out on my gifts... and I'll be super lonely and sad.")

selection = sm.sendNext("What do you say? \r\n#L0##b I'll be your friend, Mai!(Go through tutorial and get free equipment)#l \r\n #L1#I don't need you, Mai!(Skip tutorial and teleport straight to town.)#l")

if selection == 0:
    sm.sendNext("REALLY?! I'll fill you in on everything you need to know. I promise!")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)

elif selection == 1:
    sm.warp(104000000, 0)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)