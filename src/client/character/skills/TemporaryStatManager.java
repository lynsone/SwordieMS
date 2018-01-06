package client.character.skills;

import client.character.Char;
import connection.OutPacket;
import packet.WvsContext;
import server.EventManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.stream.Collectors;

import static client.character.skills.CharacterTemporaryStat.*;

/**
 * Created on 1/3/2018.
 */
public class TemporaryStatManager {
    private Map<CharacterTemporaryStat, Option> currentStats = new HashMap<>();
    private Map<CharacterTemporaryStat, Option> newStats = new HashMap<>();
    private Map<CharacterTemporaryStat, Option> removedStats = new HashMap<>();
    private Map<CharacterTemporaryStat, Timer> timers = new HashMap<>();
    private int pvpDamage;
    private byte defenseState;
    private byte defenseAtt;
    private int[] diceInfo = new int[22];
    private List<Integer> mobZoneStates;
    private int viperEnergyCharge;
    private StopForceAtom stopForceAtom;
    private List<LarknessInfo> larknessInfos;
    private Char chr;

    public TemporaryStatManager(Char chr){
        this.chr = chr;
    }

    public void addCharacterStatValue(CharacterTemporaryStat cts, Option option) {
        option.tOption *= 1000;
        option.tTerm *= 1000;
        if(cts == CombatOrders) {
            chr.setCombatOrders(option.nOption);
        }
        getNewStats().put(cts, option);
        getCurrentStats().put(cts, option);
        if(option.tOption > 0) {
            if(getTimers().containsKey(cts)) {
                getTimers().get(cts).cancel();
            }
            Timer t = EventManager.addEvent(this, "removeStat", option.tOption, cts, true);
            getTimers().put(cts, t);
        }
    }

    public void removeStat(CharacterTemporaryStat cts, Boolean fromTimer) {
        if(cts == CombatOrders) {
            chr.setCombatOrders(0);
        }
        getRemovedStats().put(cts, getCurrentStats().get(cts));
        getCurrentStats().remove(cts);
        getChr().getClient().write(WvsContext.temporaryStatReset(this, false));
        if(!fromTimer && getTimers().containsKey(cts)) {
            getTimers().get(cts).cancel();
        } else {
            getTimers().remove(cts);
        }
    }

    public boolean hasNewStat(CharacterTemporaryStat cts) {
        return newStats.containsKey(cts);
    }

    public boolean hasStat(CharacterTemporaryStat cts) {
        return getCurrentStats().containsKey(cts);
    }

    public Option getOption(CharacterTemporaryStat cts) {
        if(hasStat(cts)) {
            return getCurrentStats().get(cts);
        }
        return new Option();
    }

    public int[] getMaskByCollection(Map<CharacterTemporaryStat, Option> map) {
        int[] res = new int[CharacterTemporaryStat.length];
        for(int i = 0; i < res.length; i++) {
            for(CharacterTemporaryStat cts : map.keySet()) {
                if(cts.getPos() == i) {
                    res[i] |= cts.getVal();
                }
            }
        }
        return res;
    }

    public int[] getTotalMask() {
        return getMaskByCollection(getCurrentStats());
    }

    public int[] getNewMask() {
        return getMaskByCollection(getNewStats());
    }

    public int[] getRemovedMask() {
        return getMaskByCollection(getRemovedStats());
    }

    public void encodeForLocal(OutPacket outPacket) {
        int[] mask = getNewMask();
        for(int i = 0; i < getNewMask().length; i++) {
            outPacket.encodeInt(mask[i]);
        }
        if (hasNewStat(STR)) {
            outPacket.encodeShort(getOption(STR).nOption);
            outPacket.encodeInt(getOption(STR).rOption);
            outPacket.encodeInt(getOption(STR).tOption);
        }
        if (hasNewStat(INT)) {
            outPacket.encodeShort(getOption(INT).nOption);
            outPacket.encodeInt(getOption(INT).rOption);
            outPacket.encodeInt(getOption(INT).tOption);
        }
        if (hasNewStat(DEX)) {
            outPacket.encodeShort(getOption(DEX).nOption);
            outPacket.encodeInt(getOption(DEX).rOption);
            outPacket.encodeInt(getOption(DEX).tOption);
        }
        if (hasNewStat(LUK)) {
            outPacket.encodeShort(getOption(LUK).nOption);
            outPacket.encodeInt(getOption(LUK).rOption);
            outPacket.encodeInt(getOption(LUK).tOption);
        }
        if (hasNewStat(PAD)) {
            outPacket.encodeShort(getOption(PAD).nOption);
            outPacket.encodeInt(getOption(PAD).rOption);
            outPacket.encodeInt(getOption(PAD).tOption);
        }
        if (hasNewStat(PDD)) {
            outPacket.encodeShort(getOption(PDD).nOption);
            outPacket.encodeInt(getOption(PDD).rOption);
            outPacket.encodeInt(getOption(PDD).tOption);
        }
        if (hasNewStat(MAD)) {
            outPacket.encodeShort(getOption(MAD).nOption);
            outPacket.encodeInt(getOption(MAD).rOption);
            outPacket.encodeInt(getOption(MAD).tOption);
        }
        if (hasNewStat(MDD)) {
            outPacket.encodeShort(getOption(MDD).nOption);
            outPacket.encodeInt(getOption(MDD).rOption);
            outPacket.encodeInt(getOption(MDD).tOption);
        }
        if (hasNewStat(ACC)) {
            outPacket.encodeShort(getOption(ACC).nOption);
            outPacket.encodeInt(getOption(ACC).rOption);
            outPacket.encodeInt(getOption(ACC).tOption);
        }
        if (hasNewStat(EVA)) {
            outPacket.encodeShort(getOption(EVA).nOption);
            outPacket.encodeInt(getOption(EVA).rOption);
            outPacket.encodeInt(getOption(EVA).tOption);
        }
        if (hasNewStat(EVAR)) {
            outPacket.encodeShort(getOption(EVAR).nOption);
            outPacket.encodeInt(getOption(EVAR).rOption);
            outPacket.encodeInt(getOption(EVAR).tOption);
        }
        if (hasNewStat(Craft)) {
            outPacket.encodeShort(getOption(Craft).nOption);
            outPacket.encodeInt(getOption(Craft).rOption);
            outPacket.encodeInt(getOption(Craft).tOption);
        }
        if (hasNewStat(Speed)) {
            outPacket.encodeShort(getOption(Speed).nOption);
            outPacket.encodeInt(getOption(Speed).rOption);
            outPacket.encodeInt(getOption(Speed).tOption);
        }
        if (hasNewStat(Jump)) {
            outPacket.encodeShort(getOption(Jump).nOption);
            outPacket.encodeInt(getOption(Jump).rOption);
            outPacket.encodeInt(getOption(Jump).tOption);
        }
        if (hasNewStat(EMHP)) {
            outPacket.encodeShort(getOption(EMHP).nOption);
            outPacket.encodeInt(getOption(EMHP).rOption);
            outPacket.encodeInt(getOption(EMHP).tOption);
        }
        if (hasNewStat(EMMP)) {
            outPacket.encodeShort(getOption(EMMP).nOption);
            outPacket.encodeInt(getOption(EMMP).rOption);
            outPacket.encodeInt(getOption(EMMP).tOption);
        }
        if (hasNewStat(EPAD)) {
            outPacket.encodeShort(getOption(EPAD).nOption);
            outPacket.encodeInt(getOption(EPAD).rOption);
            outPacket.encodeInt(getOption(EPAD).tOption);
        }
        if (hasNewStat(EMAD)) {
            outPacket.encodeShort(getOption(EMAD).nOption);
            outPacket.encodeInt(getOption(EMAD).rOption);
            outPacket.encodeInt(getOption(EMAD).tOption);
        }
        if (hasNewStat(EPDD)) {
            outPacket.encodeShort(getOption(EPDD).nOption);
            outPacket.encodeInt(getOption(EPDD).rOption);
            outPacket.encodeInt(getOption(EPDD).tOption);
        }
        if (hasNewStat(EMDD)) {
            outPacket.encodeShort(getOption(EMDD).nOption);
            outPacket.encodeInt(getOption(EMDD).rOption);
            outPacket.encodeInt(getOption(EMDD).tOption);
        }
        if (hasNewStat(MagicGuard)) {
            outPacket.encodeShort(getOption(MagicGuard).nOption);
            outPacket.encodeInt(getOption(MagicGuard).rOption);
            outPacket.encodeInt(getOption(MagicGuard).tOption);
        }
        if (hasNewStat(DarkSight)) {
            outPacket.encodeShort(getOption(DarkSight).nOption);
            outPacket.encodeInt(getOption(DarkSight).rOption);
            outPacket.encodeInt(getOption(DarkSight).tOption);
        }
        if (hasNewStat(Booster)) {
            outPacket.encodeShort(getOption(Booster).nOption);
            outPacket.encodeInt(getOption(Booster).rOption);
            outPacket.encodeInt(getOption(Booster).tOption);
        }
        if (hasNewStat(PowerGuard)) {
            outPacket.encodeShort(getOption(PowerGuard).nOption);
            outPacket.encodeInt(getOption(PowerGuard).rOption);
            outPacket.encodeInt(getOption(PowerGuard).tOption);
        }
        if (hasNewStat(Guard)) {
            outPacket.encodeShort(getOption(Guard).nOption);
            outPacket.encodeInt(getOption(Guard).rOption);
            outPacket.encodeInt(getOption(Guard).tOption);
        }
        if (hasNewStat(MaxHP)) {
            outPacket.encodeShort(getOption(MaxHP).nOption);
            outPacket.encodeInt(getOption(MaxHP).rOption);
            outPacket.encodeInt(getOption(MaxHP).tOption);
        }
        if (hasNewStat(MaxMP)) {
            outPacket.encodeShort(getOption(MaxMP).nOption);
            outPacket.encodeInt(getOption(MaxMP).rOption);
            outPacket.encodeInt(getOption(MaxMP).tOption);
        }
        if (hasNewStat(Invincible)) {
            outPacket.encodeShort(getOption(Invincible).nOption);
            outPacket.encodeInt(getOption(Invincible).rOption);
            outPacket.encodeInt(getOption(Invincible).tOption);
        }
        if (hasNewStat(SoulArrow)) {
            outPacket.encodeShort(getOption(SoulArrow).nOption);
            outPacket.encodeInt(getOption(SoulArrow).rOption);
            outPacket.encodeInt(getOption(SoulArrow).tOption);
        }
        if (hasNewStat(Stun)) {
            outPacket.encodeShort(getOption(Stun).nOption);
            outPacket.encodeInt(getOption(Stun).rOption);
            outPacket.encodeInt(getOption(Stun).tOption);
        }
        if (hasNewStat(Shock)) {
            outPacket.encodeShort(getOption(Shock).nOption);
            outPacket.encodeInt(getOption(Shock).rOption);
            outPacket.encodeInt(getOption(Shock).tOption);
        }
        if (hasNewStat(Poison)) {
            outPacket.encodeShort(getOption(Poison).nOption);
            outPacket.encodeInt(getOption(Poison).rOption);
            outPacket.encodeInt(getOption(Poison).tOption);
        }
        if (hasNewStat(Seal)) {
            outPacket.encodeShort(getOption(Seal).nOption);
            outPacket.encodeInt(getOption(Seal).rOption);
            outPacket.encodeInt(getOption(Seal).tOption);
        }
        if (hasNewStat(Darkness)) {
            outPacket.encodeShort(getOption(Darkness).nOption);
            outPacket.encodeInt(getOption(Darkness).rOption);
            outPacket.encodeInt(getOption(Darkness).tOption);
        }
        if (hasNewStat(ComboCounter)) {
            outPacket.encodeShort(getOption(ComboCounter).nOption);
            outPacket.encodeInt(getOption(ComboCounter).rOption);
            outPacket.encodeInt(getOption(ComboCounter).tOption);
        }
        if (hasNewStat(WeaponCharge)) {
            outPacket.encodeShort(getOption(WeaponCharge).nOption);
            outPacket.encodeInt(getOption(WeaponCharge).rOption);
            outPacket.encodeInt(getOption(WeaponCharge).tOption);
        }
        if (hasNewStat(ElementalCharge)) {
            outPacket.encodeShort(getOption(ElementalCharge).nOption);
            outPacket.encodeInt(getOption(ElementalCharge).rOption);
            outPacket.encodeInt(getOption(ElementalCharge).tOption);
        }
        if (hasNewStat(HolySymbol)) {
            outPacket.encodeShort(getOption(HolySymbol).nOption);
            outPacket.encodeInt(getOption(HolySymbol).rOption);
            outPacket.encodeInt(getOption(HolySymbol).tOption);
        }
        if (hasNewStat(MesoUp)) {
            outPacket.encodeShort(getOption(MesoUp).nOption);
            outPacket.encodeInt(getOption(MesoUp).rOption);
            outPacket.encodeInt(getOption(MesoUp).tOption);
        }
        if (hasNewStat(ShadowPartner)) {
            outPacket.encodeInt(getOption(ShadowPartner).nOption); // isEncode4
            outPacket.encodeInt(getOption(ShadowPartner).rOption);
            outPacket.encodeInt(getOption(ShadowPartner).tOption);
        }
        if (hasNewStat(PickPocket)) {
            outPacket.encodeShort(getOption(PickPocket).nOption);
            outPacket.encodeInt(getOption(PickPocket).rOption);
            outPacket.encodeInt(getOption(PickPocket).tOption);
        }
        if (hasNewStat(MesoGuard)) {
            outPacket.encodeShort(getOption(MesoGuard).nOption);
            outPacket.encodeInt(getOption(MesoGuard).rOption);
            outPacket.encodeInt(getOption(MesoGuard).tOption);
        }
        if (hasNewStat(Thaw)) {
            outPacket.encodeShort(getOption(Thaw).nOption);
            outPacket.encodeInt(getOption(Thaw).rOption);
            outPacket.encodeInt(getOption(Thaw).tOption);
        }
        if (hasNewStat(Weakness)) {
            outPacket.encodeShort(getOption(Weakness).nOption);
            outPacket.encodeInt(getOption(Weakness).rOption);
            outPacket.encodeInt(getOption(Weakness).tOption);
        }
        if (hasNewStat(WeaknessMdamage)) {
            outPacket.encodeShort(getOption(WeaknessMdamage).nOption);
            outPacket.encodeInt(getOption(WeaknessMdamage).rOption);
            outPacket.encodeInt(getOption(WeaknessMdamage).tOption);
        }
        if (hasNewStat(Curse)) {
            outPacket.encodeShort(getOption(Curse).nOption);
            outPacket.encodeInt(getOption(Curse).rOption);
            outPacket.encodeInt(getOption(Curse).tOption);
        }
        if (hasNewStat(Slow)) {
            outPacket.encodeShort(getOption(Slow).nOption);
            outPacket.encodeInt(getOption(Slow).rOption);
            outPacket.encodeInt(getOption(Slow).tOption);
        }
        if (hasNewStat(TimeBomb)) {
            outPacket.encodeShort(getOption(TimeBomb).nOption);
            outPacket.encodeInt(getOption(TimeBomb).rOption);
            outPacket.encodeInt(getOption(TimeBomb).tOption);
        }
        if (hasNewStat(BuffLimit)) {
            outPacket.encodeShort(getOption(BuffLimit).nOption);
            outPacket.encodeInt(getOption(BuffLimit).rOption);
            outPacket.encodeInt(getOption(BuffLimit).tOption);
        }
        if (hasNewStat(Team)) {
            outPacket.encodeShort(getOption(Team).nOption);
            outPacket.encodeInt(getOption(Team).rOption);
            outPacket.encodeInt(getOption(Team).tOption);
        }
        if (hasNewStat(DisOrder)) {
            outPacket.encodeShort(getOption(DisOrder).nOption);
            outPacket.encodeInt(getOption(DisOrder).rOption);
            outPacket.encodeInt(getOption(DisOrder).tOption);
        }
        if (hasNewStat(Thread)) {
            outPacket.encodeShort(getOption(Thread).nOption);
            outPacket.encodeInt(getOption(Thread).rOption);
            outPacket.encodeInt(getOption(Thread).tOption);
        }
        if (hasNewStat(Morph)) {
            outPacket.encodeShort(getOption(Morph).nOption);
            outPacket.encodeInt(getOption(Morph).rOption);
            outPacket.encodeInt(getOption(Morph).tOption);
        }
        if (hasNewStat(Ghost)) {
            outPacket.encodeShort(getOption(Ghost).nOption);
            outPacket.encodeInt(getOption(Ghost).rOption);
            outPacket.encodeInt(getOption(Ghost).tOption);
        }
        if (hasNewStat(Regen)) {
            outPacket.encodeShort(getOption(Regen).nOption);
            outPacket.encodeInt(getOption(Regen).rOption);
            outPacket.encodeInt(getOption(Regen).tOption);
        }
        if (hasNewStat(BasicStatUp)) {
            outPacket.encodeShort(getOption(BasicStatUp).nOption);
            outPacket.encodeInt(getOption(BasicStatUp).rOption);
            outPacket.encodeInt(getOption(BasicStatUp).tOption);
        }
        if (hasNewStat(Stance)) {
            outPacket.encodeShort(getOption(Stance).nOption);
            outPacket.encodeInt(getOption(Stance).rOption);
            outPacket.encodeInt(getOption(Stance).tOption);
        }
        if (hasNewStat(SharpEyes)) {
            outPacket.encodeShort(getOption(SharpEyes).nOption);
            outPacket.encodeInt(getOption(SharpEyes).rOption);
            outPacket.encodeInt(getOption(SharpEyes).tOption);
        }
        if (hasNewStat(ManaReflection)) {
            outPacket.encodeShort(getOption(ManaReflection).nOption);
            outPacket.encodeInt(getOption(ManaReflection).rOption);
            outPacket.encodeInt(getOption(ManaReflection).tOption);
        }
        if (hasNewStat(Attract)) {
            outPacket.encodeShort(getOption(Attract).nOption);
            outPacket.encodeInt(getOption(Attract).rOption);
            outPacket.encodeInt(getOption(Attract).tOption);
        }
        if (hasNewStat(Magnet)) {
            outPacket.encodeShort(getOption(Magnet).nOption);
            outPacket.encodeInt(getOption(Magnet).rOption);
            outPacket.encodeInt(getOption(Magnet).tOption);
        }
        if (hasNewStat(MagnetArea)) {
            outPacket.encodeInt(getOption(MagnetArea).nOption); // isEncode4
            outPacket.encodeInt(getOption(MagnetArea).rOption);
            outPacket.encodeInt(getOption(MagnetArea).tOption);
        }
        if (hasNewStat(NoBulletConsume)) {
            outPacket.encodeShort(getOption(NoBulletConsume).nOption);
            outPacket.encodeInt(getOption(NoBulletConsume).rOption);
            outPacket.encodeInt(getOption(NoBulletConsume).tOption);
        }
        if (hasNewStat(StackBuff)) {
            outPacket.encodeShort(getOption(StackBuff).nOption);
            outPacket.encodeInt(getOption(StackBuff).rOption);
            outPacket.encodeInt(getOption(StackBuff).tOption);
        }
        if (hasNewStat(Trinity)) {
            outPacket.encodeShort(getOption(Trinity).nOption);
            outPacket.encodeInt(getOption(Trinity).rOption);
            outPacket.encodeInt(getOption(Trinity).tOption);
        }
        if (hasNewStat(Infinity)) {
            outPacket.encodeShort(getOption(Infinity).nOption);
            outPacket.encodeInt(getOption(Infinity).rOption);
            outPacket.encodeInt(getOption(Infinity).tOption);
        }
        if (hasNewStat(AdvancedBless)) {
            outPacket.encodeShort(getOption(AdvancedBless).nOption);
            outPacket.encodeInt(getOption(AdvancedBless).rOption);
            outPacket.encodeInt(getOption(AdvancedBless).tOption);
        }
        if (hasNewStat(IllusionStep)) {
            outPacket.encodeShort(getOption(IllusionStep).nOption);
            outPacket.encodeInt(getOption(IllusionStep).rOption);
            outPacket.encodeInt(getOption(IllusionStep).tOption);
        }
        if (hasNewStat(Blind)) {
            outPacket.encodeShort(getOption(Blind).nOption);
            outPacket.encodeInt(getOption(Blind).rOption);
            outPacket.encodeInt(getOption(Blind).tOption);
        }
        if (hasNewStat(Concentration)) {
            outPacket.encodeShort(getOption(Concentration).nOption);
            outPacket.encodeInt(getOption(Concentration).rOption);
            outPacket.encodeInt(getOption(Concentration).tOption);
        }
        if (hasNewStat(BanMap)) {
            outPacket.encodeShort(getOption(BanMap).nOption);
            outPacket.encodeInt(getOption(BanMap).rOption);
            outPacket.encodeInt(getOption(BanMap).tOption);
        }
        if (hasNewStat(MaxLevelBuff)) {
            outPacket.encodeShort(getOption(MaxLevelBuff).nOption);
            outPacket.encodeInt(getOption(MaxLevelBuff).rOption);
            outPacket.encodeInt(getOption(MaxLevelBuff).tOption);
        }
        if (hasNewStat(Barrier)) {
            outPacket.encodeShort(getOption(Barrier).nOption);
            outPacket.encodeInt(getOption(Barrier).rOption);
            outPacket.encodeInt(getOption(Barrier).tOption);
        }
        if (hasNewStat(DojangShield)) {
            outPacket.encodeShort(getOption(DojangShield).nOption);
            outPacket.encodeInt(getOption(DojangShield).rOption);
            outPacket.encodeInt(getOption(DojangShield).tOption);
        }
        if (hasNewStat(ReverseInput)) {
            outPacket.encodeShort(getOption(ReverseInput).nOption);
            outPacket.encodeInt(getOption(ReverseInput).rOption);
            outPacket.encodeInt(getOption(ReverseInput).tOption);
        }
        if (hasNewStat(MesoUpByItem)) {
            outPacket.encodeShort(getOption(MesoUpByItem).nOption);
            outPacket.encodeInt(getOption(MesoUpByItem).rOption);
            outPacket.encodeInt(getOption(MesoUpByItem).tOption);
        }
        if (hasNewStat(ItemUpByItem)) {
            outPacket.encodeShort(getOption(ItemUpByItem).nOption);
            outPacket.encodeInt(getOption(ItemUpByItem).rOption);
            outPacket.encodeInt(getOption(ItemUpByItem).tOption);
        }
        if (hasNewStat(RespectPImmune)) {
            outPacket.encodeShort(getOption(RespectPImmune).nOption);
            outPacket.encodeInt(getOption(RespectPImmune).rOption);
            outPacket.encodeInt(getOption(RespectPImmune).tOption);
        }
        if (hasNewStat(RespectMImmune)) {
            outPacket.encodeShort(getOption(RespectMImmune).nOption);
            outPacket.encodeInt(getOption(RespectMImmune).rOption);
            outPacket.encodeInt(getOption(RespectMImmune).tOption);
        }
        if (hasNewStat(DefenseAtt)) {
            outPacket.encodeShort(getOption(DefenseAtt).nOption);
            outPacket.encodeInt(getOption(DefenseAtt).rOption);
            outPacket.encodeInt(getOption(DefenseAtt).tOption);
        }
        if (hasNewStat(DefenseState)) {
            outPacket.encodeShort(getOption(DefenseState).nOption);
            outPacket.encodeInt(getOption(DefenseState).rOption);
            outPacket.encodeInt(getOption(DefenseState).tOption);
        }
        if (hasNewStat(DojangBerserk)) {
            outPacket.encodeShort(getOption(DojangBerserk).nOption);
            outPacket.encodeInt(getOption(DojangBerserk).rOption);
            outPacket.encodeInt(getOption(DojangBerserk).tOption);
        }
        if (hasNewStat(DojangInvincible)) {
            outPacket.encodeShort(getOption(DojangInvincible).nOption);
            outPacket.encodeInt(getOption(DojangInvincible).rOption);
            outPacket.encodeInt(getOption(DojangInvincible).tOption);
        }
        if (hasNewStat(SoulMasterFinal)) {
            outPacket.encodeShort(getOption(SoulMasterFinal).nOption);
            outPacket.encodeInt(getOption(SoulMasterFinal).rOption);
            outPacket.encodeInt(getOption(SoulMasterFinal).tOption);
        }
        if (hasNewStat(WindBreakerFinal)) {
            outPacket.encodeShort(getOption(WindBreakerFinal).nOption);
            outPacket.encodeInt(getOption(WindBreakerFinal).rOption);
            outPacket.encodeInt(getOption(WindBreakerFinal).tOption);
        }
        if (hasNewStat(ElementalReset)) {
            outPacket.encodeShort(getOption(ElementalReset).nOption);
            outPacket.encodeInt(getOption(ElementalReset).rOption);
            outPacket.encodeInt(getOption(ElementalReset).tOption);
        }
        if (hasNewStat(HideAttack)) {
            outPacket.encodeShort(getOption(HideAttack).nOption);
            outPacket.encodeInt(getOption(HideAttack).rOption);
            outPacket.encodeInt(getOption(HideAttack).tOption);
        }
        if (hasNewStat(EventRate)) {
            outPacket.encodeShort(getOption(EventRate).nOption);
            outPacket.encodeInt(getOption(EventRate).rOption);
            outPacket.encodeInt(getOption(EventRate).tOption);
        }
        if (hasNewStat(ComboAbilityBuff)) {
            outPacket.encodeShort(getOption(ComboAbilityBuff).nOption);
            outPacket.encodeInt(getOption(ComboAbilityBuff).rOption);
            outPacket.encodeInt(getOption(ComboAbilityBuff).tOption);
        }
        if (hasNewStat(ComboDrain)) {
            outPacket.encodeShort(getOption(ComboDrain).nOption);
            outPacket.encodeInt(getOption(ComboDrain).rOption);
            outPacket.encodeInt(getOption(ComboDrain).tOption);
        }
        if (hasNewStat(ComboBarrier)) {
            outPacket.encodeShort(getOption(ComboBarrier).nOption);
            outPacket.encodeInt(getOption(ComboBarrier).rOption);
            outPacket.encodeInt(getOption(ComboBarrier).tOption);
        }
        if (hasNewStat(PartyBarrier)) {
            outPacket.encodeShort(getOption(PartyBarrier).nOption);
            outPacket.encodeInt(getOption(PartyBarrier).rOption);
            outPacket.encodeInt(getOption(PartyBarrier).tOption);
        }
        if (hasNewStat(BodyPressure)) {
            outPacket.encodeShort(getOption(BodyPressure).nOption);
            outPacket.encodeInt(getOption(BodyPressure).rOption);
            outPacket.encodeInt(getOption(BodyPressure).tOption);
        }
        if (hasNewStat(RepeatEffect)) {
            outPacket.encodeShort(getOption(RepeatEffect).nOption);
            outPacket.encodeInt(getOption(RepeatEffect).rOption);
            outPacket.encodeInt(getOption(RepeatEffect).tOption);
        }
        if (hasNewStat(ExpBuffRate)) {
            outPacket.encodeShort(getOption(ExpBuffRate).nOption);
            outPacket.encodeInt(getOption(ExpBuffRate).rOption);
            outPacket.encodeInt(getOption(ExpBuffRate).tOption);
        }
        if (hasNewStat(StopPortion)) {
            outPacket.encodeShort(getOption(StopPortion).nOption);
            outPacket.encodeInt(getOption(StopPortion).rOption);
            outPacket.encodeInt(getOption(StopPortion).tOption);
        }
        if (hasNewStat(StopMotion)) {
            outPacket.encodeShort(getOption(StopMotion).nOption);
            outPacket.encodeInt(getOption(StopMotion).rOption);
            outPacket.encodeInt(getOption(StopMotion).tOption);
        }
        if (hasNewStat(Fear)) {
            outPacket.encodeShort(getOption(Fear).nOption);
            outPacket.encodeInt(getOption(Fear).rOption);
            outPacket.encodeInt(getOption(Fear).tOption);
        }
        if (hasNewStat(MagicShield)) {
            outPacket.encodeShort(getOption(MagicShield).nOption);
            outPacket.encodeInt(getOption(MagicShield).rOption);
            outPacket.encodeInt(getOption(MagicShield).tOption);
        }
        if (hasNewStat(MagicResistance)) {
            outPacket.encodeShort(getOption(MagicResistance).nOption);
            outPacket.encodeInt(getOption(MagicResistance).rOption);
            outPacket.encodeInt(getOption(MagicResistance).tOption);
        }
        if (hasNewStat(SoulStone)) {
            outPacket.encodeShort(getOption(SoulStone).nOption);
            outPacket.encodeInt(getOption(SoulStone).rOption);
            outPacket.encodeInt(getOption(SoulStone).tOption);
        }
        if (hasNewStat(Flying)) {
            outPacket.encodeShort(getOption(Flying).nOption);
            outPacket.encodeInt(getOption(Flying).rOption);
            outPacket.encodeInt(getOption(Flying).tOption);
        }
        if (hasNewStat(NewFlying)) {
            outPacket.encodeShort(getOption(NewFlying).nOption);
            outPacket.encodeInt(getOption(NewFlying).rOption);
            outPacket.encodeInt(getOption(NewFlying).tOption);
        }
        if (hasNewStat(NaviFlying)) {
            outPacket.encodeInt(getOption(NaviFlying).nOption); // isEncode4
            outPacket.encodeInt(getOption(NaviFlying).rOption);
            outPacket.encodeInt(getOption(NaviFlying).tOption);
        }
        if (hasNewStat(Frozen)) {
            outPacket.encodeShort(getOption(Frozen).nOption);
            outPacket.encodeInt(getOption(Frozen).rOption);
            outPacket.encodeInt(getOption(Frozen).tOption);
        }
        if (hasNewStat(Frozen2)) {
            outPacket.encodeShort(getOption(Frozen2).nOption);
            outPacket.encodeInt(getOption(Frozen2).rOption);
            outPacket.encodeInt(getOption(Frozen2).tOption);
        }
        if (hasNewStat(Web)) {
            outPacket.encodeShort(getOption(Web).nOption);
            outPacket.encodeInt(getOption(Web).rOption);
            outPacket.encodeInt(getOption(Web).tOption);
        }
        if (hasNewStat(Enrage)) {
            outPacket.encodeShort(getOption(Enrage).nOption);
            outPacket.encodeInt(getOption(Enrage).rOption);
            outPacket.encodeInt(getOption(Enrage).tOption);
        }
        if (hasNewStat(NotDamaged)) {
            outPacket.encodeShort(getOption(NotDamaged).nOption);
            outPacket.encodeInt(getOption(NotDamaged).rOption);
            outPacket.encodeInt(getOption(NotDamaged).tOption);
        }
        if (hasNewStat(FinalCut)) {
            outPacket.encodeShort(getOption(FinalCut).nOption);
            outPacket.encodeInt(getOption(FinalCut).rOption);
            outPacket.encodeInt(getOption(FinalCut).tOption);
        }
        if (hasNewStat(HowlingAttackDamage)) {
            outPacket.encodeShort(getOption(HowlingAttackDamage).nOption);
            outPacket.encodeInt(getOption(HowlingAttackDamage).rOption);
            outPacket.encodeInt(getOption(HowlingAttackDamage).tOption);
        }
        if (hasNewStat(BeastFormDamageUp)) {
            outPacket.encodeShort(getOption(BeastFormDamageUp).nOption);
            outPacket.encodeInt(getOption(BeastFormDamageUp).rOption);
            outPacket.encodeInt(getOption(BeastFormDamageUp).tOption);
        }
        if (hasNewStat(Dance)) {
            outPacket.encodeInt(getOption(Dance).nOption); // isEncode4
            outPacket.encodeInt(getOption(Dance).rOption);
            outPacket.encodeInt(getOption(Dance).tOption);
        }
        if (hasNewStat(Cyclone)) {
            outPacket.encodeShort(getOption(Cyclone).nOption);
            outPacket.encodeInt(getOption(Cyclone).rOption);
            outPacket.encodeInt(getOption(Cyclone).tOption);
        }
        if (hasNewStat(OnCapsule)) {
            outPacket.encodeShort(getOption(OnCapsule).nOption);
            outPacket.encodeInt(getOption(OnCapsule).rOption);
            outPacket.encodeInt(getOption(OnCapsule).tOption);
        }
        if (hasNewStat(HowlingCritical)) {
            outPacket.encodeShort(getOption(HowlingCritical).nOption);
            outPacket.encodeInt(getOption(HowlingCritical).rOption);
            outPacket.encodeInt(getOption(HowlingCritical).tOption);
        }
        if (hasNewStat(HowlingMaxMP)) {
            outPacket.encodeShort(getOption(HowlingMaxMP).nOption);
            outPacket.encodeInt(getOption(HowlingMaxMP).rOption);
            outPacket.encodeInt(getOption(HowlingMaxMP).tOption);
        }
        if (hasNewStat(HowlingDefence)) {
            outPacket.encodeShort(getOption(HowlingDefence).nOption);
            outPacket.encodeInt(getOption(HowlingDefence).rOption);
            outPacket.encodeInt(getOption(HowlingDefence).tOption);
        }
        if (hasNewStat(HowlingEvasion)) {
            outPacket.encodeShort(getOption(HowlingEvasion).nOption);
            outPacket.encodeInt(getOption(HowlingEvasion).rOption);
            outPacket.encodeInt(getOption(HowlingEvasion).tOption);
        }
        if (hasNewStat(Conversion)) {
            outPacket.encodeShort(getOption(Conversion).nOption);
            outPacket.encodeInt(getOption(Conversion).rOption);
            outPacket.encodeInt(getOption(Conversion).tOption);
        }
        if (hasNewStat(Revive)) {
            outPacket.encodeShort(getOption(Revive).nOption);
            outPacket.encodeInt(getOption(Revive).rOption);
            outPacket.encodeInt(getOption(Revive).tOption);
        }
        if (hasNewStat(PinkbeanMinibeenMove)) {
            outPacket.encodeShort(getOption(PinkbeanMinibeenMove).nOption);
            outPacket.encodeInt(getOption(PinkbeanMinibeenMove).rOption);
            outPacket.encodeInt(getOption(PinkbeanMinibeenMove).tOption);
        }
        if (hasNewStat(Sneak)) {
            outPacket.encodeShort(getOption(Sneak).nOption);
            outPacket.encodeInt(getOption(Sneak).rOption);
            outPacket.encodeInt(getOption(Sneak).tOption);
        }
        if (hasNewStat(Mechanic)) {
            outPacket.encodeShort(getOption(Mechanic).nOption);
            outPacket.encodeInt(getOption(Mechanic).rOption);
            outPacket.encodeInt(getOption(Mechanic).tOption);
        }
        if (hasNewStat(DrawBack)) {
            outPacket.encodeShort(getOption(DrawBack).nOption);
            outPacket.encodeInt(getOption(DrawBack).rOption);
            outPacket.encodeInt(getOption(DrawBack).tOption);
        }
        if (hasNewStat(BeastFormMaxHP)) {
            outPacket.encodeShort(getOption(BeastFormMaxHP).nOption);
            outPacket.encodeInt(getOption(BeastFormMaxHP).rOption);
            outPacket.encodeInt(getOption(BeastFormMaxHP).tOption);
        }
        if (hasNewStat(Dice)) {
            outPacket.encodeShort(getOption(Dice).nOption);
            outPacket.encodeInt(getOption(Dice).rOption);
            outPacket.encodeInt(getOption(Dice).tOption);
        }
        if (hasNewStat(BlessingArmor)) {
            outPacket.encodeShort(getOption(BlessingArmor).nOption);
            outPacket.encodeInt(getOption(BlessingArmor).rOption);
            outPacket.encodeInt(getOption(BlessingArmor).tOption);
        }
        if (hasNewStat(BlessingArmorIncPAD)) {
            outPacket.encodeShort(getOption(BlessingArmorIncPAD).nOption);
            outPacket.encodeInt(getOption(BlessingArmorIncPAD).rOption);
            outPacket.encodeInt(getOption(BlessingArmorIncPAD).tOption);
        }
        if (hasNewStat(DamR)) {
            outPacket.encodeShort(getOption(DamR).nOption);
            outPacket.encodeInt(getOption(DamR).rOption);
            outPacket.encodeInt(getOption(DamR).tOption);
        }
        if (hasNewStat(TeleportMasteryOn)) {
            outPacket.encodeShort(getOption(TeleportMasteryOn).nOption);
            outPacket.encodeInt(getOption(TeleportMasteryOn).rOption);
            outPacket.encodeInt(getOption(TeleportMasteryOn).tOption);
        }
        if (hasNewStat(CombatOrders)) {
            outPacket.encodeShort(getOption(CombatOrders).nOption);
            outPacket.encodeInt(getOption(CombatOrders).rOption);
            outPacket.encodeInt(getOption(CombatOrders).tOption);
        }
        if (hasNewStat(Beholder)) {
            outPacket.encodeShort(getOption(Beholder).nOption);
            outPacket.encodeInt(getOption(Beholder).rOption);
            outPacket.encodeInt(getOption(Beholder).tOption);
        }
        if (hasNewStat(DispelItemOption)) {
            outPacket.encodeShort(getOption(DispelItemOption).nOption);
            outPacket.encodeInt(getOption(DispelItemOption).rOption);
            outPacket.encodeInt(getOption(DispelItemOption).tOption);
        }
        if (hasNewStat(DispelItemOptionByField)) {
            outPacket.encodeShort(getOption(DispelItemOptionByField).nOption);
            outPacket.encodeInt(getOption(DispelItemOptionByField).rOption);
            outPacket.encodeInt(getOption(DispelItemOptionByField).tOption);
        }
        if (hasNewStat(Inflation)) {
            outPacket.encodeShort(getOption(Inflation).nOption);
            outPacket.encodeInt(getOption(Inflation).rOption);
            outPacket.encodeInt(getOption(Inflation).tOption);
        }
        if (hasNewStat(OnixDivineProtection)) {
            outPacket.encodeShort(getOption(OnixDivineProtection).nOption);
            outPacket.encodeInt(getOption(OnixDivineProtection).rOption);
            outPacket.encodeInt(getOption(OnixDivineProtection).tOption);
        }
        if (hasNewStat(Bless)) {
            outPacket.encodeShort(getOption(Bless).nOption);
            outPacket.encodeInt(getOption(Bless).rOption);
            outPacket.encodeInt(getOption(Bless).tOption);
        }
        if (hasNewStat(Explosion)) {
            outPacket.encodeShort(getOption(Explosion).nOption);
            outPacket.encodeInt(getOption(Explosion).rOption);
            outPacket.encodeInt(getOption(Explosion).tOption);
        }
        if (hasNewStat(DarkTornado)) {
            outPacket.encodeShort(getOption(DarkTornado).nOption);
            outPacket.encodeInt(getOption(DarkTornado).rOption);
            outPacket.encodeInt(getOption(DarkTornado).tOption);
        }
        if (hasNewStat(IncMaxHP)) {
            outPacket.encodeShort(getOption(IncMaxHP).nOption);
            outPacket.encodeInt(getOption(IncMaxHP).rOption);
            outPacket.encodeInt(getOption(IncMaxHP).tOption);
        }
        if (hasNewStat(IncMaxMP)) {
            outPacket.encodeShort(getOption(IncMaxMP).nOption);
            outPacket.encodeInt(getOption(IncMaxMP).rOption);
            outPacket.encodeInt(getOption(IncMaxMP).tOption);
        }
        if (hasNewStat(PVPDamage)) {
            outPacket.encodeShort(getOption(PVPDamage).nOption);
            outPacket.encodeInt(getOption(PVPDamage).rOption);
            outPacket.encodeInt(getOption(PVPDamage).tOption);
        }
        if (hasNewStat(PVPDamageSkill)) {
            outPacket.encodeShort(getOption(PVPDamageSkill).nOption);
            outPacket.encodeInt(getOption(PVPDamageSkill).rOption);
            outPacket.encodeInt(getOption(PVPDamageSkill).tOption);
        }
        if (hasNewStat(PvPScoreBonus)) {
            outPacket.encodeShort(getOption(PvPScoreBonus).nOption);
            outPacket.encodeInt(getOption(PvPScoreBonus).rOption);
            outPacket.encodeInt(getOption(PvPScoreBonus).tOption);
        }
        if (hasNewStat(PvPInvincible)) {
            outPacket.encodeShort(getOption(PvPInvincible).nOption);
            outPacket.encodeInt(getOption(PvPInvincible).rOption);
            outPacket.encodeInt(getOption(PvPInvincible).tOption);
        }
        if (hasNewStat(PvPRaceEffect)) {
            outPacket.encodeShort(getOption(PvPRaceEffect).nOption);
            outPacket.encodeInt(getOption(PvPRaceEffect).rOption);
            outPacket.encodeInt(getOption(PvPRaceEffect).tOption);
        }
        if (hasNewStat(IceKnight)) {
            outPacket.encodeShort(getOption(IceKnight).nOption);
            outPacket.encodeInt(getOption(IceKnight).rOption);
            outPacket.encodeInt(getOption(IceKnight).tOption);
        }
        if (hasNewStat(HolyMagicShell)) {
            outPacket.encodeShort(getOption(HolyMagicShell).nOption);
            outPacket.encodeInt(getOption(HolyMagicShell).rOption);
            outPacket.encodeInt(getOption(HolyMagicShell).tOption);
        }
        if (hasNewStat(InfinityForce)) {
            outPacket.encodeShort(getOption(InfinityForce).nOption);
            outPacket.encodeInt(getOption(InfinityForce).rOption);
            outPacket.encodeInt(getOption(InfinityForce).tOption);
        }
        if (hasNewStat(AmplifyDamage)) {
            outPacket.encodeShort(getOption(AmplifyDamage).nOption);
            outPacket.encodeInt(getOption(AmplifyDamage).rOption);
            outPacket.encodeInt(getOption(AmplifyDamage).tOption);
        }
        if (hasNewStat(KeyDownTimeIgnore)) {
            outPacket.encodeShort(getOption(KeyDownTimeIgnore).nOption);
            outPacket.encodeInt(getOption(KeyDownTimeIgnore).rOption);
            outPacket.encodeInt(getOption(KeyDownTimeIgnore).tOption);
        }
        if (hasNewStat(MasterMagicOn)) {
            outPacket.encodeShort(getOption(MasterMagicOn).nOption);
            outPacket.encodeInt(getOption(MasterMagicOn).rOption);
            outPacket.encodeInt(getOption(MasterMagicOn).tOption);
        }
        if (hasNewStat(AsrR)) {
            outPacket.encodeShort(getOption(AsrR).nOption);
            outPacket.encodeInt(getOption(AsrR).rOption);
            outPacket.encodeInt(getOption(AsrR).tOption);
        }
        if (hasNewStat(AsrRByItem)) {
            outPacket.encodeShort(getOption(AsrRByItem).nOption);
            outPacket.encodeInt(getOption(AsrRByItem).rOption);
            outPacket.encodeInt(getOption(AsrRByItem).tOption);
        }
        if (hasNewStat(TerR)) {
            outPacket.encodeShort(getOption(TerR).nOption);
            outPacket.encodeInt(getOption(TerR).rOption);
            outPacket.encodeInt(getOption(TerR).tOption);
        }
        if (hasNewStat(DamAbsorbShield)) {
            outPacket.encodeShort(getOption(DamAbsorbShield).nOption);
            outPacket.encodeInt(getOption(DamAbsorbShield).rOption);
            outPacket.encodeInt(getOption(DamAbsorbShield).tOption);
        }
        if (hasNewStat(Roulette)) {
            outPacket.encodeShort(getOption(Roulette).nOption);
            outPacket.encodeInt(getOption(Roulette).rOption);
            outPacket.encodeInt(getOption(Roulette).tOption);
        }
        if (hasNewStat(Event)) {
            outPacket.encodeShort(getOption(Event).nOption);
            outPacket.encodeInt(getOption(Event).rOption);
            outPacket.encodeInt(getOption(Event).tOption);
        }
        if (hasNewStat(SpiritLink)) {
            outPacket.encodeInt(getOption(SpiritLink).nOption); // isEncode4
            outPacket.encodeInt(getOption(SpiritLink).rOption);
            outPacket.encodeInt(getOption(SpiritLink).tOption);
        }
        if (hasNewStat(CriticalBuff)) {
            outPacket.encodeShort(getOption(CriticalBuff).nOption);
            outPacket.encodeInt(getOption(CriticalBuff).rOption);
            outPacket.encodeInt(getOption(CriticalBuff).tOption);
        }
        if (hasNewStat(DropRate)) {
            outPacket.encodeShort(getOption(DropRate).nOption);
            outPacket.encodeInt(getOption(DropRate).rOption);
            outPacket.encodeInt(getOption(DropRate).tOption);
        }
        if (hasNewStat(PlusExpRate)) {
            outPacket.encodeShort(getOption(PlusExpRate).nOption);
            outPacket.encodeInt(getOption(PlusExpRate).rOption);
            outPacket.encodeInt(getOption(PlusExpRate).tOption);
        }
        if (hasNewStat(ItemInvincible)) {
            outPacket.encodeShort(getOption(ItemInvincible).nOption);
            outPacket.encodeInt(getOption(ItemInvincible).rOption);
            outPacket.encodeInt(getOption(ItemInvincible).tOption);
        }
        if (hasNewStat(ItemCritical)) {
            outPacket.encodeShort(getOption(ItemCritical).nOption);
            outPacket.encodeInt(getOption(ItemCritical).rOption);
            outPacket.encodeInt(getOption(ItemCritical).tOption);
        }
        if (hasNewStat(ItemEvade)) {
            outPacket.encodeShort(getOption(ItemEvade).nOption);
            outPacket.encodeInt(getOption(ItemEvade).rOption);
            outPacket.encodeInt(getOption(ItemEvade).tOption);
        }
        if (hasNewStat(Event2)) {
            outPacket.encodeShort(getOption(Event2).nOption);
            outPacket.encodeInt(getOption(Event2).rOption);
            outPacket.encodeInt(getOption(Event2).tOption);
        }
        if (hasNewStat(VampiricTouch)) {
            outPacket.encodeShort(getOption(VampiricTouch).nOption);
            outPacket.encodeInt(getOption(VampiricTouch).rOption);
            outPacket.encodeInt(getOption(VampiricTouch).tOption);
        }
        if (hasNewStat(DDR)) {
            outPacket.encodeShort(getOption(DDR).nOption);
            outPacket.encodeInt(getOption(DDR).rOption);
            outPacket.encodeInt(getOption(DDR).tOption);
        }
        if (hasNewStat(IncCriticalDamMin)) {
            outPacket.encodeShort(getOption(IncCriticalDamMin).nOption);
            outPacket.encodeInt(getOption(IncCriticalDamMin).rOption);
            outPacket.encodeInt(getOption(IncCriticalDamMin).tOption);
        }
        if (hasNewStat(IncCriticalDamMax)) {
            outPacket.encodeShort(getOption(IncCriticalDamMax).nOption);
            outPacket.encodeInt(getOption(IncCriticalDamMax).rOption);
            outPacket.encodeInt(getOption(IncCriticalDamMax).tOption);
        }
        if (hasNewStat(IncTerR)) {
            outPacket.encodeShort(getOption(IncTerR).nOption);
            outPacket.encodeInt(getOption(IncTerR).rOption);
            outPacket.encodeInt(getOption(IncTerR).tOption);
        }
        if (hasNewStat(IncAsrR)) {
            outPacket.encodeShort(getOption(IncAsrR).nOption);
            outPacket.encodeInt(getOption(IncAsrR).rOption);
            outPacket.encodeInt(getOption(IncAsrR).tOption);
        }
        if (hasNewStat(DeathMark)) {
            outPacket.encodeShort(getOption(DeathMark).nOption);
            outPacket.encodeInt(getOption(DeathMark).rOption);
            outPacket.encodeInt(getOption(DeathMark).tOption);
        }
        if (hasNewStat(PainMark)) {
            outPacket.encodeShort(getOption(PainMark).nOption);
            outPacket.encodeInt(getOption(PainMark).rOption);
            outPacket.encodeInt(getOption(PainMark).tOption);
        }
        if (hasNewStat(UsefulAdvancedBless)) {
            outPacket.encodeShort(getOption(UsefulAdvancedBless).nOption);
            outPacket.encodeInt(getOption(UsefulAdvancedBless).rOption);
            outPacket.encodeInt(getOption(UsefulAdvancedBless).tOption);
        }
        if (hasNewStat(Lapidification)) {
            outPacket.encodeShort(getOption(Lapidification).nOption);
            outPacket.encodeInt(getOption(Lapidification).rOption);
            outPacket.encodeInt(getOption(Lapidification).tOption);
        }
        if (hasNewStat(VampDeath)) {
            outPacket.encodeShort(getOption(VampDeath).nOption);
            outPacket.encodeInt(getOption(VampDeath).rOption);
            outPacket.encodeInt(getOption(VampDeath).tOption);
        }
        if (hasNewStat(VampDeathSummon)) {
            outPacket.encodeShort(getOption(VampDeathSummon).nOption);
            outPacket.encodeInt(getOption(VampDeathSummon).rOption);
            outPacket.encodeInt(getOption(VampDeathSummon).tOption);
        }
        if (hasNewStat(VenomSnake)) {
            outPacket.encodeShort(getOption(VenomSnake).nOption);
            outPacket.encodeInt(getOption(VenomSnake).rOption);
            outPacket.encodeInt(getOption(VenomSnake).tOption);
        }
        if (hasNewStat(CarnivalAttack)) {
            outPacket.encodeShort(getOption(CarnivalAttack).nOption);
            outPacket.encodeInt(getOption(CarnivalAttack).rOption);
            outPacket.encodeInt(getOption(CarnivalAttack).tOption);
        }
        if (hasNewStat(CarnivalDefence)) {
            outPacket.encodeInt(getOption(CarnivalDefence).nOption); // isEncode4
            outPacket.encodeInt(getOption(CarnivalDefence).rOption);
            outPacket.encodeInt(getOption(CarnivalDefence).tOption);
        }
        if (hasNewStat(CarnivalExp)) {
            outPacket.encodeShort(getOption(CarnivalExp).nOption);
            outPacket.encodeInt(getOption(CarnivalExp).rOption);
            outPacket.encodeInt(getOption(CarnivalExp).tOption);
        }
        if (hasNewStat(SlowAttack)) {
            outPacket.encodeShort(getOption(SlowAttack).nOption);
            outPacket.encodeInt(getOption(SlowAttack).rOption);
            outPacket.encodeInt(getOption(SlowAttack).tOption);
        }
        if (hasNewStat(PyramidEffect)) {
            outPacket.encodeShort(getOption(PyramidEffect).nOption);
            outPacket.encodeInt(getOption(PyramidEffect).rOption);
            outPacket.encodeInt(getOption(PyramidEffect).tOption);
        }
        if (hasNewStat(HollowPointBullet)) {
            outPacket.encodeShort(getOption(HollowPointBullet).nOption);
            outPacket.encodeInt(getOption(HollowPointBullet).rOption);
            outPacket.encodeInt(getOption(HollowPointBullet).tOption);
        }
        if (hasNewStat(KeyDownMoving)) {
            outPacket.encodeShort(getOption(KeyDownMoving).nOption);
            outPacket.encodeInt(getOption(KeyDownMoving).rOption);
            outPacket.encodeInt(getOption(KeyDownMoving).tOption);
        }
        if (hasNewStat(KeyDownAreaMoving)) {
            outPacket.encodeShort(getOption(KeyDownAreaMoving).nOption);
            outPacket.encodeInt(getOption(KeyDownAreaMoving).rOption);
            outPacket.encodeInt(getOption(KeyDownAreaMoving).tOption);
        }
        if (hasNewStat(CygnusElementSkill)) {
            outPacket.encodeShort(getOption(CygnusElementSkill).nOption);
            outPacket.encodeInt(getOption(CygnusElementSkill).rOption);
            outPacket.encodeInt(getOption(CygnusElementSkill).tOption);
        }
        if (hasNewStat(IgnoreTargetDEF)) {
            outPacket.encodeShort(getOption(IgnoreTargetDEF).nOption);
            outPacket.encodeInt(getOption(IgnoreTargetDEF).rOption);
            outPacket.encodeInt(getOption(IgnoreTargetDEF).tOption);
        }
        if (hasNewStat(Invisible)) {
            outPacket.encodeShort(getOption(Invisible).nOption);
            outPacket.encodeInt(getOption(Invisible).rOption);
            outPacket.encodeInt(getOption(Invisible).tOption);
        }
        if (hasNewStat(ReviveOnce)) {
            outPacket.encodeShort(getOption(ReviveOnce).nOption);
            outPacket.encodeInt(getOption(ReviveOnce).rOption);
            outPacket.encodeInt(getOption(ReviveOnce).tOption);
        }
        if (hasNewStat(AntiMagicShell)) {
            outPacket.encodeShort(getOption(AntiMagicShell).nOption);
            outPacket.encodeInt(getOption(AntiMagicShell).rOption);
            outPacket.encodeInt(getOption(AntiMagicShell).tOption);
        }
        if (hasNewStat(EnrageCr)) {
            outPacket.encodeShort(getOption(EnrageCr).nOption);
            outPacket.encodeInt(getOption(EnrageCr).rOption);
            outPacket.encodeInt(getOption(EnrageCr).tOption);
        }
        if (hasNewStat(EnrageCrDamMin)) {
            outPacket.encodeShort(getOption(EnrageCrDamMin).nOption);
            outPacket.encodeInt(getOption(EnrageCrDamMin).rOption);
            outPacket.encodeInt(getOption(EnrageCrDamMin).tOption);
        }
        if (hasNewStat(BlessOfDarkness)) {
            outPacket.encodeShort(getOption(BlessOfDarkness).nOption);
            outPacket.encodeInt(getOption(BlessOfDarkness).rOption);
            outPacket.encodeInt(getOption(BlessOfDarkness).tOption);
        }
        if (hasNewStat(LifeTidal)) {
            outPacket.encodeShort(getOption(LifeTidal).nOption);
            outPacket.encodeInt(getOption(LifeTidal).rOption);
            outPacket.encodeInt(getOption(LifeTidal).tOption);
        }
        if (hasNewStat(Judgement)) {
            outPacket.encodeShort(getOption(Judgement).nOption);
            outPacket.encodeInt(getOption(Judgement).rOption);
            outPacket.encodeInt(getOption(Judgement).tOption);
        }
        if (hasNewStat(DojangLuckyBonus)) {
            outPacket.encodeInt(getOption(DojangLuckyBonus).nOption); // isEncode4
            outPacket.encodeInt(getOption(DojangLuckyBonus).rOption);
            outPacket.encodeInt(getOption(DojangLuckyBonus).tOption);
        }
        if (hasNewStat(HitCriDamR)) {
            outPacket.encodeShort(getOption(HitCriDamR).nOption);
            outPacket.encodeInt(getOption(HitCriDamR).rOption);
            outPacket.encodeInt(getOption(HitCriDamR).tOption);
        }
        if (hasNewStat(Larkness)) {
            outPacket.encodeShort(getOption(Larkness).nOption);
            outPacket.encodeInt(getOption(Larkness).rOption);
            outPacket.encodeInt(getOption(Larkness).tOption);
        }
        if (hasNewStat(SmashStack)) {
            outPacket.encodeShort(getOption(SmashStack).nOption);
            outPacket.encodeInt(getOption(SmashStack).rOption);
            outPacket.encodeInt(getOption(SmashStack).tOption);
        }
        if (hasNewStat(ReshuffleSwitch)) {
            outPacket.encodeShort(getOption(ReshuffleSwitch).nOption);
            outPacket.encodeInt(getOption(ReshuffleSwitch).rOption);
            outPacket.encodeInt(getOption(ReshuffleSwitch).tOption);
        }
        if (hasNewStat(SpecialAction)) {
            outPacket.encodeShort(getOption(SpecialAction).nOption);
            outPacket.encodeInt(getOption(SpecialAction).rOption);
            outPacket.encodeInt(getOption(SpecialAction).tOption);
        }
        if (hasNewStat(ArcaneAim)) {
            outPacket.encodeShort(getOption(ArcaneAim).nOption);
            outPacket.encodeInt(getOption(ArcaneAim).rOption);
            outPacket.encodeInt(getOption(ArcaneAim).tOption);
        }
        if (hasNewStat(StopForceAtomInfo)) {
            outPacket.encodeShort(getOption(StopForceAtomInfo).nOption);
            outPacket.encodeInt(getOption(StopForceAtomInfo).rOption);
            outPacket.encodeInt(getOption(StopForceAtomInfo).tOption);
        }
        if (hasNewStat(SoulGazeCriDamR)) {
            outPacket.encodeInt(getOption(SoulGazeCriDamR).nOption); // isEncode4
            outPacket.encodeInt(getOption(SoulGazeCriDamR).rOption);
            outPacket.encodeInt(getOption(SoulGazeCriDamR).tOption);
        }
        if (hasNewStat(SoulRageCount)) {
            outPacket.encodeShort(getOption(SoulRageCount).nOption);
            outPacket.encodeInt(getOption(SoulRageCount).rOption);
            outPacket.encodeInt(getOption(SoulRageCount).tOption);
        }
        if (hasNewStat(PowerTransferGauge)) {
            outPacket.encodeInt(getOption(PowerTransferGauge).nOption); // isEncode4
            outPacket.encodeInt(getOption(PowerTransferGauge).rOption);
            outPacket.encodeInt(getOption(PowerTransferGauge).tOption);
        }
        if (hasNewStat(AffinitySlug)) {
            outPacket.encodeShort(getOption(AffinitySlug).nOption);
            outPacket.encodeInt(getOption(AffinitySlug).rOption);
            outPacket.encodeInt(getOption(AffinitySlug).tOption);
        }
        if (hasNewStat(SoulExalt)) {
            outPacket.encodeShort(getOption(SoulExalt).nOption);
            outPacket.encodeInt(getOption(SoulExalt).rOption);
            outPacket.encodeInt(getOption(SoulExalt).tOption);
        }
        if (hasNewStat(HiddenPieceOn)) {
            outPacket.encodeShort(getOption(HiddenPieceOn).nOption);
            outPacket.encodeInt(getOption(HiddenPieceOn).rOption);
            outPacket.encodeInt(getOption(HiddenPieceOn).tOption);
        }
        if (hasNewStat(BossShield)) {
            outPacket.encodeShort(getOption(BossShield).nOption);
            outPacket.encodeInt(getOption(BossShield).rOption);
            outPacket.encodeInt(getOption(BossShield).tOption);
        }
        if (hasNewStat(MobZoneState)) {
            outPacket.encodeShort(getOption(MobZoneState).nOption);
            outPacket.encodeInt(getOption(MobZoneState).rOption);
            outPacket.encodeInt(getOption(MobZoneState).tOption);
        }
        if (hasNewStat(GiveMeHeal)) {
            outPacket.encodeShort(getOption(GiveMeHeal).nOption);
            outPacket.encodeInt(getOption(GiveMeHeal).rOption);
            outPacket.encodeInt(getOption(GiveMeHeal).tOption);
        }
        if (hasNewStat(TouchMe)) {
            outPacket.encodeShort(getOption(TouchMe).nOption);
            outPacket.encodeInt(getOption(TouchMe).rOption);
            outPacket.encodeInt(getOption(TouchMe).tOption);
        }
        if (hasNewStat(Contagion)) {
            outPacket.encodeShort(getOption(Contagion).nOption);
            outPacket.encodeInt(getOption(Contagion).rOption);
            outPacket.encodeInt(getOption(Contagion).tOption);
        }
        if (hasNewStat(ComboUnlimited)) {
            outPacket.encodeShort(getOption(ComboUnlimited).nOption);
            outPacket.encodeInt(getOption(ComboUnlimited).rOption);
            outPacket.encodeInt(getOption(ComboUnlimited).tOption);
        }
        if (hasNewStat(IgnorePCounter)) {
            outPacket.encodeShort(getOption(IgnorePCounter).nOption);
            outPacket.encodeInt(getOption(IgnorePCounter).rOption);
            outPacket.encodeInt(getOption(IgnorePCounter).tOption);
        }
        if (hasNewStat(IgnoreAllCounter)) {
            outPacket.encodeShort(getOption(IgnoreAllCounter).nOption);
            outPacket.encodeInt(getOption(IgnoreAllCounter).rOption);
            outPacket.encodeInt(getOption(IgnoreAllCounter).tOption);
        }
        if (hasNewStat(IgnorePImmune)) {
            outPacket.encodeShort(getOption(IgnorePImmune).nOption);
            outPacket.encodeInt(getOption(IgnorePImmune).rOption);
            outPacket.encodeInt(getOption(IgnorePImmune).tOption);
        }
        if (hasNewStat(IgnoreAllImmune)) {
            outPacket.encodeShort(getOption(IgnoreAllImmune).nOption);
            outPacket.encodeInt(getOption(IgnoreAllImmune).rOption);
            outPacket.encodeInt(getOption(IgnoreAllImmune).tOption);
        }
        if (hasNewStat(FinalJudgement)) {
            outPacket.encodeShort(getOption(FinalJudgement).nOption);
            outPacket.encodeInt(getOption(FinalJudgement).rOption);
            outPacket.encodeInt(getOption(FinalJudgement).tOption);
        }
        if (hasNewStat(KnightsAura)) {
            outPacket.encodeShort(getOption(KnightsAura).nOption);
            outPacket.encodeInt(getOption(KnightsAura).rOption);
            outPacket.encodeInt(getOption(KnightsAura).tOption);
        }
        if (hasNewStat(IceAura)) {
            outPacket.encodeShort(getOption(IceAura).nOption);
            outPacket.encodeInt(getOption(IceAura).rOption);
            outPacket.encodeInt(getOption(IceAura).tOption);
        }
        if (hasNewStat(FireAura)) {
            outPacket.encodeShort(getOption(FireAura).nOption);
            outPacket.encodeInt(getOption(FireAura).rOption);
            outPacket.encodeInt(getOption(FireAura).tOption);
        }
        if (hasNewStat(VengeanceOfAngel)) {
            outPacket.encodeShort(getOption(VengeanceOfAngel).nOption);
            outPacket.encodeInt(getOption(VengeanceOfAngel).rOption);
            outPacket.encodeInt(getOption(VengeanceOfAngel).tOption);
        }
        if (hasNewStat(HeavensDoor)) {
            outPacket.encodeShort(getOption(HeavensDoor).nOption);
            outPacket.encodeInt(getOption(HeavensDoor).rOption);
            outPacket.encodeInt(getOption(HeavensDoor).tOption);
        }
        if (hasNewStat(Preparation)) {
            outPacket.encodeShort(getOption(Preparation).nOption);
            outPacket.encodeInt(getOption(Preparation).rOption);
            outPacket.encodeInt(getOption(Preparation).tOption);
        }
        if (hasNewStat(BullsEye)) {
            outPacket.encodeShort(getOption(BullsEye).nOption);
            outPacket.encodeInt(getOption(BullsEye).rOption);
            outPacket.encodeInt(getOption(BullsEye).tOption);
        }
        if (hasNewStat(IncEffectHPPotion)) {
            outPacket.encodeShort(getOption(IncEffectHPPotion).nOption);
            outPacket.encodeInt(getOption(IncEffectHPPotion).rOption);
            outPacket.encodeInt(getOption(IncEffectHPPotion).tOption);
        }
        if (hasNewStat(IncEffectMPPotion)) {
            outPacket.encodeShort(getOption(IncEffectMPPotion).nOption);
            outPacket.encodeInt(getOption(IncEffectMPPotion).rOption);
            outPacket.encodeInt(getOption(IncEffectMPPotion).tOption);
        }
        if (hasNewStat(SoulMP)) {
            outPacket.encodeShort(getOption(SoulMP).nOption);
            outPacket.encodeInt(getOption(SoulMP).rOption);
            outPacket.encodeInt(getOption(SoulMP).tOption);
        }
        if (hasNewStat(FullSoulMP)) {
            outPacket.encodeShort(getOption(FullSoulMP).nOption);
            outPacket.encodeInt(getOption(FullSoulMP).rOption);
            outPacket.encodeInt(getOption(FullSoulMP).tOption);
        }
        if (hasNewStat(SoulSkillDamageUp)) {
            outPacket.encodeShort(getOption(SoulSkillDamageUp).nOption);
            outPacket.encodeInt(getOption(SoulSkillDamageUp).rOption);
            outPacket.encodeInt(getOption(SoulSkillDamageUp).tOption);
        }
        if (hasNewStat(BleedingToxin)) {
            outPacket.encodeShort(getOption(BleedingToxin).nOption);
            outPacket.encodeInt(getOption(BleedingToxin).rOption);
            outPacket.encodeInt(getOption(BleedingToxin).tOption);
        }
        if (hasNewStat(IgnoreMobDamR)) {
            outPacket.encodeShort(getOption(IgnoreMobDamR).nOption);
            outPacket.encodeInt(getOption(IgnoreMobDamR).rOption);
            outPacket.encodeInt(getOption(IgnoreMobDamR).tOption);
        }
        if (hasNewStat(Asura)) {
            outPacket.encodeShort(getOption(Asura).nOption);
            outPacket.encodeInt(getOption(Asura).rOption);
            outPacket.encodeInt(getOption(Asura).tOption);
        }
        if (hasNewStat(FlipTheCoin)) {
            outPacket.encodeShort(getOption(FlipTheCoin).nOption);
            outPacket.encodeInt(getOption(FlipTheCoin).rOption);
            outPacket.encodeInt(getOption(FlipTheCoin).tOption);
        }
        if (hasNewStat(UnityOfPower)) {
            outPacket.encodeShort(getOption(UnityOfPower).nOption);
            outPacket.encodeInt(getOption(UnityOfPower).rOption);
            outPacket.encodeInt(getOption(UnityOfPower).tOption);
        }
        if (hasNewStat(Stimulate)) {
            outPacket.encodeShort(getOption(Stimulate).nOption);
            outPacket.encodeInt(getOption(Stimulate).rOption);
            outPacket.encodeInt(getOption(Stimulate).tOption);
        }
        if (hasNewStat(ReturnTeleport)) {
            outPacket.encodeInt(getOption(ReturnTeleport).nOption); // isEncode4
            outPacket.encodeInt(getOption(ReturnTeleport).rOption);
            outPacket.encodeInt(getOption(ReturnTeleport).tOption);
        }
        if (hasNewStat(CapDebuff)) {
            outPacket.encodeShort(getOption(CapDebuff).nOption);
            outPacket.encodeInt(getOption(CapDebuff).rOption);
            outPacket.encodeInt(getOption(CapDebuff).tOption);
        }
        if (hasNewStat(DropRIncrease)) {
            outPacket.encodeShort(getOption(DropRIncrease).nOption);
            outPacket.encodeInt(getOption(DropRIncrease).rOption);
            outPacket.encodeInt(getOption(DropRIncrease).tOption);
        }
        if (hasNewStat(IgnoreMobpdpR)) {
            outPacket.encodeShort(getOption(IgnoreMobpdpR).nOption);
            outPacket.encodeInt(getOption(IgnoreMobpdpR).rOption);
            outPacket.encodeInt(getOption(IgnoreMobpdpR).tOption);
        }
        if (hasNewStat(BdR)) {
            outPacket.encodeShort(getOption(BdR).nOption);
            outPacket.encodeInt(getOption(BdR).rOption);
            outPacket.encodeInt(getOption(BdR).tOption);
        }
        if (hasNewStat(Exceed)) {
            outPacket.encodeShort(getOption(Exceed).nOption);
            outPacket.encodeInt(getOption(Exceed).rOption);
            outPacket.encodeInt(getOption(Exceed).tOption);
        }
        if (hasNewStat(DiabolikRecovery)) {
            outPacket.encodeShort(getOption(DiabolikRecovery).nOption);
            outPacket.encodeInt(getOption(DiabolikRecovery).rOption);
            outPacket.encodeInt(getOption(DiabolikRecovery).tOption);
        }
        if (hasNewStat(FinalAttackProp)) {
            outPacket.encodeShort(getOption(FinalAttackProp).nOption);
            outPacket.encodeInt(getOption(FinalAttackProp).rOption);
            outPacket.encodeInt(getOption(FinalAttackProp).tOption);
        }
        if (hasNewStat(ExceedOverload)) {
            outPacket.encodeShort(getOption(ExceedOverload).nOption);
            outPacket.encodeInt(getOption(ExceedOverload).rOption);
            outPacket.encodeInt(getOption(ExceedOverload).tOption);
        }
        if (hasNewStat(DevilishPower)) {
            outPacket.encodeShort(getOption(DevilishPower).nOption);
            outPacket.encodeInt(getOption(DevilishPower).rOption);
            outPacket.encodeInt(getOption(DevilishPower).tOption);
        }
        if (hasNewStat(OverloadCount)) {
            outPacket.encodeShort(getOption(OverloadCount).nOption);
            outPacket.encodeInt(getOption(OverloadCount).rOption);
            outPacket.encodeInt(getOption(OverloadCount).tOption);
        }
        if (hasNewStat(BuckShot)) {
            outPacket.encodeShort(getOption(BuckShot).nOption);
            outPacket.encodeInt(getOption(BuckShot).rOption);
            outPacket.encodeInt(getOption(BuckShot).tOption);
        }
        if (hasNewStat(FireBomb)) {
            outPacket.encodeShort(getOption(FireBomb).nOption);
            outPacket.encodeInt(getOption(FireBomb).rOption);
            outPacket.encodeInt(getOption(FireBomb).tOption);
        }
        if (hasNewStat(HalfstatByDebuff)) {
            outPacket.encodeShort(getOption(HalfstatByDebuff).nOption);
            outPacket.encodeInt(getOption(HalfstatByDebuff).rOption);
            outPacket.encodeInt(getOption(HalfstatByDebuff).tOption);
        }
        if (hasNewStat(SurplusSupply)) {
            outPacket.encodeShort(getOption(SurplusSupply).nOption);
            outPacket.encodeInt(getOption(SurplusSupply).rOption);
            outPacket.encodeInt(getOption(SurplusSupply).tOption);
        }
        if (hasNewStat(SetBaseDamage)) {
            outPacket.encodeInt(getOption(SetBaseDamage).nOption); // isEncode4
            outPacket.encodeInt(getOption(SetBaseDamage).rOption);
            outPacket.encodeInt(getOption(SetBaseDamage).tOption);
        }
        if (hasNewStat(AmaranthGenerator)) {
            outPacket.encodeShort(getOption(AmaranthGenerator).nOption);
            outPacket.encodeInt(getOption(AmaranthGenerator).rOption);
            outPacket.encodeInt(getOption(AmaranthGenerator).tOption);
        }
        if (hasNewStat(StrikerHyperElectric)) {
            outPacket.encodeShort(getOption(StrikerHyperElectric).nOption);
            outPacket.encodeInt(getOption(StrikerHyperElectric).rOption);
            outPacket.encodeInt(getOption(StrikerHyperElectric).tOption);
        }
        if (hasNewStat(EventPointAbsorb)) {
            outPacket.encodeShort(getOption(EventPointAbsorb).nOption);
            outPacket.encodeInt(getOption(EventPointAbsorb).rOption);
            outPacket.encodeInt(getOption(EventPointAbsorb).tOption);
        }
        if (hasNewStat(EventAssemble)) {
            outPacket.encodeShort(getOption(EventAssemble).nOption);
            outPacket.encodeInt(getOption(EventAssemble).rOption);
            outPacket.encodeInt(getOption(EventAssemble).tOption);
        }
        if (hasNewStat(StormBringer)) {
            outPacket.encodeShort(getOption(StormBringer).nOption);
            outPacket.encodeInt(getOption(StormBringer).rOption);
            outPacket.encodeInt(getOption(StormBringer).tOption);
        }
        if (hasNewStat(ACCR)) {
            outPacket.encodeShort(getOption(ACCR).nOption);
            outPacket.encodeInt(getOption(ACCR).rOption);
            outPacket.encodeInt(getOption(ACCR).tOption);
        }
        if (hasNewStat(DEXR)) {
            outPacket.encodeShort(getOption(DEXR).nOption);
            outPacket.encodeInt(getOption(DEXR).rOption);
            outPacket.encodeInt(getOption(DEXR).tOption);
        }
        if (hasNewStat(Albatross)) {
            outPacket.encodeShort(getOption(Albatross).nOption);
            outPacket.encodeInt(getOption(Albatross).rOption);
            outPacket.encodeInt(getOption(Albatross).tOption);
        }
        if (hasNewStat(Translucence)) {
            outPacket.encodeShort(getOption(Translucence).nOption);
            outPacket.encodeInt(getOption(Translucence).rOption);
            outPacket.encodeInt(getOption(Translucence).tOption);
        }
        if (hasNewStat(PoseType)) {
            outPacket.encodeShort(getOption(PoseType).nOption);
            outPacket.encodeInt(getOption(PoseType).rOption);
            outPacket.encodeInt(getOption(PoseType).tOption);
        }
        if (hasNewStat(LightOfSpirit)) {
            outPacket.encodeShort(getOption(LightOfSpirit).nOption);
            outPacket.encodeInt(getOption(LightOfSpirit).rOption);
            outPacket.encodeInt(getOption(LightOfSpirit).tOption);
        }
        if (hasNewStat(ElementSoul)) {
            outPacket.encodeShort(getOption(ElementSoul).nOption);
            outPacket.encodeInt(getOption(ElementSoul).rOption);
            outPacket.encodeInt(getOption(ElementSoul).tOption);
        }
        if (hasNewStat(GlimmeringTime)) {
            outPacket.encodeShort(getOption(GlimmeringTime).nOption);
            outPacket.encodeInt(getOption(GlimmeringTime).rOption);
            outPacket.encodeInt(getOption(GlimmeringTime).tOption);
        }
        if (hasNewStat(Restoration)) {
            outPacket.encodeShort(getOption(Restoration).nOption);
            outPacket.encodeInt(getOption(Restoration).rOption);
            outPacket.encodeInt(getOption(Restoration).tOption);
        }
        if (hasNewStat(ComboCostInc)) {
            outPacket.encodeShort(getOption(ComboCostInc).nOption);
            outPacket.encodeInt(getOption(ComboCostInc).rOption);
            outPacket.encodeInt(getOption(ComboCostInc).tOption);
        }
        if (hasNewStat(ChargeBuff)) {
            outPacket.encodeShort(getOption(ChargeBuff).nOption);
            outPacket.encodeInt(getOption(ChargeBuff).rOption);
            outPacket.encodeInt(getOption(ChargeBuff).tOption);
        }
        if (hasNewStat(TrueSight)) {
            outPacket.encodeShort(getOption(TrueSight).nOption);
            outPacket.encodeInt(getOption(TrueSight).rOption);
            outPacket.encodeInt(getOption(TrueSight).tOption);
        }
        if (hasNewStat(CrossOverChain)) {
            outPacket.encodeShort(getOption(CrossOverChain).nOption);
            outPacket.encodeInt(getOption(CrossOverChain).rOption);
            outPacket.encodeInt(getOption(CrossOverChain).tOption);
        }
        if (hasNewStat(ChillingStep)) {
            outPacket.encodeShort(getOption(ChillingStep).nOption);
            outPacket.encodeInt(getOption(ChillingStep).rOption);
            outPacket.encodeInt(getOption(ChillingStep).tOption);
        }
        if (hasNewStat(Reincarnation)) {
            outPacket.encodeShort(getOption(Reincarnation).nOption);
            outPacket.encodeInt(getOption(Reincarnation).rOption);
            outPacket.encodeInt(getOption(Reincarnation).tOption);
        }
        if (hasNewStat(DotBasedBuff)) {
            outPacket.encodeShort(getOption(DotBasedBuff).nOption);
            outPacket.encodeInt(getOption(DotBasedBuff).rOption);
            outPacket.encodeInt(getOption(DotBasedBuff).tOption);
        }
        if (hasNewStat(BlessEnsenble)) {
            outPacket.encodeShort(getOption(BlessEnsenble).nOption);
            outPacket.encodeInt(getOption(BlessEnsenble).rOption);
            outPacket.encodeInt(getOption(BlessEnsenble).tOption);
        }
        if (hasNewStat(ExtremeArchery)) {
            outPacket.encodeShort(getOption(ExtremeArchery).nOption);
            outPacket.encodeInt(getOption(ExtremeArchery).rOption);
            outPacket.encodeInt(getOption(ExtremeArchery).tOption);
        }
        if (hasNewStat(QuiverCatridge)) {
            outPacket.encodeInt(getOption(QuiverCatridge).nOption); // isEncode4
            outPacket.encodeInt(getOption(QuiverCatridge).rOption);
            outPacket.encodeInt(getOption(QuiverCatridge).tOption);
        }
        if (hasNewStat(AdvancedQuiver)) {
            outPacket.encodeShort(getOption(AdvancedQuiver).nOption);
            outPacket.encodeInt(getOption(AdvancedQuiver).rOption);
            outPacket.encodeInt(getOption(AdvancedQuiver).tOption);
        }
        if (hasNewStat(UserControlMob)) {
            outPacket.encodeShort(getOption(UserControlMob).nOption);
            outPacket.encodeInt(getOption(UserControlMob).rOption);
            outPacket.encodeInt(getOption(UserControlMob).tOption);
        }
        if (hasNewStat(ImmuneBarrier)) {
            outPacket.encodeInt(getOption(ImmuneBarrier).nOption); // isEncode4
            outPacket.encodeInt(getOption(ImmuneBarrier).rOption);
            outPacket.encodeInt(getOption(ImmuneBarrier).tOption);
        }
        if (hasNewStat(ArmorPiercing)) {
            outPacket.encodeShort(getOption(ArmorPiercing).nOption);
            outPacket.encodeInt(getOption(ArmorPiercing).rOption);
            outPacket.encodeInt(getOption(ArmorPiercing).tOption);
        }
        if (hasNewStat(ZeroAuraStr)) {
            outPacket.encodeShort(getOption(ZeroAuraStr).nOption);
            outPacket.encodeInt(getOption(ZeroAuraStr).rOption);
            outPacket.encodeInt(getOption(ZeroAuraStr).tOption);
        }
        if (hasNewStat(ZeroAuraSpd)) {
            outPacket.encodeShort(getOption(ZeroAuraSpd).nOption);
            outPacket.encodeInt(getOption(ZeroAuraSpd).rOption);
            outPacket.encodeInt(getOption(ZeroAuraSpd).tOption);
        }
        if (hasNewStat(CriticalGrowing)) {
            outPacket.encodeShort(getOption(CriticalGrowing).nOption);
            outPacket.encodeInt(getOption(CriticalGrowing).rOption);
            outPacket.encodeInt(getOption(CriticalGrowing).tOption);
        }
        if (hasNewStat(QuickDraw)) {
            outPacket.encodeShort(getOption(QuickDraw).nOption);
            outPacket.encodeInt(getOption(QuickDraw).rOption);
            outPacket.encodeInt(getOption(QuickDraw).tOption);
        }
        if (hasNewStat(BowMasterConcentration)) {
            outPacket.encodeShort(getOption(BowMasterConcentration).nOption);
            outPacket.encodeInt(getOption(BowMasterConcentration).rOption);
            outPacket.encodeInt(getOption(BowMasterConcentration).tOption);
        }
        if (hasNewStat(TimeFastABuff)) {
            outPacket.encodeShort(getOption(TimeFastABuff).nOption);
            outPacket.encodeInt(getOption(TimeFastABuff).rOption);
            outPacket.encodeInt(getOption(TimeFastABuff).tOption);
        }
        if (hasNewStat(TimeFastBBuff)) {
            outPacket.encodeShort(getOption(TimeFastBBuff).nOption);
            outPacket.encodeInt(getOption(TimeFastBBuff).rOption);
            outPacket.encodeInt(getOption(TimeFastBBuff).tOption);
        }
        if (hasNewStat(GatherDropR)) {
            outPacket.encodeShort(getOption(GatherDropR).nOption);
            outPacket.encodeInt(getOption(GatherDropR).rOption);
            outPacket.encodeInt(getOption(GatherDropR).tOption);
        }
        if (hasNewStat(AimBox2D)) {
            outPacket.encodeShort(getOption(AimBox2D).nOption);
            outPacket.encodeInt(getOption(AimBox2D).rOption);
            outPacket.encodeInt(getOption(AimBox2D).tOption);
        }
        if (hasNewStat(CursorSniping)) {
            outPacket.encodeShort(getOption(CursorSniping).nOption);
            outPacket.encodeInt(getOption(CursorSniping).rOption);
            outPacket.encodeInt(getOption(CursorSniping).tOption);
        }
        if (hasNewStat(IncMonsterBattleCaptureRate)) {
            outPacket.encodeShort(getOption(IncMonsterBattleCaptureRate).nOption);
            outPacket.encodeInt(getOption(IncMonsterBattleCaptureRate).rOption);
            outPacket.encodeInt(getOption(IncMonsterBattleCaptureRate).tOption);
        }
        if (hasNewStat(DebuffTolerance)) {
            outPacket.encodeShort(getOption(DebuffTolerance).nOption);
            outPacket.encodeInt(getOption(DebuffTolerance).rOption);
            outPacket.encodeInt(getOption(DebuffTolerance).tOption);
        }
        if (hasNewStat(DotHealHPPerSecond)) {
            outPacket.encodeInt(getOption(DotHealHPPerSecond).nOption); // isEncode4
            outPacket.encodeInt(getOption(DotHealHPPerSecond).rOption);
            outPacket.encodeInt(getOption(DotHealHPPerSecond).tOption);
        }
        if (hasNewStat(SpiritGuard)) {
            outPacket.encodeShort(getOption(SpiritGuard).nOption);
            outPacket.encodeInt(getOption(SpiritGuard).rOption);
            outPacket.encodeInt(getOption(SpiritGuard).tOption);
        }
        if (hasNewStat(PreReviveOnce)) {
            outPacket.encodeShort(getOption(PreReviveOnce).nOption);
            outPacket.encodeInt(getOption(PreReviveOnce).rOption);
            outPacket.encodeInt(getOption(PreReviveOnce).tOption);
        }
        if (hasNewStat(SetBaseDamageByBuff)) {
            outPacket.encodeInt(getOption(SetBaseDamageByBuff).nOption); // isEncode4
            outPacket.encodeInt(getOption(SetBaseDamageByBuff).rOption);
            outPacket.encodeInt(getOption(SetBaseDamageByBuff).tOption);
        }
        if (hasNewStat(LimitMP)) {
            outPacket.encodeShort(getOption(LimitMP).nOption);
            outPacket.encodeInt(getOption(LimitMP).rOption);
            outPacket.encodeInt(getOption(LimitMP).tOption);
        }
        if (hasNewStat(ReflectDamR)) {
            outPacket.encodeShort(getOption(ReflectDamR).nOption);
            outPacket.encodeInt(getOption(ReflectDamR).rOption);
            outPacket.encodeInt(getOption(ReflectDamR).tOption);
        }
        if (hasNewStat(ComboTempest)) {
            outPacket.encodeShort(getOption(ComboTempest).nOption);
            outPacket.encodeInt(getOption(ComboTempest).rOption);
            outPacket.encodeInt(getOption(ComboTempest).tOption);
        }
        if (hasNewStat(MHPCutR)) {
            outPacket.encodeShort(getOption(MHPCutR).nOption);
            outPacket.encodeInt(getOption(MHPCutR).rOption);
            outPacket.encodeInt(getOption(MHPCutR).tOption);
        }
        if (hasNewStat(MMPCutR)) {
            outPacket.encodeShort(getOption(MMPCutR).nOption);
            outPacket.encodeInt(getOption(MMPCutR).rOption);
            outPacket.encodeInt(getOption(MMPCutR).tOption);
        }
        if (hasNewStat(SelfWeakness)) {
            outPacket.encodeShort(getOption(SelfWeakness).nOption);
            outPacket.encodeInt(getOption(SelfWeakness).rOption);
            outPacket.encodeInt(getOption(SelfWeakness).tOption);
        }
        if (hasNewStat(ElementDarkness)) {
            outPacket.encodeShort(getOption(ElementDarkness).nOption);
            outPacket.encodeInt(getOption(ElementDarkness).rOption);
            outPacket.encodeInt(getOption(ElementDarkness).tOption);
        }
        if (hasNewStat(FlareTrick)) {
            outPacket.encodeShort(getOption(FlareTrick).nOption);
            outPacket.encodeInt(getOption(FlareTrick).rOption);
            outPacket.encodeInt(getOption(FlareTrick).tOption);
        }
        if (hasNewStat(Ember)) {
            outPacket.encodeShort(getOption(Ember).nOption);
            outPacket.encodeInt(getOption(Ember).rOption);
            outPacket.encodeInt(getOption(Ember).tOption);
        }
        if (hasNewStat(Dominion)) {
            outPacket.encodeShort(getOption(Dominion).nOption);
            outPacket.encodeInt(getOption(Dominion).rOption);
            outPacket.encodeInt(getOption(Dominion).tOption);
        }
        if (hasNewStat(SiphonVitality)) {
            outPacket.encodeShort(getOption(SiphonVitality).nOption);
            outPacket.encodeInt(getOption(SiphonVitality).rOption);
            outPacket.encodeInt(getOption(SiphonVitality).tOption);
        }
        if (hasNewStat(DarknessAscension)) {
            outPacket.encodeShort(getOption(DarknessAscension).nOption);
            outPacket.encodeInt(getOption(DarknessAscension).rOption);
            outPacket.encodeInt(getOption(DarknessAscension).tOption);
        }
        if (hasNewStat(BossWaitingLinesBuff)) {
            outPacket.encodeShort(getOption(BossWaitingLinesBuff).nOption);
            outPacket.encodeInt(getOption(BossWaitingLinesBuff).rOption);
            outPacket.encodeInt(getOption(BossWaitingLinesBuff).tOption);
        }
        if (hasNewStat(DamageReduce)) {
            outPacket.encodeShort(getOption(DamageReduce).nOption);
            outPacket.encodeInt(getOption(DamageReduce).rOption);
            outPacket.encodeInt(getOption(DamageReduce).tOption);
        }
        if (hasNewStat(ShadowServant)) {
            outPacket.encodeShort(getOption(ShadowServant).nOption);
            outPacket.encodeInt(getOption(ShadowServant).rOption);
            outPacket.encodeInt(getOption(ShadowServant).tOption);
        }
        if (hasNewStat(ShadowIllusion)) {
            outPacket.encodeShort(getOption(ShadowIllusion).nOption);
            outPacket.encodeInt(getOption(ShadowIllusion).rOption);
            outPacket.encodeInt(getOption(ShadowIllusion).tOption);
        }
        if (hasNewStat(AddAttackCount)) {
            outPacket.encodeShort(getOption(AddAttackCount).nOption);
            outPacket.encodeInt(getOption(AddAttackCount).rOption);
            outPacket.encodeInt(getOption(AddAttackCount).tOption);
        }
        if (hasNewStat(ComplusionSlant)) {
            outPacket.encodeShort(getOption(ComplusionSlant).nOption);
            outPacket.encodeInt(getOption(ComplusionSlant).rOption);
            outPacket.encodeInt(getOption(ComplusionSlant).tOption);
        }
        if (hasNewStat(JaguarSummoned)) {
            outPacket.encodeShort(getOption(JaguarSummoned).nOption);
            outPacket.encodeInt(getOption(JaguarSummoned).rOption);
            outPacket.encodeInt(getOption(JaguarSummoned).tOption);
        }
        if (hasNewStat(JaguarCount)) {
            outPacket.encodeShort(getOption(JaguarCount).nOption);
            outPacket.encodeInt(getOption(JaguarCount).rOption);
            outPacket.encodeInt(getOption(JaguarCount).tOption);
        }
        if (hasNewStat(SSFShootingAttack)) {
            outPacket.encodeShort(getOption(SSFShootingAttack).nOption);
            outPacket.encodeInt(getOption(SSFShootingAttack).rOption);
            outPacket.encodeInt(getOption(SSFShootingAttack).tOption);
        }
        if (hasNewStat(DevilCry)) {
            outPacket.encodeShort(getOption(DevilCry).nOption);
            outPacket.encodeInt(getOption(DevilCry).rOption);
            outPacket.encodeInt(getOption(DevilCry).tOption);
        }
        if (hasNewStat(ShieldAttack)) {
            outPacket.encodeShort(getOption(ShieldAttack).nOption);
            outPacket.encodeInt(getOption(ShieldAttack).rOption);
            outPacket.encodeInt(getOption(ShieldAttack).tOption);
        }
        if (hasNewStat(BMageAura)) {
            outPacket.encodeShort(getOption(BMageAura).nOption);
            outPacket.encodeInt(getOption(BMageAura).rOption);
            outPacket.encodeInt(getOption(BMageAura).tOption);
        }
        if (hasNewStat(DarkLighting)) {
            outPacket.encodeShort(getOption(DarkLighting).nOption);
            outPacket.encodeInt(getOption(DarkLighting).rOption);
            outPacket.encodeInt(getOption(DarkLighting).tOption);
        }
        if (hasNewStat(AttackCountX)) {
            outPacket.encodeShort(getOption(AttackCountX).nOption);
            outPacket.encodeInt(getOption(AttackCountX).rOption);
            outPacket.encodeInt(getOption(AttackCountX).tOption);
        }
        if (hasNewStat(BMageDeath)) {
            outPacket.encodeShort(getOption(BMageDeath).nOption);
            outPacket.encodeInt(getOption(BMageDeath).rOption);
            outPacket.encodeInt(getOption(BMageDeath).tOption);
        }
        if (hasNewStat(BombTime)) {
            outPacket.encodeShort(getOption(BombTime).nOption);
            outPacket.encodeInt(getOption(BombTime).rOption);
            outPacket.encodeInt(getOption(BombTime).tOption);
        }
        if (hasNewStat(NoDebuff)) {
            outPacket.encodeShort(getOption(NoDebuff).nOption);
            outPacket.encodeInt(getOption(NoDebuff).rOption);
            outPacket.encodeInt(getOption(NoDebuff).tOption);
        }
        if (hasNewStat(XenonAegisSystem)) {
            outPacket.encodeShort(getOption(XenonAegisSystem).nOption);
            outPacket.encodeInt(getOption(XenonAegisSystem).rOption);
            outPacket.encodeInt(getOption(XenonAegisSystem).tOption);
        }
        if (hasNewStat(AngelicBursterSoulSeeker)) {
            outPacket.encodeShort(getOption(AngelicBursterSoulSeeker).nOption);
            outPacket.encodeInt(getOption(AngelicBursterSoulSeeker).rOption);
            outPacket.encodeInt(getOption(AngelicBursterSoulSeeker).tOption);
        }
        if (hasNewStat(HiddenPossession)) {
            outPacket.encodeShort(getOption(HiddenPossession).nOption);
            outPacket.encodeInt(getOption(HiddenPossession).rOption);
            outPacket.encodeInt(getOption(HiddenPossession).tOption);
        }
        if (hasNewStat(NightWalkerBat)) {
            outPacket.encodeShort(getOption(NightWalkerBat).nOption);
            outPacket.encodeInt(getOption(NightWalkerBat).rOption);
            outPacket.encodeInt(getOption(NightWalkerBat).tOption);
        }
        if (hasNewStat(NightLordMark)) {
            outPacket.encodeShort(getOption(NightLordMark).nOption);
            outPacket.encodeInt(getOption(NightLordMark).rOption);
            outPacket.encodeInt(getOption(NightLordMark).tOption);
        }
        if (hasNewStat(WizardIgnite)) {
            outPacket.encodeShort(getOption(WizardIgnite).nOption);
            outPacket.encodeInt(getOption(WizardIgnite).rOption);
            outPacket.encodeInt(getOption(WizardIgnite).tOption);
        }
        if (hasNewStat(BattlePvPHelenaMark)) {
            outPacket.encodeShort(getOption(BattlePvPHelenaMark).nOption);
            outPacket.encodeInt(getOption(BattlePvPHelenaMark).rOption);
            outPacket.encodeInt(getOption(BattlePvPHelenaMark).tOption);
        }
        if (hasNewStat(BattlePvPHelenaWindSpirit)) {
            outPacket.encodeShort(getOption(BattlePvPHelenaWindSpirit).nOption);
            outPacket.encodeInt(getOption(BattlePvPHelenaWindSpirit).rOption);
            outPacket.encodeInt(getOption(BattlePvPHelenaWindSpirit).tOption);
        }
        if (hasNewStat(BattlePvPLangEProtection)) {
            outPacket.encodeShort(getOption(BattlePvPLangEProtection).nOption);
            outPacket.encodeInt(getOption(BattlePvPLangEProtection).rOption);
            outPacket.encodeInt(getOption(BattlePvPLangEProtection).tOption);
        }
        if (hasNewStat(BattlePvPLeeMalNyunScaleUp)) {
            outPacket.encodeShort(getOption(BattlePvPLeeMalNyunScaleUp).nOption);
            outPacket.encodeInt(getOption(BattlePvPLeeMalNyunScaleUp).rOption);
            outPacket.encodeInt(getOption(BattlePvPLeeMalNyunScaleUp).tOption);
        }
        if (hasNewStat(BattlePvPRevive)) {
            outPacket.encodeShort(getOption(BattlePvPRevive).nOption);
            outPacket.encodeInt(getOption(BattlePvPRevive).rOption);
            outPacket.encodeInt(getOption(BattlePvPRevive).tOption);
        }
        if (hasNewStat(PinkbeanAttackBuff)) {
            outPacket.encodeShort(getOption(PinkbeanAttackBuff).nOption);
            outPacket.encodeInt(getOption(PinkbeanAttackBuff).rOption);
            outPacket.encodeInt(getOption(PinkbeanAttackBuff).tOption);
        }
        if (hasNewStat(RandAreaAttack)) {
            outPacket.encodeShort(getOption(RandAreaAttack).nOption);
            outPacket.encodeInt(getOption(RandAreaAttack).rOption);
            outPacket.encodeInt(getOption(RandAreaAttack).tOption);
        }
        if (hasNewStat(BattlePvPMikeShield)) {
            outPacket.encodeShort(getOption(BattlePvPMikeShield).nOption);
            outPacket.encodeInt(getOption(BattlePvPMikeShield).rOption);
            outPacket.encodeInt(getOption(BattlePvPMikeShield).tOption);
        }
        if (hasNewStat(BattlePvPMikeBugle)) {
            outPacket.encodeShort(getOption(BattlePvPMikeBugle).nOption);
            outPacket.encodeInt(getOption(BattlePvPMikeBugle).rOption);
            outPacket.encodeInt(getOption(BattlePvPMikeBugle).tOption);
        }
        if (hasNewStat(PinkbeanRelax)) {
            outPacket.encodeShort(getOption(PinkbeanRelax).nOption);
            outPacket.encodeInt(getOption(PinkbeanRelax).rOption);
            outPacket.encodeInt(getOption(PinkbeanRelax).tOption);
        }
        if (hasNewStat(PinkbeanYoYoStack)) {
            outPacket.encodeShort(getOption(PinkbeanYoYoStack).nOption);
            outPacket.encodeInt(getOption(PinkbeanYoYoStack).rOption);
            outPacket.encodeInt(getOption(PinkbeanYoYoStack).tOption);
        }
        if (hasNewStat(NextAttackEnhance)) {
            outPacket.encodeShort(getOption(NextAttackEnhance).nOption);
            outPacket.encodeInt(getOption(NextAttackEnhance).rOption);
            outPacket.encodeInt(getOption(NextAttackEnhance).tOption);
        }
        if (hasNewStat(AranBeyonderDamAbsorb)) {
            outPacket.encodeShort(getOption(AranBeyonderDamAbsorb).nOption);
            outPacket.encodeInt(getOption(AranBeyonderDamAbsorb).rOption);
            outPacket.encodeInt(getOption(AranBeyonderDamAbsorb).tOption);
        }
        if (hasNewStat(AranCombotempastOption)) {
            outPacket.encodeShort(getOption(AranCombotempastOption).nOption);
            outPacket.encodeInt(getOption(AranCombotempastOption).rOption);
            outPacket.encodeInt(getOption(AranCombotempastOption).tOption);
        }
        if (hasNewStat(NautilusFinalAttack)) {
            outPacket.encodeShort(getOption(NautilusFinalAttack).nOption);
            outPacket.encodeInt(getOption(NautilusFinalAttack).rOption);
            outPacket.encodeInt(getOption(NautilusFinalAttack).tOption);
        }
        if (hasNewStat(ViperTimeLeap)) {
            outPacket.encodeShort(getOption(ViperTimeLeap).nOption);
            outPacket.encodeInt(getOption(ViperTimeLeap).rOption);
            outPacket.encodeInt(getOption(ViperTimeLeap).tOption);
        }
        if (hasNewStat(RoyalGuardState)) {
            outPacket.encodeShort(getOption(RoyalGuardState).nOption);
            outPacket.encodeInt(getOption(RoyalGuardState).rOption);
            outPacket.encodeInt(getOption(RoyalGuardState).tOption);
        }
        if (hasNewStat(RoyalGuardPrepare)) {
            outPacket.encodeShort(getOption(RoyalGuardPrepare).nOption);
            outPacket.encodeInt(getOption(RoyalGuardPrepare).rOption);
            outPacket.encodeInt(getOption(RoyalGuardPrepare).tOption);
        }
        if (hasNewStat(MichaelSoulLink)) {
            outPacket.encodeShort(getOption(MichaelSoulLink).nOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).rOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).tOption);
        }
        if (hasNewStat(MichaelStanceLink)) {
            outPacket.encodeShort(getOption(MichaelStanceLink).nOption);
            outPacket.encodeInt(getOption(MichaelStanceLink).rOption);
            outPacket.encodeInt(getOption(MichaelStanceLink).tOption);
        }
        if (hasNewStat(TriflingWhimOnOff)) {
            outPacket.encodeShort(getOption(TriflingWhimOnOff).nOption);
            outPacket.encodeInt(getOption(TriflingWhimOnOff).rOption);
            outPacket.encodeInt(getOption(TriflingWhimOnOff).tOption);
        }
        if (hasNewStat(AddRangeOnOff)) {
            outPacket.encodeShort(getOption(AddRangeOnOff).nOption);
            outPacket.encodeInt(getOption(AddRangeOnOff).rOption);
            outPacket.encodeInt(getOption(AddRangeOnOff).tOption);
        }
        if (hasNewStat(KinesisPsychicPoint)) {
            outPacket.encodeShort(getOption(KinesisPsychicPoint).nOption);
            outPacket.encodeInt(getOption(KinesisPsychicPoint).rOption);
            outPacket.encodeInt(getOption(KinesisPsychicPoint).tOption);
        }
        if (hasNewStat(KinesisPsychicOver)) {
            outPacket.encodeShort(getOption(KinesisPsychicOver).nOption);
            outPacket.encodeInt(getOption(KinesisPsychicOver).rOption);
            outPacket.encodeInt(getOption(KinesisPsychicOver).tOption);
        }
        if (hasNewStat(KinesisPsychicShield)) {
            outPacket.encodeShort(getOption(KinesisPsychicShield).nOption);
            outPacket.encodeInt(getOption(KinesisPsychicShield).rOption);
            outPacket.encodeInt(getOption(KinesisPsychicShield).tOption);
        }
        if (hasNewStat(KinesisIncMastery)) {
            outPacket.encodeShort(getOption(KinesisIncMastery).nOption);
            outPacket.encodeInt(getOption(KinesisIncMastery).rOption);
            outPacket.encodeInt(getOption(KinesisIncMastery).tOption);
        }
        if (hasNewStat(KinesisPsychicEnergeShield)) {
            outPacket.encodeShort(getOption(KinesisPsychicEnergeShield).nOption);
            outPacket.encodeInt(getOption(KinesisPsychicEnergeShield).rOption);
            outPacket.encodeInt(getOption(KinesisPsychicEnergeShield).tOption);
        }
        if (hasNewStat(BladeStance)) {
            outPacket.encodeShort(getOption(BladeStance).nOption);
            outPacket.encodeInt(getOption(BladeStance).rOption);
            outPacket.encodeInt(getOption(BladeStance).tOption);
        }
        if (hasNewStat(DebuffActiveSkillHPCon)) {
            outPacket.encodeShort(getOption(DebuffActiveSkillHPCon).nOption);
            outPacket.encodeInt(getOption(DebuffActiveSkillHPCon).rOption);
            outPacket.encodeInt(getOption(DebuffActiveSkillHPCon).tOption);
        }
        if (hasNewStat(DebuffIncHP)) {
            outPacket.encodeShort(getOption(DebuffIncHP).nOption);
            outPacket.encodeInt(getOption(DebuffIncHP).rOption);
            outPacket.encodeInt(getOption(DebuffIncHP).tOption);
        }
        if (hasNewStat(BowMasterMortalBlow)) {
            outPacket.encodeShort(getOption(BowMasterMortalBlow).nOption);
            outPacket.encodeInt(getOption(BowMasterMortalBlow).rOption);
            outPacket.encodeInt(getOption(BowMasterMortalBlow).tOption);
        }
        if (hasNewStat(AngelicBursterSoulResonance)) {
            outPacket.encodeShort(getOption(AngelicBursterSoulResonance).nOption);
            outPacket.encodeInt(getOption(AngelicBursterSoulResonance).rOption);
            outPacket.encodeInt(getOption(AngelicBursterSoulResonance).tOption);
        }
        if (hasNewStat(Fever)) {
            outPacket.encodeShort(getOption(Fever).nOption);
            outPacket.encodeInt(getOption(Fever).rOption);
            outPacket.encodeInt(getOption(Fever).tOption);
        }
        if (hasNewStat(IgnisRore)) {
            outPacket.encodeShort(getOption(IgnisRore).nOption);
            outPacket.encodeInt(getOption(IgnisRore).rOption);
            outPacket.encodeInt(getOption(IgnisRore).tOption);
        }
        if (hasNewStat(RpSiksin)) {
            outPacket.encodeShort(getOption(RpSiksin).nOption);
            outPacket.encodeInt(getOption(RpSiksin).rOption);
            outPacket.encodeInt(getOption(RpSiksin).tOption);
        }
        if (hasNewStat(TeleportMasteryRange)) {
            outPacket.encodeShort(getOption(TeleportMasteryRange).nOption);
            outPacket.encodeInt(getOption(TeleportMasteryRange).rOption);
            outPacket.encodeInt(getOption(TeleportMasteryRange).tOption);
        }
        if (hasNewStat(FireBarrier)) {
            outPacket.encodeShort(getOption(FireBarrier).nOption);
            outPacket.encodeInt(getOption(FireBarrier).rOption);
            outPacket.encodeInt(getOption(FireBarrier).tOption);
        }
        if (hasNewStat(ChangeFoxMan)) {
            outPacket.encodeShort(getOption(ChangeFoxMan).nOption);
            outPacket.encodeInt(getOption(ChangeFoxMan).rOption);
            outPacket.encodeInt(getOption(ChangeFoxMan).tOption);
        }
        if (hasNewStat(FixCoolTime)) {
            outPacket.encodeShort(getOption(FixCoolTime).nOption);
            outPacket.encodeInt(getOption(FixCoolTime).rOption);
            outPacket.encodeInt(getOption(FixCoolTime).tOption);
        }
        if (hasNewStat(IncMobRateDummy)) {
            outPacket.encodeShort(getOption(IncMobRateDummy).nOption);
            outPacket.encodeInt(getOption(IncMobRateDummy).rOption);
            outPacket.encodeInt(getOption(IncMobRateDummy).tOption);
        }
        if (hasNewStat(AdrenalinBoost)) {
            outPacket.encodeShort(getOption(AdrenalinBoost).nOption);
            outPacket.encodeInt(getOption(AdrenalinBoost).rOption);
            outPacket.encodeInt(getOption(AdrenalinBoost).tOption);
        }
        if (hasNewStat(AranSmashSwing)) {
            outPacket.encodeShort(getOption(AranSmashSwing).nOption);
            outPacket.encodeInt(getOption(AranSmashSwing).rOption);
            outPacket.encodeInt(getOption(AranSmashSwing).tOption);
        }
        if (hasNewStat(AranDrain)) {
            outPacket.encodeShort(getOption(AranDrain).nOption);
            outPacket.encodeInt(getOption(AranDrain).rOption);
            outPacket.encodeInt(getOption(AranDrain).tOption);
        }
        if (hasNewStat(AranBoostEndHunt)) {
            outPacket.encodeShort(getOption(AranBoostEndHunt).nOption);
            outPacket.encodeInt(getOption(AranBoostEndHunt).rOption);
            outPacket.encodeInt(getOption(AranBoostEndHunt).tOption);
        }
        if (hasNewStat(HiddenHyperLinkMaximization)) {
            outPacket.encodeShort(getOption(HiddenHyperLinkMaximization).nOption);
            outPacket.encodeInt(getOption(HiddenHyperLinkMaximization).rOption);
            outPacket.encodeInt(getOption(HiddenHyperLinkMaximization).tOption);
        }
        if (hasNewStat(RWCylinder)) {
            outPacket.encodeShort(getOption(RWCylinder).nOption);
            outPacket.encodeInt(getOption(RWCylinder).rOption);
            outPacket.encodeInt(getOption(RWCylinder).tOption);
        }
        if (hasNewStat(RWCombination)) {
            outPacket.encodeShort(getOption(RWCombination).nOption);
            outPacket.encodeInt(getOption(RWCombination).rOption);
            outPacket.encodeInt(getOption(RWCombination).tOption);
        }
        if (hasNewStat(RWMagnumBlow)) {
            outPacket.encodeShort(getOption(RWMagnumBlow).nOption);
            outPacket.encodeInt(getOption(RWMagnumBlow).rOption);
            outPacket.encodeInt(getOption(RWMagnumBlow).tOption);
        }
        if (hasNewStat(RWBarrier)) {
            outPacket.encodeShort(getOption(RWBarrier).nOption);
            outPacket.encodeInt(getOption(RWBarrier).rOption);
            outPacket.encodeInt(getOption(RWBarrier).tOption);
        }
        if (hasNewStat(RWBarrierHeal)) {
            outPacket.encodeShort(getOption(RWBarrierHeal).nOption);
            outPacket.encodeInt(getOption(RWBarrierHeal).rOption);
            outPacket.encodeInt(getOption(RWBarrierHeal).tOption);
        }
        if (hasNewStat(RWMaximizeCannon)) {
            outPacket.encodeShort(getOption(RWMaximizeCannon).nOption);
            outPacket.encodeInt(getOption(RWMaximizeCannon).rOption);
            outPacket.encodeInt(getOption(RWMaximizeCannon).tOption);
        }
        if (hasNewStat(RWOverHeat)) {
            outPacket.encodeShort(getOption(RWOverHeat).nOption);
            outPacket.encodeInt(getOption(RWOverHeat).rOption);
            outPacket.encodeInt(getOption(RWOverHeat).tOption);
        }
        if (hasNewStat(RWMovingEvar)) {
            outPacket.encodeShort(getOption(RWMovingEvar).nOption);
            outPacket.encodeInt(getOption(RWMovingEvar).rOption);
            outPacket.encodeInt(getOption(RWMovingEvar).tOption);
        }
        if (hasNewStat(Stigma)) {
            outPacket.encodeShort(getOption(Stigma).nOption);
            outPacket.encodeInt(getOption(Stigma).rOption);
            outPacket.encodeInt(getOption(Stigma).tOption);
        }
        if (hasNewStat(SoulMP)) {
            outPacket.encodeInt(getOption(SoulMP).xOption);
            outPacket.encodeInt(getOption(SoulMP).rOption);
        }
        if (hasNewStat(FullSoulMP)) {
            outPacket.encodeInt(getOption(FullSoulMP).xOption);
        }
        short size = 0;
        outPacket.encodeShort(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // nKey
            outPacket.encodeByte(0); // bEnable
        }
        outPacket.encodeByte(getDefenseAtt());
        outPacket.encodeByte(getDefenseState());
        outPacket.encodeByte(getPvpDamage());
        if (hasNewStat(Dice)) {
            for (int i = 0; i < getDiceInfo().length; i++) {
                outPacket.encodeInt(diceInfo[i]);
            }
        }
        if (hasNewStat(KillingPoint)) {
            outPacket.encodeByte(getOption(KillingPoint).nOption);
        }
        if (hasNewStat(PinkbeanRollingGrade)) {
            outPacket.encodeByte(getOption(PinkbeanRollingGrade).nOption);
        }
        if (hasNewStat(Judgement)) {
            outPacket.encodeByte(getOption(Judgement).xOption);
        }
        if (hasNewStat(StackBuff)) {
            outPacket.encodeByte(getOption(StackBuff).mOption);
        }
        if (hasNewStat(Trinity)) {
            outPacket.encodeByte(getOption(Trinity).mOption);
        }
        if (hasNewStat(ElementalCharge)) {
            outPacket.encodeByte(getOption(ElementalCharge).mOption);
            outPacket.encodeShort(getOption(ElementalCharge).wOption);
            outPacket.encodeByte(getOption(ElementalCharge).uOption);
            outPacket.encodeByte(getOption(ElementalCharge).zOption);
        }
        if (hasNewStat(LifeTidal)) {
            outPacket.encodeInt(getOption(LifeTidal).mOption);
        }
        if (hasNewStat(AntiMagicShell)) {
            outPacket.encodeByte(getOption(AntiMagicShell).bOption);
        }
        if (hasNewStat(Larkness)) {
            // Should be of size 2!
            if (getLarknessInfos().size() != 2) {
                System.err.println("[ERROR] - Larkness infos' size is not 2!");
            }
            for (LarknessInfo li : getLarknessInfos()) {
                li.encode(outPacket);
            }
        }
        if (hasNewStat(IgnoreTargetDEF)) {
            outPacket.encodeInt(getOption(IgnoreTargetDEF).mOption);
        }
        if (hasNewStat(StopForceAtomInfo)) {
            getStopForceAtom().encode(outPacket);
        }
        if (hasNewStat(SmashStack)) {
            outPacket.encodeInt(getOption(SmashStack).xOption);
        }
        if (hasNewStat(MobZoneState)) {
            for (int zoneState : getMobZoneStates()) {
                outPacket.encodeInt(zoneState);
            }
            outPacket.encodeInt(0); // notify end
        }
        if (hasNewStat(Slow)) {
            outPacket.encodeByte(getOption(Slow).bOption);
        }
        if (hasNewStat(IceAura)) {
            outPacket.encodeByte(getOption(IceAura).bOption);
        }
        if (hasNewStat(KnightsAura)) {
            outPacket.encodeByte(getOption(KnightsAura).bOption);
        }
        if (hasNewStat(IgnoreMobpdpR)) {
            outPacket.encodeByte(getOption(IgnoreMobpdpR).bOption);
        }
        if (hasNewStat(BdR)) {
            outPacket.encodeByte(getOption(BdR).bOption);
        }
        if (hasNewStat(DropRIncrease)) {
            outPacket.encodeInt(getOption(DropRIncrease).xOption);
            outPacket.encodeByte(getOption(DropRIncrease).bOption);
        }
        if (hasNewStat(PoseType)) {
            outPacket.encodeByte(getOption(PoseType).bOption);
        }
        if (hasNewStat(Beholder)) {
            outPacket.encodeInt(getOption(Beholder).sOption);
            outPacket.encodeInt(getOption(Beholder).ssOption);
        }
        if (hasNewStat(CrossOverChain)) {
            outPacket.encodeInt(getOption(CrossOverChain).xOption);
        }
        if (hasNewStat(Reincarnation)) {
            outPacket.encodeInt(getOption(Reincarnation).xOption);
        }
        if (hasNewStat(ExtremeArchery)) {
            outPacket.encodeInt(getOption(ExtremeArchery).bOption);
            outPacket.encodeInt(getOption(ExtremeArchery).xOption);
        }
        if (hasNewStat(QuiverCatridge)) {
            outPacket.encodeInt(getOption(QuiverCatridge).xOption);
        }
        if (hasNewStat(ImmuneBarrier)) {
            outPacket.encodeInt(getOption(ImmuneBarrier).xOption);
        }
        if (hasNewStat(ZeroAuraStr)) {
            outPacket.encodeByte(getOption(ZeroAuraStr).bOption);
        }
        if (hasNewStat(ZeroAuraSpd)) {
            outPacket.encodeByte(getOption(ZeroAuraSpd).bOption);
        }
        if (hasNewStat(ArmorPiercing)) {
            outPacket.encodeInt(getOption(ArmorPiercing).bOption);
        }
        if (hasNewStat(SharpEyes)) {
            outPacket.encodeInt(getOption(SharpEyes).mOption);
        }
        if (hasNewStat(AdvancedBless)) {
            outPacket.encodeInt(getOption(AdvancedBless).xOption);
        }
        if (hasNewStat(DotHealHPPerSecond)) {
            outPacket.encodeInt(getOption(DotHealHPPerSecond).xOption);
        }
        if (hasNewStat(SpiritGuard)) {
            outPacket.encodeInt(getOption(SpiritGuard).nOption);
        }
        if (hasNewStat(KnockBack)) {
            outPacket.encodeInt(getOption(KnockBack).nOption);
            outPacket.encodeInt(getOption(KnockBack).bOption);
        }
        if (hasNewStat(ShieldAttack)) {
            outPacket.encodeInt(getOption(ShieldAttack).xOption);
        }
        if (hasNewStat(SSFShootingAttack)) {
            outPacket.encodeInt(getOption(SSFShootingAttack).xOption);
        }
        if (hasNewStat(BMageAura)) {
            outPacket.encodeInt(getOption(BMageAura).xOption);
            outPacket.encodeByte(getOption(BMageAura).bOption);
        }
        if (hasNewStat(BattlePvPHelenaMark)) {
            outPacket.encodeInt(getOption(BattlePvPHelenaMark).cOption);
        }
        if (hasNewStat(PinkbeanAttackBuff)) {
            outPacket.encodeInt(getOption(PinkbeanAttackBuff).bOption);
        }
        if (hasNewStat(RoyalGuardState)) {
            outPacket.encodeInt(getOption(RoyalGuardState).bOption);
            outPacket.encodeInt(getOption(RoyalGuardState).xOption);
        }
        if (hasNewStat(MichaelSoulLink)) {
            outPacket.encodeInt(getOption(MichaelSoulLink).xOption);
            outPacket.encodeByte(getOption(MichaelSoulLink).bOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).cOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).yOption);
        }
        if (hasNewStat(AdrenalinBoost)) {
            outPacket.encodeByte(getOption(AdrenalinBoost).cOption);
        }
        if (hasNewStat(RWCylinder)) {
            outPacket.encodeByte(getOption(RWCylinder).bOption);
            outPacket.encodeShort(getOption(RWCylinder).cOption);
        }
        if (hasNewStat(RWMagnumBlow)) {
            outPacket.encodeShort(getOption(RWMagnumBlow).bOption);
            outPacket.encodeByte(getOption(RWMagnumBlow).xOption);
        }
        outPacket.encodeInt(getViperEnergyCharge());
        if (hasNewStat(BladeStance)) {
            outPacket.encodeInt(getOption(BladeStance).xOption);
        }
        if (hasNewStat(DarkSight)) {
            outPacket.encodeInt(getOption(DarkSight).cOption);
        }
        if (hasNewStat(Stigma)) {
            outPacket.encodeInt(getOption(Stigma).bOption);
        }
        encodeIndieTempStat(outPacket);
        if (hasNewStat(UsingScouter)) {
            outPacket.encodeShort(getOption(UsingScouter).nOption);
        }
        getNewStats().clear();
    }

    private void encodeIndieTempStat(OutPacket outPacket) {
        Map<CharacterTemporaryStat, Option> stats = getCurrentStats().entrySet().stream()
                .filter(stat -> stat.getKey().isIndie())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for(Map.Entry<CharacterTemporaryStat, Option> stat : stats.entrySet()) {
            Option option = stat.getValue();
            outPacket.encodeInt(1); // the size of the array.
            outPacket.encodeInt(option.nReason);
            outPacket.encodeInt(option.nValue);
            outPacket.encodeInt(option.nKey); // nKey
            outPacket.encodeInt(1);
            outPacket.encodeInt(option.tTerm); // tTerm
            outPacket.encodeInt(0); // size
            // pw.writeInt(0); // nMValueKey
            // pw.writeInt(0); // nMValue
        }
    }

    public boolean hasNewMovingEffectingStat() {
        return getNewStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public boolean hasMovingEffectingStat() {
        return getCurrentStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public boolean hasRemovedMovingEffectingStat() {
        return getRemovedStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public Map<CharacterTemporaryStat, Option> getCurrentStats() {
        return currentStats;
    }

    public Map<CharacterTemporaryStat, Option> getNewStats() {
        return newStats;
    }

    public Map<CharacterTemporaryStat, Option> getRemovedStats() {
        return removedStats;
    }

    public int getPvpDamage() {
        return pvpDamage;
    }

    public void setPvpDamage(int pvpDamage) {
        this.pvpDamage = pvpDamage;
    }

    public byte getDefenseState() {
        return defenseState;
    }

    public void setDefenseState(byte defenseState) {
        this.defenseState = defenseState;
    }

    public byte getDefenseAtt() {
        return defenseAtt;
    }

    public void setDefenseAtt(byte defenseAtt) {
        this.defenseAtt = defenseAtt;
    }

    public int[] getDiceInfo() {
        return diceInfo;
    }

    public void setDiceInfo(int[] diceInfo) {
        this.diceInfo = diceInfo;
    }

    public List<Integer> getMobZoneStates() {
        return mobZoneStates;
    }

    public void setMobZoneStates(List<Integer> mobZoneStates) {
        this.mobZoneStates = mobZoneStates;
    }

    public int getViperEnergyCharge() {
        return viperEnergyCharge;
    }

    public void setViperEnergyCharge(int viperEnergyCharge) {
        this.viperEnergyCharge = viperEnergyCharge;
    }

    public StopForceAtom getStopForceAtom() {
        return stopForceAtom;
    }

    public void setStopForceAtom(StopForceAtom stopForceAtom) {
        this.stopForceAtom = stopForceAtom;
    }

    public List<LarknessInfo> getLarknessInfos() {
        return larknessInfos;
    }

    public void setLarknessInfos(List<LarknessInfo> larknessInfos) {
        this.larknessInfos = larknessInfos;
    }

    public Char getChr() {
        return chr;
    }

    public Map<CharacterTemporaryStat, Timer> getTimers() {
        return timers;
    }
}
