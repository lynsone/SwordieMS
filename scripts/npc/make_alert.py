#Henesys START
itemsHene = [
    [1452002, 1452003, 1452001, 1452000, 1452005, 1452006, 1452007], #Bows
    [1462001, 1462002, 1462003, 1462000, 1462004, 1462005, 1462006, 1462007], #Xbows
    [1082012, 1082013, 1082016, 1082048, 1082068, 1082071, 1082084, 1082089], #Gloves
    [1082015,1082014,1082017,1082018,1082049,1082050,1082069,1082070,1082072,1082073,1082085,1082083,1082090,1082091], #Gloves Upgrades
    [4003001,4003001,4003000], #Materials
    [2060000,2061000,2060001,2061001,2060002,2061002] #Arrows
]

nonEquipSuffixHene = ["with Tree Branches", "with Firewood", "(packs of 15)"]

costHene = [
    [   #Bow Materials
        [4003001,4000000],
        [4011001,4003000],
        [4003001,4000016],
        [4011001,4021006,4003000],
        [4011001,4011006,4021003,4021006,4003000],
        [4011004,4021000,4021004,4003000],
        [4021008,4011001,4011006,4003000,4000014]
    ],

    [   #Xbow Materials
        [4003001,4003000],
        [4011001,4003001,4003000],
        [4011001,4003001,4003000],
        [4011001,4021006,4021002,4003000],
        [4011001,4011005,4021006,4003001,4003000],
        [4021008,4011001,4011006,4021006,4003000],
        [4021008,4011004,4003001,4003000],
        [4021008,4011006,4021006,4003001,4003000]
    ],

    [   #Gloves Materials
        [4000021,4000009],
        [4000021,4000009,4011001],
        [4000021,4000009,4011006],
        [4000021,4011006,4021001],
        [4011000,4011001,4000021,4003000],
        [4011001,4021000,4021002,4000021,4003000],
        [4011004,4011006,4021002,4000030,4003000],
        [4011006,4011007,4021006,4000030,4003000]
    ],

    [   #Gloves Upgrade Materials
        [1082013,4021003],
        [1082013,4021000],
        [1082016,4021000],
        [1082016,4021008],
        [1082048,4021003],
        [1082048,4021008],
        [1082068,4011002],
        [1082068,4011006],
        [1082071,4011006],
        [1082071,4021008],
        [1082084,4011000,4021000],
        [1082084,4011006,4021008],
        [1082089,4021000,4021007],
        [1082089,4021007,4021008]
    ],

    [   #Create Material
        [4000003],
        [4000018],
        [4011000,4011001]
    ],

    [   #Create Arrows
        [4003001,4003004],
        [4003001,4003004],
        [4011000,4003001,4003004],
        [4011000,4003001,4003004],
        [4011001,4003001,4003005],
        [4011001,4003001,4003005]
    ]
]

costQHene = [
    [   #Bow Material Quantity
        [5,30],
        [1,3],
        [30,50],
        [2,2,8],
        [5,5,3,3,30],
        [7,6,3,35],
        [1,10,3,40,50]
    ],

    [   #Xbow Material Quantity
        [7,2],
        [1,20,5],
        [1,50,8],
        [2,1,1,10],
        [5,5,3,50,15],
        [1,8,4,2,30],
        [2,6,30,30],
        [2,5,3,40,40]
    ],

    [   #Gloves Material Quantity
        [15,20],
        [20,20,2],
        [40,50,2],
        [50,2,1],
        [1,3,60,15],
        [3,1,3,80,25],
        [3,1,2,40,35],
        [2,1,8,50,50]
    ],

    [   #Glove Upgrade Materials Quantity
        [1,2],
        [1,1],
        [1,3],
        [1,1],
        [1,3],
        [1,1],
        [1,4],
        [1,2],
        [1,4],
        [1,2],
        [1,1,5],
        [1,2,2],
        [1,5,1],
        [1,2,2]
    ],

    [   #Create Materials Quantity
        [10],
        [5],
        [1, 1]
    ],

    [   #Create Arrows Quantity
        [1,1],
        [1,1],
        [1,3,10],
        [1,3,10],
        [1,5,15],
        [1,5,15]
    ]
]

costmesoHene = [
    [800,2000,3000,5000,30000,40000,80000], #Meso Cost Bow
    [1000,2000,3000,10000,30000,50000,80000,200000], #Meso Cost Xbow
    [5000,10000,15000,20000,30000,40000,50000,70000], #Meso Cost Glove
    [7000,7000,10000,12000,15000,20000,22000,25000,30000,40000,55000,60000,70000,80000], #Meso Cost Upgrade Glove
    [0,0,0], #Meso Cost Materials
    [0,0,0,0,0,0] #Meso Cost Arrows
]
#Henesys END



#Perion START
itemsPerion = [
    [4011000,4011001,4011002,4011003,4011004,4011005,4011006],  #Mineral Refine
    [4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  #Jewel Refine
    [1002042,1002041,1002002,1002044,1002003,1002040,1002007,1002052,1002011,1002058,1002009,1002056,1002087,1002088,1002049,1002050,1002047,1002048,1002099,1002098,1002085,1002028,1002022,1002101],  #Helmet Refine
    [1092014,1092013,1092010,1092011]   #Shield Refine
]

costPerion = [
    [   #Mineral Refine Materials
        [4010000],
        [4010001],
        [4010002],
        [4010003],
        [4010004],
        [4010005],
        [4010006]
    ],

    [   #Jewel Refine Materials
        [4020000],
        [4020001],
        [4020002],
        [4020003],
        [4020004],
        [4020005],
        [4020006],
        [4020007],
        [4020008]
    ],

    [   #Refine Helmet Materials
        [1002001,4011002],
        [1002001,4021006],
        [1002043,4011001],
        [1002043,4011002],
        [1002039,4011001],
        [1002039,4011002],
        [1002051,4011001],
        [1002051,4011002],
        [1002059,4011001],
        [1002059,4011002],
        [1002055,4011001],
        [1002055,4011002],
        [1002027,4011002],
        [1002027,4011006],
        [1002005,4011006],
        [1002005,4011005],
        [1002004,4021000],
        [1002004,4021005],
        [1002021,4011002],
        [1002021,4011006],
        [1002086,4011002],
        [1002086,4011004],
        [1002100,4011007,4011001],
        [1002100,4011007,4011002]
    ],

    [   #Refine Shield Materials
        [1092012,4011003],
        [1092012,4011002],
        [1092009,4011007,4011004],
        [1092009,4011007,4011003]
    ]
]

costQPerion = [
    [   #Refine Mineral Materials Quantity
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10]
    ],

    [   #Refine Jewel Materials Quantity
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10]
    ],

    [   #Refine Helmet Materials Quantity
        [1,1],
        [1,1],
        [1,1],
        [1,1],
        [1,1],
        [1,1],
        [1,2],
        [1,2],
        [1,3],
        [1,3],
        [1,3],
        [1,3],
        [1,4],
        [1,4],
        [1,5],
        [1,5],
        [1,3],
        [1,3],
        [1,5],
        [1,6],
        [1,5],
        [1,4],
        [1,1,7],
        [1,1,7]
    ],

    [   #Refine Shield Materials Quantity
        [1,10],
        [1,10],
        [1,1,15],
        [1,1,15]
    ]
]

costmesoPerion = [
    [300,300,300,500,500,500,800],  #Meso Cost Mineral
    [500,500,500,500,500,500,500,1000,3000],    #Meso Cost Jewel
    [500,300,500,800,500,800,1000,1500,1500,2000,1500,2000,2000,4000,4000,5000,8000,10000,12000,15000,20000,25000,30000,30000], #Meso Cost Helmet
    [100000,100000,120000,120000]   #Meso Cost Shield
]
#Perion END



#Kerning Item START
itemsKerningItem = [
    [1082002,1082029,1082030,1082031,1082032,1082037,1082042,1082046,1082075,1082065,1082092],   #Create Gloves
    [1082033,1082034,1082038,1082039,1082043,1082044,1082047,1082045,1082076,1082074,1082067,1082066,1082093,1082094],  #Upgrade Gloves
    [1472001,1472004,1472007,1472008,1472011,1472014,1472018],  #Create Claws
    [1472002,1472003,1472005,1472006,1472009,1472010,1472012,1472013,1472015,1472016,1472017,1472019,1472020],  #Upgrade Claws
    [4003001,4003001,4003000]   #Create Materials
]

nonEquipSuffixKerningItem = ["with Tree Branches", "with Firewood", "(packs of 15)"]

costKerningItem = [
    [   #Create Gloves Materials
        [4000021],
        [4000021,4000018],
        [4000021,4000015],
        [4000021,4000020],
        [4011000,4000021],
        [4011000,4011001,4000021],
        [4011001,4000021,4003000],
        [4011001,4011000,4000021,4003000],
        [4021000,4000014,4000021,4003000],
        [4021005,4021008,4000030,4003000],
        [4011007,4011000,4021007,4000030,4003000]
    ],

    [   #Upgrade Gloves Materials
        [1082032,4011002],
        [1082032,4021004],
        [1082037,4011002],
        [1082037,4021004],
        [1082042,4011004],
        [1082042,4011006],
        [1082046,4011005],
        [1082046,4011006],
        [1082075,4011006],
        [1082075,4021008],
        [1082065,4021000],
        [1082065,4011006,4021008],
        [1082092,4011001,4000014],
        [1082092,4011006,4000027]
    ],

    [   #Create Claw Materials
        [4011001,4000021,4003000],
        [4011000,4011001,4000021,4003000],
        [1472000,4011001,4000021,4003001],
        [4011000,4011001,4000021,4003000],
        [4011000,4011001,4000021,4003000],
        [4011000,4011001,4000021,4003000],
        [4011000,4011001,4000030,4003000]
    ],

    [   #Upgrade Claw Materials
        [1472001,4011002],
        [1472001,4011006],
        [1472004,4011001],
        [1472004,4011003],
        [1472008,4011002],
        [1472008,4011003],
        [1472011,4011004],
        [1472011,4021008],
        [1472014,4021000],
        [1472014,4011003],
        [1472014,4021008],
        [1472018,4021000],
        [1472018,4021005]
    ],

    [   #Create Materials Materials
        [4000003],
        [4000018],
        [4011000,4011001]
    ]
]

costQKerningItem = [
    [   #Create Gloves Materials Quantity
        [15],
        [30,20],
        [30,20],
        [30,20],
        [2,40],
        [2,1,10],
        [2,50,10],
        [3,1,60,15],
        [3,200,80,30],
        [3,1,40,30],
        [1,8,1,50,50]
    ],

    [   #Upgrade Gloves Materials Quantity
        [1,1],
        [1,1],
        [1,2],
        [1,2],
        [1,2],
        [1,1],
        [1,3],
        [1,2],
        [1,4],
        [1,2],
        [1,5],
        [1,2,1],
        [1,7,200],
        [1,7,150]
    ],

    [   #Create Claw Materials Quantity
        [1,20,5],
        [2,1,30,10],
        [1,3,20,30],
        [3,2,50,20],
        [4,2,80,25],
        [3,2,100,30],
        [4,2,40,35]
    ],

    [   #Upgrade Claw Materials Quantity
        [1,1],
        [1,1],
        [1,2],
        [1,2],
        [1,3],
        [1,3],
        [1,4],
        [1,1],
        [1,5],
        [1,5],
        [1,2],
        [1,6],
        [1,6]
    ],

    [   #Create Materials Materials Quantity
        [10],
        [5],
        [1,1]
    ]
]

costmesoKerningItem = [
    [1000,7000,7000,7000,10000,15000,25000,30000,40000,50000,70000],    #Create Gloves meso
    [5000,7000,10000,12000,15000,20000,22000,25000,40000,50000,55000,60000,70000,80000],    #Upgrade Gloves meso
    [2000,3000,5000,15000,30000,40000,50000],   #Create Claw meso
    [1000,2000,3000,5000,10000,15000,20000,25000,30000,30000,35000,40000,40000],    #Upgrade Claw meso
    [0,0,0] #Materials meso
]
#Kerning Item END



#Kerning Ore START
itemsKerningOre = [
    [4011000,4011001,4011002,4011003,4011004,4011005,4011006],  #Mineral Refine
    [4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  #Jewel Refine
    [1472023,1472024,1472025],  #Claw Refine
]

costKerningOre = [
    [   #Mineral Refine Material
        [4010000],
        [4010001],
        [4010002],
        [4010003],
        [4010004],
        [4010005],
        [4010006]
    ],

    [   #Jewel Refine Material
        [4020000],
        [4020001],
        [4020002],
        [4020003],
        [4020004],
        [4020005],
        [4020006],
        [4020007],
        [4020008]
    ],

    [   #Claw Upgrade Material
        [1472022,4011007,4021000,2012000],
        [1472022,4011007,4021005,2012002],
        [1472022,4011007,4021008,4000046]
    ]
]

costQKerningOre = [
    [   #Mineral Refine Material Quantity
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10]
    ],

    [   #Jewel Refine Material Quantity
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10],
        [10]
    ],

    [   #Claw Upgrade Material Quantity
        [1,1,8,10],
        [1,1,8,10],
        [1,1,3,5]
    ]
]

costmesoKerningOre = [
    [300,300,300,500,500,500,800],  #Mineral Meso Cost
    [500,500,500,500,500,500,500,1000,3000],    #Jewel Meso Cost
    [80000,80000,100000]    #Claw Upgrade Meso Cost
]
#Kerning Ore END

status = -1


if sm.getFieldID() == 100000100:

    #Vicious | Item Maker
    def init():
        sm.sendNext("Hello. I am Vicious, retired Sniper. However, I used to be the top student of Athena Pierce. Though I no longer hunt, I can make some archer items that will be useful for you...\r\n#b"
                    "#L0#Create a bow#l \r\n"
                    "#L1#Create a crossbow#l \r\n"
                    "#L2#Create gloves#l \r\n"
                    "#L3#Upgrade gloves#l \r\n"
                    "#L4#Create materials#l \r\n"
                    "#L5#Create arrows#l \r\n"
                    )

    def action(response, answer):
        global status, selection1, selection2, selection3, complete
        status += 1

        if status == 0:
            selection1 = answer
            listStr = "What item would you like to make? \r\n btw, my NPC id is: "+ str(sm.getParentID()) +"#b"
            i = 0
            while i < len(itemsHene[selection1]):
                if selection1 == 4:
                    listStr += "\r\n#L" + str(i) + "##z" + str(itemsHene[selection1][i]) + "# " + str(nonEquipSuffixHene[i])
                else:
                    listStr += "\r\n#L" + str(i) + "##z" + str(itemsHene[selection1][i]) + "#"
                i += 1
            sm.sendNext(listStr)

        if status == 1:
            selection2 = answer
            if selection1 == 4 or selection1 == 5:
                materialStr = "You want #z" + str(itemsHene[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
            else:
                materialStr = "You want a #z" + str(itemsHene[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
            i = 0
            while i < len(costHene[selection1][selection2]):
                materialStr += "\r\n" + str(costQHene[selection1][selection2][i]) + "x #z" + str(costHene[selection1][selection2][i]) + "#"
                i += 1
            if costmesoHene[selection1][selection2] > 0:
                materialStr += "\r\n#i4031138#" + str(costmesoHene[selection1][selection2]) + " mesos"
            if selection1 == 4 or selection1 == 5:
                materialStr += "\r\n\r\nHow many do you want me to make?"
                sm.sendAskNumber(materialStr, 1, 1, 50)
            else:
                sm.sendAskYesNo(materialStr)

        if status == 2:
            selection3 = answer

            if response == 1:
                multiplier = 1
                if selection1 == 4 or selection1 == 5:
                    multiplier = selection3

                if sm.getMesos() < costmesoHene[selection1][selection2]:
                    sm.sendSayOkay("I'm afraid you cannot afford my services.")
                    sm.dispose()
                    return

                i = 0
                while i < len(costHene[selection1][selection2]):
                    complete = sm.hasItem(costHene[selection1][selection2][i], (costQHene[selection1][selection2][i] * multiplier))
                    i += 1
                    if complete == False:
                        break

                if complete == False:
                    sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
                else:
                    if sm.canHold(itemsHene[selection1][selection2]) == False:
                        sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                        sm.dispose()
                        return
                    else:
                        i = 0
                        while i < len(costHene[selection1][selection2]):
                            sm.consumeItem(costHene[selection1][selection2][i], (costQHene[selection1][selection2][i] * multiplier))
                            i += 1
                        if costmesoHene[selection1][selection2] > 0:
                            sm.giveMesos(-(costmesoHene[selection1][selection2] * multiplier))

                        if itemsHene[selection1][selection2] >= 2060000 and itemsHene[selection1][selection2] <= 2060002:
                            multiplier2 = 1000 - (itemsHene[selection1][selection2] - 2060000) * 100
                        elif itemsHene[selection1][selection2] >= 2061000 and itemsHene[selection1][selection2] <= 2061002:
                            multiplier2 = 1000 - (itemsHene[selection1][selection2] - 2061000) * 100
                        elif itemsHene[selection1][selection2] == 4003000:
                            multiplier2 = 15
                        else:
                            multiplier2 = 1

                        sm.giveItem(itemsHene[selection1][selection2], (multiplier * multiplier2))
                        sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
            else:
                sm.sendSayOkay("Let me know when you are ready to create something.")
            sm.dispose()



elif sm.getFieldID() == 102000000:

    #Mr. Thunder | Repair Durability
    def init():
        sm.sendNext("hm? Who might you be? Oh, you've heard about my forging skills? In that case, I'd be glad to process some of your ores... for a fee.#b"
                    "\r\n#L0#Refine a mineral ore#l"
                    "\r\n#L1#Refine a jewel ore#l"
                    "\r\n#L2#Upgrade a helmet#l"
                    "\r\n#L3#Upgrade a shield#l"
                    )

    def action(response, answer):
        global status, selection1, selection2, selection3, complete
        status += 1

        if status == 0:
            selection1 = answer
            listStr = "What item would you like to make? #b"
            i = 0
            while i < len(itemsPerion[selection1]):
                listStr += "\r\n#L" + str(i) + "##z" + str(itemsPerion[selection1][i]) + "#"
                i += 1
            sm.sendNext(listStr)

        if status == 1:
            selection2 = answer
            if selection1 == 0 or selection1 == 1:
                materialStr = "You want #z" + str(itemsPerion[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
            else:
                materialStr = "You want a #z" + str(itemsPerion[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
            i = 0
            while i < len(costPerion[selection1][selection2]):
                materialStr += "\r\n" + str(costQPerion[selection1][selection2][i]) + "x #z" + str(costPerion[selection1][selection2][i]) + "#"
                i += 1
            if costmesoPerion[selection1][selection2] > 0:
                materialStr += "\r\n#i4031138#" + str(costmesoPerion[selection1][selection2]) + " mesos"
            if selection1 == 0 or selection1 == 1:
                materialStr += "\r\n\r\nHow many do you want me to make?"
                sm.sendAskNumber(materialStr, 1, 1, 50)
            else:
                sm.sendAskYesNo(materialStr)

        if status == 2:
            selection3 = answer

            if response == 1:
                multiplier = 1
                if selection1 == 0 or selection1 == 1:
                    multiplier = selection3

                if sm.getMesos() < costmesoPerion[selection1][selection2]:
                    sm.sendSayOkay("I'm afraid you cannot afford my services.")
                    sm.dispose()
                    return

                i = 0
                while i < len(costPerion[selection1][selection2]):
                    complete = sm.hasItem(costPerion[selection1][selection2][i], (costQPerion[selection1][selection2][i] * multiplier))
                    i += 1
                    if complete == False:
                        break

                if complete == False:
                    sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
                else:
                    if sm.canHold(itemsPerion[selection1][selection2]) == False:
                        sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                        sm.dispose()
                        return
                    else:
                        i = 0
                        while i < len(costPerion[selection1][selection2]):
                            sm.consumeItem(costPerion[selection1][selection2][i], (costQPerion[selection1][selection2][i] * multiplier))
                            i += 1
                        if costmesoPerion[selection1][selection2] > 0:
                            sm.giveMesos(-(costmesoPerion[selection1][selection2] * multiplier))


                        sm.giveItem(itemsPerion[selection1][selection2], multiplier)
                        sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
            else:
                sm.sendSayOkay("Let me know when you are ready to create something.")
            sm.dispose()



elif sm.getFieldID() == 103000000:

    #JM From Tha Streetz | Item Creator
    def init():
        sm.sendNext("Pst... If you have the right goods, I can turn it into something nice...#b"
                    "\r\n#L0#Create a glove#l"
                    "\r\n#L1#Upgrade a glove#l"
                    "\r\n#L2#Create a claw#l"
                    "\r\n#L3#Upgrade a claw#l"
                    "\r\n#L4#Create materials#l"
                    )

    def action(response, answer):
        global status, selection1, selection2, selection3, complete
        status += 1

        if status == 0:
            selection1 = answer
            listStr = "What item would you like to make? #b"
            i = 0
            while i < len(itemsKerningItem[selection1]):
                if selection1 == 4:
                    listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningItem[selection1][i]) + "# " + str(nonEquipSuffixKerningItem[i])
                else:
                    listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningItem[selection1][i]) + "#"
                i += 1
            sm.sendNext(listStr)

        if status == 1:
            selection2 = answer
            if selection1 == 4:
                materialStr = "You want #z" + str(itemsKerningItem[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
            else:
                materialStr = "You want a #z" + str(itemsKerningItem[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
            i = 0
            while i < len(costKerningItem[selection1][selection2]):
                materialStr += "\r\n" + str(costQKerningItem[selection1][selection2][i]) + "x #z" + str(costKerningItem[selection1][selection2][i]) + "#"
                i += 1
            if costmesoKerningItem[selection1][selection2] > 0:
                materialStr += "\r\n#i4031138#" + str(costmesoKerningItem[selection1][selection2]) + " mesos"
            if selection1 == 4:
                materialStr += "\r\n\r\nHow many do you want me to make?"
                sm.sendAskNumber(materialStr, 1, 1, 50)
            else:
                sm.sendAskYesNo(materialStr)

        if status == 2:
            selection3 = answer

            if response == 1:
                multiplier = 1
                if selection1 == 4:
                    multiplier = selection3

                if sm.getMesos() < costmesoKerningItem[selection1][selection2]:
                    sm.sendSayOkay("Where's the mesos, man?!")
                    sm.dispose()
                    return

                i = 0
                while i < len(costKerningItem[selection1][selection2]):
                    complete = sm.hasItem(costKerningItem[selection1][selection2][i], (costQKerningItem[selection1][selection2][i] * multiplier))
                    i += 1
                    if complete == False:
                        break

                if complete == False:
                    sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
                else:
                    if sm.canHold(itemsKerningItem[selection1][selection2]) == False:
                        sm.sendSayOkay("Make sure you have room in your inventory.")
                        sm.dispose()
                        return
                    else:
                        i = 0
                        while i < len(costKerningItem[selection1][selection2]):
                            sm.consumeItem(costKerningItem[selection1][selection2][i], (costQKerningItem[selection1][selection2][i] * multiplier))
                            i += 1
                        if costmesoKerningItem[selection1][selection2] > 0:
                            sm.giveMesos(-(costmesoKerningItem[selection1][selection2] * multiplier))

                        multiplier2 = 1
                        if itemsKerningItem[selection1][selection2] == 4003000:
                            multiplier2 = 15

                        sm.giveItem(itemsKerningItem[selection1][selection2], (multiplier * multiplier2))
                        sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
            else:
                sm.sendSayOkay("Let me know when you are ready to create something.")
            sm.dispose()



elif sm.getFieldID() == 103000006:

    #Chris | Ore Refiner
    def init():
        sm.sendNext("Yes, I do own this forge. If you're willing to pay, I can offer you some of my services.#b"
                    "\r\n#L0#Refine a mineral ore#l"
                    "\r\n#L1#Refine a jewel ore#l"
                    "\r\n#L2#Upgrade a claw#l")

    def action(response, answer):
        global status, selection1, selection2, selection3, complete
        status += 1


        if status == 0:
            selection1 = answer
            listStr = "What item would you like to make? #b"
            i = 0
            while i < len(itemsKerningOre[selection1]):
                listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningOre[selection1][i]) + "#"
                i += 1
            sm.sendNext(listStr)

        if status == 1:
            selection2 = answer
            if selection1 == 0 or selection1 == 1:
                materialStr = "You want #z" + str(itemsKerningOre[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
            else:
                materialStr = "You want a #z" + str(itemsKerningOre[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
            i = 0
            while i < len(costKerningOre[selection1][selection2]):
                materialStr += "\r\n" + str(costQKerningOre[selection1][selection2][i]) + "x #z" + str(costKerningOre[selection1][selection2][i]) + "#"
                i += 1
            if costmesoKerningOre[selection1][selection2] > 0:
                materialStr += "\r\n#i4031138#" + str(costmesoKerningOre[selection1][selection2]) + " mesos"
            if selection1 == 0 or selection1 == 1:
                materialStr += "\r\n\r\nHow many do you want me to make?"
                sm.sendAskNumber(materialStr, 1, 1, 50)
            else:
                sm.sendAskYesNo(materialStr)

        if status == 2:
            selection3 = answer

            if response == 1:
                multiplier = 1
                if selection1 == 0 or selection1 == 1:
                    multiplier = selection3

                if sm.getMesos() < costmesoKerningOre[selection1][selection2]:
                    sm.sendSayOkay("I'm afraid you cannot afford my services.")
                    sm.dispose()
                    return

                i = 0
                while i < len(costKerningOre[selection1][selection2]):
                    complete = sm.hasItem(costKerningOre[selection1][selection2][i], (costQKerningOre[selection1][selection2][i] * multiplier))
                    i += 1
                    if complete == False:
                        break

                if complete == False:
                    sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
                else:
                    if sm.canHold(itemsKerningOre[selection1][selection2]) == False:
                        sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                        sm.dispose()
                        return
                    else:
                        i = 0
                        while i < len(costKerningOre[selection1][selection2]):
                            sm.consumeItem(costKerningOre[selection1][selection2][i], (costQKerningOre[selection1][selection2][i] * multiplier))
                            i += 1
                        if costmesoKerningOre[selection1][selection2] > 0:
                            sm.giveMesos(-(costmesoKerningOre[selection1][selection2] * multiplier))


                        sm.giveItem(itemsKerningOre[selection1][selection2], multiplier)
                        sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
            else:
                sm.sendSayOkay("Let me know when you are ready to create something.")
            sm.dispose()




else:
    sm.sendSayOkay("I'm an uncoded NPC, I'm lost. \r\nWhere am I?")