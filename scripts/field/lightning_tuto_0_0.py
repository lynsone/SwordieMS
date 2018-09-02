# Hidden Street : Before the Final Battle (927020080)
def init():
    sm.lockInGameUI(True)
    sm.showScene("Effect.wz/Direction8.img", "lightningTutorial", "Scene1")

    sm.invokeAfterDelay(5000, "showFadeTransition", 0, 500, 1500)

    sm.invokeAfterDelay(5500, "lockInGameUI", False)
    sm.invokeAfterDelay(5500, "warp", 927020070)