selections = [
    [#Warrior
        "mighty Warriors",  #String for in text
        102000003           #hideout mapID
    ],

    [#Magician
        "wise Magicians",   #String for in text
        101000003           #hideout mapID
    ],

    [#Archers
        "accurate Bowmen",  #String for in text
        100000301           #hideout mapID
    ],

    [#Thieves
        "stealthy Thieves", #String for in text
        103000003           #hideout mapID
    ],

    [#Pirates
        "flashy Pirates",   #String for in text
        120000101           #hideout mapID
    ]
]

status = -1

def init():
    #sm.startQuestNoCheck(parentID)
    sm.sendNext("I see you have reached level 10!\r\nIt is time for you to select your future.")

def action(response, answer):
    global status
    status =+ 1

    if status == 0:
        text = "What path do you want to go on?"
        i = 0
        while i < len(selections):
            text += "\r\n#L"+ str(i) +"#"+ selections[i][0] +"#l"
            i += 1
        sm.sendNext(text)

    if status == 1:
        sm.sendNext("Hello")