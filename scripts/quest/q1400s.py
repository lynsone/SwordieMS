#   [Job Advancement] The 5 Paths

selections = [
    [# Warrior
        "mighty Warriors",  # String for in text
        102000003,          # hideout mapID
        1401                # Quest ID
    ],

    [# Magician
        "wise Magicians",   # String for in text
        101000003,          # hideout mapID
        1402                # Quest ID
    ],

    [# Archers
        "accurate Bowmen",  # String for in text
        100000201,          # hideout mapID
        1403                # Quest ID
    ],

    [# Thieves
        "stealthy Thieves", # String for in text
        103000003,          # hideout mapID
        1404                # Quest ID
    ],

    [# Pirates
        "flashy Pirates",   # String for in text
        120000101,          # hideout mapID
        1405                # Quest ID
    ]
]

status = -1

def init():
    sm.setSpeakerID(10301) # Mai
    sm.sendNext("I see you have reached level 10!\r\nIt is time for you to select your future.")

def action(response, answer):
    global status, status0Selection
    status += 1

    if status == 0:
        text = "What path do you want to go on?#b"
        i = 0
        while i < len(selections):
            text += "\r\n#L"+ str(i) +"#"+ selections[i][0] +"#l"
            i += 1
        sm.sendNext(text)

    if status == 1:
        status0Selection = answer
        sm.sendAskYesNo("So you would like to become just like all the "+ selections[status0Selection][0] +" in Maple world?")

    if status == 2:
        if response == 1:
            sm.completeQuestNoRewards(parentID)
            sm.startQuestNoCheck(selections[status0Selection][2])
            sm.warp(selections[status0Selection][1], 0)
        sm.dispose()