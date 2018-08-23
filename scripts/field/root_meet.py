# Root Abyss | Colossal Root  -  Quest Map where there is only the NPC, no portals to bosses.

AN_URGENT_SUMMONS = 30000 # Quest Id

def init():
    if sm.hasQuest(30000):
        sm.lockInGameUI(True)
        sm.invokeAfterDelay(25000, "lockInGameUI", False)
        sm.completeQuest(30000) # Complete Quest   [Root Abyss] An Urgent Summons


        # Falling Effect
        sm.invokeAtFixedRate(0, 1000, 5, "showFieldBackgroundEffect", "Effect/Direction11.img/effect/meet/frame0/0", 0) # frame
        sm.invokeAtFixedRate(0, 1000, 5, "showFieldBackgroundEffect", "Effect/Direction11.img/effect/meet/crash/0", 0) # Falling Image

        # Text Effect
        sm.invokeAtFixedRate(5000, 1000, 5, "showFieldBackgroundEffect", "Effect/Direction11.img/effect/meet/back/0", 0) # Black
        sm.invokeAtFixedRate(5000, 1000, 5, "showFieldBackgroundEffect", "Effect/Direction11.img/effect/meet/frame0/0", 0) # frame
        sm.showFieldEffect("Effect/Direction11.img/effect/meet/text/0", 5000) # Wake Up!!!  text

        # Mysterious Girl
        sm.invokeAtFixedRate(10000, 1000, 13, "showFieldBackgroundEffect", "Effect/Direction11.img/effect/meet/frame0/0", 0) # frame
        sm.showFieldEffect("Effect/Direction11.img/effect/meet/first/0", 10000) # Opening Eyes

        # Custom Fade Out
        sm.invokeAfterDelay(24000, "showFadeTransition", 1500, 0, 1000)


    elif sm.hasQuest(30003):
        sm.completeQuest(30003)
    sm.dispose()