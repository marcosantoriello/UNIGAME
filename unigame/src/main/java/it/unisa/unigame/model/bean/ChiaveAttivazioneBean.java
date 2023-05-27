package it.unisa.unigame.model.bean;

import java.io.Serializable;

public class ChiaveAttivazioneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long chiave;
	private int videogioco;
	
	public ChiaveAttivazioneBean() {
		this.chiave=0;
		this.videogioco=0;
	}

	public long getChiave() {
		return this.chiave;
	}

	public void setChiave(long chiave) {
		this.chiave = chiave;
	}

	public int getVideogioco() {
		return this.videogioco;
	}

	public void setVideogioco(int videogioco) {
		this.videogioco = videogioco;
	}

	@Override
	public String toString() {
		return "ChiaveAttivazioneBean [chiave=" + chiave + ", videogioco=" + videogioco + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ChiaveAttivazioneBean)) {
			return false;
		}
		ChiaveAttivazioneBean other = (ChiaveAttivazioneBean) obj;
		return chiave == other.chiave && videogioco == other.videogioco;
	}
	
}