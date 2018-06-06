package net.swordie.ms.client.jobs;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Magician;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.enums.ReviveType;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;

import java.util.HashMap;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;


/**
 * Created on 1/2/2018.
 */
public abstract class Job {
	protected Char chr;
	protected Client c;

	public Job(Char chr) {
		this.chr = chr;
		this.c = chr.getClient();
	}

	public void handleAttack(Client c, AttackInfo attackInfo) {
		Char chr = c.getChr();
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		Skill skill = SkillData.getSkillDeepCopyById(attackInfo.skillId);
		int skillID = 0;
		SkillInfo si = null;
		boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
		byte slv = 0;
		if (skill != null) {
			si = SkillData.getSkillInfoById(skill.getSkillId());
			slv = (byte) skill.getCurrentLevel();
			skillID = skill.getSkillId();
		}

		// Recovery Rune  HP Recovery
		if(tsm.getOptByCTSAndSkill(IgnoreMobDamR, RuneStone.LIBERATE_THE_RECOVERY_RUNE) != null) {
			SkillInfo recoveryRuneInfo = SkillData.getSkillInfoById(RuneStone.LIBERATE_THE_RECOVERY_RUNE);
			byte recoveryRuneSLV = 1; //Hardcode Skill Level to 1
			int healrate = recoveryRuneInfo.getValue(dotHealHPPerSecondR, recoveryRuneSLV);
			int healing = chr.getMaxHP() / (100 / healrate);
			chr.heal(healing);
		}


		Option o1 = new Option();
		Option o2 = new Option();
		Option o3 = new Option();
		Option o4 = new Option();
		switch (skillID) {
			case RuneStone.LIBERATE_THE_DESTRUCTIVE_RUNE:
				// Attack of the Rune
				AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
				aa.setMobOrigin((byte) 0);
				aa.setPosition(chr.getPosition());
				aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
				chr.getField().spawnAffectedArea(aa);

				skill.setCurrentLevel(1);
				for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
					Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
					MobTemporaryStat mts = mob.getTemporaryStat();
					mts.createAndAddBurnedInfo(chr, skill, 1);
				}

				// Buff of the Rune
				si = SkillData.getSkillInfoById(RuneStone.LIBERATE_THE_DESTRUCTIVE_RUNE_BUFF); //Buff Info
				slv = (byte) skill.getCurrentLevel();
				o1.nReason = RuneStone.LIBERATE_THE_DESTRUCTIVE_RUNE_BUFF;
				o1.nValue = si.getValue(indieDamR, slv); //50% DamR
				o1.tStart = (int) System.currentTimeMillis();
				o1.tTerm = si.getValue(time, slv);
				tsm.putCharacterStatValue(IndieDamR, o1);

				tsm.sendSetStatPacket();
				break;
		}
	}

	public abstract void handleSkill(Client c, int skillID, byte slv, InPacket inPacket);

	/**
	 * Handles the initial part of a hit, the initial connection.packet processing.
	 *
	 * @param c
	 * 		The client
	 * @param inPacket
	 * 		The packet to be processed
	 */
	public void handleHit(Client c, InPacket inPacket) {
		inPacket.decodeInt(); // tick
		int idk1 = inPacket.decodeInt();
		byte idk2 = inPacket.decodeByte(); // -1?
		byte idk3 = inPacket.decodeByte();
		int damage = inPacket.decodeInt();
		short idk4 = inPacket.decodeShort();
		int templateID = 0;
		int mobID = 0;
		if (inPacket.getUnreadAmount() >= 8) {
			templateID = inPacket.decodeInt();
			mobID = inPacket.decodeInt();
		}
		HitInfo hitInfo = new HitInfo();
		hitInfo.HPDamage = damage;
		hitInfo.templateID = templateID;
		hitInfo.mobID = mobID;
		handleHit(c, inPacket, hitInfo);
		handleHit(c, hitInfo);
	}

	/**
	 * The final part of the hit process. Assumes the correct info (wrt buffs for example) is
	 * already in <code>hitInfo</code>.
	 *
	 * @param c
	 * 		The client
	 * @param hitInfo
	 * 		The completed hitInfo
	 */
	public void handleHit(Client c, HitInfo hitInfo) {
		Char chr = c.getChr();
		hitInfo.HPDamage = Math.max(0, hitInfo.HPDamage); // to prevent -1 (dodges) healing the player.
		int curHP = chr.getStat(Stat.hp);
		int newHP = curHP - hitInfo.HPDamage;
		if (newHP <= 0) {
			curHP = 0;
		} else {
			curHP = newHP;
		}
		Map<Stat, Object> stats = new HashMap<>();
		chr.setStat(Stat.hp, curHP);
		stats.put(Stat.hp, curHP);

		int curMP = chr.getStat(Stat.mp);
		int newMP = curMP - hitInfo.MPDamage;
		if (newMP < 0) {
			// should not happen
			curMP = 0;
		} else {
			curMP = newMP;
		}
		chr.setStat(Stat.mp, curMP);
		stats.put(Stat.mp, curMP);
		c.write(WvsContext.statChanged(stats));
		if (curHP <= 0) {
			// TODO Add more items for protecting exp and whatnot
			c.write(UserLocal.openUIOnDead(true, chr.getBuffProtectorItem() != null,
					false, false, false,
					ReviveType.NORMAL.getVal(),0));
		}
	}

	/**
	 * Handles the 'middle' part of hit processing, namely the job-specific stuff like Magic Guard,
	 * and puts this info in <code>hitInfo</code>.
	 *
	 * @param c
	 * 		The client
	 * @param inPacket
	 * 		packet to be processed
	 * @param hitInfo
	 * 		The hit info that should be altered if necessary
	 */
	public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		if (tsm.hasStat(CharacterTemporaryStat.HolyMagicShell)) {
			if (Magician.hmshits < Magician.getHolyMagicShellMaxGuards(chr)) {
				Magician.hmshits++;
			} else {
				Magician.hmshits = 0;
				tsm.removeStatsBySkill(Magician.HOLY_MAGIC_SHELL);
			}
		}
	}

	public abstract boolean isHandlerOfJob(short id);

	public SkillInfo getInfo(int skillID) {
		return SkillData.getSkillInfoById(skillID);
	}

	protected Char getChar() {
		return chr;
	}

	public abstract int getFinalAttackSkill();

	public void handleLevelUp() {
		chr.addStat(Stat.mhp, 500);
		chr.addStat(Stat.mmp, 500);
		chr.addStat(Stat.ap, 5);
		int sp = 3;
		if (chr.getLevel() > 100 && (chr.getLevel() % 10) % 3 == 0) {
			sp = 6; // double sp on levels ending in 3/6/9
		}
		chr.addSpToJobByCurrentLevel(sp);
		Map<Stat, Object> stats = new HashMap<>();
		stats.put(Stat.mhp, chr.getStat(Stat.mhp));
		stats.put(Stat.mmp, chr.getStat(Stat.mmp));
		stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
		stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
		chr.write(WvsContext.statChanged(stats));
	}

	public abstract boolean isBuff(int skillID);

	public void setCharCreationStats(Char chr) {
		CharacterStat characterStat = chr.getAvatarData().getCharacterStat();
		characterStat.setLevel(1);
		characterStat.setStr(4);
		characterStat.setDex(4);
		characterStat.setInt(4);
		characterStat.setLuk(4);
		characterStat.setHp(50);
		characterStat.setMaxHp(50);
		characterStat.setMp(50);
		characterStat.setMaxMp(50);
		characterStat.setPosMap(100000000);
	}
}
