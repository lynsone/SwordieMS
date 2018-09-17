# Mary | Monster Park (951000000)

tickArray = [
    [4001513, 4001514], # Zebra Ticket   [broken, whole]
    [4001515, 4001516], # Leopard Ticket [broken, whole]
    [4001521, 4001522]  # Tiger Ticket   [broken, whole]
]


answer = sm.sendSay("Hello! If you want to enjoy the Monster Park, then you came to the right person! "
                    "So, what can I do for you? \r\n#b"
                    "#L0#Exchange Zebra Stripe Ticket Piece#l\r\n"
                    "#L1#Exchange Leopard Stripe Ticket Piece#l\r\n"
                    "#L2#Exchange Tiger Stripe Ticket Piece#l")

amount = sm.sendAskNumber(
    "You want to exchange 10x #i"+ str(tickArray[answer][0]) +"##z "+ str(tickArray[answer][0]) +"# for 1x"
    + " #i"+ str(tickArray[answer][1]) +"##z "+ str(tickArray[answer][1]) +"#?\r\nHow many would you like "
    + "to exchange?", 1, 1, 100)

requiredAmount = amount * 10

brokenTicket = tickArray[answer][0] # Will give you the selected Ticket's broken ticket ID
wholeTicket = tickArray[answer][1] # Will give you the selected Ticket's whole ticket ID

if sm.hasItem(brokenTicket, requiredAmount): # If player has required amount of broken tickets
    if sm.canHold(wholeTicket):
        sm.sendSayOkay("Thank you!")
        sm.consumeItem(brokenTicket, requiredAmount)   # Consume broken tickets
        sm.giveItem(wholeTicket, amount)            # Give whole tickets

    else:
        sm.sendSayOkay("You do not have space in your inventory.")

else:
    sm.sendSayOkay("You do not have enough pieces. You need #b" + str(requiredAmount) + " #i" + str(brokenTicket)
                   + "# #z" + str(brokenTicket) + "# #kto exchange for #b" + str(amount) + " #i" +
                   str(wholeTicket) + "# #z" + str(wholeTicket) + "#.")