package net.swordie.ms.util;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

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
	@Transient
	private boolean isConvertedForClient;

	public long getLongValue() {
		return getLowDateTime() + (long) getHighDateTime() << 32;
	}

	public boolean isConvertedForClient() {
		return isConvertedForClient;
	}

	public void setConvertedForClient(boolean convertedForClient) {
		isConvertedForClient = convertedForClient;
	}

	public enum Type {
		// Mushy
		MAX_TIME(35120710, -1157267456),
		ZERO_TIME(21968699, -35635200),
		PERMANENT(150841440000000000L),
		FT_UT_OFFSET(116444592000000000L),
		QUEST_TIME(27111903),
		PLAIN_ZERO(0);

		private long val;

		Type(long val) {
			this.val = val;
		}

		Type(int lowPart, int highPart) {
			val = lowPart + ((long) highPart << 32);
		}

		public long getVal() {
			return val;
		}
	}

	public FileTime(int lowDateTime, int highDateTime) {
		this.lowDateTime = lowDateTime;
		this.highDateTime = highDateTime;
	}

	public FileTime() {
	}

	public FileTime(long time, boolean isConvertedForClient) {
		this(time);
		this.isConvertedForClient = isConvertedForClient;
	}

	/**
	 * Creates a new copy of this FileTime
	 * @return the new copy
	 */
	public FileTime deepCopy() {
		return new FileTime(getLowDateTime(), getHighDateTime());
	}

	public static FileTime getFileTimeFromType(Type type) {
		return new FileTime(type.getVal(), true);
	}

	/**
	 * Creates a new FileTime from a given long, by splitting up the long into a low and high part
	 * @param time the long the FileTime should be created from
	 */
	public FileTime(long time) {
		lowDateTime = (int) time;
		highDateTime = (int) (time >>> 32);
	}

	public int getLowDateTime() {
		return lowDateTime;
	}

	public int getHighDateTime() {
		return highDateTime;
	}

	/**
	 * Creates a new FileTime from the current time (System.currentTimeMillis()). Ensures the date is correctly c
	 * alculated for the client.
	 * @return FileTime corresponding to the current time
	 */
	public static FileTime currentTime() {
		return fromEpochMillis(System.currentTimeMillis());
	}

	/**
	 * Creates a new FileTime from a given time (millis since epoch).
	 * @param time millis since epoch that this FileTime should correspond to
	 * @return FileTime corresponding to the given time
	 */
	public static FileTime fromEpochMillis(long time) {
		return fromLong(time);
	}

	/**
	 * Converts this FileTime (storing epoch millis) to a format that the client expects.
	 * @return formatted FileTime
	 */
	public FileTime toClientFormat() {
		FileTime ft = fromLong((long) (Type.QUEST_TIME.getVal() + (toLong() / 3600000 * 8.38195)));
		ft.setConvertedForClient(true);
		return ft;
	}

	/**
	 * Creates a new FileTime from a given date. Ensures the date is correctly calculated for the client.
	 * @param localDateTime date that this FileTime should correspond to
	 * @return FileTime corresponding to the given date
	 */
	public static FileTime fromDate(LocalDateTime localDateTime) {
		return fromEpochMillis(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	}

	/**
	 * Returns the millis since epoch that this FileTime corresponds to.
	 * @return millis since epoch
	 */
	public long toMillis() {
		if (isConvertedForClient()) {
			return (long) (((toLong() - Type.QUEST_TIME.getVal()) * 3600000) / 8.3819306);
		}
		return toLong();
	}

	/**
	 * Returns the LocalDateTime that this FileTime corresponds to.
	 * @return corresponding date and time
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(toMillis()), ZoneId.systemDefault());
	}

	/**
	 * Creates a new FileTime from a given long, by splitting up the long into a low and high part
	 * @param value the long the FileTime should be created from
	 * @return FileTime from the given log
	 */
	public static FileTime fromLong(long value) {
		return new FileTime((int) value, (int) (value >>> 32));
	}

	/**
	 * Encodes this FileTime to a packet
	 * @param outPacket the packet to encode to
	 */
	public void encode(OutPacket outPacket) {
		if (!isConvertedForClient()) {
			toClientFormat().encode(outPacket);
		} else {
			outPacket.encodeInt(getHighDateTime());
			outPacket.encodeInt(getLowDateTime());
		}
	}

	/**
	 * Returns this FileTime as a long, by adding up the high and low part
	 * @return addition of the low and high part
	 */
	public long toLong() {
		return (getLowDateTime() & 0xFFFFFFFFL) + ((long) getHighDateTime() << 32);
	}

	/**
	 * Returns true if this FileTime's time is before the current time.
	 * @return expiredness
	 */
	public boolean isExpired() {
		return toMillis() < System.currentTimeMillis();
	}

	/**
	 * Checks if this FileTime is before a given date.
	 * @param localDateTime the given date
	 * @return if this FileTime is before the given date
	 */
	public boolean isBefore(LocalDateTime localDateTime) {
		return toLocalDateTime().isBefore(localDateTime);
	}

	/**
	 * Checks if this FileTime is before a given FileTime.
	 * @param fileTime the given date
	 * @return if this FileTime is before the given FileTime
	 */
	public boolean isBefore(FileTime fileTime) {
		return toLocalDateTime().isBefore(fileTime.toLocalDateTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileTime fileTime = (FileTime) o;
		return lowDateTime == fileTime.lowDateTime &&
				highDateTime == fileTime.highDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lowDateTime, highDateTime);
	}

	@Override
	public String toString() {
		return "FileTime{" +
				"lowDateTime=" + lowDateTime +
				", highDateTime=" + highDateTime +
				'}';
	}
}
