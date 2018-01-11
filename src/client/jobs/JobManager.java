package client.jobs;

import client.Client;
import client.character.Char;
import client.character.CharacterStat;
import client.character.skills.AttackInfo;
import client.character.skills.SkillInfo;
import client.jobs.adventurer.*;
import client.jobs.cygnus.*;
import client.jobs.legend.*;
import client.jobs.nova.AngelicBuster;
import client.jobs.nova.Kaiser;
import client.jobs.resistance.*;
import client.jobs.sengoku.Hayato;
import client.jobs.sengoku.Kanna;
import connection.InPacket;
import constants.JobConstants;
import loaders.SkillData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/14/2017.
 */
public class JobManager {
    private static final Class[] jobClasses = new Class[] {
            Archer.class,
            BeastTamer.class,
            Beginner.class,
            Kinesis.class,
            Magician.class,
            PinkBean.class,
            Pirate.class,
            Thief.class,
            Warrior.class,

            BlazeWizard.class,
            DawnWarrior.class,
            Mihile.class,
            NightWalker.class,
            Noblesse.class,
            ThunderBreaker.class,
            WindArcher.class,

            Aran.class,
            Evan.class,
            Legend.class,
            Luminous.class,
            Mercedes.class,
            Phantom.class,
            Shade.class,

            AngelicBuster.class,
            Kaiser.class,

            BattleMage.class,
            Blaster.class,
            Citizen.class,
            Demon.class,
            Mechanic.class,
            WildHunter.class,
            Xenon.class,

            Hayato.class,
            Kanna.class,

            Zero.class
    };

    private short id;

    public JobConstants.JobEnum getJobEnum() {
        return JobConstants.getJobEnumById(getId());
    }
    public void setDefaultCharStatValues(CharacterStat characterStat) {
        characterStat.setLevel(1);
        characterStat.setStr(4);
        characterStat.setDex(4);
        characterStat.setInt(4);
        characterStat.setLuk(4);
        characterStat.setMaxHp(50);
        characterStat.setHp(50);
        characterStat.setMaxMp(50);
        characterStat.setMp(50);
    }

    public static void handleAtt(Client c, AttackInfo attackInfo) {
        for(Class clazz : jobClasses) {
            Job job = null;
            try {
                job = (Job) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(c.getChr().getJob())) {
                job.handleAttack(c, attackInfo);
            }
        }
    }

    public static void handleSkill(Client c, InPacket inPacket) {
        for(Class clazz : jobClasses) {
            Job job = null;
            try {
                job = (Job) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(c.getChr().getJob())) {
                inPacket.decodeInt(); // crc
                int skillID = inPacket.decodeInt();
                byte slv = inPacket.decodeByte();
                job.handleSkill(c, skillID, slv, inPacket);
            }
        }
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public static Job getJobById(short id, Char chr) {
        Job job = null;
        for(Class clazz : jobClasses) {
            try {
                job = (Job) clazz.getConstructor(Char.class).newInstance(chr);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(id)) {
                return job;
            }
        }
        return job;
    }
}
