FERDI = 2159311

sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendNext("#bThere's much to consider...")
sm.sendSay("#bBut the fact remains that my strength has not yet fully returned.")
sm.sendSay("#bHow vexing, to have lost the power I wielded against the Black Mage himself.")
sm.sendSay("#bPerhaps it is a sign that I was meant for another path...")
sm.sendSay("#b...Why am I spouting such nonsense?")
sm.sendSay("#bI am #h0#. The Fury that once served the Black Mage now seeks his total destruction.")

sm.setSpeakerID(FERDI)
if sm.sendAskSelectMenu(1, 0) == 1:
    # Demon Slayer
    sm.giveSkill(30010112, -1)
    sm.setJob(3100)
    sm.showEffect("Effect/BasicEff.img/JobChangedDemon", 0, 0, 0, -2, -2, False, 0)
    sm.playSound("tutoCommon/JobChanged", 100)
    sm.setSTR(35)
    sm.setDEX(4)
    sm.setAP(23)
    sm.addMaxHP(50)
    # Max MP setting to 10
    sm.addSP(5, True)
    sm.createQuestWithQRValue(23977, "1", False)
    sm.giveSkill(30010111, 1)
    sm.giveAndEquip(1322122)
    sm.giveAndEquip(1099000)
    sm.giveItem(1142341)
    sm.giveItem(2001527, 30)
else:
    # Demon Avanger
    sm.giveSkill(30010112, -1)
    sm.setJob(3101)
    sm.showEffect("Effect/BasicEff.img/JobChangedDemon", 0, 0, 0, -2, -2, False, 0)
    sm.playSound("tutoCommon/JobChanged", 100)
    sm.setSTR(4)
    sm.setDEX(4)
    sm.setAP(23)
    sm.addMaxHP(1190)
    sm.addSP(2, True)
    sm.createQuestWithQRValue(23977, "1", False)
    sm.giveSkill(30010241, 0)
    sm.giveSkill(30010242, 1)
    sm.giveSkill(30010230, 1)
    sm.giveSkill(30010231, 1)
    sm.giveSkill(30010232, 1)
    sm.giveSkill(31011000, 1, 20)
    sm.giveSkill(31011001, 1, 20)
    sm.giveSkill(31010002, 1, 10)
    sm.giveAndEquip(1232001)
    sm.giveAndEquip(1099006)
    sm.giveItem(1142553)
    sm.giveItem(2001527, 30)

sm.setPlayerAsSpeaker()
sm.sendNext("#bMy strength as a Commander will serve me on my dark quest!")
sm.sendSay("#bI should check my Use tab first by pressing #rI#b.")

sm.lockInGameUI(False)