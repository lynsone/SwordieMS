# 1400 - [Job Advancement] The 5 Paths

selections = [
[# Warrior
    "Warrior",  # String for in text
    102000003,  # hideout mapID
    1401# Quest ID
],

[# Magician
    "Magician",   # String for in text
    101000003,  # hideout mapID
    1402# Quest ID
],

[# Archers
    "Bowman",  # String for in text
    100000201,  # hideout mapID
    1403# Quest ID
],

[# Thieves
    "Thieves", # String for in text
    103000003,  # hideout mapID
    1404# Quest ID
],

[# Pirates
    "Pirates",   # String for in text
    120000101,  # hideout mapID
    1405# Quest ID
]
]


sm.setSpeakerID(10301) # Mai
sm.sendNext("I see you have reached level 10!\r\nIt is time for you to select your future.")


text = "What path do you want to go on?#b"
i = 0
while i < len(selections):
    text += "\r\n#L"+ str(i) +"#"+ selections[i][0] +"#l"
    i += 1
response = sm.sendNext(text)
job = selections[response][0]
hideout = selections[response][1]
quest = selections[response][2]
isSure = sm.sendAskYesNo("Are you sure you want to follow the path of a " + job + "?")

if isSure:
    sm.sendNext("Okay, I will send you to the " + job + "'s instructor.")
    sm.warp(hideout)
    sm.completeQuestNoRewards(parentID)
    sm.startQuest(quest)
sm.dispose()
