field = {
	100000201 : 100000000
}

portal = {
	100000201 : 5
}

def init():
	sm.warp(field[sm.getFieldID()], portal[sm.getFieldID()])
	sm.dispose()