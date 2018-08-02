package net.swordie.ms.life.npc;

import net.swordie.ms.connection.packet.SlideMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2/19/2018.
 */
public class NpcScriptInfo {
	private byte speakerType = 4; // ?
	private int overrideSpeakerTemplateID;
	private byte param;
	private byte color;
	private String text;
	private NpcMessageType messageType;
	private String[] images;
	private int min;
	private int max;
	private String defaultText;
	private int defaultNumber;
	private byte type;
	private int time;
	private String title;
	private String problemText;
	private String hintText;
	private int quizType;
	private int answer;
	private int correctAnswers;
	private int remaining;
	private boolean angelicBuster;
	private boolean zeroBeta;
	private int dlgType;

	public byte getSpeakerType() {
		return speakerType;
	}

	public void setSpeakerType(byte speakerType) {
		this.speakerType = speakerType;
	}

	public int getOverrideSpeakerTemplateID() {
		return overrideSpeakerTemplateID;
	}

	public void setOverrideSpeakerTemplateID(int overrideSpeakerTemplateID) {
		this.overrideSpeakerTemplateID = overrideSpeakerTemplateID;
	}

	public byte getParam() {
		return param;
	}

	public void setParam(byte param) {
		this.param = param;
	}

	public byte getColor() {
		return color;
	}

	public void setColor(byte color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setMessageType(NpcMessageType messageType) {
		this.messageType = messageType;
	}

	public NpcMessageType getMessageType() {
		return messageType;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String[] getImages() {
		return images;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMin() {
		return min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMax() {
		return max;
	}

	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	public String getDefaultText() {
		return defaultText;
	}

	public int getDefaultNumber() {
		return defaultNumber;
	}

	public void setDefaultNumber(int defaultNumber) {
		this.defaultNumber = defaultNumber;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProblemText() {
		return problemText;
	}

	public void setProblemText(String problemText) {
		this.problemText = problemText;
	}

	public String getHintText() {
		return hintText;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	public int getQuizType() {
		return quizType;
	}

	public void setQuizType(int quizType) {
		this.quizType = quizType;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public boolean isAngelicBuster() {
		return angelicBuster;
	}

	public void setAngelicBuster(boolean angelicBuster) {
		this.angelicBuster = angelicBuster;
	}

	public boolean isZeroBeta() {
		return zeroBeta;
	}

	public void setZeroBeta(boolean zeroBeta) {
		this.zeroBeta = zeroBeta;
	}

    public int getDlgType() {
        return dlgType;
    }

    public void setDlgType(int dlgType) {
        this.dlgType = dlgType;
    }
}
