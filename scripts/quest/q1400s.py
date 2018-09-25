# 1400 - [Job Advancement] The 5 Paths

selections = [
[# Warrior
    "Mighty Warrior",  # String for in text
    102000003,  # hideout mapID
    1401# Quest ID
],

[# Magician
    "Wise Magician",   # String for in text
    101000003,  # hideout mapID
    1402# Quest ID
],

[# Archers
    "Accurate Bowman",  # String for in text
    100000201,  # hideout mapID
    1403# Quest ID
],

[# Thieves
    "Stealthy Thieves", # String for in text
    103000003,  # hideout mapID
    1404# Quest ID
],

[# Pirates
    "Flashy Pirates",   # String for in text
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


if response:
    sm.completeQuestNoRewards(parentID)
sm.dispose()
