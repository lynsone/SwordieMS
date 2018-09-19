# 105300303 - Damien enter portal

fields = [
["Normal", 350160200],
["#rHard#k", 350160100]
]

s = "Which difficulty of Damien do you wish to face? \r\n"
i = 0
for entry in fields:
    s += "#L" + str(i) + "#" + entry[0] + "#l\r\n"
    i += 1
sm.sendSay(s)

if sm.getParty() is None:
    sm.sendSayOkay("Please create a party before going in.")
elif not sm.isPartyLeader():
    sm.sendSayOkay("Please have your party leader talk to me if you wish to face Damien.")
elif sm.checkParty():
    sm.setPartyDeathCount(20)
    sm.warpPartyIn(fields[answer][1])
sm.dispose()
