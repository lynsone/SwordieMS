import time

while True:
    if sm.checkDropsinRect(4001796, 400):
        sm.spawnMob(8800003, 0, 0, False)
        sm.spawnMob(8800004, 0, 0, False)
        sm.spawnMob(8800005, 0, 0, False)
        sm.spawnMob(8800006, 0, 0, False)
        sm.spawnMob(8800007, 0, 0, False)
        sm.spawnMob(8800008, 0, 0, False)
        sm.spawnMob(8800009, 0, 0, False)
        sm.spawnMob(8800010, 0, 0, False)
        sm.spawnMob(8800002, 0, 0, False)
        break;
    time.sleep(4)