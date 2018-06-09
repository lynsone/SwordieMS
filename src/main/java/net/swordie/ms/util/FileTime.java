package net.swordie.ms.util;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "filetimes")
public class FileTime implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int lowDateTime;
	private int highDateTime;

	public long getLongValue() {
		return getLowDateTime() + (long) getHighDateTime() << 32;
	}

	public enum Type {
		// Mushy
		MAX_TIME(150842304000000000L),
		ZERO_TIME(94354848000000000L),
		PERMANENT(150841440000000000L),
		FT_UT_OFFSET(116444592000000000L),
		QUEST_TIME(27111908);

		private long val;

		Type(long val) {
			this.val = val;
		}

		public long getVal() {
			return val;
		}
	}

	public enum FileTimeType {
		DATE_19000101_444(-35635200, 21968699),
		DATE_20790101_424(-1157267456, 35120710),;

		private int low;
		private int high;

		FileTimeType(int low, int high) {
			this.low = low;
			this.high = high;
		}

		public FileTime getVal() {
			return new FileTime(low, high);
		}
	}

	public FileTime(int lowDateTime, int highDateTime) {
		this.lowDateTime = lowDateTime;
		this.highDateTime = highDateTime;
	}

	public FileTime() {
	}

	public FileTime deepCopy() {
		return new FileTime(getLowDateTime(), getHighDateTime());
	}

	public static FileTime getFileTimeFromType(Type type) {
		return new FileTime(type.getVal());
	}

	public FileTime(long time) {
		lowDateTime = (int) time;
		highDateTime = (int) (time >> 32);
	}


	public int getLowDateTime() {
		return lowDateTime;
	}

	public int getHighDateTime() {
		return highDateTime;
	}

	public static FileTime getTime() {
		return getFTFromLong(System.currentTimeMillis() * 10000L + Type.FT_UT_OFFSET.getVal()); // Mushy
	}

	public static FileTime getCurrentTimeForQuest() {
		return getFTFromLong((long) ((System.currentTimeMillis() / 1000 / 60) * 0.1396987) + Type.QUEST_TIME.getVal());
	}

	public static FileTime getTimeFromLong(long time) {
		return getFTFromLong(time * 10000L + Type.FT_UT_OFFSET.getVal());
	}

	public static FileTime getFTFromLong(long value) {
		return new FileTime((int) value, (int) (value >>> 32));
	}

	public void encode(OutPacket outPacket) {
		outPacket.encodeInt(getHighDateTime());
		outPacket.encodeInt(getLowDateTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
