package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.info.LarknessInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.UserLocal;

/**
 * Created on 2/10/2018.
 */
public class LarknessManager {
	private Char chr;
	private LarknessInfo darkInfo = new LarknessInfo(20040217, 0, true);
	private LarknessInfo lightInfo = new LarknessInfo(20040216, 0, false);
	private int darkGauge;
	private int lightGauge;
	private int darkFeathers;
	private int lightFeathers;
	private int unk;
	private boolean dark;

	public LarknessManager(Char chr) {
		this.chr = chr;

	}

	public LarknessInfo getDarkInfo() {
		return darkInfo;
	}

	public LarknessInfo getLightInfo() {
		return lightInfo;
	}

	public int getDarkGauge() {
		return darkGauge;
	}

	public void setDarkGauge(int darkGauge) {
		this.darkGauge = darkGauge;
	}

	public int getLightGauge() {
		return lightGauge;
	}

	public void setLightGauge(int lightGauge) {
		this.lightGauge = lightGauge;
	}

	public int getDarkFeathers() {
		return darkFeathers;
	}

	public void setDarkFeathers(int darkFeathers) {
		this.darkFeathers = darkFeathers;
	}

	public int getLightFeathers() {
		return lightFeathers;
	}

	public void setLightFeathers(int lightFeathers) {
		this.lightFeathers = lightFeathers;
	}

	public int getUnk() {
		return unk;
	}

	public void setUnk(int unk) {
		this.unk = unk;
	}

	public void encode(OutPacket outPacket) {
		outPacket.encodeInt(getDarkGauge());
		outPacket.encodeInt(getLightGauge());
		outPacket.encodeInt(getDarkFeathers());
		outPacket.encodeInt(getLightFeathers());
		outPacket.encodeInt(getUnk());
	}

	public boolean isDark() {
		return dark;
	}

	public void setDark(boolean dark) {
		this.dark = dark;
	}

	/**
	 * Adds a dark feather, up to a maximum of 5.
	 */
	private void addDarkFeather() {
		if (getDarkFeathers() < 5) {
			setDarkFeathers(getDarkFeathers() + 1);
		}
	}

	/**
	 * Adds a light feather, up to a maximum of 5.
	 */
	private void addLightFeather() {
		if (getLightFeathers() < 5) {
			setLightFeathers(getLightFeathers() + 1);
		}
	}

	/**
	 * Adds a feather to the corresponding mode.
	 *
	 * @param dark
	 * 		mode
	 */
	private void addFeather(boolean dark) {
		if (dark) {
			addDarkFeather();
		} else {
			addLightFeather();
		}
	}

	/**
	 * Adds a specified amount to the given gauge. Note: Max value of a gauge is 10000.
	 *
	 * @param amount
	 * 		The amount to add to the gauge
	 * @param dark
	 * 		Which gauge to add the amount to
	 */
	public void addGauge(int amount, boolean dark) {
		int newGauge;
		if (dark) {
			if (getDarkFeathers() < 5) {
				newGauge = getDarkGauge() + amount;
				if (newGauge >= 10000) {
					newGauge -= 10000;
					addDarkFeather();
				}
				setDarkGauge(newGauge);
			}
		} else {
			if (getLightFeathers() < 5) {
				newGauge = getLightGauge() + amount;
				if (newGauge >= 10000) {
					newGauge -= 10000;
					addLightFeather();
				}
				setLightGauge(newGauge);
			}
		}
		updateInfo();
	}

	/**
	 * Changes mode to dark if light, and vice versa. Includes decrementing feathers, and updating
	 * the client.
	 */
	public void changeMode() {
		if (isDark() && getLightFeathers() > 0) {
			setLightFeathers(getLightFeathers() - 1);
		} else if (getDarkFeathers() > 0) {
			setDarkFeathers(getDarkFeathers() - 1);
		}
		setDark(!isDark());
		updateInfo();
	}

	/**
	 * Sends a packet to update the client's larkness state
	 */
	public void updateInfo() {
		chr.write(UserLocal.incLarknessReponse(this));
	}
}
